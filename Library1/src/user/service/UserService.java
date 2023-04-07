package user.service;

import java.util.List;
import bookRental.service.BookRentalDAO;

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

public class UserService {

	//Controller에서 전달받은 값을 처리할 DAO를 호출하기 위해 객체 생성
	UserDAO uda = new UserDAO();

	/* Method Name  :  addUser  
	 * ======================================= Method Detail ============================================ 
	 * 1. Controller에서 전달 받은 사용자정보를 가지고 사용자추가를 하기 위해 처리 할 uda.addUser(udt) 전달.
	 * 2. Controller에서 return type을 boolean type으로 설정을 하였기 때문에 boolean type return_value 설정.
	 * 3. 비즈니스 로직 중 에러 발생 시 throws Exception로 해당 서비스 호출한 Controller에게 Exception 전달
	 * 4. if문으로 DAO에서 반환 값을 토대로 (true,false) Controller에게 반환
	 * ==================================================================================================
	 */
	public boolean addUser(UserDTO udt) throws Exception {

		// Controller에게  boolean(불린)type으로 반환하기 위해 선언.
		boolean return_value = false;	 

		//DB에서 (int) 반환값을 받기 위해 int result값 선언. 
		int result = uda.addUser(udt);

		//올바르게 DB에 저장되었다면
		if(result > 0) {	

			return_value = true;
		}

		return return_value;
	}

	/* Method Name  :  updateUser  
	 * ======================================= Method Detail ===========================================
	 * 1. Controller에서 전달 받은 사용자 정보를 가지고 사용자정보 수정을 하기 위해 처리 할 uda.updateUser(udt) 전달.
	 * 2. Controller에서 return type을 boolean type으로 설정을 하였기 때문에 boolean type return_value 설정.
	 * 3. 비즈니스 로직 중 에러 발생 시 throws Exception로 해당 서비스 호출한 Controller에게 Exception 전달
	 * 4. if문으로 DAO에서 반환 값을 토대로 (true,false) Controller에게 반환
	 * =================================================================================================
	 */	
	public boolean updateUser(UserDTO udt) throws Exception {

		// Controller에게  boolean(불린)type으로 반환하기 위해 선언.
		boolean return_value = false;

		//DB에서 (int) 반환값을 받기 위해 int result값 선언.
		int result = uda.updateUser(udt);

		//올바르게 DB에 저장되었다면
		if(result > 0) {

			return_value = true;
		}


		return return_value;
	}

	/* Method Name  :  deleteUser  
	 * =================================== Method Detail =======================================
	 * 1. Controller에서 전달 받은 사용자ID(PK)를 가지고 사용자 삭제를 하기 위해 처리 할 uda.deleteUser(userID) 전달.
	 * 2. Controller에서 return type을 boolean type으로 설정을 하였기 때문에 boolean type return_value 설정.
	 * 3. 비즈니스 로직 중 에러 발생 시 throws Exception로 해당 서비스 호출한 Controller에게 Exception 전달
	 * 4. if문으로 DAO에서 반환 값을 토대로 (true,false) Controller에게 반환
	 * =========================================================================================
	 */	
	public boolean deleteUser(int userID) throws Exception {

		// Controller에게  boolean(불린)type으로 반환하기 위해 선언.
		boolean return_value = false;


		BookRentalDAO bra = new BookRentalDAO();

		//DB에서 (int) 반환값을 받기 위해 int result값 선언. 
		int result = uda.deleteUser(userID);

		//올바르게 DB에서 처리가 되었다면
		if(result > 0) {

			//유저삭제가 되었다면 대여중인 회원도 삭제 
			bra.deleteBookRental2(userID);


			return_value = true;

		}
		return return_value;
	}

	/* Method Name  :  selectUser  
	 * =================================== Method Detail =======================================
	 * 1. Controller에서 도서조회를 요청을 함으로써 요청을 처리 할 uda.selectUser() 전달
	 * 2. 사용자 정보를 담기위해 List<UserDTO> 선언. 
	 * 3. 에러 발생 시 throws Exception로 해당 서비스 호출한 Controller에게 Exception 전달
	 * =========================================================================================
	 */	
	public List<UserDTO> selectUser() throws Exception {

		//DB에 저장되어있는 도서정보를 가져오기 위해 List형 선언 
		List<UserDTO> userList = uda.selectUser();

		return userList;
	}

	/* Method Name  :  selectUser1  
	 * ========================================== Method Detail ==============================================
	 * 1. Controller에서 사용자 정보 수정을 요청 후 사용자 정보를 수정 하기 전에 전달 받은 사용자ID가 DB에 일치하는 데이터가 있는지 확인절차.
	 * 2. uda.selectUser1(userID) 전달
	 * 3. Controller에서 return type을 boolean type으로 설정을 하였기 때문에 boolean type return_value 설정.
	 * 4. 비즈니스 로직 중 에러 발생 시 에러를 받기 위해 try-catch문
	 * 5. if문으로 DAO에서 반환 값을 토대로 (true,false) Controller에게 반환
	 * =======================================================================================================
	 */	
	public boolean selectUser1(int userID) throws Exception {

		// Controller에게  boolean(불린)type으로 반환하기 위해 선언.
		boolean return_value = false;

		// Controller로 받은 userID가 데이터베이스에 일치한 데이터가 있는지 확인 하기위해 result 선언  
		int result =  uda.selectUser1(userID);

		// 데이터베이스에 일치하는 정보가 있다면
		if(result > 0) {


			return_value = true;

		}

		return return_value;

	}

}
