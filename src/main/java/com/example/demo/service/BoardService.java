package com.example.demo.service;

import java.util.List;
import com.example.demo.domain.Board;


public interface BoardService {
	
	public List<Board> selectBoardList();
	
	public void insertBoard(Board board);
	
	public Board selectBoard(int bId);
	
	public void updateBoard(Board board);
	
	public void deleteBoard(int bid);
	
}
