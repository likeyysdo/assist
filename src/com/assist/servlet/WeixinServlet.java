package com.assist.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;

import com.assist.message.TextMessage;
import com.assist.model.MyImage;
import com.assist.util.CheckUtil;
import com.assist.util.InterfaceUtil;
import com.assist.util.MessageUtil;

public class WeixinServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String signature = req.getParameter("signature");
		String timestamp = req.getParameter("timestamp");
		String nonce = req.getParameter("nonce");
		String echostr = req.getParameter("echostr");
		
		PrintWriter out=resp.getWriter();
		if(CheckUtil.checkSignature(signature, timestamp, nonce)){
			out.print(echostr);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out=resp.getWriter();
		try {
			Map<String,String> map=MessageUtil.xmlToMap(req);
			String fromUserName=map.get("FromUserName");
			String toUserName=map.get("ToUserName");
			String msgType=map.get("MsgType");
			String content=map.get("Content");
			String picUrl=map.get("PicUrl");
			String mediaId=map.get("MediaId");
			
			String message=null;
			if(MessageUtil.MESSAGE_TEXT.equals(msgType)){
				if("2012302580279".equals(content)){
//					message=MessageUtil.initText(toUserName, fromUserName, MessageUtil.firstMenu());
					message=MessageUtil.initText(toUserName, fromUserName, MessageUtil.courseQuery(content));
				}else if("1".equals(content)){
					message=MessageUtil.initNews(toUserName, fromUserName);
				}else if("2".equals(content)){
					message=MessageUtil.initText(toUserName, fromUserName, InterfaceUtil.getAccess_Token());
				}else if("?".equals(content)||"？".equals(content)){
					message=MessageUtil.initText(toUserName, fromUserName, MessageUtil.menuText());
				}else if("2012302580279成绩".equals(content)){
					message=MessageUtil.initText(toUserName, fromUserName, MessageUtil.gradeQuery(content.substring(0, 13)));	
				}else if("test".equals(content)){
					System.out.println(InterfaceUtil.getAccess_Token()+"\n");
					System.out.println(InterfaceUtil.getMenu(InterfaceUtil.getAccess_Token()));
				}else{
					message=MessageUtil.initText(toUserName, fromUserName, "回复？显示主菜单，并按菜单提示操作！");
				}
			}else if(MessageUtil.MESSAGE_EVENT.equals(msgType)){
				String eventType=map.get("Event");
				if(eventType.equals(MessageUtil.MESSAGE_SUBSCRIBE)){
					message=MessageUtil.initText(toUserName, fromUserName, MessageUtil.menuText());
				}
			}else if(MessageUtil.MESSAGE_IMAGE.equals(msgType)){
				message=MessageUtil.initImage(toUserName, fromUserName, new MyImage(mediaId));
			}
			out.print(message);
			System.out.println(message);
			System.out.println("剩余内存: " + java.lang.Runtime.getRuntime().freeMemory()  / 1024 / 1024 + "MB");
			
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			out.close();
		}
	}
}
