package dao;
import model.User;
import util.DBUtil;

<<<<<<< HEAD
=======
import util.DBUtil;
import model.User;
>>>>>>> origin/master
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
<<<<<<< HEAD

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
=======
        /**
        * 增加新用户
        * @param user
        */
        	String sql = "insert into user values(null ,? ,? ,? ,?,?)";
            try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
                ps.setString(1, user.getName());
                ps.setString(2, user.getIdentity());
                ps.setString(3, user.getPhone());
                ps.setInt(4, user.getAppointmentID());
                ps.setInt(5,user.getLastSelectionID());
                ps.executeUpdate();
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

>>>>>>> origin/master
    }

    @Override
    public User selectByID(int id) {
<<<<<<< HEAD
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
=======
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

>>>>>>> origin/master
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
<<<<<<< HEAD
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
=======
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
>>>>>>> origin/master

    @Override
    public List<User> selectByIdentity(String identity) {
<<<<<<< HEAD
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
=======
    	String sql = "select * from user where identity=?";
	    try {
	    	List<User> userList = new ArrayList<User>();
	    	Connection c = DBUtil.getConnection(); 
	    	PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1,identity);
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
>>>>>>> origin/master
    }

    @Override
    public User selectByIdentityAndCurrentAPPID(String identity, int currentAppID) {
<<<<<<< HEAD
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
=======
    	String sql = "select * from user where identity=? and appointmentID=?";
    	User user = new User();
	    try {
	    	Connection c = DBUtil.getConnection(); 
	    	PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, identity);
			ps.setInt(2,currentAppID);
			ResultSet rs = ps.executeQuery();
	    	if (rs.next()) {
	    		user.setId(rs.getInt(1));
	    		user.setName(rs.getString(2));
	    		user.setIdentity(rs.getString(3));
	    		user.setPhone(rs.getString(4));
	    		user.setAppointmentID(rs.getInt(5));
	    	}
        }
        catch (SQLException e) {
            e.printStackTrace();
>>>>>>> origin/master
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
