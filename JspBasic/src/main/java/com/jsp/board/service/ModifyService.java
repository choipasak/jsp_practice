package com.jsp.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.board.model.BoardRepository;

public class ModifyService implements IBoardService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		int bId = Integer.parseInt(request.getParameter("bId"));
		request.setAttribute("article", BoardRepository.getInstace().getContent(bId));
		//BoardRepository.getInstace().getContent(bId): 글 내용을 가지고 온다. 수정하는거니까
		//변수선언해서 그 값을 넣는게 아니라 점점 문법을 압축시켜서 작성하기 위해서 위처럼 작성
		request.setAttribute("boardNo", bId);
		//BoardVO에 글 번호가 없지만 한번만 전해주면 되기 때문에 이렇게 계속 request에 태워보낸다.
	}

}
