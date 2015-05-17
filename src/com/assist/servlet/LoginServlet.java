package com.assist.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.assist.model.DAO;

public class LoginServlet extends HttpServlet {


	private DAO d=new DAO();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doPost(request,response);
	}

 
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		     //从表示层获取用户填写的用户名和密码
			 String uid=request.getParameter("uid");
			 String pwd=request.getParameter("pwd");
			 String checkcode=request.getParameter("piccode");
			 String piccode=(String) request.getSession().getAttribute("piccode");
			 DAO d=new DAO();
			 if(request.getSession().getAttribute("openid")!=null){;
			 //调用业务层
			 try {
				if ((d.getPassword(uid)).equals(pwd)&(checkcode.toLowerCase()).equals(piccode.toLowerCase())){ //登录成功
					  //跳转到首页 
					  d.addopenid((String)request.getSession().getAttribute("openid"), uid);
					  request.getRequestDispatcher("index.jsp").forward(request, response);
				 }else{
					  //跳转到错误页面
					  request.getRequestDispatcher("error.jsp").forward(request, response);
				 }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				request.getSession().removeAttribute("openid");
			}
			}else{
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
			 
	}

}
