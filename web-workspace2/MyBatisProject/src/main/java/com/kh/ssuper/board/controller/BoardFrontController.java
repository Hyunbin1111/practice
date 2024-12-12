package com.kh.ssuper.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.kh")
public class BoardFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardFrontController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("모든 kh요청이 오면 출력됨 (asfd.kh / fewqr.kh 등 무엇이든)");
		
		String uri = request.getRequestURI();
		//System.out.println(uri);
		
		String mapping = uri.substring(uri.lastIndexOf("/") + 1, uri.lastIndexOf("."));
		System.out.println(mapping);

		BoardController bc = new BoardController();
		
		String view = "";
		
		request.setCharacterEncoding("UTF-8");
		
		switch(mapping) {
		case "insert" : bc.insert(request, response); break;
		case "update" : bc.update(request, response); break;
		
		
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
