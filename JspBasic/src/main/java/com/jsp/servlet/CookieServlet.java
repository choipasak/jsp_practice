package com.jsp.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/cookie/login")
public class CookieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public CookieServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		if(id.equals("abc1234") && pw.equals("aaa1234")) {
			
			//# 쿠키 생성 방법
			//1. 쿠키 객체를 생성 - 생성자의 매개값으로 쿠키의 이름과 저장할 데이터 입력(String)
			Cookie loginCoo = new Cookie("login_cookie", id);//매개값은 setAttribute()처럼지정! 그대신 둘 다 문자열만.
			
			//2. 쿠키 클래스의 setter 메서드로 쿠키의 속성을 설정.(필수: 쿠키의 수명)
			loginCoo.setMaxAge(5);//쿠키의 유효 시간 설정 단위: 초. 1시간 -> 60 * 60 으로 작성 권장
			
			//3. http 응답 시 response 객체에 생성된 쿠키를 탑재해서 클라이언트에게 전송.
			response.addCookie(loginCoo);
			//응답에 쿠키 태워서 보내기(전달)
			
			//사용자가 아이디 기억하기 체크박스를 체크했는지의 여부 확인.
			if(request.getParameter("rememberId") != null) {//체크를 했다면 null이 아님.
				//rememberId의 value값인 true가 오지 않았다면
				Cookie idMemory = new Cookie("rememberId", id);
				idMemory.setMaxAge(30);//수명 30초로 잡아주고
				response.addCookie(idMemory);//응답과 함께 로그인유지+아이디기억 해서 쿠키 2개를 태워보낸다.
			}
			

			response.sendRedirect("/JspBasic/cookie/cookie_welcome.jsp");
			//sendRedirect가 제공된 경로로 바로 이동하는게 아님
			//만약 로그인을해 그럼 여기로 요청이 오잖아, 그때 쿠키를 만들면서 위의 명령어를 응답으로 보내(to브라우저)
			//
			//경로로 가라고 브라우저로 응답함
			//그럼 저 경로로 브라우저에서 요청함
			//그럼 저 경로가 응답을 다시 브라우저로 전달함
			//그래서 저 경로로 화면이 바뀌는 것임
			//한번쓰고 말 데이터를 request를 사용함. -> session을 쓰면 내가 저장한걸 다시 지워줘야하기때문
			//request는 response를 받으면 
			//소멸하기 때문.
			
			
			
			
		}else {
			response.sendRedirect("/JspBasic/cookie/cookie_login.jsp");
		}
		
	}

}
