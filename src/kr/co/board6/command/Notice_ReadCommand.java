package kr.co.board6.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.dao.Board6DAO;
import kr.co.domain.Command;
import kr.co.domain.CommandAction;
import kr.co.dto.Board6CommentDTO;
import kr.co.dto.Board6NoticeDTO;

public class Notice_ReadCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String sNum = request.getParameter("num");
		int num = -1;
		if (sNum != null) {
			num = Integer.parseInt(sNum);
		}

		Board6DAO dao = new Board6DAO();
		Board6NoticeDTO dto = dao.read(num);

		List<Board6CommentDTO> cList = dao.cList(num); // 댓글 목록 가져오기

		request.setAttribute("dto", dto);
		request.setAttribute("cList", cList);
		
		return new CommandAction(false, "board6/notice_read.jsp");

	}

}
