package com.kh.ssuper.board.model.service;

import java.util.List;
import java.util.Map;

import com.kh.ssuper.board.model.vo.Board;
import com.kh.ssuper.board.model.vo.Reply;
import com.kh.ssuper.common.PageInfo;

public interface BoardService {

	// 게시글 관련 기능
	
	// 1. 목록 조회(페이징처리)
	
	int selectListCount();
	
	List<Board> selectList(PageInfo pi);
	
	// 2. 상세조회
	
	int increaseCount(int boardNo);
	Board selectBoard(int boardNo);
	
	List<Reply> selectReplyList(int boardNo);
	
	
	/*
	 * 1. 수업시간에 사용해봤던 내용은
	 * 1) 동기식 요청으로 게시글의 정보를 조회할 때 select를 한번 더 수행해서 조회
	 * 2) 동기식 요청으로 조회하되 게시글 정보를 한 꺼번에 조회해서 가져갈 예정
	 */
	
	// 3. 검색서비스
	// 검색 된 결과의 개수 먼저 세야함
	int searchedCount(Map<String, String> map);
	List<Board> selectSearchList(PageInfo pi, Map<String, String> map);
	
}
