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
    //增加新用户
    void add(User user);
    //筛选所有用户
    List<User> select();
    //根据用户ID查找
    User selectByID(int id);
    //删除用户
    void deleteById(int id);
}
