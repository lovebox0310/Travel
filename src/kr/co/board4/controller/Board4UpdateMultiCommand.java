package kr.co.board4.controller;

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

public class Board4UpdateMultiCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
		String sNum = multi.getParameter("num");
		String location = multi.getParameter("location");
		String thema = multi.getParameter("thema");
		String writer = multi.getParameter("writer");
		String title = multi.getParameter("title");
		String content = multi.getParameter("content");
		
		int num = 0;
		if (sNum != null) {
			num = Integer.parseInt(sNum);
		}
		
		Board4DAO dao = new Board4DAO();
		dao.update(new Board4DTO(num, writer, title, content, location, thema, filename, null, 0, 0, 0, 0));
		
		System.out.println("update num " + num);
		
		return new CommandAction(false, "board4updateui.do?num="+num);
	}

}
