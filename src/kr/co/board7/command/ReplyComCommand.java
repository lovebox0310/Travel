package kr.co.board7.command;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.dao.Board7DAO;
import kr.co.domain.Command;
import kr.co.domain.CommandAction;
import kr.co.dto.QnaCommandDTO;

public class ReplyComCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String sNum = request.getParameter("num");
		int orgnum = -1;
		if (sNum != null) {
			orgnum = Integer.parseInt(sNum);
		}
		
		
		String sQnanum = request.getParameter("qnanum");
		int qnanum = -1;
		if (sQnanum != null) {
			qnanum = Integer.parseInt(sQnanum);
		}
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		String orgWriter = request.getParameter("orgWriter");
		
		Board7DAO dao = new Board7DAO();
		QnaCommandDTO dto = new QnaCommandDTO(id, qnanum, -1, writer, content, null, -1, -1, -1,orgWriter);
		dao.replycomment(orgnum, dto);
		dao.updateReadcnt(qnanum);
		return new CommandAction(true, "board7read.do?num=" + qnanum + "&id=" + id);
	}

}
