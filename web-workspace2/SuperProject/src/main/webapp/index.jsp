<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>웰컴파일</title>
</head>
<body>

	<!-- 
		* WEB 환경에서의 CRUD
		: 대부분의 응용 프로그램이 가지는 데이터 처리기능을 묶어서 일컫는 말
		  사용자 인터페이스가 갖춰야하는 기능을 가리키는 용도로도 사용됨
		  
		- C : Create(생성)		=> INSERT
		- R : Read(읽기/인출)		=> SELECT
		- U : Update(갱신/수정)	=> UPDATE
		- D : Delete(삭제)		=> DELETE / UPDATE

	* 회원서비스
	로그인(CRUD 중 R)*, 회원가입(C)*-(아이디중복체크), 마이페이지(R), 내정보변경(U), 회원탈퇴(D/U)
	
	* 공지사항 서비스 CRUD => ☆★☆★ 숙제(우선순위 1순위) ☆★☆★ => 주말까지 숙제로 해올것
	
	* 일반게시판 서비스
	게시글목록조회(R)-페이징처리, 상세조회(R), 게시글 작성(C)-첨부파일 1개 업로드, 게시글수정(U), 게시글삭제(U/D)
	
	* 사진게시판 서비스
	사진게시글목록조회(R)이미지, 상세조회(R), 게시글작성(C)-다중파일업로드
	
	 -->
	 
	<jsp:include page="/WEB-INF/views/include/header.jsp" />
	<jsp:include page="/WEB-INF/views/include/main.jsp" />
	<jsp:include page="/WEB-INF/views/include/footer.jsp" />
	
</body>
</html>