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
		
//		public void addGoddess(Goddess g) throws SQLException{
//			Connection conn=DBUtil.getConnection();
//			String sql=""+
//					"insert into imooc_goddess"+
//					"(user_name,sex,age,birthday,email,mobile,"+
//					"create_user,create_date,update_user,update_date,isdel)" +
//					" values("+
//					"?,?,?,?,?,?,?,current_date(),?,current_date(),?)";
//			PreparedStatement ptmt=conn.prepareStatement(sql);
//			
//			ptmt.setString(1,g.getUser_name());
//			ptmt.setInt(2,g.getSex());
//			ptmt.setInt(3,g.getAge());
//			ptmt.setDate(4, new Date(g.getBirthday().getTime()));
//			ptmt.setString(5,g.getEmail());
//			ptmt.setString(6,g.getMobile());
//			ptmt.setString(7, g.getCreate_user());
//			ptmt.setString(8,g.getUpdate_user());
//			ptmt.setInt(9, g.getIsdel());
//			
//			ptmt.execute();
//		}
		
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
		
//		public void delGoddess(Integer id) throws SQLException{
//			Connection conn=DBUtil.getConnection();
//			String sql=""+
//					"delete from imooc_goddess"
//					+" where id=?";
//			PreparedStatement ptmt=conn.prepareStatement(sql);
//			
//			ptmt.setInt(1,id);
//			
//			ptmt.execute();
//		}
		
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
				
				result.add(c);
			}
			return result;
		}
		/**
		 * ��ѯ�ɼ�
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
		
//		public Goddess get(Integer id) throws SQLException{
//			Connection conn=DBUtil.getConnection();
//			String sql=""+"select *"+
////					"user_name,sex,age,birthday,email,mobile"+
//////					",create_user,create_date,update_user,update_date,isdel"+
//					" from imooc_goddess "+
//					"where id=?";
//			
//			PreparedStatement ptmt=conn.prepareStatement(sql);
//			
//			ptmt.setInt(1,id);
////			ptmt.executeQuery();
//			
//			ResultSet rs=ptmt.executeQuery();
////			
//			Goddess g=null;
//			while(rs.next()){
//				g=new Goddess();
//				g.setId(rs.getInt("id"));
//				g.setUser_name(rs.getString("user_name"));
//				g.setSex(rs.getInt("sex"));
//				g.setAge(rs.getInt("age"));
//				g.setBirthday(rs.getDate("birthday"));
//				g.setEmail(rs.getString("email"));
//				g.setMobile(rs.getString("mobile"));
//				g.setCreate_user(rs.getString("create_user"));
//				g.setCreate_date(rs.getDate("create_date"));
//				g.setUpdate_user(rs.getString("update_user"));
//				g.setUpdate_date(rs.getDate("update_date"));
//				g.setIsdel(rs.getInt("isdel"));
//				
//			}
//			return g;
//		}
		
//		public List<Goddess> query(String name,String mobile,String email) throws Exception{
//			List<Goddess> result=new ArrayList<Goddess>();
//			
//			Connection conn=DBUtil.getConnection();
//			StringBuilder sb=new StringBuilder();
//			sb.append("select * from imooc_goddess");
//			
//			sb.append(" where user_name like ? and mobile like ? and email like ?");
////			Statement stmt=conn.createStatement();
//			PreparedStatement ptmt=conn.prepareStatement(sb.toString());
//			ptmt.setString(1,"%"+name+"%");
//			ptmt.setString(2,"%"+mobile+"%");
//			ptmt.setString(3,"%"+email+"%");
//			
//			ResultSet rs=ptmt.executeQuery();
//
//			Goddess g=null;
//			while(rs.next()){
//				g=new Goddess();
//				g.setId(rs.getInt("id"));
//				g.setUser_name(rs.getString("user_name"));
//				g.setSex(rs.getInt("sex"));
//				g.setAge(rs.getInt("age"));
//				g.setBirthday(rs.getDate("birthday"));
//				g.setEmail(rs.getString("email"));
//				g.setMobile(rs.getString("mobile"));
//				g.setCreate_user(rs.getString("create_user"));
//				g.setCreate_date(rs.getDate("create_date"));
//				g.setUpdate_user(rs.getString("update_user"));
//				g.setUpdate_date(rs.getDate("update_date"));
//				g.setIsdel(rs.getInt("isdel"));
//				
//				result.add(g);
//			}
//			return result;
//		}
		
//		public List<Goddess> query(List<Map<String,Object>> params) throws Exception{
//			List<Goddess> result=new ArrayList<Goddess>();
//			
//			Connection conn=DBUtil.getConnection();
//			StringBuilder sb=new StringBuilder();
//			sb.append("select * from imooc_goddess where 1=0");//ʹ��and��ѯʱ1=1��or��ѯʱ1=0��
//			
//			if(params!=null&&params.size()>0){
//				for(int i=0;i<params.size();i++){
//					Map<String,Object> map=params.get(i);
//					sb.append(" or "+map.get("name")+" "+map.get("rela")+" "+map.get("value")+" ");
//				}
//			}
////			Statement stmt=conn.createStatement();
//			PreparedStatement ptmt=conn.prepareStatement(sb.toString());
//			
//			
//			ResultSet rs=ptmt.executeQuery();
//
//			Goddess g=null;
//			while(rs.next()){
//				g=new Goddess();
//				g.setId(rs.getInt("id"));
//				g.setUser_name(rs.getString("user_name"));
//				g.setSex(rs.getInt("sex"));
//				g.setAge(rs.getInt("age"));
//				g.setBirthday(rs.getDate("birthday"));
//				g.setEmail(rs.getString("email"));
//				g.setMobile(rs.getString("mobile"));
//				g.setCreate_user(rs.getString("create_user"));
//				g.setCreate_date(rs.getDate("create_date"));
//				g.setUpdate_user(rs.getString("update_user"));
//				g.setUpdate_date(rs.getDate("update_date"));
//				g.setIsdel(rs.getInt("id"));
//				
//				result.add(g);
//			}
//			return result;
//		}
}
