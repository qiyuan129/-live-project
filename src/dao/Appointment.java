package dao;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @ClassName Appointment
 * @Description TODO
 * @Author mingll
 * @Date 2020/3/15 2:01 下午
 * @Version 1.0
 */
public interface Appointment {
    /**
     * 新建一次预约项目
     * @param begin
     * @param end
     * @param totalMask 口罩总数
     * @param maxRegister 单人可预约的最大数
     */
    void addAppointment(LocalDateTime begin,LocalDateTime end,int totalMask,int maxRegister);

    /**
     * 新建一次预约项目(口罩总数默认1000，单人默认最多3个)
     * @param begin
     * @param end
     */
    void addAppointment(LocalDateTime begin,LocalDateTime end);

    /**
     * （用户界面测试用）新建一次预约，口罩总数默认1000，单人3个，开始时间当前系统时间，结束时间一个月后
     */
    void addAppointment();

    /**
     * 获取预约的详细情况
     * @param id
     */
    void getAppointment(int id);
    /**
     * 获取数据库中最新一次预约的id
     * @return
     */
    int getLatestId();

    /**
     * 修改一个预约的结束时间
     * @param time
     */
    void setEndTime(LocalDateTime time);

}
