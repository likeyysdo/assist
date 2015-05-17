package com.assist.model;

public class Course {
	private String CourseNumber;
	private String CourseName;
	private String CourseType;
	private String CourseCollege;
	private String TeacherName;
	private String CourseMajor;
	private int Credit;
	private int CreditHours;
	private String ClassTime;
	private String Comment;
	private String DateOfTest;
	private String StartTimeOfTest;
	private String EndTimeOfTest;
	private String ClassroomOfTest;
	private String AddressOfTest;
	private String MajorTestTeacher;
	private String OtherTestTeacher;
	
	
	public String getCourseNumber() {
		return CourseNumber;
	}
	public void setCourseNumber(String courseNumber) {
		CourseNumber = courseNumber;
	}
	public String getCourseName() {
		return CourseName;
	}
	public void setCourseName(String courseName) {
		CourseName = courseName;
	}
	public String getCourseType() {
		return CourseType;
	}
	public void setCourseType(String courseType) {
		CourseType = courseType;
	}
	public String getCourseCollege() {
		return CourseCollege;
	}
	public void setCourseCollege(String courseCollege) {
		CourseCollege = courseCollege;
	}
	public String getTeacherName() {
		return TeacherName;
	}
	public void setTeacherName(String teacherName) {
		TeacherName = teacherName;
	}
	public String getCourseMajor() {
		return CourseMajor;
	}
	public void setCourseMajor(String courseMajor) {
		CourseMajor = courseMajor;
	}
	public int getCredit() {
		return Credit;
	}
	public void setCredit(int credit) {
		Credit = credit;
	}
	public int getCreditHours() {
		return CreditHours;
	}
	public void setCreditHours(int creditHours) {
		CreditHours = creditHours;
	}
	public String getClassTime() {
		return ClassTime;
	}
	public void setClassTime(String classTime) {
		ClassTime = classTime;
	}
	public String getComment() {
		return Comment;
	}
	public void setComment(String comment) {
		Comment = comment;
	}
	public String getDateOfTest() {
		return DateOfTest;
	}
	public void setDateOfTest(String dateOfTest) {
		DateOfTest = dateOfTest;
	}
	public String getStartTimeOfTest() {
		return StartTimeOfTest;
	}
	public void setStartTimeOfTest(String startTimeOfTest) {
		StartTimeOfTest = startTimeOfTest;
	}
	public String getEndTimeOfTest() {
		return EndTimeOfTest;
	}
	public void setEndTimeOfTest(String endTimeOfTest) {
		EndTimeOfTest = endTimeOfTest;
	}
	public String getClassroomOfTest() {
		return ClassroomOfTest;
	}
	public void setClassroomOfTest(String classroomOfTest) {
		ClassroomOfTest = classroomOfTest;
	}
	public String getAddressOfTest() {
		return AddressOfTest;
	}
	public void setAddressOfTest(String addressOfTest) {
		AddressOfTest = addressOfTest;
	}
	public String getMajorTestTeacher() {
		return MajorTestTeacher;
	}
	public void setMajorTestTeacher(String majorTestTeacher) {
		MajorTestTeacher = majorTestTeacher;
	}
	public String getOtherTestTeacher() {
		return OtherTestTeacher;
	}
	public void setOtherTestTeacher(String otherTestTeacher) {
		OtherTestTeacher = otherTestTeacher;
	}
	@Override
	public String toString() {
		return "Course [CourseNumber=" + CourseNumber + ", CourseName="
				+ CourseName + ", CourseType=" + CourseType
				+ ", CourseCollege=" + CourseCollege + ", TeacherName="
				+ TeacherName + ", CourseMajor=" + CourseMajor + ", Credit="
				+ Credit + ", CreditHours=" + CreditHours + ", ClassTime="
				+ ClassTime + ", Comment=" + Comment + ", DateOfTest="
				+ DateOfTest + ", StartTimeOfTest=" + StartTimeOfTest
				+ ", EndTimeOfTest=" + EndTimeOfTest + ", ClassroomOfTest="
				+ ClassroomOfTest + ", AddressOfTest=" + AddressOfTest
				+ ", MajorTestTeacher=" + MajorTestTeacher
				+ ", OtherTestTeacher=" + OtherTestTeacher + "]";
	}
}
