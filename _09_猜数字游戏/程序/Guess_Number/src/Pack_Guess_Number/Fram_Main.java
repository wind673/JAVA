/*******************************(C) COPYRIGHT 2016 WindRises（谢玉伸）*********************************/
/**============================================================================
* @File_Name   : Fram_Main.java
* @Description : None
* @Date        : 2016/9/20
* @By           : WindRises（谢玉伸）
* @E_mail      : 1659567673@ qq.com
* @Platform    : Eclipse(JDK1.8)
* @Explain     : None
*=============================================================================*/
package Pack_Guess_Number;

/** 声明相关库 ---------------------------------------------------------------*/
import java.awt.event.*; 
import javax.swing.*; 
import java.awt.*;   

/** 类的实现 -----------------------------------------------------------------*/
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
		/* 窗口组件 -------------------------*/
		setLayout(null);//设置排列方式
		setTitle("猜数字小游戏");
		setSize(500,600);
		setVisible(true); //可视化
		setResizable(false);//禁止伸缩
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );//设置关闭方式
		setLocationRelativeTo(null);//设置开启时位置为中间
		
		
		/* 按钮组件 ------------------------*/
		Button_Start = new JButton("开始"); 
		Button_Start.setBounds(300,500,100,40); 
		Button_Start.setFont(new Font("宋体", Font.BOLD, 24));  //设置当前字体。  
		Button_Start.setEnabled(true);  //按钮使能 
		Button_Start.addActionListener(this); //开启该按钮的检测
		add(Button_Start);//加入组件
		
		Button_Player[1] = new JButton("玩家1"); 
		Button_Player[1].setBounds(100,100,100,40); 
		Button_Player[1].setFont(new Font("宋体", Font.BOLD, 24));  //设置当前字体。  
		Button_Player[1].setEnabled(false);  //按钮使能  
		add(Button_Player[1]);//加入组件
		Button_Player[1].setBackground(Color.GRAY);//设置颜色
		
		Button_Player[2] = new JButton("玩家2"); 
		Button_Player[2].setBounds(100,150,100,40); 
		Button_Player[2].setFont(new Font("宋体", Font.BOLD, 24));  //设置当前字体。  
		Button_Player[2].setEnabled(false);  //按钮使能  
		add(Button_Player[2]);//加入组件
		Button_Player[2].setBackground(Color.GRAY);//设置颜色
		
		Button_Player[3] = new JButton("玩家3"); 
		Button_Player[3].setBounds(100,200,100,40); 
		Button_Player[3].setFont(new Font("宋体", Font.BOLD, 24));  //设置当前字体。  
		Button_Player[3].setEnabled(false);  //按钮使能  
		add(Button_Player[3]);//加入组件
		Button_Player[3].setBackground(Color.GRAY);//设置颜色
		
		Button_Player[4] = new JButton("玩家4"); 
		Button_Player[4].setBounds(100,250,100,40); 
		Button_Player[4].setFont(new Font("宋体", Font.BOLD, 24));  //设置当前字体。  
		Button_Player[4].setEnabled(false);  //按钮使能  
		add(Button_Player[4]);//加入组件
		Button_Player[4].setBackground(Color.GRAY);//设置颜色
		
		Button_Player[5] = new JButton("玩家5"); 
		Button_Player[5].setBounds(100,300,100,40); 
		Button_Player[5].setFont(new Font("宋体", Font.BOLD, 24));  //设置当前字体。  
		Button_Player[5].setEnabled(false);  //按钮使能  
		add(Button_Player[5]);//加入组件
		Button_Player[5].setBackground(Color.GRAY);//设置颜色
		
		Button_Player[6] = new JButton("玩家6"); 
		Button_Player[6].setBounds(100,350,100,40); 
		Button_Player[6].setFont(new Font("宋体", Font.BOLD, 24));  //设置当前字体。  
		Button_Player[6].setEnabled(false);  //按钮使能  
		add(Button_Player[6]);//加入组件
		Button_Player[6].setBackground(Color.GRAY);//设置颜色 
		
		Button_Result = new JButton("猜测结果"); 
		Button_Result.setBounds(220,100,220,290); 
		Button_Result.setFont(new Font("宋体", Font.BOLD, 32));  //设置当前字体。  
		Button_Result.setEnabled(false);  //按钮使能  
		add(Button_Result);//加入组件
		Button_Result.setBackground(Color.CYAN);//设置颜色 
		
		/* 输入框组件 ------------------------*/ 
		TextField_InputNum = new JTextField(10);
		TextField_InputNum.setEditable(true);
		TextField_InputNum.setEnabled(true);
		TextField_InputNum.setFont(new Font("宋体", Font.TRUETYPE_FONT, 16));  //设置当前字体。  
		TextField_InputNum.setBounds(100,500,190,40);   
		add(TextField_InputNum); 
		TextField_InputNum.setText("  设置谜底数值（0~99）"); 
		
		setVisible(true); //可视化（用来刷新UI）
	}
	  
	/**----------------------------------------------------------------------------
	* @FunctionName  : InputNum_Set()     
	* @Description   : 设置玩家状态 
	* @Data          : 2016/9/20
	* @Explain       
	-------------------------------------------------------------------------------
	* _player	：玩家序号
	* _num		：所猜数值
	* _color	: 玩家状态的颜色
	------------------------------------------------------------------------------*/ 
	public void Player_Set(int _player,int _num,Color _color)
	{ 
		TextStrGuess = Integer.toString(_num);//转换成字符串显示
		Button_Result.setText(TextStrGuess);  //显示现在猜的数值
		
		for(int i = 1;i<=6;i++)
		{ 
			if(i == _player) 
				Button_Player[i].setBackground(_color);//设置颜色
			else 
				Button_Player[i].setBackground(Color.GRAY);//设置颜色 
		}
	}
	 


	/**----------------------------------------------------------------------------
	* @FunctionName  : actionPerformed()     
	* @Description   : 检测响应函数 
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
			 
			Player_Set(1,NumGuess,Color.RED);//设置玩家状态
			try
			{
				NumInput = Integer.parseInt(TextStrInput); //转换成数值
				if(NumInput >99) 
				{
					Button_Result.setText( "输入有误"); 
					return;
				}
			} 
			catch(Exception error)
			{
				error.printStackTrace(); 
				Button_Result.setText( "输入有误"); 
				return;
			}
			
			while(NumGuess != NumInput)
			{   
				cnt ++;
				cnt %= 6; 
				
				Player_Set(cnt+1,NumGuess,Color.RED);//设置玩家状态
				
				flag = 0;
				while(flag == 0)//重复了继续找
				{
					NumGuess = (int)(Math.random() * System.currentTimeMillis())%100;  
					 
					if(num[NumGuess] == 0) {num[NumGuess] = 1;flag = 1;}
						
				} 
			}
			Player_Set(cnt+1,NumGuess,Color.GREEN);//设置玩家状态
		}
		
			
			
			
			
	}
		
}	





/*******************************(C) COPYRIGHT 2016 WindRises（谢玉伸）*********************************/


