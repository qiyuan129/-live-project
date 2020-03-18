package service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Admin;
import util.DBUtil;

/**
 * @ClassName AdminService
 * @Description TODO
 * @Author mingll
 * @Date 2020/3/15 2:02 下午
 * @Version 1.0
 */
public class AdminService {
	
	/*
	 * 管理员登录方法
	 */
	public static Admin adminLogin(String id, String password) {
		int i=0;
		Admin admin=new Admin();
        try (Connection c = DBUtil.getConnection(); Statement s = c.createStatement()) {
        	String sql = "select *  from admin where id='" + id + "' and password='" + password;
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
				String names = rs.getString(1);
				admin.setId(rs.getString("id"));
				admin.setPassword(rs.getString("password"));
				if (names != null) {
					i = 1;
				}
			}
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return admin;
	}
	
}
