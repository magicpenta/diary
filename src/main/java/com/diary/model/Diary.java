package com.diary.model;

import java.util.Date;

/**
 * 日记实体类
 * @author panda
 *
 */
public class Diary {
	private int id = 0;
	private String title = "";
	private String address = "";
	private Date writeTime = null;
	private int userId = 0;
	private String userName = "";
	
	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public String getAddress()
	{
		return address;
	}
	
	public void setAddress(String address)
	{
		this.address = address;
	}
	
	public Date getWriteTime()
	{
		return writeTime;
	}
	
	public void setWriteTime(Date writeTime)
	{
		this.writeTime = writeTime;
	}
	
	public int getUserId()
	{
		return userId;
	}
	
	public void setUserId(int userId)
	{
		this.userId = userId;
	}
	
	public String getUserName()
	{
		return userName;
	}
	
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
}
