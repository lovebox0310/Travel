package kr.co.board6.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.dao.Board6DAO;
import kr.co.domain.Command;
import kr.co.domain.CommandAction;

public class Comment_DeleteCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String scomment_num = request.getParameter("comment_num");
		int comment_num = -1;

		if (scomment_num != null) {
			comment_num = Integer.parseInt(scomment_num);
		}
		
		String scomment_board = request.getParameter("comment_board");
		int comment_board = -1;
		if (scomment_board != null) {
			comment_board = Integer.parseInt(scomment_board);
		}
		
		String srepRoot = request.getParameter("repRoot");
		int repRoot = -1;
		if (srepRoot != null) {
			repRoot = Integer.parseInt(srepRoot);
		}
		
		String srepIndent = request.getParameter("repIndent");
		int repIndent = -1;
		if (srepIndent != null) {
			repIndent = Integer.parseInt(srepIndent);
		}
		
		Board6DAO dao = new Board6DAO();
		dao.decreaseReadCnt(comment_board);
		
		if(repIndent == 0) {
			dao.comment_deleteAll(repRoot, comment_board);
		} else {
			dao.comment_delete(comment_num);			
		}
		
		return new CommandAction(true, "board6notice_read.do?num=" + comment_board);
	}

}
