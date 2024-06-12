package com.example.demo.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.example.demo.domain.Board;

@Mapper
public interface BoardMapper {
	public List<Board> selectBoardList();
}