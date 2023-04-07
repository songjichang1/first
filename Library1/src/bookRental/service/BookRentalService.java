package bookRental.service;

import java.util.List;


/*       Service 역할
 * ==============================
 *  Controller -> Service -> DAO 
 *  DAO -> Service -> Controller
 * =======================================================================================
 *  Controller에서 전달받은 값으로 Service는 비즈니스로직 수행 -> DAO에게 값 전달 
 *  DAO는 DB에서 받아온 데이터를(반환) 값을 Service가 받아서 가공 후 Controller에게 반환
 *  Service는 사용자가 요청한 작업을 처리하는 과정을 하나로 묶은 것.
 * =======================================================================================
 */

public class BookRentalService {

	//Controller에서 전달받은 값을 처리할 DAO를 호출하기 위해 객체 생성
	BookRentalDAO bra = new BookRentalDAO();

	/* Method Name  :  overLapID  
	 * ======================================= Method Detail ============================================ 
	 * 1. Controller에서 전달 받은 도서ID,사용자ID를 가지고 대여를 하기 전 도서가 중복인지 확인하기 위해 bra.overLapID(brd) 전달
	 * 2. Controller에서 return type을 boolean type으로 설정을 하였기 때문에 boolean type return_value 설정.
	 * 3. 비즈니스 로직 중 에러 발생 시 throws Exception로 해당 서비스 호출한 Controller에게 Exception 전달
	 * 4. if문으로 DAO에서 반환 값을 토대로 (true,false) Controller에게 반환
	 * ==================================================================================================
	 */
	public boolean overLapID(int userID, int bookID) throws Exception {

		// Controller에게  boolean(불린)type으로 반환하기 위해 선언.
		boolean return_value =false;

		// bra에서 반환값이 int형이기 때문에 result 선언.
		int result = bra.overLapID(userID,bookID);

		// 전달받은 값이  0이라면 (대여가 가능 하다면)
		if(result == 0) {

			return_value = true;
		}

		return return_value;
	}

	/* Method Name  :  bookRental  
	 * ===================================== Method Detail ========================================= 
	 * 1. Controller에서 전달 받은 도서ID,사용자ID를 가지고 대여를 하기 위해 처리 할 bra.bookRental(brd) 전달.
	 * 2. Controller에서 return type을 boolean type으로 설정을 하였기 때문에 boolean type return_value 설정.
	 * 3. 비즈니스 로직 중 에러 발생 시 throws Exception로 해당 서비스 호출한 Controller에게 Exception 전달
	 * 4. if문으로 DAO에서 반환 값을 토대로 (true,false) Controller에게 반환
	 * =============================================================================================
	 */	
	public boolean bookRental(BookRentalDTO brd) throws Exception {

		// Controller에게  boolean(불린)type으로 반환하기 위해 선언.
		boolean return_value =false;	 

		// bra에서 반환값이 int형이기 때문에 result 선언.
		int result = bra.bookRental(brd);

		//올바르게 DB에 저장되었다면
		if(result > 0) {	

			return_value = true;
		}

		return return_value;
	}

	/* Method Name  :  deleteBookRental  
	 * ======================================== Method Detail ============================================
	 * 1. Controller에서 전달 받은 사용자ID(PK)를 가지고 사용자 삭제를 하기 위해 처리 할 bra.deleteBookRental(userID) 전달.
	 * 2. Controller에서 return type을 boolean type으로 설정을 하였기 때문에 boolean type return_value 설정.
	 * 3. 비즈니스 로직 중 에러 발생 시 throws Exception로 해당 서비스 호출한 Controller에게 Exception 전달
	 * 4. if문으로 DAO에서 반환 값을 토대로 (true,false) Controller에게 반환
	 * ===================================================================================================
	 */	 
	public boolean deleteBookRental(int userID) throws Exception {

		// Controller에게  boolean(불린)type으로 반환하기 위해 선언.
		boolean return_value = false;

		// bra에서 반환값이 int형이기 때문에 result 선언.
		int result = bra.deleteBookRental(userID);

		//올바르게 DB에 저장되었다면
		if(result > 0) {

			return_value = true;
		}


		return return_value;
	}

	/* Method Name  :  selectBookRental  
	 * =================================== Method Detail =======================================
	 * 1. Controller에서 대여조회를 요청을 함으로써 요청을 처리 할  bra.selectBookRental() 전달.
	 * 2. 대여정보를 담기위해 List<BookRentalDTO> 선언. 
	 * 3. 에러 발생 시 throws Exception로 해당 서비스 호출한 Controller에게 Exception 전달
	 * =========================================================================================
	 */	
	public List<BookRentalDTO> selectBookRental() throws Exception {

		//DB에 저장되어 있는 대여정보를 가져오기 위해서 List<BookRentalDTO> 선언
		List<BookRentalDTO> bookrental = bra.selectBookRental();

		return bookrental;


	}

}
