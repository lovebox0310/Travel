package kr.co.travel5.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.domain.Command;
import kr.co.domain.CommandAction;

public class LoginUICommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String tag = request.getParameter("tag");
		if(tag == null) {
			tag = "01";
		}
		request.setAttribute("tag", tag);
		
		return new CommandAction(false, "Board5/login.jsp");
	}

}
