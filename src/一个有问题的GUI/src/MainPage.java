import java.awt.*;//导入awt包
import javax.swing.*;//导入swing包
import javax.swing.table.TableCellEditor;
import java.awt.event.ActionListener;//导入awt包中的监听器事件包
import java.awt.event.ActionEvent;//导入awt包中的ActionEvent事件包
import java.lang.annotation.Target;
public class MainPage extends JFrame {

    //返回预约开始时间
    public String getBeginTime(JTextField begintime) {
        String num = begintime.getText().toString();
        return num;
    }

    //返回预约结束时间
    public String getEndTime(JTextField endtime){
        String num = endtime.getText().toString();
        return num;
    }
    //返回口罩数量
    public int getMasknum(JTextField masknum)
    {
        int num=0;
        String mnum = masknum.getText().toString();
        try {

            num = Integer.valueOf(mnum).intValue();

        } catch (NumberFormatException e) {

            e.printStackTrace();

        }
        return num;
    }

    //设置起始时间面板
    public JPanel SetTimePanle()
    {
        Container mk=getContentPane();
        JPanel Time = new JPanel();
        setSize(450,450);//设计窗体的大小
        JLabel a=new JLabel("起始时间");
        JLabel b=new JLabel("结束时间:");
        JTextField begintime= new JTextField();
        JTextField endtime= new JTextField();
        JButton check = new JButton("确认");
        JButton cancle = new JButton("取消");
        mk.add(Time);
        Time.add(a);
        Time.add(b);
        Time.add(begintime);
        Time.add(endtime);
        Time.add(check);
        Time.add(cancle);
        Time.setLayout(null);

        a.setBounds(110,90,60,40);
        begintime.setBounds(180,100,200,20);
        b.setBounds(110,150,60,40);
        endtime.setBounds(180,160,200,20);
        check.setBounds(150,230,80,30);
        cancle.setBounds(280,230,80,30);

        setVisible(false);
        return Time;
    }

    //设置口罩数量面板
    public JPanel SetMasknumPanle()
    {
        JPanel Masknum = new JPanel();
        setSize(450,450);//设计窗体的大小
        JLabel a=new JLabel("修改口罩数量：");
        JTextField masknum= new JTextField();
        JButton check = new JButton("确认");
        JButton cancle = new JButton("取消");
        Masknum.add(a);
        Masknum.add(masknum);
        Masknum.add(check);
        Masknum.add(cancle);
        Masknum.setLayout(null);

        a.setBounds(90,90,100,40);
        masknum.setBounds(180,100,200,20);
        check.setBounds(130,200,80,30);
        cancle.setBounds(250,200,80,30);

        setVisible(false);
        return Masknum;
    }

    //设置导出表格面板
    public JPanel SetExportPanle()
    {
        JPanel ExportPanle = new JPanel();
        setSize(450,450);//设计窗体的大小
        JLabel a=new JLabel("获取预约表格");
        JTable Table = new JTable();
        ExportPanle.add(a);
        ExportPanle.add(Table);

        ExportPanle.setLayout(null);
        a.setBounds(150,10,100,20);

        setVisible(false);
        return ExportPanle;
    }

    public MainPage() {
        JPanel TimePanle = SetTimePanle();
        JPanel MaskPanle = SetMasknumPanle();
        JPanel ListPanle = SetExportPanle();
        JButton time = new JButton("修改预约时间");
        JButton mask = new JButton("修改口罩数量");
        JButton list = new JButton("查看预约表");
        setVisible(true);//使窗体可视化
        Container mk = getContentPane();//获取一个容器
        mk.add(time);
        mk.add(mask);
        mk.add(list);

        //面板
        mk.add(TimePanle);
        mk.add(MaskPanle);
        mk.add(ListPanle);
        setBounds(200, 200, 800, 600);//设置窗体的长宽各为300、300  让其显示在左上方的300、300处
        mk.setLayout(null);
        time.setBounds(650, 30, 120, 30);
        mask.setBounds(650, 90, 120, 30);
        list.setBounds(650, 150, 120, 30);

        time.addActionListener(new ActionListener() {//对修改时间按钮添加监听事件
            @Override
            public void actionPerformed(ActionEvent arg0) {
                TimePanle.setVisible(true);
                MaskPanle.setVisible(false);
                ListPanle.setVisible(false);
            }
        });

        mask.addActionListener(new ActionListener() {//对修改口罩数量加监听事件
            @Override
            public void actionPerformed(ActionEvent arg0) {
                TimePanle.setVisible(false);
                MaskPanle.setVisible(true);
                ListPanle.setVisible(false);
            }
        });

        list.addActionListener(new ActionListener() {//对查看表格添加监听事件
            @Override
            public void actionPerformed(ActionEvent arg0) {
                TimePanle.setVisible(false);
                MaskPanle.setVisible(false);
                ListPanle.setVisible(true);
            }
        });

    }
}