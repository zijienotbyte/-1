package model;

public class CellMes 
{
	private boolean cellStatus;
	private boolean isChangeStatus;
	
	public CellMes()
	{
		cellStatus = false;//ϸ��״̬
		isChangeStatus = false;//��һʱ��״̬�Ƿ���Ҫ�޸�
	}
	//���캯��
	public CellMes(boolean cellStatus,boolean isChangeStatus)
	{
		this.cellStatus = cellStatus;
		this.isChangeStatus=isChangeStatus;
	}
	
	//getter and setter
	public boolean isCellStatus() {
		return cellStatus;
	}

	public void setCellStatus(boolean cellStatus) {
		this.cellStatus = cellStatus;
	}

	public boolean isChangeStatus() {
		return isChangeStatus;
	}

	public void setChangeStatus(boolean isChangeStatus) {
		this.isChangeStatus = isChangeStatus;
	}
	
}
