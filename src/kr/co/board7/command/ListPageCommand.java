package kr.co.board7.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.dao.Board7DAO;
import kr.co.dao.MemberDAO;
import kr.co.domain.Command;
import kr.co.domain.CommandAction;
import kr.co.dto.LoginDTO;
import kr.co.dto.MemberDTO;
import kr.co.to.PageTO;

public class ListPageCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String scurPage = request.getParameter("curPage");
		int curPage = 1;
		if(scurPage!=null) {
			curPage = Integer.parseInt(scurPage);
		}
		String id = request.getParameter("id");
		String search = request.getParameter("search");
		String find = request.getParameter("find");
		if(find==null)find="title";
		if(search==null)search="";
		
		
		
		MemberDAO memberdao = new MemberDAO();
		LoginDTO loginDTO = new LoginDTO(id, null);
		MemberDTO member = memberdao.selectById(loginDTO);
				
		Board7DAO dao = new Board7DAO();
		PageTO to = dao.page(find,search,curPage);
		
		request.setAttribute("search", search);
		request.setAttribute("find",find);
		request.setAttribute("to", to);
		request.setAttribute("list", to.getList());
		request.setAttribute("writer", member.getName());
		return new CommandAction(false, "board7/qnalist.jsp");
	}

}
