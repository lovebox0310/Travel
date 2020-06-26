package kr.co.board6.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.dao.Board6DAO;
import kr.co.domain.Command;
import kr.co.domain.CommandAction;
import kr.co.dto.Board6CommentDTO;

public class Comment_InsertCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//		String scomment_num = request.getParameter("comment_num");
//		int comment_num = -1;
//
//		if (scomment_num != null) {
//			comment_num = Integer.parseInt(scomment_num);
//		}
		String scomment_board = request.getParameter("comment_board");
		int comment_board = -1;
		if (scomment_board != null) {
			comment_board = Integer.parseInt(scomment_board);
		}

		String comment_content = request.getParameter("comment_content");
		String comment_writer = request.getParameter("comment_writer");

//		String comment_day = request.getParameter("comment_day");
		
		Board6DAO dao = new Board6DAO();
		Board6CommentDTO cDto = new Board6CommentDTO(-1, comment_board, comment_content, comment_writer, null, 0, 0, 0);
		
		dao.comment_insert(cDto);
		dao.decreaseReadCnt(comment_board);
		
		return new CommandAction(true, "board6notice_read.do?num="+comment_board);
	}

}
