package com.kh.ssuper.ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.ssuper.member.model.service.MemberService;

@WebServlet("/checkId.me")
public class AjaxCheckIdController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AjaxCheckIdController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// GET
		
		// 2) request로부터 값 뽑기
		String id = request.getParameter("id");
		
		// 3) VO 가공
		
		// 4) Service호출
		int count = new MemberService().checkId(id);
		// SELECT USER_ID FROM MEMBER WHERE USER_ID = '입력한거' => ResultSet이 0행
		// if(rset.next(){ }
		// SELECT COUNT(*) FROM MEMBER USER_ID = '입력한거' => 얘는 무조견 결과가 있음
		
		// 5) 결과에 따른 응답
		// 중복값이 있을 때 count == 1 => "NNNNN"
		// 중복값이 없을 때 count == 0 => "NNNNY"
		
		response.getWriter().print(count > 0 ? "NNNNN" : "NNNNY");
		
		
	
	
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
