<<<<<<< HEAD:src/dao/AppointmentDAO.java
package Dao;
=======
package dao;
>>>>>>> 70f126be75391b80bae63d23c57ef7f95a4532cf:src/dao/AppointmentDao.java

import java.util.Date;
import java.util.List;

import model.Appointment;
import model.Register;

public interface AppointmentDao {
	/**
     * 新建一次预约项目
     * @param begin
     * @param end
     * @param totalMask 口罩总数
     * @param maskMAX 单人可预约的最大数
     */
    void addAppointment(Date begin,Date end,int totalMask,int maskMAX);

    /**
     * 新建一次预约项目(口罩总数默认1000，单人默认最多3个)
     * @param begin
     * @param end
     */
    void addAppointment(Date begin,Date end);

    /**
     * （用户界面测试用）新建一次预约，口罩总数默认1000，单人3个，开始时间当前系统时间，结束时间一个月后
     */
    void addAppointment();

    /**
     * 获取预约的详细情况
     * @param id
     */
    Appointment getAppointment(int id);

    /**
     * 获取所有预约项目表
     * @return
     */
    List<Appointment> getAppointmentList();

    /**
     * 修改一个预约的结束时间
     * @param time
     */
    void setEndTime(Date time);

    /*
     * 返回最新预约
     */
	Appointment getLatestAppointment();

	
}
