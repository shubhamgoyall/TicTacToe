import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


class CustomWindowAdapter extends WindowAdapter{
    public CustomWindowAdapter()
    {

    }
    public void windowClosing(WindowEvent e)
    {
        System.exit(0);
    }
}
class CustomMouseAdapter extends MouseAdapter
{
    JButton[][] board;
    TicTacToe ttt;
    boolean reset=false;
    int player=1;
    int count=0;
    public CustomMouseAdapter(TicTacToe t, JButton[][] b)
    {
        ttt=t;
        board=b;
    }
    public void mouseClicked(MouseEvent e)
    {
        JButton btn=(JButton)(e.getSource());

        if(btn.getText()=="RESET")
        {
            reset();
            return;
        }
        if(reset==true) {
            ttt.setTitle("Please RESET");
            return ;
        }


        if(btn.getText()=="")
        {
            btn.setText((player==1)?"O":"X");
            btn.setEnabled(false);
            count++;

            player=(player==1)?2:1;
        }
        if(count>=5 && check())
        {
            int won=(player==1)?2:1;
            ttt.setTitle("Player "+won+" won!!!!!");
            reset=true;
            return ;
        }
        
        if(count==9)
        {
            ttt.setTitle("Please RESET");
        }
    }

    private void reset()
    {
        for(int i=0;i<3;i++)
        {
            for (int j = 0; j < 3; j++) {
                board[i][j].setText("");
                board[i][j].setEnabled(true);
            }
        }
        player=1;
        count=0;
        reset=false;
        ttt.setTitle("Tic Tac Toe");
    }

    private boolean check()
    {

        for(int i=0;i<3;i++)
        {
            if(board[i][0].getText()==board[i][1].getText() &&
                board[i][1].getText()==board[i][2].getText())
            {
                return board[i][0].getText()!="";
            }
        }
        for(int i=0;i<3;i++)
        {
            if(board[0][i].getText()==board[1][i].getText() &&
                board[1][i].getText()==board[2][i].getText())
            {
                return board[0][i].getText()!="";
            }
        }

        if(board[0][0].getText()==board[1][1].getText() &&
            board[1][1].getText()==board[2][2].getText())
        {
            return board[1][1].getText()!="";
        }

        if(board[0][2].getText()==board[1][1].getText() &&
            board[1][1].getText()==board[2][0].getText())
        {
            return board[1][1].getText()!="";
        }


        return false;
    }
}
public class TicTacToe extends JFrame // main window for the game
{

    public TicTacToe()
    {
        super("Tic Tac Toe"); // set title for the frame
        this.setLayout(new GridLayout(4,3)); // 3x3 board

        //X button should exit the game
        this.addWindowListener(new CustomWindowAdapter());

        JButton[][] board=new JButton[3][3];
        CustomMouseAdapter cma= new CustomMouseAdapter(this,board); // adding functionality to the buttons

        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                board[i][j]=new JButton("");
                board[i][j].addMouseListener(cma);
                this.add(board[i][j]);
            }
        }

        JButton reset= new JButton("RESET");
        reset.addMouseListener(cma);
        this.add(reset);
        this.setSize(300,300);

        
        this.setVisible(true);

    }

    public static void main(String args[])
    {
        TicTacToe ttt=new TicTacToe();

    }

}
