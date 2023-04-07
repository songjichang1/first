package bookMng.book.service;

import java.util.List;
import bookMng.book.dto.BookDTO;
import bookMng.bookRental.service.BookRentalDAO;

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

public class BookService {

	//Controller에서 전달받은 값을 처리할 DAO를 호출하기 위해 객체 생성
	BookDAO ba = new BookDAO(); 

	/* Method Name  :  addBook  
	 * ======================================= Method Detail ============================================ 
	 * 1. Controller에서 전달 받은 도서정보를 가지고 도서추가를 하기 위해 처리 할 ba.addbook(dto)전달.
	 * 2. Controller에서 return type을 boolean type으로 설정을 하였기 때문에 boolean type return_value 설정.
	 * 3. 비즈니스 로직 중 에러 발생 시 throws Exception로 해당 서비스 호출한 Controller에게 Exception 전달
	 * 4. if문으로 DAO에서 반환 값을 토대로 (true,false) Controller에게 반환
	 * ==================================================================================================
	 */
	public boolean addBook(BookDTO dto) throws Exception {	 

		// Controller에게  boolean(불린)type으로 반환하기 위해 선언.
		boolean return_value = false;

		//DB에서 (int) 반환값을 받기 위해 int result값 선언. 
		int result = ba.addBook(dto);

		//올바르게 DB에 저장되었다면
		if(result > 0) {	

			return_value = true;
		}

		return return_value ;

	}

	/* Method Name  :  updateBook  
	 * ======================================= Method Detail ===========================================
	 * 1. Controller에서 전달 받은 도서정보를 가지고 도서수정을 하기 위해 처리 할 ba.updateBook(dto)전달.
	 * 2. Controller에서 return type을 boolean type으로 설정을 하였기 때문에 boolean type return_value 설정.
	 * 3. 비즈니스 로직 중 에러 발생 시 throws Exception로 해당 서비스 호출한 Controller에게 Exception 전달
	 * 4. if문으로 DAO에서 반환 값을 토대로 (true,false) Controller에게 반환
	 * =================================================================================================
	 */	
	public boolean updateBook(BookDTO dto) throws Exception {

		// Controller에게  boolean(불린)type으로 반환하기 위해 선언.
		boolean return_valse=false;

		//DB에서 (int) 반환값을 받기 위해 int result값 선언. 
		int result = ba.updateBook(dto);

		//올바르게 DB에 저장되었다면
		if(result > 0) {

			return_valse = true;
		}

		return return_valse;
	}

	/* Method Name  :  deleteBook  
	 * =================================== Method Detail =======================================
	 * 1. Controller에서 전달 받은 도서ID(PK)를 가지고 도서삭제를 하기 위해 처리 할 ba.delete(bookID) 전달.
	 * 2. Controller에서 return type을 boolean type으로 설정을 하였기 때문에 boolean type return_value 설정.
	 * 3. 비즈니스 로직 중 에러 발생 시 throws Exception로 해당 서비스 호출한 Controller에게 Exception 전달
	 * 4. if문으로 DAO에서 반환 값을 토대로 (true,false) Controller에게 반환
	 * =========================================================================================
	 */	
	public boolean deleteBook(int bookID) throws Exception{

		// Controller에게  boolean(불린)type으로 반환하기 위해 선언.
		boolean return_value = false;

		//책이 삭제가 되었을 때 그 도서를 대여중인 회원의 대여정보도 삭제
		BookRentalDAO brd = new BookRentalDAO(); 

		//DB에서 (int) 반환값을 받기 위해 int result값 선언. 
		int result = ba.deleteBook(bookID);


		//올바르게 DB에서 처리(삭제)가 되었다면
		if(result > 0) {

			return_value = true;

			//도서삭제가 되었다면 삭제된 도서를 대여중인 회원도 삭제
			brd.deleteBookRental1(bookID);
		}

		return return_value;
	}

	/* Method Name  :  selectBook1  
	 * ========================================== Method Detail ==============================================
	 * 1. Controller에서 특정 도서정보를 요청 시. 처리 할 ba.selectBook1(bookID) 전달.
	 * 2. Controller에서 return type을 BookDTO dto 으로 설정을 하였기 때문에 BookDTO dto 설정.
	 * 3. 비즈니스 로직 중 에러 발생 시 throws Exception로 해당 서비스 호출한 Controller에게 Exception 전달
	 * 4. if문으로 DAO에서 반환 값이 BookDTO dto 이라서 null이 아닐 때 dto 값을 반환 / null일 때 null을  Controller에게 반환
	 * =======================================================================================================
	 */	
	public BookDTO selectBook1(int bookID) throws Exception{

		// 특정 회원의 상세정보를 담기위해서 Book DTO 선언 
		BookDTO dto = ba.selectBook1(bookID);

		// DAO에서 전달받은 값이 null값이 아니라면
		if(dto!=null) {

			return  dto;
		}

		else {

			return null;

		}
	}

	/* Method Name  :  selectBook  
	 * =================================== Method Detail =======================================
	 * 1. Controller에서 도서조회를 요청을 함으로써 요청을 처리 할 ba.select() 전달.
	 * 2. 도서정보를 담기위해 List<BookDTO> 선언. 
	 * 3. 에러 발생 시 throws Exception로 해당 서비스 호출한 Controller에게 Exception 전달
	 * =========================================================================================
	 */	
	public List<BookDTO> selectBook() throws Exception {
		//DB에 저장되어 있는 도서정보를 담기 위해서 List<BookDTO> userList 선언
		List<BookDTO> bookList = ba.selectBook();

		return bookList;
	}

}
