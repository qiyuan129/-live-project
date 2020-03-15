package model;

import java.util.Date;

/**
 * @ClassName Reigster
 * @Description TODO
 * @Author mingll
 * @Date 2020/3/15 1:59 下午
 * @Version 1.0
 */
public class Register {
    int id;
    int userID;
    int mask;
    int appointment;
    Date time;

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
