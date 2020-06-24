package kr.co.travel5.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.domain.Command;
import kr.co.domain.CommandAction;
import kr.co.dto.*;

public class ReplyUICommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sNum = request.getParameter("num");
		int num = -1;
		if(sNum != null) {
			num = Integer.parseInt(sNum);
		}
		
		String locationID = request.getParameter("locationID");
		String title = request.getParameter("title");
		
		request.setAttribute("dto", new Board5DTO(num, "replyer", locationID, null, title, null, null, 0, 0, 0, 0, -1));
		
		
		return new CommandAction(false, "Board5/reply.jsp");
	}

}
