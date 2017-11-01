import java.awt.event.*;
import java.io.*;
import java.util.zip.*;
import javax.swing.*;
import javax.swing.filechooser.*;
import javax.swing.filechooser.FileFilter;

public class ZipDemo implements ActionListener
{
    JFrame frame;
    JLabel lb;
    String file = null;

    // ��������Ͳ˵�
    public void initMenu()
    {
        // ��������
        frame = new JFrame("MY Frame");
        // �����˵�
        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);
        JMenu menu1 = new JMenu("�ļ�");
        menubar.add(menu1);
        JMenu menu2 = new JMenu("ѹ��");
        menubar.add(menu2);
        JMenuItem item11 = new JMenuItem("��");
        menu1.add(item11);
        JMenuItem item13 = new JMenuItem("�˳�");
        menu1.add(item13);
        JMenuItem item21 = new JMenuItem("ѹ��");
        menu2.add(item21);
        JMenuItem item22 = new JMenuItem("��ѹ��");
        menu2.add(item22);

        // ��Ӽ�����
        item11.addActionListener(this);
        item13.addActionListener(this);
        item21.addActionListener(this);
        item22.addActionListener(this);

        // ��ӱ�ǩ
        lb = new JLabel();
        frame.add(lb);

        // ���ô�������
        frame.setSize(400, 300);
        frame.setLocation(200, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    // main����
    public static void main(String[] args)
    {
        ZipDemo t1 = new ZipDemo();
        t1.initMenu();
    }
    
    // �����¼���Ӧ
    public void actionPerformed(ActionEvent e)
    {
        String itemname = e.getActionCommand();
        if (itemname.equals("��"))
        {
            file = choosefile();
            lb.setText(file);
        }
        if (itemname.equals("ѹ��"))
        {
            if (file != null)
            {
                try
                {
                    zip(file);
                }
                catch (IOException e1)
                {
                    e1.printStackTrace();
                }
            }
        }
        if (itemname.equals("��ѹ��"))
        {
            if (file != null)
            {
                try
                {
                    unzip(file);
                }
                catch (IOException e1)
                {
                    e1.printStackTrace();
                }
            }
        }
        if (itemname.equals("�˳�"))
        {
            frame.dispose();
        }
    }

    // �ļ�ѡ��modeΪ1��zip�� modeΪ2��unzip
    public String choosefile()
    {
        JFileChooser fileChooser = new JFileChooser("d:");
        File file = null;
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int n = fileChooser.showOpenDialog(null);
        if (n == JFileChooser.APPROVE_OPTION)
        {
            file = fileChooser.getSelectedFile();
        }
        if (file == null)
            return null;
        else
            return file.getAbsolutePath();
    }

    // �ļ����棬modeΪ1��zip�� modeΪ2��unzip
    public String savefile(int mode)
    {
        JFileChooser fileChooser = new JFileChooser("d:");
        File file = null;
        if (mode == 1)
        {
            FileFilter filter = new FileNameExtensionFilter("ѹ���ļ�", "ZIP");
            fileChooser.setFileFilter(filter);
        }
        else
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int n = fileChooser.showSaveDialog(null);
        if (n == JFileChooser.APPROVE_OPTION)
        {
            file = fileChooser.getSelectedFile();
            String fname = file.getAbsolutePath();
            if (mode == 1)
            {
                if (!(fname.endsWith(".zip") || fname.endsWith(".ZIP")))
                    fname = fname + ".zip";
            }
            return fname;
        }
        else
            return null;
    }

    // unzip
    public void unzip(String filename) throws IOException
    {
        System.out.println(filename);
        FileInputStream fin = new FileInputStream(filename);
        ZipInputStream zin = new ZipInputStream(fin);
        String dir = savefile(2);
        if (!dir.endsWith("\\"))  dir = dir + "\\";
        ZipEntry entry;
        while ((entry = zin.getNextEntry()) != null)
        {
            if (entry.isDirectory())
            {
                File f = new File(dir + entry.getName());
                f.mkdirs();
            }
            else
            {
                File f = new File(dir + entry.getName());
                f.createNewFile();
                FileOutputStream fout = new FileOutputStream(f);
                int b;
                while ((b = zin.read()) != -1)
                    fout.write(b);
                fout.close();
            }
            zin.closeEntry();
        }
        zin.close();
        fin.close();
    }

    public void zip(String dirname) throws IOException
    {
        String file = savefile(1);
        if (file != null)
        {
            FileOutputStream fout = new FileOutputStream(file);
            ZipOutputStream zout = new ZipOutputStream(fout);
            File dir = new File(dirname);
            compressDir(dir, zout, "");
            zout.close();
            fout.close();
        }
    }

    public void compressDir(File dir, ZipOutputStream zout, String basedir)
    throws IOException
    {
        if (dir.isDirectory())
        {
            String subdir = basedir + dir.getName() + "/";
            ZipEntry entry = new ZipEntry(subdir);
            zout.putNextEntry(entry);
            File[] list = dir.listFiles();
            for (File f : list)
                compressDir(f, zout, subdir);
        }
        else {
            ZipEntry entry = new ZipEntry(basedir + dir.getName());
            zout.putNextEntry(entry);
            FileInputStream fin = new FileInputStream(dir);
            int b;
            while ((b = fin.read()) != -1)
                zout.write(b);
            fin.close();
        }
    }
}


