package kr.co.travel5.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.domain.Command;
import kr.co.domain.CommandAction;
import kr.co.dto.*;

public class LogoutCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session != null) {
			Board5loginDTO login = (Board5loginDTO) session.getAttribute("login");
			if(login != null) {		//session의 데이터가 바인딩x -> 로그인이 되어있지 않다.
				session.invalidate();					
			}
		}
		return new CommandAction(true, "Board5list.do");
	}

}
