package bookMng.bookRental.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Controller;

import bookMng.book.dto.BookDTO;
import bookMng.book.service.BookService;
import bookMng.user.dto.UserDTO;
import bookMng.user.service.UserService;


/* Controller 역할
 * =====================================================
 *    FrontController(Dispatcher-Servlet) -> controller 
 *    controller -> FrontController(Dispatcher-Servlet)
 * =======================================================================================
 *  FrontController(Dispatcher-Servlet)에서 사용자가 입력한 값을
 *   controller에게 전달 후 값을 받아 어떤 서비스를 호출할 지 정하는 역할 *(관문)*
 * =======================================================================================
 */


/* Method Name  :  SelectBookRentalController  
 * ============================================= Method Detail =====================================================
 * 1. SelectBookRental.JSP에 사용자정보,도서정보를 보여줘야 하기 위해 처리 할 us.selectUser(),bs.selectBook() 전달.
 * 2. FrontController(Dispatcher-Servlet)에서 다음페이지로(forward)해야하기 때문에 String type으로 선언  
 * 3. Service에서 에러 발생 시 에러를 받기 위해 try-catch문
 * 4. if문으로 Service에서 반환 값을 토대로 nextPage를 FrontController(Dispatcher-Servlet) 에게 반환
 * =================================================================================================================
 */	
public class SelectBookRentalController implements Controller {

	//FrontController에게 받은 request(요청), response(응답)이 담겨져 있다.
	@Override
	public String rquestHandler(HttpServletRequest request, HttpServletResponse rseponse)
			throws ServletException, IOException {

		//FrontController(Dispatcher-Servlet)에서 전달받은 값을 처리 할 service를 호출하기 위해 객체 생성
		UserService us = new UserService();
		BookService bs = new BookService();


		try {
			//DB에 저장되어 있는 도서정보를 가져오기위해서 List<UserDTO> 선언
			//DB에 저장되어 있는 도서정보를 가져오기위해서 List<BookDTO> 선언
			List<UserDTO> userList = us.selectUser();
			List<BookDTO> bookList = bs.selectBook();


			//us.selectUser()에서 반환 받은 userList를 SelectBookRental.JSP에서 값을 보여주기 위해 객체바인딩
			request.setAttribute("userList", userList);
			//bs.selectBook()에서 반환 받은 userList를 SelectBookRental.JSP에서 값을 보여주기 위해 객체바인딩
			request.setAttribute("bookList", bookList);

		} catch (Exception e) {

			System.out.println(e.getMessage());

		}

		return "/WEB-INF/BookRental/SelectBookRental.jsp";
	}

}
