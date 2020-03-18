package service;

import dao.AppointmentDao;
import model.Appointment;
import java.util.Date;
import dao.AppointmentDaoImpl;
import java.util.List;

/**
 * @ClassName AppointmentService
 * @Description TODO
 * @Author mingll
 * @Date 2020/3/15 2:03 下午
 * @Version 1.0
 */
public class AppointmentService {
    public void endLatestAppointment(){
        AppointmentDao appointmentDao=new AppointmentDaoImpl();
        Appointment latestAppointment=appointmentDao.getLatestAppointment();
        Date currentDate=new Date();
        appointmentDao.setEndTime(latestAppointment.getId(),currentDate);
    }

    public int IsInAppointmentTime(){
        Date currentDate=new Date();
        AppointmentDao appointmentDao=new AppointmentDaoImpl();
        Appointment latestAppointment=appointmentDao.getLatestAppointment();
        Date start=latestAppointment.getStart();
        Date end=latestAppointment.getEnd();

        if(currentDate.compareTo(start)==1 && currentDate.compareTo(end)==-1){
            return latestAppointment.getId();
        }
        else{
            return -1;
        }
    }

}
