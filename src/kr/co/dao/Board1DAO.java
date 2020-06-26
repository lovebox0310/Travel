package kr.co.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import kr.co.domain.BoardDTO;
import kr.co.dto.Board1DTO;
import kr.co.to.PageTO;

public class Board1DAO {
   
private DataSource dataFactory;
	
	public Board1DAO() {
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
		
		public List<Board1DTO> list() {
			List<Board1DTO> list = new ArrayList<Board1DTO>();
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = "select * from board1 order by repRoot desc, repStep asc";
			ResultSet rs = null;
			try {
				conn = dataFactory.getConnection();
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					int num = rs.getInt("num");
					String writer = rs.getString("writer");
					String title = rs.getString("title");
					String writeday = rs.getString("writeday");
					int readcnt = rs.getInt("readcnt");
					int repRoot = rs.getInt("repRoot");
					int repStep = rs.getInt("repStep");
					int repIndent = rs.getInt("repIndent");
					list.add(new Board1DTO(num, writer, title, null, writeday, readcnt, repRoot, repStep, repIndent));
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				closeAll(rs, pstmt, conn);
			}
			return list;

		}

		public void insert(Board1DTO board1DTO) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = "insert into board1 (num, writer, title, content, readcnt, repRoot, repStep, repIndent) values (?,?,?,?,?,?,?,?)";

			try {
				conn = dataFactory.getConnection();
				pstmt = conn.prepareStatement(sql);

				int num = createNum(conn);

				pstmt.setInt(1, num);
				pstmt.setString(2, board1DTO.getWriter());
				pstmt.setString(3, board1DTO.getTitle());
				pstmt.setString(4, board1DTO.getContent());
				pstmt.setInt(5, 0);
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

		private int createNum(Connection conn) {
			
			PreparedStatement pstmt = null;
			String sql = "select max(num) from board1";
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
				closeAll(rs, pstmt, null);
			}
			return num;
		}

		public Board1DTO read(int num) {
			
			Board1DTO dto = null;
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = "select * from board1 where num=?";
			ResultSet rs = null;

			boolean isOk = false;

			try {
				conn = dataFactory.getConnection();
				conn.setAutoCommit(false);

				increaseReadCnt(conn, num);

				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, num);
				rs = pstmt.executeQuery();
				if (rs.next()) {

					String writer = rs.getString("writer");
					String title = rs.getString("title");
					String writeday = rs.getString("writeday");
					int readcnt = rs.getInt("readcnt");
					String content = rs.getString("content");
					
					dto = new Board1DTO(num, writer, title, content, writeday, readcnt, 0, 0, 0);

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

		private void increaseReadCnt(Connection conn, int num) {
			PreparedStatement pstmt = null;
			String sql = "update board1 set readcnt = readcnt +1 where num=?";

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

		public Board1DTO updateUI(int num) {
			Board1DTO dto = null;
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = "select * from board1 where num=?";
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
					int repRoot = rs.getInt("repRoot");
					int repStep = rs.getInt("repStep");
					int repIndent = rs.getInt("repIndent");

					dto = new Board1DTO(num, writer, title, content, null, 0, repRoot, repStep, repIndent);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				closeAll(rs, pstmt, conn);
			}

			return dto;
		}

		public void update(Board1DTO board1DTO) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = "update board1 set writer=?, title=?, content=? where num =?";

			try {
				conn = dataFactory.getConnection();
				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, board1DTO.getWriter());
				pstmt.setString(2, board1DTO.getTitle());
				pstmt.setString(3, board1DTO.getContent());
				pstmt.setInt(4, board1DTO.getNum());

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
			String sql = "delete from board1 where num=?";
			
			try {
				conn = dataFactory.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, num);
				
				pstmt.executeUpdate();
			}catch (Exception e) {
				e.printStackTrace();
			}finally {
				closeAll(null,pstmt,conn);
			}
			
		}

		public void reply(int orgnum, Board1DTO dto) {
			// TODO Auto-generated method stub
			Connection conn = null;
			PreparedStatement pstmt = null;
			String sql = "insert into board1 (num, writer, title, content,readcnt, repRoot, repStep, repIndent) values (?,?,?,?,?,?,?,?)";
			boolean isOk = false;
			
			try {
				conn = dataFactory.getConnection();
				conn.setAutoCommit(false);
				
				int num = createNum(conn);
				
				Board1DTO orgDTO = updateUI(orgnum);
				stepPlus1(conn, orgDTO);
				
				pstmt =  conn.prepareStatement(sql);
				
				pstmt.setInt(1, num);
				pstmt.setString(2, dto.getWriter());
				pstmt.setString(3, dto.getTitle());
				pstmt.setString(4, dto.getContent());
				pstmt.setInt(5, dto.getReadcnt());
				pstmt.setInt(6, orgDTO.getRepRoot());
				pstmt.setInt(7, orgDTO.getRepStep()+1);
				pstmt.setInt(8, orgDTO.getRepIndent()+1);
				
				pstmt.executeUpdate();
				
				isOk = true;
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if(isOk) {
						conn.commit();
					} else {
						conn.rollback();
					}
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}	
			    closeAll(null, pstmt, conn);	
		}
	}

		private void stepPlus1(Connection conn, Board1DTO orgDTO) {
			// TODO Auto-generated method stub
			PreparedStatement pstmt = null;
			String sql = "update board1 set repStep=repStep+1 where repRoot=? and repStep>?";
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, orgDTO.getRepRoot());
				pstmt.setInt(2, orgDTO.getRepStep());
				
				pstmt.executeUpdate();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally {
				closeAll(null, pstmt, null);
			}
		}

		public PageTO page(int curPage) {
			String sql= "select * from (select rownum rnum, num, title, writer, writeday, readcnt, repIndent from(select * from board1 order by repRoot desc, repStep asc)) where rnum>=? and rnum<=?";

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
					int repIndent = rs.getInt("repIndent");
					
					Board1DTO dto = new Board1DTO(num, writer, title, null, writeday, readcnt, -1, -1, repIndent);
					
					list.add(dto);
				}
				to.setList(list);
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			} finally {
				closeAll(rs, pstmt, conn);
			}
			
			return to;
		}
		
		private int getAmount(Connection conn) {
			int amount = 0;
			
			PreparedStatement pstmt = null;
			String sql = "select count(num) from board1";
			ResultSet rs = null;
			
			try {
	            pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if(rs.next())
			    amount = rs.getInt(1);
				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				closeAll(rs, pstmt, null);
			}
			return amount;
		}
		
	}
