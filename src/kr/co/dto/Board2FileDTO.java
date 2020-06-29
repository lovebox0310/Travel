package kr.co.dto;

import java.io.Serializable;

public class Board2FileDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	private int fNum;
	private String fName;
	private String ogFName;
	private String url;
	
	public Board2FileDTO() {}
	public Board2FileDTO(int fNum, String fName, String ogFName, String url) {
		super();
		this.fNum = fNum;
		this.fName = fName;
		this.ogFName = ogFName;
		this.url = url;
	}
	public int getfNum() {
		return fNum;
	}
	public void setfNum(int fNum) {
		this.fNum = fNum;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getOgFName() {
		return ogFName;
	}
	public void setOgFName(String ogFName) {
		this.ogFName = ogFName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + fNum;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Board2FileDTO other = (Board2FileDTO) obj;
		if (fNum != other.fNum)
			return false;
		return true;
	}	
}