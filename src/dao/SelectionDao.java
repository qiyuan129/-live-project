package dao;

import model.Selection;
import model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Selection
 * @Description TODO
 * @Author mingll
 * @Date 2020/3/15 2:01 下午
 * @Version 1.0
 */
public interface SelectionDao {
    /**
     *
     * @param obj 要插入的中签记录对象
     *
     */
    public  void add(Selection obj);


    /**
     * @param array 要插入的Selection数组
     */
    public void addSelections(ArrayList<Selection> array);



    /**
     * @param indetityNumber 身份证号码
     * @return 返回表中这个身份证所有的记录
     */
    public List<Selection> findTotal(String indetityNumber);




    /**
     * @param appointmentID 某次预约表ID
     * @return 中签名单
     */
    public List<Selection> importSelectedList(int appointmentID);

    /**
     * @param id
     * @return Selection对象
     */
    public Selection selectByID(int id);


    /**
     * @param registerID 预约ID
     * @return 返回Selection
     */
    public Selection findByRegisterID(int registerID);



    /**
     * @param appointmentID 预约ID
     * @return
     */
    public User getUserByAppointmentID(int appointmentID);


    public void updateUserLastSelectionID( ArrayList<Selection> array);


}


