/*******************************(C) COPYRIGHT 2016 WindRises��л���죩*********************************/
/**============================================================================
* @File_Name   : Fram_Main.java
* @Description : None
* @Date        : 2016/9/20
* @By           : WindRises��л���죩
* @E_mail      : 1659567673@ qq.com
* @Platform    : Eclipse(JDK1.8)
* @Explain     : None
*=============================================================================*/
package Pack_Guess_Number;

/** ������ؿ� ---------------------------------------------------------------*/
import java.awt.event.*; 
import javax.swing.*; 
import java.awt.*;   

/** ���ʵ�� -----------------------------------------------------------------*/
public class Fram_Main extends JFrame implements ActionListener
{  
	private static final long serialVersionUID = 6736544563442661259L;
	private JButton  Button_Start; 
	private JTextField TextField_InputNum;  
	private JButton[] Button_Player = new JButton[7];
	private JButton Button_Result;
	
	public static String TextStrInput = "";
	public static String TextStrGuess = "";  
	private static int NumGuess = 0;
	private static int NumInput = 0;
	
	public Fram_Main()
	{
		/* ������� -------------------------*/
		setLayout(null);//�������з�ʽ
		setTitle("������С��Ϸ");
		setSize(500,600);
		setVisible(true); //���ӻ�
		setResizable(false);//��ֹ����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );//���ùرշ�ʽ
		setLocationRelativeTo(null);//���ÿ���ʱλ��Ϊ�м�
		
		
		/* ��ť��� ------------------------*/
		Button_Start = new JButton("��ʼ"); 
		Button_Start.setBounds(300,500,100,40); 
		Button_Start.setFont(new Font("����", Font.BOLD, 24));  //���õ�ǰ���塣  
		Button_Start.setEnabled(true);  //��ťʹ�� 
		Button_Start.addActionListener(this); //�����ð�ť�ļ��
		add(Button_Start);//�������
		
		Button_Player[1] = new JButton("���1"); 
		Button_Player[1].setBounds(100,100,100,40); 
		Button_Player[1].setFont(new Font("����", Font.BOLD, 24));  //���õ�ǰ���塣  
		Button_Player[1].setEnabled(false);  //��ťʹ��  
		add(Button_Player[1]);//�������
		Button_Player[1].setBackground(Color.GRAY);//������ɫ
		
		Button_Player[2] = new JButton("���2"); 
		Button_Player[2].setBounds(100,150,100,40); 
		Button_Player[2].setFont(new Font("����", Font.BOLD, 24));  //���õ�ǰ���塣  
		Button_Player[2].setEnabled(false);  //��ťʹ��  
		add(Button_Player[2]);//�������
		Button_Player[2].setBackground(Color.GRAY);//������ɫ
		
		Button_Player[3] = new JButton("���3"); 
		Button_Player[3].setBounds(100,200,100,40); 
		Button_Player[3].setFont(new Font("����", Font.BOLD, 24));  //���õ�ǰ���塣  
		Button_Player[3].setEnabled(false);  //��ťʹ��  
		add(Button_Player[3]);//�������
		Button_Player[3].setBackground(Color.GRAY);//������ɫ
		
		Button_Player[4] = new JButton("���4"); 
		Button_Player[4].setBounds(100,250,100,40); 
		Button_Player[4].setFont(new Font("����", Font.BOLD, 24));  //���õ�ǰ���塣  
		Button_Player[4].setEnabled(false);  //��ťʹ��  
		add(Button_Player[4]);//�������
		Button_Player[4].setBackground(Color.GRAY);//������ɫ
		
		Button_Player[5] = new JButton("���5"); 
		Button_Player[5].setBounds(100,300,100,40); 
		Button_Player[5].setFont(new Font("����", Font.BOLD, 24));  //���õ�ǰ���塣  
		Button_Player[5].setEnabled(false);  //��ťʹ��  
		add(Button_Player[5]);//�������
		Button_Player[5].setBackground(Color.GRAY);//������ɫ
		
		Button_Player[6] = new JButton("���6"); 
		Button_Player[6].setBounds(100,350,100,40); 
		Button_Player[6].setFont(new Font("����", Font.BOLD, 24));  //���õ�ǰ���塣  
		Button_Player[6].setEnabled(false);  //��ťʹ��  
		add(Button_Player[6]);//�������
		Button_Player[6].setBackground(Color.GRAY);//������ɫ 
		
		Button_Result = new JButton("�²���"); 
		Button_Result.setBounds(220,100,220,290); 
		Button_Result.setFont(new Font("����", Font.BOLD, 32));  //���õ�ǰ���塣  
		Button_Result.setEnabled(false);  //��ťʹ��  
		add(Button_Result);//�������
		Button_Result.setBackground(Color.CYAN);//������ɫ 
		
		/* �������� ------------------------*/ 
		TextField_InputNum = new JTextField(10);
		TextField_InputNum.setEditable(true);
		TextField_InputNum.setEnabled(true);
		TextField_InputNum.setFont(new Font("����", Font.TRUETYPE_FONT, 16));  //���õ�ǰ���塣  
		TextField_InputNum.setBounds(100,500,190,40);   
		add(TextField_InputNum); 
		TextField_InputNum.setText("  �����յ���ֵ��0~99��"); 
		
		setVisible(true); //���ӻ�������ˢ��UI��
	}
	  
	/**----------------------------------------------------------------------------
	* @FunctionName  : InputNum_Set()     
	* @Description   : �������״̬ 
	* @Data          : 2016/9/20
	* @Explain       
	-------------------------------------------------------------------------------
	* _player	��������
	* _num		��������ֵ
	* _color	: ���״̬����ɫ
	------------------------------------------------------------------------------*/ 
	public void Player_Set(int _player,int _num,Color _color)
	{ 
		TextStrGuess = Integer.toString(_num);//ת�����ַ�����ʾ
		Button_Result.setText(TextStrGuess);  //��ʾ���ڲµ���ֵ
		
		for(int i = 1;i<=6;i++)
		{ 
			if(i == _player) 
				Button_Player[i].setBackground(_color);//������ɫ
			else 
				Button_Player[i].setBackground(Color.GRAY);//������ɫ 
		}
	}
	 


	/**----------------------------------------------------------------------------
	* @FunctionName  : actionPerformed()     
	* @Description   : �����Ӧ���� 
	* @Data          : 2016/9/20
	* @Explain       
	-------------------------------------------------------------------------------
	*None
	------------------------------------------------------------------------------*/
	public void actionPerformed(ActionEvent e)
	{
		int cnt = 0;   
		int[] num = new int[100]; 
		char flag = 0;
		
		if(e.getSource() == Button_Start) 
		{   
			
			TextStrInput = TextField_InputNum.getText();  
			NumGuess = (int)(Math.random() * System.currentTimeMillis())%100;
			 
			Player_Set(1,NumGuess,Color.RED);//�������״̬
			try
			{
				NumInput = Integer.parseInt(TextStrInput); //ת������ֵ
				if(NumInput >99) 
				{
					Button_Result.setText( "��������"); 
					return;
				}
			} 
			catch(Exception error)
			{
				error.printStackTrace(); 
				Button_Result.setText( "��������"); 
				return;
			}
			
			while(NumGuess != NumInput)
			{   
				cnt ++;
				cnt %= 6; 
				
				Player_Set(cnt+1,NumGuess,Color.RED);//�������״̬
				
				flag = 0;
				while(flag == 0)//�ظ��˼�����
				{
					NumGuess = (int)(Math.random() * System.currentTimeMillis())%100;  
					 
					if(num[NumGuess] == 0) {num[NumGuess] = 1;flag = 1;}
						
				} 
			}
			Player_Set(cnt+1,NumGuess,Color.GREEN);//�������״̬
		}
		
			
			
			
			
	}
		
}	





/*******************************(C) COPYRIGHT 2016 WindRises��л���죩*********************************/


