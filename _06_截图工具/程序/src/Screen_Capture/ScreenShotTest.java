package Screen_Capture;

import java.awt.*;
import java.awt.event.*;
import javax.swing.ImageIcon;

public class ScreenShotTest
{
    //菜单项
    MenuItem capture;
    MenuItem exit;


    public static void main(String[] args)
    {
        new ScreenShotTest();
    }

    
    
    public ScreenShotTest()
    {
        setTray();
        addListener();
    }

    public void setTray()
    {
        if (SystemTray.isSupported())   // 判断是否支持系统托盘
        {
            // 创建托盘图标
            ImageIcon icon = new ImageIcon("6.jpg"); //图标（16×16 的图片）
            //ImageIcon icon = new ImageIcon(getClass().getResource("/capture.jpg"));
            Image image = icon.getImage(); // 获得 Image 对象
            TrayIcon trayIcon = new TrayIcon(image); //托盘图标
            // 创建弹出菜单
            PopupMenu popupMenu = new PopupMenu();
            	
            capture = new MenuItem("截图"); //创建菜单项
            exit = new MenuItem("退出");
            popupMenu.add(capture); // 为弹出菜单添加菜单项
            popupMenu.add(exit); // 为弹出菜单添加菜单项
            trayIcon.setPopupMenu(popupMenu); // 为托盘图标加弹出菜弹
            trayIcon.setToolTip("屏幕截图工具"); // 添加工具提示文本
            //显示托盘
            SystemTray systemTray = SystemTray.getSystemTray(); // 获得系统托盘对象
            try
            {
                systemTray.add(trayIcon); // 将图标添加到系统托盘
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public void addListener()
    {
        //退出菜单项事件监听
        exit.addActionListener(new ActionListener()   //为 exit 添加动作监听器对象
        {
            @Override
            public void actionPerformed(ActionEvent e)    //重写动作事件处理方法
            {
                System.exit(0); //退出系统
            }
        });
        //截图菜单项事件监听
        capture.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try
                {    
                	//new ScreenGrab(); 
                	  
                    new ImageCapture();  //创建 ImageCapture 对象
                }
                catch (Exception e1)
                {
                    e1.printStackTrace();
                }
            }
        });
    }

}






