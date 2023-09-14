package com.jsp.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.board.model.BoardRepository;
import com.jsp.board.model.BoardVO;

public class ContentService implements IBoardService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		//선생님.ver
		int bId = Integer.parseInt(request.getParameter("bId"));//bid의 값을 가져오기 + int로 변환
		
		//DB 가져와서 getContent()호출!
		BoardVO board = BoardRepository.getInstace().getContent(bId);
		//board를 가지고 가야한다! + reqeust에 board를 담아줌
		request.setAttribute("content", board);
		request.setAttribute("boardNo", bId);
		
	}

}
