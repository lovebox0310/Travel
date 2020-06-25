package kr.co.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
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

		map.put("/board4list.do", "kr.co.board4.command.Board4ListCommand");
		map.put("/board4insertui.do", "kr.co.board4.command.Board4InsertUICommand");
		map.put("/board4insert.do", "kr.co.board4.command.Board4InsertMultiCommand");
		map.put("/board4read.do", "kr.co.board4.command.Board4ReadCommand");
		map.put("/board4updateui.do", "kr.co.board4.command.Board4UpdateUICommand");
		map.put("/board4update.do", "kr.co.board4.command.Board4UpdateMultiCommand");
		map.put("/board4replyui.do", "kr.co.board4.command.Board4ReplyUICommand");
		map.put("/board4reply.do", "kr.co.board4.command.Board4ReplyMultiCommand");
		map.put("/board4delete.do", "kr.co.board4.command.Board4DeleteCommand");
				
		map.put("/board2list.do", "kr.co.board2.command.ListPageCommand");
		map.put("/board2insertui.do", "kr.co.board2.command.InsertUICommand");
		map.put("/board2insert.do", "kr.co.board2.command.InsertCommand");
		map.put("/board2read.do", "kr.co.board2.command.ReadCommand");
		map.put("/board2updateui.do", "kr.co.board2.command.UpdateUICommand");
		map.put("/board2update.do", "kr.co.board2.command.UpdateCommand");
		map.put("/board2delete.do", "kr.co.board2.command.DeleteCommand");
		map.put("/board2replyui.do", "kr.co.board2.command.ReplyUICommand");
		map.put("/board2reply.do", "kr.co.board2.command.ReplyCommand");
		map.put("/board2loginui.do", "kr.co.board2.command.LoginUICommand");
		map.put("/board2login.do", "kr.co.board2.command.LoginCommand");
		map.put("/board2logout.do", "kr.co.board2.command.LogoutCommand");
		map.put("/board2signupui.do", "kr.co.board2.command.SignupUICommand");
			
		map.put("/Board5logout.do", "kr.co.travel5.command.LogoutCommand");
		map.put("/Board5list.do", "kr.co.travel5.command.ListPageCommand");
		map.put("/Board5imglist.do", "kr.co.travel5.command.ImgListPageCommand");	
		map.put("/Board5insertui.do", "kr.co.travel5.command.InsertUICommand");
		map.put("/Board5insert.do", "kr.co.travel5.command.InsertCommand");
		map.put("/Board5read.do", "kr.co.travel5.command.ReadCommand");
		map.put("/Board5updateui.do", "kr.co.travel5.command.UpdateUICommand");
		map.put("/Board5update.do", "kr.co.travel5.command.UpdateCommand");
		map.put("/Board5delete.do", "kr.co.travel5.command.DeleteCommand");
		map.put("/Board5replyui.do", "kr.co.travel5.command.ReplyUICommand");
		map.put("/Board5reply.do", "kr.co.travel5.command.ReplyCommand");		
		map.put("/Board5signupui.do", "kr.co.travel5.command.SignUpUICommand");
		map.put("/Board5signup.do", "kr.co.travel5.command.SignUpCommand");
		map.put("/Board5memberlistui.do", "kr.co.travel5.command.MemberListUICommand");
		map.put("/Board5grant.do", "kr.co.travel5.command.MemberGrantCommand");
		map.put("/Board5revoke.do", "kr.co.travel5.command.MemberRevokeCommand");	
		map.put("/Board5profile.do", "kr.co.travel5.command.ProfileCommand");
		map.put("/Board5profileupdateui.do", "kr.co.travel5.command.ProfileUpdateUICommand");
		map.put("/Board5profileupdate.do", "kr.co.travel5.command.ProfileUpdateCommand");		
		map.put("/Board5profiledelete.do", "kr.co.travel5.command.ProfileDeleteCommand");
		
		map.put("/board7qnaUpdateui.do", "kr.co.board7.command.QnaUpdateUICommand");
		map.put("/board7qnaUpdate.do", "kr.co.board7.command.QnaUpdateCommand");
		map.put("/board7qnalist.do", "kr.co.board7.command.ListPageCommand");
		map.put("/board7askui.do","kr.co.board7.command.AskUICommand");
		map.put("/board7ask.do","kr.co.board7.command.AskCommand");
		map.put("/board7read.do","kr.co.board7.command.ReadCommand");
		map.put("/board7qnaDelete.do","kr.co.board7.command.QnaDeleteCommand");
		map.put("/board7replyui.do","kr.co.board7.command.ReplyUICommand");
		map.put("/board7reply.do","kr.co.board7.command.ReplyCommand");
		map.put("/board7asklist.do","kr.co.board7.command.MyAskListCommand");
		map.put("/board7replylist.do","kr.co.board7.command.MyReplyListCommand");
		map.put("/board7insertQnaComment.do","kr.co.board7.command.PutQnaCommentCommand");
		map.put("/board7replyComment.do","kr.co.board7.command.ReplyComCommand");
		map.put("/board7updateComment.do", "kr.co.board7.command.UpdateComCommand");
		map.put("/board7deleteComment.do", "kr.co.board7.command.DeleteComCommand");
		map.put("/board7download.do", "kr.co.board7.command.DownloadAction");
		
		map.put("/board6notice_list.do", "kr.co.board6.command.Notice_ListPageCommand");
	    map.put("/board6notice_insertui.do", "kr.co.board6.command.Notice_InsertUICommand");
	    map.put("/board6notice_insert.do", "kr.co.board6.command.Notice_InsertCommand");
	    map.put("/board6notice_read.do", "kr.co.board6.command.Notice_ReadCommand");
	    map.put("/board6notice_updateui.do", "kr.co.board6.command.Notice_UpdateUICommand");
	    map.put("/board6notice_update.do", "kr.co.board6.command.Notice_UpdateCommand");
	    map.put("/board6notice_delete.do", "kr.co.board6.command.Notice_DeleteCommand");
	    map.put("/board6notice_loginui.do", "kr.co.board6.command.Notice_LoginUICommand");
	    map.put("/board6notice_login.do", "kr.co.board6.command.Notice_LoginCommand");
	    map.put("/board6notice_logout.do", "kr.co.board6.command.Notice_LogoutCommand");
	    map.put("/board6notice_cinsert.do", "kr.co.board6.command.Comment_InsertCommand");
	    map.put("/board6notice_reply.do", "kr.co.board6.command.Comment_ReplyCommand");
	    map.put("/board6notice_cupdate.do", "kr.co.board6.command.Comment_UpdateCommand");
	    map.put("/board6notice_cdelete.do", "kr.co.board6.command.Comment_DeleteCommand");
	    map.put("/board6notice_filedownload.do", "kr.co.board6.command.Notice_FileDownloadCommand");
		
	    System.out.println("map.get(sp) " + map.get(sp));
	    
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
