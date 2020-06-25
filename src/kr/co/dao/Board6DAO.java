package kr.co.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import kr.co.domain.BoardDTO;
import kr.co.dto.Board6DTO;
import kr.co.dto.QnaCommandDTO;
import kr.co.to.PageTO;

public class Board6DAO {

	private DataSource dataFactory;

	public Board6DAO() {
		try {
			Context ctx = new InitialContext();
			dataFactory = (DataSource) ctx.lookup("java:comp/env/jdbc/oracle11g");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private void closeAll(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public PageTO page(String find, String search, int curPage) {
		String sql = "select * from(select rownum rnum, id, num, title, writer, writeday, readcnt, repIndent from"
				+" (select * from qnaboard where "+find+" like '%"+search+"%' order by repRoot desc, repStep asc) )"
				+" where rnum>=? and rnum <=?";
		PageTO to = new PageTO(curPage);
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dataFactory.getConnection();
			int amount = getAmount(conn,find,search);
			to.setAmount(amount);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, to.getStartNum());
			pstmt.setInt(2, to.getEndNum());

			rs = pstmt.executeQuery();
			while (rs.next()) {
				String id = rs.getString("id");
				int num = rs.getInt("num");
				String title = rs.getString("title");
				String writer = rs.getString("writer");
				String writeday = rs.getString("writeday");
				int readcnt = rs.getInt("readcnt");
				int repIndent = rs.getInt("repIndent");
				Board6DTO dto = new Board6DTO(id, num, writer, title, null, writeday, readcnt, -1, -1, repIndent);
				list.add(dto);
			}
			to.setList(list);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt, conn);
		}

		return to;
	}

	private int getAmount(Connection conn,String find, String search) { // 글 갯수
		int amount = 0;
		PreparedStatement pstmt = null;
		String sql = "select count(num) from qnaboard where "+find+" like '%"+search+"%'";
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				amount = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt, null);
		}
		return amount;
	}

	public void insert(Board6DTO boardDTO) { // 글 작성
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "insert into qnaboard (id, num, writer, title, content, repRoot, repStep, repIndent, filename) values (?,?,?,?,?,?,?,?,?)";
		try {

			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			int num = createNum(conn);

			pstmt.setString(1, boardDTO.getId());
			pstmt.setInt(2, num);
			pstmt.setString(3, boardDTO.getWriter());
			pstmt.setString(4, boardDTO.getTitle());
			pstmt.setString(5, boardDTO.getContent());
			pstmt.setInt(6, num);
			pstmt.setInt(7, 0);
			pstmt.setInt(8, 0);
			pstmt.setString(9, boardDTO.getFilename());

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(null, pstmt, conn);
		}
	}

	private int createNum(Connection conn) {
		PreparedStatement pstmt = null;
		String sql = "select max(num) from qnaboard";
		ResultSet rs = null;
		Integer num = null;

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				num = rs.getInt(1);
				num += 1;

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return num;
	}

	public Board6DTO read(int number) {
		Board6DTO dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "select * from qnaboard where num = ?";
		ResultSet rs = null;
		boolean isOk = false;

		try {
			conn = dataFactory.getConnection();
			conn.setAutoCommit(false);
			increaseReadCnt(conn, number); // 조회수 증가시키기

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, number);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String id = rs.getString("id");
				String content = rs.getString("content");
				String writer = rs.getString("writer");
				String title = rs.getString("title");
				String writeday = rs.getString("writeday");
				int readcnt = rs.getInt("readcnt");
				String filename = rs.getString("filename");
				dto = new Board6DTO(id, number, writer, title, content, writeday, readcnt, 0, 0, 0, filename);
				isOk = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (isOk) {
					conn.commit();
				} else {
					conn.rollback();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}

			closeAll(rs, pstmt, conn);
		}
		return dto;

	}

	private void increaseReadCnt(Connection conn, int num) { // 조회수 증가시키기
		PreparedStatement pstmt = null;
		String sql = "update qnaboard set readcnt = readcnt + 1 where num = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(null, pstmt, null);
		}

	}

	public Board6DTO updateUI(int number) {
		Board6DTO dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "select * from qnaboard where num = ?";
		ResultSet rs = null;

		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, number);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String writer = rs.getString("writer");
				String title = rs.getString("title");
				String content = rs.getString("content");

				int repRoot = rs.getInt("repRoot");
				int repStep = rs.getInt("repStep");
				int repIndent = rs.getInt("repIndent");

				dto = new Board6DTO(null, number, writer, title, content, null, 0, repRoot, repStep, repIndent);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt, conn);
		}
		return dto;

	}

	public void update(Board6DTO dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update qnaboard set title= ?, content=?, filename=?, writer=? where num = ?";
		try {

			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setString(3, dto.getFilename());
			pstmt.setString(4, dto.getWriter());
			pstmt.setInt(5, dto.getNum());
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(null, pstmt, conn);
		}

	}

	public void delete(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "delete from qnaboard where num = ?";
		try {

			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(null, pstmt, conn);
		}
	}

	public void deleteRelatedcom(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "delete from qnacomment where qnanum = ?";
		try {

			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(null, pstmt, conn);
		}
	}

	public void reply(int orgnum, Board6DTO dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "insert into qnaboard (id, num,writer,title,content,repRoot,repStep,repIndent,filename) values (?,?,?,?,?,?,?,?,?)";

		boolean isOk = false;

		try {
			conn = dataFactory.getConnection();
			conn.setAutoCommit(false);

			int num = createNum(conn);
			Board6DTO orgDTO = updateUI(orgnum);
			stepPlus(conn, orgDTO);

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, dto.getId());
			pstmt.setInt(2, num);
			pstmt.setString(3, dto.getWriter());
			pstmt.setString(4, dto.getTitle());
			pstmt.setString(5, dto.getContent());
			pstmt.setInt(6, orgDTO.getRepRoot());
			pstmt.setInt(7, orgDTO.getRepStep() + 1);
			pstmt.setInt(8, orgDTO.getRepIndent() + 1);
			pstmt.setString(9, dto.getFilename());
			pstmt.executeUpdate();

			isOk = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (isOk) {
					conn.commit();
				} else {
					conn.rollback();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}

			closeAll(null, pstmt, conn);
		}
	}

	private void stepPlus(Connection conn, Board6DTO orgDTO) {
		PreparedStatement pstmt = null;
		String sql = "update qnaboard set repStep = repStep + 1 where repRoot = ? and repStep > ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, orgDTO.getRepRoot());
			pstmt.setInt(2, orgDTO.getRepStep());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(null, pstmt, null);
		}

	}

	public List<Board6DTO> getAskRepRoots(String id) {
		String sql = "select * from qnaboard where repRoot in (select repRoot from qnaboard where id = ? and repIndent = 0 ) order by repRoot desc, repStep asc"; // 본인이
																																									// 작성한
																																									// 글의
																																									// repRoot값
																																									// 가져오기
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Board6DTO> list = new ArrayList<Board6DTO>();

		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int num = rs.getInt("num");
				String rid = rs.getString("id");
				String writer = rs.getString("writer");
				String title = rs.getString("title");
				String writeday = rs.getString("writeday");
				int readcnt = rs.getInt("readcnt");
				int repIndent = rs.getInt("repIndent");
				Board6DTO dto = new Board6DTO(rid, num, writer, title, null, writeday, readcnt, -1, -1, repIndent);
				list.add(dto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt, conn);
		}
		return list;

	}

	public List<Board6DTO> getReplyRepRoots(String id) {
		String sql = "select * from qnaboard where repRoot in ("
				+ "	select repRoot from qnaboard where repIndent > 0 and id = ?"
				+ ") order by repRoot desc, repStep asc"; // 본인이 작성한 글의 repRoot값 가져오기
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Board6DTO> list = new ArrayList<Board6DTO>();

		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String rid = rs.getString("id");
				int num = rs.getInt("num");
				String writer = rs.getString("writer");
				String title = rs.getString("title");
				String writeday = rs.getString("writeday");
				int readcnt = rs.getInt("readcnt");
				int repIndent = rs.getInt("repIndent");

				Board6DTO dto = new Board6DTO(rid, num, writer, title, null, writeday, readcnt, -1, -1, repIndent);
				list.add(dto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt, conn);
		}
		return list;
	}

	public void insertQnaCom(QnaCommandDTO dto, int number) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "insert into qnacomment (id, qnanum, num, writer, content, repRoot, repStep, repIndent) values (?,?,?,?,?,?,?,?)";
		try {

			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			int num = createNum2(conn);

			pstmt.setString(1, dto.getId());
			pstmt.setInt(2, number);
			pstmt.setInt(3, num);
			pstmt.setString(4, dto.getWriter());
			pstmt.setString(5, dto.getContent());
			pstmt.setInt(6, num);
			pstmt.setInt(7, 0);
			pstmt.setInt(8, 0);

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(null, pstmt, conn);
		}

	}

	private int createNum2(Connection conn) {
		PreparedStatement pstmt = null;
		String sql = "select max(num) from qnacomment";
		ResultSet rs = null;
		Integer num = null;

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				num = rs.getInt(1);
				num += 1;

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return num;
	}

	public List<QnaCommandDTO> readComments(int number) {
		String sql = "select * from qnacomment where qnanum = ?  order by repRoot desc, num asc, repStep desc";
		List<QnaCommandDTO> list = new ArrayList<QnaCommandDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, number);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String id = rs.getString("id");
				int num = rs.getInt("num");
				String writer = rs.getString("writer");
				String content = rs.getString("content");
				String writeday = rs.getString("writeday");
				int repRoot = rs.getInt("repRoot");
				int repIndent = rs.getInt("repIndent");
				String orgWriter = rs.getString("orgWriter");
				QnaCommandDTO dto = new QnaCommandDTO(id, number, num, writer, content, writeday, repRoot, -1,
						repIndent, orgWriter);
				list.add(dto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt, conn);
		}

		return list;
	}

	public void replycomment(int orgnum, QnaCommandDTO dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "insert into qnacomment (id, qnanum,num,writer,content,repRoot,repStep,repIndent,orgWriter) values (?,?,?,?,?,?,?,?,?)";

		boolean isOk = false;

		try {
			conn = dataFactory.getConnection();
			conn.setAutoCommit(false);

			int num = createNum2(conn);
			QnaCommandDTO orgDTO = updateUI2(orgnum);
			stepPlus2(conn, orgDTO);

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, dto.getId());
			pstmt.setInt(2, dto.getQnanum());
			pstmt.setInt(3, num);
			pstmt.setString(4, dto.getWriter());
			pstmt.setString(5, dto.getContent());
			pstmt.setInt(6, orgDTO.getRepRoot());
			pstmt.setInt(7, orgDTO.getRepStep() + 1);
			pstmt.setInt(8, orgDTO.getRepIndent() + 1);
			pstmt.setString(9, dto.getOrgWriter());
			pstmt.executeUpdate();

			isOk = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (isOk) {
					conn.commit();
				} else {
					conn.rollback();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}

			closeAll(null, pstmt, conn);
		}

	}

	public QnaCommandDTO updateUI2(int number) {
		QnaCommandDTO dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "select * from qnacomment where num = ?";
		ResultSet rs = null;

		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, number);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String writer = rs.getString("writer");
				String content = rs.getString("content");

				int repRoot = rs.getInt("repRoot");
				int repStep = rs.getInt("repStep");
				int repIndent = rs.getInt("repIndent");

				dto = new QnaCommandDTO(null, -1, number, writer, content, null, repRoot, repStep, repIndent, null);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt, conn);
		}
		return dto;

	}

	private void stepPlus2(Connection conn, QnaCommandDTO orgDTO) {
		PreparedStatement pstmt = null;
		String sql = "update qnacomment set repStep = repStep + 1 where repRoot = ? and repStep > ?";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, orgDTO.getRepRoot());
			pstmt.setInt(2, orgDTO.getRepStep());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(null, pstmt, null);
		}

	}

	public void updateComment(QnaCommandDTO dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update qnacomment set writer= ?, content=? where num = ?";
		try {

			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, dto.getWriter());
			pstmt.setString(2, dto.getContent());
			pstmt.setInt(3, dto.getNum());
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(null, pstmt, conn);
		}

	}

	public void deleteComment(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "delete from qnacomment where num = ?";
		try {

			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(null, pstmt, conn);
		}
	}

	public void deleteAllComment(int qnanum, int repRoot) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "delete from qnacomment where qnanum = ? and repRoot = ?";
		try {

			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, qnanum);
			pstmt.setInt(2, repRoot);

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(null, pstmt, conn);
		}
	}

	public void updateReadcnt(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update qnaboard set readcnt = readcnt - 1 where num = ? ";
		try {

			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(null, pstmt, conn);
		}
	}

	public int countComments(int number) {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "select count(*) from qnacomment where qnanum = ?";
		ResultSet rs = null;

		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, number);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt, conn);
		}
		return count;
	}

	public String getWriter(String id) {
		String writer = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "select name from travelMember where id = ?";
		ResultSet rs = null;

		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				writer = rs.getString(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt, conn);
		}
		return writer;
	}

}
