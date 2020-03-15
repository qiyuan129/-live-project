package dao;

import model.Appointment;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @ClassName ApponitmentDaoImpl
 * @Description TODO
 * @Author mingll
 * @Date 2020/3/15 4:22 下午
 * @Version 1.0
 */
public class AppointmentDaoImpl implements AppointmentDAO {
    @Override
    public void addAppointment(LocalDateTime begin, LocalDateTime end, int totalMask, int maxRegister) {

    }

    @Override
    public void addAppointment(LocalDateTime begin, LocalDateTime end) {

    }

    @Override
    public void addAppointment() {

    }

    @Override
    public void getAppointment(int id) {

    }

    @Override
    public List<Appointment> getAppointmentList() {
        return null;
    }

    @Override
    public void setEndTime(LocalDateTime time) {

    }
}
