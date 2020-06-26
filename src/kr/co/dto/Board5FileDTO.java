package kr.co.dto;

import java.io.File;
import java.io.Serializable;

public class Board5FileDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private String fileName;
	private String orgFileName;
	private String uploadFolder;
	private String filePath = uploadFolder + File.separator + fileName;
	
	public Board5FileDTO() {
		// TODO Auto-generated constructor stub
	}

	public Board5FileDTO(String fileName, String orgFileName, String uploadFolder) {
		super();
		this.fileName = fileName;
		this.orgFileName = orgFileName;
		this.uploadFolder = uploadFolder;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getOrgFileName() {
		return orgFileName;
	}

	public void setOrgFileName(String orgFileName) {
		this.orgFileName = orgFileName;
	}

	public String getUploadFolder() {
		return uploadFolder;
	}

	public void setUploadFolder(String uploadFolder) {
		this.uploadFolder = uploadFolder;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = uploadFolder + File.separator + fileName;
	}
}
