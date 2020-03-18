package service;

import dao.*;
import model.Appointment;
import model.Register;
import model.Selection;
import model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @ClassName SelectionService
 * @Description TODO
 * @Author mingll
 * @Date 2020/3/15 2:03 下午
 * @Version 1.0
 */
public class SelectionService {
    /**
     * 产生中签
     */
    public void createSelection() {
        List<Integer> integers = new ArrayList<>();
        AppointmentDaoImpl appointDao = new AppointmentDaoImpl();//获取一个AppointmentDaoImpl对象
        //得到最新预约对象，里面包含预约时间段等数据
        Appointment lastAppointment = appointDao.getLatestAppointment();
        RegisterDaoImpl registerDao = new RegisterDaoImpl();
        //获取候选者列表 条件 register.预约表id= lastAppointment.id
        List<Register> candidate = registerDao.getByAppointmentID(lastAppointment.getId());
        int min = getMinRegisterID(candidate);
        int max = getMaxRegisterID(candidate);
        for (int i = 0; i < 2; i++){
            int random = random(min, max);
            if (isExist(integers,random)){
                i--;
                continue;
            }
            integers.add(random);
            for (Register r : candidate) {
                if (r.getId() == random){
                    Selection selection = new Selection();
                    selection.setRegisterID(r.getId());
                    selection.setAppointmentID(r.getAppointment());
                    selection.setUserID(r.getUserID());
                    selection.setTime(r.getTime());
                    SelectionDao selectionDao = new SelectionDaoImpl();
                    selectionDao.add(selection);
                    UserDao userDao = new UserDaoImpl();
                    User user = userDao.selectByID(r.getUserID());
                    Selection temp =  selectionDao.findByRegisterID(r.getId());
                    user.setLastSelectionID(temp.getId());
                    userDao.updateUser(user);
                }
            }
        }
    }

    public User isExistSelection(int registerID) {
        User user = null;
        SelectionDao selectionDao = new SelectionDaoImpl();
        Selection selection = selectionDao.findByRegisterID(registerID);
        if (selection != null) {
            UserDao userDao = new UserDaoImpl();
            user = userDao.selectByID(selection.getUserID());
        }
        return user;
    }

    private int getMinRegisterID(List<Register> list) {
        int min = 999999999;
        for (Register r : list) {
            if (min > r.getId()) {
                min = r.getId();
            }
        }
        return min;
    }

    private int getMaxRegisterID(List<Register> list) {
        int max = 0;
        for (Register r : list) {
            if (max < r.getId()) {
                max = r.getId();
            }
        }
        return max;
    }

    private int random(int a, int b){
        Random random = new Random();
        int range = b - a;
        return random.nextInt(range) + a;
    }

    private boolean isExist(List<Integer> list, int i){
        if (list.contains(i)){
            return true;
        }
        return false;
    }

}
