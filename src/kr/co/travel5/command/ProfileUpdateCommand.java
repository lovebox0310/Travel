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

public class ProfileUpdateCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if(session != null) {
			Board5loginDTO login = (Board5loginDTO) session.getAttribute("login");
			if(login != null) {
				String id = login.getId();
				String pw = request.getParameter("pw");
				String name = request.getParameter("name");
				String sAge = request.getParameter("age");
				int age = -1;
				if(sAge != null) {
					age = Integer.parseInt(sAge);
				}
				
				System.out.println(id + pw + name + age);
				
				new MemberDAO().profileUpdate(new MemberDTO(id, name, age, pw,  null));
				
				return new CommandAction(true, "Board5profile.do");
			}
		}
		
		return new CommandAction(true, "Board5list.do");
		
		

	}

}
