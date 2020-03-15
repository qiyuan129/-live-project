package service;


import com.sun.source.tree.IfTree;
import dao.*;
import model.Appointment;
import model.Register;
import model.User;


import java.util.Date;
import java.util.List;
import java.util.function.IntToDoubleFunction;

/**
 * @ClassName ReigsterService
 * @Description TODO
 * @Author mingll
 * @Date 2020/3/15 2:02 下午
 * @Version 1.0
 */
public class RegisterService {
    private User user;
    private Register register;

    /**
     * 发送预约信息类。
     *
     * @param userName
     * @param userIdentity
     * @param userPhone
     * @return 成功 true 不成功 false
     */
    public boolean sendMessage(String userName, String userIdentity, String userPhone, int num) {
        this.register = new Register();
        this.register.setTime(new Date());
        UserDao userDao = new UserDaoImpl();
        this.user = userDao.selectByIdentity(userIdentity);
        if (this.user == null) {
            this.user.setName(userName);
            this.user.setIdentity(userIdentity);
            this.user.setPhone(userPhone);
            userDao.add(this.user);
        }
        if (this.isSucceed()) {
            User temp = userDao.selectByIdentity(userIdentity);
            this.register.setUserID(temp.getId());
            this.register.setMask(num);
            RegisterDao registerDao = new RegisterDaoImpl();
            registerDao.addRegister(this.register);
            return true;
        }
        return false;
    }

    private boolean isSucceed() {
        int currentId = 0;
        AppointmentDAO appointmentDAO = new AppointmentDaoImpl();
        List<Appointment> appointments = appointmentDAO.getAppointmentList();
        for (Appointment a : appointments) {
            if (this.register.getTime().after(a.getStart()) && this.register.getTime().before(a.getEnd())) {
                currentId = a.getId();
            }
        }
        if ((currentId - Integer.parseInt(this.user.getAppointmentID())) > 3) {
            return true;
        }
        return false;
    }
}

