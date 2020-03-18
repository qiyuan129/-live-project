package dao;

import model.Admin;

/**
 * @ClassName AdminDao
 * @Description TODO
 * @Author mingll
 * @Date 2020/3/15 2:00 下午
 * @Version 1.0
 */
public interface AdminDao {
	//管理员登陆
	void adminLogin(String id, String password);
}
