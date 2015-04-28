package com.assist.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.io.SAXReader;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;

import sun.misc.MessageUtils;

import com.assist.message.ImageMessage;
import com.assist.message.TextMessage;
import com.assist.messageResponse.ImageMessageResponse;
import com.assist.messageResponse.NewsMessageResponse;
import com.assist.model.Article;
import com.assist.model.Course;
import com.assist.model.DAO;
import com.assist.model.Grade;
import com.assist.model.MyImage;
import com.thoughtworks.xstream.XStream;

/**
 * @author fang
 * 消息处理类
 */
public class MessageUtil {
	
	public static final String MESSAGE_TEXT="text";
	public static final String MESSAGE_IMAGE="image";
	public static final String MESSAGE_VOICE="voice";
	public static final String MESSAGE_VIDEO="video";
	public static final String MESSAGE_LINK="link";
	public static final String MESSAGE_LOCATION="location";
	public static final String MESSAGE_NEWS="news";
	public static final String MESSAGE_EVENT="event";
	public static final String MESSAGE_SUBSCRIBE="subscribe";
	public static final String MESSAGE_UNSUBSCRIBE="unsubscribe";
	public static final String MESSAGE_CLICK="CLICK";
	public static final String MESSAGE_VIEW="VIEW";
	/**
	 * xml转map
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws DocumentException
	 */
	public static Map<String,String> xmlToMap(HttpServletRequest request) throws IOException,DocumentException{
		Map<String,String> map=new HashMap<String,String>();
		SAXReader reader=new SAXReader();
		
		InputStream ins=request.getInputStream();
		
		Document doc=reader.read(ins);
		
		Element root=doc.getRootElement();
		
		List<Element> list=root.elements();
		
		for(Element e:list){
			map.put(e.getName(), e.getText());
		}
		ins.close();
		return map;	
	}
	
	
	/**
	 * 文本对象转xml
	 * @param textMessage
	 * @return
	 */
	public static String textMessageToXml(TextMessage textMessage){
		XStream xstream = new XStream();
		xstream.alias("xml", textMessage.getClass());
		return xstream.toXML(textMessage);
	}
	
	/**
	 * 图像回复对象转xml
	 * @param textMessage
	 * @return
	 */
	public static String imageMessageToXml(ImageMessageResponse imageMessageResponse){
		XStream xstream = new XStream();
		xstream.alias("xml", imageMessageResponse.getClass());
		return xstream.toXML(imageMessageResponse);
	}
	/**
	 * 图文回复对象转xml
	 * @param textMessage
	 * @return
	 */
	public static String newsMessageToXml(NewsMessageResponse newsMessageResponse){
		XStream xstream = new XStream();
		xstream.alias("xml", newsMessageResponse.getClass());
		xstream.aliasField("Articles", newsMessageResponse.getClass(), "addlist");
		xstream.alias("item", com.assist.model.Article.class);
		return xstream.toXML(newsMessageResponse);
	}
	/**
	 * 文本消息
	 * @param toUserName
	 * @param fromUserName
	 * @param content
	 * @return
	 */
	public static String initText(String toUserName,String fromUserName,String content){
		
		TextMessage text=new TextMessage();
		text.setFromUserName(toUserName);
		text.setToUserName(fromUserName);
		text.setMsgType(MessageUtil.MESSAGE_TEXT);
		text.setCreateTime(123);
		text.setContent(content);
		text.setMsgId(123L);
		
		return textMessageToXml(text);
	}
	/**
	 * 图片回复消息
	 * @param toUserName
	 * @param fromUserName
	 * @param myimage
	 * @return
	 */
	public static String initImage(String toUserName,String fromUserName,MyImage myimage){
		
		ImageMessageResponse image=new ImageMessageResponse();
		image.setFromUserName(toUserName);
		image.setToUserName(fromUserName);
		image.setMsgType(MessageUtil.MESSAGE_IMAGE);
		image.setCreateTime(123);
		image.setImage(myimage);
		
		return imageMessageToXml(image);
	}
	/**
	 * 图文回复消息
	 * @param toUserName
	 * @param fromUserName
	 * @param myimage
	 * @return
	 */
	public static String initNews(String toUserName,String fromUserName){
		
		NewsMessageResponse news=new NewsMessageResponse();
		Article item1=new Article("我的标题","这是我的图文消息描述，这里描述了该条图文消息的概述", 
				"https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=2622361694,3464373607&fm=58", "https://www.baidu.com/");
		Article item2=new Article("我的标题","这是我的图文消息描述，这里描述了该条图文消息的概述", 
				"https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=2622361694,3464373607&fm=58", "https://www.baidu.com/");
		List<Article> addlist=new ArrayList<Article>();
		addlist.add(item1);
		addlist.add(item2);
		news.setFromUserName(toUserName);
		news.setToUserName(fromUserName);
		news.setMsgType(MessageUtil.MESSAGE_NEWS);
		news.setCreateTime(123);
		news.setArticleCount(2);
		news.setAddlist(addlist);
		
		return newsMessageToXml(news);
	}
	/**
	 * 
	 * @return
	 */
	public static String firstMenu(){
		StringBuffer sb=new StringBuffer();
		sb.append("哈哈");
		return sb.toString();
	}
	/**
	 * 课程查询
	 * @param StuNumber
	 * @return
	 * @throws Exception
	 */
	public static String courseQuery(String StuNumber) throws Exception{
		DAO d=new DAO();
		List<Course> c=d.query(StuNumber);
		StringBuffer sb=new StringBuffer();
		for(Course course:c){
			System.out.println(course.getCourseName());
			sb.append(course.getCourseName()+"\n");
		}
		return sb.toString();
	}
	public static String gradeQuery(String StuNumber) throws Exception{
		DAO d=new DAO();
		List<Grade> g=d.query2(StuNumber);
		StringBuffer sb=new StringBuffer();
		for(Grade grade:g){
			sb.append(grade.getCourseName());
			sb.append(grade.getGrade()+"\n");
		}
		return sb.toString();
	}
	/**
	 * @return
	 */
	public static String secondMenu(){
		StringBuffer sb=new StringBuffer();
		sb.append("你是傻逼");
		return sb.toString();
	}
	
	/**
	 * 主菜单
	 * @return
	 */
	public static String menuText(){
		StringBuffer sb=new StringBuffer();
		sb.append("欢迎关注，按以下提示操作\n\n");
		sb.append("1.阿常\n");
		sb.append("2.汪汪汪\n\n");
		sb.append("回复？显示该菜单");
		return sb.toString();
	}
}
