package com.kh.ssuper.ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

@WebServlet("/ajax2.do")
public class AjaxController2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AjaxController2() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// POST => 인코딩
		request.setCharacterEncoding("UTF-8");
		
		// 값 뽑기
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		
		// VO가공 => Service
		
		
		// 결과 응답 (Ajax는 순수 데이터만을 응답해준다)
		
		
		// 인코딩 설정 (필수)
		/*
		response.setContentType("text/html; charset=UTF-8");
		
		// 값 넘기기
		//response.getWriter().print(name, age);
		
		String responseData = "이름 : " + name + ", 나이 : " + age;
		
		response.getWriter().print(responseData);
		*/
		
		// AJAX를 활용해서 실제 값을 여러개 응답하고 싶다
		// JSON(JavaScript Object Notation)
		// 데이터 전송 시 이용할 수 있는 포맷형식 중 하나
		// 1. 자바스크립트의 배열 객체 => [value, value, value ...]
		// 2. 자바스크립트의 일반 객체 => {key,value, key:value, ...}
		
		// [name, age]
		// [홍길동, 15]
		
		/*
		String responseData = "['" + name + "'," + age + "]";
		
		System.out.println(responseData);
		
		//response.setContentType("text/html; charset=UTF-8");
		*/
		
		/*
		 * JSON형태로 처리 시 사용하는 클래스
		 * => 자바에서는 기본적으로 제공하지않음 => 라이브러리 추가
		 * 
		 * 1. JSONArray
		 * 2. JSONObject
		 * 
		 */
		
		/*
		JSONArray responseData = new JSONArray(); // []
		// 요소추가 => add()
		responseData.add(name); // ['홍길동']
		responseData.add(age); // ['홍길동', 15]
		*/
		
		JSONObject obj = new JSONObject();
		
		obj.put("name", name);
		obj.put("age", age);
		
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().print(obj);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
