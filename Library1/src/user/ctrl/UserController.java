package user.ctrl;

import java.util.List;

import user.service.UserDTO;
import user.service.UserService;

/*       Controller 역할
 * ==========================
 *    View -> controller 
 *    controller -> view
 * =======================================================================================
 *  view에서 사용자가 입력한 값을 controller에게 전달 후 값을 받아 어떤 서비스를 호출할 지 정하는 역할 *(관문)*
 * =======================================================================================
 */


public class UserController {

	//view에서 전달받은 값을 처리할 service를 호출하기 위해 객체 생성
	UserService userservice = new UserService();

	/* Method Name  :  addUser  
	 * ================================= Method Detail ===================================== 
	 * 1. View에서 전달 받은 사용자정보를 가지고 사용자추가를 하기 위해 처리 할 userservice.addUser(dto)전달.
	 * 2. View에서 return type을 boolean type으로 설정을 하였기 때문에 boolean type return_value 설정.
	 * 3. Service에서 에러 발생 시 에러를 받기 위해 try-catch문
	 * 4. if문으로 Service에서 반환 값을 토대로 true,false View에게 반환
	 * =====================================================================================
	 */	
	public boolean addUser(UserDTO dto)  {  

		// service에서 반환값이 boolean(불린)type 이기때문에 선언.
		boolean return_value = false;

		try {
			// service에서 전달받은 값이 true라면
			if(userservice.addUser(dto)) {

				return_value = true;
			}

		}catch(Exception e) {
			System.out.println(e.getMessage());
		}

		return return_value;
	}

	/* Method Name  :  updateUser  
	 * =================================== Method Detail =======================================
	 * 1. View에서 전달 받은 사용자정보를 가지고 사용자정보 수정을 하기 위해 처리 할 userservice.updateUser(udt)전달.
	 * 2. View에서 return type을 boolean type으로 설정을 하였기 때문에 boolean type return_value 설정.
	 * 3. Service에서 에러 발생 시 에러를 받기 위해 try-catch문
	 * 4. if문으로 Service에서 반환 값을 토대로 (true,false) View에게 반환
	 * =========================================================================================
	 */	
	public boolean updateUser(UserDTO udt)  { 

		// service에서 반환값이 boolean(불린)type 이기때문에 선언.
		boolean return_value = false;

		try {

			// service에서 전달받은 값이 true라면
			if(userservice.updateUser(udt)) {

				return_value = true;
			}


		}catch(Exception e) {
			System.out.println(e.getMessage());
		}

		return return_value ;

	}

	/* Method Name  :  deleteUser  
	 * ===================================== Method Detail =========================================
	 * 1. View에서 전달 받은 사용자ID(PK)를 가지고 사용자 삭제를 하기 위해 처리 할 userservice.deleteUser(userID)전달.
	 * 2. View에서 return type을 boolean type으로 설정을 하였기 때문에 boolean type return_value 설정.
	 * 3. Service에서 에러 발생 시 에러를 받기 위해 try-catch문
	 * 4. if문으로 Service에서 반환 값을 토대로 (true,false) View에게 반환
	 * =============================================================================================
	 */	
	public boolean deleteUser(int userID)  { 

		// service에서 반환값이 boolean(불린)type 이기때문에 선언.
		boolean return_value = false;

		try {
			// service에서 전달받은 값이 true라면
			if(userservice.deleteUser(userID)) {

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
	 * 2. 도서정보를 담기위해 List<UserDTO> 선언. 
	 * 3. Service에서 에러 발생 시 에러를 받기 위해 try-catch문
	 * 4. if문으로 Service에서 반환 값은 사용자의 정보가 들어 있기 때문에 userList 이다 
	 * 5. userList가 null 아닐 경우 userList(사용자정보) 반환 / null 일경우 null를 View에게 반환
	 * =========================================================================================
	 */	
	public List<UserDTO> selectUser() { 

		try {
			//데이터베이스 저장된 회원정보를 가져오기 위해서 List<UserDTO> 선언 
			List<UserDTO> userList = userservice.selectUser() ;
			//반환 받은 값이 null값이 아니라면
			if(userList != null) {

				return userList;
			}

		}catch(Exception e) {

			System.out.println(e.getMessage());
		}


		return null;
	}

	/* Method Name  :  selectUser1  
	 * ===================================== Method Detail =========================================
	 * 1. View에서 사용자 정보 수정을 요청 후 사용자 정보를 수정 하기 전에 전달 받은 사용자ID가 DB에 일치하는 데이터가 있는지 확인절차. 
	 * 2. userservice.selectUser1(userID)
	 * 3. View에서 return type을 boolean type으로 설정을 하였기 때문에 boolean type return_value 설정.
	 * 4. Service에서 에러 발생 시 에러를 받기 위해 try-catch문
	 * 5. if문으로 Service에서 반환 값을 토대로 (true,false) View에게 반환
	 * =============================================================================================
	 */	
	public boolean selectUser1(int userID) {

		// service에서 반환값이 boolean(불린)type 이기때문에 선언.
		boolean return_value = false;

		try {

			// service에서 전달받은 값이 true라면
			if(userservice.selectUser1(userID)) {

				return_value = true;
			}

		}catch(Exception e) {

			System.out.println(e.getMessage());
		}

		return return_value ;
	}


}
