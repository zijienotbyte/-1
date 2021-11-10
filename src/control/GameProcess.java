package control;

import model.CellLifeWorld;

public class GameProcess 
{
	protected CellLifeWorld world;
	public GameProcess()
	{
		world=new CellLifeWorld(10,10);
	}
	public GameProcess(int row,int col)
	{
		world=new CellLifeWorld(row,col);
	}
	
	/*���ܹ���
	 */
	public void cellMultiply()
	{
		//ϸ����һ״̬�ж�
		for(int i=0;i<world.getWorldRow();i++)
		{
			for(int j=0;j<world.getWorldCol();j++)
			{
				//��ȡ�ܱ�ϸ������
				int cellNum=world.getPericyteCellNum(i, j);
				if(world.getCellStatus(i, j)!=cellSavePrinciple(world.getCellStatus(i, j),cellNum))
				{
					world.setChangeFlag(i, j);
				}				
			}
		}
		//ϸ��״̬�ı�
		for(int i=0;i<world.getWorldRow();i++)
		{
			for(int j=0;j<world.getWorldCol();j++)
			{
				if(world.getChangeStatus(i, j))
				{
					world.changeCellStatus(i, j);
					world.canelChangeFlag(i, j);
				}
			}
		}
	}
	/*****************
	 * ϸ���������
	 */
	private boolean cellSavePrinciple(boolean curStatus,int surCellNumber)
	{
		if(surCellNumber==3)
		{
			return true;
		}
		else if(surCellNumber==2)
		{
			return curStatus;
		}
		else
		{
			return false;
		}
	}
	/*************
	 * �޸�ĳһλ��ϸ��״̬ ������ԭ״̬
	 * @param row
	 * @param col
	 */
	public boolean changeCellStatus(int row,int col)
	{
		return world.changeCellStatus(row, col);
	}
	/*****
	 * ����ϸ����ʼ״̬Ϊ���
	 */
	public void setDefaultWorld()
	{
		world.randomCellsStatus();
	}
	/***********
	 * ����ϸ���������Ķ�ά����
	 * @return
	 */
	public int[][] getAllCellStatus()
	{
		int width=world.getWorldRow();
		int length=world.getWorldCol();
		
		int[][] cellSaveRect=new int[width][length];
		
		for(int i=0;i<width;i++)
		{
			for(int j=0;j<length;j++)
			{
				if(world.getCellStatus(i, j))
				{
					cellSaveRect[i][j]=1;
				}
				else
				{
					cellSaveRect[i][j]=0;
				}
			}
		}
		return cellSaveRect;
	}

}
