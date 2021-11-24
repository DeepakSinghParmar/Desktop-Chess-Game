package pieces;

import java.util.ArrayList;

import chess.Cell;

public class Knight extends Piece{
	
	
	public Knight(String i,String p,int c)
	{
		setId(i);
		setPath(p);
		setColor(c);
	}
	
	
	public ArrayList<Cell> move(Cell state[][],int x,int y)
	{
		possiblemoves.clear();
		int posx[]={x+1,x+1,x+2,x+2,x-1,x-1,x-2,x-2};
		int posy[]={y-2,y+2,y-1,y+1,y-2,y+2,y-1,y+1};
		for(int i=0;i<8;i++)
			if((posx[i]>=0&&posx[i]<8&&posy[i]>=0&&posy[i]<8))
				if((state[posx[i]][posy[i]].getpiece()==null||state[posx[i]][posy[i]].getpiece().getcolor()!=this.getcolor()))
				{
					possiblemoves.add(state[posx[i]][posy[i]]);
				}
		return possiblemoves;
	}
}