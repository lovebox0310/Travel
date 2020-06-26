package kr.co.board1.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.dao.Board1DAO;
import kr.co.domain.Command;
import kr.co.domain.CommandAction;
import kr.co.dto.Board1DTO;

public class ListCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Board1DAO dao = new Board1DAO();
		List<Board1DTO> list = dao.list();
		
		request.setAttribute("list", list);
		
		return new CommandAction(false, "board1/list.jsp");
	}

}
