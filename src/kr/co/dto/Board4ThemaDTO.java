package kr.co.dto;

import java.io.Serializable;

public class Board4ThemaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String tid;
	private String tname;
	
	public Board4ThemaDTO() {
	}

	public Board4ThemaDTO(String tid, String tname) {
		super();
		this.tid = tid;
		this.tname = tname;
	}

	public final String getTid() {
		return tid;
	}

	public final void setTid(String tid) {
		this.tid = tid;
	}

	public final String getTname() {
		return tname;
	}

	public final void setTname(String tname) {
		this.tname = tname;
	}

	public static final long getSerialversionuid() {
		return serialVersionUID;
	}

}
