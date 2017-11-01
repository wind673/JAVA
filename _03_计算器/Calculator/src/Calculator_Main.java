import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Window.Type;
import javax.swing.JTextArea;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Calculator_Main extends JFrame
{
    public static int SIGN_ADD = 1;
    public static int SIGN_MIN = 2;
    public static int SIGN_MULT = 3;
    public static int SIGN_DIVI = 4;

    private JPanel contentPane;
    private static JTextField textField_Result;

    public static double  Last_Num = 0;
    public static int  	Last_Sign = 0;

    public static double  Input_Num = 0;
    public static String  Input_Str = "";


    /**
     * Launch the application.
     */
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                try
                {
                    Calculator_Main frame = new Calculator_Main();
                    frame.setVisible(true);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Calculator_Main()
    {
        setResizable(false);
        setAutoRequestFocus(false);
        setTitle("\u8BA1\u7B97\u5668");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 244, 363);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton btnNewButton = new JButton("7");
        btnNewButton.setFont(new Font("宋体", Font.PLAIN, 24));
        btnNewButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                Input_Str += btnNewButton.getText();
                textField_Result.setText(Input_Str);
            }
        });
        btnNewButton.setBounds(10, 94, 54, 57);
        contentPane.add(btnNewButton);

        JButton button = new JButton("8");
        button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                Input_Str += button.getText();
                textField_Result.setText(Input_Str);
            }
        });
        button.setFont(new Font("宋体", Font.PLAIN, 24));
        button.setBounds(65, 94, 54, 57);
        contentPane.add(button);

        JButton button_1 = new JButton("9");
        button_1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                Input_Str += button_1.getText();
                textField_Result.setText(Input_Str);
            }
        });
        button_1.setFont(new Font("宋体", Font.PLAIN, 24));
        button_1.setBounds(120, 94, 54, 57);
        contentPane.add(button_1);

        JButton button_2 = new JButton("6");
        button_2.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                Input_Str += button_2.getText();
                textField_Result.setText(Input_Str);
            }
        });
        button_2.setFont(new Font("宋体", Font.PLAIN, 24));
        button_2.setBounds(120, 153, 54, 57);
        contentPane.add(button_2);

        JButton button_3 = new JButton("5");
        button_3.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                Input_Str += button_3.getText();
                textField_Result.setText(Input_Str);
            }
        });
        button_3.setFont(new Font("宋体", Font.PLAIN, 24));
        button_3.setBounds(65, 153, 54, 57);
        contentPane.add(button_3);

        JButton button_4 = new JButton("4");
        button_4.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                Input_Str += button_4.getText();
                textField_Result.setText(Input_Str);
            }
        });
        button_4.setFont(new Font("宋体", Font.PLAIN, 24));
        button_4.setBounds(10, 153, 54, 57);
        contentPane.add(button_4);

        JButton button_5 = new JButton("3");
        button_5.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                Input_Str += button_5.getText();
                textField_Result.setText(Input_Str);
            }
        });
        button_5.setFont(new Font("宋体", Font.PLAIN, 24));
        button_5.setBounds(120, 211, 54, 57);
        contentPane.add(button_5);

        JButton button_6 = new JButton("2");
        button_6.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                Input_Str += button_6.getText();
                textField_Result.setText(Input_Str);
            }
        });
        button_6.setFont(new Font("宋体", Font.PLAIN, 24));
        button_6.setBounds(65, 211, 54, 57);
        contentPane.add(button_6);

        JButton button_7 = new JButton("1");
        button_7.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                Input_Str += button_7.getText();
                textField_Result.setText(Input_Str);
            }
        });
        button_7.setFont(new Font("宋体", Font.PLAIN, 24));
        button_7.setBounds(10, 211, 54, 57);
        contentPane.add(button_7);

        JButton btnBackspace = new JButton("BackSpace");
        btnBackspace.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
            	Input_Str = Input_Str.substring(0,Input_Str.length()-1);
            	textField_Result.setText(Input_Str);
            }
        });
        btnBackspace.setBackground(new Color(176, 196, 222));
        btnBackspace.setForeground(new Color(47, 79, 79));
        btnBackspace.setBounds(5, 48, 105, 33);
        contentPane.add(btnBackspace);

        JButton button_8 = new JButton("*");
        button_8.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {

                if(!Input_Str.isEmpty())
                {
                    Input_Num = Double.parseDouble(Input_Str);
                    Input_Str = "";

                    Result_Show(); 
                }
                Last_Sign = SIGN_MULT;
            }
        });
        button_8.setBackground(Color.CYAN);
        button_8.setForeground(Color.BLACK);
        button_8.setFont(new Font("宋体", Font.PLAIN, 24));
        button_8.setBounds(179, 176, 50, 37);
        contentPane.add(button_8);

        JButton button_9 = new JButton("0");
        button_9.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(Double.parseDouble(Input_Str) != 0)
                {
                    Input_Str += "0";
                    textField_Result.setText(Input_Str);
                }

            }
        });
        button_9.setFont(new Font("宋体", Font.PLAIN, 24));
        button_9.setBounds(10, 270, 109, 57);
        contentPane.add(button_9);

        JButton button_10 = new JButton(".");
        button_10.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(Input_Str.indexOf(".") < 0)//没有点
                {
                    Input_Str += ".";
                    textField_Result.setText(Input_Str);

                }

            }
        });
        button_10.setFont(new Font("宋体", Font.PLAIN, 24));
        button_10.setBounds(121, 270, 54, 57);
        contentPane.add(button_10);

        JButton button_11 = new JButton("-");
        button_11.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(!Input_Str.isEmpty())
                {
                    Input_Num = Double.parseDouble(Input_Str);
                    Input_Str = "";

                    Result_Show(); 
                }
                Last_Sign = SIGN_MIN;
            }
        });
        button_11.setBackground(Color.CYAN);
        button_11.setForeground(Color.BLACK);
        button_11.setFont(new Font("宋体", Font.PLAIN, 24));
        button_11.setBounds(179, 134, 50, 37);
        contentPane.add(button_11);

        JButton button_12 = new JButton("+");
        button_12.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(!Input_Str.isEmpty())
                {
                    Input_Num = Double.parseDouble(Input_Str);
                    Input_Str = "";
                    
                    Result_Show(); 
                }
                Last_Sign = SIGN_ADD;
            }
        });
        button_12.setBackground(Color.CYAN);
        button_12.setForeground(Color.BLACK);
        button_12.setFont(new Font("宋体", Font.PLAIN, 24));
        button_12.setBounds(179, 94, 50, 37);
        contentPane.add(button_12);

        JButton button_13 = new JButton("/");
        button_13.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(!Input_Str.isEmpty())
                {
                    Input_Num = Double.parseDouble(Input_Str);
                    Input_Str = "";

                    Result_Show();
                   
                }
                Last_Sign = SIGN_DIVI;
            }
        });
        button_13.setBackground(Color.CYAN);
        button_13.setForeground(Color.BLACK);
        button_13.setFont(new Font("宋体", Font.PLAIN, 24));
        button_13.setBounds(179, 217, 50, 37);
        contentPane.add(button_13);

        JButton btnCe = new JButton("CE");
        btnCe.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                Input_Num = 0;
                Input_Str = "";
                textField_Result.setText(String.valueOf(Input_Num));
            }
        });
        btnCe.setBackground(new Color(176, 196, 222));
        btnCe.setForeground(new Color(47, 79, 79));
        btnCe.setFont(new Font("宋体", Font.PLAIN, 16));
        btnCe.setBounds(115, 48, 58, 33);
        contentPane.add(btnCe);

        JButton btnC = new JButton("C");
        btnC.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                Last_Num = 0;
                Last_Sign = 0;
                Input_Num = 0;
                Input_Str = "";
                textField_Result.setText(String.valueOf(Last_Num));
            }
        });
        btnC.setBackground(new Color(176, 196, 222));
        btnC.setForeground(new Color(47, 79, 79));
        btnC.setFont(new Font("宋体", Font.PLAIN, 24));
        btnC.setBounds(178, 48, 55, 33);
        contentPane.add(btnC);

        JButton button_14 = new JButton("=");
        button_14.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {

                if(!Input_Str.isEmpty())
                {
                    Input_Num = Double.parseDouble(Input_Str);
                    Input_Str = "";

                    Result_Show(); 
                }
                Last_Sign = 0;
            }
        });
        button_14.setForeground(Color.BLACK);
        button_14.setFont(new Font("宋体", Font.PLAIN, 24));
        button_14.setBackground(Color.GREEN);
        button_14.setBounds(179, 256, 50, 72);
        contentPane.add(button_14);

        textField_Result = new JTextField();
        textField_Result.setHorizontalAlignment(SwingConstants.RIGHT);
        textField_Result.setFont(new Font("宋体", Font.PLAIN, 18));
        textField_Result.setBackground(Color.WHITE);
        textField_Result.setForeground(Color.BLACK);
        textField_Result.setEditable(false);
        textField_Result.setText("0");
        textField_Result.setBounds(10, 10, 219, 28);
        contentPane.add(textField_Result);
        textField_Result.setColumns(12);

        JButton button_15 = new JButton("");
        button_15.setEnabled(false);
        button_15.setForeground(Color.BLACK);
        button_15.setFont(new Font("宋体", Font.PLAIN, 24));
        button_15.setBackground(Color.WHITE);
        button_15.setBounds(5, 87, 228, 245);
        contentPane.add(button_15);

        JButton button_16 = new JButton("");
        button_16.setForeground(Color.BLACK);
        button_16.setFont(new Font("宋体", Font.PLAIN, 24));
        button_16.setEnabled(false);
        button_16.setBackground(Color.WHITE);
        button_16.setBounds(5, 5, 229, 38);
        contentPane.add(button_16);
    }


    public static void Result_Show()
    {
        if(Input_Num != 0)
        {
            if(Last_Sign == SIGN_ADD)
            {
                Last_Num += Input_Num;

            }

            else if(Last_Sign == SIGN_MIN)
            {
                Last_Num -= Input_Num;

            }

            else if(Last_Sign == SIGN_MULT)
            {
                Last_Num *= Input_Num;

            }

            else if(Last_Sign == SIGN_DIVI)
            {
                Last_Num /= Input_Num;

            }
            else Last_Num = Input_Num;

            Last_Sign = 0;
            Input_Num = 0;
            textField_Result.setText(String.valueOf(Last_Num));
        }
    }


}



