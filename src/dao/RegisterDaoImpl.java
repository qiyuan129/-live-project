package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Appointment;
import model.Register;


public class RegisterDaoImpl implements DAO.RegisterDao {

	

	@Override
	public void addRegister(Register register) {
		DAO.DBUtil dbu=new DAO.DBUtil();
		
		Connection conn;
		
		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        String datetime = df.format(register.getTime());
			
			conn = dbu.getConnection();
			String sql="insert into register(userID , mask , appointmentID , time) value(?,?,?,?)";
			PreparedStatement pstmt=(PreparedStatement) conn.prepareStatement(sql);
			pstmt.setInt(1, register.getUserID());
			pstmt.setInt(2,register.getMask());
			pstmt.setInt(3,register.getAppointment());
			pstmt.setObject(4, datetime);
			pstmt.executeUpdate();

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Register> select() {
		List<Register> list= new ArrayList<Register>();
		DAO.DBUtil dbu=new DAO.DBUtil();
		Connection conn=null;
		Register register=null;
		try {
			conn = dbu.getConnection();
			String sql="SELECT * from register";
			PreparedStatement pstmt=(PreparedStatement) conn.prepareStatement(sql);			
			ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
            	Timestamp time = rs.getTimestamp(5);
            	
            	register = new Register(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),new Date(time.getTime()));
            	list.add(register);
            }

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Register selectByUserID(int id) {
		DAO.DBUtil dbu=new DAO.DBUtil();
		Connection conn=null;
		Register register=null;
		try {
			conn = dbu.getConnection();
			String sql="SELECT * from register WHERE userID=?";
			PreparedStatement pstmt=(PreparedStatement) conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
            	Timestamp time = rs.getTimestamp(5);
            	
            	register = new Register(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),new Date(time.getTime()));
            }

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return register;
	}

	@Override
	public void deleteByID(int id) {
		DAO.DBUtil dbu=new DAO.DBUtil();
		Connection conn=null;
		Register register=null;
		try {
			conn = dbu.getConnection();
			String sql="delete from register where id=? "; 
			PreparedStatement pstmt=(PreparedStatement) conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	

}
