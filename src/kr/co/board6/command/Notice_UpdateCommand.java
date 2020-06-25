package kr.co.board6.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.dao.Board6DAO;
import kr.co.domain.Command;
import kr.co.domain.CommandAction;
import kr.co.dto.Board6NoticeDTO;

public class Notice_UpdateCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int num = -1;
		String sNum = request.getParameter("num");
		if (sNum != null) {
			num = Integer.parseInt(sNum);
		}
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String filename = request.getParameter("filename");	
		
		Board6DAO dao = new Board6DAO();
		dao.update(new Board6NoticeDTO(num, writer, title, content, null, 0, filename));
		
		return new CommandAction(true, "board6notice_list.do");
	}

}
