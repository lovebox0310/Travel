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
import kr.co.dto.Board2DTO;
import kr.co.dto.SiteDTO;
import kr.co.to.PageTO;

public class Board2DAO {
	private DataSource dataFactory;

	public Board2DAO() {
		try {
			Context ctx = new InitialContext();
			dataFactory =  (DataSource) ctx.lookup("java:comp/env/jdbc/oracle11g");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	public void closeAll(ResultSet rs, PreparedStatement pstmt, Connection conn) {	
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	public List<Board2DTO> list() {
		List<Board2DTO> list = new ArrayList<Board2DTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "select * from board2";
		ResultSet rs = null;
		
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int num = rs.getInt("num");
				String writer = rs.getString("writer");
				String title = rs.getString("title");
				String writeday = rs.getString("writeday");
				String location = rs.getString("location");
				int readcnt = rs.getInt("readcnt");
				int repRoot = rs.getInt("repRoot");
				int repStep = rs.getInt("repStep");
				int repIndent = rs.getInt("repIndent");
				
				list.add(new Board2DTO(num, writer, title, null, writeday, location, readcnt, repRoot, repStep, repIndent));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeAll(rs, pstmt, conn);
		}
		return list;
	}
	public void insert(Board2DTO dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "insert into board2 (num, writer, title, content, location, repRoot, repStep, repIndent) "
				+ "values (?,?,?,?,?,?,?,?)";
			
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			int num = createNum(conn);
			
			
			pstmt.setInt(1, num);
			pstmt.setString(2, dto.getWriter());
			pstmt.setString(3, dto.getTitle());
			pstmt.setString(4, dto.getContent());
			pstmt.setString(5, dto.getLocation());
			pstmt.setInt(6, num);
			pstmt.setInt(7, 0);
			pstmt.setInt(8, 0);
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeAll(null, pstmt, conn);
		}
	}
	private int createNum(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select max(num) from board2";
		Integer num = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				num = rs.getInt(1);
				num += 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeAll(rs, pstmt, null);
		}		
		return num;
	}
	public Board2DTO read(int num) {
		Board2DTO dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "select num, writer, title, content, writeday, s.location, readcnt from BOARD2 b left join SITE s on b.location = sid  where num = ?";
		ResultSet rs = null;
		
		boolean isOk = false;
		
		try {
			conn = dataFactory.getConnection();
			conn.setAutoCommit(false);
			increaseReadCnt(conn, num);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {					
				String writer = rs.getString("writer");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String writeday = rs.getString("writeday");
				String location = rs.getString("location");
				int readcnt = rs.getInt("readcnt");
				
				dto = new Board2DTO(num, writer, title, content, writeday, location, readcnt, 0, 0, 0);
				
				isOk = true;
			}						
		} catch (Exception e) {
			e.printStackTrace();
		}finally {		
			try {
				if (isOk) {
					conn.commit();
				}else {
					conn.rollback();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			closeAll(rs, pstmt, conn);
		}
		return dto;	
	}
	private void increaseReadCnt(Connection conn, int num) {
		PreparedStatement pstmt = null;
		String sql = "update board2 set readcnt = readcnt + 1 where num = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeAll(null, pstmt, null);
		}
	}
	
	public Board2DTO updateui(int num) {
		Board2DTO dto = new Board2DTO();
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "select num, writer, title, content, location, repRoot, repStep, repIndent "
				+ "from BOARD2 where num = ?";
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
				String location = rs.getString("location");
				int repRoot = rs.getInt("repRoot");
				int repStep = rs.getInt("repStep");
				int repIndent = rs.getInt("repIndent");
				
				dto = new Board2DTO(num, writer, title, content, null, location, 0, repRoot, repStep, repIndent);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeAll(rs, pstmt, conn);
		}
		return dto;
	}
	public void update(Board2DTO dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update board2 set writer = ?, title = ?, content = ?, location = ? where num = ?";
		
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dto.getWriter());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getContent());
			pstmt.setString(4, dto.getLocation());
			pstmt.setInt(5, dto.getNum());
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeAll(null, pstmt, conn);
		}
	}
	public void delete(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "delete from board2 where num = ?";
		
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeAll(null, pstmt, conn);
		}
	}
	public void reply(int orgnum, Board2DTO dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "insert into board2 (num, writer, title, content, location, repRoot, repStep, repIndent) "
				+ "values (?, ?, ?, ?, ?, ?, ?, ?)";
		boolean isOk = false;
		
		try {
			conn = dataFactory.getConnection();
			conn.setAutoCommit(false);
			
			int num = createNum(conn);
			
			Board2DTO orgDTO = updateui(orgnum);
			
			stepPlus1(conn, orgDTO);
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, num);
			pstmt.setString(2, dto.getWriter());
			pstmt.setString(3, dto.getTitle());
			pstmt.setString(4, dto.getContent());
			pstmt.setString(5, dto.getLocation());
			pstmt.setInt(6, orgDTO.getRepRoot());
			pstmt.setInt(7, orgDTO.getRepStep() + 1);
			pstmt.setInt(8, orgDTO.getRepIndent() + 1);
			
			pstmt.executeUpdate();
			
			isOk = true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (isOk) {
					conn.commit();
				}else {
					conn.rollback();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			closeAll(null, pstmt, conn);
		}	
	}
	private void stepPlus1(Connection conn, Board2DTO orgDTO) {
		PreparedStatement pstmt = null;
		String sql = "update board2 set repStep = repStep + 1 where repRoot = ? and repStep > ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, orgDTO.getRepRoot());
			pstmt.setInt(2, orgDTO.getRepStep());
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeAll(null, pstmt, null);
		}
	}
	
	public PageTO page(int curPage) {
		String sql = "select * from" + 
				"(select rownum as rnum, num, title, writer, writeday, location, readcnt, repIndent from" + 
				"(select * from board order by repRoot desc, repStep asc))" + 
				"where rnum >= ? and rnum <= ?";
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
			
			while(rs.next()) {
				int num = rs.getInt("num");
				String title = rs.getString("title");
				String writer = rs.getString("writer");
				String writeday = rs.getString("writeday");
				String location = rs.getString("location");
				int readcnt = rs.getInt("readcnt");
				int repIndent = rs.getInt("repIndent");
				
				Board2DTO dto = new Board2DTO(num, writer, title, null, writeday, location, readcnt, -1, -1, repIndent);
				
				list.add(dto);
			}
			to.setList(list);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeAll(rs, pstmt, conn);
		}	
		return to;
	}	
	
	public PageTO page(int curPage, String sid) {
		String sql = "select * from" + 
				" (select rownum rnum, num, writer, writeday, title, content, s.location, readcnt, repRoot, repStep, repIndent" + 
				" from(select * from BOARD2 where location like decode (?, null, '%', ?) order by repRoot desc, repStep asc) b" + 
				" left join site s on b.location = sid)where rnum >= ? and rnum <= ?";
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
			pstmt.setString(1, sid);
			pstmt.setString(2, sid);
			pstmt.setInt(3, to.getStartNum());
			pstmt.setInt(4, to.getEndNum());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int num = rs.getInt("num");
				String title = rs.getString("title");
				String writer = rs.getString("writer");
				String writeday = rs.getString("writeday");
				String location = rs.getString("location");
				int readcnt = rs.getInt("readcnt");
				int repIndent = rs.getInt("repIndent");
				
				BoardDTO dto = new Board2DTO(num, writer, title, null, writeday, location, readcnt, -1, -1, repIndent);
				
				list.add((Board2DTO) dto);
			}
			to.setList(list);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeAll(rs, pstmt, conn);
		}	
		return to;
	}	
	
	private int getAmount(Connection conn) {
		int amount = 0;
		
		PreparedStatement pstmt = null;
		String sql = "select count(num) from board2";
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				amount = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeAll(rs, pstmt, null);
		}
		return amount;
	}
	
	public List<SiteDTO> site(){
		List<SiteDTO> siteList = new ArrayList<SiteDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "select * from site";
		ResultSet rs = null;
		
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String sid = rs.getString("sid");
				String location = rs.getString("location");
				
				siteList.add(new SiteDTO(sid, location));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeAll(rs, pstmt, conn);
		}
		return siteList;
	}
	
	
}



















