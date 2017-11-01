package Screen_Capture;

import java.awt.*;
import java.awt.event.*;
import javax.swing.ImageIcon;

public class ScreenShotTest
{
    //�˵���
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
        if (SystemTray.isSupported())   // �ж��Ƿ�֧��ϵͳ����
        {
            // ��������ͼ��
            ImageIcon icon = new ImageIcon("6.jpg"); //ͼ�꣨16��16 ��ͼƬ��
            //ImageIcon icon = new ImageIcon(getClass().getResource("/capture.jpg"));
            Image image = icon.getImage(); // ��� Image ����
            TrayIcon trayIcon = new TrayIcon(image); //����ͼ��
            // ���������˵�
            PopupMenu popupMenu = new PopupMenu();
            	
            capture = new MenuItem("��ͼ"); //�����˵���
            exit = new MenuItem("�˳�");
            popupMenu.add(capture); // Ϊ�����˵���Ӳ˵���
            popupMenu.add(exit); // Ϊ�����˵���Ӳ˵���
            trayIcon.setPopupMenu(popupMenu); // Ϊ����ͼ��ӵ����˵�
            trayIcon.setToolTip("��Ļ��ͼ����"); // ��ӹ�����ʾ�ı�
            //��ʾ����
            SystemTray systemTray = SystemTray.getSystemTray(); // ���ϵͳ���̶���
            try
            {
                systemTray.add(trayIcon); // ��ͼ����ӵ�ϵͳ����
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public void addListener()
    {
        //�˳��˵����¼�����
        exit.addActionListener(new ActionListener()   //Ϊ exit ��Ӷ�������������
        {
            @Override
            public void actionPerformed(ActionEvent e)    //��д�����¼�������
            {
                System.exit(0); //�˳�ϵͳ
            }
        });
        //��ͼ�˵����¼�����
        capture.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try
                {    
                	//new ScreenGrab(); 
                	  
                    new ImageCapture();  //���� ImageCapture ����
                }
                catch (Exception e1)
                {
                    e1.printStackTrace();
                }
            }
        });
    }

}






