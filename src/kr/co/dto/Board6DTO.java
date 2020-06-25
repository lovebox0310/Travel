package kr.co.dto;
import java.io.Serializable;

import kr.co.domain.BoardDTO;

public class Board6DTO extends BoardDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private String id;
	private int num;
	private String writer;
	private String title;
	private String content;
	private String writeday;
	private int readcnt;
	
	private int repRoot;
	private int repStep;
	private int repIndent;
	private String filename;
	
	public Board6DTO() {
		// TODO Auto-generated constructor stub
	}
	
	public Board6DTO(String id, int num, String writer, String title, String content, String writeday, int readcnt,
			int repRoot, int repStep, int repIndent, String filename) {
		super();
		this.id = id;
		this.num = num;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.writeday = writeday;
		this.readcnt = readcnt;
		this.repRoot = repRoot;
		this.repStep = repStep;
		this.repIndent = repIndent;
		this.filename = filename;
	}

	public Board6DTO(String id, int num, String writer, String title, String content, String writeday, int readcnt,
			int repRoot, int repStep, int repIndent) {
		super();
		this.id = id;
		this.num = num;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.writeday = writeday;
		this.readcnt = readcnt;
		this.repRoot = repRoot;
		this.repStep = repStep;
		this.repIndent = repIndent;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriteday() {
		return writeday;
	}
	public void setWriteday(String writedate) {
		this.writeday = writedate;
	}
	public int getReadcnt() {
		return readcnt;
	}
	public void setReadcnt(int readcnt) {
		this.readcnt = readcnt;
	}
	public int getRepRoot() {
		return repRoot;
	}
	public void setRepRoot(int repRoot) {
		this.repRoot = repRoot;
	}
	public int getRepStep() {
		return repStep;
	}
	public void setRepStep(int repStep) {
		this.repStep = repStep;
	}
	public int getRepIndent() {
		return repIndent;
	}
	public void setRepIndent(int repIndent) {
		this.repIndent = repIndent;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + num;
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
		Board6DTO other = (Board6DTO) obj;
		if (num != other.num)
			return false;
		return true;
	}
	
	

}
