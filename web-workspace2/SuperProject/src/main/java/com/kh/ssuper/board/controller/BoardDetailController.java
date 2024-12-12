package com.kh.ssuper.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.ssuper.board.model.service.BoardService;
import com.kh.ssuper.board.model.vo.Attachment;
import com.kh.ssuper.board.model.vo.Board;

@WebServlet("/detail.board")
public class BoardDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardDetailController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// GET 방식
		
		// 2) 값 뽑기
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));

		// 3) 가공해야하는데 값이 하나임
		
		// 4) 서비스 요청
		// 4_1) 조회수 증가 및 Board 테이블 조회
		Board board = new BoardService().findById(boardNo);
		
		String path = "";
		
		// 게시글 조회에 성공했다면
		if(board != null) {
			// 4_2) ATTACHMENT 테이블 조회
			Attachment attachment = new BoardService().selectAttachment(boardNo);
			
			// 조회된 데이터를 Attribute에 담기
			request.setAttribute("board", board);
			request.setAttribute("attachment", attachment);
			
			// 포워딩
			//request.getRequestDispatcher("/WEB-INF/views/board/detail.jsp")
			//	   .forward(request, response);
			path = "board/detail";
			
		} else {
			request.setAttribute("failMsg", "게시글 조회 실패");
			//request.getRequestDispatcher("/WEB-INF/views/common/fail_page.jsp")
			//	   .forward(request, response);
			path = "common/fail_page";
		}
		
		request.getRequestDispatcher("/WEB-INF/views/" + path + ".jsp")
			   .forward(request, response);
	
		
		
		
		
		
		
		
		
		
		
		
		
		/* 메소드명 정리
		 * 작업 도메인 BOARD
		 * 
		 * insertBoard()
		 * updateBoard()
		 * deleteBoard()
		 * selectBoard()
		 * selectBoardList()
		 * increaseCount()
		 * searchBoard()
		 * 
		 * --------------------------------
		 * 
		 * save()
		 * update()
		 * deleteById()
		 * findById()
		 * findAll()
		 * increaseCount()
		 * findBy뭐시기 ~~()
		 * 
		 * 
		 * 위나 아래 중 한종류만 쓸 것, 섞어쓰면 안좋음
		 * 
		 */
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
