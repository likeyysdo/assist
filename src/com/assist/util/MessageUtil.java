package com.assist.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
     * 教务系统版图文消息回复
     * @param toUserName
     * @param fromUserName
     * @return
     */
    public static String initNews2(String toUserName,String fromUserName,List<Article> a){
		
		NewsMessageResponse news=new NewsMessageResponse();
		List<Article> addlist=new ArrayList<Article>();
		for(Article article:a){
			Article item=new Article(article.getTitle(),article.getDescription(), 
					article.getPicUrl(),article.getUrl());
			addlist.add(item);
		}
		news.setFromUserName(toUserName);
		news.setToUserName(fromUserName);
		news.setMsgType(MessageUtil.MESSAGE_NEWS);
		news.setCreateTime(123);
		news.setArticleCount(3);
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
	 * 课表查询
	 * @param StuNumber
	 * @return
	 * @throws Exception
	 */
	public static String courseQuery(String StuNumber) throws Exception{
		DAO d=new DAO();
		List<Course> c=d.query(StuNumber);
		StringBuffer sb=new StringBuffer();
		for(Course course:c){
			sb.append(course.getClassTime()+",");
			sb.append(course.getCourseName()+",");
			sb.append(course.getCourseType()+",");
			sb.append(course.getCourseCollege()+",");
			sb.append(course.getTeacherName()+",");
			sb.append(course.getCredit()+"学分,");
			sb.append(course.getCreditHours()+"学时\n\n");
			
		}
		return sb.toString();
	}
	/**
	 * 考试安排查询
	 * @param StuNumber
	 * @return
	 * @throws Exception
	 */
	public static String testPlanQuery(String StuNumber) throws Exception{
		DAO d=new DAO();
		List<Course> c=d.queryTestPlan(StuNumber);
		StringBuffer sb=new StringBuffer();
		for(Course course:c){
			sb.append(course.getCourseName()+",");
			sb.append(course.getCourseType()+",");
			sb.append(course.getCourseCollege()+",");
			sb.append(course.getTeacherName()+",");
			sb.append(course.getCredit()+"学分,");
			sb.append("考试时间："+course.getDateOfTest()+" "+course.getStartTimeOfTest()+"~"+course.getEndTimeOfTest()+",");
			sb.append("考试地点："+course.getAddressOfTest()+" "+course.getClassroomOfTest()+",");
			sb.append("主监考老师："+course.getMajorTestTeacher()+"&副监考老师："+course.getOtherTestTeacher()+"\n\n");
		}
		return sb.toString();
	}
	/**
	 * 成绩查询
	 * @param StuNumber
	 * @return
	 * @throws Exception
	 */
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
	 * 教务系统通知查询
	 * @param toUserName
	 * @param fromUserName
	 * @return
	 * @throws Exception
	 */
	public static String noticeQuery(String toUserName,String fromUserName) throws Exception{
		DAO d=new DAO();
		List<Article> a=d.queryNotice();
		return initNews2(toUserName,fromUserName,a);
	}
	/**
	 * 本学期所开公选课查询
	 * @return
	 * @throws Exception 
	 */
	public static String publicCourseQuery() throws Exception{
		DAO d=new DAO();
		List<Course> c=d.queryPublicCourse();
		StringBuffer sb=new StringBuffer();
		for(Course course:c){
			sb.append(course.getCourseName()+",");
			sb.append(course.getCourseCollege()+",");
			sb.append(course.getTeacherName()+",");
			sb.append(course.getCredit()+"学分,");
			sb.append(course.getCreditHours()+"学时,");
			sb.append(course.getClassTime()+"\n\n");
		}
		return sb.toString();
		
	}
	/**
	 * 已选公选课查询
	 * @param StuNumber
	 * @return
	 * @throws Exception 
	 */
	public static String privatepublicCourseQuery(String StuNumber) throws Exception{
		DAO d=new DAO();
		List<Course> c=d.queryPrivatePublicCourse(StuNumber);
		StringBuffer sb=new StringBuffer();
		for(Course course:c){
			sb.append(course.getClassTime()+",");
			sb.append(course.getCourseName()+",");
			sb.append(course.getCourseCollege()+",");
			sb.append(course.getTeacherName()+",");
			sb.append(course.getCredit()+"学分,");
			sb.append(course.getCreditHours()+"学时,");
			sb.append(course.getClassTime()+"\n\n");
		}
		return sb.toString();
		
		
	}
	/**
	 * 自习教室查询
	 * @return
	 */
	public static String studyClassQuery(){
		return null;
		
	}
	public static void cancleTie(String openid){
		DAO d = new DAO();
		try {
			d.delTie(openid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static String games(){
		StringBuffer sb=new StringBuffer();
		sb.append("<a href=\"http://pictoword.hortorgame.com");
		sb.append("\">疯狂猜图</a>");
		sb.append("\n");  
		sb.append("<a href=\"http://2000140264.zhan.qq.com/?t=1430825330");
		sb.append("\">我的简历</a>");
		return sb.toString();
	}
	/**
	 * 主菜单
	 * @return
	 */
	public static String menuText(){
		StringBuffer sb=new StringBuffer();
		sb.append("欢迎关注，按以下提示操作\n\n");
		sb.append("1.一键绑定\n");
		sb.append("2.成绩查询\n");
		sb.append("3.课表查询\n");
		sb.append("4.教务通知查询\n");
		sb.append("5.考试安排查询\n");
		sb.append("6.本学期所开公选课查询\n");
		sb.append("7.已选公选课查询\n");
		sb.append("8.自习教室查询\n");
		sb.append("9.小游戏\n\n");
		sb.append("回复？显示该菜单");
		return sb.toString();
	}
	public static String link(String fromUserName,String timestamp,String signature){
		StringBuffer sb=new StringBuffer();
		sb.append("<a href=\"http://assist.tunnel.mobi/Assist/login.jsp?openid=");
		sb.append(fromUserName);
		sb.append("&timestamp=");
		sb.append(timestamp);
		sb.append("&signature=");
		sb.append(signature);
		sb.append("\">一键绑定</a>");
		return sb.toString();
	}
	public static String link1(){
		StringBuffer sb=new StringBuffer();
		sb.append("<a href=\"http://pictoword.hortorgame.com");
		sb.append("\">疯狂猜图</a>");
		
		return sb.toString();
	}
	/**
	 * 学分计算
	 * @param map<score,credit>
	 * @return average credit
	 */
	public static double CreditCalcu(Map<Integer, Integer> map) {
		double value = 0;
		int sumCredit = 0;
		Set set = map.entrySet();  
        Iterator iterator =set.iterator();   
        while (iterator.hasNext())  
      {   
          Map.Entry mapentry= (Map.Entry)iterator.next();  
		
          value += (double)((((double)mapentry.getKey() - 60)/10)*(double)(mapentry.getValue()));
          sumCredit += (Integer)mapentry.getValue();        
      }
        value = value/ (double)sumCredit;
       System.out.println(value);
	   return value;
        
	}
}

