package FrontEnd;

import dao.SelectionDao;
import dao.SelectionDaoImpl;
import model.Selection;
import service.RegisterService;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class Program
{

    public static void main(String[] args)
    {
        //窗口宽度与高度
        int width=700;
        int height=500;

        RegisterService registTestClass = new RegisterService();
        SelectionDaoImpl queryTestClass=new SelectionDaoImpl();
        JFrame frame = new JFrame("SignUp");
        frame.setContentPane(new SignUp(registTestClass,queryTestClass).userPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //窗口大小设置
        Dimension dimension = new Dimension();
        dimension.setSize(width,height);
        frame.setPreferredSize(dimension);
        //窗口居中
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) screenSize.getWidth() / 2 - width/ 2;
        int y = (int) screenSize.getHeight() / 2 - height/ 2;
        frame.setLocation(x, y);

        frame.pack();
        frame.setVisible(true);

    }


}
