package com.kh.ssuper.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.ssuper.board.model.service.BoardServiceImpl;
import com.kh.ssuper.board.model.vo.Board;
import com.kh.ssuper.common.PageInfo;
import com.kh.ssuper.common.Pagination;

@WebServlet("/list.board")
public class BoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardListController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 페이징 처리
		int listCount = new BoardServiceImpl().selectListCount();
		//System.out.println(listCount);
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		// 한 페이지에 보여줄 글의 개수, 한 페이지에 보여줄 버튼의 개수
		int pageLimit = 8;
		int boardLimit = 8;
		/*
		int maxPage = (int)Math.ceil((double)listCount / boardLimit);
		int startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
		int endPage = startPage + pageLimit - 1;
		if(endPage > maxPage) { endPage = maxPage; };
		*/
		
		PageInfo pi = Pagination.getPageInfo(listCount, currentPage, pageLimit, boardLimit);
		
		
		List<Board> list = new BoardServiceImpl().selectList(pi);
		
		request.setAttribute("list", list);
		request.setAttribute("pi", pi);
		
		
		request.getRequestDispatcher("/WEB-INF/views/board/board_list.jsp")
			   .forward(request, response);
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
