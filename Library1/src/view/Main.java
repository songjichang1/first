package view;

import java.util.List;
import java.util.Scanner;

import book.ctrl.BookController;
import book.service.BookDTO;
import bookRental.ctrl.BookRentalController;
import bookRental.service.BookRentalDTO;
import com.check.Check;
import user.ctrl.UserController;
import user.service.UserDTO;


/*
 *  작 성 자       :   송 지 창
 *  프 로 젝 트 명  : 도서관 프로그램 
 *  --------------------------------------------    
 *    프로그램 실행자가 도서매니저다                 
 *    도서관에 회원이 있다                     
 *    회원은 도서를 책을 n개 대여할 수 있다 
 *    회원은 동일한 책을 1개 이상 대여하지 못한다 (중복x)
 *  --------------------------------------------
 *  
 * View -> Controller 
 * Controller -> View
 */


public class Main {


	public static void main(String[] args) {

		// 사용자의 입력값에 대한 각 controller에게 요청하기 위한 객체 생성
		BookController bookController = new BookController();
		UserController userController = new UserController();
		BookRentalController bookRentalController = new BookRentalController();


		// 사용자 입력값을 저장하기 위한 각 클래스 객체 생성
		BookDTO bdt =new BookDTO();
		UserDTO udt =new UserDTO();
		BookRentalDTO brd= new BookRentalDTO();

		// 입력값을 체크하는 객체 생성 
		Check check = new Check(); 

		// 사용자가 입력할 수 있게 Scanner의 객체를 생성.
		Scanner scanner = new Scanner(System.in);

		try {

			while(true) {

				System.out.println();
				System.out.println("             안녕하세요 도서관입니다.             ");
				System.out.println("============================================");
				System.out.println("  1.도서관리 2.사용자관리 3.대여관리 4.프로그램 종료    ");
				System.out.println("============================================");
				System.out.println("원하시는 번호 입력해주세요 :");
				//사용자가 원하는 서비스를 텍스트로 입력하였을 때 사전 검사.
				int choice1=check.checkInt(scanner.nextInt());

				//사용자가 도서관리의 서비스를 원했을 때
				if(choice1==1) {
					System.out.println("============================================");
					System.out.println("1.도서추가 2.도서수정 3.도서삭제 4.도서조회 5.프로그램종료");
					System.out.println("============================================");
					//사용자가 원하는 서비스를 텍스트로 입력하였을 때 사전 검사.
					int choice = check.checkInt(scanner.nextInt());

					//사용자가 입력한 텍스트에 따라 switch문 돌기.
					switch(choice) {
					case 1:

						System.out.println("도서 아이디를 입력하세요 :");
						// 사용자가 입력한 bookID를 BookDTO에 담기전에 check에 값 넘기기
						int bookID =check.checkInt(scanner.nextInt()); 
						//올바르게 입력되었으면 DTO에 값을 저장
						bdt.setBookID(bookID);


						scanner.nextLine();

						System.out.println("도서명을 입력하세요 :");
						// 사용자가 입력한 도서명을 BookDTO에 담기 전에 check에 값 넘기기
						String bookTitle =check.checkString(scanner.nextLine());
						//올바르게 입력되었으면 DTO에 값을 저장
						bdt.setBookTitle(bookTitle);

						System.out.println("도서저자를 입력하세요 :");
						// 사용자가 입력한 도서저자를 BookDTO에 담기 전에 check에 값 넘기기
						String bookAuthor = check.checkString(scanner.nextLine());
						//올바르게 입력되었으면 DTO에 값을 저장
						bdt.setBookAuthor(bookAuthor);

						System.out.println("도서가격을 입력하세요 :");
						// 사용자가 입력한 도서가격을 BookDTO에 담기 전에 check에 값 넘기기
						int bookPrice = check.checkInt(scanner.nextInt());
						// 올바르게 입력되었으면 DTO에 값을 저장
						bdt.setBookPrice(bookPrice);

						//사용자가 입력한 값을 데이터베이스에 저장이 되었는지 , 저장이 안되었는지 확인하기 위해 boolean Type 설정
						boolean Flag= bookController.addBook(bdt);

						// 참이라면.(데이터가 데이터베이스에 올바르게 저장이 되었다면)
						if(Flag) {
							System.out.println("저장 성공 !");
						}
						// 그렇지 아니하면
						else {
							System.out.println("저장 실패 !");
						}
						break;

					case 2:
						System.out.println("도서번호를 입력하세요 :");

						// 사용자가 입력한 도서아이디을 BookDTO에 담기 전에 check에 값 넘기기
						bookID = check.checkInt(scanner.nextInt());
						//수정하기 앞서 도서아이디가 데이터베이스에 존재 여부 확인

						boolean Falg = bookController.selectBook1(bookID);

						//사용자 입력한 도서명이 데이터베이스에 있다면 
						if(Falg) {

							bdt.setBookID(bookID);
							System.out.println("수정하고자 하는 도서명을 입력하세요 :");


							scanner.nextLine();
							// 사용자가 입력한 도서명을 BookDTO에 담기 전에 check에 값 넘기기
							bookTitle =check.checkString(scanner.nextLine());
							//올바르게 입력이 되었다면 DTO에 값 저장
							bdt.setBookTitle(bookTitle);

							System.out.println("수정하고자 하는 도서저자을 입력하세요 :");
							// 사용자가 입력한 도서저자를 BookDTO에 담기 전에 check에 값 넘기기
							bookAuthor=check.checkString(scanner.nextLine());
							// 올바르게 입력이 되었다면 DTO에 값 저장
							bdt.setBookAuthor(bookAuthor);

							System.out.println("수정하고자 하는 도서가격을 입력하세요 :");
							// 사용자가 입력한 도서가격을 BookDTO에 담기 전에 check에 값 넘기기
							bookPrice=check.checkInt(scanner.nextInt());
							// 올바르게 입력이 되었다면 DTO에 값 저장
							bdt.setBookPrice(bookPrice);

							// 데이터베이스에 저장된 도서정보가 올바르게 수정이 되었는지 확인하기 위해 boolean Type 설정
							boolean Flag1 = bookController.updateBook(bdt);

							// 수정이 되었다면
							if(Flag1) {
								System.out.println("수정 성공 !");
							}
							//수정이 안되었다면
							else {

								System.out.println("수정 실패 !");
							}

							//데이터베이스에 도서아이디가 없다면
						}else {
							System.out.println("등록된 도서아이디가 없습니다 !");
						}


						break;

					case 3:

						// 도서번호를 기억하지 못할 수 있는 경우를 위해 도서 전체 조회가 있다
						System.out.println("도서번호를 입력하세요 :");
						// 사용자가 입력한 도서아이디을 BookDTO에 담기 전에 check에 값 넘기기
						bookID = check.checkInt(scanner.nextInt());

						// 데이터베이스에 저장된 도서정보가 올바르게 삭제가 되었는지 확인하기 위해 boolean Type 설정
						boolean Falg2 = bookController.deleteBook(bookID);
						//데이터베이스에 있는 도서정보의 삭제가 성공적으로 이루어졌을 때
						if(Falg2) {
							System.out.println("삭제 성공!");
						}
						//삭제가 안됐을 때 
						else {
							System.out.println("삭제 실패!");
						}

						break;

					case 4:

						// 데이터베이스안에 도서정보(view)를 가져오기위해 List<BookDTO> 후 bookContoller 호출
						List<BookDTO> book = bookController.selectBook();
						//데이터베이스에 저장된 도서정보가 null 일때
						if(book==null) {
							System.out.println("저장된 도서정보가 없습니다!");  
						}
						//데이터베이스에 저장되어 있는 데이터가 book에 저장이 되었을 때
						else {
							System.out.println("=========도서정보===========");
							System.out.println(book);
						}

						break;
					}

				}

				else if(choice1==2) {
					System.out.println("==================================================");
					System.out.println("1.사용자추가 2.사용자수정 3.사용자삭제 4.사용자조회 5.프로그램종료");
					System.out.println("==================================================");
					int choice2=scanner.nextInt();
					switch(choice2) {
					case 1:

						System.out.println("등록할 사용자 번호를 입력하세요 :");
						//사용자의 입력값을 UserDTO에 담기전에 사전 check넘기기
						int userID=check.checkInt(scanner.nextInt());

						scanner.nextLine();
						//올바르게 입력이 되었을 때 dto에 값 저장.
						udt.setUserID(userID);

						System.out.println("사용자 이름을 입력하세요 :");
						//사용자의 입력값을 UserDTO에 담기전에 사전 check넘기기 
						String userName =check.checkString(scanner.nextLine());
						//올바르게 입력이 되었을 때 dto에 값 저장
						udt.setUserName(userName);

						System.out.println("사용자 성별을 입력하세요 :");
						//사용자의 입력값을 UserDTO에 담기전에 사전 check넘기기
						String userSex =check.checkString(scanner.nextLine());
						//올바르게 입력이 되었을 때 dto에 값 저장
						udt.setuserSex(userSex);

						System.out.println("사용자 나이를 입력하세요 :");
						//사용자의 입력값을 UserDTO에 담기전에 사전 check넘기기
						int userAge = check.checkInt(scanner.nextInt());

						scanner.nextLine();
						//올바르게 입력이 되었을 때 dto에 값 저장
						udt.setuserAge(userAge);

						System.out.println("사용자 전화번호를 입력하세요 :");
						//사용자의 입력값을 UserDTO에 담기전에 사전 check넘기기
						String userPhone = check.checkString(scanner.nextLine());
						//올바르게 입력이 되었을 때 dto에 값 저장
						udt.setUserPhone(userPhone);

						//사용자가 입력한 값을 데이터베이스에 저장이 되었는지 , 저장이 안되었는지 확인하기 위해 boolean Type 설정
						boolean Flag= userController.addUser(udt);
						//데이터베이스 올바르게 사용자의 입력값(dto)이 저장되었다면
						if(Flag) {
							System.out.println("저장 성공 !");
						}
						//데이터베이스 사용자의 입력이 저장이 되어있지 않다면
						else {
							System.out.println("저장 실패 !");
						}
						break;

					case 2:

						// 회원번호를 기억하지 못하는 회원을 위해 회원 전체 조회가 있다
						System.out.println("회원 번호를 입력하세요 :");
						//사용자의 입력값을 UserDTO에 담기전에 사전 check넘기기 
						userID = check.checkInt(scanner.nextInt());

						//사용자의 입력값이 데이터베이스 저장되어 있는 userID와 일치 여부 확인 
						boolean Falg = userController.selectUser1(userID);

						// 일치한다면 IF문 수행 
						if(Falg) {

							//올바르게 입력이 되었을 때 dto에 값 저장
							udt.setUserID(userID);
							System.out.println("수정하고자 하는 회원이름을 입력하세요 :");

							scanner.nextLine();
							//사용자의 입력값을 UserDTO에 담기전에 사전 check넘기기
							userName = check.checkString(scanner.nextLine());
							//올바르게 입력이 되었을 때 dto에 값 저장
							udt.setUserName(userName);

							System.out.println("수정하고자 하는 회원성별을 입력하세요 :");
							//사용자의 입력값을 UserDTO에 담기전에 사전 check넘기기 
							userSex=check.checkString(scanner.nextLine());
							//올바르게 입력이 되었을 때 dto에 값 저장
							udt.setuserSex(userSex);

							System.out.println("수정하고자 하는 회원나이을 입력하세요 :");
							//사용자의 입력값을 UserDTO에 담기전에 사전 check넘기기 
							userAge=check.checkInt(scanner.nextInt());
							//올바르게 입력이 되었을 때 dto에 값 저장
							udt.setuserAge(userAge);

							//이클립스 버그로 scanner 선언
							scanner.nextLine();

							System.out.println("수정하고자 하는 회원전화번호를 입력하세요 :");
							//사용자의 입력값을 UserDTO에 담기전에 사전 check넘기기 
							userPhone=check.checkString(scanner.nextLine());
							//올바르게 입력이 되었을 때 dto에 값 저장
							udt.setUserPhone(userPhone);

							// 데이터베이스에 저장된 도서정보가 올바르게 수정이 되었는지 확인하기 위해 boolean Type 설정
							boolean Flag1 = userController.updateUser(udt);

							//올바르게 수행이 되었다면
							if(Flag1) {
								System.out.println("수정 성공!");
							}
							//그렇지 않다면
							else {
								System.out.println("수정 실패!");
							}
						}
						else {
							System.out.println("등록된 회원정보가 없습니다!");
						}

						break;

					case 3:

						System.out.println("회원 번호를 입력하세요 :");
						userID = check.checkInt(scanner.nextInt());
						//사용자가 입력한 값을 데이터베이스에 저장이 되었는지 , 저장이 안되었는지 확인하기 위해 boolean Type 설정
						boolean Flag2 = userController.deleteUser(userID);
						if(Flag2) {
							System.out.println("삭제 성공");
						}
						else{
							System.out.println("삭제 실패");
						}

						break;

					case 4:

						//데이터베이스에 저장되어 있는 회원정보를 가져오기위해 List<UserDTO> user 객체 생성
						List<UserDTO> user = userController.selectUser();

						// user가 null값이라면 (데이터베이스에서 반환값이 없다면)
						if(user==null) {

							System.out.println("저장된 회원 정보가 없습니다!");  
						}
						else {
							System.out.println("=========회원정보===========");
							System.out.println(user);	
						}

						break;

					}

				}

				else if(choice1==3) {
					System.out.println("==================================================");
					System.out.println(      "1.도서대여 2.도서반납 3.대여조회 4.프로그램종료"        );
					System.out.println("==================================================");

					int choice3 = scanner.nextInt();

					switch(choice3) {

					case 1:

						System.out.println("도서 대여를 도와드리겠습니다");
						System.out.println("");


						System.out.println("회원님의 번호를 입력 하세요 (도서번호가 기억이 나지 않으시면 도서대여클릭해서 도서조회해서 확인하시면 됩니다) : ");
						//사용자의 입력값을 bookRentalDTO에 담기전에 사전 check넘기기
						int userID = check.checkInt(scanner.nextInt());


						System.out.println("원하는 도서 번호를 입력 하세요 (도서번호가 기억이 나지 않으시면 도서대여클릭해서 도서조회해서 확인하시면 됩니다) : ");
						//사용자의 입력값을 bookRentalDTO에 담기전에 사전 check넘기기
						int bookID = check.checkInt(scanner.nextInt());


						// 회원이 도서를 대여하기 전 중복된 도서가 있는지 확인절차
						// true시 대여 진행 중복된 도서가 있으면 else문 실행
						boolean Flag =bookRentalController.overLapID(userID,bookID);

						// 중복 대여가 없다면 !
						if(Flag) {
							// 입력값을 brd에 저장!
							brd.setUserID(userID);
							brd.setBookID(bookID);

							// 대여를 하기위해서 반환값을 boolean 값으로 설정
							boolean Flag1 = bookRentalController.bookRental(brd);

							if(Flag1) {

								System.out.println("대여 완료!");	
							}
							else {

								System.out.println("대여 실패!");
							}

						}else {

							System.out.println("이미 대여중입니다!");
						}


						break;

					case 2:

						System.out.println("도서 반납을 도와드리겠습니다");
						System.out.println("");

						System.out.println("회원님의 번호를 입력 하세요 (도서번호가 기억이 나지 않으시면 도서대여클릭해서 도서조회해서 확인하시면 됩니다) : ");
						userID = check.checkInt(scanner.nextInt());

						// 회원이 도서를 반납하기 위해서 boolean type 선언
						// true시 대여 진행 중복된 도서가 있으면 else문 실행
						boolean Falg1= bookRentalController.deleteBookRental(userID);

						// true라면
						if(Falg1) {
							System.out.println("반납 성공");
						}
						else{
							System.out.println("반납 실패");
						}

						break;

					case 3:

						//DB에 있는 대여목록을 보여주기 위해 List<BookRentalDTO> 선언
						List<BookRentalDTO> bookrental = bookRentalController.selectBookRental();
						if(bookrental==null) {
							System.err.println("대여중인 도서가 없습니다!");  
						}
						else {

							System.out.println(bookrental);
						}
					}

				}
				else if(choice1==4) {
					System.out.println("프로그램 종료합니다");
					System.exit(0);
				}
			} 

		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}  
} 
