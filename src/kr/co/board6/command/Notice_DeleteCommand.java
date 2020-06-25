package kr.co.board6.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.dao.Board6DAO;
import kr.co.domain.Command;
import kr.co.domain.CommandAction;

public class Notice_DeleteCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String sNum = request.getParameter("num");
		int num = -1;
		if(sNum != null) {
			num = Integer.parseInt(sNum);
		}
		
		Board6DAO dao = new Board6DAO();
		dao.delete(num);
		
		return new CommandAction(true, "board6notice_list.do");
	}

}
