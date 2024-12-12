package com.kh.ssuper.board.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.kh.ssuper.board.model.service.BoardService;
import com.kh.ssuper.board.model.vo.Attachment;
import com.kh.ssuper.board.model.vo.Board;
import com.kh.ssuper.common.MyRenamePolicy;
import com.oreilly.servlet.MultipartRequest;

@WebServlet("/update.board")
public class BoardUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardUpdateController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1) POST => 인코딩
		request.setCharacterEncoding("UTF-8");
		
		// 값 뽑기전
		// multipart 방식으로 왔을때만 수정이 되도록
		if(ServletFileUpload.isMultipartContent(request)) {
			
			// 파일 업로드
			// 1. 전송파일 용량 제한
			int maxSize = 1024 * 1024 * 10;
			// 2. 파일을 저장할 물리적인 경로
			String savePath = request.getSession()
									 .getServletContext()
									 .getRealPath("/resources/board_upfiles");
			
			// MultipartRequest객체 생성과 동시에 파일 업로드
			MultipartRequest multiRequest =
					new MultipartRequest(request,
										 savePath,
										 maxSize,
										 "UTF-8",
										 new MyRenamePolicy());
			
			// --- 파일을 서버에 업로드 ---
			// BOARD UPDATE
			// ATTACHMENT
			// case 1. 첨부파일이 존재하지 않는 경우 => BOARD UPDATE + AT X
			// case 2. 첨부파일을 첨부, 기존 첨부파일이 존재 => BOARD UPDATE + AT UPDATE
			// case 3. 첨부파일을 첨부, 기존 첨부파일이 X => BOARD UPDATE + AT INSERT
			
			// 값 뽑기
			String category = multiRequest.getParameter("category");
			String boardTitle = multiRequest.getParameter("title");
			String boardContent = multiRequest.getParameter("content");
			int boardNo = Integer.parseInt(multiRequest.getParameter("boardNo"));
			
			// VO로 가공
			Board board = new Board();
			board.setCategory(category);
			board.setBoardTitle(boardTitle);
			board.setBoardContent(boardContent);
			board.setBoardNo(boardNo);
			
			// Attachment객체 선언
			// 실제 첨부파일이 존재할 때만 => 객체 생성
			
			Attachment at = null;
			
			if(multiRequest.getOriginalFileName("reUpfile") != null) {
				
				// 새로운 파일명이 존재한다면 객체 생성후, 원본명, 수정명, 파일경로 담기
				at = new Attachment();
				at.setOriginName(multiRequest.getOriginalFileName("reUpfile"));
				at.setChangeName(multiRequest.getFilesystemName("reUpfile"));
				at.setFilePath("resources/board_upfiles");
				// 여기까지 새롭게 업로드한 첨부파일에 대한 내용
				
				// INSERT / UPDATE
				// INSERT하는 경우 update_form에 첨부파일이 없다면
				// INSERT : 이 첨부파일이 어떤 게시글에 달리는건가를 알아야함
				// UPDATE하는 경우 update_form에 첨부파일이 있다면
				// UPDATE : ATTACHMENT테이블의 몇 행인지?
				
			
				
				/*!!!!!!!!!!!!!여기 빈공간 채우기!!!!!!!!!!!!!!*/
				
				
				
			}
			
			int result = new BoardService().update(board, at);
			
			// 5) 결과에 따른 응답 뷰 지정
			if(result > 0) {
				request.getSession().setAttribute("alertMsg", "게시글 수정 성공");
				
				// http://loaclhost/super/
				
				response.sendRedirect(request.getContextPath()
									  + "/detail.board?boardNo="
									  + boardNo);
				
			} else {
				request.setAttribute("failMsg", "게시글 수정 실패");
				request.getRequestDispatcher("/WEB-INF/views/common/fail_page.jsp")
					   .forward(request, response);
			}
			
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
