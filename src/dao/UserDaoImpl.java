package dao;


import model.User;

import util.DBUtil;
import model.User;
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
    @Override
    public void add(User user) {
        /**
        * 增加新用户
        * @param user
        */
        	String sql = "insert into user values(null ,? ,? ,? ,?)";
            try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
                ps.setString(1, user.getName());
                ps.setString(2, user.getIdentity());
                ps.setString(3, user.getPhone());
                ps.setInt(4, user.getAppointmentID());
                ps.execute();
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    user.setId(id);
                }
            } catch (SQLException e) {

                e.printStackTrace();
            }
        }

    @Override
    public List<User> select() {
        /**
         * 查询所有用户信息
         * @return 所有用户信息
         */
    	String sql = "select * from user";
	    try {
	    	List<User> userList = new ArrayList<User>();
	    	Connection c = DBUtil.getConnection(); 
	    	PreparedStatement ps = c.prepareStatement(sql);
	    	//try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
            //String sql = "select * from user";
            ResultSet rs = ps.executeQuery();
            //遍历结果集
            while(rs.next())
            	{
            	//创建User对象
            	User user_obj = new User();
            	//从数据库从得到相应字段的值
            	user_obj.setId(rs.getInt("id"));
            	user_obj.setName(rs.getString("name"));
            	user_obj.setIdentity(rs.getString("identity"));
            	user_obj.setPhone(rs.getString("phone"));
            	user_obj.setAppointmentID(rs.getInt("appointmentID"));
            	userList.add(user_obj);
			}
            return userList;
        }
        catch (SQLException e) {

            e.printStackTrace();
        }
        return null;

    }

    @Override
    public User selectByID(int id) {
        /**
         * 根据UserID查找信息
         * @param id
         * @return 若有返回User，没有返回null;
         */
    	String sql = "select * from user where id=?";
	    try {
	    	Connection c = DBUtil.getConnection(); 
	    	PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1,id);
			ps.executeUpdate();
	    	if (ps!=null) {
	    		User user_obj = new User();
            	return user_obj;
	    	}
        }
        catch (SQLException e) {

            e.printStackTrace();
        }
        return null;

    }

    public void deleteById(int id) {
        /**
         * 根据UserID删除信息
         * @param id
         */
    		String sql="delete from user where id=?";
    		try {
    			Connection c=DBUtil.getConnection();
    			PreparedStatement ps=(PreparedStatement) c.prepareStatement(sql);
    			ps.setInt(1,id);
    			ps.executeUpdate();
    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    }
    
    @Override
    public void deleteByIdentity(String identity) {
	    /**
	     * 根据身份证删除信息
	     * @param identity
	     */
		String sql="delete from user where identity=?";
		try {
			Connection c=DBUtil.getConnection();
			PreparedStatement ps=(PreparedStatement) c.prepareStatement(sql);
			ps.setString(1,identity);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



    @Override
    public List<User> selectByIdentity(String identity) {
    	String sql = "select * from user where identity=?";
	    try {
	    	List<User> userList = new ArrayList<User>();
	    	Connection c = DBUtil.getConnection(); 
	    	PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1,identity);
			ps.executeUpdate();
	    	//try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
            //String sql = "select * from user";
            ResultSet rs = ps.executeQuery();
            //遍历结果集
            while(rs.next())
            	{
            	//创建User对象
            	User user_obj = new User();
            	//从数据库从得到相应字段的值
            	user_obj.setId(rs.getInt("id"));
            	user_obj.setName(rs.getString("name"));
            	user_obj.setIdentity(rs.getString("identity"));
            	user_obj.setPhone(rs.getString("phone"));
            	user_obj.setAppointmentID(rs.getInt("appointmentID"));
            	userList.add(user_obj);
			}
            return userList;
        }
        catch (SQLException e) {

            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User selectByIdentityAndCurrentAPPID(String identity, int currentAppID) {
    	String sql = "select * from user where identity=? and appointmentID=?";
	    try {
	    	Connection c = DBUtil.getConnection(); 
	    	PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, identity);
			ps.setInt(2,currentAppID);
			ps.executeUpdate();
	    	if (ps!=null) {
	    		User user_obj = new User();
            	return user_obj;
	    	}
        }
        catch (SQLException e) {

            e.printStackTrace();
        }
        return null;
    }


}
