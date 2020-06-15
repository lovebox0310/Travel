package kr.co.board4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.dao.Board4DAO;
import kr.co.domain.Command;
import kr.co.domain.CommandAction;
import kr.co.to.PageTO;

public class Board4ListCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sCurPage = request.getParameter("curPage");
		String location = request.getParameter("location");
		String thema = request.getParameter("htema");
		
		int curPage = 1;
		if (sCurPage != null) {
			curPage = Integer.parseInt(sCurPage);
		}
		if (location == null) {
			location = "";
		}
		if (thema == null) {
			thema = "";
		}
		
		Board4DAO dao = new Board4DAO();
		PageTO to = dao.page(curPage, location, thema);
		
		request.setAttribute("to", to);
		request.setAttribute("list", to.getList());
		
		System.out.println(to.getList().size());
		
		return new CommandAction(false, "board4/board4list.jsp");
	}

}
