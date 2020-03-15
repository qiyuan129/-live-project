package dao;

import model.Appointment;

import java.util.List;

/**
 * @ClassName Appointment
 * @Description TODO
 * @Author mingll
 * @Date 2020/3/15 2:01 下午
 * @Version 1.0
 */
public interface AppointmentDao {
    //增加新的预约信息
    void addAppointment(Appointment appointment);
    //查找所有预约信息
    List<Appointment> select();
    //根据用户id查找预约信息
    Appointment selectByUserID(int id);
    //根据id删除预约信息
    void deleteByID(int id);
}
