package view;
import control.GameProcess;

public class LifeGameTest 
{
public static void main(String[] args)
{
	GameProcess gameControl=new GameProcess(10,10);
	int[][] array=gameControl.getAllCellStatus();
	for(int i=0;i<1000;i++)
	{
		for(int j=0;j<10;j++)
		{
			for(int k=0;k<10;k++)
			{
				System.out.print(array[j][k]+"  ");
			}
			System.out.println("");
	}
		gameControl.cellMultiply();
	}
}
}
