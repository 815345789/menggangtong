package com.PRD.model;


public class UserMessage {
	//用户信息表
	private Integer user_Id;
	private String username;
	private String usermessage_name;
	private String password;
	private Integer using_Unit_Id;
	private Integer found_Id;
	private Integer power_Num;
	public Integer getUser_Id() {
		return user_Id;
	}
	public void setUser_Id(Integer user_Id) {
		this.user_Id = user_Id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getUsing_Unit_Id() {
		return using_Unit_Id;
	}
	public void setUsing_Unit_Id(Integer using_Unit_Id) {
		this.using_Unit_Id = using_Unit_Id;
	}
	public Integer getFound_Id() {
		return found_Id;
	}
	public void setFound_Id(Integer found_Id) {
		this.found_Id = found_Id;
	}
	public Integer getPower_Num() {
		return power_Num;
	}
	public void setPower_Num(Integer power_Num) {
		this.power_Num = power_Num;
	}
	
	public String getUsermessage_name() {
		return usermessage_name;
	}
	public void setUsermessage_name(String usermessage_name) {
		this.usermessage_name = usermessage_name;
	}
	@Override
	public String toString() {
		return "UserMessage [user_Id=" + user_Id + ", username=" + username
				+ ", usermessage_name=" + usermessage_name + ", password="
				+ password + ", using_Unit_Id=" + using_Unit_Id + ", found_Id="
				+ found_Id + ", power_Num=" + power_Num + "]";
	}
	
	
}
