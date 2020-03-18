package dao;
import model.User;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName UserDaoImpl
 * @Description TODO
 * @Author mingll
 * @Date 2020/3/15 2:52 下午
 * @Version 1.0
 */
public class UserDaoImpl implements UserDao {
    Connection conn = null;
    PreparedStatement stmt=null;
    ResultSet rs = null;
    @Override
    public void add(User user) {

        try {
            conn = DBUtil.getConnection();
            String sql = "INSERT  INTO user (name,identity,phone,appointmentID,lastSelectionID)" + " VALUES (?,?,?,?,?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getIdentity());
            stmt.setString(3, user.getPhone());
            stmt.setObject(4, user.getAppointmentID());
            stmt.setObject(5, user.getLastSelectionID());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(null,stmt,conn);
        }


    }

    @Override
    public List<User> select() {
        ArrayList<User> users=new ArrayList<>();
        User user=new User();
        try {
            conn = DBUtil.getConnection();
            String sql = "select * from user ";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                user.setId(rs.getInt(1));
                user.setName(rs.getString(2));
                user.setIdentity(rs.getString(3));
                user.setPhone(rs.getString(4));
                user.setAppointmentID(rs.getInt(5));
                user.setLastSelectionID(rs.getInt(6));
                users.add(user);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(rs,stmt,conn);
        }
        return users;
    }

    @Override
    public User selectByID(int id) {
        User user=null;
        try {
            conn = DBUtil.getConnection();
            String sql = "select * from user where id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setObject(1,id);
            rs = stmt.executeQuery();
            while (rs.next()) {
                user=new User();
                user.setId(rs.getInt(1));
                user.setName(rs.getString(2));
                user.setIdentity(rs.getString(3));
                user.setPhone(rs.getString(4));
                user.setAppointmentID(rs.getInt(5));
                user.setLastSelectionID(rs.getInt(6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(rs,stmt,conn);
        }
        return user;
    }

    @Override
    public void deleteByIdentity(String identity) {
        try {
            conn = DBUtil.getConnection();
            String sql = "delete from user where identity=?";
            stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(rs,stmt,conn);
        }
    }

    @Override
    public List<User> selectByIdentity(String identity) {
        ArrayList<User> users=new ArrayList<>();
        User user=new User();
        try {
            conn = DBUtil.getConnection();
            String sql = "select * from user where identity=?";
            stmt = conn.prepareStatement(sql);
            stmt.setObject(1,identity);
            rs = stmt.executeQuery();
            while (rs.next()) {
                user.setId(rs.getInt(1));
                user.setName(rs.getString(2));
                user.setIdentity(rs.getString(3));
                user.setPhone(rs.getString(4));
                user.setAppointmentID(rs.getInt(5));
                user.setLastSelectionID(rs.getInt(6));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(rs,stmt,conn);
        }
        return users;
    }

    @Override
    public User selectByIdentityAndCurrentAPPID(String identity, int currentAppID) {
        User user=null;
        try {
            conn = DBUtil.getConnection();
            String sql = "select * from user where identity=? and appointmentID=?";
            stmt = conn.prepareStatement(sql);
            stmt.setObject(1,identity);
            stmt.setObject(2,currentAppID);
            rs = stmt.executeQuery();
            rs.next();
            user=new User();
            user.setId(rs.getInt(1));
            user.setName(rs.getString(2));
            user.setIdentity(rs.getString(3));
            user.setPhone(rs.getString(4));
            user.setAppointmentID(rs.getInt(5));
            user.setLastSelectionID(rs.getInt(6));
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(rs,stmt,conn);
        }
        return user;
    }
    public static void main(String args[]){
        User user=new User();
        User user1=new User();

        user.setPhone("119");
        user.setLastSelectionID(3);
        user.setAppointmentID(3);
        user.setIdentity("456");
        user.setName("z");

        UserDaoImpl dao =new UserDaoImpl();
        dao.add(user);
        System.out.println("哈哈，成功了");
    }
}
