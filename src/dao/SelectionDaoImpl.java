package dao;

import model.Selection;
import model.User;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SelectionDaoImpl
 * @Description TODO
 * @Author mingll
 * @Date 2020/3/15 2:52 下午
 * @Version 1.0
 */
public class SelectionDaoImpl implements SelectionDao {

    /**
     *
     * @param obj 要插入的中签记录对象
     * @return 若插入成功则true 否则false
     */
    @Override
    public  void add(Selection obj){
        try {
            Connection conn = null;
            conn = DBUtil.getConnection();
            String sql = "INSERT  INTO selection (id,userID,registerID,Date,appointmentID)" +
                    " VALUES (?,?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);;
            stmt.setInt(1, obj.getId());
            stmt.setInt(2, obj.getUserID());
            stmt.setInt(3, obj.getRegisterID());
            stmt.setObject(4, obj.getTime());
            stmt.setInt(5, obj.getAppointmentID());

            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * @param id 预约编号
     * @return 若中签表中存在这个人的记录则返回true 否则返回false
     * 查询是否中签，若中签则
     * 刘昭玮
     */
    public ArrayList<Object> isExistSelection(int id){
        ArrayList<Object> resultData = new ArrayList<Object>();//最终数据
        ArrayList<Selection> list = new ArrayList<Selection>();;
        try {
            Connection conn = null;
            conn = DBUtil.getConnection();
            //先查中签表
            String sql = "SELECT FROM selection WHERE registerID=?";
            PreparedStatement stmt = conn.prepareStatement(sql);;
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();//执行查
            ResultSetMetaData md = rs.getMetaData();//获取键名
            while(rs.next()){
                Selection tmp = new Selection();
                tmp.setId(rs.getInt("id"));
                tmp.setUserID(rs.getInt("userID"));
                tmp.setRegisterID(rs.getInt("registerID"));
                tmp.setTime(rs.getDate("time"));
                tmp.setAppointmentID(rs.getInt("appointmentID"));
                list.add(tmp);
            }

            if(list.size()==0){//没用中签记录
                return null;
            }
            else {
                ArrayList<Object> data = new  ArrayList<Object>();
                int appointmentID = list.get(0).getAppointmentID();//得到预约id  确定只有一条吗?????
                /////再查用户表
                String SQL = "SELECT FROM user WHERE appointmentID=?";
                stmt.setInt(1, appointmentID);
                ResultSet resullt = stmt.executeQuery();//执行查
                User tmpUser = new User();
                while(resullt.next()){
                    tmpUser.setId(resullt.getInt("id"));
                    tmpUser.setName(resullt.getString("name"));
                    tmpUser.setIdentity(resullt.getString("identity"));
                    tmpUser.setPhone(resullt.getString("phone"));
                    tmpUser.setAppointmentID(resullt.getInt("appointmentID"));
                }

                //再查登记表，得到口罩数量
                String findRespirator = "SELECT FROM register WHERE id=?";
                stmt.setInt(1,id);//registerID
                ResultSet respiratorResult = stmt.executeQuery();//执行查
                respiratorResult.next();
                int respiratorNumber = respiratorResult.getInt("mask");

                ///准备返回的数据，三个String 一个int
                resultData.add(tmpUser.getName());
                resultData.add(tmpUser.getId());
                resultData.add(tmpUser.getPhone());
                resultData.add(respiratorNumber);
            }

            stmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultData;
    }

    /**
     * @param appointmentID 某次预约表ID
     * @return 中签名单
     * 张玉婷
     */
    public ArrayList<Selection> importSelectedList(int appointmentID){
        ArrayList<Selection> list = new ArrayList<Selection>();

        try {
            Connection conn = null;
            conn = DBUtil.getConnection();
            String sql = "SELECT FROM selection WHERE appointmentID=?";
            PreparedStatement stmt = conn.prepareStatement(sql);;
            stmt.setInt(1, appointmentID);
            ResultSet rs = stmt.executeQuery();//执行查询
            ResultSetMetaData md = rs.getMetaData();//获取键名
            int columnCount = md.getColumnCount();//获取行的数量
            while(rs.next()){
                Selection tmp = new Selection();
                tmp.setId(rs.getInt("id"));
                tmp.setUserID(rs.getInt("userID"));
                tmp.setRegisterID(rs.getInt("registerID"));
                tmp.setTime(rs.getDate("time"));
                tmp.setAppointmentID(rs.getInt("appointmentID"));
                list.add(tmp);
            }


            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(list.size()==0){
            return null;
        }
        else {
            return list;
        }
    }

    /**
     * @param indetityNumber 身份证号码
     * @return 返回表中这个身份证所有的记录
     */
    @Override
    public List<Selection> findTotal(String indetityNumber){

        return null;
    }

    /**
     * @param indetityNumber 身份证号码
     * @return 若中签表中存在这个人的记录则返回true 否则返回false
     */
    @Override
    public boolean isExist(String indetityNumber){
        return  true;
    }

    /**
     * 根据id属性查找。
     * @param id
     * @return
     */
    @Override
    public Selection selectByID(int id) {
        return null;
    }


}
