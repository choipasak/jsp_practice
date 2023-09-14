package com.jsp.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.board.model.BoardRepository;

public class DeleteService implements IBoardService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		int bId = Integer.parseInt(request.getParameter("bId"));
		BoardRepository.getInstace().delete(bId);
		//delete는 돌아올 값이 없음. 삭제 메서드니까!
//		System.out.println(bId);
//		request.setAttribute("bId", bId);
	}

}
