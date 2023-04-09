package bookMng.bookRental.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Controller;

import bookMng.bookRental.dto.BookRentalDTO;
import bookMng.bookRental.service.BookRentalService;

/* Controller 역할
 * =====================================================
 *    FrontController(Dispatcher-Servlet) -> controller 
 *    controller -> FrontController(Dispatcher-Servlet)
 * =======================================================================================
 *  FrontController(Dispatcher-Servlet)에서 사용자가 입력한 값을
 *   controller에게 전달 후 값을 받아 어떤 서비스를 호출할 지 정하는 역할 *(관문)*
 * =======================================================================================
 */


/* Method Name  :  SelectBookRentalController1  
 * ============================================= Method Detail =====================================================
 * 1. BorrowBookRental.JSP에 대여정보를 보여줘야 하기 위해 처리 할 brs.selectBookRental() 전달.
 * 2. FrontController(Dispatcher-Servlet)에서 다음페이지로(forward)해야하기 때문에 String type으로 선언  
 * 3. Service에서 에러 발생 시 에러를 받기 위해 try-catch문
 * 4. if문으로 Service에서 반환 값을 토대로 nextPage를 FrontController(Dispatcher-Servlet) 에게 반환
 * =================================================================================================================
 */	
public class SelectBookRentalController1 implements Controller {

	//FrontController에게 받은 request(요청), response(응답)이 담겨져 있다.
	@Override
	public String rquestHandler(HttpServletRequest request, HttpServletResponse rseponse)
			throws ServletException, IOException {

		//FrontController(Dispatcher-Servlet)에서 전달받은 값을 처리 할 service를 호출하기 위해 객체 생성
		BookRentalService brs= new BookRentalService();
		
		
		try {

			//DB에 저장되어 있는 도서정보를 가져오기위해서 List<BookRentalDTO> 선언
			List<BookRentalDTO> bookrental = brs.selectBookRental();

			//brs.selectBookRental()에서 반환 받은 userList를 BorrowBookRental.JSP에서 값을 보여주기 위해 객체바인딩
			request.setAttribute("bookrental", bookrental);

		}catch(Exception e) {

			System.out.println(e.getMessage());
		}

		return "/WEB-INF/BookRental/BorrowBookRental.jsp";
	}

}
