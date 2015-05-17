package com.assist.model;

public class Student {
	private String StuName;
	private String StuNumber;
	private String StuSex;
	private int StuAge;
	private String StuMajor;
	private String StuPhone;
	private String StuAddress;
	private String StuClass;
	public String getStuName() {
		return StuName;
	}
	public void setStuName(String stuName) {
		StuName = stuName;
	}
	public String getStuNumber() {
		return StuNumber;
	}
	public void setStuNumber(String stuNumber) {
		StuNumber = stuNumber;
	}
	public String getStuSex() {
		return StuSex;
	}
	public void setStuSex(String stuSex) {
		StuSex = stuSex;
	}
	public int getStuAge() {
		return StuAge;
	}
	public void setStuAge(int stuAge) {
		StuAge = stuAge;
	}
	public String getStuMajor() {
		return StuMajor;
	}
	public void setStuMajor(String stuMajor) {
		StuMajor = stuMajor;
	}
	public String getStuPhone() {
		return StuPhone;
	}
	public void setStuPhone(String stuPhone) {
		StuPhone = stuPhone;
	}
	public String getStuAddress() {
		return StuAddress;
	}
	public void setStuAddress(String stuAddress) {
		StuAddress = stuAddress;
	}
	public String getStuClass() {
		return StuClass;
	}
	public void setStuClass(String stuClass) {
		StuClass = stuClass;
	}
	@Override
	public String toString() {
		return "Student [StuName=" + StuName + ", StuNumber=" + StuNumber
				+ ", StuSex=" + StuSex + ", StuAge=" + StuAge + ", StuMajor="
				+ StuMajor + ", StuPhone=" + StuPhone + ", StuAddress="
				+ StuAddress + ", StuClass=" + StuClass + "]";
	}
}
