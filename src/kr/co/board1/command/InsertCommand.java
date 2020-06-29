package kr.co.board1.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.dao.Board1DAO;
import kr.co.domain.Command;
import kr.co.domain.CommandAction;
import kr.co.dto.Board1DTO;

public class InsertCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");	
		
		Board1DAO dao = new Board1DAO();
		dao.insert(new Board1DTO(-1, writer, title, content, null, 0, 0, 0, 0));
		
//		   for(int i=0;i<100;i++) {w
//	       dao.insert(new BoardDTO(-1, writer, title, content, null, 0, 0, 0, 0));
//	       try {
//	            Thread.sleep(50); //0.05초마다 멈춤. 너무 빠른 속도로 인해 일어나는 에러 방지
//	       } catch (InterruptedException e) {
//	          e.printStackTrace();
//	        }
//	    }
		

		return new CommandAction(true, "board1list.do");

	}

}

