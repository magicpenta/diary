package com.diary.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.diary.model.Diary;
import com.diary.tools.ConnDB;

/**
 * 日记实体类数据操作对象
 * @author panda
 *
 */
public class DiaryDao {
	
	private ConnDB connDB = null;
	
	public DiaryDao()
	{
		connDB = new ConnDB();
	}

	/**
	 * 获取日记列表
	 * @param sql
	 * @return
	 */
	public List<Diary> queryDiary(String sql) {
		ResultSet rs = connDB.executeQuery(sql);
		List<Diary> list = new ArrayList<Diary>();
		
		try {
			while ( rs.next() )
			{
				Diary diary = new Diary();
				diary.setId(rs.getInt("id"));
				diary.setTitle(rs.getString("title"));
				diary.setAddress(rs.getString("address"));	// 获取图片地址
				Date date;
				try {
					date = DateFormat.getDateTimeInstance().parse(rs.getString("writeTime"));	// 获取写日记的时间
					diary.setWriteTime(date);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				diary.setUserId(rs.getInt("userID"));
				diary.setUserName(rs.getString("userName"));
				list.add(diary);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connDB.close();
		}
		return list;
	}
	
	/**
	 * 删除指定 ID 日记
	 * @param id
	 * @return
	 */
	public int delDiary(int id)
	{
		String sql = "delete from tb_diary where id="+id;
		int result = 0;
		try{
			result = connDB.executeUpdate(sql);
		} catch ( Exception e )
		{
			e.printStackTrace();
		} finally {
			connDB.close();
		}
		return result;
	}

	/**
	 * 保存日记
	 * @param diary
	 * @return
	 */
	public int saveDiary(Diary diary) {
		String sql = "insert into tb_diary(title, address, userID) values('"
						+diary.getTitle()+"','"+diary.getAddress()+"',"+diary.getUserId()+")";
		int result = connDB.executeUpdate(sql);
		connDB.close();
		return result;
	}
}
