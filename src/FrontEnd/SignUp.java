package FrontEnd;
import dao.SelectionDao;
import dao.SelectionDaoImpl;
import service.RegisterService;

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


    public SignUp(RegisterService registTestClass, SelectionDaoImpl queryTestClass)
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
                    //------------------------------Regist测试

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
                        ArrayList<Object> certificInfo = new ArrayList<>();
                        if ((certificInfo = queryTestClass.isExistSelection(registID)) != null)
                        {
                            String name = certificInfo.get(0).toString();
                            String ID = certificInfo.get(1).toString();
                            String phone = certificInfo.get(2).toString();
                            int number = Integer.parseInt(certificInfo.get(3).toString());
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
    }
}



