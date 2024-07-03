package com.example.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Board;
import com.example.demo.domain.Reply;
import com.example.demo.mapper.BoardMapper;


@Service("BoardServiceImpl") 
// @Service("BoardServiceImpl")과 같이 @Service 어노테이션을 사용하여 클래스를 등록하면 해당 클래스 전체가 스프링의 빈으로 등록된다.
// ("BoardServiceImpl")는 생략 가능
public class BoardServiceImpl implements BoardService {
	@Autowired BoardMapper boardmapper;
	
	
	@Override
	public void insertBoard(Board board) {
		boardmapper.insertBoard(board);
	}
	
	@Override
	public int selectLastInsertID() {
		return boardmapper.selectLastInsertID();
	}
	
	@Override
	@Transactional
	public int createBoard(Board board) {
		boardmapper.insertBoard(board);
		return boardmapper.selectLastInsertID();
	}
	
	@Override
	public Board selectBoard(int bId) {
		return boardmapper.selectBoard(bId);
	}
	
	@Override
	public List<Board> selectBoardList() {
		return boardmapper.selectBoardList();
	}
	
	@Override
	public void updateBoard(Board board) {
		boardmapper.updateBoard(board);
	}
	
	@Override
	public void deleteBoard(int bid) {
		boardmapper.deleteBoard(bid);
	}
	
	@Override
	public void updateData(Board board) {
		boardmapper.updateData(board);
	}
	
	@Override
	public void updateGrpord(Board board) {
		boardmapper.updateGrpord(board);
	}
	
	@Override
	public List<Reply> selectReplyList(int bid) {
		return boardmapper.selectReplyList(bid);
	}
	
	@Override
	public void insertReply(Reply reply) {
		boardmapper.insertReply(reply);
	}
	
	@Override
	public void updateReply(Reply reply) {
		boardmapper.updateReply(reply);
	}
	
	@Override
	public void updateReplyGrpord(Reply reply) {
		boardmapper.updateReplyGrpord(reply);
	}
	
	@Override
	public void changeReply(Reply reply) {
		boardmapper.changeReply(reply);
	}
	
	@Override
	public void deleteReply(Reply reply) {
		boardmapper.deleteReply(reply);
	}

}
