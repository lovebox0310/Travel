package kr.co.board6.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.dao.Board6DAO;
import kr.co.dao.MemberDAO;
import kr.co.domain.Command;
import kr.co.domain.CommandAction;
import kr.co.dto.Board6DTO;
import kr.co.dto.LoginDTO;
import kr.co.dto.MemberDTO;
import kr.co.dto.QnaCommandDTO;

public class ReadCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id"); // 현재 로그인 되어있는 id
		String sNumber = request.getParameter("num");
		int number = -1;
		if (sNumber != null) {
			number = Integer.parseInt(sNumber);
		}

		Board6DAO dao = new Board6DAO();
		Board6DTO dto = dao.read(number);
		List<QnaCommandDTO> commentlist = dao.readComments(number);
		
		MemberDAO memberdao = new MemberDAO();
		LoginDTO loginDTO = new LoginDTO(id, null);
		MemberDTO member = memberdao.selectById(loginDTO);

		request.setAttribute("dto", dto);
		request.setAttribute("commentlist", commentlist);
		
		request.setAttribute("writer", member.getName());
		return new CommandAction(false, "board6/read.jsp?id=" + id);

	}

}
