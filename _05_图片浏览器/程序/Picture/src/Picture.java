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
//		setMargin(new Insets(0,0,0,0));//将边框外的上下左右空间设置为0
//		setIconTextGap(0);//将标签中显示的文本和图标之间的间隔量设置为0
//		setBorderPainted(false);//不打印边框
//		setBorder(null);//除去边框
//		setText(null);//除去按钮的默认名称
//		setFocusPainted(false);//除去焦点的框
//		setContentAreaFilled(false);//除去默认的背景填充
//	}
//}


public class Picture extends JFrame implements ActionListener
{
    //菜单栏
    private JMenuBar menubar;
    private JMenu menu;
    private JMenuItem itemOpen, itemExit;
    //底部面板
    private JButton bPrev, bNext;
    private JButton bDel;
    private JButton bClock, bAnticlock;
    private JPanel bPanel;
    //图像部分
    private JLabel imgLabel;
    private ImageIcon image;
    //文件
    LinkedList<File> imgList = new LinkedList<File>(); //当前目录下的文件链表
    File curFile = null; //当前打开的图片文件
    static int index = 0; //当前图片文件序号



    //构造函数
    public Picture()
    {
        init(); //创建组件
        addComponent(); //添加组件
        addListener(); //监听事件
        showFrame(); //显示窗体
    }



    //创建组件
    public void init()
    {
        //菜单
        menubar = new JMenuBar(); //创建菜单栏
        menu = new JMenu("文件（File）"); //创建菜单
        menu.setMnemonic('F');
        itemOpen = new JMenuItem("打开（O）"); //创建菜单项
        itemOpen.setAccelerator(KeyStroke.getKeyStroke("control O"));
        itemExit = new JMenuItem("退出（E）");//底部面板 
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
        
        //图像部分
        imgLabel = new JLabel();
        image = new ImageIcon();

    }

    //添加组件
    public void addComponent()
    {
        //添加菜单

        setJMenuBar(menubar); //添加菜单栏至窗体
        menubar.add(menu); //添加菜单至菜单栏
        menu.add(itemOpen); //添加菜单项至菜单
        menu.addSeparator();
        menu.add(itemExit);
        //添加按钮
        bPanel.add(bPrev);
        bPanel.add(bNext);
        bPanel.add(bDel);
        bPanel.add(bClock);
        bPanel.add(bAnticlock);
        add(bPanel, BorderLayout.SOUTH);
        buttonEanbled(false); //调用buttonEnabled 方法，不激活按钮
        //添加图片标签
        add(imgLabel, BorderLayout.CENTER);
        imgLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imgLabel.setIcon(image);
        
    }


    //添加事件监听
    public void actionPerformed(ActionEvent e)
    {
        try
        {
            Object source = e.getSource();
            if(source == itemExit) //退出菜单事件
                System.exit(1);
            if(source == itemOpen) //打开菜单事件
                openFile();
            if(source == bNext) //下翻页按钮事件
                toNext();
            if(source == bPrev) //上翻页按钮事件
                toPrev1();
            if(source == bDel) //删除按钮事件
                imgDel1();
            if(source == bClock) //顺时针旋转按钮事件
                rotate(Math.PI / 2); // PI/2，即90 度
            if(source == bAnticlock) //逆时针旋转按钮事件
                rotate(-Math.PI / 2); // -PI/2，即-90 度
        }
        catch (Exception e1)
        {
            e1.printStackTrace();
        }
    }


    public void addListener()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //窗口关闭操作
        itemExit.addActionListener(this); //动作事件监听：退出菜单项
        itemOpen.addActionListener(this); //动作事件监听：打开文件项
        bPrev.addActionListener(this); //动作事件监听：
        bNext.addActionListener(this);
        bDel.addActionListener(this);
        bClock.addActionListener(this);
        bAnticlock.addActionListener(this);
        addComponentListener(new ComponentAdapter()
        {
            public void componentResized(ComponentEvent e)
            {
                //补充相应代码
            }
        });
    }


    //显示窗体
    public void showFrame()
    {
        setTitle("图片浏览器");
        setSize(800, 600);
        setLocation(0, 0);
        setVisible(true);
    }

    public void openFile()
    {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        //设置文件选择器当前目录
        chooser.setCurrentDirectory(new File("./pics"));
        FileFilter filter = new FileNameExtensionFilter(
            "图像文件 (jpg/gif/png)", "jpg", "gif", "png");
        chooser.setFileFilter(filter);
        int result = chooser.showOpenDialog(this); //打开文件选择对话框
        if (result == JFileChooser.APPROVE_OPTION)
        {
            curFile = chooser.getSelectedFile();
            image = new ImageIcon(curFile.getAbsolutePath());
            dispImage(image); //显示图片
            findImages(curFile); //查找当前目录下的所有图片
            buttonEanbled(true); //打开文件后，激活按钮
        }
    }


    public void toNext() throws IOException
    {
        index = (index + 1) % imgList.size(); //可以循环翻页
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
        int iw = b1.getWidth(); //原宽高
        int ih = b1.getHeight();
        int w = ih; //目标宽高
        int h = iw;
        int x = (w / 2) - (iw / 2); //目标原点
        int y = (h / 2) - (ih / 2);
        BufferedImage buffer1 = new BufferedImage(ih, iw, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = buffer1.createGraphics();
        g2.translate(x, y);
        g2.rotate(rad, iw / 2, ih / 2); //以(iw/2, ih/2)为支点旋转
        g2.drawImage(b1, null, null);
        image = new ImageIcon(buffer1);
        imgLabel.setIcon(image);
        dispImage(image);
        ImageIO.write(buffer1, "JPEG", curFile);
    }

    public void dispImage(ImageIcon image)
    {
        Dimension d = imageFitsize( //获得最佳比例图片显示尺寸（该方法稍后讲）
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
        //w1,h1: 图片宽高； 标签宽高：w2,h2
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

    //主方法
    public static void main(String[] args)
    {
        new Picture();


    }

}
