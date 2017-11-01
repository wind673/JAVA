package Screen_Capture;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
@SuppressWarnings("serial")
public class ImageCapture extends JFrame
{
    //屏幕
    BufferedImage screenimage;
    Dimension screenSize;
    //
    JLabel label;
    JPanel toolPanel;
    JButton cancel ;
    JButton ok ;
    JButton save ;
    //
    int x1;
    int x2;
    int y1;
    int y2;
    //
    boolean isDrag = false;

    BufferedImage capturedimage;

    public ImageCapture()
    {
        captureScreen();
        initLayeredPane();
        addCaptureListener();
    }

    public void captureScreen()
    {
        //获取屏幕的尺寸
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle d = new Rectangle(0, 0, screenSize.width, screenSize.height);
        //截取整个屏幕的图像
        Robot robot;
        try
        {
            Thread.sleep(300); //等待某些提示信息消失
            robot = new Robot();
            screenimage = robot.createScreenCapture(d);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void initLayeredPane()
    {
        //层次面板，第 0 层为屏幕图像
        JLayeredPane layeredPane = new JLayeredPane();
        label = new JLabel();
        ImageIcon icon = new ImageIcon(screenimage);
        label.setBounds(0, 0, screenSize.width, screenSize.height);
        label.setIcon(icon); //屏幕图像放入标签中
        layeredPane.add(label, new Integer(0)); //标签放入第 0 层
        setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR)); //采用十字光标
        //第 1 层为工具按钮
        toolPanel = new JPanel();
        toolPanel.setLayout(new GridLayout(1, 3));
        ok = new JButton("确定");
        cancel = new JButton("取消");
        save = new JButton("保存");
        toolPanel.add(ok);
        toolPanel.add(save);  
        toolPanel.add(cancel); 
        toolPanel.setVisible(false);  //在区域截取完成之前暂不显示
        layeredPane.add(toolPanel, new Integer(1)); //工具面板放入第 1 层
        add(layeredPane);
        //显示窗体
        setUndecorated(true); //去掉窗口的无边框标题栏（无装饰）
        setAlwaysOnTop(true); //设置该窗体在其他窗体前面显示
        setSize(screenimage.getWidth(), screenimage.getHeight());
        setVisible(true);
    }
    void addCaptureListener()
    {
        //在事件处理方法中调用 saveImage()和 setImageClipboard()
        label.addMouseListener(new MouseAdapter()
        {
            //按下鼠标右键
            public void mouseClicked(MouseEvent e)
            {
                if (e.getButton() == MouseEvent.BUTTON3)
                {
                    dispose(); //释放资源
                }
            }
            //按下鼠标键
            public void mousePressed(MouseEvent e)
            {
                x1 = e.getX(); //获取选框起点坐标
                y1 = e.getY();
            }
            //释放鼠标键
            public void mouseReleased(MouseEvent e)
            {
                if (isDrag)
                {
                    isDrag = false;
                    x2 = e.getX(); //获取选框终点坐标
                    y2 = e.getY();
                    toolPanel.setBounds(x2,y2, 200, 30);
                    toolPanel.setVisible(true); //选区确定后，显示工具条 toolPanel
                }
            }
        });
        //添加鼠标移动监听器
        label.addMouseMotionListener(new MouseMotionAdapter()
        {
            public void mouseDragged(MouseEvent e)
            {
                if(!isDrag)
                {
                    isDrag = true;
                }
                //拖动过程中选区边框的显示，在屏幕图像的基础上加入选区边框
                capturedimage = new BufferedImage(
                    screenimage.getWidth(), screenimage.getHeight(), screenimage.getType());
                //使用 java 绘图类 Graphics
                Graphics g = capturedimage.getGraphics(); 
                g.drawImage(screenimage,
                            0, 0, screenimage.getWidth(), screenimage.getHeight(), null); 
                
                g.setColor(new Color(0,0,0,50)); 
                g.fillRect(0, 0, screenimage.getWidth(), screenimage.getHeight());
                
                int x = e.getX(); //获取当前坐标
                int y = e.getY();
                //用 drawRect()方法绘制带选区边框的屏幕图像（）
                //使用 Math 类的 min 和 abs 方法计算选区左上 角坐标和选区宽高   
                g.setColor(new Color(255,255,255,50));  
                g.fillRect(Math.min(x1, x), Math.min(y1, y), Math.abs(x - x1), Math.abs(y - y1));
                g.setColor(Color.BLUE); 
                g.drawRect(Math.min(x1, x), Math.min(y1, y), Math.abs(x - x1), Math.abs(y - y1));
                label.setIcon(new ImageIcon(capturedimage));
            }
        });
        // 按钮事件
        cancel.addActionListener(new ActionListener()  //取消按钮
        {
            public void actionPerformed(ActionEvent e)
            {
            	 dispose();
            }
            
        });
        //确定按钮
        ok.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                 
                if (capturedimage != null)
                {
                    //从屏幕图像中获取选区子图像
                    BufferedImage subimg = screenimage.getSubimage(
                                               Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2 - x1), Math.abs(y2 - y1));
                    setImageClipboard(subimg); //调用写剪贴板方法
                }
                dispose();
            }
        });
        //保存按钮
        save.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            { 
                if (capturedimage != null)
                {
                    BufferedImage subimg = screenimage.getSubimage(
                                               Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2 - x1), Math.abs(y2 - y1));
                    saveImage(subimg);  //调用保存文件方法
                }
                dispose();
            }
        });
    }

    public void saveImage(BufferedImage image)
    {
        JFileChooser jf = new JFileChooser(".");
        jf.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int value = jf.showSaveDialog(this); //保存对话框
        if(value == JFileChooser.APPROVE_OPTION)
        {
            File file = jf.getSelectedFile();
            String ext = file.toString().toLowerCase();
            if(!ext.endsWith(".bmp"))
            {
                String ns = ext + ".bmp";
                file = new File(ns);
            }
            try
            {
                ImageIO.write(image, "BMP", file); //将截图 image 写入图像文件
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    public void setImageClipboard(final Image image)
    {
        Transferable trans = new Transferable()  //创建支持图像数据传递的Transferable对象
        {
            //实现 Transferable 接口中的三个方法
            public DataFlavor[] getTransferDataFlavors()
            {
                return new DataFlavor[] { DataFlavor.imageFlavor };
            }
            public boolean isDataFlavorSupported(DataFlavor flavor)
            {
                return DataFlavor.imageFlavor.equals(flavor);
            }
            public Object getTransferData(DataFlavor flavor)
            throws UnsupportedFlavorException, IOException
            {
                if(isDataFlavorSupported(flavor))
                    return image;
                throw new UnsupportedFlavorException(flavor);
            }
        };
        Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard(); //获取系统剪贴板
        cb.setContents(trans, null); //设置剪贴板当前内容为 trans 对象
    }
}
