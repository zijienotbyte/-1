package model;

import java.util.Random;

public class CellLifeWorld 
{
	private int worldRow;
	private int worldCol;
	private CellMes[][] cells;
	Random random = new Random();
	
	/**************
	 * ��������̶�Ĭ�������С
	 * @param worldRow
	 * @param worldCol
	 */
	public CellLifeWorld(int worldRow,int worldCol)
	{
		this.worldRow=worldRow;
		this.worldCol=worldCol;
		cells=new CellMes[worldRow][worldCol];
		for(int i=0;i<worldRow;i++)
			for(int j=0;j<worldCol;j++)
			{
				cells[i][j]=new CellMes();
			}
	//	randomCellsStatus();
	}
	/*
	 * ��ȡ���糤��
	 */
	public int getWorldRow()
	{
		return worldRow;
	}
	public int getWorldCol()
	{
		return worldCol;
	}
	/************
	 * ��ʼ����������
	 */
	public void randomCellsStatus()
	{
		for(int i=0;i<worldRow;i++)
		{
			for(int j=0;j<worldCol;j++)
			{
				//����0~9����� ����7ʱ��ϸ������Ϊ��
				int randomNum=random.nextInt(10);
				if(randomNum>7)
					cells[i][j].setCellStatus(true);
				else
					cells[i][j].setCellStatus(false);	
				//ϸ��״̬����Ϊ���ı�
				cells[i][j].setChangeStatus(false);
			}
		}
	}
	/**************
	 * ��ȡĳλ����ϸ��״̬ Ĭ�ϱ߽���ȫΪ��ϸ��
	 * @param row
	 * @param col
	 * @return
	 */
	public boolean getCellStatus(int row,int col)
	{
		if((row<0)||(row>worldRow-1)||(col<0)||(col>worldCol-1))
			return false;
		else
		return cells[row][col].isCellStatus();
	}
	/*********************
	 * �ı�ĳһλ���ϵ�ϸ��״̬
	 */
	public boolean changeCellStatus(int row,int col)
	{
		boolean beforeStatus=cells[row][col].isCellStatus();
		cells[row][col].setCellStatus(!beforeStatus);
		
		return beforeStatus;
	}
	/***************
	 * ��ȡ�ı��ʶ
	 */
	public boolean getChangeStatus(int row,int col)
	{
		return cells[row][col].isChangeStatus();
	}
	/*****************************
	 * ���øı��־
	 */
	public void setChangeFlag(int row,int col)
	{
		cells[row][col].setChangeStatus(true);
	}
	/*************************
	*ȡ���ı��ʶ
	*/
	public void canelChangeFlag(int row,int col)
	{
		cells[row][col].setChangeStatus(false);
	}
	/**************
	 * ��ȡ�ܱ�����ϸ������
	 */
	public int getPericyteCellNum(int row,int col)
	{
		int curNum=0;
		for(int i=row-1;i<=row+1;i++)
		{
			for(int j=col-1;j<=col+1;j++)
			{
				if((i==row)&&(j==col))
					continue;
				else if(getCellStatus(i,j))
				{
					curNum++;
				}
			}
		}
		return curNum;
	}
}