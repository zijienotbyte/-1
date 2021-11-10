package view;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import control.GameProcess;


public class GameFrame
{
	JFrame gameFrame;//����
	
	JPanel operatePanel;//�������
	JPanel viewPanel;//��ʾ���
	GameProcess gameControl;//��Ϸ������
	
	//��ͼ��������
	boolean isRunning=false;//�Ƿ��ڷ�ֳ״̬
	int multipyCycle=2;//��ֳ����
	int multipyCount=0;//��ֳ����
	int row=10;//��ͼ��С
	int col=10;
	
	int[][] cells= new int[10][10];//ϸ������
	
	//�����������
	int sideLength=20;//ÿ�����񳤿�
	int adjustLength=50;//��ʼλ�þര�����ϱ߽����
	
	//�ı���
	JTextField multipyCountField;
	//��ť
	JButton changeButton;
	JButton reStartButton;
	JButton startButton;
	
	/****
	 * ������
	 * @param args
	 */
	public static void main(String[] args)
	{
		new GameFrame();
	}
	/*
	 * ���캯��
	 */
	public GameFrame()
	{
		gameFrame=new JFrame("ϸ��������Ϸ");
		//��С���ɱ�
		gameFrame.setResizable(false);
		
		//����λ��
		Dimension screnSize=Toolkit.getDefaultToolkit().getScreenSize();
		int width=1200;
		int height=800;
		gameFrame.setBounds((screnSize.width-width)/2, (screnSize.height-height)/2, width, height);
		//���ò���
		gameFrame.setLayout(null);

		gameControl=new GameProcess(row,col);
		//��ȡϸ������
		cells=gameControl.getAllCellStatus();
		//��ʼ�����
		operatePanel=getOperatePanel();
		viewPanel=getViewPanel();
		
		gameFrame.add(viewPanel);
		gameFrame.add(operatePanel);
		
		//���ô���ɼ�
		gameFrame.setVisible(true);
		
		gameFrame.addMouseListener(new ClickMonitor());

	}

	/***************************
	 * ��������
	 */
	private JPanel getOperatePanel()
	{
		JPanel operatePanel=new JPanel();

		//���ñ���ɫ�����λ��
		operatePanel.setBounds(800, 0, 360, 800);
		
		//���ò���
		operatePanel.setLayout(new GridLayout(12,1,10,10));
		//�������

		//���
		JPanel mapWidthPanel=new JPanel();
		JLabel mapWidthLabel=new JLabel("��ͼ������");
		JTextField mapWidthFiled=new JTextField(20);
		mapWidthFiled.setText(String.valueOf(col));
		mapWidthPanel.add(mapWidthLabel);
		mapWidthPanel.add(mapWidthFiled);
		
		//�߶�
		JPanel mapLengthPanel=new JPanel();
		JLabel mapLengthLabel=new JLabel("��ͼ������");
		JTextField mapLengthField=new JTextField(20);
		mapLengthField.setText(String.valueOf(row));
		mapLengthPanel.add(mapLengthLabel);
		mapLengthPanel.add(mapLengthField);
		
		JPanel multipyCountPanel=new JPanel();
		JLabel multipyCountLabel=new JLabel("��ֳ������");
		multipyCountField=new JTextField(20);
		multipyCountField.setText(String.valueOf(multipyCount));
		//���ô���Ϊ���ɸ���
		multipyCountField.setEditable(false);
		multipyCountPanel.add(multipyCountLabel);
		multipyCountPanel.add(multipyCountField);
		
		JPanel multipyCycPanel=new JPanel();
		JLabel multipyCycLabel=new JLabel("��ֳ����(s)��");
		JTextField multipyCycField=new JTextField(20);
		multipyCycField.setText(String.valueOf(multipyCycle));
		multipyCycPanel.add(multipyCycLabel);
		multipyCycPanel.add(multipyCycField);
		
		//��Ӱ�ť
		changeButton=new JButton("�޸�����");
		changeButton.addActionListener(new ActionListener()
				{
				@Override
					public void actionPerformed(ActionEvent e) {
					//�������ͼ��
					clearPaint(gameFrame.getGraphics());
					//��ȡ�ؼ�ֵ����Ա������
					row=Integer.parseInt(mapWidthFiled.getText());
					col=Integer.parseInt(mapLengthField.getText());
					multipyCycle=Integer.parseInt(multipyCycField.getText());
					multipyCount=0;
					multipyCountField.setText(String.valueOf(multipyCount));
					//��ʼ����Ϸ��ͼ
					gameControl=new GameProcess(row,col);
					
					//���Ʊ�����
					paintGirdLines(gameFrame.getGraphics());
					
					//Ϊ������Ӽ���
			//		gameFrame.addMouseListener(clickAction);
					}
				});
		
		reStartButton=new JButton("���ɵ�ͼ");
		reStartButton.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//���������ͼ
				gameControl.setDefaultWorld();
				cells=gameControl.getAllCellStatus();
				
				//������ͼ
				paintGirdLines(gameFrame.getGraphics());
				paintCells(gameFrame.getGraphics());
				
			}
		});
		
		startButton=new JButton("��ʼ��ֳ");
		startButton.addActionListener(new ActionListener() 
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				
				new MultipyThread().start();
			}
			
		});

		JButton stopButton=new JButton("ֹͣ��ֳ");
		stopButton.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent e) {
						isRunning=false;	
						changeButton.setEnabled(true);
						reStartButton.setEnabled(true);
						startButton.setEnabled(true);
					}		
				});

		
		//�����ӵ����
		//�հ����
		JLabel spaceLabel=new JLabel("");
		operatePanel.add(spaceLabel);
		operatePanel.add(spaceLabel);
		operatePanel.add(mapWidthPanel);
		operatePanel.add(mapLengthPanel);
		operatePanel.add(multipyCountPanel);
		operatePanel.add(multipyCycPanel);
		operatePanel.add(changeButton);
		operatePanel.add(reStartButton);
		operatePanel.add(startButton);
		operatePanel.add(stopButton);
		
		return operatePanel;
	}
	/*******
	 * ��ʾ����
	 * @param g
	 * @param cells
	 */
	private JPanel getViewPanel()
	{
		JPanel viewPanel=new JPanel();
		
		viewPanel.setBounds(0, 0, 800, 800);
		
		return viewPanel;
	}
	/**********
	 * �������ͼ��
	 * @param g
	 */
	public void clearPaint(Graphics g)
	{
		g.setColor(new Color(238,238,238));
		for(int i=0;i<=row;i++)
		{
			for(int j=0;j<=col;j++)
			{
			
					g.fillRect(j*sideLength+adjustLength, i*sideLength+adjustLength,sideLength,sideLength);
				
			}
		}
	}
	/**************
	 * ����������
	 * @param g
	 */
	public void paintGirdLines(Graphics g)
	{
		g.setColor(new Color(0, 0, 0));
		//������ ����
		for(int i=0;i<=row;i++)
		{
			g.drawLine(adjustLength, i*sideLength+adjustLength, col*sideLength+adjustLength,i*sideLength+adjustLength);
		}
		//����
		for(int i=0;i<=col;i++)
		{
			g.drawLine(i*sideLength+adjustLength,adjustLength,i*sideLength+adjustLength,row*sideLength+adjustLength);
		}
	}
	/*
	 * ���������е�ϸ��
	 */
	public void paintCells(Graphics g)
	{
		g.setColor(new Color(0,0,0));
		
		//���Ƹ���
		for(int i=0;i<row;i++)
		{
			for(int j=0;j<col;j++)
			{
				if(1==cells[i][j])
				{
					g.fillRect(j*sideLength+adjustLength, i*sideLength+adjustLength, sideLength, sideLength);
				}
			}
		}
	}
	/*****
	 * �����ض�λ�õ�ϸ��
	 * @param g
	 * @param row
	 * @param col
	 */
	public void createCell(Graphics g,int row,int col)
	{
		g.setColor(new Color(0,0,0));
		
		g.fillRect(col*sideLength+adjustLength, row*sideLength+adjustLength, sideLength, sideLength);
	}
	/*********
	 * ����ĳһλ�õ�ϸ��
	 * @param g
	 * @param row
	 * @param col
	 */
	public void killCell(Graphics g,int row,int col)
	{
		g.setColor(new Color(238,238,238));
		g.fillRect(col*sideLength+adjustLength, row*sideLength+adjustLength, sideLength, sideLength);
		//clearPaint(g);
	}
class MultipyThread extends Thread
{
	@Override
	public void run()
	{
		isRunning=true;	//�Ƿ����ڷ�ֳ
		changeButton.setEnabled(false);
		reStartButton.setEnabled(false);
		startButton.setEnabled(false);
		
		//��ť����Ϊ���ɵ��
		while(isRunning)
		{
			gameControl.cellMultiply();//ϸ������ı�һ��
			cells=gameControl.getAllCellStatus();//��ȡ�ı��ϸ����������
			
			clearPaint(gameFrame.getGraphics());//���ԭͼ
			paintGirdLines(gameFrame.getGraphics());//������ͼ
			paintCells(gameFrame.getGraphics());//����ϸ��
			multipyCount++;//��ֳһ�Σ����ڼ�һ
			multipyCountField.setText(String.valueOf(multipyCount));//���õ�ǰ����
			
			try {
				Thread.sleep(multipyCycle*1000);//�߳�����multipyCycle��
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
class ClickMonitor implements MouseListener
{

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int point_x=e.getX();
		int point_y=e.getY();
		
		if((point_x<adjustLength)||(point_x>col*sideLength+adjustLength)||(point_y<adjustLength)||(point_y>row*sideLength+adjustLength))
			return;
		int col=(point_x-adjustLength)/sideLength;
		int row=(point_y-adjustLength)/sideLength;
		
		boolean beforeStatus=gameControl.changeCellStatus(row, col);
		if(beforeStatus)
		{
			killCell(gameFrame.getGraphics(),row,col);
		}
		else
			createCell(gameFrame.getGraphics(),row,col);	
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
}

