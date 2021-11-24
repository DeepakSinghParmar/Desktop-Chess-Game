package chess;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.Timer;


public class Time
{
    private JLabel label;
    public Timer countdownTimer;
    int Timerem;
    public Time(JLabel passedLabel)
    {
       countdownTimer = new Timer(1000, new CountdownTimerListener());
       this.label = passedLabel;
       Timerem=Chessboard.timeRemaining;
    }
    

    public void start()
    {
    	countdownTimer.start();
    }
    
   
    public void reset()
    {
    	Timerem=Chessboard.timeRemaining;
    }
    
    //A function that is called after every second. It updates the timer and takes other necessary actions
    class CountdownTimerListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
       	 int min,sec;
       	 if (Timerem > 0)
       	 {
           	min=Timerem/60;
           	sec=Timerem%60;
            label.setText("0"+String.valueOf(min)+":"+(sec>=10?String.valueOf(sec):"0"+String.valueOf(sec)));
            Timerem--;
         }
       	 else
       	 {
               label.setText("Time's up!");
               reset();
               start();
               Chessboard.changeChance();
		 }
    }
 }
}