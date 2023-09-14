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
	
	//선생님.ver
	//글번호를 가지고 특정 게시물 객체를 리턴하는 메서드
	public BoardVO getContent(int bId) {
		return boardList.get(bId-1);//bid가 0번부터 시작하지 않기 때무네
	}
	
	//객체를 수정하는 메서드
	public void update(BoardVO vo, int bId) {//갈아끼울거니까 객체와 몇번째 객체인지 알기위한 숫자를 받는다.
		boardList.set(bId-1, vo);
	}
	
	//객체를 삭제하는 메서드
	public void delete(int bId) {//지우고 땡이기 때문에 void, 조회하는 경우에만 리턴값을 준다!
		boardList.remove(bId-1);
	}
	
	//이름을 받아서 검색하는 메서드
	public List<BoardVO> search(String name) {
		List<BoardVO> vo = new ArrayList<>();
		for(BoardVO f : boardList) {
			if(f.getWriter().contains(name)) {
				vo.add(f);
			}
		}
		return vo;
	}
}














