package kr.co.dto;

import java.io.Serializable;

public class Board4LocationDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String lid;
	private String lname;
	
	public Board4LocationDTO() {
	}
	
	public Board4LocationDTO(String lid, String lname) {
		super();
		this.lid = lid;
		this.lname = lname;
	}
	public String getLid() {
		return lid;
	}
	public void setLid(String lid) {
		this.lid = lid;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
