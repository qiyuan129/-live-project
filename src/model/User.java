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
    String appointmentID;

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

    public String getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(String appointmentID) {
        this.appointmentID = appointmentID;
    }
}
