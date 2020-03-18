<<<<<<< HEAD
package Dao;
=======
package dao;
>>>>>>> 70f126be75391b80bae63d23c57ef7f95a4532cf

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Register;
import util.DBUtil;


public class RegisterDaoImpl implements RegisterDao {

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private DBUtil dbu=new DBUtil();

	@Override
	public void addRegister(Register register) {
				
		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        String datetime = df.format(register.getTime());
			
			conn = dbu.getConnection();
			String sql="insert into register(userID , mask , appointmentID , time) value(?,?,?,?)";
			pstmt=(PreparedStatement) conn.prepareStatement(sql);
			pstmt.setInt(1, register.getUserID());
			pstmt.setInt(2,register.getMask());
			pstmt.setInt(3,register.getAppointment());
			pstmt.setObject(4, datetime);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pstmt, conn);
		}
		
	}

	@Override
	public List<Register> select() {
		List<Register> list= new ArrayList<Register>();
		Register register=null;
		try {
			conn = dbu.getConnection();
			String sql="SELECT * from register";
			pstmt=(PreparedStatement) conn.prepareStatement(sql);			
			rs = pstmt.executeQuery();
            while (rs.next()) {
            	Timestamp time = rs.getTimestamp(5);
            	
            	register = new Register(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),new Date(time.getTime()));
            	list.add(register);
            }

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pstmt, conn);
		}
		return list;
	}

	@Override
	public Register selectByUserID(int id) {
		Register register=null;
		try {
			conn = dbu.getConnection();
			String sql="SELECT * from register WHERE userID=?";
			pstmt=(PreparedStatement) conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			rs = pstmt.executeQuery();
            while (rs.next()) {
            	Timestamp time = rs.getTimestamp(5);
            	
            	register = new Register(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),new Date(time.getTime()));
            }

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pstmt, conn);
		}
		return register;
	}

	@Override
	public void deleteByID(int id) {
		Register register=null;
		try {
			conn = dbu.getConnection();
			String sql="delete from register where id=? "; 
			pstmt=(PreparedStatement) conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pstmt, conn);
		}
		
	}
	
	@Override
	public List<Register> getByAppointmentID(int id) {
		Register register=null;
		List<Register> list = new ArrayList<Register>();
		try {
			conn=DBUtil.getConnection();
			String sql="SELECT * FROM register where appointmentID = ?";
			pstmt=(PreparedStatement) conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				Timestamp time = rs.getTimestamp(5);

				register = new Register(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),
										new Date(time.getTime()));
				list.add(register);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pstmt, conn);
		}
		return list;
	}
	

}
