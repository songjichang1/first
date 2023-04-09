package bookMng.book.service;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.DBConnection;

import bookMng.book.dto.BookDTO;

/*      DAO 역할
 * ================
 *  Service -> DAO  
 *  DAO -> Service 
 * ======================================================================================= 
 *  DAO는 DB에 접근하는 클래스 
 *  DAO는 DB에 접근해 INSERT(입력),UPDATE(수정),DELETE(삭제),SELECT(읽기) DML 작업을 한다.
 *  DB에서 받아온 데이터를 Service에게 반환.
 * =======================================================================================
 */

/*
 * DBConnection dbc : DB연결을 하기위한 생성자 호출
 * Connection con : 데이터베이스 연결 담당
 * PreparedStatement psmt : 동적 쿼리문 실행 시 사용 
 * ResultSet rs : SELECT 쿼리문 결과 저장할때 사용 
 * Statement stmt : 정적 쿼리문 실행 시 사용
 */
public class BookDAO {
	

	/* Method Name  :  addbook  
	 * ========================================= Method Detail =============================================
	 * 1. Service에서 전달 받은 BookDTO dto를 가지고 도서등록를 하기 위해 DB에 접근한다.
	 * 2. DAO에서 return type이 int type이기 때문에 int type 설정.
	 * 3. 개발자가 try-catch문을 작성해 수동적으로 제어 함.  
	 * =====================================================================================================
	 */	
	public int addBook(BookDTO dto) throws SQLException {

		// INSERT의 쿼리반환 값이 int형 이기 때문에 int type 선언
		int result = 0;
		
		// DB 연결을 위한 생성자 호출
		DBConnection dbc= new DBConnection(); 
		//동적 쿼리를 실행하기 위해서 psmt 선언 및 null값 선언
		PreparedStatement psmt =null;
		
		String sql= "INSERT INTO book VALUES (?,?,?,?)";

		try {
			psmt=dbc.con.prepareStatement(sql);
			psmt.setInt(1,dto.getBookID());
			psmt.setString(2,dto.getBookTitle());
			psmt.setString(3,dto.getBookAuthor());
			psmt.setInt(4,dto.getBookPrice());

			result = psmt.executeUpdate();

		}catch(SQLException e) {
		
			System.out.println(e.getMessage());
			throw new SQLException(e);
		}finally {
			//쿼리가 종료되고 메모리낭비를 방지하기위해 close 선언
			dbc.close(psmt);
		}

		return result;

	}
	
	/* Method Name  :  deleteBook  
	 * ========================================= Method Detail =============================================
	 * 1. Service에서 전달 받은 int bookID를 가지고 도서삭제를 하기 위해 DB에 접근한다.
	 * 2. DAO에서 return type이 int type이기 때문에 int type 설정.
	 * 3. 개발자가 try-catch문을 작성해 수동적으로 제어 함.  
	 * =====================================================================================================
	 */	
	public int deleteBook(int bookID) throws SQLException {

		// DELETE의 쿼리반환 값이 int형 이기 때문에 int type 선언
		int result = 0;
		
		// DB 연결을 위한 생성자 호출
		DBConnection dbc= new DBConnection();
		//동적 쿼리를 실행하기 위해서 psmt 선언 및 null값 선언
		PreparedStatement psmt =null;
		
		String sql= "DELETE "
				+ "FROM BOOK A "
				+ "WHERE 1=1 "
				+ "AND A.BOOKID = ?";
		try {
			psmt=dbc.con.prepareStatement(sql);
			psmt.setInt(1,bookID);

			result = psmt.executeUpdate();

		}catch(SQLException e) {
			
			System.out.println(e.getMessage());
			throw new SQLException(e);
		}
		finally {
			//쿼리가 종료되고 메모리낭비를 방지하기위해 close 선언
			dbc.close(psmt);
		}
		return result;
	}
	
	/* Method Name  :  updateBook  
	 * ========================================= Method Detail =============================================
	 * 1. Service에서 전달 받은 BookDTO dto를 가지고 도서수정을 하기 위해 DB에 접근한다.
	 * 2. DAO에서 return type이 int type이기 때문에 int type 설정.
	 * 3. 개발자가 try-catch문을 작성해 수동적으로 제어 함.  
	 * =====================================================================================================
	 */	
	public int updateBook(BookDTO bd) throws SQLException {

		// UPDATE의 쿼리반환 값이 int형 이기 때문에 int type 선언
		int result = 0;

		// DB 연결을 위한 생성자 호출
		DBConnection dbc= new DBConnection();
		//동적 쿼리를 실행하기 위해서 psmt 선언 및 null값 선언
		PreparedStatement psmt =null;
		
		String sql="UPDATE BOOK "
				+ "SET BOOKTITLE =?,BOOKAUTHOR=?,BOOKPRICE = ? WHERE BOOKID = ?";
		try {
			
			psmt=dbc.con.prepareStatement(sql);
			psmt.setString(1,bd.getBookTitle());
			psmt.setString(2,bd.getBookAuthor());
			psmt.setInt(3,bd.getBookPrice());
			psmt.setInt(4, bd.getBookID());

			result = psmt.executeUpdate();

			
		}catch(SQLException e) {
			
			System.out.println(e.getMessage());
			throw new SQLException(e);
		}
		finally{
			//쿼리가 종료되고 메모리낭비를 방지하기위해 close 선언
			dbc.close(psmt);
		}
		
		return result;
	}
	
	/* Method Name  :  selectBook1  
	 * ========================================= Method Detail =============================================
	 * 1. Service에서 전달 받은 int bookID를 가지고 특정 도서의 정보를 보여주기 위해 DB에 접근한다.
	 * 2. DAO에서 return type이 int type이기 때문에 int type 설정.
	 * 3. 개발자가 try-catch문을 작성해 수동적으로 제어 함.  
	 * =====================================================================================================
	 */	
	public BookDTO selectBook1(int bookID) throws SQLException{
		
		// DB 연결을 위한 생성자 호출
		DBConnection dbc= new DBConnection();
		//동적 쿼리를 실행하기 위해서 psmt 선언 및 null값 선언
		PreparedStatement psmt =  null;
		//정적 쿼리문 실행 시 사용하기 때문에 null값 선언
		ResultSet rs = null;
		
		// 특정 도서의 정보를 담기 위해서 BookDTO book 선언 후 null 값 지정.
		BookDTO book = null;
		

		
		String sql= "SELECT "
				+ "A.BOOKID,"
				+ "A.BOOKAUTHOR,"
				+ "A.BOOKTITLE,"
				+ "A.BOOKPRICE "
				+ "FROM BOOK A "
				+ "WHERE 1=1 "
				+ "AND A.BOOKID = ?";
		
		try {
			psmt=dbc.con.prepareStatement(sql);
			
			psmt.setInt(1,bookID);			
			
			rs=psmt.executeQuery();
			
			while(rs.next()) {
				
				bookID = rs.getInt("BOOKID");
				String bookAuthor=rs.getString("BOOKAUTHOR");
				String bookTitle=rs.getString("BOOKTITLE");
				int bookPrice=rs.getInt("BOOKPRICE");
				
				book = new BookDTO (bookID,bookAuthor,bookTitle,bookPrice);
		     		
			}

		}catch(SQLException e) {
			
			System.out.println(e.getMessage());
			throw new SQLException(e);

		}finally {
			//쿼리가 종료되고 메모리낭비를 방지하기위해 close 선언
			dbc.close(psmt);
			dbc.close(rs);

		}
		
		return book;
	}


	/* Method Name  :  updateBook  
	 * ========================================= Method Detail =============================================
	 * 1. Service에서 요청한 도서정보를 가져오기 위해 DB에 접근한다.
	 * 2. DAO에서 return type을 List<BookDTO> type이기 때문에 List<BookDTO> type 설정.
	 * 3. 개발자가 try-catch문을 작성해 수동적으로 제어 함.  
	 * =====================================================================================================
	 */	
	public List<BookDTO> selectBook() throws SQLException{

		//DB에 저장되어있는 정보를 가져오기 위해 List<BookDTO>타입 선언
		List<BookDTO> bookList = new ArrayList<>();

		//DB 연결 (생성자 호출)
		DBConnection dbc= new DBConnection(); 
		//정적 쿼리문 실행 시 사용하기 때문에 null값 선언
		ResultSet rs = null;
		//동적 쿼리를 실행하기 위해서 psmt 선언 및 null값 선언
		PreparedStatement psmt =  null;


		String sql = "SELECT "
				+ "A.BOOKID,"
				+ "A.BOOKAUTHOR,"
				+ "A.BOOKTITLE,"
				+ "A.BOOKPRICE "
				+ "FROM BOOK A";

		psmt=dbc.con.prepareStatement(sql);
		
		rs=psmt.executeQuery();
		try {
			while(rs.next()) {

				BookDTO book = new BookDTO();

				int bookID=rs.getInt("BOOKID");
				book.setBookID(bookID);

				String bookAuthor=rs.getString("BOOKAUTHOR");
				book.setBookAuthor(bookAuthor);

				String bookTitle=rs.getString("BOOKTITLE");
				book.setBookTitle(bookTitle);

				int bookPrice=rs.getInt("BOOKPRICE");
				book.setBookPrice(bookPrice);

				bookList.add(book);

			}
		}catch(SQLException e) {
			
			System.out.println(e.getMessage());
			throw new SQLException(e);

		}
		finally {
			//쿼리가 종료되고 메모리낭비를 방지하기위해 close 선언
			dbc.close(rs);
			dbc.close(psmt);
		}
		
		return bookList;

	}
}
