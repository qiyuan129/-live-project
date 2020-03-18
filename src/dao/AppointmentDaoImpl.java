package dao;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import model.Appointment;
import util.DBUtil;

public class AppointmentDaoImpl implements AppointmentDao{
	Connection conn=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	
	
	
	
	@Override
	public void addAppointment(Date begin, Date end, int totalMask, int maskMAX) {
		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        String beginString = df.format(begin);
	        String endString = df.format(end);
			
			conn =DBUtil.getConnection();
			String sql="insert into appointment(start , end , mask , maskMAX) value(?,?,?,?)";
			ps=(PreparedStatement) conn.prepareStatement(sql);
			ps.setObject(1, beginString);
			ps.setObject(2, endString);
			ps.setInt(3, totalMask);
			ps.setInt(4,maskMAX);
			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			DBUtil.close(rs,ps,conn);
		}
		
	}

	@Override
	public void addAdminAppointment(String beginString, String endString, int totalMask, int maskMAX) {
		try {
			conn =DBUtil.getConnection();
			String sql="insert into appointment(start , end , mask , maskMAX) value(?,?,?,?)";
			ps=(PreparedStatement) conn.prepareStatement(sql);
			ps.setObject(1, beginString);
			ps.setObject(2, endString);
			ps.setInt(3, totalMask);
			ps.setInt(4,maskMAX);
			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			DBUtil.close(rs,ps,conn);
		}

	}


	@Override
	public void addAppointment(Date begin, Date end) {
		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        String beginString = df.format(begin);
	        String endString = df.format(end);
			
			conn = DBUtil.getConnection();
			String sql="insert into appointment(start , end , mask , maskMAX) value(?,?,?,?)";
			ps=(PreparedStatement) conn.prepareStatement(sql);
			ps.setObject(1, beginString);
			ps.setObject(2, endString);
			ps.setInt(3, 1000);
			ps.setInt(4,3);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			DBUtil.close(rs,ps,conn);
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
			
			conn = DBUtil.getConnection();
			String sql="insert into appointment(start , end , mask , maskMAX) value(?,?,?,?)";
			ps=(PreparedStatement) conn.prepareStatement(sql);
			ps.setObject(1, beginString);
			ps.setObject(2, endString);
			ps.setInt(3, 1000);
			ps.setInt(4,3);
			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			DBUtil.close(rs,ps,conn);
		}
	}

	@Override
	public Appointment getAppointment(int id) {
		Appointment appointment=null;
		try {
			conn =DBUtil.getConnection();
			String sql="SELECT * from appointment WHERE id=?";
			ps=(PreparedStatement) conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
            while (rs.next()) {
            	Timestamp startStamp = rs.getTimestamp(2);
            	Timestamp endStamp = rs.getTimestamp(3);
            	
            	appointment = new Appointment(rs.getInt(1),new Date(startStamp.getTime()),new Date(endStamp.getTime()),rs.getInt(4),rs.getInt(5));
            }

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			DBUtil.close(rs,ps,conn);
		}
		return appointment;
	}

	@Override
	public List<Appointment> getAppointmentList() {
		
		List<Appointment> list= new ArrayList<Appointment>();
		Appointment appointment=null;
		try {
			conn =DBUtil.getConnection();
			String sql="SELECT * from appointment";
			ps=(PreparedStatement) conn.prepareStatement(sql);
			rs = ps.executeQuery();
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
		finally {
			DBUtil.close(rs,ps,conn);
		}
		return list;
		
	}

	@Override
	public void setEndTime(int id,Date time) {
		Appointment appointment=null;
		String sql="UPDATE appointment SET end=? where id=?";
		try{
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String endString = df.format(time);

			conn=DBUtil.getConnection();
			ps=conn.prepareStatement(sql);
			ps.setObject(1,endString);
			ps.setInt(2,id);

			int rsCount=ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			DBUtil.close(rs,ps,conn);
		}


	}

	@Override
	public void setMaskNumber(int id, int number) {
		Appointment appointment=null;
		String sql="UPDATE appointment SET mask=? where id=?";
		try{

			conn=DBUtil.getConnection();
			ps=conn.prepareStatement(sql);
			ps.setInt(1,number);
			ps.setInt(2,id);

			int rsCount=ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			DBUtil.close(rs,ps,conn);
		}
	}

	@Override
	public Appointment getLatestAppointment() {
		Appointment appointment=null;

		try {
			conn=DBUtil.getConnection();
			ps= (PreparedStatement) conn.createStatement();

			String sql="SELECT * FROM appointment ORDER BY start DESC";
			rs=ps.executeQuery(sql);

			if(rs.next()){
				Date startStamp = rs.getTimestamp(2);
				Date endStamp = rs.getTimestamp(3);

				appointment = new Appointment(rs.getInt(1),startStamp,endStamp,
											rs.getInt(4),rs.getInt(5));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			DBUtil.close(rs,ps,conn);
		}
		return appointment;
	}
}
