package service;


import dao.*;
import model.Appointment;
import model.Register;
import model.Selection;
import model.User;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    public int sendMessage(String userName, String userIdentity, String userPhone, int num) {
        this.user = new User();
        this.register = new Register();
        this.register.setTime(new Date());
        int currentAppointmentID = getCurrentAppointmentID();
        UserDao userDao = new UserDaoImpl();
        if (isRepead(userIdentity, currentAppointmentID)) {
            return -1;
        }
        if (!UserService.isIDValidator(userIdentity)||UserService.isTruePhone(userPhone)) {
            return -1;
        }

        this.user = new User();
        this.user.setName(userName);
        this.user.setIdentity(userIdentity);
        this.user.setPhone(userPhone);
        this.user.setAppointmentID(currentAppointmentID);
        if (this.isSucceed()) {
            userDao.add(this.user);
            User temp = userDao.selectByIdentityAndCurrentAPPID(userIdentity, currentAppointmentID);
            this.register.setUserID(temp.getId());
            this.register.setMask(num);
            this.register.setAppointment(currentAppointmentID);
            RegisterDao registerDao = new RegisterDaoImpl();
            registerDao.addRegister(this.register);
            Register te = registerDao.selectByUserID(temp.getId());
            return te.getId();
        }
        return -1;
    }

    private boolean isSucceed() {
        int currentId = getCurrentAppointmentID();
        List<User> users = getUsers();
        if (users.isEmpty()) {
            return true;
        }
        for (User u : users) {
            if (u.getAppointmentID() - currentId < 3) {
                return false;
            }
        }
        return true;
    }

    private int getCurrentAppointmentID() {
        int currentID = 0;
        AppointmentDao appointmentDAO = new AppointmentDaoImpl();
        List<Appointment> appointments = appointmentDAO.getAppointmentList();
        for (Appointment appointment : appointments) {
            if (this.register.getTime().after(appointment.getStart()) && this.register.getTime().before(appointment.getEnd())) {
                currentID = appointment.getId();
            }
        }
        return currentID;
    }

    private List<User> getUsers() {
        SelectionDao selectionDao = new SelectionDaoImpl();
        UserDao userDao = new UserDaoImpl();
        List<User> users = userDao.selectByIdentity(this.user.getIdentity());
        List<User> returnUsers = new ArrayList<>();
        for (User u : users) {
            if (u.getLastSelectionID() != 0) {
                returnUsers.add(u);
            }
        }
        return returnUsers;
    }

    private boolean isRepead(String identity, int currentAppID) {
        UserDao userDao = new UserDaoImpl();
        List<User> users = userDao.selectByIdentity(identity);
        for (User user : users) {
            if (user.getAppointmentID() == currentAppID) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        RegisterService service = new RegisterService();
        service.sendMessage("123", "123", "123", 1);
    }

}

