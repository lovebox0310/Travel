package kr.co.board6.command;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.dao.Board6DAO;
import kr.co.domain.Command;
import kr.co.domain.CommandAction;
import kr.co.dto.QnaCommandDTO;

public class PutQnaCommentCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String sNum = request.getParameter("num");
		int num = -1;
		if (sNum != null) {
			num = Integer.parseInt(sNum);
		}
		
		
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		
		QnaCommandDTO dto = new QnaCommandDTO(id, 1, 1, writer, content,null, -1, -1, -1,null);
		Board6DAO dao = new Board6DAO();
		dao.insertQnaCom(dto, num);
		dao.updateReadcnt(num);
		
		return new CommandAction(true, "board6read.do?num="+num+"&id="+id);
	}

}
