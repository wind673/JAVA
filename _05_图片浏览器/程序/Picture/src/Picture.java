import java.awt.*;

import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import javax.swing.filechooser.FileFilter;

import java.io.*;
import javax.imageio.*;
import java.util.*;

import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
 
//public class ImageButton extends JButton {
// 
//	public ImageButton(ImageIcon icon){
//		setSize(icon.getImage().getWidth(null),
//				icon.getImage().getHeight(null));
//		setIcon(icon);
//		setMargin(new Insets(0,0,0,0));//���߿�����������ҿռ�����Ϊ0
//		setIconTextGap(0);//����ǩ����ʾ���ı���ͼ��֮��ļ��������Ϊ0
//		setBorderPainted(false);//����ӡ�߿�
//		setBorder(null);//��ȥ�߿�
//		setText(null);//��ȥ��ť��Ĭ������
//		setFocusPainted(false);//��ȥ����Ŀ�
//		setContentAreaFilled(false);//��ȥĬ�ϵı������
//	}
//}


public class Picture extends JFrame implements ActionListener
{
    //�˵���
    private JMenuBar menubar;
    private JMenu menu;
    private JMenuItem itemOpen, itemExit;
    //�ײ����
    private JButton bPrev, bNext;
    private JButton bDel;
    private JButton bClock, bAnticlock;
    private JPanel bPanel;
    //ͼ�񲿷�
    private JLabel imgLabel;
    private ImageIcon image;
    //�ļ�
    LinkedList<File> imgList = new LinkedList<File>(); //��ǰĿ¼�µ��ļ�����
    File curFile = null; //��ǰ�򿪵�ͼƬ�ļ�
    static int index = 0; //��ǰͼƬ�ļ����



    //���캯��
    public Picture()
    {
        init(); //�������
        addComponent(); //������
        addListener(); //�����¼�
        showFrame(); //��ʾ����
    }



    //�������
    public void init()
    {
        //�˵�
        menubar = new JMenuBar(); //�����˵���
        menu = new JMenu("�ļ���File��"); //�����˵�
        menu.setMnemonic('F');
        itemOpen = new JMenuItem("�򿪣�O��"); //�����˵���
        itemOpen.setAccelerator(KeyStroke.getKeyStroke("control O"));
        itemExit = new JMenuItem("�˳���E��");//�ײ���� 
        bPanel = new JPanel(); 
        
        ImageIcon image =  new ImageIcon("D:\\_image\\3.jpg");
        bPrev = new JButton(image); 
        bPrev.setPreferredSize(new Dimension(image.getIconWidth(), image.getIconHeight())); 
        
        image =  new ImageIcon("D:\\_image\\4.jpg");
        bNext = new JButton(image);  
        bNext.setPreferredSize(new Dimension(image.getIconWidth(), image.getIconHeight())); 
         
        image =  new ImageIcon("D:\\_image\\5.jpg");
        bDel = new JButton(image);  
        bDel.setPreferredSize(new Dimension(image.getIconWidth(), image.getIconHeight())); 
 
        image =  new ImageIcon("D:\\_image\\1.jpg");
        bClock = new JButton(image);  
        bClock.setPreferredSize(new Dimension(image.getIconWidth(), image.getIconHeight())); 
         
        image =  new ImageIcon("D:\\_image\\2.jpg");
        bAnticlock = new JButton(image);  
        bAnticlock.setPreferredSize(new Dimension(image.getIconWidth(), image.getIconHeight())); 
        
        //ͼ�񲿷�
        imgLabel = new JLabel();
        image = new ImageIcon();

    }

    //������
    public void addComponent()
    {
        //��Ӳ˵�

        setJMenuBar(menubar); //��Ӳ˵���������
        menubar.add(menu); //��Ӳ˵����˵���
        menu.add(itemOpen); //��Ӳ˵������˵�
        menu.addSeparator();
        menu.add(itemExit);
        //��Ӱ�ť
        bPanel.add(bPrev);
        bPanel.add(bNext);
        bPanel.add(bDel);
        bPanel.add(bClock);
        bPanel.add(bAnticlock);
        add(bPanel, BorderLayout.SOUTH);
        buttonEanbled(false); //����buttonEnabled �����������ť
        //���ͼƬ��ǩ
        add(imgLabel, BorderLayout.CENTER);
        imgLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imgLabel.setIcon(image);
        
    }


    //����¼�����
    public void actionPerformed(ActionEvent e)
    {
        try
        {
            Object source = e.getSource();
            if(source == itemExit) //�˳��˵��¼�
                System.exit(1);
            if(source == itemOpen) //�򿪲˵��¼�
                openFile();
            if(source == bNext) //�·�ҳ��ť�¼�
                toNext();
            if(source == bPrev) //�Ϸ�ҳ��ť�¼�
                toPrev1();
            if(source == bDel) //ɾ����ť�¼�
                imgDel1();
            if(source == bClock) //˳ʱ����ת��ť�¼�
                rotate(Math.PI / 2); // PI/2����90 ��
            if(source == bAnticlock) //��ʱ����ת��ť�¼�
                rotate(-Math.PI / 2); // -PI/2����-90 ��
        }
        catch (Exception e1)
        {
            e1.printStackTrace();
        }
    }


    public void addListener()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //���ڹرղ���
        itemExit.addActionListener(this); //�����¼��������˳��˵���
        itemOpen.addActionListener(this); //�����¼����������ļ���
        bPrev.addActionListener(this); //�����¼�������
        bNext.addActionListener(this);
        bDel.addActionListener(this);
        bClock.addActionListener(this);
        bAnticlock.addActionListener(this);
        addComponentListener(new ComponentAdapter()
        {
            public void componentResized(ComponentEvent e)
            {
                //������Ӧ����
            }
        });
    }


    //��ʾ����
    public void showFrame()
    {
        setTitle("ͼƬ�����");
        setSize(800, 600);
        setLocation(0, 0);
        setVisible(true);
    }

    public void openFile()
    {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        //�����ļ�ѡ������ǰĿ¼
        chooser.setCurrentDirectory(new File("./pics"));
        FileFilter filter = new FileNameExtensionFilter(
            "ͼ���ļ� (jpg/gif/png)", "jpg", "gif", "png");
        chooser.setFileFilter(filter);
        int result = chooser.showOpenDialog(this); //���ļ�ѡ��Ի���
        if (result == JFileChooser.APPROVE_OPTION)
        {
            curFile = chooser.getSelectedFile();
            image = new ImageIcon(curFile.getAbsolutePath());
            dispImage(image); //��ʾͼƬ
            findImages(curFile); //���ҵ�ǰĿ¼�µ�����ͼƬ
            buttonEanbled(true); //���ļ��󣬼��ť
        }
    }


    public void toNext() throws IOException
    {
        index = (index + 1) % imgList.size(); //����ѭ����ҳ
        curFile = imgList.get(index);
        BufferedImage b1 = ImageIO.read(curFile);
        image = new ImageIcon(b1);
        dispImage(image);
    }

    public void toPrev1() throws Exception
    {
        index = (index + imgList.size() - 1) % imgList.size();
        curFile = imgList.get(index);
        BufferedImage b1 = ImageIO.read(curFile);
        image = new ImageIcon(b1);
        dispImage(image);
    }

    public void imgDel1()
    {
        if(imgList.size() >= 1)
        {
            curFile = imgList.get(index);
            imgList.remove(index);
            curFile.delete();
            if(imgList.size() == 0)
            {
                imgLabel.setIcon(new ImageIcon());
                curFile = null;
                buttonEanbled(false);
            }
            else
            {
                if(imgList.size() == index)
                    index--;
                curFile = imgList.get(index);
                image = new ImageIcon(curFile.getAbsolutePath());
                dispImage(image);
            }
        }
    }
    public void toPrev() throws Exception
    {
        index = (index + imgList.size() - 1) % imgList.size();
        curFile = imgList.get(index);
        BufferedImage b1 = ImageIO.read(curFile);
        image = new ImageIcon(b1);
        dispImage(image);
    }

    public void imgDel()
    {
        if(imgList.size() >= 1)
        {
            curFile = imgList.get(index);
            imgList.remove(index);
            curFile.delete();
            if(imgList.size() == 0)
            {
                imgLabel.setIcon(new ImageIcon());
                curFile = null;
                buttonEanbled(false);
            }
            else
            {
                if(imgList.size() == index)
                    index--;
                curFile = imgList.get(index);
                image = new ImageIcon(curFile.getAbsolutePath());
                dispImage(image);
            }
        }
    }

    public void rotate(double rad) throws IOException
    {
        BufferedImage b1 = ImageIO.read(curFile);
        int iw = b1.getWidth(); //ԭ���
        int ih = b1.getHeight();
        int w = ih; //Ŀ����
        int h = iw;
        int x = (w / 2) - (iw / 2); //Ŀ��ԭ��
        int y = (h / 2) - (ih / 2);
        BufferedImage buffer1 = new BufferedImage(ih, iw, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = buffer1.createGraphics();
        g2.translate(x, y);
        g2.rotate(rad, iw / 2, ih / 2); //��(iw/2, ih/2)Ϊ֧����ת
        g2.drawImage(b1, null, null);
        image = new ImageIcon(buffer1);
        imgLabel.setIcon(image);
        dispImage(image);
        ImageIO.write(buffer1, "JPEG", curFile);
    }

    public void dispImage(ImageIcon image)
    {
        Dimension d = imageFitsize( //�����ѱ���ͼƬ��ʾ�ߴ磨�÷����Ժ󽲣�
                          image.getIconWidth(), image.getIconHeight(), imgLabel.getWidth(), imgLabel.getHeight());
        BufferedImage bfimage = new BufferedImage(
            d.width, d.height, BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D g2 = (Graphics2D) bfimage.getGraphics();
        g2.drawImage(image.getImage(), 0, 0, d.width, d.height, null);
        image = new ImageIcon(bfimage);
        imgLabel.setIcon(image);
    }

    public void findImages(File file)
    {
        File[] files;
        File dir = file.getParentFile();
        files = dir.listFiles();
        int i = 0;
        for(File f : files) 
        {
            if(f.getName().endsWith(".jpg") || f.getName().endsWith(".gif")
                    || f.getName().endsWith(".png"))
            {
                if (f.equals(file))
                    index = i;
                imgList.add(f);
                i++;
            }
        }
    }

    public Dimension imageFitsize(int w1, int h1, int w2, int h2)
    {
        //w1,h1: ͼƬ��ߣ� ��ǩ��ߣ�w2,h2
        Dimension d = new Dimension(w1, h1);
        double rw, rh;
        rw = (double)w1 / w2;
        rh = (double)h1 / h2;
        if (rw > 1 && rw > rh)
        {
            d.width = w2;
            d.height = (int)(h1 / rw);
        }
        else if(rh > 1 && rh > rw)
        {
            d.height = h2;
            d.width = (int)(w1 / rh);
        }
        return d;
    }
    
    public void buttonEanbled(boolean flag)
    {
        bPrev.setEnabled(flag);
        bNext.setEnabled(flag);
        bDel.setEnabled(flag);;
        bClock.setEnabled(flag);
        bAnticlock.setEnabled(flag);
    }

    //������
    public static void main(String[] args)
    {
        new Picture();


    }

}
