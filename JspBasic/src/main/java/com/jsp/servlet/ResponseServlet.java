package com.jsp.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//서블릿파일로 만든 경우임!


//@WebServlet를 지정하면 따로 서블릿을 등록하고 url mapping하는 과정을 생략할 수 있습니다.
@WebServlet("/resp")//맵핑을 대신 해준다!
public class ResponseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ResponseServlet() {
        super();
    }

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("service 메서드가 호출됨!");
		
		//요청과 함께 넘어온 아이디를 얻자!
		//파라미터 변수명 제대로 입력 안하면 null이 옵니다.
		int age = Integer.parseInt(request.getParameter("customerAge"));
		
		if(age >= 20) {
			//성인 페이지로 이동시키고 싶다!
			response.sendRedirect("/JspBasic/response/res_adult.jsp");
			//이렇게 jsp파일경로로 response용 파일을 따로 만들어서 요청이 들어오면 경로의 jsp파일을 보내는 것!
		}else {
			//미성년자 페이지로 이동시키고 싶다!
			response.sendRedirect("/JspBasic/response/res_underage.jsp");
			
		}
		
	}

}













