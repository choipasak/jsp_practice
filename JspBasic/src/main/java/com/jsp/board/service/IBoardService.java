package com.jsp.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IBoardService {
	
	//모든 execute를 구현하는 객체는 request와 response를 전달 받는다!
	public void execute(HttpServletRequest request, HttpServletResponse response);
	
}
