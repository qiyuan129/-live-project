package Dao;

import java.util.Date;
import java.util.List;

import model.Register;

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

    /*
	 * 根据预约id返回登记结果集
	 */
	List<Register> getByAppointmentID(int id);

}
