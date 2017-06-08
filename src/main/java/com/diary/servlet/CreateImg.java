package com.diary.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 生成图片的 Servlet
 * @author panda
 *
 */
public class CreateImg extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Pragma", "No-cache");	// 禁止缓存
		response.setHeader("Cache-Control", "No-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");	//指定生成的响应是图片
		int width = 1280;
		int height = 1280;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		HttpSession session = request.getSession(true);
		String template = session.getAttribute("template").toString();	// 获取模板名称
		String[] content = (String[])session.getAttribute("content");	// 获取内容信息
		File imgFile;
		if ( "极简主义".equals(template) )
		{
			imgFile = new File(request.getServletContext().getRealPath("images/diaryBG_1.jpg"));
			Image src = ImageIO.read(imgFile);	// 构建image对象
			g.drawImage(src, 0, 0, width, height, null);
			outWord(g, content, template, 0, 0);
		} 
		else if ( "高冷风范".equals(template) )
		{
			imgFile = new File(request.getServletContext().getRealPath("images/diaryBG_2.jpg"));
			Image src = ImageIO.read(imgFile);	// 构建image对象
			g.drawImage(src, 0, 0, width, height, null);
			outWord(g, content, template, 0, 0);
		}
		ImageIO.write(image, "PNG", response.getOutputStream());
		session.setAttribute("diaryImg", image);
	}

	/**
	 * 在生成的图片上写上内容
	 * @param g
	 * @param content
	 * @param template
	 * @param offsetX
	 * @param offsetY
	 */
	public void outWord(Graphics g, String[] content, String template, int offsetX, int offsetY) {
		Font font = new Font("微软雅黑", Font.PLAIN, 30);
		g.setFont(font);
		if ( "极简主义".equals(template) )
		{
			g.setColor(new Color(0, 0, 0));
		} 
		else if ( "高冷风范".equals(template) )
		{
			g.setColor(new Color(255, 255, 255));
		}
		int contentLen = 0;
		int x = 0;
		int y = 0;
		for ( int i = 0; i<content.length; i++ )
		{
			contentLen = content[i].length();
			x = 50 + (i%3) * 420 + offsetX;
			y = 160 + (i/3) * 410 + offsetY;
			if ( content[i].equals("score") )
			{
				
			}
			else
			{
				if ( Math.ceil(contentLen/10.0) == 1 )
				{
					g.drawString(content[i], x, y);
				}
				else if ( Math.ceil(contentLen/10.0) == 2 )
				{
					g.drawString(content[i].substring(0, 10), x, y);
					g.drawString(content[i].substring(10), x, y+40);
				}
				else if ( Math.ceil(contentLen/10.0) == 3 )
				{
					g.drawString(content[i].substring(0, 10), x, y);
					g.drawString(content[i].substring(10, 20), x, y+40);
					g.drawString(content[i].substring(20), x, y+80);
				}
			}
		}
		g.dispose();
	}

}
