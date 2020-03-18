package model;

import java.util.Date;

public class Register {
    int id;
    int userID;
    int mask;
    int appointment;
    Date time;

    public Register(int i,int u, int m, int a, Date t) {
    	this.id = i;
    	this.userID = u;
    	this.mask = m;
    	this.appointment = a;
    	this.time = t;
	}
    
    public Register(int u, int m, int a, Date t) {
    	this.id = 0;
    	this.userID = u;
    	this.mask = m;
    	this.appointment = a;
    	this.time = t;
	}

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

    public int getMask() {
        return mask;
    }

    public void setMask(int mask) {
        this.mask = mask;
    }

    public int getAppointment() {
        return appointment;
    }

    public void setAppointment(int appointment) {
        this.appointment = appointment;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
