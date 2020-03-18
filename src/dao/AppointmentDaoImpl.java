package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import model.Appointment;
import model.Register;

public class AppointmentDaoImpl implements AppointmentDao{

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private DBUtil dbu=new DBUtil();
	
	@Override
	public void addAppointment(Date begin, Date end, int totalMask, int maskMAX) {
		
		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        String beginString = df.format(begin);
	        String endString = df.format(end);
			
			conn = dbu.getConnection();
			String sql="insert into appointment(start , end , mask , maskMAX) value(?,?,?,?)";
			pstmt=(PreparedStatement) conn.prepareStatement(sql);
			pstmt.setObject(1, beginString);
			pstmt.setObject(2, endString);
			pstmt.setInt(3, totalMask);
			pstmt.setInt(4,maskMAX);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pstmt, conn);
		}
		
	}
	
	@Override
	public void addAdminAppointment(String beginString, String endString, int totalMask, int maskMAX) {
		DBUtil dbu=new DBUtil();
		Connection conn=null;
		try {
			conn = dbu.getConnection();
			String sql="insert into appointment(start , end , mask , maskMAX) value(?,?,?,?)";
			PreparedStatement pstmt=(PreparedStatement) conn.prepareStatement(sql);
			pstmt.setObject(1, beginString);
			pstmt.setObject(2, endString);
			pstmt.setInt(3, totalMask);
			pstmt.setInt(4,maskMAX);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void addAppointment(Date begin, Date end) {
		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        String beginString = df.format(begin);
	        String endString = df.format(end);
			
			conn = dbu.getConnection();
			String sql="insert into appointment(start , end , mask , maskMAX) value(?,?,?,?)";
			pstmt=(PreparedStatement) conn.prepareStatement(sql);
			pstmt.setObject(1, beginString);
			pstmt.setObject(2, endString);
			pstmt.setInt(3, 1000);
			pstmt.setInt(4,3);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pstmt, conn);
		}
		
	}

	@Override
	public void addAppointment() {
		Date now = new Date();
		Calendar nowDate = Calendar.getInstance();  
		nowDate.setTime(now);  
		nowDate.add(Calendar.MONTH, 1);  
        Date after = nowDate.getTime();  

		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        String beginString = df.format(now);
	        String endString = df.format(after);
			
			conn = dbu.getConnection();
			String sql="insert into appointment(start , end , mask , maskMAX) value(?,?,?,?)";
			pstmt=(PreparedStatement) conn.prepareStatement(sql);
			pstmt.setObject(1, beginString);
			pstmt.setObject(2, endString);
			pstmt.setInt(3, 1000);
			pstmt.setInt(4,3);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pstmt, conn);
		}
	}

	@Override
	public Appointment getAppointment(int id) {
		Appointment appointment=null;
		try {
			conn = dbu.getConnection();
			String sql="SELECT * from appointment WHERE id=?";
			pstmt=(PreparedStatement) conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			rs = pstmt.executeQuery();
            while (rs.next()) {
            	Timestamp startStamp = rs.getTimestamp(2);
            	Timestamp endStamp = rs.getTimestamp(3);
            	
            	appointment = new Appointment(rs.getInt(1),new Date(startStamp.getTime()),new Date(endStamp.getTime()),rs.getInt(4),rs.getInt(5));
            }

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pstmt, conn);
		}
		return appointment;
	}

	@Override
	public List<Appointment> getAppointmentList() {
		
		List<Appointment> list= new ArrayList<Appointment>();
		Appointment appointment=null;
		try {
			conn = dbu.getConnection();
			String sql="SELECT * from appointment";
			pstmt=(PreparedStatement) conn.prepareStatement(sql);			
			rs = pstmt.executeQuery();
            while (rs.next()) {
            	Timestamp startStamp = rs.getTimestamp(2);
            	Timestamp endStamp = rs.getTimestamp(3);
            	
            	appointment = new Appointment(rs.getInt(1),new Date(startStamp.getTime()),new Date(endStamp.getTime()),rs.getInt(4),rs.getInt(5));
            	list.add(appointment);
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
	public Appointment getLatestAppointment() {
		Appointment appointment=null;

		try {
			Connection conn=DBUtil.getConnection();
			String sql="SELECT * FROM appointment ORDER BY start DESC";
			pstmt=(PreparedStatement) conn.prepareStatement(sql);	
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				Timestamp startStamp = rs.getTimestamp(2);
				Timestamp endStamp = rs.getTimestamp(3);

				appointment = new Appointment(rs.getInt(1),new Date(startStamp.getTime()),new Date(endStamp.getTime()),
											rs.getInt(4),rs.getInt(5));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, pstmt, conn);
		}
		return appointment;
	}
	
	
	

	@Override
	public void setEndTime(Date time) {
		// TODO Auto-generated method stub
		
		
		
		
	}
	
}
