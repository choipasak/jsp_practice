package com.jsp.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//age의 값을 브라우저로 보내기 위한 클래스
@WebServlet("/session/servlet")
public class SessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public SessionServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");//한글깨지지말게 설정해주기
		
		String id = request.getParameter("ID");
		String pw = request.getParameter("PW");
		
		if(id.equals("abc123") && pw.equals("aaa111!!!")){
			
			/*
            - 세션은 http 통신 데이터를 유지하기 위한 수단으로 사용합니다.
            - 세션에 데이터를 저장할 때는 session 객체의 메서드 setAttribute()
             메서드를 사용합니다.
            - 해당 메서드의 첫번째 매개값으로 세션 데이터의 이름을 정하고, 두 번째 매개값으로
             세션에 저장할 값을 지정합니다.
            */
			HttpSession session = request.getSession();//HttpSession을 반환해줌
			session.setAttribute("user_id", id);//session에 유지하고 하는 데이터를 담았음
			//로그인에 성공하면 밑의 페이지로 이동시킨다.
			response.sendRedirect("/JspBasic/session/session_welcome.jsp");//데이터를 보냄.
			
		}else {
			//경고창을 띄워서 뒤로가기 진행을 하게 해보자!
			
			//먼저 파일 깨지지 않게 설정해주고
			response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            
            PrintWriter w = response.getWriter();
            
            //경고창을 띄우는 html로직
            String htmlCode = "";
            htmlCode += "<script>\r\n"
                    + "        alert('로그인에 실패했습니다.');\r\n"
                    + "        history.back();\r\n"
                    + "    </script>\r\n"
                    + "";
            
            w.write(htmlCode);
            w.flush();
            w.close();
            
		}
	}

}






