package kr.co.board7.command;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.dao.Board7DAO;
import kr.co.domain.Command;
import kr.co.domain.CommandAction;
import kr.co.dto.Board7DTO;

public class MyAskListCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		Board7DAO dao = new Board7DAO();

		List<Board7DTO> list = dao.getAskRepRoots(id);// repRoot 리스트
		String writer = dao.getWriter(id);
		
		request.setAttribute("writer", writer);
		request.setAttribute("list", list);

		return new CommandAction(false, "board7/asklist.jsp");
	}

}
