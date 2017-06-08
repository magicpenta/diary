package com.diary.servlet;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.diary.dao.DiaryDao;
import com.diary.model.Diary;
import com.diary.tools.MyPagination;

/**
 * 日记相关 Servlet
 * @author panda
 *
 */
public class DiaryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MyPagination pagination = null;
	private DiaryDao diaryDao = null;
	
	public DiaryServlet()
	{
		super();
		diaryDao = new DiaryDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if ( "preview".equals(action) )
		{
			this.preview(request, response);
		}
		else if ( "listAllDiary".equals(action) )
		{
			this.listAllDiary(request, response);
		}
		else if ( "listMyDiary".equals(action) )
		{
			this.listMyDiary(request, response);
		}
		else if ( "delDiary".equals(action) )
		{
			this.delDiary(request, response);
		}
		else if ( "save".equals(action) )
		{
			this.save(request, response);
		}
	}
	
	/**
	 * 进入日记列表页
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void listAllDiary(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String strPage = request.getParameter("Page");	// 获取当前页码
		int Page = 1;
		List<Diary> list = null;
		if ( strPage == null )
		{
			String sql = "select d.*,u.userName from tb_diary d inner join tb_user u on d.userID=u.id "
					+ "order by d.writeTime DESC";
			pagination = new MyPagination();
			list = diaryDao.queryDiary(sql);	// 获取日记信息
			int pageSize = 4;
			list = pagination.getInitPage(list, Page, pageSize);	// 初始化第一分页
			request.getSession().setAttribute("pagination", pagination);
		} else {
			pagination = (MyPagination)request.getSession().getAttribute("pagination");
			Page = pagination.getPage(strPage);
			list = pagination.getAppointPage(Page);		// 获取指定的分页
		}
		request.setAttribute("diaryList", list);
		request.setAttribute("Page", Page);
		request.setAttribute("url", "listAllDiary");
		request.getRequestDispatcher("listAllDiary.jsp").forward(request, response);
	}
	
	/**
	 * 进入我的日记列表页
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void listMyDiary(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String strPage = request.getParameter("Page");
		int Page = 1;
		List<Diary> list = null;
		if  ( strPage == null )
		{
			int userid = Integer.parseInt(session.getAttribute("uid").toString());
			String sql = "select d.*,u.userName from tb_diary d inner join tb_user u on d.userID=u.id "
					+ "where u.id="+userid+" order by d.writeTime DESC";
			pagination = new MyPagination();
			list = diaryDao.queryDiary(sql);	// 获取我的日记信息
			int pageSize = 4;
			list = pagination.getInitPage(list, Page, pageSize);
			session.setAttribute("pagination", pagination);
		} else {
			pagination = (MyPagination)session.getAttribute("pagination");
			Page = pagination.getPage(strPage);
			list = pagination.getAppointPage(Page);
		}
		request.setAttribute("diaryList", list);
		request.setAttribute("Page", Page);
		request.setAttribute("url", "listMyDiary");
		request.getRequestDispatcher("listAllDiary.jsp").forward(request, response);
	}
	
	/**
	 * 删除日记
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void delDiary(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String imgName = request.getParameter("imgName");
		String url = request.getParameter("url");
		int result = diaryDao.delDiary(id);
		PrintWriter pw = response.getWriter();
		if ( result > 0 )	// 删除成功时
		{
			String path = getServletContext().getRealPath("\\")+"images\\diary\\";
			File file = new File(path+imgName+"scale.jpg");
			file.delete();
			file = new File(path+imgName+".png");
			file.delete();
			pw.print("<script>alert('删除日记成功！');window.location.href='DiaryServlet?action="+url+"';</script>");
		} else {
			pw.print("<script>alert('删除日记失败！请稍后重试');history.back();</script>");
		}
	}
	
	/**
	 * 预览日记页面
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void preview(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("div_title");
		String template = request.getParameter("template");
		String[] content = request.getParameterValues("content");
		// 设置未设置内容的默认值
		for ( int i = 0; i<content.length; i++ )
		{
			if ( content[i].equals(null) || content[i].equals("") )
			{
				content[i] = "什么都没有";
				
			}
		}
		HttpSession session = request.getSession();
		session.setAttribute("title", title);
		session.setAttribute("template", template);
		session.setAttribute("content", content);
		request.getRequestDispatcher("preview.jsp").forward(request, response);
	}
	
	/**
	 * 保存日记页面
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		BufferedImage image = (BufferedImage)session.getAttribute("diaryImg");
		String url = request.getServletContext().getRealPath("/");
		File imgFile = new File(url+"images/diary");
		if ( !imgFile.exists() )
		{
			imgFile.mkdirs();
		}
		long date = new Date().getTime();
		Random r = new Random(date);
		long value = r.nextLong();	// 生成随机数
		url = url + "images/diary/" + value;
		String scaleImgUrl = url + "scale.jpg";	// 生成缩略图URL地址
		url = url + ".png";	// 生成原图URL地址
		ImageIO.write(image, "PNG", new File(url));
		
		File file = new File(url);
		Image src = ImageIO.read(file);
		int old_w = src.getWidth(null);
		int old_h = src.getHeight(null);
		int new_w = 0;
		int new_h = 0;
		double temp = 0;	// 缩放比例
		
		double tagSize = 200;
		if ( old_w > old_h )
		{
			temp = old_w/tagSize;
		}
		else
		{
			temp = old_h/tagSize;
		}
		
		new_w = (int) Math.round(old_w/temp);
		new_h = (int) Math.round(old_h/temp);
		image = new BufferedImage(new_w, new_h, BufferedImage.TYPE_INT_RGB);
		src = src.getScaledInstance(new_w, new_h, Image.SCALE_SMOOTH);
		image.getGraphics().drawImage(src, 0, 0, new_w, new_h, null);
		ImageIO.write(image, "JPG", new File(scaleImgUrl));
		
		Diary diary = new Diary();
		diary.setAddress(String.valueOf(value));
		diary.setTitle(session.getAttribute("title").toString());
		diary.setUserId(Integer.parseInt(session.getAttribute("uid").toString()));
		int result = diaryDao.saveDiary(diary);
		PrintWriter pw = response.getWriter();
		if ( result > 0 )
		{
			pw.println("<script>alert('保存成功！');window.location.href='DiaryServlet?action=listAllDiary';</script>");
		}
		else
		{
			pw.print("<script>alert('保存日记失败！请稍后重试');history.back();</script>");
		}
	}
}
