<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
header.masthead {
   display: none;
}
.row{
	height : 800px;
}
tr:hover{
	cursor : pointer;
}
</style>
<br/><br/>
   <jsp:include page="../include/header.jsp"/>
   <!-- Begin Page Content -->
   <div class="container">
      <div class="row">
         <div class="col-lg-1">
         </div>
         <div class="col-lg-10">
            <div class="panel-body">
            <h2 class="page-header"><span style="color: #52B1FF;">KH</span> 자유 게시판
               <a href="enrollForm.board" class="btn float-right" style="background-color: #52B1FF; margin-top: 0; height: 40px; color: white; border: 0px solid #F78F24; opacity: 0.8">글쓰기</a>
            </h2>
               <table class="table table-bordered table-hover">
                  <thead>
	                  <tr style="background-color: #52B1FF; margin-top: 0; height: 40px; color: white; border: 0px solid #F78F24; opacity: 0.8">
	                     <th width="100">번호</th>
	                     <th width="150">카테고리</th>
	                     <th width="150">작성자</th>
	                     <th width="450">제목</th>
	                     <th width="200">작성일</th>
	                     <th width="100">조회수</th>
	                  </tr>
                  </thead>
                  <tbody>
	           		<!-- 게시글 리스트 목록이 출력될 영역 list의 담겨져온 친구들을 가지고 출력을 해줘야함
	           				==> 반복문 이용=c:forEach -->
	                <c:forEach items="${ list }" var="board" >
                    <tr style="color: #52B1FF;"
                        class="board"
                        id="${ board.boardNo }"> <!-- ${ board.boardNo }-->
                        <td>
                        ${ board.boardNo }
                        </td>
                        <td>
                        ${ board.category }
                        </td>
                        <td>
                        ${ board.boardWriter }
                        </td>
                        <td style="color: #52d6ffcc;">
                        ${ board.boardTitle } &nbsp;
                        <!-- 
                        <a href="detail.board?boardNo=${ board.boardNo }">                        
                        </a>
                         -->
                        </td>
                        <td>
                        ${ board.createDate }
                        </td>
                        <td>
                        ${ board.count }
                        </td>
                    </tr>
	        		</c:forEach>
                  </tbody>
               </table>
               
               <script>
                 $(function(){
                	 
                	$('.board').click(e => {
                		
                		// detail.board?boardNo= ~~~
                		// ~~~에 들어가게될 게시글을 구분할 값 구하기
                		const targetId = e.currentTarget.id;
                		
                		location.href = 'detail.board?boardNo=' + targetId; 
                		
                	});
                	 
                 })
               </script>
               
            </div>
         <div class="paging-area" align="center" >
        	
        		<c:if test="${ pi.currentPage > 1 }">
	        	<button
	       		class="btn btn-outline-primary" style="color:#52B1FF;"
	       		onclick="location.href='list.board?currentPage=${pi.currentPage - 1}'">이전</button>
				</c:if>
			
				<c:forEach begin="${ pi.startPage }" end="${ pi.endPage }"
						   var="i">
				
                <button
                class="btn btn-outline-primary" style="color:#52B1FF;"
                onclick="location.href='list.board?currentPage=${i}'">${ i }</button>
				
				</c:forEach>

	        	<c:if test="${ pi.currentPage ne pi.maxPage }">
	        	<button
	       		class="btn btn-outline-primary" style="color:#52B1FF;"
	       		onclick="location.href='list.board?currentPage=${pi.getEndPage() + 1}'">다음</button>
        		</c:if>
        </div>
         </div>
      </div>
      
      
   </div>
   
   <jsp:include page="../include/footer.jsp"/>