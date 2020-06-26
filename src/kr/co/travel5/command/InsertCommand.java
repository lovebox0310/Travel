package kr.co.travel5.command;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.sun.glass.ui.Application;

import kr.co.dao.Board5DAO;
import kr.co.domain.Command;
import kr.co.domain.CommandAction;
import kr.co.dto.*;

public class InsertCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String realPath = request.getSession().getServletContext().getRealPath(File.separator + "img");
		File uploadFolder = new File(realPath);
		//폴더 존재 유무 확인(기존 폴더 내 데이터 날아갈 수 있기 때문
		if(!uploadFolder.exists()) {
			System.out.println("img폴더가 없는 관계로 생성합니다.");
			uploadFolder.mkdir();
		}

		String title = "";
		String content = "";
		String locationID = "";
		String writer = "";
		String fileName = "";
		String orgFileName = "";

		// 10mb만 하는걸로 -> 객체 만드는 순간 업로드 끝남
		MultipartRequest multi = new MultipartRequest(request, realPath, 10 * 1024 * 1024, "utf-8",
				new DefaultFileRenamePolicy()); // 파일이 중복됐을때 정책
		title = multi.getParameter("title"); // 바로 파라미터 받으면 안됨. 데이터를 쪼개서 받아서
		content = multi.getParameter("content");
		locationID = multi.getParameter("location");
		writer = multi.getParameter("writer");
		fileName = multi.getFilesystemName("file");
		orgFileName = multi.getOriginalFileName("file");

		Board5DAO dao = new Board5DAO();
		int num = -1;

		if(fileName!=null) {
		num = dao.insert(new Board5DTO(-1, writer, locationID, null, title, content, null, 0, 0, 0, 0, -1),
				new Board5FileDTO(fileName, orgFileName, realPath));
		}else {
			num = dao.insert(new Board5DTO(-1, writer, locationID, null, title, content, null, 0, 0, 0, 0, -1), null);
		}

		Board5DTO boardDTO = dao.updateUI(num);
		Board5FileDTO fileDTO = dao.readFile(num);
		request.setAttribute("fileDTO", fileDTO);
		request.setAttribute("boardDTO", boardDTO);
		request.setAttribute("multi", multi);

		return new CommandAction(false, "Board5/read.jsp");
	}

}
