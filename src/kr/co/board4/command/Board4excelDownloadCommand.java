package kr.co.board4.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.domain.Command;
import kr.co.domain.CommandAction;

public class Board4excelDownloadCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * board4 list 준비... 
		 * 
		*/
		
		
		return new CommandAction(false, "board4/excelDownload.jsp");
	}

}
