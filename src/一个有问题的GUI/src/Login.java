
import java.awt.*;//导入awt包
import javax.swing.*;//导入swing包

import dao.AdminDaoImpl;
import model.Admin;
import util.DBUtil;

import java.awt.event.ActionListener;//导入awt包中的监听器事件包
import java.awt.event.ActionEvent;//导入awt包中的ActionEvent事件包

public class Login extends JFrame {
    private JButton check;
    private JButton cancel;
    private JTextField id;
    private JPasswordField password;
    
    private static Admin admin;

    public Login() {
        JLabel a=new JLabel("管理员登录");
        JLabel b=new JLabel("用户名:");
        JLabel c=new JLabel("密    码:");
        id=new JTextField(15);
        password =new JPasswordField(15);
        password.setEchoChar('*');
        check=new JButton("确定");
        cancel=new JButton("重置");
        setVisible(true);
        Container mk=getContentPane();

        mk.add(a);
        mk.add(b);
        mk.add(c);
        mk.add(id);
        mk.add(password);
        mk.add(check);
        mk.add(cancel);
        setBounds(200,200,600,400);//设置窗体的长宽各为300、300  让其显示在左上方的300、300处
        mk.setLayout(null);

        a.setBounds(250,40,200,30);
        b.setBounds(150,90,60,40);
        id.setBounds(200,100,200,20);
        c.setBounds(150,150,60,40);
        password.setBounds(200,160,200,20);
        check.setBounds(190,230,80,30);
        cancel.setBounds(320,230,80,30);


        cancel.addActionListener(new ActionListener() {//对重置按钮添加监听事件

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                id.setText("");//对用户名文本框进行重置
                password.setText("");//对密码文本框进行重置
            }

        });


        //获取数据库ID,PASSWORD用于判断登录
        check.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
            	admin = AdminDaoImpl.adminLogin(id.getText(), password.getText());
                if(admin.getId() != null){
                    JOptionPane.showMessageDialog(null, "登录成功");
                    new MainPage();

                }else {
                    JOptionPane.showMessageDialog(null, "登录失败");
                }

            }
        });
        
    }

    public static void main(String[] args) {
        new Login();

    }

}
