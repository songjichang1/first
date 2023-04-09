package com;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import bookMng.book.web.DeleteBookController;
import bookMng.book.web.InsertBookController;
import bookMng.book.web.SelectBookController;
import bookMng.book.web.UpdateBookController;
import bookMng.book.web.SelectBookController1;

import bookMng.user.web.DeleteUserController;
import bookMng.user.web.InsertUserController;
import bookMng.user.web.SelectUserController;
import bookMng.user.web.UpdateUserController;
import bookMng.user.web.SelectUserController1;
import bookMng.bookRental.web.BorrowBookRentalController;
import bookMng.bookRental.web.ReturnBookRentalController;

//import bookMng.bookRental.web.BorrowBookRentalController;

import bookMng.bookRental.web.SelectBookRentalController;
import bookMng.bookRental.web.SelectBookRentalController1;


// 클라이언트(사용자)가 .do로 끝나는 (모든)요청은 FrontController(servlet)이 받는다.

public class FrontController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	//같은 패키지 내의 또는 자손클래스만 접근 가능하도록 접근제한자를 설정 
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		//JSP 한글깨짐 방지를 위한 코드 작성.
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");


		// 클라이언트가 어떤 요청을 했는지 파악하는 코드
		String url = request.getRequestURI();
//		System.out.println(url);
		//library/main.do
		//위와 요청을 했는지 파악하지만 프로젝트명 제외 
		String ctx = request.getContextPath();
//		System.out.println(ctx);
        //library
		//실제로 요청한 명령이 무엇인지 파악하는 코드
		String command = url.substring(ctx.length());
//		System.out.println(command);
       ///main.do
		//초기 값 null 설정
		
		Controller controller =null;
		String nextPage=null;

		//시작페이지 설정
		//요청에 따른 if-else if 작업
		if(command.equals("/main.do")) {//command가 main.do와 같다면 if문 실행

			request.setCharacterEncoding("euc-kr"); 
			// RequestDispatcher(요청의뢰)객체 생성 후 main.jsp화면을 띄우기위한 코드  
			RequestDispatcher red = request.getRequestDispatcher("/WEB-INF/main/Main.jsp");
			//포워드하여서 request,response (요청,의뢰) 객체 전달 
			red.forward(request, response);
		}

		//command가 insertBookPage.do와 같다면 else-if문 실행
		else if(command.equals("/insertBookPage.do")){ 
			// InsertBook.JSP로 포워딩 하기 
			RequestDispatcher red = request.getRequestDispatcher("WEB-INF/Book/InsertBook.jsp");
			//포워드하여서 request,response (요청,의뢰) 전달
			red.forward(request, response);
			
		}


		else if(command.equals("/insertBook.do")) {
			// insertBookController 객체 생성	
			controller = new InsertBookController();
			//1. insertBook.jsp에서 입력받은 값이 FrontController의 request, response 받는다 
			//2. FrontController의 request, response를 insertBookController에게 request, response 객체 넘김
			//3. 전달 후 insertBookController는 반환값(return)을 다음Page로 전환될 url를 받는다.
			nextPage=controller.rquestHandler(request, response);
			//전달 받은 nextPage로 요청의뢰(Dispatcher)한다.
			RequestDispatcher red = request.getRequestDispatcher(nextPage);
			//포워드하여서 request,response (요청,의뢰) 전달
			red.forward(request, response);
		}

		else if(command.equals("/deleteBook.do")) {

			//DeleteBookCotroller 객체 생성
			controller = new DeleteBookController();
			//1. SelectBook.jsp에서 checkBox를 통해 bookID를  FrontController의 request, response 받는다 
			//2. FrontController의 request, response를 DeleteBookController에게 request, response 객체 넘김
			//3. 전달 후 insertBookController는 반환값(return)을 다음Page로 전환될 url를 받는다.
			nextPage = controller.rquestHandler(request, response);
			//전달 받은 nextPage로 요청의뢰(Dispatcher)한다.
			RequestDispatcher red = request.getRequestDispatcher(nextPage);
			//포워드하여서 request,response (요청,의뢰) 전달
			red.forward(request, response);
		}

		//도서정보를 보기위한 코드
		else if(command.equals("/selectBook.do")) {

			controller = new SelectBookController(); 
			
			nextPage = controller.rquestHandler(request, response);
			//전달 받은 nextPage로 요청의뢰(Dispatcher)한다.
			RequestDispatcher red = request.getRequestDispatcher(nextPage);
			//포워드하여서 request,response (요청,의뢰) 전달
			red.forward(request, response);
		}

		//selectBook.jsp에서 checkBox누르면 그 해당 도서(Book)정보만 보여주기위한 코드.
		else if(command.equals("/updateBookPage.do")) {

			controller = new SelectBookController1();
			//1. SelectBook.jsp에서 bookID를 클릭 시 bookID의 값이  FrontController의 request, response 받는다 
			//2. FrontController의 request, response를 SelectBookController1에게 request, response 객체 넘김
			//3. 전달 후 SelectBookController1는 반환값(return)을 다음Page로 전환 될 url를 받는다.			
			nextPage= controller.rquestHandler(request, response);
			//전달 받은 nextPage로 요청의뢰(Dispatcher)한다.
			RequestDispatcher red = request.getRequestDispatcher(nextPage);
			//포워드하여서 request,response (요청,의뢰) 전달
			red.forward(request, response);
		}

		else if(command.equals("/updateBook.do")) {

			controller = new UpdateBookController();
			//1. UpdateBookPage.jsp에서 입력받은 값이 FrontController의 request, response 받는다 
			//2. FrontController의 request, response를 에게 UpdateBookController request, response 객체 넘김
			//3. 전달 후 UpdateBookController는 반환값(return)을 다음Page로 전환 될 url를 받는다.			
			nextPage= controller.rquestHandler(request, response);
			//전달 받은 nextPage로 요청의뢰(Dispatcher)한다.
			RequestDispatcher red = request.getRequestDispatcher(nextPage);
			//포워드하여서 request,response (요청,의뢰) 전달
			red.forward(request, response);
		}

		//--------------------------------------------------------------------------
		// 회원

		else if(command.equals("/insertUserPage.do")) { 
			RequestDispatcher red = request.getRequestDispatcher("WEB-INF/user/InsertUser.jsp");
			//포워드하여서 request,response (요청,의뢰) 객체 전달
			red.forward(request, response);
		}

		else if(command.equals("/insertUser.do")) {
			// insertUserController 객체 생성	
			controller = new InsertUserController();
			//1. InsertUser.jsp에서 입력받은 값이 FrontController의 request, response 받는다 
			//2. FrontController의 request, response를 InsertUser.jsp 에게 request, response 객체 넘김
			//3. 전달 후 InsertUserController는 반환값(return)을 다음Page로 전환 될 url를 받는다.			
			nextPage=controller.rquestHandler(request, response); 
			//전달 받은 nextPage로 요청의뢰(Dispatcher)한다.
			RequestDispatcher red = request.getRequestDispatcher(nextPage);
			//포워드하여서 request,response (요청,의뢰) 전달
			red.forward(request, response);
		}
		else if(command.equals("/selectUser.do")) {

			// SelectUserController 객체 생성
			controller = new SelectUserController();
		
			nextPage= controller.rquestHandler(request, response);
			//전달 받은 nextPage로 요청의뢰(Dispatcher)한다.	
			RequestDispatcher red = request.getRequestDispatcher(nextPage);
			//포워드하여서 request,response (요청,의뢰) 전달
			red.forward(request, response);
		}

		//selectUser.jsp에서 checkBox누르면 그 해당 회원(user)정보만 보여주기위한 코드.
		else if(command.equals("/updateUserPage.do")) {
			//해당 회원(user)정보를 보내기위해 SelectUserController 객체 생성
			controller = new SelectUserController1();
			//1.SelectUser.jsp에서 (checkBox)로 받은 값을 FrontController의 request, response 받는다 
			//2.FrontController의 request, response를 InsertUser.jsp 에게 request, response 객체 넘김
			//3.전달 후 SelectUserController1는 반환값(return)을 다음 Page로 전환 될 url를 받는다.
			nextPage= controller.rquestHandler(request, response);
			//전달 받은 nextPage로 요청의뢰(Dispatcher)한다.
			RequestDispatcher red = request.getRequestDispatcher(nextPage);
			//포워드하여서 request,response (요청,의뢰) 전달
			red.forward(request, response);
		}

		// 해당 회원(user)정보를 수정하기 위한 코드
		else if(command.equals("/updateUser.do")) {

			controller = new UpdateUserController();
			//1.UpdateUserPage.jsp에서 (checkBox)로 받은 값을 FrontController의 request, response 받는다 
			//2.FrontController의 request, response를 InsertUser.jsp 에게 request, response 객체 넘김
			//3.전달 후 UpdateUserController는 반환값(return)을 다음 Page로 전환 될 url를 받는다.
			nextPage= controller.rquestHandler(request, response);
			//전달 받은 nextPage로 요청의뢰(Dispatcher)한다.
			RequestDispatcher red = request.getRequestDispatcher(nextPage);
			//포워드하여서 request,response (요청,의뢰) 전달
			red.forward(request, response);
		}

		else if(command.equals("/deleteUser.do")) {
			
			controller = new DeleteUserController();
			//1.SelectUser.jsp에서 (checkBox)로 받은 값을 FrontController의 request, response 받는다 
			//2.FrontController의 request, response를 InsertUser.jsp 에게 request, response 객체 넘김
			//3.전달 후 DeleteUserController는 반환값(return)을 다음 Page로 전환 될 url를 받는다.
			nextPage= controller.rquestHandler(request, response);
			//전달 받은 nextPage로 요청의뢰(Dispatcher)한다.

			RequestDispatcher red = request.getRequestDispatcher(nextPage);
			//포워드하여서 request,response (요청,의뢰) 전달
			red.forward(request, response);
		}

		//----------------------------------------------------------------

		//대여목록을 나타내기 위한 코드  
		else if(command.equals("/borrowSelectBook.do")) {

			//SelectBookRentalController1 객체 생성
			controller = new SelectBookRentalController1();
			
			nextPage = controller.rquestHandler(request, response);
			//전달 받은 nextPage로 요청의뢰(Dispatcher)한다.
			RequestDispatcher red = request.getRequestDispatcher(nextPage);
			//포워드하여서 request,response (요청,의뢰) 전달
			red.forward(request, response);
		}

		/*
		 * 대여를 원활하게 하기 위해서 회원정보가 있는 데이터와 도서정보가 있는 데이터를 보여준다 
		 * 대여를 하고자 하는 회원이 원하는 도서(checkBox)를 통해 대여가 이루어진다. 
		 */
		else if(command.equals("/borrowSelectBookPage.do")) {

			controller = new SelectBookRentalController();
			
			nextPage = controller.rquestHandler(request, response);
			//전달 받은 nextPage로 요청의뢰(Dispatcher)한다.
			RequestDispatcher red = request.getRequestDispatcher(nextPage);
			//포워드하여서 request,response (요청,의뢰) 전달
			red.forward(request, response);
		}

		//대여하기
		else if(command.equals("/borrowBookRental.do")) {

			///SelectBookRental.jsp에서 대여를 하려는 회원(userID)과  대여하고자 하는 도서(bookID)를 checkBox를 통해 데이터를 보낸다.
			controller = new BorrowBookRentalController();
			//1.SelectBookRental.jsp에서 (checkBox)로 받은 값(userID,bookID)을 FrontController의 request, response 받는다 
			//2.FrontController의 request, response를 InsertUser.jsp 에게 request, response 객체 넘김
			//3.전달 후 BorrowBookRentalController는 반환값(return)을 다음 Page로 전환 될 url를 받는다.
			nextPage = controller.rquestHandler(request, response);
			//전달 받은 nextPage로 요청의뢰(Dispatcher)한다.
			RequestDispatcher red = request.getRequestDispatcher(nextPage);
			//포워드하여서 request,response (요청,의뢰) 전달
			red.forward(request, response);
		}

		// BorrowBookRental.jsp에서 checkBox(userID) 데이터를 받아 ReturnBookRental.do에 전달한다 
		else if(command.equals("/ReturnBookRental.do")) {
			
			controller = new ReturnBookRentalController();
			//1.BorrowBookRental.jsp에서 (checkBox)로 받은 값(userID)을 FrontController의 request, response 받는다 
			//2.FrontController의 request, response를 InsertUser.jsp 에게 request, response 객체 넘김
			//3.전달 후 ReturnBookRentalController는 반환값(return)을 다음 Page로 전환 될 url를 받는다.
			nextPage = controller.rquestHandler(request, response);
			//전달 받은 nextPage로 요청의뢰(Dispatcher)한다.
			RequestDispatcher red = request.getRequestDispatcher(nextPage);
			//포워드하여서 request,response (요청,의뢰) 전달
			red.forward(request, response);
		}
		
	}	

}
