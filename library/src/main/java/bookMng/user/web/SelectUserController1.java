package bookMng.user.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Controller;


import bookMng.user.dto.UserDTO;
import bookMng.user.service.UserService;


/*       Controller 역할
 * ======================================================
 *    FrontController(Dispatcher-Servlet) -> controller 
 *    controller -> FrontController(Dispatcher-Servlet)
 * =======================================================================================
 *  FrontController(Dispatcher-Servlet)에서 사용자가 입력한 값을
 *   controller에게 전달 후 값을 받아 어떤 서비스를 호출할 지 정하는 역할 *(관문)*
 * =======================================================================================
 */

/* Method Name  : SelectUserController1 
 * ========================================= Method Detail ==============================================
 * 1. SelectUser.JSP에서 회원목록 테이블에 사용자ID를 클릭하면 특정 사용자의 정보가 나오게끔 처리 할 us.selectUser1(userID) 전달.
 * 2. FrontController(Dispatcher-Servlet)에서 다음페이지로(forward)해야하기 때문에 String type으로 선언
 * 3. Service에서 에러 발생 시 에러를 받기 위해 try-catch문
 * 4. if문으로 Service에서 반환 값을 토대로 nextPage를 FrontController(Dispatcher-Servlet) 에게 반환
 * ======================================================================================================
 */	
public class SelectUserController1 implements Controller{
	//FrontController에게 받은 request(요청), response(응답)이 담겨져 있다.
	@Override
	public String rquestHandler(HttpServletRequest request, HttpServletResponse rseponse)
			throws ServletException, IOException {
	
		/*
		 * SelectUser.jsp에서 name에 적은 이름을 getParameter(); ()안에 동일하게 넣는다. 
		 */
		int userID = Integer.parseInt(request.getParameter("userID"));

		try {
			//FrontController(Dispatcher-Servlet)에서 전달받은 값을 처리 할 service를 호출하기 위해 객체 생성
			UserService us = new UserService();
			// bookID의 상세정보를 담기위해 bookDTO dto 선언  ex) bookID=1 <  bookID=1 에 해당하는 정보 !
			UserDTO dto = us.selectUser1(userID);

			// 전달받은 정보를 UpdateUserPage.jsp에 보여주기 위해 객체 바인딩
			request.setAttribute("dto", dto);
			
		} catch (Exception e) {

          System.out.println(e.getMessage());
		}

		return "/WEB-INF/user/UpdateUserPage.jsp";
	}
}


