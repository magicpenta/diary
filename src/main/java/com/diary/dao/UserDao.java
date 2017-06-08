package com.diary.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.diary.model.User;
import com.diary.tools.ConnDB;

/**
 * 用户实体类数据操作对象
 * @author panda
 *
 */
public class UserDao {
	
	private ConnDB connDB = null;
	
	public UserDao()
	{
		connDB = new ConnDB();
	}
	
	/**
	 * 检测用户是否已被注册
	 * @param sql
	 * @return
	 */
	public String checkUser(String sql)
	{
		ResultSet rs = connDB.executeQuery(sql);
		String result = "";
		try {
			if ( rs.next() )
			{
				result = "该账号已被注册！";
			}
			else
			{
				result = "1"; // 表示用户没有被注册
			}
		} catch (SQLException e) {
			e.getStackTrace();
		} finally {
			connDB.close();
		}
		return result;
	}
	
	/**
	 * 保存注册用户信息
	 * @param sql
	 * @return
	 */
	public String save(String sql)
	{
		int rs = connDB.executeUpdate(sql);
		String result = "";
		if ( rs > 0 )
		{
			result = "用户注册成功！";
		}
		else
		{
			result = "用户注册失败！";
		}
		connDB.close();
		return result;
	}
	
	/**
	 * 判断用户登录
	 * @param user
	 * @return
	 */
	public int login(User user)
	{
		int flag = 0;	// 判断登录是否成功的标志
		String sql = "select * from tb_user where userName='"+user.getUserName()+"'";
		ResultSet rs = connDB.executeQuery(sql);
		try {
			if ( rs.next() )
			{
				String pwd = user.getPwd();
				int id = rs.getInt(1);
				if ( pwd.equals(rs.getString("pwd")) )
				{
					flag = id;
					rs.last();
					int rowSum = rs.getRow();
					rs.first();
					if ( rowSum != 1 )
					{
						flag = 0;
					}
				}
				else
				{
					flag = 0;
				}
			}
			else
			{
				flag = 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			flag = 0;
		} finally {
			connDB.close();
		}
		return flag;
	}
	
	/**
	 * 获取密码提示问题
	 * @param userName
	 * @return
	 */
	public String getAnswer(String userName)
	{
		String result = "";
		String sql = "select question from tb_user where userName='"+userName+"'";
		ResultSet rs = connDB.executeQuery(sql);
		try {
			if ( rs.next() )
			{
				result = rs.getString("question");
			}
			else
			{
				result = "您输入的用户名不存在！";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			result = "您输入的用户名不存在！";
		} finally {
			connDB.close();
		}
		return result;
	}

	/**
	 * 根据密码提示问题获得密码
	 * @param userName
	 * @param question
	 * @param answer
	 * @return
	 */
	public String getPwd(String userName, String question, String answer) {
		String result = "";
		String sql = "select pwd from tb_user where userName='"+userName+"' and question='"+question
				+"' and answer='"+answer+"'";
		ResultSet rs = connDB.executeQuery(sql);
		try {
			if ( rs.next() )
			{
				result = rs.getString("pwd");
			}
			else
			{
				result = "您输入的密码提示问题答案错误！";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = "您输入的密码提示问题答案错误！";
		} finally {
			connDB.close();
		}
		return result;
	}

}
