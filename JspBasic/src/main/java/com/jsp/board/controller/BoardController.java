package com.jsp.board.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.board.model.BoardRepository;
import com.jsp.board.model.BoardVO;


@WebServlet("*.board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public BoardController() {
        super();
    }

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getMethod().equals("POST")) {// method="post"일 때, 그리고 매개값은 대문자로 작성해 줘야 한다!
			request.setCharacterEncoding("UTF-8");
		}//한글 깨지지 말라고 인코딩 해준다.
		
		//uri를 정제하자!
		//먼저 uri를 얻어낸다!
		String uri = request.getRequestURI();
		
		//정제!
		uri = uri.substring(request.getContextPath().length()+1, uri.lastIndexOf("."));
		
		//정제된 uri 확인!
		System.out.println("정제된 URI: " + uri);
		
		switch (uri) {
		case "write": //
			System.out.println("글쓰기 페이지로 이동 요청!");
			response.sendRedirect("/JspBasic/board/board_write.jsp");
			//상대경로를 작성할 땐, 호출한 파일의 위치 기준으로 이동하고싶은 경로 작성
			//절대경로는 프로젝트 이름부터 작성.
			break;
			
			
		case "regist":
			System.out.println("글 등록 요청이 들어옴!");
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
			
			//단순하게 생각하면 response.sendRedirect("/JspBasic/board_list.jsp")이렇게 작성해줄 수 있음.
			//근데 막상 jsp파일로 가보면 아무것도 없음!
			//.sendRedirect는 알려준 경로로 브라우저에 자동재요청을 요청하는 응답을 보내는 메서드임
			//그래서 글을 등록 완료 후, 등록된 글이 올라간 글목록을 보여주고 싶으면 .sendRedirect로 list를 보내야함.
			//response.sendRedirect("/JspBasic/list.board"); 라고 해줘야함.
			/*
			 * 선생님의 정리!
            왜 board_list.jsp로 바로 리다이렉트를 하면 안될까?
            board_list.jsp에는 데이터베이스로부터 전체 글 목록을 가져오는
            로직을 작성하지 않을 것이기 때문입니다. (jsp는 단순히 보여지는 역할만 수행)
            컨트롤러로 글 목록 요청이 다시 들어올 수 있게끔
            sendRedirect()를 사용하여 자동 목록 재 요청이 들어오게 하는 겁니다.
            */
			response.sendRedirect("/JspBasic/list.board");
			//.sendRedirect() 는 브라우저에 자동재요청을 원할 때도 사용!!
			
			break;
			
			
		case "list":
			System.out.println("글 목록 요청이 들어옴!");
			List<BoardVO> list = BoardRepository.getInstace().getList();
			//위의 배열을 이제 jsp로 보내서 jsp에서 반복문을 통해 값을 하나씩 꺼내서 보여주게 하면 된다!
			//근데 어떻게 보냄?
			//요청이 들어오면 응답내보내고 끝내고싶어 -> request객체 이용!
			//DB로부터 전달받은 글 목록을 세션에 넣기는 좀 아깝습니다. 세션이 아깝다! 그리고 유지가 너무 길어!
			//세션 -> 데이터를 계속 유지하기 위한 수단. -> 글 목록을 계속 유지해? 왜?
			//글 목록은 한 번 응답하면 더 이상 필요 없다. -> 계속 갱신되는 데이터이기 때문.
			//응답이 나가면 자동으로 소멸하는 request객체를 사용하자.
			request.setAttribute("boardList", list);//세션처럼 값을 담아준다! 메서드 동일
			
			//response.sendRedirect("");를 사용하면 안됌
			//또 왜: request는 response나가면 죽음
			//근데 sendRedirect는 응답이 '응답했고 내가 원한거 다시 요청해줘!' 하는 자동재요청 메서드이기 때문에
			//그럼 어떡해: 그냥 요청을 떠넘기는거임. 요청객체를 넘겨줄테니 너가 처리해줘라 -> 포워딩
			/*
			 여기서 sendRedirect를 하면 안되는 이유.
			 request 객체에 list를 담아서 전달하려 하는데, sendRedirect를 사용하면
			 응답이 바로 나가면서 기존의 request객체가 소멸해 버립니다.
			 여기서는 forward방식을 사용해서 request를 원하는 jsp파일로 전달해서
			 그쪽에서 응답이 나갈 수 있도록 처리해야 합니다.
			 jsp파일로 요청을 떠넘겨서 jsp파일에서 처리할 수 있도록 만드는 것. -> 포워드방식
			 */
			
			//근데 그럼 포워드방식은 어떻게 쓰나요?
			//request 객체를 다음 화면까지 운반하기 위한 forward 기능을 제공하는 객체가 있어요
			//=> RequestDispatcher한테 경로 알려줄 때 contextPath를 빼서 알려줘야한다. => 상대경로를 줘라!
			//절대경로를 주고 싶다면 앞의 contextPath를 빼고 주면된다.
			RequestDispatcher dp = request.getRequestDispatcher("board/board_list.jsp");
			dp.forward(request, response);
			System.out.println("보냄!");
			//이렇게 request와 response를 가지고 위의 경로로 간다!
			//그럼 경로의 jsp파일에서 반복문으로 list의 값을 꺼내서 보여주는 로직을 짜서 보여주면 된다!
			
			break;
			
			
			
			
		
		}
		
	
	}

}










