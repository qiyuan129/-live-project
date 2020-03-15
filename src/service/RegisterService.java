package service;


import dao.UserDao;
import dao.UserDaoImpl;
import model.Register;
import model.User;

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

    public RegisterService(String userName, String userIdentity, String userPhone,int appointmentID){
        UserDao userDao = new UserDaoImpl();
        this.user = userDao.selectByIdentity(userIdentity);
        if (this.user == null){
            this.user = new User();

        }
    }
}
