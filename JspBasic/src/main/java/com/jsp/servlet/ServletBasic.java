package com.jsp.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
	- 서블릿이란 웹 페이지를 자바 언어로만 구성하는 것입니다.
	즉, jsp 파일을 자바 언어로만 구성하는 것입니다. 
	톰캣을 사용해서 jsp 파일을 자동으로 class로 변환했다면
	지금은 직접 클래스를 생성해서 클래스로 요청을 처리해 보자는 것입니다. -> 이 역할을 하는게 servlet
 */

public class ServletBasic extends HttpServlet{

	//# 서블릿 클래스를 만드는 방법
	//1. HttpServlet 클래스를 상속 받아야 함(Abstract클래스임)
	//Spring을 사용하면 위의 과정을 할 필요가 없음
	//그니까 지금은 사용 안한다는 얘기

	//2. 생성자를 선언 (선택)
	public ServletBasic() {
		System.out.println("페이지 요청이 들어옴!");
		System.out.println("서블릿 객체가 생성됨!!");
	}

	//3. HttpServlet이 제공하는 상속 메서드를 목적에 맞게 오버라이딩(재정의)합니다.
	//클라이언트가 어떠한 요청을 했을 때 서버는 어떠한 처리와 함께 응답을 할 것인가?
	//init(): 객체가 생성될 때 호출, doGet(): 요청이 get방식,
	//doPost(): 요청이 post방식, service(), destroy(): 객체가 소멸되기 전에 호출...

	@Override
	public void init() throws ServletException {
		/*
        - 페이지 요청이 들어왔을 때 처음 실행할 로직을 작성.
        - init()은 컨테이너(서버 프로그램)에 의해 서블릿 객체가 생성될 때
        최초 1회 자동으로 호출됩니다.
        - 객체의 생성자와 비슷한 역할을 수행합니다.
		 */
		System.out.println("init 메서드 호출!");
	}

	/*
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
        - 페이지 요청이 들어왔을 때 자동으로 호출.
        - get, post 구분 없이 들어오는 모든 요청과 응답을 관제할 수 있다.
    	- 매개값으로 요청, 응답 내장 객체가 전달됩니다. 
	 */

	/*
		 - get: 경로가 노출이 되어도 상관 없는 데이터를 보낼 때 사용
		 form태그 굳이 필요X => a태그로 링크 넣어줄 때 경로가 노출되어 있는 경로를
		 그대로 작성해주면 get을 굳이 사용하지 않아도 된다.
		 보내는 데이터의 양에 제한이 있
		 (왜: 주소창이 최대값으로 받을 수 있는 양이 정해져 있기 때문, 넘으면 짤려서 전송됨)

		 - post: 사용자의 개인정보 등을 보낼 때 사용
		 반드시 form태그 필요
		 보내는 데이터의 양의 제한이 없다.


		System.out.println("서비스 메서드가 호출됨!");

		//요청 방식이 뭐니
		String method = request.getMethod();
		//요청 URI가 뭐니?
		String uri = request.getRequestURI();
		//요청 파라미터
		String queryString = request.getQueryString();
		//요청을 보낸 IP주소
		String addr = request.getRemoteAddr();

		//요청에 관련된 것들은 다 request가 가지고 있다고 생각하면 된다!

		System.out.println("===== 값 확인하기 =====");
		System.out.println("method: " + method);
		System.out.println("uri: " + uri);
		System.out.println("queryString: " + queryString);
		System.out.println("addr: " + addr);// (v6)localhost랑 똑같다고 생각하면 된다!
		//요청을 받으면 request가 실제로 받는지를 확인하는 출력문들


		//사용자가 입력한 값을 getParameter로 얻어서
		//다시 브라우저로 응답으로 바로 내보내주기 방법
		//요청과 함께 전달된 파라미터를 낱개로 얻는 방법
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String name = request.getParameter("name");

		System.out.println("아이디: " + account);
		System.out.println("비밀번호: " + password);
		System.out.println("이름: " + name);

		//응답하고자 하는 content의 타입과 문자열 인코딩을 셋팅.
		//이렇게 설정하지X -> 파일 다 깨짐.
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");


		//이렇게 값 뽑아낸걸 html파일을 만들어서 짜서 전달해 줘야함
		//응답 화면 제작
		//자바 클래스에서 브라우저로 바로 응답을 구현하기 위해서는
		//PrintWriter 객체를 사용합니다.
		//이미 완성되있는 객체를 받아와서 메서드를 호출?사용? 함
		PrintWriter w = response.getWriter();
		//ㄴ> io클래스인데, 클래스에서 브라우저로 out한다는 의미

		String htmlCode = "";

		htmlCode += "<!DOCTYPE html> \r\n" 
				+ "<html>\r\n" 
				+ "<head>\r\n" 
				+ "<meta charset=\"UTF-8\"> \r\n"
				+ "<title>서블릿 요청과 응답 연습!</title>"
				+ "</head>\r\n"
				+ "<body>\r\n" 
				+ "\r\n"
				+ "\t아이디: " + account + "<br>\r\n"
				+ "\t비밀번호: " + password + "<br>\r\n"
				+ "\t이름: " + name + "\r\n"
				+ "</body>\r\n"
				+ "</html>";

		//이제 브라우저로 쏘면 된다
		w.write(htmlCode); //버퍼에 응답하고자 하는 데이터 작성하기
		w.flush(); //버퍼를 비우면서 작성한 내용을 브라우저로 밀어내기.왜: 다음 응답을 위해서 비운다
		w.close(); // 객체 해제
	}
	 */

	//만약 get과 post를 따로 관리하고 싶다면!
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//http 통신 중 get 요청이 발생했을 때 자동으로 호출되는 메서드
		//매개값으로 내장객체 request와 response가 전달되므로
		//객체의 메서드를 통해 파라미터를 가져오거나, 페이지 이동이 가능합니다.
		System.out.println("doGet 메서드가 호출됨!");
		
		System.out.println("아이디: " + req.getParameter("account"));
		System.out.println("비밀번호: " + req.getParameter("password"));
		System.out.println("이름: " + req.getParameter("name"));
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//http 통신 중 post 요청이 발생했을 때 자동으로 호출되는 메서드
        //doGet과 마찬가지로 내장객체 request와 response를 매개값으로 받습니다.
		//post방식으로 요청이 들어왔을 때, 함께 전송된 파라미터(한글)를 얻으실 때는
		//헤더파일이 한글을 읽지 못하기 때문에 인코딩 작업을 다시 거치셔야 합니다. -> 스프링에서는 필요X
		
		req.setCharacterEncoding("UTF-8");		
		
		System.out.println("doPost 메서드가 호출됨!");
		
		System.out.println("아이디: " + req.getParameter("account"));
		System.out.println("비밀번호: " + req.getParameter("password"));
		System.out.println("이름: " + req.getParameter("name"));
		//ㄴ> 사용자의 입력값을 숨겨서(헤더파일에) 가져오는데
		//	 한글을 디코딩할때 깨져서 이렇게 바로 꺼내서 출력하면 깨진게 바로 출력된다.
		
	}
	
	@Override
	public void destroy() {
		//서블릿 객체가 소멸될 때 호출되는 메서드 (객체 소멸 시 1회 자동으로 호출)
		//대부분 객체 반납이나 소멸 등에 사용.
		//필요하면 오버라이딩 작성!
		
		//확인
		System.out.println("destroy 메서드가 호출됨!");
	}
	


}















