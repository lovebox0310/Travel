package kr.co.travel5.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.dao.Board5DAO;
import kr.co.domain.Command;
import kr.co.domain.CommandAction;
import kr.co.dto.*;

public class ReadCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sNum = request.getParameter("num");
		int num = -1;
		if(sNum != null) {
			num = Integer.parseInt(sNum);
		}
		
		Board5DAO dao = new Board5DAO();
		Board5DTO boardDTO = dao.readBoard(num);
		Board5FileDTO fileDTO = dao.readFile(num);
		
		request.setAttribute("boardDTO", boardDTO);
		request.setAttribute("fileDTO", fileDTO);
		
		return new CommandAction(false, "Board5/read.jsp");
	}

}
