package book.ctrl;

import java.util.List;

import book.service.BookDTO;
import book.service.BookService;

/*       Controller 역할
 * ==========================
 *    View -> controller 
 *    controller -> view
 * =======================================================================================
 *  view에서 사용자가 입력한 값을 controller에게 전달 후 값을 받아 어떤 서비스를 호출할 지 정하는 역할 *(관문)*
 * =======================================================================================
 */

public class BookController {

	//view에서 전달받은 값을 처리 할 service를 호출하기 위해 객체 생성
	BookService bookservice= new BookService();


	/* Method Name  :  addBook  
	 * ================================= Method Detail ===================================== 
	 * 1. View에서 전달 받은 도서정보를 가지고 도서추가를 하기 위해 처리 할 bookservice.addBook(dto)전달.
	 * 2. View에서 return type을 boolean type으로 설정을 하였기 때문에 boolean type return_value 설정.
	 * 3. Service에서 에러 발생 시 에러를 받기 위해 try-catch문
	 * 4. if문으로 Service에서 반환 값을 토대로 true,false View에게 반환
	 * =====================================================================================
	 */	
	public boolean addBook(BookDTO dto)  {

		// service에서 반환값이 boolean(불린)type 이기때문에 선언.
		boolean return_value = false;

		try {
			// service에서 전달받은 값이 true라면
			if(bookservice.addBook(dto)) {


				return_value =  true;

			}  


		}catch(Exception e) {
			System.out.println(e.getMessage());
		}

		return return_value;
	}

	/* Method Name  :  updateBook  
	 * ================================== Method Detail ======================================
	 * 1. View에서 전달 받은 도서정보를 가지고 도서수정을 하기 위해 처리 할 bookservice.updateBook(dto)전달.
	 * 2. View에서 return type을 boolean type으로 설정을 하였기 때문에 boolean type return_value 설정.
	 * 3. Service에서 에러 발생 시 에러를 받기 위해 try-catch문
	 * 4. if문으로 Service에서 반환 값을 토대로 (true,false) View에게 반환
	 * =======================================================================================
	 */	
	public boolean updateBook(BookDTO dto)  {

		// service에서 반환값이 boolean(불린)type 이기때문에 선언.
		boolean return_value = false;

		try {

			// service에서 전달받은 값이 true라면
			if(bookservice.updateBook(dto)) {

				return_value = true;
			}

		}catch(Exception e){

			System.out.println(e.getMessage());
		}
		return return_value;
	}

	/* Method Name  :  deleteBook  
	 * =================================== Method Detail =======================================
	 * 1. View에서 전달 받은 도서ID(PK)를 가지고 도서삭제를 하기 위해 처리 할 bookservice.deleteBook(bookID)전달.
	 * 2. View에서 return type을 boolean type으로 설정을 하였기 때문에 boolean type return_value 설정.
	 * 3. Service에서 에러 발생 시 에러를 받기 위해 try-catch문
	 * 4. if문으로 Service에서 반환 값을 토대로 (true,false) View에게 반환
	 * =========================================================================================
	 */	
	public boolean deleteBook(int bookID) {

		// service에서 반환값이 boolean(불린)type 이기때문에 선언.
		boolean return_value = false;

		try {
			// service에서 전달받은 값이 true라면
			if(bookservice.deleteBook(bookID)) {


				return_value = true;
			}

		}catch(Exception e) {

			System.out.println(e.getMessage());
		}


		return return_value;
	}

	/* Method Name  :  selectBook  
	 * =================================== Method Detail =======================================
	 * 1. View에서 도서조회를 요청을 함으로써 요청을 처리 할  bookservice.selectBook()전달
	 * 2. 도서정보를 담기위해 List<BookDTO> 선언. 
	 * 3. Service에서 에러 발생 시 에러를 받기 위해 try-catch문
	 * 4. if문으로 Service에서 반환 값은 도서정보가 들어 있기 때문에 bookList 이다 
	 * 5. bookList가 null 아닐 경우 bookList(도서정보) 반환 / null 일경우 null를 View에게 반환
	 * =========================================================================================
	 */	
	public List<BookDTO> selectBook() {

		try {
			//데이터베이스 저장된 회원정보를 가져오기 위해서 List<BookDTO> 선언 
			List<BookDTO> bookList = bookservice.selectBook();

			//반환 받은 값이 null값이 아니라면
			if(bookList != null ) {

				return bookList;	
			}

		}catch(Exception e) {

			System.out.println(e.getMessage());
		}
		return null;

	}

	/* Method Name  :  selectBook1  
	 * =================================== Method Detail =======================================
	 * 1. View에서 도서수정을 요청 후 도서정보를 수정 하기 전에 전달 받은 도서ID가 DB에 일치하는 데이터가 있는지 확인절차.
	 * 2. bookservice.selectBook1(bookID) 전달
	 * 3. View에서 return type을 boolean type으로 설정을 하였기 때문에 boolean type return_value 설정.
	 * 4. Service에서 비즈니스 로직 중 에러 발생 시 에러를 받기 위해 try-catch문
	 * 5. if문으로 Service에서 반환 값을 토대로 (true,false) View에게 반환
	 * =========================================================================================
	 */	
	public boolean selectBook1(int bookID) {

		boolean return_value = false;

		try {

			// service에서 전달받은 값이 true라면
			if(bookservice.selectBook1(bookID)) {

				return_value = true;
			}

		}catch(Exception e) {

			System.out.println(e.getMessage());
		}

		return return_value ;
	}
}
