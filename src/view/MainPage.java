package view;

import java.awt.*;//导入awt包
import javax.swing.*;//导入swing包
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import dao.AppointmentDaoImpl;
import dao.SelectionDaoImpl;
import model.Selection;

import java.awt.event.ActionListener;//导入awt包中的监听器事件包
import java.util.ArrayList;
import java.awt.event.ActionEvent;//导入awt包中的ActionEvent事件包

public class MainPage extends JFrame{
    private JPanel contentPane;
    private JTable table;
    private String head[]=null;
    private Object [][]data=null;
	private AppointmentDaoImpl appointmentDaoImpl=new AppointmentDaoImpl();
	private SelectionDaoImpl selectionDaoImpl=new SelectionDaoImpl();
	
    //返回预约开始时间
    public String getBeginTime(JTextField jt) {
        JFrame jframe = new JFrame();
        String begintime = jt.getText();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Date utilDate = null;
//		try {
//			utilDate = sdf.parse(begintime);
//		} catch (ParseException e) {
//			// TODO 自动生成的 catch 块
//			e.printStackTrace();
//		}
//        return utilDate;
        return begintime;
    }

    //返回预约结束时间
    public String getEndTime(JTextField jt) {
    	JFrame jframe = new JFrame();
        String endtime = jt.getText();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Date utilDate = null;
//		try {
//			utilDate = sdf.parse(endtime);
//		} catch (ParseException e) {
//			// TODO 自动生成的 catch 块
//			e.printStackTrace();
//		}
//        return utilDate;
        return endtime;
    }
    
    //返回口罩总数
    public int getTotalMaskNum(JTextField jt) {
        JFrame jframe = new JFrame();
        String totalMask = jt.getText();
        return Integer.parseInt(totalMask);
    }

    //返回单人可预约最大数量
    public int getMaskNum(JTextField jt) {
        JFrame jframe = new JFrame();
        String maskmax = jt.getText();
        return Integer.parseInt(maskmax);
    }
    
  //返回某次预约的预约id
    public int getappointmentID(JTextField jt) {
        JFrame jframe = new JFrame();
        String appointmentID = jt.getText();
        return Integer.parseInt(appointmentID);
    }
    
    //生成数据表格
    public Object[][] selectionData(int appointmentID) {
    	ArrayList<Selection> list=selectionDaoImpl.importSelectedList(appointmentID);
    	data=new Object[list.size()][head.length];
    	
    	for (int i=0;i<list.size();i++) {
    		for (int j=0;j<head.length;j++) {
    			data[i][0]=list.get(i).getId();
    			data[i][1]=list.get(i).getUserID();
    			data[i][2]=list.get(i).getRegisterID();
    			data[i][3]=list.get(i).getTime();
    			data[i][4]=list.get(i).getAppointmentID();
    		}
    	}
		return data;
    }

    //数据库数据填写
    public JPanel setListPanel() {
        JFrame jframe = new JFrame("中签表格");
        JPanel jp = new JPanel();
        jp.setLayout(null);
        jframe.setContentPane(jp);
        int width = 600;
        int heigh = 400;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //得到屏幕的尺寸
        int x = (int) screenSize.getWidth() / 2 - width / 2;
        int y = (int) screenSize.getHeight() / 2 - heigh / 2;
        jframe.setBounds(x, y, width, heigh);
        jframe.setVisible(true);
        
        
        table=new JTable();

        table.setBorder(new LineBorder(new Color(0,0,0)));
        
        JLabel a = new JLabel("请输入预约id:");
        JTextField appointmentID = new JTextField(15);
        JButton check = new JButton("确认");
        JButton cancle = new JButton("取消");
        
        jp.add(a);
        jp.add(appointmentID);
        jp.add(check);
        jp.add(cancle);
        
        a.setBounds(100, 300, 80, 25);
        appointmentID.setBounds(190, 300, 150, 25);
        check.setBounds(350, 300, 80, 30);
        cancle.setBounds(450, 300, 80, 30);
        
        jp.setBorder(new EmptyBorder(5,5,5,5));
        jp.setBounds(500, 500, 700, 250);
        
        JScrollPane scrollPane=new JScrollPane();
        scrollPane.setBounds(500,500,700,250);
        
        
       
        head=new String[] {"中签单编号","用户编号","登记表预约编号","时间","预约编号"};
        
        check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
            	DefaultTableModel tableModel=new DefaultTableModel(selectionData(getappointmentID(appointmentID)),head) {
                	public boolean isCellEditable(int row,int column) {
        				return false;
        			}
                };
                table.setModel(tableModel);
                scrollPane.setViewportView(table);
                GroupLayout gl_contentPane=new GroupLayout(jp);
                gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                		.addComponent(scrollPane,GroupLayout.DEFAULT_SIZE,684,Short.MAX_VALUE));
                gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                		.addGroup(gl_contentPane.createSequentialGroup()
                				.addComponent(scrollPane,GroupLayout.PREFERRED_SIZE,195,
                						GroupLayout.PREFERRED_SIZE).addGap(66)));
            	jp.setLayout(gl_contentPane);	
            }
            	
        });
        
        cancle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                jframe.setVisible(false);
            }
        });

        return jp;
    }

    public JPanel setTimePanel() {
        JFrame jframe = new JFrame("发布预约");
        JPanel jp = new JPanel();
        jp.setLayout(null);
        jframe.setContentPane(jp);
        int width = 420;
        int heigh = 420;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //得到屏幕的尺寸
        int x = (int) screenSize.getWidth() / 2 - width / 2;
        int y = (int) screenSize.getHeight() / 2 - heigh / 2;
        jframe.setBounds(x, y, width, heigh);
        jframe.setVisible(true);
        jp.setLayout(null);
        JLabel a = new JLabel("起始时间:");
        JLabel b = new JLabel("结束时间:");
        JLabel c = new JLabel("口罩数量:");
        JLabel d = new JLabel("单人预约数:");
        JTextField begintime = new JTextField(15);
        JTextField endtime = new JTextField(15);
        JTextField totalnum = new JTextField(15);
        JTextField maskmax = new JTextField(15);
        JButton check = new JButton("确认");
        JButton cancle = new JButton("取消");
        jp.add(a);
        jp.add(begintime);
        jp.add(b);
        jp.add(endtime);
        jp.add(c);
        jp.add(totalnum);
        jp.add(d);
        jp.add(maskmax);
        jp.add(check);
        jp.add(cancle);
        a.setBounds(80, 50, 80, 25);
        begintime.setBounds(150, 50, 150, 25);
        b.setBounds(80, 120, 80, 25);
        endtime.setBounds(150, 120, 150, 25);
        c.setBounds(80, 190, 80, 25);
        totalnum.setBounds(150, 190, 150, 25);
        d.setBounds(70, 260, 80, 25);
        maskmax.setBounds(150, 260, 150, 25);
        check.setBounds(100, 310, 80, 30);
        cancle.setBounds(220, 310, 80, 30);

        check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                appointmentDaoImpl.addAdminAppointment(getBeginTime(begintime),getEndTime(endtime),
                		getTotalMaskNum(totalnum),getMaskNum(maskmax));
                jframe.setVisible(false);
            }
        });
        
        cancle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                jframe.setVisible(false);
            }
        });
        
        return jp;
    }


    public JPanel setMainPanel()
    {
        JFrame jframe = new JFrame("管理员设置");
        JPanel jp = new JPanel();
        jp.setLayout(null);
        jframe.setContentPane(jp);
        int width = 400;
        int heigh = 400;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //得到屏幕的尺寸
        int x = (int) screenSize.getWidth() / 2 - width / 2;
        int y = (int) screenSize.getHeight() / 2 - heigh / 2;
        jframe.setBounds(x, y, width, heigh);
        jframe.setVisible(true);

        JButton buttonTime = new JButton("发布预约");
        JButton buttonTable = new JButton("查看中签表");

        jp.add(buttonTime);
        jp.add(buttonTable);

        buttonTime.setBounds(130, 60, 140, 40);
        buttonTable.setBounds(130, 180, 140, 40);

        buttonTime.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                setTimePanel();
            }
        });

        buttonTable.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                setListPanel();
            }

        });
        return jp;
    }

    public MainPage() {
        setMainPanel();
    }

}