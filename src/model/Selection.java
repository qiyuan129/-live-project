package model;

import java.util.Date;

/**
 * @ClassName Selection
 * @Description TODO
 * @Author mingll
 * @Date 2020/3/15 1:59 下午
 * @Version 1.0
 */
public class Selection {
    int id;
    int userID;
    int registerID;
    Date time;
    int appointmentID;
    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getUserID() {
		return userID;
	}
	
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	public int getRegisterID() {
		return registerID;
	}
	
	public void setRegisterID(int registerID) {
		this.registerID = registerID;
	}
	
	public Date getTime() {
		return time;
	}
	
	public void setTime(Date time) {
		this.time = time;
	}
	
	public int getAppointmentID() {
		return appointmentID;
	}

	public void setAppointmentID(int appointmentID) {
		this.appointmentID = appointmentID;
	}
	
}
