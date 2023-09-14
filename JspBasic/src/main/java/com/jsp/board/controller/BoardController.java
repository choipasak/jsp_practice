package com.jsp.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.board.service.ContentService;
import com.jsp.board.service.DeleteService;
import com.jsp.board.service.GetListService;
import com.jsp.board.service.IBoardService;
import com.jsp.board.service.ModifyService;
import com.jsp.board.service.RegistService;
import com.jsp.board.service.SearchService;
import com.jsp.board.service.UpdateService;


@WebServlet("*.board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	private IBoardService sv;
	private RequestDispatcher dp;
    
    public BoardController() {
        super();
    }

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getMethod().equals("POST")) {// method="post"일 때
			//그리고 매개값은 대문자로 작성해 줘야 한다! getMethod()가 대문자로 문자열을 리턴하기 때문!
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
			sv = new RegistService(); // -> 등록해주는 객체를 생성해서 일처리를 맡겼음
			sv.execute(request, response);
			
			
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
			sv = new GetListService(); //리스트를 보여주는 객체를 생성해서 일처리를 맡김.
			sv.execute(request, response);
			
			
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
			dp = request.getRequestDispatcher("board/board_list.jsp");
			dp.forward(request, response);
			//이렇게 request와 response를 가지고 위의 경로로 간다!
			//그럼 경로의 jsp파일에서 반복문으로 list의 값을 꺼내서 보여주는 로직을 짜서 보여주면 된다!
			
			break;
			
		case "content":
			
			System.out.println("글 상세보기 요청이 들어옴!");
			sv = new ContentService();//일은 ContentService()가 하는거임.
			sv.execute(request, response);
			
			dp = request.getRequestDispatcher("board/board_content.jsp");
			//ㄴ> switch문이여서 자꾸 변수가 중복됌. -> 코드의 양이 많아짐 -> 컨트롤러가 하는 양이 너무 많아짐. -> 서비스계층 생성(인터페이스)
			//서비스계층 생성 -> 다형성을 적용한 인터페이스를 이용해서 컨트롤러 로직을 다시 작성해보자!
			dp.forward(request, response);
			break;
			
			
			/////////////////////////////////////// 내가 한 버전 /////////////////////////////////////////
			/*
			System.out.println("글 제목을 눌렀음! 내용도 보여조야함!");
			//String idx = request.getParameter("bId");
			int bIdidx = request.getQueryString().indexOf("=");
			String idx = request.getQueryString().substring(bIdidx+1);
			//String cContent = 
			//일단 둘다 문자열ㄹ ㅗ저장했으니까 문자열로 받아주고
			
			//받아준 값을 새로 리스트를 만들엇 ㅓ보내야ㅏ하나 이러면 넘 ㅜ길어지는데
			//int nums = BoardRepository.getInstace().getList().indexOf("content");
			//BoardVO contentlist = BoardRepository.getInstace().getList(nums);
			
			System.out.println(idx);
			
			List<BoardVO> clist = BoardRepository.getInstace().getList();
			String realcontent = clist.get(Integer.parseInt(idx)-1).getContent();
			//get의 인덱스 값은 페이지의 번호로 0부터 시작이 아니기 때문에 -1해준당
			request.setAttribute("showcontent", realcontent);
			
			//인덱스번호 살려서 보내야하니까 디스패쳐사용
			RequestDispatcher dpcontent = request.getRequestDispatcher("board/board_content.jsp");
			dpcontent.forward(request, response);
			System.out.println("content보냄!");
			//////////////////////////////////////////////////////////////////////////////////////////
			*/
			
		case "modify":
			System.out.println("글 수정 페이지로 이동 요청!");
			sv = new ModifyService();
			sv.execute(request, response);
		
			dp = request.getRequestDispatcher("board/board_modify.jsp");
			dp.forward(request, response);
			break;
			
		case "update":
			//새롭게 입력받은 수정값으로 BoardVO 객체를 생성해서 수정을 진행하세요.
			//(기존 리스트에 존재하는 객체를 새로운 객체로 교체)
			//수정이 완료되면 수정된 글의 상세보기 페이지로 응답이 나가야 합니다.
			
			System.out.println("글 수정 요청이 들어옴!");
			sv = new UpdateService();
			sv.execute(request, response);// UpdateService()에서 작성해준 내용을 저장해준다.
			
			response.sendRedirect("/JspBasic/content.board?bId=" + request.getParameter("boardNo"));
			//sendRedirect()사용하는게 맞음. 그래야 content로 넘어가서 통해서 상세글로 넘어가기 때문에
			//그리고 글 번호를 같이 보내줘야햠 -> content에서 글번호를 받아서 상세글을 보여주기 때문에
			//그럼 어떤 번호를 보내야하냐 -> 수정요정을 보낸 그 글의 번호!
			//컨트롤러에서 url을 수정해서 보내준거임
			
			break;
			
		case "delete":
			System.out.println("글 삭제 요청이 들어옴!");
			sv = new DeleteService();
			sv.execute(request, response);
			response.sendRedirect("/JspBasic/list.board");
			//jsp파일로 보내면 아무것도 없음
			//목록을 끌고 가서 보내야함 -> list로 보내야 한다는 말. 왜냐면 list의 케이스가
			//목록을 끌고와서 응답하는 로직이기 때문에!
			break;
			
		case "search":
			System.out.println("작성자로 게시글 검색 요청이 들어옴!");
			sv = new SearchService();
			sv.execute(request, response);
			
			//포워딩
			dp = request.getRequestDispatcher("board/board_list.jsp");
			dp.forward(request, response);
			break;
		}
		
	
	}

}










