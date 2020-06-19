package kr.co.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import kr.co.dto.Board4DTO;
import kr.co.dto.Board4LocationDTO;
import kr.co.dto.Board4ThemaDTO;
import kr.co.to.PageTO;

public class Board4DAO {
	private DataSource dataFactory;

	public Board4DAO() {
		try {
			Context cont = new InitialContext();
			dataFactory = (DataSource) cont.lookup("java:comp/env/jdbc/oracle11g");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insert(Board4DTO dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "insert into"
				+ " board4(num, writer, title, content, location, thema, filename, readcnt, repRoot, repStep, repIndent)"
				+ " values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			conn = dataFactory.getConnection();
			int num = createNum(conn) + 1;
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, dto.getWriter());
			pstmt.setString(3, dto.getTitle());
			pstmt.setString(4, dto.getContent());
			pstmt.setString(5, dto.getLocation());
			pstmt.setString(6, dto.getThema());
			pstmt.setString(7, dto.getFilename());
			pstmt.setInt(8, 0);
			pstmt.setInt(9, num);
			pstmt.setInt(10, 0);
			pstmt.setInt(11, 0);

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(null, pstmt, conn);
		}
	}

	private int createNum(Connection conn) {
		PreparedStatement pstmt = null;
		String sql = "select count(num) num from board4";
		ResultSet rs = null;
		int num = 0;

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				num = rs.getInt("num");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt, null);
		}
		return num;
	}

	public PageTO page(int curPage) {
		PageTO to = new PageTO(curPage);
		List<Board4DTO> list = new ArrayList<Board4DTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "select * from (" + 
				"	select rownum rnum, num, writer, title, content, lname, tname, filename, writeday, readcnt, repRoot, repStep, repIndent" + 
				"	from (" + 
				"		select * " + 
				"		from board4 b" + 
				"		left join board4_location l on b.location = l.lid" + 
				"		left join board4_thema t on b.thema = t.tid" + 
				"		order by repRoot desc, repStep asc" + 
				"	)" + 
				") where rnum >= ? and rnum <= ?";
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
				String writer = rs.getString("writer");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String location = rs.getString("lname");
				String thema = rs.getString("tname");
				String filename = rs.getString("filename");
				String writeday = rs.getString("writeday");
				int readcnt = rs.getInt("readcnt");
				int repRoot = rs.getInt("repRoot");
				int repStep = rs.getInt("repStep");
				int repIndent = rs.getInt("repIndent");
				list.add(new Board4DTO(num, writer, title, content, location, thema, filename, writeday, readcnt, repRoot, repStep, repIndent));
			}
			
			to.setList(list);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt, conn);
		}

		return to;
	}

	public PageTO page(int curPage, String paramlocation, String paramthema) {
		PageTO to = new PageTO(curPage);
		List<Board4DTO> list = new ArrayList<Board4DTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "select * from (" + 
				"	select rownum rnum, num, writer, title, content, lname, tname, filename, writeday, readcnt, repRoot, repStep, repIndent" + 
				"	from (" + 
				"		select * " + 
				"		from board4 b" + 
				"		left join board4_location l on b.location = l.lid" + 
				"		left join board4_thema t on b.thema = t.tid" + 
				"		where b.location like decode(?, '000', '%', ?)" +
				"		and b.thema like decode(?, '000', '%', ?)" +
				"		order by repRoot desc, repStep asc" + 
				"	)" + 
				") where rnum >= ? and rnum <= ?";
		ResultSet rs = null;
		
		try {
			conn = dataFactory.getConnection();
			
			int amount = getAmount(conn);
			to.setAmount(amount);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paramlocation);
			pstmt.setString(2, paramlocation);
			pstmt.setString(3, paramthema);
			pstmt.setString(4, paramthema);
			pstmt.setInt(5, to.getStartNum());
			pstmt.setInt(6, to.getEndNum());
			
//			System.out.println(sql);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int num = rs.getInt("num");
				String writer = rs.getString("writer");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String location = rs.getString("lname");
				String thema = rs.getString("tname");
				String filename = rs.getString("filename");
				String writeday = rs.getString("writeday");
				int readcnt = rs.getInt("readcnt");
				int repRoot = rs.getInt("repRoot");
				int repStep = rs.getInt("repStep");
				int repIndent = rs.getInt("repIndent");
				list.add(new Board4DTO(num, writer, title, content, location, thema, filename, writeday, readcnt, repRoot, repStep, repIndent));
			}
			
			to.setList(list);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt, conn);
		}
		
		return to;
	}

	private int getAmount(Connection conn) {
		PreparedStatement pstmt = null;
		String sql = "select count(num) from board4";
		ResultSet rs = null;
		int amount = 0;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				amount = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt, null);
		}
		
		return amount;
	}
	
	public int getAmount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "select count(num) from board4";
		ResultSet rs = null;
		int amount = 0;
		
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				amount = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt, conn);
		}
		
		return amount;
	}
	
	public List<Board4LocationDTO> locationList() {
		List<Board4LocationDTO> list = new ArrayList<Board4LocationDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "select * from board4_location";
		ResultSet rs = null;
		
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String lid = rs.getString("lid");
				String lname = rs.getString("lname");
				list.add(new Board4LocationDTO(lid, lname));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt, conn);
		}
		return list;
	}
	
	public List<Board4ThemaDTO> ThemaList() {
		List<Board4ThemaDTO> list = new ArrayList<Board4ThemaDTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "select * from board4_thema";
		ResultSet rs = null;
		
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String tid = rs.getString("tid");
				String tname = rs.getString("tname");
				list.add(new Board4ThemaDTO(tid, tname));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt, conn);
		}
		return list;
	}

	public Board4DTO read(int num) {
		Board4DTO dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "select * from board4 "
				+ "left join board4_location on location = lid "
				+ "left join board4_thema on thema = tid "
				+ "where num = ?";
		ResultSet rs = null;
		
		try {
			conn = dataFactory.getConnection();
			readcntPlus1(conn, num);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String writer = rs.getString("writer");
				String title = rs.getString("title");
				String content = rs.getString("content"); 
				String location = rs.getString("lname"); 
				String thema = rs.getString("tname");
				String filename = rs.getString("filename");
				String writeday = rs.getString("writeday");
				int readcnt = rs.getInt("readcnt") + 1; 
				int repRoot = rs.getInt("repRoot");
				int repStep = rs.getInt("repStep"); 
				int repIndent = rs.getInt("repIndent");
				dto = new Board4DTO(num, writer, title, content, location, thema, filename, writeday, readcnt, repRoot, repStep, repIndent);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt, conn);
		}
		return dto;
	}
	
	private void readcntPlus1(Connection conn, int num) {
		PreparedStatement pstmt = null;
		String sql = "update board4 set readcnt = readcnt + 1 where num = ?";
		
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
	
	public Board4DTO updateui(int num) {
		Board4DTO dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "select * from board4 where num = ?";
		ResultSet rs = null;
		
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String writer = rs.getString("writer");
				String title = rs.getString("title");
				String content = rs.getString("content"); 
				String location = rs.getString("location"); 
				String thema = rs.getString("thema");
				String filename = rs.getString("filename");
				String writeday = rs.getString("writeday");
				int readcnt = rs.getInt("readcnt") + 1; 
				int repRoot = rs.getInt("repRoot");
				int repStep = rs.getInt("repStep"); 
				int repIndent = rs.getInt("repIndent");
				dto = new Board4DTO(num, writer, title, content, location, thema, filename, writeday, readcnt, repRoot, repStep, repIndent);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt, conn);
		}
		return dto;
	}
	
	public Board4DTO updateui(int num, Connection conn) {
		Board4DTO dto = null;
		PreparedStatement pstmt = null;
		String sql = "select * from board4 where num = ?";
		ResultSet rs = null;
		
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String writer = rs.getString("writer");
				String title = rs.getString("title");
				String content = rs.getString("content"); 
				String location = rs.getString("location"); 
				String thema = rs.getString("thema");
				String filename = rs.getString("filename");
				String writeday = rs.getString("writeday");
				int readcnt = rs.getInt("readcnt") + 1; 
				int repRoot = rs.getInt("repRoot");
				int repStep = rs.getInt("repStep"); 
				int repIndent = rs.getInt("repIndent");
				dto = new Board4DTO(num, writer, title, content, location, thema, filename, writeday, readcnt, repRoot, repStep, repIndent);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(rs, pstmt, null);
		}
		return dto;
	}
	
	public void update(Board4DTO dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "update board4 set writer = ?, title = ?, content = ?, location = ?, thema = ?, filename = ? where num = ?";
		
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getWriter());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getContent());
			pstmt.setString(4, dto.getLocation());
			pstmt.setString(5, dto.getThema());
			pstmt.setString(6, dto.getFilename());
			pstmt.setInt(7, dto.getNum());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(null, pstmt, conn);
		}
	}

	
	public void reply(int orgNum, Board4DTO dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "insert into"
				+ " board4 (num, writer, title, content, location, thema, filename readcnt, repRoot, repStep, repIndent)"
				+ " values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			conn = dataFactory.getConnection();
			
			int num = createNum(conn) + 1;
			Board4DTO orgDTO = updateui(orgNum, conn);
			stepPlus1(conn, orgDTO);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, dto.getWriter());
			pstmt.setString(3, dto.getTitle());
			pstmt.setString(4, dto.getContent());
			pstmt.setString(5, dto.getLocation());
			pstmt.setString(6, dto.getThema());
			pstmt.setString(7, dto.getFilename());
			pstmt.setInt(8, dto.getReadcnt());
			pstmt.setInt(9, orgDTO.getRepRoot());
			pstmt.setInt(10, orgDTO.getRepStep() + 1);
			pstmt.setInt(11, orgDTO.getRepIndent() + 1);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(null, pstmt, conn);
		}
		
	}

	private void stepPlus1(Connection conn, Board4DTO orgDTO) {
		PreparedStatement pstmt = null;
		String sql = "update board4 set repStep = repStep + 1 where repRoot = ? and repStep > ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, orgDTO.getRepRoot());
			pstmt.setInt(2, orgDTO.getRepStep());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(null, pstmt, null);
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

}
