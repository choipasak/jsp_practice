package com.jsp.board.service;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.board.model.BoardRepository;
import com.jsp.board.model.BoardVO;

public class RegistService implements IBoardService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		//위의 입력값이 하나의 글. 이기 때문에 하나의 객체로 포장해서 DB역할하는 객체에 넘기자!
		
		BoardVO vo = new BoardVO();
		vo.setWriter(writer);
		vo.setTitle(title);
		vo.setContent(content);
		vo.setRegDate(LocalDateTime.now());//나중에 DB쓰면 이런거 안해용
		//이렇게 값을 넣는 이유: 생성자의 매개변수에 regDate는 DB에서 줘야하기 때문에
		//지금 당장은 null값을 입력할 수 밖엔 없다.
		//null값을 주기 싫기 때문에 참조변수를 통해 값 전달!
		
		BoardRepository.getInstace().regist(vo);//글 등록 완료!
	}

}
