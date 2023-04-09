package bookMng.user.web;

import java.io.IOException;

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


/* Method Name  :  UpdateUserController  
 * ==================================== Method Detail =========================================
 * 1. UpdateUserPage.JSP에서 특정 도서의 정보를 수정하기 위해 전달 받은 값을 처리 할 us.updateUser(ud 전달.
 * 2. FrontController(Dispatcher-Servlet)에서 다음페이지로(forward)해야하기 때문에 String nextPage로 선언
 * 3. Service에서 에러 발생 시 에러를 받기 위해 try-catch문
 * 4. if문으로 Service에서 반환 값을 토대로 nextPage를 FrontController(Dispatcher-Servlet) 에게 반환
 * ============================================================================================
 */	
public class UpdateUserController implements Controller {
	//FrontController에게 받은 request(요청), response(응답)이 담겨져 있다.
	@Override
	public String rquestHandler(HttpServletRequest request, HttpServletResponse rseponse)
			throws ServletException, IOException {
		// BookService에게 값을 전달하기 위해 UserDTO 선언
		UserDTO ud = new UserDTO();
		//FrontController(Dispatcher-Servlet)에서 전달받은 값을 처리 할 service를 호출하기 위해 객체 생성
		UserService  us = new UserService();
		
		/*
		 * UpdateUserPage.JSP에서 input 태그에서 name에 적은 이름을 getParameter(); ()안에 동일하게 넣는다.
		 * bd.set()안에 getParmeter에서 얻어온 데이터를 bd에 데이터를 저장한다. 
		 */
		int userID = Integer.parseInt(request.getParameter("userid")) ;
		ud.setUserID(userID);
		
		String userName = request.getParameter("username");
        ud.setUserName(userName);
		
        int userAge = Integer.parseInt(request.getParameter("userage"));
        ud.setUserAge(userAge);
		
        String userSex = request.getParameter("usersex");
        ud.setUserSex(userSex);

	    String userPhone = request.getParameter("userphone");
	    ud.setUserPhone(userPhone);
		
	    String nextPage = null;

		try {

			// 반환값이 true라면
			if(us.updateUser(ud)) {
				
				System.out.println("회원수정 성공!");

				nextPage= "/WEB-INF/main/Main.jsp";
			}
			else {
				System.out.println("회원수정 실패!");
			}

		} catch (Exception e) {

			System.out.println(e.getMessage());
		}

		return nextPage;

	}
}


