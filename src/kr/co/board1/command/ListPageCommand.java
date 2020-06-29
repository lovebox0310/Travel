package kr.co.board1.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.dao.Board1DAO;
import kr.co.domain.Command;
import kr.co.domain.CommandAction;
import kr.co.to.PageTO;
public class ListPageCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String scurPage = request.getParameter("curPage");
		int curPage = 1;
		if (scurPage != null) {
			curPage = Integer.parseInt(scurPage);
		}
		
		Board1DAO dao = new Board1DAO();
		PageTO to = dao.page(curPage);
		
		request.setAttribute("to", to);
		request.setAttribute("list", to.getList());
		
		
		return new CommandAction(false, "board1/list.jsp");
	}

}

