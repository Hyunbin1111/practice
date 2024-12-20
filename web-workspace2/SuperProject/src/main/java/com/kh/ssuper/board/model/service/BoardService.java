package com.kh.ssuper.board.model.service;
import java.sql.Connection;
import java.util.List;

import com.kh.ssuper.board.model.dao.BoardDao;
import com.kh.ssuper.board.model.vo.Attachment;
import com.kh.ssuper.board.model.vo.Board;
import com.kh.ssuper.board.model.vo.Category;
import com.kh.ssuper.board.model.vo.Reply;
import com.kh.ssuper.common.JDBCTemplate;
import com.kh.ssuper.common.PageInfo;
public class BoardService {
	
	static {
		JDBCTemplate.registDriver();
	}
	
	public int selectListCount() {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new BoardDao().selectListCount(conn);
		
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	
	public List<Board> findAll(PageInfo pi) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		// 인라인뷰 활용
		// 1) ORDER BY절은 순서가 가장 마지막인데 정렬이 끝난 상태가 필요함!
		//	  일단 정렬해주는 SELECT문을 만들고 -> 서브쿼리로 사용
		// 2) 서브쿼리를 FROM절에 넣음 + ROWNUM도 같이 사용
		
		// BETWEEN AND에 정수값(첫값,끝값)을 넣어줘야한다
		/*
		 * boardLimit이 10이라는 가정하에
		 * current Page == 1 -> 게시글의 시작값 : 1 , 끝값 : 10
		 * current Page == 2 -> 시작값 : 11 , 끝값 : 20
		 * current Page == 3 -> 시작값 : 21 , 끝값 : 30
		 *
		 * 시작값 = (currentPage - 1) * boardLimit + 1;
		 * 끝값 = 시작값 + boardLimit - 1;
		 *
		 */
		
		int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
		
		int endRow = startRow + pi.getBoardLimit() - 1;
		
		List<Board> boardlist = new BoardDao().findAll(conn, startRow, endRow);
		
		JDBCTemplate.close(conn);
		
		return boardlist;
		
	}


	public List<Category> selectCategory() {
		
		Connection conn = JDBCTemplate.getConnection();
		
		List<Category> list = new BoardDao().selectCategory(conn);
		
		JDBCTemplate.close(conn);
		
		
		return list;
	}


	public int insert(Board board, Attachment attachment) {

		Connection conn = JDBCTemplate.getConnection();
		
		// 1) BOARD테이블에 INSERT
		int boardResult = new BoardDao().insertBoard(conn, board);
		
		// 2) Attachment 테이블에 INSERT
		int attachmentResult = 1;
		if(attachment != null) {
			attachmentResult = 
					new BoardDao().insertAttachment(conn, attachment);
		}
		
		// dao를 두번 호출해야하는경우 Service에서 할 것
		
		// 3) 트랜잭션 처리
		// boardResult가 성공이고 attachmentResult도 성공이면
		// commit
		// 둘 중 하나라도 실패했다면
		// rollback
		if(boardResult > 0 && attachmentResult > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return (boardResult * attachmentResult);
		
	}
	
	

	public Board findById(int boardNo) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		BoardDao boardDao = new BoardDao();

		Board board = null;
		
		// 1.조회수 증가
		int result = boardDao.increaseCount(conn, boardNo);
		
		
		// 2. 조회수 증가에 성공했다면 한 행 조회
		if(result > 0) {
			board = boardDao.findById(conn, boardNo);
			JDBCTemplate.commit(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return board; //controller에게 반환
		
	}

	
	public Attachment selectAttachment(int boardNo) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		Attachment attachment = new BoardDao().selectAttachment(conn, boardNo);
		
		JDBCTemplate.close(conn);
		
		return attachment;
	}
	
	public Board selectBoard(int boardNo) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		Board board = new BoardDao().findById(conn, boardNo);
		
		JDBCTemplate.close(conn);
		
		return board;
	}

	public int update(Board board, Attachment attachment) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new BoardDao().updateBoard(conn, board);
		
		// ATTACHMETN는 ??
		int attachmentResult = 1;
		
		// 새롭게 파일을 첨부했을 경우
		if(attachment != null) {
			
			if(attachment.getFileNo() != 0) {
				// 기존의 첨부파일이 존재했을 경우 => UPDATE
				attachmentResult =
						new BoardDao().updateAttachment(conn,attachment);
			} else {
				// 기존의 첨부파일이 존재하지 않았을 경우 => INSERT
				attachmentResult =
						new BoardDao().insertNewAttachment(conn, attachment);
			}
		}// 아닐 경우는 할 게 없음, else 없어도 됨
		
		// 둘 다 성공했을 경우에만 commit;
		// 하나라도 실패했다 rollback;
		
		
		if((result * attachmentResult) > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
	
		return (result * attachmentResult);
	}


	public int insertThumbnailBoard(Board board, List<Attachment> list) {

		Connection conn = JDBCTemplate.getConnection();
		
		// 한 개의 트랜잭션에 최소 2개에서 최대 5개까지의 DML구문이 만들어질 것
		int boardResult = new BoardDao().insertThumbnailBoard(conn, board);
		
		// boardResult에 돌아온값이 0이라면 attachment까지 갈 필요가 없음
		// 바로 커넥션 닫고 실패했을때 값 돌려주면 효율적
		if(!(boardResult > 0)) {
			JDBCTemplate.close(conn);
			return 0;
		}
		
		
		int attachmentResult = new BoardDao().insertAttachmentList(conn, list);
		
		if((boardResult * attachmentResult) > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
			
		return (boardResult * attachmentResult);
	}


	public List<Board> selectList() {
		
		Connection conn = JDBCTemplate.getConnection();
		
		List<Board> list = new BoardDao().selectList(conn);
		
		
		JDBCTemplate.close(conn);
		
		
		return list;
	}


	public List<Attachment> selectImageList(int boardNo) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		List<Attachment> list = new BoardDao().selectImageList(conn, boardNo);
		
		JDBCTemplate.close(conn);
		
		return list;
		
	}


	public List<Reply> selectReplyList(int boardNo) {

		Connection conn = JDBCTemplate.getConnection();
		
		List<Reply> replyList = new BoardDao().selectReplyList(conn, boardNo);
		
		JDBCTemplate.close(conn);
		
		return replyList;
	}


	public int insertReply(Reply reply) {

		Connection conn = JDBCTemplate.getConnection();
		
		int result = new BoardDao().insertReply(conn, reply);
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}
		JDBCTemplate.close(conn);
		
		return result;
	}
	
}