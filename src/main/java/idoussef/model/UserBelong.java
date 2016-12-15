package idoussef.model;

import org.springframework.data.annotation.Id;

public class UserBelong {
	
	
	@Id
	String id ; 
	String userid ; 
	String groupid ;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getGroupid() {
		return groupid;
	}
	public void setGroupid(String groupid) {
		this.groupid = groupid;
	} 
	

}
