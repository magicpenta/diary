package com.diary.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.diary.dao.UserDao;
import com.diary.model.User;

/**
 * 用户相关 Servlet
 * @author panda
 *
 */
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao = null;
       
    public UserServlet() {
        super();
        userDao = new UserDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		if ( "login".equals(action) )
		{
			this.login(request, response);
		}
		else if ( "checkUser".equals(action) )
		{
			this.checkUser(request, response);
		}
		else if ( "save".equals(action) )
		{
			this.save(request, response);
		}
		else if ( "exit".equals(action) )
		{
			this.exit(request, response);
		}
		else if ( "getAnswer".equals(action) )
		{
			this.getAnswer(request, response);
		}
		else if ( "getPwd".equals(action) )
		{
			this.getPwd(request, response);
		}
		
	}

	/**
	 * 检测用户是否已注册
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void checkUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("userName");
		String sql = "select * from tb_user where userName ='"+userName+"'";
		String result = userDao.checkUser(sql);
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		pw.print(result);
		pw.flush();
		pw.close();
	}
	
	/**
	 * 保存注册用户信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("userName");
		String pwd = request.getParameter("pwd");
		String email = request.getParameter("email");
		String question = request.getParameter("question");
		String answer = request.getParameter("answer");
		String sql = "insert into tb_user(userName,pwd,email,question,answer) value('"+userName
				+"','"+pwd+"','"+email+"','"+question+"','"+answer+"')";
		String result = userDao.save(sql);
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		pw.print(result);
		pw.flush();
		pw.close();
	}
	
	/**
	 * 登录操作
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void login (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		String userName = request.getParameter("userName");
		String pwd = request.getParameter("pwd");
		user.setUserName(userName);
		user.setPwd(pwd);
		int result = userDao.login(user);
		if ( result > 0 )
		{
			HttpSession session = request.getSession();
			session.setAttribute("userName", userName);
			session.setAttribute("uid", result);
			request.setAttribute("returnValue", "登录成功！");
			request.getRequestDispatcher("userMessage.jsp").forward(request, response);// 重定向页面
		} else {
			request.setAttribute("returnValue", "您输入的用户名或密码错误！请重新输入！");
			request.getRequestDispatcher("userMessage.jsp").forward(request, response);
		}
	}
	
	/**
	 * 退出操作
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void exit (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
	
	/**
	 * 找回密码提示答案
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getAnswer (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("userName");
		String question = userDao.getAnswer(userName);
		System.out.println("getAnswer中的userName="+userName);
		PrintWriter pw = response.getWriter();
		if ("".equals(question)||question==null)
		{
			pw.print("<script>alert('您没有设置密码提示问题，无法找回！');history.back();</script>");
		} else if ("您输入的用户名不存在！".equals(question))
		{
			pw.print("<script>alert('您输入的用户名不存在！');history.back();</script>");
		} else {
			request.setAttribute("question", question);
			request.setAttribute("userName", userName);
			request.getRequestDispatcher("getPwd.jsp").forward(request, response);
		}
	}
	
	/**
	 * 找回密码
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getPwd (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("userName");
		String question = request.getParameter("question");
		String answer = request.getParameter("answer");
		
		String pwd = userDao.getPwd(userName, question, answer);
		PrintWriter pw = response.getWriter();
		if ( "您输入的密码提示问题答案错误！".equals(pwd) )
		{
			pw.print("<script>alert('您输入的密码提示问题答案错误！');history.back();</script>");
		} else {
			pw.print("<script>alert('您的密码是：\\r\\n"+pwd
					+"\\r\\n请牢记！');window.location.href='DiaryServlet?action=listAllDiary';</script>");
		}
	}
}
