package kr.co.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
<<<<<<< HEAD
=======

>>>>>>> d14bbed0f9a2594819b39db166b670cee18b6d4f
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import kr.co.domain.BoardDTO;
<<<<<<< HEAD
import kr.co.dto.Board6DTO;
import kr.co.dto.QnaCommandDTO;
=======
import kr.co.dto.Board6CommentDTO;
import kr.co.dto.Board6NoticeDTO;
import kr.co.dto.LoginDTO;
>>>>>>> d14bbed0f9a2594819b39db166b670cee18b6d4f
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

<<<<<<< HEAD
	private void closeAll(ResultSet rs, PreparedStatement pstmt, Connection conn) {
=======
	private void closeAll(Connection conn, PreparedStatement pstmt, ResultSet rs) {
>>>>>>> d14bbed0f9a2594819b39db166b670cee18b6d4f
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

<<<<<<< HEAD
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
=======
	private int createNum(Connection conn) {

		PreparedStatement pstmt = null;
		String sql = "select max(num) from notice";
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
		} finally {
			closeAll(null, pstmt, rs);
		}
		return num;
	}

//	public List<Board6NoticeDTO> list() {
//		List<Board6NoticeDTO> list = new ArrayList<Board6NoticeDTO>();
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		String sql = "select * from notice order by num desc";
//		ResultSet rs = null;
//
//		try {
//			conn = dataFactory.getConnection();
//			pstmt = conn.prepareStatement(sql);
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				int num = rs.getInt("num");
//				String writer = rs.getString("writer");
//				String title = rs.getString("title");
//				String content = rs.getString("content");
//				String writeday = rs.getString("writeday");
//				int readcnt = rs.getInt("readcnt");
//				String filename = rs.getString("filename");
//
//				list.add(new Board6NoticeDTO(num, writer, title, content, writeday, readcnt, filename));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			closeAll(conn, pstmt, rs);
//		}
//		return list;
//	}

	private int createCommentNum(Connection conn) {

		PreparedStatement pstmt = null;
		String sql = "select max(comment_num) from notice_comment";
		ResultSet rs = null;
		Integer comment_num = null;
		try {

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				comment_num = rs.getInt(1);
				comment_num += 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return comment_num;
	}
	
	public void insert(Board6NoticeDTO noticeDTO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "insert into notice (num, writer, title, content, filename) values (?,?,?,?,?)";

		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);

			int num = createNum(conn);

			pstmt.setInt(1, num);
			pstmt.setString(2, noticeDTO.getWriter());
			pstmt.setString(3, noticeDTO.getTitle());
			pstmt.setString(4, noticeDTO.getContent());
			// pstmt.setString(5, noticeDTO.getWriteday());
			// pstmt.setInt(6, noticeDTO.getReadcnt());
			pstmt.setString(5, noticeDTO.getFilename());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, null);
		}
	}
	
	public void decreaseReadCnt(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update notice set readcnt = readcnt-1 where num=?";
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(null, pstmt, null);
		}
	}


	private void increaseReadCnt(Connection conn, int num) {
		PreparedStatement pstmt = null;
		String sql = "update notice set readcnt = readcnt+1 where num=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(null, pstmt, null);
		}
	}

	public Board6NoticeDTO read(int num) {
		Board6NoticeDTO dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "select * from notice where num=?";
		ResultSet rs = null;

>>>>>>> d14bbed0f9a2594819b39db166b670cee18b6d4f
		boolean isOk = false;

		try {
			conn = dataFactory.getConnection();
			conn.setAutoCommit(false);
<<<<<<< HEAD
			increaseReadCnt(conn, number); // 조회수 증가시키기

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, number);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String id = rs.getString("id");
				String content = rs.getString("content");
=======
			pstmt = conn.prepareStatement(sql);
			increaseReadCnt(conn, num);

			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
>>>>>>> d14bbed0f9a2594819b39db166b670cee18b6d4f
				String writer = rs.getString("writer");
				String title = rs.getString("title");
				String writeday = rs.getString("writeday");
				int readcnt = rs.getInt("readcnt");
<<<<<<< HEAD
				String filename = rs.getString("filename");
				dto = new Board6DTO(id, number, writer, title, content, writeday, readcnt, 0, 0, 0, filename);
=======
				String content = rs.getString("content");
				String filename = rs.getString("filename");
				dto = new Board6NoticeDTO(num, writer, title, content, writeday, readcnt, filename);
>>>>>>> d14bbed0f9a2594819b39db166b670cee18b6d4f
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
<<<<<<< HEAD

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
=======
			closeAll(conn, pstmt, rs);
		}

		return dto;
	}

	public Board6NoticeDTO updatUI(int num) {

		Board6NoticeDTO dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "select * from notice where num=?";
>>>>>>> d14bbed0f9a2594819b39db166b670cee18b6d4f
		ResultSet rs = null;

		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
<<<<<<< HEAD
			pstmt.setInt(1, number);
			rs = pstmt.executeQuery();
=======
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();

>>>>>>> d14bbed0f9a2594819b39db166b670cee18b6d4f
			if (rs.next()) {
				String writer = rs.getString("writer");
				String title = rs.getString("title");
				String content = rs.getString("content");
<<<<<<< HEAD

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
=======
				String writeday = rs.getString("writeday");
				String filename = rs.getString("filename");

				dto = new Board6NoticeDTO(num, writer, title, content, writeday, 0, filename);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}

		return dto;
	}

	public void update(Board6NoticeDTO noticeDTO) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update notice set writer=?, title=?, content=?, filename=? where num =?";

		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, noticeDTO.getWriter());
			pstmt.setString(2, noticeDTO.getTitle());
			pstmt.setString(3, noticeDTO.getContent());
			pstmt.setString(4, noticeDTO.getFilename());
			pstmt.setInt(5, noticeDTO.getNum());
>>>>>>> d14bbed0f9a2594819b39db166b670cee18b6d4f
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
<<<<<<< HEAD
			closeAll(null, pstmt, conn);
		}

	}

	public void delete(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "delete from qnaboard where num = ?";
		try {

=======
			closeAll(conn, pstmt, null);
		}
	}


	public void delete(int num) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "delete from notice where num=?";

		try {
>>>>>>> d14bbed0f9a2594819b39db166b670cee18b6d4f
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);

			pstmt.executeUpdate();
<<<<<<< HEAD

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
=======
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, null);
		}

	}

	private int getAmount(Connection conn) {
		int amount = 0;
		PreparedStatement pstmt = null;
		String sql = "select count(num) from notice";
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
			closeAll(null, pstmt, rs);
		}
		return amount;
	}

	public PageTO page(int curPage) {
		String sql = "select * from (select rownum rnum, num, title, writer, writeday, readcnt from (select * from notice order by num desc))  where rnum>=? and rnum<=?";
		PageTO to = new PageTO(curPage);
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dataFactory.getConnection();
			int amount = getAmount(conn);
			to.setAmount(amount);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, to.getStartNum());
			pstmt.setInt(2, to.getEndNum());

			rs = pstmt.executeQuery();
			while (rs.next()) {
				int num = rs.getInt("num");
				String title = rs.getString("title");
				String writer = rs.getString("writer");
				String writeday = rs.getString("writeday");
				int readcnt = rs.getInt("readcnt");
				Board6NoticeDTO dto = new Board6NoticeDTO(num, writer, title, null, writeday, readcnt, null);
				list.add(dto);
			}
			to.setList(list);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}

		return to;
	}

	public boolean login(LoginDTO notice_LoginDTO) {
		boolean isLogin = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "select * from member where id = ? and pw = ?";
		ResultSet rs = null;
>>>>>>> d14bbed0f9a2594819b39db166b670cee18b6d4f

		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
<<<<<<< HEAD
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
=======
			pstmt.setString(1, notice_LoginDTO.getId());
			pstmt.setString(2, notice_LoginDTO.getPw());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				isLogin = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}
		return isLogin;
	}

	public List<Board6CommentDTO> cList(int num) {

		List<Board6CommentDTO> cList = new ArrayList<Board6CommentDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "select * from notice_comment, notice where notice_comment.comment_board = notice.num and comment_board = ? order by repRoot asc, repStep asc";
		ResultSet rs = null;
>>>>>>> d14bbed0f9a2594819b39db166b670cee18b6d4f

		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
<<<<<<< HEAD
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
=======
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int comment_num = rs.getInt("comment_num");
				int comment_board = rs.getInt("comment_board");
				String comment_content = rs.getString("comment_content");
				String comment_writer = rs.getString("comment_writer");
				String comment_day = rs.getString("comment_day");
				int comment_repRoot = rs.getInt("repRoot");
				int comment_repStep  = rs.getInt("repStep");
				int comment_repIndent  = rs.getInt("repIndent");
				cList.add(new Board6CommentDTO(comment_num, comment_board, comment_content, comment_writer, comment_day, comment_repRoot,
						comment_repStep, comment_repIndent));
>>>>>>> d14bbed0f9a2594819b39db166b670cee18b6d4f
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
<<<<<<< HEAD
			closeAll(rs, pstmt, conn);
		}
		return list;
	}

	public void insertQnaCom(QnaCommandDTO dto, int number) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "insert into qnacomment (id, qnanum, num, writer, content, repRoot, repStep, repIndent) values (?,?,?,?,?,?,?,?)";
=======
			closeAll(conn, pstmt, rs);
		}

		return cList;
	}

	public void comment_insert(Board6CommentDTO commentDTO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "insert into notice_comment (comment_num, comment_board, comment_content, comment_writer, repRoot, repStep, repIndent ) values (?, ?, ?, ?, ?, ?, ?)";
>>>>>>> d14bbed0f9a2594819b39db166b670cee18b6d4f
		try {

			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
<<<<<<< HEAD
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

=======
			int comment_num = createCommentNum(conn);
			
			pstmt.setInt(1, comment_num);
			pstmt.setInt(2, commentDTO.getComment_board());
			pstmt.setString(3, commentDTO.getComment_content());
			pstmt.setString(4, commentDTO.getComment_writer());
			pstmt.setInt(5, comment_num);
			pstmt.setInt(6, 0);
			pstmt.setInt(7, 0);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, null);
		}
	}


	private void stepPlus(Connection conn, Board6CommentDTO orgDTO) {
		PreparedStatement pstmt = null;
		String sql = "update notice_comment set repStep = repStep + 1 where repRoot = ? and repStep > ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, orgDTO.getRepRoot());
			pstmt.setInt(2, orgDTO.getRepStep());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeAll(null, pstmt, null); //Connection은 아직 닫아주면 안된다!!
		}
		
	}
	
	public void reply(int orgnum, Board6CommentDTO dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "insert into notice_comment (comment_num, comment_board, comment_content, comment_writer, repRoot, repStep, repIndent) values (?, ?, ?, ?, ?, ?, ?)";
		
>>>>>>> d14bbed0f9a2594819b39db166b670cee18b6d4f
		boolean isOk = false;

		try {
			conn = dataFactory.getConnection();
			conn.setAutoCommit(false);
<<<<<<< HEAD

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

=======
			
			
			int comment_num = createCommentNum(conn);
			
			Board6CommentDTO orgDTO = updateUI(orgnum); //원래글 정보 가져옴
			
			stepPlus(conn,orgDTO);
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, comment_num);
			pstmt.setInt(2, dto.getComment_board());
			pstmt.setString(3,dto.getComment_content());
			pstmt.setString(4, dto.getComment_writer());
			pstmt.setInt(5, orgDTO.getRepRoot());
			pstmt.setInt(6, orgDTO.getRepStep()+1);
			pstmt.setInt(7, orgDTO.getRepIndent()+1);
			
			pstmt.executeUpdate();
			
			isOk = true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(isOk) {
					conn.commit();
				}else {
					conn.rollback();
				}
		} catch (Exception e2) {
			e2.printStackTrace();
		}
			
			closeAll(conn, pstmt, null);
		}
		
	}
	
	public Board6CommentDTO updateUI(int comment_num) {
		Board6CommentDTO dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "select * from notice_comment where comment_num = ?";
		ResultSet rs = null;
		
		
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, comment_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				
				int comment_board  = rs.getInt("comment_board");
				String comment_content = rs.getString("comment_content");
				String comment_writer  = rs.getString("comment_writer");
				String comment_day = rs.getString("comment_day");
				
				int repRoot = rs.getInt("repRoot"); //reply메소드에서 사용하기 위해 생성했음
				int repStep = rs.getInt("repStep");
				int repIndent = rs.getInt("repIndent");
				
				dto = new Board6CommentDTO(comment_num, comment_board, comment_content, comment_writer, comment_day, repRoot, repStep, repIndent);
				
			}
	
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {		
			closeAll(conn, pstmt, rs);
		}
		return dto;
		
	}

	public void comment_update(Board6CommentDTO cDto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update notice_comment set comment_content=? where comment_num =?";
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cDto.getComment_content());
			pstmt.setInt(2, cDto.getComment_num());
>>>>>>> d14bbed0f9a2594819b39db166b670cee18b6d4f
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
<<<<<<< HEAD
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

=======
			closeAll(conn, pstmt, null);
		}
	}

	public void comment_delete(int comment_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "delete from notice_comment where comment_num = ?";
		try {
			
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, comment_num);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeAll(conn, pstmt, null);
		}
	}

	public void comment_deleteAll(int repRoot, int comment_board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "delete from notice_comment where repRoot = ? and comment_board = ?";
		try {
			
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, repRoot);
			pstmt.setInt(2, comment_board);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeAll(conn, pstmt, null);
		}

	}
>>>>>>> d14bbed0f9a2594819b39db166b670cee18b6d4f
}
