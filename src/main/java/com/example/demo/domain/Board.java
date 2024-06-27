package com.example.demo.domain;

public class Board {
	private int bId;
	private int p_board;
	private String bTitle;
	private String bContent;
	private String bWriter;
	private String bDateTime;
	private int depth;
	private int grpord;
	
	
	
	public int getP_board() {
		return p_board;
	}
	public void setP_board(int p_board) {
		this.p_board = p_board;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public int getGrpord() {
		return grpord;
	}
	public void setGrpord(int grpord) {
		this.grpord = grpord;
	}
	public String getbTitle() {
		return bTitle;
	}
	public void setbTitle(String bTitle) {
		this.bTitle = bTitle;
	}
	public String getbContent() {
		return bContent;
	}
	public void setbContent(String bContent) {
		this.bContent = bContent;
	}
	public String getbWriter() {
		return bWriter;
	}
	public void setbWriter(String bWriter) {
		this.bWriter = bWriter;
	}
	public int getbId() {
		return bId;
	}
	public void setbId(int bId) {
		this.bId = bId;
	}
	public String getbDateTime() {
		return bDateTime;
	}
	public void setbDateTime(String bDateTime) {
		this.bDateTime = bDateTime;
	}
}
