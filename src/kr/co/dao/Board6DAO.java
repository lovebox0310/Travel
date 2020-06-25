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
import kr.co.dto.Board6CommentDTO;
import kr.co.dto.Board6NoticeDTO;
import kr.co.dto.LoginDTO;
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

	private void closeAll(Connection conn, PreparedStatement pstmt, ResultSet rs) {
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

		boolean isOk = false;

		try {
			conn = dataFactory.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			increaseReadCnt(conn, num);

			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String writer = rs.getString("writer");
				String title = rs.getString("title");
				String writeday = rs.getString("writeday");
				int readcnt = rs.getInt("readcnt");
				String content = rs.getString("content");
				String filename = rs.getString("filename");
				dto = new Board6NoticeDTO(num, writer, title, content, writeday, readcnt, filename);
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
			closeAll(conn, pstmt, rs);
		}

		return dto;
	}

	public Board6NoticeDTO updatUI(int num) {

		Board6NoticeDTO dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "select * from notice where num=?";
		ResultSet rs = null;

		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String writer = rs.getString("writer");
				String title = rs.getString("title");
				String content = rs.getString("content");
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
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, null);
		}
	}


	public void delete(int num) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "delete from notice where num=?";

		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);

			pstmt.executeUpdate();
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

		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
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

		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
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
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}

		return cList;
	}

	public void comment_insert(Board6CommentDTO commentDTO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "insert into notice_comment (comment_num, comment_board, comment_content, comment_writer, repRoot, repStep, repIndent ) values (?, ?, ?, ?, ?, ?, ?)";
		try {

			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
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
		
		boolean isOk = false;

		try {
			conn = dataFactory.getConnection();
			conn.setAutoCommit(false);
			
			
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
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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
}
