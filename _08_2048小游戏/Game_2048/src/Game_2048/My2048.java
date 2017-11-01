package Game_2048;

import java.awt.GridLayout;
import javax.swing.JFrame;

public class My2048 extends JFrame
{
    private static final long serialVersionUID = 7090479580633473730L;

    public My2048()
    {
        setTitle("2048");
        setSize(300, 400);
        setLocation(500, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new GridLayout(4, 4, 5, 5));
        new Operation(this);
        this.setVisible(true);
    }

    public static void main(String args[])
    {
        new My2048();
        
    }

}