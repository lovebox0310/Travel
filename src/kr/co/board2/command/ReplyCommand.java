package kr.co.board2.command;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.co.dao.Board2DAO;
import kr.co.domain.Command;
import kr.co.domain.CommandAction;
import kr.co.dto.Board2DTO;
import kr.co.dto.Board2FileDTO;

public class ReplyCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String realPath = request.getSession().getServletContext().getRealPath("/img");

		File uploadFolder = new File(realPath);
		if (!uploadFolder.exists()) {
			uploadFolder.mkdir();
		}
		MultipartRequest multi = new MultipartRequest(request, realPath, 10 * 1024 * 1024, "utf-8",
				new DefaultFileRenamePolicy());
		
		String sNum = multi.getParameter("num");
		int orgnum = -1;
		if (sNum != null) {
			orgnum = Integer.parseInt(sNum);
		}
		String writer = multi.getParameter("writer");
		String title = multi.getParameter("title");
		String content = multi.getParameter("content");
		String location = multi.getParameter("location");
		String fName = multi.getFilesystemName("file");
		String ogFName = multi.getOriginalFileName("file");
		
		Board2DAO dao = new Board2DAO();
	
		if(fName != null) {
			dao.reply(orgnum, new Board2DTO(-1, writer, title, content, null, location, 0, 0, 0, 0),
					new Board2FileDTO(-1, fName, ogFName, null));
		} else {
			dao.reply(orgnum, new Board2DTO(-1, writer, title, content, null, location, 0, 0, 0, 0));
		}
		
		return new CommandAction(true, "board2list.do");
	}
}