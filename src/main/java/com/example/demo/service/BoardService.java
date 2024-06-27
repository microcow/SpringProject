package com.example.demo.service;

import java.util.List;
import com.example.demo.domain.Board;


public interface BoardService {
	
	public void insertBoard(Board board);
	
	public int selectLastInsertID();
	
	int createBoard(Board board);
	
	public List<Board> selectBoardList();
	
	public Board selectBoard(int bId);
	
	public void updateBoard(Board board);
	
	public void deleteBoard(int bid);
	
	public void insertP_board(Board board);
	
}
