package kr.co.board7.command;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.dao.Board7DAO;
import kr.co.domain.Command;
import kr.co.domain.CommandAction;
import kr.co.dto.QnaCommandDTO;

public class UpdateComCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sNum = request.getParameter("num");
		int num = -1;
		if (sNum != null) {
			num = Integer.parseInt(sNum);
		}
		String sQnanum = request.getParameter("qnanum");
		int qnanum = -1;
		if (sQnanum != null) {
			qnanum = Integer.parseInt(sQnanum);
		}
		String id = request.getParameter("id"); // 로그인된 id
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");

		Board7DAO dao = new Board7DAO();
		dao.updateComment(new QnaCommandDTO(null, -1, num, writer, content, null, -1, -1, -1,null));
		dao.updateReadcnt(qnanum);
		return new CommandAction(true, "board7read.do?num=" + qnanum + "&id=" + id);

	}

}
