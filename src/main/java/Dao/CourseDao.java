package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.xdevapi.Statement;
//import com.mysql.cj.protocol;


import db.DbConnect;
import model.Course;

public class CourseDao {
	Connection connection;
	
	public CourseDao() throws ClassNotFoundException, SQLException {
		connection = DbConnect.getConnection();
	}
	
	public void addCourse(Course course) {
		try {
			PreparedStatement pst = connection.prepareStatement("insert into course(coursename, fees, category, duration)" + "values(?,?,?,?)");
			pst.setString(1, course.getCoursename());
			pst.setInt(2, course.getFees());
			pst.setString(3, course.getCategory());
			pst.setInt(4, course.getDuration());
			
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public List<Course> getAllCourses(){
		
		List<Course> courses = new ArrayList<Course>();
		try {
			java.sql.Statement statement  = connection.createStatement();
			
			ResultSet rs = statement.executeQuery("select * from course");
			
			while(rs.next())
			{
				Course course = new Course();
				course.setCoursename(rs.getString("coursename"));
				course.setCourseid(rs.getInt("courseid"));
				course.setCategory(rs.getString("category"));
				course.setFees(rs.getInt("fees"));
				course.setDuration(rs.getInt("duration"));
				
				courses.add(course);
				
			}
		}
		catch( SQLException e) {
			e.printStackTrace();
		}
		return courses;
		
	}
	
	public void deleteCourse(int courseid) {
		try {
			PreparedStatement pst = connection.prepareStatement("delete from course where courseid = ?");
			pst.setInt(1, courseid);
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Course getCourseByID(int courseid) {
		Course course = new Course();
		try {
			PreparedStatement pst = connection.prepareStatement("select * from course where courseid=?");
			pst.setInt(1, courseid);
			ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				course.setCoursename(rs.getString("coursename"));
				course.setCourseid(rs.getInt("courseid"));
				course.setCategory(rs.getString("category"));
				course.setFees(rs.getInt("fees"));
				course.setDuration(rs.getInt("duration"));					
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return course;
	}
	
	public void updateCourse(Course course)
	{
		try {
			PreparedStatement pst = connection.prepareStatement("update course set " + "coursename=?, category=?, fees=?, duration=?" + " where courseid = ?");
			pst.setString(1, course.getCoursename());
			pst.setString(2, course.getCategory());
			pst.setInt(3, course.getFees());
			pst.setInt(4, course.getDuration());
			pst.setInt(5, course.getCourseid());
			
			pst.executeUpdate();
			
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}



	