package dao;

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
     * @return 若插入成功则true 否则false
     */
    public  boolean add(Selection obj);

    /**
     * @param indetityNumber 身份证号码
     * @return 返回表中这个身份证所有的记录
     */
    public List<Selection> findTotal(String indetityNumber);
    /**
     * @param indetityNumber 身份证号码
     * @return 若中签表中存在这个人的记录则返回true 否则返回false
     */
    public boolean isExist(String indetityNumber);

    /**
     * @param appointmentID 某次预约表ID
     * @return 中签名单
     */
    public List<Selection> importSelectedList(int appointmentID);


}


