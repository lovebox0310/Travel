package kr.co.board1.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.dao.Board1DAO;
import kr.co.domain.Command;
import kr.co.domain.CommandAction;
import kr.co.dto.Board1DTO;

public class ReplyCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String sNum = request.getParameter("num");
		int orgnum = -1;
		if (sNum != null) {
			orgnum = Integer.parseInt(sNum);
		}
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		Board1DAO dao = new Board1DAO();
		
		Board1DTO dto = new Board1DTO(-1, writer, title, content, null, 0, 0, 0, 0);
		
		dao.reply(orgnum, dto);
		
		return new CommandAction(true, "board1list.do");
	}

}
