package chess;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

import pieces.*;

public class Cell extends JPanel implements Cloneable{
	
	//Member Variables
	private static final long serialVersionUID = 1L;
	private boolean ispossibledestination;
	private JLabel content;
	private Piece piece;
	public int x,y;                             
	private boolean isSelected=false;
	private boolean ischeck=false;
	
	public Cell(int x,int y,Piece p)
	{		
		this.x=x;
		this.y=y;
		
		setLayout(new BorderLayout());
            int thickness = 2;
                Border blackline = BorderFactory.createLineBorder(Color.black,thickness);
	
	 if((x+y)%2==0){
         setBackground(Color.gray);
         setBorder( blackline);
         } 
         
	
         else{
         setBackground(Color.white);
         setBorder( blackline);
         }
	  
	 
	 if(p!=null)
		 setPiece(p);
	}
	
	//takes a cell as argument and returns a new cell will the same data but different reference
	public Cell(Cell cell) throws CloneNotSupportedException
	{
		this.x=cell.x;
		this.y=cell.y;
		setLayout(new BorderLayout());
		if((x+y)%2==0){
                     Border blackline = BorderFactory.createLineBorder(Color.black,2);
			setBackground( Color.gray);
                }
                else{
                     Border blackline = BorderFactory.createLineBorder(Color.black,2);
			setBackground(Color.white);
                }
		if(cell.getpiece()!=null)
		{
			setPiece(cell.getpiece().getcopy());
		}
		else
			piece=null;
	}
	
	public void setPiece(Piece p)    
	{
		piece=p;
		ImageIcon img=new javax.swing.ImageIcon(this.getClass().getResource(p.getPath()));
		content=new JLabel(img);
		this.add(content);
	}
	
	public void removePiece()      
	{
		if (piece instanceof King)
		{
			piece=null;
			this.remove(content);
		}
		else
		{
			piece=null;
			this.remove(content);
		}
	}
	
	
	public Piece getpiece()    
	{
		return this.piece;
	}
	
	public void select()      
	{
		this.setBorder(BorderFactory.createLineBorder(Color.green,5));
		this.isSelected=true;
	}
	
	public boolean isselected()   
	{
		return this.isSelected;
	}
	
	public void deselect()      
	{
		this.setBorder(BorderFactory.createLineBorder(Color.black,2));
		this.isSelected=false;
	}
	
	public void setpossibledestination()     
	{
		this.setBorder(BorderFactory.createLineBorder(Color.green,5));
		this.ispossibledestination=true;
	}
	
	public void removepossibledestination()      
	{
		this.setBorder(BorderFactory.createLineBorder(Color.black,2));
		this.ispossibledestination=false;
	}
	
	public boolean ispossibledestination()    
	{
		return this.ispossibledestination;
	}
	
	public void setcheck()     
	{
		this.setBackground(Color.red);
		this.ischeck=true;
	}
	
	public void removecheck()   
	{
		this.setBorder(null);
		if((x+y)%2==0){
                 Border blackline = BorderFactory.createLineBorder(Color.black,2);
                 setBackground(Color.gray);
                }
			
                else{
                        Border blackline = BorderFactory.createLineBorder(Color.black,2);
			setBackground(Color.white);
                }
		this.ischeck=false;
	}
	
	public boolean ischeck()    
	{
		return ischeck;
	}
}