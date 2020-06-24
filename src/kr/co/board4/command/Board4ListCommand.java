package kr.co.board4.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.dao.Board4DAO;
import kr.co.domain.Command;
import kr.co.domain.CommandAction;
import kr.co.dto.Board4LocationDTO;
import kr.co.dto.Board4ThemaDTO;
import kr.co.to.PageTO;

public class Board4ListCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sCurPage = request.getParameter("curPage");
		String location = request.getParameter("location");
		String thema = request.getParameter("thema");
		
		int curPage = 1;
		if (sCurPage != null) {
			curPage = Integer.parseInt(sCurPage);
		}
		
//		System.out.println("curPage "+curPage+"location "+location+"thema "+thema);
		
		Board4DAO dao = new Board4DAO();
		PageTO to = dao.page(curPage, location, thema);
		List<Board4LocationDTO> locationList = dao.locationList();
		List<Board4ThemaDTO> themaList = dao.ThemaList();
		
		request.setAttribute("to", to);
		request.setAttribute("list", to.getList());
		
		request.setAttribute("location", location);
		request.setAttribute("thema", thema);
		
		request.setAttribute("locationList", locationList);
		request.setAttribute("themaList", themaList);
		
//		System.out.println(to.getList().size());
		
		return new CommandAction(false, "board4/list.jsp");
	}

}
