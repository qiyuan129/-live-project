package dao;

import model.Register;
import model.Selection;
import model.User;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName SelectionDaoImpl
 * @Description TODO
 * @Author mingll
 * @Date 2020/3/15 2:52 下午
 * @Version 1.0
 */
public class SelectionDaoImpl implements SelectionDao {
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;
    private DBUtil dbu=new DBUtil();
    /**
     * @param obj 要插入的中签记录对象
     * @return 若插入成功则true 否则false
     */
    @Override
    public void add(Selection obj) {
        try {
            conn = DBUtil.getConnection();
            //id为自增，所以不用指定
            String sql = "INSERT  INTO selection (userID,registerID,time,appointmentID)" + " VALUES (?,?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, obj.getUserID());
            stmt.setInt(2, obj.getRegisterID());
            stmt.setObject(3, obj.getTime());
            stmt.setInt(4, obj.getAppointmentID());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(null,stmt,conn);
        }

    }


    /**
     * @param array  Selection数组
     *
     */
    @Override
    public void addSelections(ArrayList<Selection> array){
        try {
            conn = DBUtil.getConnection();
            for(Selection obj:array){
                String sql = "INSERT  INTO selection (userID,registerID,time,appointmentID)" +
                        " VALUES (?,?,?,?)";
                stmt = conn.prepareStatement(sql);
                stmt.setInt(1, obj.getUserID());
                stmt.setInt(2, obj.getRegisterID());
                stmt.setObject(3, obj.getTime());
                stmt.setInt(4, obj.getAppointmentID());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(null,stmt,conn);
        }
    }




    /**
     * @param appointmentID 某次预约表ID
     * @return 中签名单
     * 张玉婷
     */
    public ArrayList<Selection> importSelectedList(int appointmentID) {
        ArrayList<Selection> list = new ArrayList<Selection>();
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT * FROM selection WHERE appointmentID=?";
            /* String sql="SELECT * from register WHERE userID=?";*/
            stmt= conn.prepareStatement(sql);
            stmt.setInt(1, appointmentID);
            rs = stmt.executeQuery();//执行查询
            ResultSetMetaData md = rs.getMetaData();//获取键名
            int columnCount = md.getColumnCount();//获取行的数量
            while (rs.next()) {
                Selection tmp = new Selection();
                tmp.setId(rs.getInt("id"));
                tmp.setUserID(rs.getInt("userID"));
                tmp.setRegisterID(rs.getInt("registerID"));
                tmp.setTime(rs.getDate("time"));
                tmp.setAppointmentID(rs.getInt("appointmentID"));
                list.add(tmp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(rs,stmt,conn);
        }

        if (list.size() == 0) {
            return null;
        } else {
            return list;
        }
    }

    /**
     * @param indetityNumber 身份证号码
     * @return 返回表中这个身份证所有的记录
     */
    @Override
    public List<Selection> findTotal(String indetityNumber) {
        return null;
    }


    /**
     * 根据id属性查找。
     *
     * @param id
     * @return
     */
    @Override
    public Selection selectByID(int id) {
        Selection selection = null;
        try {
            Connection conn = DBUtil.getConnection();
            String sql = "SELECT * from register WHERE userID=?";
            PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                selection = new Selection();
                Timestamp time = rs.getTimestamp(5);
                selection.setId(rs.getInt(1));
                selection.setUserID(rs.getInt(2));
                selection.setRegisterID(rs.getInt(3));
                selection.setTime(rs.getTime(4));
                selection.setAppointmentID(5);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return selection;
    }


    /**
     * @param registerID 预约ID
     * @return 返回Selection
     */
    @Override
    public Selection findByRegisterID(int registerID){
        Selection item=null;
        try {
            conn = DBUtil.getConnection();
            //先查中签表
            String sql = "SELECT * FROM  selection WHERE registerID=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, registerID);
            rs = stmt.executeQuery();//执行查
            ResultSetMetaData md = rs.getMetaData();//获取键名
            rs.next();
            Selection tmp = new Selection();
            tmp.setId(rs.getInt("id"));
            tmp.setUserID(rs.getInt("userID"));
            tmp.setRegisterID(rs.getInt("registerID"));
            tmp.setTime(rs.getDate("time"));
            tmp.setAppointmentID(rs.getInt("appointmentID"));
            item=tmp;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(rs,stmt,conn);
        }
        return item;
    }


    /**
     * @param appointmentID 预约ID
     * @return
     */
    @Override
    public User getUserByAppointmentID(int appointmentID){
        User user=null;
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT * FROM user WHERE appointmentID=?";
            stmt= conn.prepareStatement(sql);
            stmt.setInt(1, appointmentID);
            rs = stmt.executeQuery();//执行查
            User tmpUser = new User();
            rs.next();
            tmpUser.setId(rs.getInt("id"));
            tmpUser.setName(rs.getString("name"));
            tmpUser.setIdentity(rs.getString("identity"));
            tmpUser.setPhone(rs.getString("phone"));
            tmpUser.setAppointmentID(rs.getInt("appointmentID"));
            user=tmpUser;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(rs,stmt,conn);
        }
        return user;
    }


    @Override
    public void updateUserLastSelectionID( ArrayList<Selection> array){
        User user=null;
        try {
            conn = DBUtil.getConnection();
            for(Selection item:array){
                //UPDATE 表名称 SET 列名称 = 新值 WHERE 列名称 = 某值
                String sql = "UPDATE user SET lastSelectionID=? WHERE id=?";
                stmt= conn.prepareStatement(sql);
                stmt.setInt(1, item.getId());
                stmt.setInt(2, item.getUserID());
                stmt.executeUpdate();//执行查
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            DBUtil.close(null,stmt,conn);
        }

    }



    public static void main(String args[]){
        int id=12;
        SelectionDaoImpl dao = new SelectionDaoImpl();
        /*Selection sel=new Selection();
        Selection sel2=new Selection();

        sel2.setUserID(25);
        sel2.setRegisterID(544);
        sel2.setTime(new Date(25634));
        sel2.setAppointmentID(21);

        sel.setAppointmentID(91);
        sel.setTime(new Date(1544555));
        sel.setRegisterID(153);
        sel.setUserID(25);
        ArrayList<Selection> array=new  ArrayList<Selection>();
        array.add(sel);
        array.add(sel2);*/

        /*  ArrayList<Object> rear =dao.isExistSelection(5);*/
        /* ArrayList<Object> qray =dao.isExistSelection(5);*/
     /*  for(Object o:rear){
           System.out.println(o);
       }*/
       /* for(Object ao:qray){
            System.out.println(ao);
        }*/

        ArrayList<Selection> tmpay= dao.importSelectedList(11);

        dao.updateUserLastSelectionID(tmpay);

      /*  System.out.println(tmpay.size());
         for(int i=0;i<tmpay.size();i++){
             System.out.println(tmpay.get(i).getUserID());
         }*/
        System.out.println("哈哈，成功了");
    }


}
