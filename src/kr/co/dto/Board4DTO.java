package kr.co.dto;

import java.io.Serializable;

public class Board4DTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int num;
	private String writer;
	private String title;
	private String content;
	private String location;
	private String thema;
	private String filename;
	private String writeday;
	private int readcnt;
	private int repRoot;
	private int repStep;
	private int repIndent;

	public Board4DTO() {
	}

	public Board4DTO(int num, String writer, String title, String content, String location, String thema,
			String filename, String writeday, int readcnt, int repRoot, int repStep, int repIndent) {
		super();
		this.num = num;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.location = location;
		this.thema = thema;
		this.filename = filename;
		this.writeday = writeday;
		this.readcnt = readcnt;
		this.repRoot = repRoot;
		this.repStep = repStep;
		this.repIndent = repIndent;
	}

	public static final long getSerialversionuid() {
		return serialVersionUID;
	}

	public final int getNum() {
		return num;
	}

	public final void setNum(int num) {
		this.num = num;
	}

	public final String getWriter() {
		return writer;
	}

	public final void setWriter(String writer) {
		this.writer = writer;
	}

	public final String getTitle() {
		return title;
	}

	public final void setTitle(String title) {
		this.title = title;
	}

	public final String getContent() {
		return content;
	}

	public final void setContent(String content) {
		this.content = content;
	}

	public final String getLocation() {
		return location;
	}

	public final void setLocation(String location) {
		this.location = location;
	}

	public final String getThema() {
		return thema;
	}

	public final void setThema(String thema) {
		this.thema = thema;
	}

	public String getFilename() {
		return filename;
	}
	
	public void setFilename(String filename) {
		this.filename = filename;
	}

	public final String getWriteday() {
		return writeday;
	}

	public final void setWriteday(String writeday) {
		this.writeday = writeday;
	}

	public final int getReadcnt() {
		return readcnt;
	}

	public final void setReadcnt(int readcnt) {
		this.readcnt = readcnt;
	}

	public final int getRepRoot() {
		return repRoot;
	}

	public final void setRepRoot(int repRoot) {
		this.repRoot = repRoot;
	}

	public final int getRepStep() {
		return repStep;
	}

	public final void setRepStep(int repStep) {
		this.repStep = repStep;
	}

	public final int getRepIndent() {
		return repIndent;
	}

	public final void setRepIndent(int repIndent) {
		this.repIndent = repIndent;
	}
	
	
}
