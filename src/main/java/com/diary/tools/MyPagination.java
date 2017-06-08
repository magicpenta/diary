package com.diary.tools;

import java.util.ArrayList;
import java.util.List;

import com.diary.model.Diary;

/**
 * 分页效果工具
 * @author panda
 *
 */
public class MyPagination {
	
	public List<Diary> list = null;
	private int recordCount = 0; // 保存记录总数的变量
	private int pageSize = 0; // 保存每页显示的记录数的变量
	private int maxPage = 0; // 保存最大页数的变量
	
	public List<Diary> getInitPage(List<Diary> list, int Page, int pageSize)
	// 初始化分页信息
	{
		List<Diary> newList = new ArrayList<Diary>();
		this.list = list;
		recordCount = list.size();
		this.pageSize = pageSize;
		this.maxPage = getMaxPage();
		try{
			for ( int i = (Page-1)*pageSize; i <= Page*pageSize-1; i++ )
			{
				try{
					if ( i >= recordCount )
					// 在list中，下标最大值为recordCount-1
					{
						break;
					}
				} catch ( Exception e ) {
					// ignore
				}
				newList.add(list.get(i));
			}
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		return newList;
	}

	public List<Diary> getAppointPage(int Page)
	{
		List<Diary> newList = new ArrayList<Diary>();
		try{
			for ( int i = (Page-1)*pageSize; i <= Page*pageSize-1; i++ )
			{
				try{
					if ( i >= recordCount )
					// 在list中，下标最大值为recordCount-1
					{
						break;
					}
				} catch ( Exception e ) {
					// ignore
				}
				newList.add(list.get(i));
			}
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		return newList;
	}
	
	private int getMaxPage() 
	{
		int maxPage = ( recordCount%pageSize == 0 )?( recordCount/pageSize ):( recordCount/pageSize+1 );
//		相当于
//		if ( recordCount%pageSize == 0 )
//		{
//			maxPage = recordCount/pageSize;
//		}
//		else
//		{
//			maxPage = recordCount/pageSize+1;
//		}
		return maxPage;
	}
	
	public int getRecordCount()
	{
		return recordCount;
	}
	
	public int getPage(String str)
	{
		if ( str == null )
		{
			str = "0";
		}
		int Page = Integer.parseInt(str);
		if ( Page < 1 )
		{
			Page = 1;
		}
		else if ( ((Page-1)*pageSize+1) > recordCount )
		{
			Page = maxPage;
		}
		return Page;
	}
	
	public String printCtrl(int Page, String url, String para)
	{
		String strHtml = "<table width='100%' border='0' cellspacing='0' cellpadding='0'>"
					+"<tr><td height='24' align='center'> "
					+"当前页数：【"+Page+"/"+maxPage+"】&nbsp;";
		if ( Page > 1 )
		{
			strHtml+="<a href='"+url+"&Page=1"+para+"'>第一页</a>  ";
			strHtml+="<a href='"+url+"&Page="+(Page-1)+para+"'>上一页</a>";
		}
		if ( Page < maxPage )
		{
			strHtml+="<a href='"+url+"&Page="+(Page+1)+para+"'>下一页</a>  ";
			strHtml+="<a href='"+url+"&Page="+maxPage+para+"'>最后一页&nbsp;</a>";
		}
		strHtml+="</td></tr>    </table>";
		return strHtml;
	}
}
