package kr.co.travel5.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.dao.MemberDAO;
import kr.co.domain.Command;
import kr.co.domain.CommandAction;
import kr.co.dto.*;

public class LoginCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String tag = request.getParameter("tag");
		System.out.println("tag "+ tag);
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		
		boolean isOK = false;
		

		isOK = new MemberDAO().loginBoard5(new Board5loginDTO(id, pw, null), tag);
		
		if(isOK) {
			HttpSession session = request.getSession();
			session.setAttribute("login", new Board5loginDTO(id, null, tag));
					
			return new CommandAction(false, "Board5list.do");
		}	
		
		request.setAttribute("tag", tag);
		return new CommandAction(false, "Board5loginui.do");
	}
}
