package com.kh.ssuper.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.ssuper.board.model.service.BoardService;
import com.kh.ssuper.board.model.vo.Attachment;
import com.kh.ssuper.board.model.vo.Board;
import com.kh.ssuper.board.model.vo.Category;

@WebServlet("/updateForm.board")
public class BoardUpdateFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardUpdateFormController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 게시글 수정 양식 보여주기
		
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		
		BoardService boardService = new BoardService();
		
		// 1.
		List<Category> categoryList = boardService.selectCategory();
		
		// 2.
		Board board = boardService.selectBoard(boardNo);
		
		// 3.
		Attachment at = boardService.selectAttachment(boardNo);
		
		
		// 값 담기
		request.setAttribute("categoryList", categoryList);
		request.setAttribute("board", board);
		request.setAttribute("attachment", at);
		
		// 포워딩
		request.getRequestDispatcher("/WEB-INF/views/board/update_form.jsp")
			   .forward(request, response);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
