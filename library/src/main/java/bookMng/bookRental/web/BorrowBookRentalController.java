package bookMng.bookRental.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Controller;

import bookMng.bookRental.dto.BookRentalDTO;
import bookMng.bookRental.service.BookRentalService;

/*       Controller 역할
 * =====================================================
 *    FrontController(Dispatcher-Servlet) -> controller 
 *    controller -> FrontController(Dispatcher-Servlet)
 * =======================================================================================
 *  FrontController(Dispatcher-Servlet)에서 사용자가 입력한 값을
 *   controller에게 전달 후 값을 받아 어떤 서비스를 호출할 지 정하는 역할 *(관문)*
 * =======================================================================================
 */


/* Method Name  :  BorrowBookRentalController  
 * ============================================= Method Detail =====================================================
 * 1. SelectBookRental.jsp에서 CheckBox(bookID,userID)로 전달 받아 대여를 하기 위해 처리 할 brs.bookRental(BookID,userID) 전달.
 * 2. FrontController(Dispatcher-Servlet)에서 다음페이지로(forward)해야하기 때문에 String nextPage로 선언  
 * 3. Service에서 에러 발생 시 에러를 받기 위해 try-catch문
 * 4. if문으로 Service에서 반환 값을 토대로 nextPage를 FrontController(Dispatcher-Servlet) 에게 반환
 * =================================================================================================================
 */	
public class BorrowBookRentalController implements Controller{
	//FrontController에게 받은 request(요청), response(응답)이 담겨져 있다.
	@Override
	public String rquestHandler(HttpServletRequest request, HttpServletResponse rseponse)
			throws ServletException, IOException {

		//FrontController(Dispatcher-Servlet)에서 전달받은 값을 처리 할 service를 호출하기 위해 객체 생성
		BookRentalService brs = new BookRentalService();

		/*
		 * SelectBookRental.jsp에서 checkBox에서 name에 적은 이름을 getParameter(); ()안에 동일하게 넣는다. 
		 */
		int BookID = Integer.parseInt(request.getParameter("bookID")) ;
		int userID = Integer.parseInt(request.getParameter("userID"));
		

		String nextPage = null;


		try {
			 //반환값이 true라면
			if(brs.bookRental(BookID,userID)) {

				System.out.println("대여성공!");
				
				nextPage="/WEB-INF/main/Main.jsp";
			
			}  
			
			//false라면
			else {
			
				System.out.println("대여 실패!");
			
			}
		} catch (Exception e) {

			System.out.println(e.getMessage());
		}

		return nextPage;


	}

}

