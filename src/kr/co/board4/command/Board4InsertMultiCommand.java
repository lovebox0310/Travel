package kr.co.board4.command;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.co.dao.Board4DAO;
import kr.co.domain.Command;
import kr.co.domain.CommandAction;
import kr.co.dto.Board4DTO;

public class Board4InsertMultiCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		MultipartRequest multi = null;
		int sizeLimit = 1024 * 1024 * 10;

		ServletContext context = request.getSession().getServletContext();
		String realFolder = context.getRealPath("upload");
		
		try {
			multi = new MultipartRequest(request, realFolder, sizeLimit, "UTF-8", new DefaultFileRenamePolicy());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String filename = multi.getFilesystemName("filename");
		String writer = multi.getParameter("writer");
		String title = multi.getParameter("title");
		String content = multi.getParameter("content");
		String location = multi.getParameter("location");
		String thema = multi.getParameter("thema");
		
		System.out.println(""
				+ "  writer "  + writer
				+ ", title "   + title
				+ ", content " + content
				+ ", location "+ location
				+ ", thema "   + thema
				+ ", filename "+ filename
				);

		Board4DTO dto = new Board4DTO(-1, writer, title, content, location, thema, filename, null, 0, 0, 0, 0);
		Board4DAO dao = new Board4DAO();
		dao.insert(dto);
		
		return new CommandAction(true, "board4list.do?curPage=1&location=000&thema=000");
	}

}
