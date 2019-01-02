package model;

import java.io.Serializable;

public class CustomerVO implements Serializable{
	int tNum;
	int numofpeo;
	String sex;
	
	public int gettNum() {
		return tNum;
	}
	public void settNum(int tNum) {
		this.tNum = tNum;
	}
	public int getNumofpeo() {
		return numofpeo;
	}
	public void setNumofpeo(int numofpeo) {
		this.numofpeo = numofpeo;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
}
