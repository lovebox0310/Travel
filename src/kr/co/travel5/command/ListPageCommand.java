package kr.co.travel5.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.dao.Board5DAO;
import kr.co.domain.Command;
import kr.co.domain.CommandAction;
import kr.co.dto.*;
import kr.co.to.PageTO;

public class ListPageCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String scurPage = request.getParameter("curPage");
		int curPage = 1;
		if(scurPage != null) {
			curPage = Integer.parseInt(scurPage);
		}
		String locationID = request.getParameter("locationID");
		String searchTitle = request.getParameter("searchTitle");
		
		Board5DAO dao = new Board5DAO();
		PageTO to = null;
		List<Board4LocationDTO> locations = dao.locations();

		to = dao.page(curPage, searchTitle, locationID);
		
		
		request.setAttribute("to", to);
		request.setAttribute("list", to.getList());		//나중에 바꿔도 됨
		request.setAttribute("locations", locations);
		request.setAttribute("searchTitle", searchTitle);
		request.setAttribute("nowLocationID", locationID);
		
		return new CommandAction(false, "Board5/list.jsp");
	}

}
