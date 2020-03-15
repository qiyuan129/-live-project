package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import model.Appointment;
import util.DBUtil;

public class AppointmentDaoImpl implements AppointmentDao{

	
	
	
	
	@Override
	public void addAppointment(Date begin, Date end, int totalMask, int maskMAX) {
		DBUtil dbu=new DBUtil();
		Connection conn=null;
		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        String beginString = df.format(begin);
	        String endString = df.format(end);
			
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
		DBUtil dbu=new DBUtil();
		Connection conn=null;
		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        String beginString = df.format(begin);
	        String endString = df.format(end);
			
			conn = dbu.getConnection();
			String sql="insert into appointment(start , end , mask , maskMAX) value(?,?,?,?)";
			PreparedStatement pstmt=(PreparedStatement) conn.prepareStatement(sql);
			pstmt.setObject(1, beginString);
			pstmt.setObject(2, endString);
			pstmt.setInt(3, 1000);
			pstmt.setInt(4,3);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void addAppointment() {
		DBUtil dbu=new DBUtil();
		Connection conn=null;
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
			PreparedStatement pstmt=(PreparedStatement) conn.prepareStatement(sql);
			pstmt.setObject(1, beginString);
			pstmt.setObject(2, endString);
			pstmt.setInt(3, 1000);
			pstmt.setInt(4,3);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Appointment getAppointment(int id) {
		DBUtil dbu=new DBUtil();
		Connection conn=null;
		Appointment appointment=null;
		try {
			conn = dbu.getConnection();
			String sql="SELECT * from appointment WHERE id=?";
			PreparedStatement pstmt=(PreparedStatement) conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
            	Timestamp startStamp = rs.getTimestamp(2);
            	Timestamp endStamp = rs.getTimestamp(3);
            	
            	appointment = new Appointment(rs.getInt(1),new Date(startStamp.getTime()),new Date(endStamp.getTime()),rs.getInt(4),rs.getInt(5));
            }

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return appointment;
	}

	@Override
	public List<Appointment> getAppointmentList() {
		
		List<Appointment> list= new ArrayList<Appointment>();
		DBUtil dbu=new DBUtil();
		Connection conn=null;
		Appointment appointment=null;
		try {
			conn = dbu.getConnection();
			String sql="SELECT * from appointment";
			PreparedStatement pstmt=(PreparedStatement) conn.prepareStatement(sql);			
			ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
            	Timestamp startStamp = rs.getTimestamp(2);
            	Timestamp endStamp = rs.getTimestamp(3);
            	
            	appointment = new Appointment(rs.getInt(1),new Date(startStamp.getTime()),new Date(endStamp.getTime()),rs.getInt(4),rs.getInt(5));
            	list.add(appointment);
            }

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}

	@Override
	public void setEndTime(Date time) {
		// TODO Auto-generated method stub

		
		
	}
	
}
