package com.jsp.board.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.board.model.BoardRepository;
import com.jsp.board.model.BoardVO;

public class UpdateService implements IBoardService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		//사용자의 입력값 다 가져와서 글번호를 통해 list문법중 객체를 교체하는 문법이잇음
		//이용해서 글목록의 글을 교체시켜서 수정이 완료된ㄴ 글의 상세보기 화면이 응답되어야함.
		//새롭게 입력받은 수정값으로 BoardVO 객체를 생성해서 수정을 진행하세요.
		//(기존 리스트에 존재하는 객체를 새로운 객체로 교체)
		//수정이 완료되면 수정된 글의 상세보기 페이지로 응답이 나가야 합니다.
		
		int bId = Integer.parseInt(request.getParameter("boardNo"));
		
		//폼에서 넘어오는 값 가져오기
		/*
		 * 이렇게 변수를 선언해서 따로 값을 받아도 된다!
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		*/
		
		//폼에서 넘어오는 값 가져오기 동시에 새롭게 생성한 객체에 값 넣기
		BoardVO vo = new BoardVO(
				request.getParameter("writer"),
				request.getParameter("title"),
				request.getParameter("content"),
				LocalDateTime.now()
				);
	
		//객체를 레파지토리에 저장
		BoardRepository.getInstace().update(vo, bId);
		
		
		request.setAttribute("modicon", vo);
		request.setAttribute("bId", request.getParameter("boardNo"));
		
		//List<BoardVO> modi = BoardRepository.getInstace().getModify(bId);
		//tomodi = request.getAttribute("update");
//		BoardVO newcon = request.setAttribute("newcon", BoardRepository.getInstace().getList("boardNo"));
		
//		String contentnum = request.getParameter("BoardNo");
		
		
		
	}

}
