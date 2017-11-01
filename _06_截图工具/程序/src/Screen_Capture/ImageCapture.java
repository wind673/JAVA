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
    //��Ļ
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
        //��ȡ��Ļ�ĳߴ�
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle d = new Rectangle(0, 0, screenSize.width, screenSize.height);
        //��ȡ������Ļ��ͼ��
        Robot robot;
        try
        {
            Thread.sleep(300); //�ȴ�ĳЩ��ʾ��Ϣ��ʧ
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
        //�����壬�� 0 ��Ϊ��Ļͼ��
        JLayeredPane layeredPane = new JLayeredPane();
        label = new JLabel();
        ImageIcon icon = new ImageIcon(screenimage);
        label.setBounds(0, 0, screenSize.width, screenSize.height);
        label.setIcon(icon); //��Ļͼ������ǩ��
        layeredPane.add(label, new Integer(0)); //��ǩ����� 0 ��
        setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR)); //����ʮ�ֹ��
        //�� 1 ��Ϊ���߰�ť
        toolPanel = new JPanel();
        toolPanel.setLayout(new GridLayout(1, 3));
        ok = new JButton("ȷ��");
        cancel = new JButton("ȡ��");
        save = new JButton("����");
        toolPanel.add(ok);
        toolPanel.add(save);  
        toolPanel.add(cancel); 
        toolPanel.setVisible(false);  //�������ȡ���֮ǰ�ݲ���ʾ
        layeredPane.add(toolPanel, new Integer(1)); //����������� 1 ��
        add(layeredPane);
        //��ʾ����
        setUndecorated(true); //ȥ�����ڵ��ޱ߿����������װ�Σ�
        setAlwaysOnTop(true); //���øô�������������ǰ����ʾ
        setSize(screenimage.getWidth(), screenimage.getHeight());
        setVisible(true);
    }
    void addCaptureListener()
    {
        //���¼��������е��� saveImage()�� setImageClipboard()
        label.addMouseListener(new MouseAdapter()
        {
            //��������Ҽ�
            public void mouseClicked(MouseEvent e)
            {
                if (e.getButton() == MouseEvent.BUTTON3)
                {
                    dispose(); //�ͷ���Դ
                }
            }
            //��������
            public void mousePressed(MouseEvent e)
            {
                x1 = e.getX(); //��ȡѡ���������
                y1 = e.getY();
            }
            //�ͷ�����
            public void mouseReleased(MouseEvent e)
            {
                if (isDrag)
                {
                    isDrag = false;
                    x2 = e.getX(); //��ȡѡ���յ�����
                    y2 = e.getY();
                    toolPanel.setBounds(x2,y2, 200, 30);
                    toolPanel.setVisible(true); //ѡ��ȷ������ʾ������ toolPanel
                }
            }
        });
        //�������ƶ�������
        label.addMouseMotionListener(new MouseMotionAdapter()
        {
            public void mouseDragged(MouseEvent e)
            {
                if(!isDrag)
                {
                    isDrag = true;
                }
                //�϶�������ѡ���߿����ʾ������Ļͼ��Ļ����ϼ���ѡ���߿�
                capturedimage = new BufferedImage(
                    screenimage.getWidth(), screenimage.getHeight(), screenimage.getType());
                //ʹ�� java ��ͼ�� Graphics
                Graphics g = capturedimage.getGraphics(); 
                g.drawImage(screenimage,
                            0, 0, screenimage.getWidth(), screenimage.getHeight(), null); 
                
                g.setColor(new Color(0,0,0,50)); 
                g.fillRect(0, 0, screenimage.getWidth(), screenimage.getHeight());
                
                int x = e.getX(); //��ȡ��ǰ����
                int y = e.getY();
                //�� drawRect()�������ƴ�ѡ���߿����Ļͼ�񣨣�
                //ʹ�� Math ��� min �� abs ��������ѡ������ �������ѡ�����   
                g.setColor(new Color(255,255,255,50));  
                g.fillRect(Math.min(x1, x), Math.min(y1, y), Math.abs(x - x1), Math.abs(y - y1));
                g.setColor(Color.BLUE); 
                g.drawRect(Math.min(x1, x), Math.min(y1, y), Math.abs(x - x1), Math.abs(y - y1));
                label.setIcon(new ImageIcon(capturedimage));
            }
        });
        // ��ť�¼�
        cancel.addActionListener(new ActionListener()  //ȡ����ť
        {
            public void actionPerformed(ActionEvent e)
            {
            	 dispose();
            }
            
        });
        //ȷ����ť
        ok.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                 
                if (capturedimage != null)
                {
                    //����Ļͼ���л�ȡѡ����ͼ��
                    BufferedImage subimg = screenimage.getSubimage(
                                               Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2 - x1), Math.abs(y2 - y1));
                    setImageClipboard(subimg); //����д�����巽��
                }
                dispose();
            }
        });
        //���水ť
        save.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            { 
                if (capturedimage != null)
                {
                    BufferedImage subimg = screenimage.getSubimage(
                                               Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2 - x1), Math.abs(y2 - y1));
                    saveImage(subimg);  //���ñ����ļ�����
                }
                dispose();
            }
        });
    }

    public void saveImage(BufferedImage image)
    {
        JFileChooser jf = new JFileChooser(".");
        jf.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int value = jf.showSaveDialog(this); //����Ի���
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
                ImageIO.write(image, "BMP", file); //����ͼ image д��ͼ���ļ�
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    public void setImageClipboard(final Image image)
    {
        Transferable trans = new Transferable()  //����֧��ͼ�����ݴ��ݵ�Transferable����
        {
            //ʵ�� Transferable �ӿ��е���������
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
        Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard(); //��ȡϵͳ������
        cb.setContents(trans, null); //���ü����嵱ǰ����Ϊ trans ����
    }
}
