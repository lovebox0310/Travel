package kr.co.dto;

import java.io.Serializable;
import java.util.List;

import kr.co.domain.BoardDTO;

public class Board5DTO extends BoardDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private int num;
	private String writer;
	private String locationID;
	private String locationName;
	private String title;
	private String content;
	private String writeday;
	private int readcnt;
	private int repRoot;
	private int repStep;
	private int repIndent;
	private int fileNum;
	private Board5FileDTO fileDTO;
	
	public Board5DTO() {
		// TODO Auto-generated constructor stub
	}

	public Board5DTO(int num, String writer, String locationID, String locationName, String title, String content,
			String writeday, int readcnt, int repRoot, int repStep, int repIndent, int fileNum) {
		super();
		this.num = num;
		this.writer = writer;
		this.locationID = locationID;
		this.locationName = locationName;
		this.title = title;
		this.content = content;
		this.writeday = writeday;
		this.readcnt = readcnt;
		this.repRoot = repRoot;
		this.repStep = repStep;
		this.repIndent = repIndent;
		this.fileNum = fileNum;
	}

	public Board5DTO(int num, String writer, String locationID, String locationName, String title, String content,
			String writeday, int readcnt, int repRoot, int repStep, int repIndent, int fileNum, Board5FileDTO fileDTO) {
		super();
		this.num = num;
		this.writer = writer;
		this.locationID = locationID;
		this.locationName = locationName;
		this.title = title;
		this.content = content;
		this.writeday = writeday;
		this.readcnt = readcnt;
		this.repRoot = repRoot;
		this.repStep = repStep;
		this.repIndent = repIndent;
		this.fileNum = fileNum;
		this.fileDTO = fileDTO;
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

	public String getLocationID() {
		return locationID;
	}

	public void setLocationID(String locationID) {
		this.locationID = locationID;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
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

	public void setWriteday(String writeday) {
		this.writeday = writeday;
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

	public void setRepRoot(int reapRoot) {
		this.repRoot = reapRoot;
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
	public int getFileNum() {
		return fileNum;
	}
	public void setFileNum(int fileNum) {
		this.fileNum = fileNum;
	}
	

	public Board5FileDTO getFileDTO() {
		return fileDTO;
	}

	public void setFileDTO(Board5FileDTO fileDTO) {
		this.fileDTO = fileDTO;
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
		Board5DTO other = (Board5DTO) obj;
		if (num != other.num)
			return false;
		return true;
	}
	
}
