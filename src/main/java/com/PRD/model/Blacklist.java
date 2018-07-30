package com.PRD.model;

import java.io.Serializable;

public class Blacklist implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//黑名单
	private Integer blacklist_Id;
	private String blacklist_Name;
	private Integer blacklist_sex;
	private String blacklist_Nation;
	private String blacklist_Cardno;
	private Integer blacklist_Phone;
	private Integer Using_Unit_Id;
	private String blacklist_Notes;
	public Integer getBlacklist_Id() {
		return blacklist_Id;
	}
	public void setBlacklist_Id(Integer blacklist_Id) {
		this.blacklist_Id = blacklist_Id;
	}
	public String getBlacklist_Name() {
		return blacklist_Name;
	}
	public void setBlacklist_Name(String blacklist_Name) {
		this.blacklist_Name = blacklist_Name;
	}
	public Integer getBlacklist_sex() {
		return blacklist_sex;
	}
	public void setBlacklist_sex(Integer blacklist_sex) {
		this.blacklist_sex = blacklist_sex;
	}
	public String getBlacklist_Nation() {
		return blacklist_Nation;
	}
	public void setBlacklist_Nation(String blacklist_Nation) {
		this.blacklist_Nation = blacklist_Nation;
	}
	public String getBlacklist_Cardno() {
		return blacklist_Cardno;
	}
	public void setBlacklist_Cardno(String blacklist_Cardno) {
		this.blacklist_Cardno = blacklist_Cardno;
	}
	public Integer getBlacklist_Phone() {
		return blacklist_Phone;
	}
	public void setBlacklist_Phone(Integer blacklist_Phone) {
		this.blacklist_Phone = blacklist_Phone;
	}
	public String getBlacklist_Notes() {
		return blacklist_Notes;
	}
	public void setBlacklist_Notes(String blacklist_Notes) {
		this.blacklist_Notes = blacklist_Notes;
	}
	public Integer getUsing_Unit_Id() {
		return Using_Unit_Id;
	}
	public void setUsing_Unit_Id(Integer using_Unit_Id) {
		Using_Unit_Id = using_Unit_Id;
	}
	@Override
	public String toString() {
		return "Blacklist [blacklist_Id=" + blacklist_Id + ", blacklist_Name="
				+ blacklist_Name + ", blacklist_sex=" + blacklist_sex
				+ ", blacklist_Nation=" + blacklist_Nation
				+ ", blacklist_Cardno=" + blacklist_Cardno
				+ ", blacklist_Phone=" + blacklist_Phone + ", Using_Unit_Id="
				+ Using_Unit_Id + ", blacklist_Notes=" + blacklist_Notes + "]";
	}
	
	
	
}
