package kr.co.travel5.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.dao.Board5DAO;
import kr.co.domain.Command;
import kr.co.domain.CommandAction;
import kr.co.dto.*;

public class InsertUICommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Board5DAO dao = new Board5DAO();
		List<Board4LocationDTO> locations = dao.locations();
		
		HttpSession session = request.getSession(false);
		
		if(session != null) {
			Board5loginDTO login = (Board5loginDTO) session.getAttribute("login");
			if(login != null) {
				request.setAttribute("locations", locations);
				
				return new CommandAction(false, "Board5/insert.jsp");		
			}
		}
		
		return new CommandAction(true, "Board5list.do");
	}

}
