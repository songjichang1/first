package bookRental.ctrl;

import java.util.List;

import bookRental.service.BookRentalDTO;
import bookRental.service.BookRentalService;

/*       Controller 역할
 * ==========================
 *    View -> controller 
 *    controller -> view
 * =======================================================================================
 *  view에서 사용자가 입력한 값을 controller에게 전달 후 값을 받아 어떤 서비스를 호출할 지 정하는 역할 *(관문)*
 * =======================================================================================
 */

public class BookRentalController {


	//view에서 전달받은 값을 처리할 service를 호출하기 위해 객체 생성
	BookRentalService bookrentalservice = new BookRentalService();

	/* Method Name  :  overLapID  
	 * ============================================ Method Detail ================================================
	 * 1. View에서 전달 받은 도서ID,사용자ID를 가지고 대여를 하기 전 도서가 중복인지 확인하기 위해 bookrentalservice.overLapID(brd)전달
	 * 2. View에서 return type을 boolean type으로 설정을 하였기 때문에 boolean type return_value 설정.
	 * 3. Service에서 에러 발생 시 에러를 받기 위해 try-catch문
	 * 4. if문으로 Service에서 반환 값을 토대로 true,false View에게 반환
	 * ===========================================================================================================
	 */	
	public boolean overLapID(int userID, int bookID)  {

		// service에서 반환값이 boolean(불린)type 이기때문에 선언.
		boolean return_value = false;

		try {
			// service에서 전달받은 값이 true라면
			if(bookrentalservice.overLapID(userID,bookID)) {

				return_value = true;
			}

		}catch(Exception e) {
			System.out.println(e.getMessage());
		}

		return return_value;
	}

	/* Method Name  :  bookRental  
	 * ===================================== Method Detail ========================================= 
	 * 1. View에서 전달 받은 도서ID,사용자ID를 가지고 대여를 하기 위해 처리 할 bookrentalservice.bookRental(brd)전달.
	 * 2. View에서 return type을 boolean type으로 설정을 하였기 때문에 boolean type return_value 설정.
	 * 3. Service에서 에러 발생 시 에러를 받기 위해 try-catch문
	 * 4. if문으로 Service에서 반환 값을 토대로 true,false View에게 반환
	 * =============================================================================================
	 */	
	public boolean bookRental(BookRentalDTO brd)  {

		// service에서 반환값이 boolean(불린)type 이기때문에 선언.
		boolean return_value = false;

		try {
			// service에서 전달받은 값이 true라면  
			if(bookrentalservice.bookRental(brd)) {

				return_value = true;
			}  
		}catch(Exception e) {

			System.out.println(e.getMessage());
		}

		return return_value;
	}

	/* Method Name  :  deleteBookRental  
	 * ===================================== Method Detail =========================================
	 * 1. View에서 전달 받은 사용자ID(PK)를 가지고 사용자 삭제를 하기 위해 처리 할 userservice.deleteUser(userID)전달.
	 * 2. View에서 return type을 boolean type으로 설정을 하였기 때문에 boolean type return_value 설정.
	 * 3. Service에서 에러 발생 시 에러를 받기 위해 try-catch문
	 * 4. if문으로 Service에서 반환 값을 토대로 (true,false) View에게 반환
	 * =============================================================================================
	 */	 
	public boolean deleteBookRental(int userID)  { //회원 탈퇴

		// service에서 반환값이 boolean(불린)type 이기때문에 선언.
		boolean return_value = false;

		try {
			// service에서 전달받은 값이 true라면
			if(bookrentalservice.deleteBookRental(userID)) {
				return_value = true; 
			}

		}catch(Exception e) {

			System.out.println(e.getMessage());
		}


		return return_value;
	}

	/* Method Name  :  selectBookRental  
	 * =================================== Method Detail =======================================
	 * 1. View에서 대여목록을 요청을 함으로써 요청을 처리 할 bookrentalservice.selectBookRental()전달
	 * 2. 대여정보를 담기위해 List<BookRentalDTO> 선언. 
	 * 3. Service에서 에러 발생 시 에러를 받기 위해 try-catch문
	 * 4. if문으로 Service에서 반환 값은 대여정보가 들어 있기 때문에 bookRentalList 이다 
	 * 5. bookRentalList가 null 아닐 경우bookRentalList(대여정보) 반환 / null 일경우 null를 View에게 반환
	 * =========================================================================================
	 */	
	public List<BookRentalDTO> selectBookRental() { //회원 조회

		try {

			// 데이터베이스에 저장되어 있는 대여정보를 담기위해 List<BookRentalDTO> bookRental 선언
			List<BookRentalDTO> bookRentalList = bookrentalservice.selectBookRental();

			//반환 받은 값이 null값이 아니라면
			if(bookRentalList != null) {

				return bookRentalList;
			}

		}catch(Exception e) {

			System.out.println(e.getMessage());
		}


		return null;
	}

}
