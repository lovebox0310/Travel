package kr.co.board2.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.dao.Board2DAO;
import kr.co.domain.Command;
import kr.co.domain.CommandAction;
import kr.co.dto.Board2SiteDTO;
import kr.co.to.PageTO;

public class ListPageCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String scurPage = request.getParameter("curPage");	
		int curPage = 1;
		if (scurPage != null) {
			curPage = Integer.parseInt(scurPage);
		}
		String sid = request.getParameter("sid");
		
		Board2DAO dao = new Board2DAO();
		PageTO to = dao.page(curPage, sid);
		List<Board2SiteDTO> siteList = dao.site();
				
		request.setAttribute("to", to);
		request.setAttribute("list", to.getList());
		request.setAttribute("siteList", siteList);
		request.setAttribute("sid", sid);
		
		return new CommandAction(false, "board2/list.jsp");
	}
}