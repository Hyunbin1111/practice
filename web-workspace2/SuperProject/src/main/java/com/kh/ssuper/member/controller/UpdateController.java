package com.kh.ssuper.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.ssuper.member.model.service.MemberService;
import com.kh.ssuper.member.model.vo.Member;

@WebServlet("/update.me")
public class UpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 1) POST 방식은 인코딩부터
		request.setCharacterEncoding("UTF-8");
		
		
		// 2) request로부터 값 뽑기
		// key값을 뭐로 넘겼는지 생각하기. userId, userName, email, interest(checkbox)
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
		String email = request.getParameter("email");
		String[] interestArr = request.getParameterValues("interest");
		
		String interest  = interestArr != null ?
				 String.join(",", interestArr) : "";
		
		// 3) VO객체로 가공
		Member member = new Member();
		member.setUserId(userId);
		member.setUserName(userName);
		member.setEmail(email);
		member.setInterest(interest);
		
		// 4) Service 메소드 호출, 값담기
		int result = new MemberService().update(member);
		
		// 5) 결과값에 따라서 응답화면 지정
		if(result > 0) {
			
			HttpSession session = request.getSession();
			session.setAttribute("alertMsg", "정보수정에 성공했습니다.");
			
			// 1. sendRedirect
			// 2. forward
			
			// 현재 생긴 문제
			// Update에 성공했는데, session의 loginUser키값에는
			// 로그인 당시 조회했던 값들이 필드에 담겨있기 때문에,
			// 마이페이지에서 갱신되기 전 값들이 출력됨
			
			// 목표 => DB가서 갱신된 회원정보 들고오기
			// 현재 update에 성공한 행을 식별할 수 있는 값이 존재하나? => userId
			// new MemberService().selectOne(userId);
			// 로그인에 이미 한 행에대한 모든 정보를 가져오는 기능이 있기떄문에,
			// 다시 한번 더 만들 이유가 없음(바퀴 또 만들기임)
			// 로그인 기능을 이용하려면 아이디와 비밀번호가 필요함
			
			// Object 타입이기때문에 Member타입으로 바꾼 후 userPwd의 값을 뽑는다.
			String userPwd = ((Member)session.getAttribute("loginUser")).getUserPwd(); 
			
			Member selectMember = new Member();
			selectMember.setUserId(userId);
			selectMember.setUserPwd(userPwd);
			
			Member updateMember = new MemberService().login(selectMember);
			
			session.setAttribute("loginUser", updateMember);
			
			// 1.
			response.sendRedirect(request.getContextPath() + "/myPage.me");
			
			// 2.
			/* 2번을 사용하게 될 경우 유지보수가 귀찮아짐
			request.getRequestDispatcher("/WEB-INF/views/member/my_page.jsp")
				   .forward(request, response);
			*/
		} else {
			request.setAttribute("failMsg", "회원 정보 수정에 실패했습니다😢😢");
			request.getRequestDispatcher("/WEB-INF/views/common/fail_page.jsp")
				   .forward(request, response);
		}
	}

}
