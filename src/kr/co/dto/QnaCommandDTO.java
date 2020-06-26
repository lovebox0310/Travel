package kr.co.dto;
import java.io.Serializable;

public class QnaCommandDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private int qnanum;
	private int num;
	private String writer;
	private String content;
	private String writeday;
	private int repRoot;
	private int repStep;
	private int repIndent;
	private String orgWriter;
	
	public QnaCommandDTO() {
	}

	public QnaCommandDTO(String id, int qnanum, int num, String writer, String content, String writeday, int repRoot, int repStep,
			int repIndent, String orgWriter) {
		super();
		this.id = id;
		this.qnanum = qnanum;
		this.num = num;
		this.writer = writer;
		this.content = content;
		this.writeday = writeday;
		this.repRoot = repRoot;
		this.repStep = repStep;
		this.repIndent = repIndent;
		this.orgWriter = orgWriter;
	}

	public String getOrgWriter() {
		return orgWriter;
	}

	public void setOrgWriter(String orgWriter) {
		this.orgWriter = orgWriter;
	}

	public String getId() {
		return id;
	}

	public int getQnanum() {
		return qnanum;
	}

	public void setQnanum(int qnanum) {
		this.qnanum = qnanum;
	}

	public void setId(String id) {
		this.id = id;
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
		QnaCommandDTO other = (QnaCommandDTO) obj;
		if (num != other.num)
			return false;
		return true;
	}
	
}
