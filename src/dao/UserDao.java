package dao;

import model.User;

import java.util.List;

/**
 * @ClassName UserDao
 * @Description TODO
 * @Author mingll
 * @Date 2020/3/15 1:50 下午
 * @Version 1.0
 */
public interface UserDao {
    /**
     * 新增用户
     * @param user
     */
    void add(User user);

    /**
     * 查询所有用户信息
     * @return 所有用户信息
     */
    List<User> select();

    /**
     * 根据UserID查找信息
     * @param id
     * @return 若有返回User，没有返回null;
     */
    User selectByID(int id);

    /**
     * 根据身份证删除信息
     * @param identity
     */
    void deleteByIdentity(String identity);

    /**
     * 根据身份证号查询
     * @param identity
     * @return 若有返回User，没有返回null;
     */
    User selectByIdentity(String identity);

    /**
     * 根据身份证号更新User信息
     * @param identity
     * @param user
     */
    void updataByIdentity(String identity,User user);
}
