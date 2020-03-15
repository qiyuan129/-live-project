package service;


import dao.*;
import model.Appointment;
import model.Register;
import model.Selection;
import model.User;


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
        this.register = new Register();
        this.register.setTime(new Date());
        int currentAppointmentID = getCurrentAppointmentID();
        UserDao userDao = new UserDaoImpl();
        if (isRepead(userIdentity,currentAppointmentID)){
            return -1;
        }
        if (!UserService.isIDValidator(userIdentity)||UserService.isTruePhone(userPhone)) {
            return -1;
        }
        if (this.user == null) {
            this.user.setName(userName);
            this.user.setIdentity(userIdentity);
            this.user.setPhone(userPhone);
            userDao.add(this.user);
        }
        if (this.isSucceed()) {
            User temp = userDao.selectByIdentityAndCurrentAPPID(userIdentity,currentAppointmentID);
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
        if ((currentId - getLastSelectionOfAppID()) > 3) {
            return true;
        }
        return false;
    }

    private int getCurrentAppointmentID(){
        int currentID = 0;
        AppointmentDao appointmentDAO = new AppointmentDaoImpl();
        List<Appointment> appointments = appointmentDAO.getAppointmentList();
        for(Appointment appointment : appointments){
            if (this.register.getTime().after(appointment.getStart())&&this.register.getTime().before(appointment.getEnd())){
                currentID = appointment.getId();
            }
        }
        return currentID;
    }

    private int getLastSelectionOfAppID(){
        SelectionDao selectionDao = new SelectionDaoImpl();
        Selection selection = selectionDao.selectByID(this.user.getLastSelectionID());
        return selection.getAppointmentID();
    }

    private boolean isRepead(String identity, int currentAppID){
        UserDao userDao = new UserDaoImpl();
        List<User> users = userDao.selectByIdentity(identity);
        for (User user : users){
            if (user.getAppointmentID() == currentAppID){
                return true;
            }
        }
        return false;
    }
}

