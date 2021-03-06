package kr.co.board7.command;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.co.dao.Board7DAO;
import kr.co.domain.Command;
import kr.co.domain.CommandAction;
import kr.co.dto.Board7DTO;

public class ReplyCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		MultipartRequest multi = null;

		int sizeLimit = 10 * 1024 * 1024;

		ServletContext context = request.getSession().getServletContext();
		String savePath = context.getRealPath("/upload");

		try {
			multi = new MultipartRequest(request, savePath, sizeLimit, "UTF-8", new DefaultFileRenamePolicy());

		} catch (Exception e) {
			e.printStackTrace();
		}

		String filename = multi.getFilesystemName("filename");

		String id = multi.getParameter("id");
		String sNum = multi.getParameter("num");
		int orgnum = -1; // 원글의 num
		if (sNum != null) {
			orgnum = Integer.parseInt(sNum);
		}
		String writer = multi.getParameter("writer");
		String title = multi.getParameter("title");
		String content = multi.getParameter("content");

		Board7DAO dao = new Board7DAO();
		Board7DTO dto = new Board7DTO(id, -1, writer, title, content, null, 0, -1, -1, -1, filename);
		dao.reply(orgnum, dto);

		return new CommandAction(true, "board7qnalist.do?id=" + id);

	}

}
