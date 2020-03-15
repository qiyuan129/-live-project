package dao;

import model.Appointment;
import model.Register;

import java.util.List;

/**
 * @ClassName Reigster
 * @Description TODO
 * @Author mingll
 * @Date 2020/3/15 2:01 下午
 * @Version 1.0
 */
public interface RegisterDao {
    /**
     * 新增预约信息
     * @param register
     */
    void addRegister(Register register);

    /**
     * 查找所有信息
     * @return 所有预约信息
     */
    List<Register> select();
    //根据用户id查找预约信息

    /**
     * 根据用户id查找预约信息
     * @param id
     * @return 若有返回Appointment 没有null
     */
    Register selectByUserID(int id);

    /**
     * 根据预约信息id删除预约信息
     * @param id
     */
    void deleteByID(int id);

}
