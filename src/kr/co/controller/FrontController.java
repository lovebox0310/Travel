package kr.co.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.domain.Command;
import kr.co.domain.CommandAction;

/**
 * Servlet implementation class FrontController
 */

public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String contp = request.getContextPath();
		String sp = uri.substring(contp.length());
		
		System.out.println(sp);
		
		Command com = null;
		
		Map<String, String> map = new HashMap<String, String>();		
		map.put("/loginui.do", "kr.co.controller.LoginUICommand");
		map.put("/login.do", "kr.co.controller.LoginCommand");
		map.put("/logout.do", "kr.co.controller.LogoutCommand");
		map.put("/insertui.do", "kr.co.controller.InsertUICommand");
		map.put("/insert.do", "kr.co.controller.InsertCommand");
		map.put("/memberinfo.do", "kr.co.controller.MemberinfoCommand");
		map.put("/updateui.do", "kr.co.controller.UpdateUICommand");
		map.put("/update.do", "kr.co.controller.UpdateCommand");
		map.put("/delete.do", "kr.co.controller.DeleteCommand");
		map.put("/selectById.do", "kr.co.controller.SelectByIdCommand");

		map.put("/board4list.do", "kr.co.board4.controller.Board4ListCommand");
		map.put("/board4insertui.do", "kr.co.board4.controller.Board4InsertUICommand");
		map.put("/board4insert.do", "kr.co.board4.controller.Board4InsertCommand");
		map.put("/board4read.do", "kr.co.board4.controller.Board4ReadCommand");
		map.put("/board4updateui.do", "kr.co.board4.controller.Board4UpdateUICommand");
		map.put("/board4update.do", "kr.co.board4.controller.Board4UpdateCommand");
		map.put("/board4replyui.do", "kr.co.board4.controller.Board4ReplyUICommand");
		map.put("/board4reply.do", "kr.co.board4.controller.Board4ReplyCommand");

		
		try {
			com = (Command) Class.forName(map.get(sp)).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (com != null) {
			CommandAction action = com.execute(request, response);
			if (action.isRedirect()) {
				response.sendRedirect(action.getWhere());
			} else {
				request.getRequestDispatcher(action.getWhere()).forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
