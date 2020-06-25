package kr.co.dto;

import java.io.Serializable;

import kr.co.domain.BoardDTO;

public class Board6CommentDTO extends BoardDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int comment_num;
	private int comment_board;
	private String comment_content;
	private String comment_writer;
	private String comment_day;
	private int repRoot;
	private int repStep;
	private int repIndent;
	
	public Board6CommentDTO() {
		// TODO Auto-generated constructor stub
	}

	public Board6CommentDTO(int comment_num, int comment_board, String comment_content, String comment_writer,
			String comment_day, int repRoot, int repStep, int repIndent) {
		super();
		this.comment_num = comment_num;
		this.comment_board = comment_board;
		this.comment_content = comment_content;
		this.comment_writer = comment_writer;
		this.comment_day = comment_day;
		this.repRoot = repRoot;
		this.repStep = repStep;
		this.repIndent = repIndent;
	}

	public int getComment_num() {
		return comment_num;
	}

	public void setComment_num(int comment_num) {
		this.comment_num = comment_num;
	}

	public int getComment_board() {
		return comment_board;
	}

	public void setComment_board(int comment_board) {
		this.comment_board = comment_board;
	}

	public String getComment_content() {
		return comment_content;
	}

	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}

	public String getComment_writer() {
		return comment_writer;
	}

	public void setComment_writer(String comment_writer) {
		this.comment_writer = comment_writer;
	}

	public String getComment_day() {
		return comment_day;
	}

	public void setComment_day(String comment_day) {
		this.comment_day = comment_day;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + comment_num;
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
		Board6CommentDTO other = (Board6CommentDTO) obj;
		if (comment_num != other.comment_num)
			return false;
		return true;
	}
	
	
	
	
}
