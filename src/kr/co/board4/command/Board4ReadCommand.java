package kr.co.board4.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.dao.Board4DAO;
import kr.co.domain.Command;
import kr.co.domain.CommandAction;
import kr.co.dto.Board4DTO;

public class Board4ReadCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		String sNum = request.getParameter("num");
		int num = 0;
		if (sNum != null) {
			num = Integer.parseInt(sNum);
		}
//		System.out.println("sNum "+sNum);
		
		Board4DAO dao = new Board4DAO();
		Board4DTO dto = dao.read(num);
		
		int totalNum = dao.getAmount();
		
		request.setAttribute("dto", dto);
		request.setAttribute("totalNum", totalNum);
		
		return new CommandAction(false, "board4/read.jsp");
	}

}
