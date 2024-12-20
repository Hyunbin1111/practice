package com.kh.ssuper.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

@WebServlet("/save.thumbnail")
public class ThumbnailInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ThumbnailInsertController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 1) POST방식을 doGet에서 하니까 인코딩부터
		request.setCharacterEncoding("UTF-8");
	
		// 2) 첨부파일 -> multipart/form-data => 조건제시 => 서버로 파일을 올려주자
		if(ServletFileUpload.isMultipartContent(request)) {
			
			// 1) MultipartRequest객체 생성
			// 1_1. 전송용량제한(10Byte)
			int maxSize = 1024 * 1024 * 100;
			
			// 1_2. 저장할 경로를 구해야함
			String savePath = request.getSession()
									 .getServletContext()
									 .getRealPath("resources/img_upfiles");
			
			// 2) MultipartRequest객체를 생성하면서 파일의 이름을 바꿔주면서 업로드하기
			MultipartRequest multiRequest = 
					new MultipartRequest(request,
										 savePath,
										 maxSize,
										 "UTF-8",
										 new MyRenamePolicy());
			// --------------- 파일업로드 --------------- 
			
			// 3) multiRequest로부터 값 뽑기 => getParameter() 호출
			String boardTitle = multiRequest.getParameter("title");
			String boardContent = multiRequest.getParameter("content");
			String userNo = multiRequest.getParameter("userNo");
			
			// 4) 넘기기 쉽게 데이터 가공
			Board board = new Board();
			board.setBoardTitle(boardTitle);
			board.setBoardContent(boardContent);
			board.setBoardWriter(userNo);
			
			// Attachment
			// => 사진게시판 작섬 폼 required
			// => 게시글 한 개당 최소 한개의 첨부파일은 존재
			
			// file1, file2, file3, file4
			
			List<Attachment> list = new ArrayList();
			
			//Attachment at = new Attachment();
			
			// 키값 : file1 ~ file4
			for(int i = 1; i < 4; i++) {
				
				String key = "file" + i;
				
				// 조건검사 name속성값을 이용해서 파일이 첨부되어있는지
				// 중ㄱ
				if(multiRequest.getOriginalFileName(key) != null) {
					// 파일이 존재할경우
					Attachment at = new Attachment();
					at.setOriginName(multiRequest.getOriginalFileName(key));
					at.setChangeName(multiRequest.getFilesystemName(key));
					at.setFilePath("resources/img_upfiles");
					
					//파일레벨
					at.setFileLevel(i == 1 ? 1 : 2);
					
					list.add(at);
				}
			}
			// 5) 서비스 요청
			int result = new BoardService().insertThumbnailBoard(board, list);
			
			// 6) 결과에 따른 응답화면 지정
			if(result > 0) {
				request.getSession().setAttribute("alertMsg", "게시글 작성 성공");
				response.sendRedirect(request.getContextPath() + "/list.thumbnail");
			} else {
				request.setAttribute("failMsg", "게시글 작성 실패");
				request.getRequestDispatcher("/WEB-INF/views/common/fail_page.jsp")
					   .forward(request, response);
			}
			
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
