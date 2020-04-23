package com.thinkgem.jeesite.common.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.PageContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.FileUtils;

import sun.misc.BASE64Decoder;
/**
* @Author: 谭垒
* @date 2020-04-22 17:38
 @Description: 上传
*/
@SuppressWarnings("serial")
public class UploadServlet extends HttpServlet {
	private PrintWriter out;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			out.print(1);
		} catch (Exception e) {
			e.printStackTrace();
			out.print(0);
		}
		out.flush();
		out.close();
	}

}
