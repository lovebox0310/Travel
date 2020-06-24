package kr.co.board2.command;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.dao.Board2DAO;
import kr.co.domain.Command;
import kr.co.domain.CommandAction;
import kr.co.dto.Board2DTO;

public class InsertCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String location = request.getParameter("location");

		Board2DAO dao = new Board2DAO();
		dao.insert(new Board2DTO(-1, writer, title, content, null, location, 0, 0, 0, 0));

//		for (int i = 0; i < 100; i++) {
//			dao.insert(new BoardDTO(-1, "writer" + i, "title" + i, "content" + i, null, "001", 0, 0, 0, 0));
//			try {
//				Thread.sleep(50);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}

		return new CommandAction(true, "board2list.do");
	}
}