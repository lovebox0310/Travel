package kr.co.board7.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.dao.Board7DAO;
import kr.co.domain.Command;
import kr.co.domain.CommandAction;
import kr.co.dto.Board7DTO;

public class QnaUpdateUICommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");//로그인된 아이디
		String sNumber = request.getParameter("num");
		
		int number = -1;
		if (sNumber != null) {
			number = Integer.parseInt(sNumber);
		}
		String writer = request.getParameter("writer");
		
		
		Board7DAO dao = new Board7DAO();
		Board7DTO dto = dao.updateUI(number);

		request.setAttribute("dto", dto);
		request.setAttribute("id", id);

		request.setAttribute("writer", writer);
		return new CommandAction(false, "board7/qnaUpdate.jsp");
	}

}
