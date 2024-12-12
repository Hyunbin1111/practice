package com.kh.ssuper.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout.me")
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LogoutController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 로그아웃이란 뭘까?
		// => 1. loginUser키값만 지우기
		// => 2. session을 만료시킨다.(== 무효화한다)
		// 2번 => invalidate() => session객체 메소드
		request.getSession().invalidate();
		// 응답데이터 => 웰컴파일
		// sendRedirect();
		response.sendRedirect(request.getContextPath());
		
	
	
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
