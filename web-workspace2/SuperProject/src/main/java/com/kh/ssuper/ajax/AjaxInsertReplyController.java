package com.kh.ssuper.ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.ssuper.board.model.service.BoardService;
import com.kh.ssuper.board.model.vo.Reply;
import com.kh.ssuper.member.model.vo.Member;

@WebServlet("/insert.reply")
public class AjaxInsertReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AjaxInsertReplyController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// POST => 인코딩 해야함
		request.setCharacterEncoding("UTF-8");
		
		// request로 부터 값 뽑기
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		String content = request.getParameter("content");
		
		// session
		int userNo = ((Member)request.getSession()
									 .getAttribute("loginUser"))
									 .getUserNo();
		
		// VO에 담기
		Reply reply = new Reply();
		reply.setRefBno(boardNo);
		reply.setReplyContent(content);
		reply.setReplyWriter(String.valueOf(userNo));
		
		// Service로 요청
		int result = new BoardService().insertReply(reply);
		
		// Gson, Json
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().print(result > 0 ? "success" : "fail");
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
