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
import kr.co.dto.*;
import kr.co.to.Board5ImgPageTO;
import kr.co.to.PageTO;

public class Board5DAO {
	DataSource dataFactory;
	
	public Board5DAO() {
		try {
			Context context = new InitialContext();
			dataFactory = (DataSource) context.lookup("java:comp/env/jdbc/oracle11g");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Board5DTO updateUI(int num) {
		System.out.println("updateUI 실행");
		Board5DTO dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "select * from tb_board5 b left join TB_LOCATION l on b.location = l.lid where num = ?";
		ResultSet rs = null;
		
		try {
			conn = dataFactory.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String writer = rs.getString("writer");
				String locationID = rs.getString("location");
				String locationName = rs.getString("lname");
				String title = rs.getString("title");
				String content = rs.getString("content");
				int readcnt = rs.getInt("readcnt");
				int repRoot = rs.getInt("repRoot");
				int repStep = rs.getInt("repStep");
				int repIndent = rs.getInt("repIndent");
				
				dto = new Board5DTO(num, writer, locationID, locationName, title, content, null, readcnt, repRoot, repStep, repIndent, -1);
			}	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			closAll(rs, pstmt, conn);
		}
		return dto;
		
	}
	
	public void update(Board5DTO boardDTO, Board5FileDTO fileDTO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql_board5 = "update tb_board5 set title=?, content=?, location=? where num = ?";
		String sql_fileUpload = "update tb_fileUpload set fileName = ?, orgfileName = ?, uploadFolder=? where fnum = ? ";
		
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql_board5);
			
			pstmt.setString(1, boardDTO.getTitle());
			pstmt.setString(2, boardDTO.getContent());
			pstmt.setString(3, boardDTO.getLocationID());			
			pstmt.setInt(4, boardDTO.getNum());
			
			pstmt.executeUpdate();
			
			if(fileDTO != null) {
				pstmt.clearParameters();
				
				pstmt = conn.prepareStatement(sql_fileUpload);
				
				pstmt.setString(1, fileDTO.getFileName());
				pstmt.setString(2, fileDTO.getOrgFileName());
				pstmt.setString(3, fileDTO.getUploadFolder());
				pstmt.setInt(4, boardDTO.getNum());
				pstmt.executeUpdate();
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closAll(null, pstmt, conn);
		}
		
	}

	public void delete(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql_file = "delete from tb_fileUpload where fnum=?";
		String sql = "delete from tb_board5 where num = ?";
				
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql_file);
			
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			
			pstmt.clearParameters();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closAll(null, pstmt, conn);
		}
	}

	public int reply(int orgnum, Board5DTO boardDTO, Board5FileDTO fileDTO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql_board5 = "insert into tb_board5 (num, writer, location, title, content, repRoot, repStep, repIndent) values" + 
										"(?, ?, ?, ?, ?, ?, ? ,?)";
		String sql_fileUpload = "insert into tb_fileUpload (fnum, fileName, orgfileName, uploadFolder) values (?, ?, ?, ?)";
		int num = -1;
		boolean isOK = false;		
		try {
			conn = dataFactory.getConnection();
			
			conn.setAutoCommit(false);
			
			pstmt = conn.prepareStatement(sql_board5);
			
			num = createNum(conn);
			Board5DTO orgDTO = updateUI(orgnum);
			
			stepPlus(conn, orgDTO);
			
			pstmt.setInt(1, num);
			pstmt.setString(2, boardDTO.getWriter());
			pstmt.setString(3, boardDTO.getLocationID());			
			pstmt.setString(4, boardDTO.getTitle());
			pstmt.setString(5, boardDTO.getContent());
			pstmt.setInt(6, orgDTO.getRepRoot());
			pstmt.setInt(7, orgDTO.getRepStep()+1);
			pstmt.setInt(8, orgDTO.getRepIndent()+1);
			
			pstmt.executeUpdate();
			
			if(fileDTO != null) {
				pstmt.clearParameters();
				
				pstmt = conn.prepareStatement(sql_fileUpload);
				pstmt.setInt(1, num);
				pstmt.setString(2, fileDTO.getFileName());
				pstmt.setString(3, fileDTO.getOrgFileName());
				pstmt.setString(4, fileDTO.getUploadFolder());
				pstmt.executeUpdate();
			}
						
			isOK = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(isOK) {
					conn.commit();
				}else {
					conn.rollback();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			closAll(null, pstmt, conn);
		}
		return num;
	}

	public PageTO page(int curPage, String searchTitle, String nowLocationID) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		System.out.println("searchTitle " + searchTitle);
		System.out.println("nowLocation " + nowLocationID);
		String sql = "select * from " + 
				"				( " + 
				"				select rownum rnum, num, location, lname, title, fnum, writer, writeday, readcnt, repIndent from  " + 
				"				(" + 
				"					select * from tb_board5 b  " + 
				"					left join tb_location l on b.location = l.lid  " + 
				"					left join TB_FILEUPLOAD f on b.num = f.fnum " + 
				"				order by repRoot desc, repStep asc" + 
				"				)WHERE title LIKE DECODE(?, NULL, '%', '%"+ searchTitle +"%') and location LIKE DECODE(?, NULL, '%', ?)" + 
				"				)	where rnum>= ? and rnum<=?";
		ResultSet rs = null;
		PageTO to = new PageTO(curPage);
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		
		try {
			conn = dataFactory.getConnection();
			int amount = getAmount(conn, searchTitle, nowLocationID);	
			to.setAmount(amount);
			System.out.println("게시글 개수" + to.getAmount());
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchTitle);
			pstmt.setString(2, nowLocationID);
			pstmt.setString(3, nowLocationID);
			pstmt.setInt(4, to.getStartNum());
			pstmt.setInt(5, to.getEndNum());
	
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				int num = rs.getInt("num");
				String title = rs.getString("title");
				String writer = rs.getString("writer");
				String locationName = rs.getString("lname");
				String writeday = rs.getString("writeday");
				int readcnt = rs.getInt("readcnt");
				int repIndent = rs.getInt("repIndent");
				int fileNum = rs.getInt("fnum");
				
				list.add(new Board5DTO(num, writer, nowLocationID, locationName, title, null, writeday, readcnt, -1, -1, repIndent, fileNum));
				
			}
			to.setList(list);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closAll(rs, pstmt, conn);
		}
		
		return to;
	}

	public Board5ImgPageTO imgPage(int curPage, String searchTitle, String nowLocationID) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		System.out.println("searchTitle " + searchTitle);
		System.out.println("nowLocation " + nowLocationID);
		String sql = "select * from " + 
				"				( " + 
				"				select rownum rnum, num, location, lname, title, fnum, writer, writeday, readcnt, repIndent, fileName, orgfileName, uploadFolder from  " + 
				"				(" + 
				"					select * from tb_board5 b  " + 
				"					left join tb_location l on b.location = l.lid  " + 
				"					left join TB_FILEUPLOAD f on b.num = f.fnum " + 
				"				order by repRoot desc, repStep asc" + 
				"				)WHERE title LIKE DECODE(?, NULL, '%', '%"+ searchTitle +"%') and location LIKE DECODE(?, NULL, '%', ?) and fnum is not null" + 
				"				)	where rnum>= ? and rnum<=?";
		ResultSet rs = null;
		Board5ImgPageTO to = new Board5ImgPageTO(curPage);
		List<Board5DTO> list = new ArrayList<Board5DTO>();
		
		try {
			conn = dataFactory.getConnection();
			int amount = getImgAmount(conn, searchTitle, nowLocationID);	
			to.setAmount(amount);
			System.out.println("이미지 게시글 개수" + to.getAmount());
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchTitle);
			pstmt.setString(2, nowLocationID);
			pstmt.setString(3, nowLocationID);
			pstmt.setInt(4, to.getStartNum());
			pstmt.setInt(5, to.getEndNum());
	
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				int num = rs.getInt("num");
				String title = rs.getString("title");
				String writer = rs.getString("writer");
				String locationName = rs.getString("lname");
				String writeday = rs.getString("writeday");
				int readcnt = rs.getInt("readcnt");
				int repIndent = rs.getInt("repIndent");
				int fileNum = rs.getInt("fnum");
				
				String fileName = rs.getString("fileName");
				String orgFileName = rs.getString("orgfileName");
				String uploadFolder = rs.getString("uploadFolder");
				
				
				Board5FileDTO fileDTO = new Board5FileDTO(fileName, orgFileName, uploadFolder);
				list.add(new Board5DTO(num, writer, nowLocationID, locationName, title, null, writeday, readcnt, -1, -1, repIndent, fileNum, fileDTO));
				
			}
			to.setNowLocationID(nowLocationID);
			to.setList(list);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closAll(rs, pstmt, conn);
		}
		
		return to;
	}
	
	private int getAmount(Connection conn, String searchTitle, String location) {
		int amount = 0;
		PreparedStatement pstmt = null;
		String sql = "select count(num) from tb_board5 where title LIKE DECODE(?, NULL, '%', '%"+ searchTitle +"%') and location LIKE DECODE(?, NULL, '%', ?)";
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchTitle);
			pstmt.setString(2, location);
			pstmt.setString(3, location);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				amount = rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closAll(rs, pstmt, null);
		}
		
		return amount;
	}

	private int getImgAmount(Connection conn, String searchTitle, String location) {
		int amount = 0;
		PreparedStatement pstmt = null;
		String sql = "select count(num) from tb_board5 b " + 
			" left join tb_fileUpload f on b.num = f.fnum where fnum is not null " + 
			" and title LIKE DECODE(?, NULL, '%', '%"+ searchTitle +"%') and location LIKE DECODE(?, NULL, '%', ?)";
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchTitle);
			pstmt.setString(2, location);
			pstmt.setString(3, location);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				amount = rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closAll(rs, pstmt, null);
		}
		
		return amount;
	}
	
	public int insert(Board5DTO dto, Board5FileDTO fileDTO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql_board5 = "insert into tb_board5 (num, writer, location, title, content, repRoot, repStep, repIndent) values" + 
										"(?, ?, ?, ?, ?, ?, 0. ,0)";
		String sql_fileUpload = "insert into tb_fileUpload (fnum, fileName, orgfileName, uploadFolder) values (?, ?, ?, ?)";
		int num = -1;
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql_board5);
			
			num = createNum(conn);
			
			pstmt.setInt(1, num);
			pstmt.setString(2, dto.getWriter());
			pstmt.setString(3, dto.getLocationID());
			pstmt.setString(4, dto.getTitle());
			pstmt.setString(5, dto.getContent());
			pstmt.setInt(6, num);
			pstmt.executeUpdate();
			
			if(fileDTO != null) {
				pstmt.clearParameters();
				
				pstmt = conn.prepareStatement(sql_fileUpload);
				pstmt.setInt(1, num);
				pstmt.setString(2, fileDTO.getFileName());
				pstmt.setString(3, fileDTO.getOrgFileName());
				pstmt.setString(4, fileDTO.getUploadFolder());
				pstmt.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closAll(null, pstmt, conn);
		}
		return num;
	}


	public Board5DTO readBoard(int num) {
		Board5DTO boardDTO = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "select * from tb_board5 b left join TB_LOCATION l on b.location = l.lid where num = ?";
		ResultSet rs = null;
		
		boolean isOK = false;
		
		try {
			conn = dataFactory.getConnection();
			conn.setAutoCommit(false);

			increaseReadcnt(conn, num);
			
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String writer = rs.getString("writer");
				String locationID = rs.getString("lid");
				String locationName = rs.getString("lname");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String writeday = rs.getString("writeday");
				int readcnt = rs.getInt("readcnt");
				int repRoot = rs.getInt("repRoot");
				int repStep = rs.getInt("repStep");
				int repIndent = rs.getInt("repIndent");
				
				boardDTO = new Board5DTO(num, writer, locationID, locationName, title, content, writeday, readcnt, repRoot, repStep, repIndent, -1);
			}
			isOK = true;
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(isOK) {
					conn.commit();
				}else {
					conn.rollback();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			closAll(rs, pstmt, conn);
		}
		
		return boardDTO;
	}
	
	public Board5FileDTO readFile(int num) {
		Board5FileDTO fileDTO = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "select * from tb_fileUpload where fnum = ?";
		ResultSet rs = null;
		
		boolean isOK = false;

		try {
			conn = dataFactory.getConnection();
			
			conn.setAutoCommit(false);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String fileName = rs.getString("fileName");
				String orgFileName = rs.getString("orgfileName");
				String uploadFolder = rs.getString("uploadFolder");
				
				fileDTO = new Board5FileDTO(fileName, orgFileName, uploadFolder);
			}
			isOK = true;
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(isOK) {
					conn.commit();
				}else {
					conn.rollback();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			closAll(rs, pstmt, conn);
		}
		
		return fileDTO;
	}
	
	private void stepPlus(Connection conn, Board5DTO orgDTO) {
		PreparedStatement pstmt = null;
		String sql = "update tb_board5 set repStep = repStep + 1 where repRoot = ? and repStep > ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, orgDTO.getRepRoot());
			pstmt.setInt(2, orgDTO.getRepStep());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closAll(null, pstmt, null);
		}
	}


	private void increaseReadcnt(Connection conn, int num) {
		PreparedStatement pstmt = null;
		String sql = "update tb_board5 set readcnt = readcnt+1 where num = ?";
				
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closAll(null, pstmt, null);
		}
	}
	
	private int createNum(Connection conn) {
		PreparedStatement pstmt = null;
		String sql = "select max(num) from tb_board5";
		ResultSet rs = null;
		Integer num = null;
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				num = rs.getInt(1);
				num++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//conn 닫으면 큰일납니다.
			closAll(rs, pstmt, null);
		}
		return num;
	}
	
	private void closAll(ResultSet rs, PreparedStatement pstmt,  Connection conn) {
		try {
			if(rs != null) {
				rs.close();
			}
			if(pstmt != null) {
				pstmt.close();
			}
			if(conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Board4LocationDTO> locations() {
		Connection conn = null;
		List<Board4LocationDTO> locations = new ArrayList<Board4LocationDTO>();
		PreparedStatement pstmt = null;
		String sql = "select * from tb_location";
		ResultSet rs = null;
		
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String lid = rs.getString("lid");
				String lname = rs.getString("lname");
				
				locations.add(new Board4LocationDTO(lid, lname));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closAll(rs, pstmt, conn);
		}
		
		return locations;
	}

}
