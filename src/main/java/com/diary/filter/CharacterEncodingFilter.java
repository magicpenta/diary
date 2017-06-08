package com.diary.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 字符编码过滤器
 * @author panda
 *
 */
public class CharacterEncodingFilter implements Filter {
	
	protected String encoding = null;
	protected FilterConfig filterConfig = null;
	
	public void init(FilterConfig fConfig) throws ServletException {
		// 1.获取过滤器配置信息
		this.filterConfig = fConfig;
		// 2.获取配置文件设置的编码值
		this.encoding = filterConfig.getInitParameter("encoding");
	}

	public void destroy() {
		this.encoding = null;
		this.filterConfig = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if ( encoding != null )
		{
			// 设置请求对象和响应对象的编码值
			request.setCharacterEncoding(encoding);
			response.setCharacterEncoding(encoding);
			response.setContentType("text/html; charset="+encoding);
		}
		chain.doFilter(request, response);
	}


}
