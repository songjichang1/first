package book.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.db.DBConnection;

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
	public int addbook(BookDTO dto) throws SQLException {

		// INSERT의 쿼리반환 값이 int형 이기 때문에 int type 선언
		int result = 0;

		// DB 연결을 위한 생성자 호출
		DBConnection dbc= new DBConnection(); 

		//동적 쿼리를 실행하기 위해서 psmt 선언 및 null값 선언
		PreparedStatement psmt =null;

		String sql= "INSERT INTO "
				+ "BOOK VALUES (?,?,?,?)";

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

	/* Method Name  :  updateBook  
	 * ========================================= Method Detail =============================================
	 * 1. Service에서 전달 받은 BookDTO dto를 가지고 도서수정을 하기 위해 DB에 접근한다.
	 * 2. DAO에서 return type이 int type이기 때문에 int type 설정.
	 * 3. 개발자가 try-catch문을 작성해 수동적으로 제어 함.  
	 * =====================================================================================================
	 */	
	public int updateBook(BookDTO dto) throws SQLException {

		// UPDATE의 쿼리반환 값이 int형 이기 때문에 int type 선언
		int result =0;

		// DB 연결을 위한 생성자 호출
		DBConnection dbc= new DBConnection();
		//동적 쿼리를 실행하기 위해서 psmt 선언 및 null값 선언
		PreparedStatement psmt =null;

		String sql="UPDATE BOOK A SET "
				+ "A.BOOKTITLE = ?,"
				+ "A.BOOKAUTHOR= ?,"
				+ "A.BOOKPRICE = ? "
				+ "WHERE 1=1"
				+ "AND A.BOOKID = ?";
		try {
			psmt=dbc.con.prepareStatement(sql);
			psmt.setString(1,dto.getBookTitle());
			psmt.setString(2,dto.getBookAuthor());
			psmt.setInt(3,dto.getBookPrice());
			psmt.setInt(4, dto.getBookID());


			result = psmt.executeUpdate();

		}catch(SQLException e) {

			System.out.println(e.getMessage());
			throw new SQLException(e);
		}finally{
			//쿼리가 종료되고 메모리낭비를 방지하기위해 close 선언
			dbc.close(psmt);
		}

		return result;
	}

	/* Method Name  :  deleBook  
	 * ========================================= Method Detail =============================================
	 * 1. Service에서 전달 받은 BookDTO dto를 가지고 도서삭제를 하기 위해 DB에 접근한다.
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

		String sql= "DELETE"
				+ " FROM BOOK A"
				+ " WHERE 1=1"
				+ "AND A.BOOKID = ?";
		try {
			psmt=dbc.con.prepareStatement(sql);
			psmt.setInt(1,bookID);

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

	/* Method Name  :  updateBook  
	 * ========================================= Method Detail =============================================
	 * 1. Service에서 전달 받은 BookDTO dto를 가지고 도서정보를 가져오기 위해 DB에 접근한다.
	 * 2. DAO에서 return type을 List<BookDTO> type으로 반환해야 하기 때문에 List<BookDTO> type 설정.
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
		PreparedStatement psmt =null;



		String sql = "SELECT " 
				+ "A.BOOKID, "
				+ "A.BOOKAUTHOR, "
				+ "A.BOOKTITLE, "
				+ "A.BOOKPRICE "
				+ "FROM BOOK A";

		psmt=dbc.con.prepareStatement(sql);

		rs=psmt.executeQuery();

		try {
			//데이터베이스에 있는 데이터만큼 반복
			while(rs.next()) {

				// 데이터베이스에 저장되어 있는 데이터를 받기 위해서 BookDTO book 선언
				BookDTO book = new BookDTO();

				int bookID=rs.getInt("BOOKID");
				book.setBookID(bookID);

				String bookAuthor=rs.getString("BOOKAUTHOR");
				book.setBookAuthor(bookAuthor);

				String bookTitle=rs.getString("BOOKTITLE");
				book.setBookTitle(bookTitle);

				int bookPrice=rs.getInt("BOOKPRICE");
				book.setBookPrice(bookPrice);

				// book에 반환 받은 값을 bookList에 추가
				bookList.add(book);

			}
		}catch(SQLException e) {

			System.out.println(e.getMessage());
			throw new SQLException(e);
		}finally {

			//쿼리가 종료되고 메모리낭비를 방지하기위해 close 선언
			dbc.close(rs);
			dbc.close(psmt);
		}

		return bookList;


	}

	/* Method Name  :  selectBook1 
	 * ========================================= Method Detail =============================================
	 * 1. Service에서 전달 받은 int bookID를 가지고 정보를 수정하기 전 DB에 일치하는 데이터가 있는지 확인 하기위해 DB접근한다. 
	 * 2. DAO에서 return type이 int type이기 때문에 int type 설정.
	 * 3. 개발자가 try-catch문을 작성해 수동적으로 제어 함.  
	 * =====================================================================================================
	 */	
	public int selectBook1(int bookID) throws SQLException {

		int cnt = 0;

		//DB연결 (생성자 호춣)
		DBConnection dbc= new DBConnection();
		//SELECT 쿼리문 결과 저장할때 사용하기 때문에 null값 선언
		ResultSet rs = null;
		//동적 쿼리를 실행하기 위해서 psmt 선언 및 null값 선언
		PreparedStatement psmt =null;

		// DB에 저장되어있는 값과 사용자가 입력값과 비교만 하기 때문에 COUNT 선언
		String sql = "SELECT COUNT (*) AS CNT "
				+    "FROM BOOK A "
				+    "WHERE 1=1 "
				+    "AND A.BOOKID = ?";
		try {
			// SQL문에 (?)에 전달하기 위해 psmt 선언
			psmt=dbc.con.prepareStatement(sql);
			//데이터베이스에 저장되어 있는 데이터를 확인하기 위해서 rs 선언
			// SQL문에 (?)에 전달하기 위해 psmt 선언
			psmt.setInt(1,bookID);


			rs=psmt.executeQuery();

			while(rs.next()) {

				//일치하는 값이 있다면 cnt에 값을 반환
				cnt = rs.getInt("CNT");

			}

		}catch(SQLException e) {

			System.out.println(e.getMessage());
			throw new SQLException(e);
		
		}finally {
			//쿼리가 종료되고 메모리낭비를 방지하기위해 close 선언
			dbc.close(rs);
			dbc.close(psmt);
		}

		System.out.println(cnt);

		return cnt;


	}
}