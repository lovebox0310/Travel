package kr.co.board1.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.dao.Board1DAO;
import kr.co.domain.Command;
import kr.co.domain.CommandAction;
import kr.co.dto.Board1DTO;

public class UpdateCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int num = -1;
		String sNum = request.getParameter("num");
		if (sNum != null) {
			num = Integer.parseInt(sNum);
		}
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");

		Board1DAO dao = new Board1DAO();
		dao.update(new Board1DTO(num, writer, title, content, null, -1, -1, -1, -1));
		
		return new CommandAction(true, "board1list.do");
	}
}
