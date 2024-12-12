package com.kh.ssuper.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/myPage.me")
public class MyInfoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MyInfoController() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 내 정보 조회란 뭘까?
		// DB에서 MEMBER 테이블의 한 행에 대한 모든 값을 가져와 보여줌
		// 이 한 행은 USER_NO 혹은 USER_ID와 같은 유일한 값과 비교하는 조건을 걸어 찾는다.
		
		
		request.getRequestDispatcher("/WEB-INF/views/member/my_page.jsp")
			   .forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
