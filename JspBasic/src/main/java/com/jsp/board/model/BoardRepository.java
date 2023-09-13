package com.jsp.board.model;

import java.util.ArrayList;
import java.util.List;

//우리가 DB를 아직 안배워서;;
//DB 역할을 대신할 List를 하나 선언해서 BoardVO 객체를 저장하겠습니다.
public class BoardRepository {
	
	//외부에서 이 리스트에 직접 접근하지 못하게 하겠습니다.
	//부르고 싶다면! 메서드를 불러라! 밑에!
	private static final List<BoardVO> boardList = new ArrayList<>();
	
	private BoardRepository() {}
	
	private static BoardRepository repository = new BoardRepository();
	
	public static BoardRepository getInstace() {
		return repository;
	}
	
	//게시글 등록 메서드!
	public void regist(BoardVO vo) {
		boardList.add(vo);
		//객체를 보내면(=글 등록하고 싶으면) 보드리스트에 저장해줄게~(직접 접근제한해버리기)
	}
	
	//전체 게시물(boardList)을 담고 있는 리스트를 리턴
	//글 등록 후의 자동재요청에 따라 전체 글 목록을 전달해 주기 위한 메서드이다.
	public List<BoardVO> getList(){
		return boardList;
	}
	
	
	
}














