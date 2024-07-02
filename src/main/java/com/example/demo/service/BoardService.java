package com.example.demo.service;

import java.util.List;
import com.example.demo.domain.Board;
import com.example.demo.domain.Reply;


public interface BoardService {
	
	public void insertBoard(Board board); // 글을 db에 저장하는 메서드
	
	public int selectLastInsertID(); // 마지막에 저장된 글의 primary key를 불러오는 메서드
	
	int createBoard(Board board); // insertBoard와 selectLastInsertID 메서드를 함께 실행하는 메서드 (같은 트랜젝션에서 실행하기 위해)
	
	public List<Board> selectBoardList(); // db에서 작성된 게시글을 모두 불러오는 메서드
	
	public Board selectBoard(int bId); // db에서 작성된 게시글 하나를 불러오는 메서드
	
	public void updateBoard(Board board); // 수정된 내용을 db에 업데이트하는 메서드
	
	public void deleteBoard(int bid); // db에서 게시글을 삭제하는 메서드
	
	public void updateData(Board board); // 게시글의 p_board, depth, grpord 값을 업데이트하는 메서드
	
	public void updateGrpord(Board board); // 답글일 경우 원글보다 grpord의 값이 큰 게시글의 grpord값을 +1시켜주는 메서드
	
	public List<Reply> selectReplyList(int bid);
	
	public void insertReply(Reply reply);
	
	public void updateReply(Reply reply);
	
	public void updateReplyGrpord(Reply reply);
}
