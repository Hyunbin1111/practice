package com.kh.ssuper.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.ssuper.member.model.service.MemberService;
import com.kh.ssuper.member.model.vo.Member;

@WebServlet("/sign-up.me")
public class JoinController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public JoinController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// POST
		// 1) 인코딩 설정
		request.setCharacterEncoding("UTF-8");
		
		// 회원가입이란..?
		// 사용자가 회원가입 양식에 적은 값을 MEMBER테이블까지 들고가서 한 행 INSERT
		// 2) request객체로부터 요청 시 전달값 뽑기
		String userId = request.getParameter("userId"); // "필수입력"
		String userPwd = request.getParameter("userPwd");
		String userName = request.getParameter("userName");
		String email = request.getParameter("email");
		String[] interestArr = request.getParameterValues("interest");
		// [농사, 자바] / null
		
		String interest = interestArr != null ?
				String.join(",", interestArr) : "";
		
		// 3) Member 객체에 담기
		Member member = new Member();
		member.setUserId(userId);
		member.setUserPwd(userPwd);
		member.setUserName(userName);
		member.setEmail(email);
		member.setInterest(interest);
		
		// 4) 요청처리(Service호출)
		int result = new MemberService().join(member);
		
		// 5) 처리결과를 받아 사용자가 보게 될 응답화면 지정
		if(result > 0) {
			// 성공 => 메인(index.jsp)로 보내고 alert 끼워주기
			
			request.getSession().setAttribute("alertMsg", "회원가입 성공🎉🎉🎉");

			response.sendRedirect(request.getContextPath());
			
		} else {
			// 실패 => 실패페이지로 포워딩
			request.setAttribute("failMsg", "회원가입에 실패하셨습니다");
			
			request.getRequestDispatcher("/WEB-INF/views/common/fail_page.jsp").forward(request, response);
			
		}
		
		
		
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
