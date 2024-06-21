package com.example.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.domain.Board;
import com.example.demo.mapper.BoardMapper;


@Service("BoardServiceImpl") 
// @Service("BoardServiceImpl")과 같이 @Service 어노테이션을 사용하여 클래스를 등록하면 해당 클래스 전체가 스프링의 빈으로 등록된다.
// ("BoardServiceImpl")는 생략 가능
public class BoardServiceImpl implements BoardService {

	@Autowired BoardMapper boardmapper;
	
	@Override
	public List<Board> selectBoardList() {
		return boardmapper.selectBoardList();
	}
	
	@Override
	public void insertBoard(Board board) {
		boardmapper.insertBoard(board);
	}

}
