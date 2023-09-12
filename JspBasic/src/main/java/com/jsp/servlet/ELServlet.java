package com.jsp.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.User;


@WebServlet("/el/obj")
public class ELServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
    public ELServlet() {
        super();
    }

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//obj에서 받는 입력값은 4개임. 그럼 session을 4개를 만드냐
		//아님: 저 4개를 다 품을 수 있는 객체를 디자인해서 넣어주자! -> 클래스를 하나 만든다.
//		String id = request.getParameter("id");
//		String pw = request.getParameter("pw");
//		String name = request.getParameter("name");
//		String email = request.getParameter("email");
		//앞으로는 위처럼 따로 변수로 선언하지말고 아래처럼 메서드체이닝을 사용하려고 노력하자
		
		User user = new User(
				request.getParameter("id"),
				request.getParameter("pw"),
				request.getParameter("name"),
				request.getParameter("email")
		);
		//이 service메서드가 호출되면 입력값을 매개값으로 받아
		//디자인한 User객체에 getParameter로 담아준다.
		
		request.getSession().setAttribute("member", user);
		//세션에 저장함. 뭐를: user를. 구분하기위해서 member라는 이름을 붙여서.
		//그리고 세션을 얻어서(바로 setAttribute를 할 수 있음) member라는 이름으로
		//user 객체를 set해줌
		
		response.sendRedirect("/JspBasic/EL/el_obj2.jsp");
		//내가 지정한 url로 강제로 이동하게 응답해준다.
		//set한 다음 위의 양식으로 응답해준다.
		
	}

}
