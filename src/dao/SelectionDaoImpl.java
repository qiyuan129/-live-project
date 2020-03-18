package dao;

import model.Selection;

/**
 * @ClassName SelectionDaoImpl
 * @Description TODO
 * @Author mingll
 * @Date 2020/3/15 2:52 下午
 * @Version 1.0
 */
public class SelectionDaoImpl implements SelectionDao {

    /**
     *
     * @param obj 要插入的中签记录对象
     * @return 若插入成功则true 否则false
     */
    public  boolean add(Selection obj){
        Connection conn = null;
        try {
           /* INSERT INTO table_name (column1,column2,column3,...)
            VALUES (value1,value2,value3,...);*/
            conn = DBUtil.getConnection();
            String sql = "INSERT  INTO selection (id,userID,registerID,time,appointmentID)" +
                    " VALUES (?,?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);;

            stmt.setString(1,obj.id);
            stmt.setString(2,String.valueOf(bean.getId()));

            stmt.executeUpdate();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }



    /**
     * @param indetityNumber 身份证号码
     * @return 返回表中这个身份证所有的记录
     */
    public List<Selection> findTotal(String indetityNumber){


    }

    /**
     * @param indetityNumber 身份证号码
     * @return 若中签表中存在这个人的记录则返回true 否则返回false
     */
    public boolean isExist(String indetityNumber){


    }

    /**
     * @param appointmentID 某次预约表ID
     * @return 中签名单
     */
    public List<String> importSelectedList(int appointmentID){

    }

    @Override
    public Selection selectByID(int id) {
        return null;
    }


}
