package bookMng.user.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Controller;

import bookMng.user.dto.UserDTO;
import bookMng.user.service.UserService;

/*       Controller 역할
 * =====================================================
 *    FrontController(Dispatcher-Servlet) -> controller 
 *    controller -> FrontController(Dispatcher-Servlet)
 * =======================================================================================
 *  FrontController(Dispatcher-Servlet)에서 사용자가 입력한 값을
 *   controller에게 전달 후 값을 받아 어떤 서비스를 호출할 지 정하는 역할 *(관문)*
 * =======================================================================================
 */


/* Method Name  :  InsertUserController  
 * ==================================== Method Detail ======================================== 
 * 1. InsertUser.JSP에서 전달 받은 사용자정보를 가지고 도서추가를 하기 위해 처리 할 us.insertUser(ud) 전달.
 * 2. FrontController(Dispatcher-Servlet)에서 다음페이지로(forward)해야하기 때문에 String nextPage로 선언  
 * 3. Service에서 에러 발생 시 에러를 받기 위해 try-catch문
 * 4. if문으로 Service에서 반환 값을 토대로 nextPage를 FrontController(Dispatcher-Servlet) 에게 반환
 * ============================================================================================
 */	
public class InsertUserController implements Controller{

	//FrontController에게 받은 request(요청), response(응답)이 담겨져 있다.
	@Override
	public String rquestHandler(HttpServletRequest request, HttpServletResponse rseponse)
			throws ServletException, IOException {
		//사용자의 입력값을 수집하기 위해 객체 생성 		
		UserDTO ud = new UserDTO();
		//FrontController(Dispatcher-Servlet)에서 전달받은 값을 처리 할 service를 호출하기 위해 객체 생성
		UserService us = new UserService();

		/*
		 * insertUser.jsp에서 input 태그에서 name에 적은 이름을 getParameter(); ()안에 동일하게 넣는다.
		 * bd.set()안에 getParmeter에서 얻어온 데이터를 bd에 데이터를 저장한다. 
		 */
		int userID = Integer.parseInt(request.getParameter("userid")) ;
		ud.setUserID(userID);

		String userName = request.getParameter("username");
		ud.setUserName(userName);

		String userSex = request.getParameter("usersex");
		ud.setUserSex(userSex);

		int userAge = Integer.parseInt(request.getParameter("userage")) ;
		ud.setUserAge(userAge);

		String userPhone = request.getParameter("userphone") ;
		ud.setUserPhone(userPhone);
		
		
		
		String nextPage = null;
	
		
		try {
			 //us.insertUser(ud)에게 값을 전달 후 boolean 타입으로 반환 받는다
			boolean return_value = us.addUser(ud);

			// service에서 전달받은 값이 true라면
			if(return_value) {
			
				System.out.println("회원추가 성공!");
				
				nextPage="/WEB-INF/main/Main.jsp";
			}
			
			else {
				System.out.println("회원추가 실패!");
			}

		} catch (Exception e) {

			System.out.println(e.getMessage());
		}

		return nextPage;
	}
}


