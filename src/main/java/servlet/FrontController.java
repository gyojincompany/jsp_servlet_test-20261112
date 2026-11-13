package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public FrontController() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uri = request.getRequestURI(); //클라이언트가 요청한 uri
		System.out.println(uri);
		//jsp_webservlet-20261112/loginOk.do
		
		String context = request.getContextPath();
		System.out.println(context);
		
		String command = uri.substring(context.length());
		System.out.println(command);
		
		HttpSession session = null;
		
		if(command.equals("/loginOk.do")) {
			//로그인 확인 하는 일 진행
			request.setCharacterEncoding("utf-8");
			String mid = request.getParameter("mid");
			String mpw = request.getParameter("mpw");
			
			if(mid.equals("tiger") && mpw.equals("12345")) { //참->로그인 성공
				session = request.getSession(); //세션 초기화
				session.setAttribute("loginId", mid); //로그인 성공 처리
				request.setAttribute("mid", mid);
				
				//request 객체 싣기 후 해당 페이지로 보내기 
				request.getRequestDispatcher("loginSuccess.jsp").forward(request, response);
				
			} else { //로그인 실패
				request.setAttribute("mid", mid);
				request.getRequestDispatcher("loginFail.jsp").forward(request, response);
			}
			
		} else if(command.equals("/join.do")) {
			//회원가입 하는 일 진행
		}
		
		
	}

}
