package com.kh.ssuper.board.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.kh.ssuper.board.model.service.BoardService;
import com.kh.ssuper.board.model.vo.Attachment;
import com.kh.ssuper.board.model.vo.Board;
import com.kh.ssuper.common.MyRenamePolicy;
import com.oreilly.servlet.MultipartRequest;

@WebServlet("/insert.board")
public class BoardInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardInsertController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1) 인코딩 설정(UTF-8)
		request.setCharacterEncoding("UTF-8");
		
		// 2) 값 뽑기
		//String userNo = request.getParameter("userNo");
		//System.out.print(userNo);
		
		// form태그 요청 시 multipart/form-data형식으로 전송하는 경우
		// request.getParameter로는 값 뽑기가 불가능
		// com.oreilly.servlet
		
		// 스텝 0) 요청이 multipart방식으로 잘 전송이 되었는지부터 확인
		if(ServletFileUpload.isMultipartContent(request)) {
			//multipart타입으로 잘 도착하면 true, 아닐경우 false
			//System.out.println("성공");
			
			// 스텝1) 전송되는 파일의 처리를 위한 작업
			// 1_1. 전송파일 용량 제한
			/*
			 * 단위 정리
			 * 
			 * bit X 8 => Byte => Kbyte => MByte => GByte => TByte => PByte => ...
			 * 
			 * 10MegaByte
			 * 
			 * 1KByte => 1024Byte
			 * 
			 * 1MByte => 1024KByte
			 * 
			 */
			
			int maxSize = 1024 * 1024 * 10;
			
			// 1_2 전달된 파일을 저장할 서버의 폴더 경로 알아내기
			// HttpservletRequest
			// HttpSession
			// ServletContext
			// getRealPath()
			// => 인자값으로 webapp부터 board_up i[
			
			HttpSession session = request.getSession();
			ServletContext application = session.getServletContext();
			String savePath = application.getRealPath("/resources/board_upfiles");
			
			// a.jpa a2.jpg a3.jpg
			
			/*
			 * HttpServletRequest requst
			 * =>
			 * MultipartRequest multiRequest 객체로 변환
			 * 
			 * [ 표현법 ]
			 * 
			 * MultiRequest multiRequest =
			 * new MultiRequest(request, savePath, maxSize, 인코딩,
			 * 					파일명을 수정해주는 객체);
			 * 
			 * MultiRequest 객체를 생성하면 파일이 업로드됨
			 * 
			 * 사용자가 올린파일명은 해당 폴더에 업로드하지 않는 것이 일반적
			 * 
			 * Q) 파일명을 수정하는 이유는?
			 * A) 같은 파일명이 존재할 수 있으니까,
			 * 	  파일명에 한글 / 특수문자 / 공백문자 가 포함될 경우 서버에 따라 문제가 생길 수 있음
			 * 
			 * cor.jar => 기본적으로 파일명을 수정해주는 객체
			 * => 내부적으로 rename()을 호출하면서 파일명 수정
			 * => bono.jpg bono1.jpg bono2.jpg
			 * 
			 * => 우리 입맛대로 파일명을 수정해서 겹치지 않게 하기 위해
			 *    MyRenamePolicy라는 클래스를 만들었음
			 * 
			 */
			
			MultipartRequest multiRequest =
					new MultipartRequest(request, savePath, maxSize, "UTF-8",
										 new MyRenamePolicy());
			
			// --- 파일 업로드 ---
			// 이곳으로 온 이유 => 작성한 게시글을 BOARD 테이블에 INSERT하기 위해서
			// 2) 값 뽑기
			String userNo = multiRequest.getParameter("userNo");
			String title = multiRequest.getParameter("title");
			String content = multiRequest.getParameter("content");
			String categoryNo = multiRequest.getParameter("category");
			
			// 3) VO객체로 가공
			Board board = new Board();
			board.setBoardTitle(title);
			board.setBoardContent(content);
			board.setBoardWriter(userNo);
			board.setCategory(categoryNo);
			
			
			/*
			 * 	FILE_NO			Primary Key(식별용도)
				REF_BNO			어떤 게시글에 붙어있는지, 게시글번호(Foreign Key)
				ORIGIN_NAME		파일의 원본명
				CHANGE_NAME		파일의 변경된 이름
				FILE_PATH		파일이 저장될 경로
				UPLOAD_DATE		업로드 날짜
				FILE_LEVEL		썸네일인가, 아닌가 
				STATUS			지웠냐 아니냐
			 */
			
			// 3_2) 첨부파일의 경우 => 선택적
			Attachment attachment = null;
			
			// 첨부파일 유무를 파악
			// multiRequest.getOriginalFileName("키값")
			// input="file" 타입의 name 속성값을 키값에 넣는다.
			// 첨부파일이 존재한다면 "원본파일명" / 존재하지 않는다면 null값을 반환
			if(multiRequest.getOriginalFileName("upfile") != null) {
				
				// 첨부파일 있다!! => VO객체로 가공
				
				attachment = new Attachment();
				// originName
				attachment.setOriginName(multiRequest.getOriginalFileName("upfile"));
				
				// changeName
				attachment.setChangeName(multiRequest.getFilesystemName("upfile"));
				
				// filePath (저장경로)
				attachment.setFilePath("/resources/board_upfiles");
				
				
			}
			
			
			// 4) 서비스호출
			
			int result = new BoardService().insert(board, attachment);
			
			// 5) 응답화면지정
			if(result > 0) { // 성공
				
				request.getSession().setAttribute("alertMsg", "게시글 등록 성공🎉🎉🎉");
				
				
				/*
				 * request.getRequestDispatcher("/WEB-INF/views/board/board_list.jsp").forward(request, response);
				 * forward로 보낼경우 board_list로 돌아가지만 페이지를 보여주지 않음
				 */
				
				response.sendRedirect(request.getContextPath() + "/list.board?currentPage=1");
				
			} else { // 실패
				
				// 실패했을 경우 이미 업로드한 파일을 삭제
				
				if(attachment != null) {
					new File(savePath + "/" + attachment.getChangeName())
						.delete();
				}
				
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
