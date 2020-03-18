package model;

/**
 * @ClassName User
 * @Description TODO
 * @Author mingll
 * @Date 2020/3/15 1:56 下午
 * @Version 1.0
 */

public class User {
    int id;
    String name;
    String identity;
    String phone;
    int appointmentID;
    int lastSelectionID;

	public int getLastSelectionID() {
		return lastSelectionID;
	}

	public void setLastSelectionID(int lastSelectionID) {
		this.lastSelectionID = lastSelectionID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getIdentity() {
		return identity;
	}
	
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public int getAppointmentID() {
		return appointmentID;
	}

	public void setAppointmentID(int appointmentID) {
		this.appointmentID = appointmentID;
	}
    
}
