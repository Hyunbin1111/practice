package com.kh.ssuper.board.controller;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.kh.ssuper.board.model.service.BoardService;
import com.kh.ssuper.board.model.vo.Board;
import com.kh.ssuper.common.PageInfo;
@WebServlet("/list.board")
public class BoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public BoardListController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// - 페이징 처리 -
		// 필요한 변수들
		int listCount; // 현재 일반 게시판의 총 게시글의 개수를 알아야함
		// => BOARD테이블가서 갯수를 세야한다=> COUNT(*) (STATUS='Y')해서 조회
		int currentPage; // 현재 사용자가 요청한 페이지
		// => request.getParameter("currentPage")로 뽑아서 씀
		int pageLimit;//페이지 하단에 보여질 페이징 버튼 개수 => 10개로 고정
		int boardLimit; // 한 페이지에 보여질 게시글의 최대 개수 => 10개로 고정
		
		int maxPage; //가장 마지막 페이지가 몇 번 페이지인지(총 페이지의 개수)
		int startPage; //페이지 하단에 보여질 페이징바의 시작 수
		int endPage; //페이지 하단에 보여질 페이징바의 끝 수
		
		// * listCount : 총 게시글의 수 -> 그 수를 알려면 db에 가야함
		listCount = new BoardService().selectListCount(); //107
		
		//System.out.println(listCount);
		
		// * currentPage : 현재 페이지(사용자가 요청한 페이지)
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
		
		// * pageLimit : 페이징버튼 최대 개수
		pageLimit = 10;
		
		// * boardLimit : 한페이지에 보여질 게시글의 최대 개수
		boardLimit = 10;
		
		//* maxPage : 가장 마지막 페이지가 몇 번 페이지인지(총 페이지의 개수)
		/*
		 *  listCount와 boardLimit의 영향을 받아서 갯수가 달라짐
		 *
		 *  - 공식 구하기
		 *    단, boardLimit이 10이라고 가정
		 *
		 *  총 개수		한 페이지 개수 			나눗셈 결과	마지막 페이지
		 *  100		 /		10			=		10.0		10
		 *
		 *  107		/		10			=		10.7		11
		 *
		 *  111		/		10			=		11.1		12
		 *  										=> 소수점 올림 처리를 하면 마지막 페이지가 됨
		 *  => 나눗셈 결과(listCount/boardLimit)를 올림처리를 할 경우 maxPage가 됨
		 *
		 *  스텝.
		 *  1. listCount를 double로 변환
		 *  2. listCount / boardLimit
		 *  3. Math.ceil() => 결과를 올림처리
		 *  4. 결과값을 int로 형변환
		 *
		 */
		// 소수점이 필요하므로 실수로 바꾸어서 연산이 이루워져야함 -> 하나만 형변환을 해줘도 됨
		maxPage = (int)Math.ceil((double)listCount / boardLimit); // 소수점만 올리면 double형이다 double형은 maxPage에 못담기때문에 다시 (int)형으로 바꾸어줘야한다
		
		// * startPage : 페이지 하단에 보여질 페이징 버튼 중 시작 수
		/*
		 *  pageLimit, currentPage 영향을 받음
		 *
		 *  - 공식 구하기
		 *    단, pageLimit이 10이라고 가정
		 *
		 *  startPage : 1, 11, 21, 31, 41... => n * 10 + 1
		 *
		 *  만약에 pageLimit이 5였다??
		 *
		 *  startPage : 1, 6, 11, 16... => n * 5 + 1
		 *
		 *  즉, startPage == n * pageLimit + 1;
		 *
		 *  currentPage			startPage
		 *  	1					1
		 *  	5					1
		 *  	10					1
		 *  	11					11
		 *  	16					11
		 *  	19					11
		 *  	21					21
		 *  	30					21
		 *  	31					31
		 *
		 *  => 1 ~ 10 : n * 10 + 1 ==> n == 0
		 *  => 11 ~ 20 : n * 10 + 1 ==> n == 1
		 *  => 21 ~ 30 : n * 10 _ 1 ==> n == 2
		 *
		 *  pagelimit으로 나눈다고 했을 때
		 *  1 ~ 10 / 10 => 0 ~ 1
		 *  11 ~ 20 / 10 => 1 ~ 2
		 *  21 ~ 30 / 10 => 2 ~ 3
		 *
		 * -> currentpage를 1 빼면
		 * 0 ~ 9 / 10 => 0
		 * 10 ~ 19 / 10 => 1
		 * 20 ~ 29 / 10 => 2
		 *
		 * n = (currentPage-1) / pageLimit
		 *
		 * startPage = (currentPage -1) / pageLimit * pageLimit + 1;
		 *
		 *
		 */
		startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
		
		
		
		// * endPage : 페이지 하단에 보여질 페이징 버튼의 끝 수
		
		/*
		 * 	startPage, pageLimit에 영향을 받음(maxPage도 마지막 페이징바에 대한 영향을 끼침)
		 *
		 *  - 공식 구하기
		 *  단, pageLimit이 10이라는 가정하에
		 *
		 *  startPage : 1 => endPage : 10
		 *  startPage : 11 => endPage : 20
		 *  startPage : 21 => endPage : 30
		 *
		 *  endPage = startPage + pageLimit - 1;
		 *
		 */
		endPage = startPage + pageLimit - 1;
		
		// startPage 11이라서 endPage에는 20이 들어갔는데
		// maxPage가 11 이라면?
		if(endPage > maxPage)
			endPage = maxPage;
		
		// 여기까지 총 7개의 변수를 선언 및 초기화까지 완료
		PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit, maxPage, startPage, endPage);
		
		List<Board> list = new BoardService().findAll(pi);
		request.setAttribute("list",list);
		request.setAttribute("pi", pi);
		
		request.getRequestDispatcher("/WEB-INF/views/board/board_list.jsp").forward(request, response);
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}
}