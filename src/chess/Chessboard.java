package chess;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import pieces.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;


public class Chessboard extends JFrame implements MouseListener
{
	
	public static final int Height=700;
	public static final int Width=1110;
	public static Rook wr01,wr02,br01,br02;
	public static Knight wk01,wk02,bk01,bk02;
	public static Bishop wb01,wb02,bb01,bb02;
	public static Pawn wp[],bp[];
	public static Queen wq,bq;
	public static King wk,bk;
	public static Cell c,previous;
	public static int chance=0;
	public static Cell boardState[][];
	public static ArrayList<Cell> destinationlist = new ArrayList<Cell>();
	public static JPanel board = new JPanel(new GridLayout(8,8));
	public static JPanel controlPanel,WhitePlayer,BlackPlayer,temp,displayTime,showPlayer,time;
	public static JSplitPane split;
	public static JLabel label,mov;
	public static JLabel CHNC;
	public static Time timer;
	public static Chessboard Mainboard;
	public static boolean selected = false,end = false;
	public static Container content;
	static String move;

	private JSlider timeSlider;
	private BufferedImage image;
	public static int timeRemaining=60;
        public static String mov1 = "";
        public static String mov2 = "";
        public static String fn = "";
        public static String sn = "";
        public static int m1 = 0;
        public static int m2 = 0;
        
        
       
	public static void main(String[] args){
            
	   Mainboard = new Chessboard("","");
	   Mainboard.setResizable(false);
           
	}
	
	public Chessboard(String fn , String sn)
       {
        
	wr01=new Rook("WR01","images/white/White_Rook.png",0);
	wr02=new Rook("WR02","images/white/White_Rook.png",0);
	br01=new Rook("BR01","images/black/Black_Rook.png",1);
	br02=new Rook("BR02","images/black/Black_Rook.png",1);
	wk01=new Knight("WK01","images/white/White_Knight.png",0);
	wk02=new Knight("WK02","images/white/White_Knight.png",0);
	bk01=new Knight("BK01","images/black/Black_Knight.png",1);
	bk02=new Knight("BK02","images/black/Black_Knight.png",1);
	wb01=new Bishop("WB01","images/white/White_Bishop.png",0);
	wb02=new Bishop("WB02","images/white/White_Bishop.png",0);
	bb01=new Bishop("BB01","images/black/Black_Bishop.png",1);
	bb02=new Bishop("BB02","images/black/Black_Bishop.png",1);
	wq=new Queen("WQ","images/white/White_Queen.png",0);
	bq=new Queen("BQ","images/black/Black_Queen.png",1);
	wk=new King("WK","images/white/White_King.png",0,7,3);
	bk=new King("BK","images/black/Black_King.png",1,0,3);
	wp=new Pawn[8];
	bp=new Pawn[8];
	for(int i=0;i<8;i++)
	{
		wp[i]=new Pawn("WP0"+(i+1),"images/white/White_Pawn.png",0);
		bp[i]=new Pawn("BP0"+(i+1),"images/black/Black_Pawn.png",1);
	}
        
        
                String name1[] = fn.split(" ");
                String name2[] = sn.split(" ");
                mov1 = name1[0]+" ( White )";
                mov2 = name2[0]+" ( Black )";
		timeRemaining=60;
		move=mov1;
		board=new JPanel(new GridLayout(8,8));
		board.setMinimumSize(new Dimension(800,700));
		ImageIcon img = new ImageIcon(this.getClass().getResource("images/icon.png"));
		this.setIconImage(img.getImage());
                
		
		Cell cell;
		board.setBorder(BorderFactory.createLoweredBevelBorder());
		pieces.Piece P;
		content=getContentPane();
		setSize(Width,Height);
		setTitle("Chess Game V-0.1");
		content.setBackground(Color.black);
		controlPanel=new JPanel();
		content.setLayout(new BorderLayout());
		controlPanel.setLayout(new GridLayout(3,3));
		
		WhitePlayer=new JPanel();
		WhitePlayer.setLayout(new BorderLayout());
		
		BlackPlayer=new JPanel();
	        JPanel whitestats=new JPanel(new GridLayout(5,5));
		whitestats.add(new JLabel("     Player 1   :   "+fn));
		
		whitestats.add(new JLabel("     Player 2   :  "+sn));
  
		
		WhitePlayer.add(whitestats,BorderLayout.WEST);
		controlPanel.add(WhitePlayer);
		controlPanel.add(BlackPlayer);
		
		
		boardState=new Cell[8][8];
		for(int i=0;i<8;i++)
			for(int j=0;j<8;j++)
			{	
				P=null;
				if(i==0&&j==0)
					P=br01;
				else if(i==0&&j==7)
					P=br02;
				else if(i==7&&j==0)
					P=wr01;
				else if(i==7&&j==7)
					P=wr02;
				else if(i==0&&j==1)
					P=bk01;
				else if (i==0&&j==6)
					P=bk02;
				else if(i==7&&j==1)
					P=wk01;
				else if (i==7&&j==6)
					P=wk02;
				else if(i==0&&j==2)
					P=bb01;
				else if (i==0&&j==5)
					P=bb02;
				else if(i==7&&j==2)
					P=wb01;
				else if(i==7&&j==5)
					P=wb02;
				else if(i==0&&j==3)
					P=bk;
				else if(i==0&&j==4)
					P=bq;
				else if(i==7&&j==3)
					P=wk;
				else if(i==7&&j==4)
					P=wq;
				else if(i==1)
				P=bp[j];
				else if(i==6)
					P=wp[j];
				cell=new Cell(i,j,P);
				cell.addMouseListener(this);
				board.add(cell);
				boardState[i][j]=cell;
			}
		showPlayer = new JPanel(new FlowLayout());  
                
		JLabel setTime=new JLabel("   .................................................................  "); 
                JButton newGame = new JButton(" New Game ");
                 newGame.addActionListener(new ActionListener(){  
              public void actionPerformed(ActionEvent e){  
            
            int reply = JOptionPane.showConfirmDialog(null,"Are You Sure ?" ,"",JOptionPane.YES_NO_OPTION);
            if (reply == JOptionPane.YES_OPTION) {
                  java.awt.Window win[] = java.awt.Window.getWindows(); 
                  for(int i=0;i<win.length;i++){ 
                   win[i].dispose(); 
                   win[i]=null;
                  } 
            
                  new Chessboard(fn,sn).setVisible(true);
                  move = mov1;
                  chance = 0;
            } else {
               
            }                
    }  
    });  
                newGame.setBackground(Color.black);
                newGame.setForeground(Color.white);
                newGame.setFont(new Font("Arial",Font.BOLD,20));
                newGame.setFocusable(false);
                newGame.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                
		
		setTime.setFont(new Font("Arial",Font.BOLD,16));
		label = new JLabel("Welcom!", JLabel.CENTER);
		  label.setFont(new Font("SERIF", Font.BOLD, 30));
                  label.setForeground(Color.green);
	      displayTime=new JPanel(new FlowLayout());
	      time=new JPanel(new GridLayout(5,5));
	      time.add(setTime);
	      time.add(showPlayer);
	     
	      time.add(displayTime);
              time.add(new JLabel(""));
              time.add(newGame);
	      controlPanel.add(time);
		board.setMinimumSize(new Dimension(800,700));
		
		
		

		controlPanel.setMinimumSize(new Dimension(282,100));
		split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,board, controlPanel);
                
                
                mov=new JLabel("Move :");
		mov.setFont(new Font("TimesRoman",Font.BOLD,20));
		mov.setForeground(Color.blue);
		showPlayer.add(mov);
		CHNC=new JLabel(move);
		CHNC.setFont(new Font("TimesRoman",Font.BOLD,20));
		CHNC.setForeground(Color.red);
		showPlayer.add(CHNC);
		
		displayTime.add(label);
		timer=new Time(label);
		timer.start();
                
                split.setEnabled(false);
                
	    content.add(split);
            this.setResizable(false);
            this.setLocation(130, 40);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
	
	
	public static void changeChance()
	{
		if (boardState[getKing(chance).getx()][getKing(chance).gety()].ischeck())
		{
			chance ^= 1;
			endGame();
		}
		if(destinationlist.isEmpty()==false)
			cleandestinations(destinationlist);
		if(previous!=null)
			previous.deselect();
		previous=null;
		chance^=1;
		if(!end && timer!=null)
		{
			timer.reset();
			timer.start();
			showPlayer.remove(CHNC);
			if(Chessboard.move==mov1)
				Chessboard.move=mov2;
			else
				Chessboard.move=mov1;
			CHNC.setText(Chessboard.move);
			showPlayer.add(CHNC);
		}
	}
	
	//A function to retrieve the Black King or White King
	public static King getKing(int color)
	{
		if (color==0)
			return wk;
		else
			return bk;
	}
	
	//A function to clean the highlights of possible destination cells
        //Function to clear the last move's destinations
    public static void cleandestinations(ArrayList<Cell> destlist)      

    {
    	ListIterator<Cell> it = destlist.listIterator();
    	while(it.hasNext())
    		it.next().removepossibledestination();
    }
    
    //A function that indicates the possible moves by highlighting the Cells
    private void lightDestinations(ArrayList<Cell> destlist)
    {
    	ListIterator<Cell> it = destlist.listIterator();
    	while(it.hasNext())
    		it.next().setpossibledestination();
    }
    
    
  //Function to check if the king will be in danger if the given move is made
    private boolean kingIndanger(Cell fromcell,Cell tocell)
    {
    	Cell newboardstate[][] = new Cell[8][8];
    	for(int i=0;i<8;i++)
    		for(int j=0;j<8;j++)
    		{	try { newboardstate[i][j] = new Cell(boardState[i][j]);} catch (CloneNotSupportedException e){e.printStackTrace(); System.out.println("There is a problem with cloning !!"); }}
    	
    	if(newboardstate[tocell.x][tocell.y].getpiece()!=null)
			newboardstate[tocell.x][tocell.y].removePiece();
    	
		newboardstate[tocell.x][tocell.y].setPiece(newboardstate[fromcell.x][fromcell.y].getpiece());
		if(newboardstate[tocell.x][tocell.y].getpiece() instanceof King)
		{
			((King)(newboardstate[tocell.x][tocell.y].getpiece())).setx(tocell.x);
			((King)(newboardstate[tocell.x][tocell.y].getpiece())).sety(tocell.y);
		}
		newboardstate[fromcell.x][fromcell.y].removePiece();
		if (((King)(newboardstate[getKing(chance).getx()][getKing(chance).gety()].getpiece())).isindanger(newboardstate)==true)
			return true;
		else
			return false;
    }
    
    //A function to eliminate the possible moves that will put the King in danger
    private ArrayList<Cell> checkDestination (ArrayList<Cell> destlist, Cell fromcell)
    {
    	ArrayList<Cell> newlist = new ArrayList<Cell>();
    	Cell newboardstate[][] = new Cell[8][8];
    	ListIterator<Cell> it = destlist.listIterator();
    	int x,y;
    	while (it.hasNext())
    	{
    		for(int i=0;i<8;i++)
        		for(int j=0;j<8;j++)
        		{	try { newboardstate[i][j] = new Cell(boardState[i][j]);} catch (CloneNotSupportedException e){e.printStackTrace();}}
    		
    		Cell tempc = it.next();
    		if(newboardstate[tempc.x][tempc.y].getpiece()!=null)
    			newboardstate[tempc.x][tempc.y].removePiece();
    		newboardstate[tempc.x][tempc.y].setPiece(newboardstate[fromcell.x][fromcell.y].getpiece());
    		x=getKing(chance).getx();
    		y=getKing(chance).gety();
    		if(newboardstate[fromcell.x][fromcell.y].getpiece() instanceof King)
    		{
    			((King)(newboardstate[tempc.x][tempc.y].getpiece())).setx(tempc.x);
    			((King)(newboardstate[tempc.x][tempc.y].getpiece())).sety(tempc.y);
    			x=tempc.x;
    			y=tempc.y;
    		}
    		newboardstate[fromcell.x][fromcell.y].removePiece();
    		if ((((King)(newboardstate[x][y].getpiece())).isindanger(newboardstate)==false))
    			newlist.add(tempc);
    	}
    	return newlist;
    }
    
    //A Function to filter the possible moves when the king of the current player is under Check 
    private ArrayList<Cell> incheckfilter (ArrayList<Cell> destlist, Cell fromcell, int color)
    {
    	ArrayList<Cell> newlist = new ArrayList<Cell>();
    	Cell newboardstate[][] = new Cell[8][8];
    	ListIterator<Cell> it = destlist.listIterator();
    	int x,y;
    	while (it.hasNext())
    	{
    		for(int i=0;i<8;i++)
        		for(int j=0;j<8;j++)
        		{	try { newboardstate[i][j] = new Cell(boardState[i][j]);} catch (CloneNotSupportedException e){e.printStackTrace();}}
    		Cell tempc = it.next();
    		if(newboardstate[tempc.x][tempc.y].getpiece()!=null)
    			newboardstate[tempc.x][tempc.y].removePiece();
    		newboardstate[tempc.x][tempc.y].setPiece(newboardstate[fromcell.x][fromcell.y].getpiece());
    		x=getKing(color).getx();
    		y=getKing(color).gety();
    		if(newboardstate[tempc.x][tempc.y].getpiece() instanceof King)
    		{
    			((King)(newboardstate[tempc.x][tempc.y].getpiece())).setx(tempc.x);
    			((King)(newboardstate[tempc.x][tempc.y].getpiece())).sety(tempc.y);
    			x=tempc.x;
    			y=tempc.y;
    		}
    		newboardstate[fromcell.x][fromcell.y].removePiece();
    		if ((((King)(newboardstate[x][y].getpiece())).isindanger(newboardstate)==false))
    			newlist.add(tempc);
    	}
    	return newlist;
    }
    
    //A function to check if the King is check-mate. The Game Ends if this function returns true.
    public boolean checkmate(int color)
    {
    	ArrayList<Cell> dlist = new ArrayList<Cell>();
    	for(int i=0;i<8;i++)
    	{
    		for(int j=0;j<8;j++)
    		{
    			if (boardState[i][j].getpiece()!=null && boardState[i][j].getpiece().getcolor()==color)
    			{
    				dlist.clear();
    				dlist=boardState[i][j].getpiece().move(boardState, i, j);
    				dlist=incheckfilter(dlist,boardState[i][j],color);
    				if(dlist.size()!=0)
    					return false;
    			}
    		}
    	}
    	return true;
    }
	
    @SuppressWarnings("deprecation")
	public static void endGame()
    {
    	cleandestinations(destinationlist);
    	timer.countdownTimer.stop();
    	if(previous!=null)
    		previous.removePiece();
   
		JOptionPane.showMessageDialog(board,"Checkmate!!!");
		end=true;
                System.exit(0);
    }
    
    //These are the abstract function of the parent class. Only relevant method here is the On-Click Fuction
    //which is called when the user clicks on a particular cell
    //every cell click mouse event
	@Override
	public void mouseClicked(MouseEvent arg0){
		// TODO Auto-generated method stub
		c=(Cell)arg0.getSource();
		if (previous==null)
		{
			if(c.getpiece()!=null)
			{
				if(c.getpiece().getcolor()!=chance)
					return;
				c.select();
				previous=c;
				destinationlist.clear();
				destinationlist=c.getpiece().move(boardState, c.x, c.y);
				if(c.getpiece() instanceof King)
					destinationlist=checkDestination(destinationlist,c);
				else
				{
					if(boardState[getKing(chance).getx()][getKing(chance).gety()].ischeck())
						destinationlist = new ArrayList<Cell>(checkDestination(destinationlist,c));
					else if(destinationlist.isEmpty()==false && kingIndanger(c,destinationlist.get(0)))
						destinationlist.clear();
				}
				lightDestinations(destinationlist);
			}
		}
		else
		{
			if(c.x==previous.x && c.y==previous.y)
			{
				c.deselect();
				cleandestinations(destinationlist);
				destinationlist.clear();
				previous=null;
			}
			else if(c.getpiece()==null||previous.getpiece().getcolor()!=c.getpiece().getcolor())
			{
				if(c.ispossibledestination())
				{
					if(c.getpiece()!=null)
						c.removePiece();
					c.setPiece(previous.getpiece());
					if (previous.ischeck())
						previous.removecheck();
					previous.removePiece();
					if(getKing(chance^1).isindanger(boardState))
					{
						boardState[getKing(chance^1).getx()][getKing(chance^1).gety()].setcheck();
						if (checkmate(getKing(chance^1).getcolor()))
						{
							previous.deselect();
							if(previous.getpiece()!=null)
								previous.removePiece();
							endGame();
						}
					}
					if(getKing(chance).isindanger(boardState)==false)
						boardState[getKing(chance).getx()][getKing(chance).gety()].removecheck();
					if(c.getpiece() instanceof King)
					{
						((King)c.getpiece()).setx(c.x);
						((King)c.getpiece()).sety(c.y);
					}
					changeChance();
					if(!end)
					{
						timer.reset();
						timer.start();
					}
				}
				if(previous!=null)
				{
					previous.deselect();
					previous=null;
				}
				cleandestinations(destinationlist);
				destinationlist.clear();
			}
			else if(previous.getpiece().getcolor()==c.getpiece().getcolor())
			{
				previous.deselect();
				cleandestinations(destinationlist);
				destinationlist.clear();
				c.select();
				previous=c;
				destinationlist=c.getpiece().move(boardState, c.x, c.y);
				if(c.getpiece() instanceof King)
					destinationlist=checkDestination(destinationlist,c);
				else
				{
					if(boardState[getKing(chance).getx()][getKing(chance).gety()].ischeck())
						destinationlist = new ArrayList<Cell>(checkDestination(destinationlist,c));
					else if(destinationlist.isEmpty()==false && kingIndanger(c,destinationlist.get(0)))
						destinationlist.clear();
				}
				lightDestinations(destinationlist);
			}
		}
		if(c.getpiece()!=null && c.getpiece() instanceof King)
		{
			((King)c.getpiece()).setx(c.x);
			((King)c.getpiece()).sety(c.y);
		}
	}
    
    //Other Irrelevant abstract function. Only the Click Event is captured.
	@Override
	public void mouseEntered(MouseEvent arg0) {}
	@Override
	public void mouseExited(MouseEvent arg0) {}
	@Override
	public void mousePressed(MouseEvent arg0) {}
	@Override
	public void mouseReleased(MouseEvent arg0) {}

    public void actionPerformed(ActionEvent e){  
             
}  
    
}