package com.kh.ssuper.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.ssuper.member.model.service.MemberServiceImpl;
import com.kh.ssuper.member.model.vo.Member;

// 제일 먼저 확인할 것, webServlet Mapping값 확인
@WebServlet("/login.me")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public LoginController() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 * <HttpServletRequest, HttpServletResponse>
		 * 
		 * - request : 서버로 요청할 때 정보(요청시 전달값, 요청 전송 방식, 요청한 사용자 정보 등등)
		 * - response : 요청에 대해 응답하고자 할 때 사용하는 객체
		 * 
		 */
		
		// 절차
		// 1) GET인가? POST인가? => 요청방식이 POST라면 인코딩 작업
		request.setCharacterEncoding("UTF-8");
		
		//2) 요청 시 전달값이 있나? 없나? => 있으면 뽑아서 가공해야됨
		// request.getParameter("키값") : String
		// request.getParameterValues("키값") : String => checkbox일 경우 사용
		
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
		//System.out.println(userId);
		//System.out.println(userPwd);
		
		Member member = new Member();
		member.setUserId(userId);
		member.setUserPwd(userPwd);
		
		// 해당 요청을 처리해주는 서비스의 메소드를 호출, 값받기
		Member loginUser = new MemberServiceImpl().login(member);

		if(loginUser != null) {
			// 성공 => index.jsp 응답
			
			// 사용자의 정보 넘기기
			
			// 로그인한 회원의 정보를 로그아웃 하거나 브라우저를 종료하기 전까지는
			// 계속 유지하며 사용할 예정이기 때문에 session에 담아준다.
			
			// step1. session의 Attribute에 사용자 정보 담기
			// session객체의 Type : HttpSession
			// => 현재 요청 보낸 Client의 Session : request.getSession();
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", loginUser);
			
			// * 포워딩
			/*
			// step2. RequestDispatcher객체 생성
			RequestDispatcher view = request.getRequestDispatcher("/index.jsp");
			
			// step3. forward();
			view.forward(request, response);
			*/
			
			// localhost/super
			// sendRedirect : Client에게 url을 다시 요청하게 함. url 재요청 방식
			// response객체를 이용
			// response.sendRedirect("/재요청경로");
			
			session.setAttribute("alertMsg", "로그인에 성공했습니다🎉🎉🎉");
			
			response.sendRedirect(request.getContextPath());
			
			
		} else {
			// 실패
			
			// 에러 메시지 넘기기
			request.setAttribute("failMsg", "로그인에 실패했습니다");
			
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/fail_page.jsp");
			
			view.forward(request, response);
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
