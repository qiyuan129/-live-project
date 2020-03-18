package FrontEnd;

import dao.*;
import model.Register;
import model.User;
import service.AppointmentService;
import service.RegisterService;
import service.SelectionService;
import view.Login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SignUp extends JFrame
{

    JTextField nameTextField1;
    JTextField IDTextField2;
    JTextField phoneTextField3;
    JButton 提交预约Button;
    JPanel SignInPanel;
    JTextField registIDTextField5;
    JButton 查询Button;

    JPanel userPanel;
    private JComboBox numberComboBox1;
    private JButton addAppointmentBtn;
    private JButton endAppointmentBtn;
    private JButton 管理员登陆Button;


    public SignUp(RegisterService registTestClass, SelectionService queryTestClass)
    {
        提交预约Button.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (nameTextField1.getText().isEmpty() || IDTextField2.getText().isEmpty()
                    || phoneTextField3.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null, "登记表格中填写信息不能为空", "提示",
                        JOptionPane.WARNING_MESSAGE);
                }
                else
                {
                    String name = nameTextField1.getText();
                    String ID = IDTextField2.getText();
                    String phone = phoneTextField3.getText();
                    int number = Integer.parseInt(numberComboBox1.getSelectedItem().toString());
                    int registID = -1;
                    try
                    {
                        if ((registID = registTestClass.sendMessage(name, ID, phone, number)) != -1)
                        {
                            JOptionPane
                                .showMessageDialog(null, "预约成功,您的预约编号为:" + registID, "提示",
                                    JOptionPane.WARNING_MESSAGE);
                        }
                        else
                        {
                            JOptionPane
                                .showMessageDialog(null, "预约失败！", "提示",
                                    JOptionPane.WARNING_MESSAGE);
                        }
                    }
                    catch(Exception ex)
                    {
                        JOptionPane
                            .showMessageDialog(null, "预约失败！", "提示",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }
                return;


            }
        });
        查询Button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int registID;
                if ((registIDTextField5.getText()).isEmpty())
                {
                    JOptionPane
                        .showMessageDialog(null, "预约编号不能为空", "提示", JOptionPane.WARNING_MESSAGE);
                }
                else
                {
                    try
                    {
                        registID = Integer.parseInt(registIDTextField5.getText());
                        User user = null;
                        if ((user = queryTestClass.isExistSelection(registID)) != null)
                        {
                            RegisterDao service = new RegisterDaoImpl();
                            Register register = service.selectByUserID(user.getId());
                            String name = user.getName();
                            String ID = user.getIdentity();
                            String phone = user.getPhone();
                            int number = register.getMask();
                            JOptionPane
                                .showMessageDialog(null, "您的购买凭证如下:"
                                    + "\n姓名:" + name
                                    + "\n身份证号:" + ID
                                    + "\n电话号码:" + phone
                                    + "\n购买数量:" + number, "提示", JOptionPane.WARNING_MESSAGE);
                        }
                        else
                        {
                            JOptionPane
                                .showMessageDialog(null, "该预约编号未中签", "提示",
                                    JOptionPane.WARNING_MESSAGE);
                        }


                    }catch (Exception ex)
                    {
                        JOptionPane
                            .showMessageDialog(null, "查询错误，请稍后重试", "提示",
                                JOptionPane.WARNING_MESSAGE);

                    }

                }
            }
        });
        addAppointmentBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AppointmentDao appointmentDao=new AppointmentDaoImpl();
                appointmentDao.addAppointment();
                JOptionPane
                        .showMessageDialog(null, "开启新的预约", "提示",
                                JOptionPane.WARNING_MESSAGE);
            }
        });
        endAppointmentBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SelectionService service = new SelectionService();
                service.createSelection();
                AppointmentService appointmentService=new AppointmentService();
                appointmentService.endLatestAppointment();
                JOptionPane
                        .showMessageDialog(null, "已结束当前预约", "提示",
                                JOptionPane.WARNING_MESSAGE);

            }
        });
        管理员登陆Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Login();
            }
        });
    }
}



