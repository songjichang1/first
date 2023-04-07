package bookRental.service;


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

public class BookRentalDAO {

	/* Method Name  :  overLapID 
	 * ========================================= Method Detail =============================================
	 * 1. Service에서 전달 받은 int userID,int bookiD를 가지고 대여를 하기 전 DB에 중복된 데이터가 없는지 확인하기 위해 DB접근한다. 
	 * 2. DAO에서 return type이 int type이기 때문에 int type 설정.
	 * 3. 개발자가 try-catch문을 작성해 수동적으로 제어 함.  
	 * =====================================================================================================
	 */	
	public int overLapID(int userID, int bookID) throws SQLException {

		//SELECT 쿼리문 결과 저장할때 사용하려면 int type 선언이 필요없지만
		// 사용자의 입력값이 DB에 저장되어 있는 데이터와 일치여부를 확인하기 위해서 int형 선언
		int cnt = 0;

		//DB연결 (생성자 호춣)
		DBConnection dbc= new DBConnection();
		//SELECT 쿼리문 결과 저장할때 사용하기 때문에 null값 선언
		ResultSet rs = null;
		//동적 쿼리를 실행하기 위해서 psmt 선언 및 null값 선언
		PreparedStatement psmt =null;

		// DB에 저장되어있는 값과 사용자가 입력값과 비교만 하기 때문에 COUNT 선언
		// 중복된 도서를 대여할 수 없으므로 SQL문을 통해 데이터베이스에서 USERID,BOOKID확인 
		String sql = "SELECT COUNT (*) AS CNT "
				+    "FROM BOOKRENTAL A "
				+    "WHERE 1=1 "
				+    "AND A.USERID = ? "
				+    "AND A.BOOKID = ? ";

		try {
			// SQL문에 (?)에 전달하기 위해 psmt 선언
			psmt=dbc.con.prepareStatement(sql);

			// SQL문에 (?)에 전달하기 위해 psmt 선언
			psmt.setInt(1,userID);
			psmt.setInt(2,bookID);

			//데이터베이스에 저장되어 있는 데이터를 확인하기 위해서 rs 선언
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

		return cnt;

	}


	/* Method Name  :  bookRental 
	 * ========================================= Method Detail =============================================
	 * 1. Service에서 전달 받은 BookRentalDTO brd를 가지고 대여를 하기 위해 DB에 접근한다.
	 * 2. DAO에서 return type이 int type이기 때문에 int type 설정.
	 * 3. 개발자가 try-catch문을 작성해 수동적으로 제어 함.  
	 * =====================================================================================================
	 */	
	public int bookRental(BookRentalDTO brd) throws SQLException{

		// INSERT의 쿼리반환 값이 int형 이기 때문에 int type 선언
		int result = 0;

		// DB 연결을 위한 생성자 호출
		DBConnection dbc= new DBConnection(); 
		//동적 쿼리를 실행하기 위해서 psmt 선언 및 null값 선언
		PreparedStatement psmt =null; 

		String sql= "INSERT INTO BOOKRENTAL "
				+ "VALUES (?,?)";

		try {

			psmt=dbc.con.prepareStatement(sql);

			psmt.setInt(1,brd.getUserID());
			psmt.setInt(2,brd.getBookID());



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

	/* Method Name  :  deleteBookRental  
	 * ========================================= Method Detail =============================================
	 * 1. Service에서 전달 받은 int userID를 가지고 대여목록을 삭제를 하기 위해 DB에 접근한다.
	 * 2. DAO에서 return type이 int type이기 때문에 int type 설정.
	 * 3. 개발자가 try-catch문을 작성해 수동적으로 제어 함.  
	 * =====================================================================================================
	 */	
	public int deleteBookRental(int userID) throws SQLException{

		// DELETE는 쿼리반환 값이 int형 이기 때문에 int type 선언
		int result = 0;
		// DB 연결을 위한 생성자 호출
		DBConnection dbc= new DBConnection();
		//동적 쿼리를 실행하기 위해서 psmt 선언 및 null값 선언
		PreparedStatement psmt =null;

		String sql= "DELETE FROM "
				+ "BOOKRENTAL A "
				+ "WHERE 1=1 "
				+ "AND A.USERID = ?";
		try {
			psmt=dbc.con.prepareStatement(sql);
			psmt.setInt(1,userID);


			result = psmt.executeUpdate();

		}catch(SQLException e) {

			System.out.println(e.getMessage());
			throw new SQLException(e);

		}finally {
			//메모리 낭비를 하지 않기 위해서 close
			dbc.close(psmt);
		}

		return result;
	}

	/* Method Name  :  deleteBookRental1
	 * ========================================= Method Detail =============================================
	 * 1. Service에서 전달 받은 int bookID를 가지고 도서 삭제가 되었을 시 도서를 대여중인 대여 목록도 함께삭제를 하기 위해 DB에 접근한다.
	 * 2. DAO에서 return type이 int type이기 때문에 int type 설정.
	 * 3. 개발자가 try-catch문을 작성해 수동적으로 제어 함.  
	 * =====================================================================================================
	 */	
	public int deleteBookRental1(int bookID) throws SQLException{

		// DELETE의 쿼리반환 값이 int형 이기 때문에 int type 선언
		int result = 0;
		// DB 연결을 위한 생성자 호출
		DBConnection dbc= new DBConnection();
		//동적 쿼리를 실행하기 위해서 psmt 선언 및 null값 선언
		PreparedStatement psmt =null;

		String sql= "DELETE"
				+ " FROM BOOKRENTAL A "
				+ "WHERE 1=1"
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

	/* Method Name  :  deleteBookRental2
	 * ========================================= Method Detail =============================================
	 * 1. Service에서 전달 받은 int userID를 가지고 사용자삭제가 되었을 시 대여중인 사용자의 목록도 함께삭제를 하기 위해 DB에 접근한다.
	 * 2. DAO에서 return type이 int type이기 때문에 int type 설정.
	 * 3. 개발자가 try-catch문을 작성해 수동적으로 제어 함.  
	 * =====================================================================================================
	 */	
	public int deleteBookRental2(int userID) throws SQLException{

		// DELETE의 쿼리반환 값이 int형 이기 때문에 int type 선언
		int result = 0;
		// DB 연결을 위한 생성자 호출
		DBConnection dbc= new DBConnection();
		//동적 쿼리를 실행하기 위해서 psmt 선언 및 null값 선언
		PreparedStatement psmt =null;

		String sql= "DELETE  FROM BOOKRENTAL WHERE USERID = ?";
		try {
			psmt=dbc.con.prepareStatement(sql);
			psmt.setInt(1,userID);

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

	/* Method Name  : selectBookRental
	 * ========================================= Method Detail =============================================
	 * 1. Service에서 요청한 대여목록를 가져오기 위해 DB에 접근한다.
	 * 2. DAO에서 return type을 List<BookRentalDTO> type으로 반환해야 하기 때문에 List<BookRentalDTO> type 설정.
	 * 3. 개발자가 try-catch문을 작성해 수동적으로 제어 함.  
	 * =====================================================================================================
	 */	
	public List<BookRentalDTO> selectBookRental() throws SQLException{

		//DB에 저장되어있는 정보를 가져오기 위해 List<BookRentalDTO>타입 선언
		List<BookRentalDTO> bookRentalList = new ArrayList<>(); 

		// DB 연결을 위한 생성자 호출
		DBConnection dbc= new DBConnection();

		//동적 쿼리를 실행하기 위해서 psmt 선언 및 null값 선언
		PreparedStatement psmt =null;
		//SELECT 쿼리문 결과 저장할때 사용하기 때문에 null값 선언 
		ResultSet rs = null;



		String sql = "SELECT * "
				+ "FROM BOOKRENTAL A INNER JOIN USER1 B "
				+ "ON(A.USERID = B.USERID) "
				+ "INNER JOIN BOOK C "
				+ "ON(A.BOOKID = C.BOOKID)";

		psmt=dbc.con.prepareStatement(sql);
		rs=psmt.executeQuery();

		try {
			while(rs.next()) {

				BookRentalDTO brd = new BookRentalDTO();

				int BookID1 = rs.getInt("BOOKID");
				brd.setBookID(BookID1);
				int UserID1 = rs.getInt("USERID");
				brd.setUserID(UserID1);

				String bookTitle = rs.getString("BOOKTITLE");
				brd.setBookTitle(bookTitle);
				String bookAuthor = rs.getString("BOOKAUTHOR");
				brd.setBookAuthor(bookAuthor);
				int bookPrice = rs.getInt("BOOKPRICE");
				brd.setBookPrice(bookPrice);

				String userName = rs.getString("USERNAME");
				brd.setUserName(userName);
				String userSex = rs.getString("USERSEX");
				brd.setUserSex(userSex);
				int userAge = rs.getInt("USERAGE");
				brd.setUserAge(userAge);
				String userPhone = rs.getString("USERPHONE");
				brd.setUserPhone(userPhone);  

				bookRentalList.add(brd);
			}
		}catch(SQLException e) {

			System.out.println(e.getMessage());
			throw new SQLException(e);

		}finally {
			//쿼리가 종료되고 메모리낭비를 방지하기위해 close 선언
			dbc.close(rs);
			dbc.close(psmt);
		}

		return bookRentalList;
	}

}
