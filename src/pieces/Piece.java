package pieces;

import java.util.ArrayList;

import chess.Cell;


public abstract class Piece implements Cloneable{

	private int color;
	private String id=null;
	private String path;
	protected ArrayList<Cell> possiblemoves = new ArrayList<Cell>();  
	public abstract ArrayList<Cell> move(Cell pos[][],int x,int y);  
	
	
	public void setId(String id)
	{
		this.id=id;
	}
	
	
	public void setPath(String path)
	{
		this.path=path;
	}
	
	
	public void setColor(int c)
	{
		this.color=c;
	}
	
	
	public String getPath()
	{
		return path;
	}
	
	
	public String getId()
	{
		return id;
	}
	
	
	public int getcolor()
	{
		return this.color;
	}
	
	public Piece getcopy() throws CloneNotSupportedException
	{
		return (Piece) this.clone();
	}
}