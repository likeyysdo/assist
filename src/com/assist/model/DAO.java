package com.assist.model;

/**
 * 
 * @author fang
 *
 */
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.assist.util.DBUtil;

	
	public class DAO{
		
		
		public void addopenid(String openid,String StuNumber) throws SQLException{
			Connection conn=DBUtil.getConnection();
			String sql=""+
					"insert into so"+
					"(openId,StuNumber)" +
					" values("+
					"?,?)";
			PreparedStatement ptmt=conn.prepareStatement(sql);
			
			ptmt.setString(1,openid);
			ptmt.setString(2,StuNumber);
			
			ptmt.execute();
		}
		
//		public void updateGoddess(Goddess g) throws SQLException{
//			Connection conn=DBUtil.getConnection();
//			String sql=""+
//					"update imooc_goddess"+
//					" set user_name=?,sex=?,age=?,birthday=?,email=?,mobile=?,"+
//					"update_user=?,update_date=?,isdel=?"
//					+" where id=?";
//			PreparedStatement ptmt=conn.prepareStatement(sql);
//			
//			ptmt.setString(1,g.getUser_name());
//			ptmt.setInt(2,g.getSex());
//			ptmt.setInt(3,g.getAge());
//			ptmt.setDate(4, new Date(g.getBirthday().getTime()));
//			ptmt.setString(5,g.getEmail());
//			ptmt.setString(6,g.getMobile());
//			ptmt.setString(7, g.getUpdate_user());
//			ptmt.setDate(8,new Date(g.getUpdate_date().getTime()));
//			ptmt.setInt(9, g.getIsdel());
//			ptmt.setInt(10,g.getId());
//			
//			ptmt.execute();
//		}
		
		/**
		 * 解除绑定
		 * @param openid
		 * @throws SQLException
		 */
		public void delTie(String openid) throws SQLException{
			Connection conn=DBUtil.getConnection();
			String sql="delete from so where openid=?";
			PreparedStatement ptmt=conn.prepareStatement(sql);
			
			ptmt.setString(1,openid);
			
			ptmt.execute();
		}
		/**
		 * 所开公选课查询
		 * @param StuNumber
		 * @return
		 * @throws Exception
		 */
		public List<Course> queryPublicCourse() throws Exception{
			List<Course> result=new ArrayList<Course>();
			
			Connection conn=DBUtil.getConnection();
			StringBuilder sb=new StringBuilder();
			sb.append("select * from PublicElective");
//			Statement stmt=conn.createStatement();
			PreparedStatement ptmt=conn.prepareStatement(sb.toString());
			ResultSet rs=ptmt.executeQuery();

			Course c=null;
			while(rs.next()){
				c=new Course();
				c.setCourseName(rs.getString("CourseName"));
				c.setCourseCollege(rs.getString("CourseCollege"));
				c.setTeacherName(rs.getString("TeacherName"));
				c.setCredit(rs.getInt("Credit"));
				c.setCreditHours(rs.getInt("CreditHours"));
				c.setClassTime(rs.getString("ClassTime"));
				result.add(c);
			}
			return result;
		}
		/**
		 * 课表查询
		 * @param StuNumber
		 * @return
		 * @throws Exception
		 */
		public List<Course> query(String StuNumber) throws Exception{
			List<Course> result=new ArrayList<Course>();
			
			Connection conn=DBUtil.getConnection();
			StringBuilder sb=new StringBuilder();
			sb.append("select c.* from SC s_c,Course c where s_c.CourseNumber=c.CourseNumber and s_c.StuNumber=?;");
//			Statement stmt=conn.createStatement();
			PreparedStatement ptmt=conn.prepareStatement(sb.toString());
			ptmt.setString(1,StuNumber);
			ResultSet rs=ptmt.executeQuery();

			Course c=null;
			while(rs.next()){
				c=new Course();
				c.setCourseNumber(rs.getString("CourseNumber"));
				c.setCourseName(rs.getString("CourseName"));
				c.setCourseType(rs.getString("CourseType"));
				c.setCourseCollege(rs.getString("CourseCollege"));
				c.setTeacherName(rs.getString("TeacherName"));
				c.setCredit(rs.getInt("Credit"));
				c.setCreditHours(rs.getInt("CreditHours"));
				c.setClassTime(rs.getString("ClassTime"));
				result.add(c);
			}
			return result;
		}
		/**
		 * 已选公选课查询
		 * @param StuNumber
		 * @return
		 * @throws Exception
		 */
		public List<Course> queryPrivatePublicCourse(String StuNumber) throws Exception{
			List<Course> result=new ArrayList<Course>();
			
			Connection conn=DBUtil.getConnection();
			StringBuilder sb=new StringBuilder();
			sb.append("select c.* from SC s_c,Course c where s_c.CourseNumber=c.CourseNumber and c.CourseType='公共选修' and s_c.StuNumber=?;");
//			Statement stmt=conn.createStatement();
			PreparedStatement ptmt=conn.prepareStatement(sb.toString());
			ptmt.setString(1,StuNumber);
			ResultSet rs=ptmt.executeQuery();

			Course c=null;
			while(rs.next()){
				c=new Course();
				c.setCourseNumber(rs.getString("CourseNumber"));
				c.setCourseName(rs.getString("CourseName"));
				c.setCourseType(rs.getString("CourseType"));
				c.setCourseCollege(rs.getString("CourseCollege"));
				c.setTeacherName(rs.getString("TeacherName"));
				c.setCredit(rs.getInt("Credit"));
				c.setCreditHours(rs.getInt("CreditHours"));
				c.setClassTime(rs.getString("ClassTime"));
				result.add(c);
			}
			return result;
		}
		/**
		 * 教务通知查询
		 * @param notice_id
		 * @return
		 * @throws Exception
		 */
		public List<Article> queryNotice() throws Exception{
			List<Article> result=new ArrayList<Article>();
			
			Connection conn=DBUtil.getConnection();
			StringBuilder sb=new StringBuilder();
			sb.append("select * from notice;");
//			Statement stmt=conn.createStatement();
			PreparedStatement ptmt=conn.prepareStatement(sb.toString());
			ResultSet rs=ptmt.executeQuery();

			Article a=null;
			while(rs.next()){
				a=new Article();
				a.setTitle(rs.getString("title"));
				a.setDescription(rs.getString("description"));
				a.setPicUrl(rs.getString("picurl"));
				a.setUrl(rs.getString("url"));
				result.add(a);
			}
			return result;
		}
		/**
		 * 成绩查询
		 * @param StuNumber
		 * @return
		 * @throws Exception
		 */
		public List<Grade> query2(String StuNumber) throws Exception{
			List<Grade> result=new ArrayList<Grade>();
			
			Connection conn=DBUtil.getConnection();
			StringBuilder sb=new StringBuilder();
			sb.append("select c.coursename,s_c.grade from SC s_c,Course c where s_c.CourseNumber=c.CourseNumber and s_c.StuNumber=?;");
//			Statement stmt=conn.createStatement();
			PreparedStatement ptmt=conn.prepareStatement(sb.toString());
			ptmt.setString(1,StuNumber);
			ResultSet rs=ptmt.executeQuery();

			Grade g=null;
			while(rs.next()){
				g=new Grade();
				
				g.setCourseName(rs.getString("CourseName"));
				g.setGrade(rs.getInt("Grade"));
				
				result.add(g);
			}
			return result;
		}
		public List<Course> queryTestPlan(String StuNumber) throws Exception{
			List<Course> result=new ArrayList<Course>();
			
			Connection conn=DBUtil.getConnection();
			StringBuilder sb=new StringBuilder();
			sb.append("select c.* from SC s_c,Course c where s_c.CourseNumber=c.CourseNumber and s_c.StuNumber=?;");
//			Statement stmt=conn.createStatement();
			PreparedStatement ptmt=conn.prepareStatement(sb.toString());
			ptmt.setString(1,StuNumber);
			ResultSet rs=ptmt.executeQuery();

			Course c=null;
			while(rs.next()){
				c=new Course();
				c.setCourseName(rs.getString("CourseName"));
				c.setCourseType(rs.getString("CourseType"));
				c.setCourseCollege(rs.getString("CourseCollege"));
				c.setTeacherName(rs.getString("TeacherName"));
				c.setCredit(rs.getInt("Credit"));
				c.setDateOfTest(rs.getString("DateOfTest"));
				c.setStartTimeOfTest(rs.getString("StartTimeOfTest"));
				c.setEndTimeOfTest(rs.getString("EndTimeOfTest"));
				c.setClassroomOfTest(rs.getString("ClassroomOfTest"));
				c.setAddressOfTest(rs.getString("AddressOfTest"));
				c.setMajorTestTeacher(rs.getString("MajorTestTeacher"));
				c.setOtherTestTeacher(rs.getString("OtherTestTeacher"));
				result.add(c);
			}
			return result;
		}
		public String getPassword(String StuNumber) throws SQLException{
			Connection conn=DBUtil.getConnection();
			String sql="select password from sp where StuNumber=?";
			
			PreparedStatement ptmt=conn.prepareStatement(sql);
			
			ptmt.setString(1,StuNumber);
			
			ResultSet rs=ptmt.executeQuery();
			
			String password=null;
			while(rs.next()){
				password=rs.getString("password");
			}
			return password;
		}
		public String getStuNumber(String openid) throws SQLException{
			Connection conn=DBUtil.getConnection();
			String sql="select StuNumber from so where openid=?";
			
			PreparedStatement ptmt=conn.prepareStatement(sql);
			
			ptmt.setString(1,openid);
			
			ResultSet rs=ptmt.executeQuery();		
			String StuNumber=null;
			while(rs.next()){
				StuNumber=rs.getString("StuNumber");
			}
			return StuNumber;
		}
		
}
