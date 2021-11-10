package model;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CellLifeWorldTest
{
	private CellMes[][] cells;
	private static CellLifeWorld world =new CellLifeWorld(10,10);

	
	@Before
	public void setUp() throws Exception 
	{
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetWorldRow() 
	{
		world.getWorldRow();
		assertEquals(10,world.getWorldRow());
	}

	@Test
	public void testGetWorldCol()
	{
		world.getWorldCol();
		assertEquals(10,world.getWorldCol());
	}

	@Test
	public void testGetCellStatus()
	{
		assertEquals(false,world.getCellStatus(-1,9));
		assertEquals(false,world.getCellStatus(0,9));
		assertEquals(false,world.getCellStatus(5,5));
	}

	@Test
	public void testChangeCellStatus()
	{
		assertEquals(true,world.changeCellStatus(5,5));
	}
}
