package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.domain.Board;

@Mapper
public interface BoardMapper {
	
	/*
	@Insert("INSERT INTO sb_board (b_title, b_content, b_writer) VALUES (#{bTitle}, #{bContent}, #{bWriter})")
    public void insertBoard(Board board); 
    // Insert 어노테이션 기반으로 매핑을 통해 db에 값을 저장하는 법 (XML을 거치지 않음)
    // 해당 방법을 사용할 때, XML에 해당 메서드 명으로 매핑받는 sql문이 있을 경우 오류 발생
	*/
	
	
	public void insertBoard(Board board); // XML 기반으로 매핑을 통해 db에 값을 저장하는 법 (XML로 전달됨)
	
	@Select("SELECT LAST_INSERT_ID()") // 어노테이션 기반으로 db의 값을 불러오는법 (XML을 거치지 않음)
	  public int selectLastInsertID();
	
	public List<Board> selectBoardList();
	
	public Board selectBoard(int bId);
	
	public void updateBoard(Board board);
	
	public void deleteBoard(int bid);
	
	public void insertP_board(Board board);
}