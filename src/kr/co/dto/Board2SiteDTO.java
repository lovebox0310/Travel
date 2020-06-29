package kr.co.dto;

import java.io.Serializable;

public class Board2SiteDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	private String sid;
	private String location;
	
	public Board2SiteDTO() {}

	public Board2SiteDTO(String sid, String location) {
		super();
		this.sid = sid;
		this.location = location;
	}
	
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}