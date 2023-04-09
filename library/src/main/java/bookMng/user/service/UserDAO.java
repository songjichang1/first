package bookMng.user.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.DBConnection;

import bookMng.user.dto.UserDTO;

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

public class UserDAO {


	/* Method Name  :  addUser  
	 * ========================================= Method Detail =============================================
	 * 1. Service에서 전달 받은 UserDTO udt를 가지고 사용자의 정보를 등록를 하기 위해 DB에 접근한다.
	 * 2. Service에서 return type을 int type으로 설정을 하였기 때문에 int type 설정.
	 * 3. 개발자가 try-catch문을 작성해 수동적으로 제어 함.  
	 * =====================================================================================================
	 */	
	public int addUser(UserDTO udt) throws SQLException{

		// INSERT의 쿼리반환 값이 int형 이기 때문에 int type 선언
		int result = 0;

		// DB 연결을 위한 생성자 호출
		DBConnection dbc= new DBConnection(); //  DB 연결을 위한 생성자 호출

		//동적 쿼리를 실행하기 위해서 psmt 선언 및 null값 선언
		PreparedStatement psmt=null;
		
		
		String sql= "INSERT INTO USER1 "
				+ "VALUES (?,?,?,?,?)";

		try {
			psmt=dbc.con.prepareStatement(sql);

			psmt.setInt(1, udt.getUserID());
			psmt.setString(2,udt.getUserName());
			psmt.setString(3,udt.getUserSex());
			psmt.setInt(4, udt.getUserAge());
			psmt.setString(5, udt.getUserPhone());

			
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
	
	/* Method Name  :  updateUser  
	 * ========================================= Method Detail =============================================
	 * 1. Service에서 전달 받은 UserDTO udt를 가지고 사용자의 정보를 수정을 하기 위해 DB에 접근한다.
	 * 2. Service에서 return type을 int type으로 설정을 하였기 때문에 int type 설정.
	 * 3. 개발자가 try-catch문을 작성해 수동적으로 제어 함.  
	 * =====================================================================================================
	 */	
	public int updateUser(UserDTO udt) throws SQLException {

		// UPDATE의 쿼리반환 값이 int형 이기 때문에 int type 선언
		int result = 0;

		// DB 연결을 위한 생성자 호출
		DBConnection dbc= new DBConnection();

		//동적 쿼리를 실행하기 위해서 psmt 선언 및 null값 선언
		PreparedStatement psmt=null;

		String sql = "UPDATE USER1 A "
				+ "SET A.USERNAME = ?,"
				+ "A.USERSEX = ?,"
				+ "A.USERAGE = ?,"
				+ "A.USERPHONE = ?"
				+ " WHERE USERID = ?";

		try {
			psmt=dbc.con.prepareStatement(sql);

			psmt.setString(1,udt.getUserName());
			psmt.setString(2,udt.getUserSex());
			psmt.setInt(3,udt.getUserAge());
			psmt.setString(4,udt.getUserPhone());
			psmt.setInt(5, udt.getUserID());

			result = psmt.executeUpdate();

		}catch(SQLException e) {
			
			System.out.println(e.getMessage());
			throw new SQLException(e);

		}
		finally {
			//메모리 낭비를 방지하기 위해 close 
			dbc.close(psmt);
		}

		return result;
	}

	/* Method Name  :  deleteUser  
	 * ========================================= Method Detail =============================================
	 * 1. Service에서 전달 받은 int userID를 가지고 사용자 삭제를 하기 위해 DB에 접근한다.
	 * 2. Service에서 return type을 int type으로 설정을 하였기 때문에 int type 설정.
	 * 3. 개발자가 try-catch문을 작성해 수동적으로 제어 함.  
	 * =====================================================================================================
	 */	
	public int deleteUser(int userID) throws SQLException{

		// DELETE의 쿼리반환 값이 int형 이기 때문에 int type 선언
		int result = 0;

		// DB 연결을 위한 생성자 호출
		DBConnection dbc= new DBConnection();
		//동적 쿼리를 실행하기 위해서 psmt 선언 및 null값 선언
		PreparedStatement psmt=null;

		String sql= "DELETE "
				+ "FROM USER1 A "
				+ "WHERE 1=1 "
				+ "AND A.USERID = ?";
		try {
			psmt=dbc.con.prepareStatement(sql);
			psmt.setInt(1,userID);

			result =psmt.executeUpdate();

		}catch(SQLException e) {
			
			System.out.println(e.getMessage());
			throw new SQLException(e);

		}
		finally {
			//메모리 낭비를 방지하기 위해 close 
			dbc.close(psmt);

		}

		return result;
	}

	/* Method Name  :  selectUser1 
	 * ========================================= Method Detail =============================================
	 * 1. Service에서 특정 사용자의 int userID를 가지고 그 사용자의 상세정보를 보기 위해 DB에 접근한다.
	 * 2. Service에서 return type을 UserDTO dto 으로 설정을 하였기 때문에 UserDTO dto 설정.
	 * 3. 개발자가 try-catch문을 작성해 수동적으로 제어 함.  
	 * =====================================================================================================
	 */	
	public UserDTO selectUser1(int userID) throws SQLException{

		// DB 연결을 위한 생성자 호출
		DBConnection dbc= new DBConnection();

		//동적 쿼리를 실행하기 위해서 psmt 선언 및 null값 선언
		PreparedStatement psmt =  null;
		//정적 쿼리문 실행 시 사용하기 때문에 null값 선언
		ResultSet rs = null;
		// 특정 사용자의 정보를 담기 위해서 UserDTO user 선언 후 null 지정
		UserDTO user = null;


		String sql= "SELECT "
				+ "A.USERID,"
				+ "A.USERNAME,"
				+ "A.USERSEX,"
				+ "A.USERAGE,"
				+ "A.USERPHONE"
				+ " FROM USER1 A "
				+ "WHERE 1=1 "
				+ "AND A.USERID = ?";

		try {
			psmt=dbc.con.prepareStatement(sql);

			psmt.setInt(1,userID);			

			rs=psmt.executeQuery();


			while(rs.next()) {

				userID = rs.getInt("USERID");
				String userName =rs.getString("USERNAME");
				String userSex=rs.getString("USERSEX");
				int userAge=rs.getInt("USERAGE");
				String userPhone = rs.getString("USERPHONE");

				user = new UserDTO(userID,userName,userSex,userAge,userPhone);

			}

		}catch(SQLException e) {
			System.out.println(e.getMessage());
			throw new SQLException(e);
		}
		finally {
			//메모리 낭비를 방지하기 위해 close 
			dbc.close(psmt);
			dbc.close(rs);
	
		}

		return user;
	}
	
	/* Method Name  :  selectUser
	 * ========================================= Method Detail =============================================
	 * 1. Service에서 요청한 사용자의정보를 가져오기 위해 DB에 접근한다.
	 * 2. Service에서 return type을 List<BookDTO> type으로 설정을 하였기 때문에 List<BookDTO> type 설정.
	 * 3. 개발자가 try-catch문을 작성해 수동적으로 제어 함.  
	 * =====================================================================================================
	 */	
	public List<UserDTO> selectUser() throws SQLException{

		//DB에 저장되어있는 정보를 가져오기 위해 List<UserDTO>타입 선언
		List<UserDTO> userList = new ArrayList<>();
		//DB 연결 (생성자 호출)
		DBConnection dbc= new DBConnection();
		//동적 쿼리를 실행하기 위해서 psmt 선언 및 null값 선언
		PreparedStatement psmt =  null;
		//정적 쿼리문 실행 시 사용하기 때문에 null값 선언
		ResultSet rs = null;

	
		String sql = "SELECT "
				+ "A.USERID,"
				+ "A.USERNAME,"
				+ "A.USERSEX,"
				+ "A.USERAGE,"
				+ "A.USERPHONE"
				+ " FROM USER1 A";

		psmt=dbc.con.prepareStatement(sql);

		rs=psmt.executeQuery();
		
		try {
			while(rs.next()) {

				UserDTO user = new UserDTO ();

				int userID=rs.getInt("USERID");
				user.setUserID(userID);

				String userName =rs.getString("USERNAME");
				user.setUserName(userName);

				String userSex =rs.getString("USERSEX");
				user.setUserSex(userSex);

				int userAge =rs.getInt("USERAGE");
				user.setUserAge(userAge);

				String userPhone =rs.getString("USERPHONE");
				user.setUserPhone(userPhone);

				userList.add(user);
			}
		}catch(SQLException e) {
			
			System.out.println(e.getMessage());
			throw new SQLException(e);
		}
		finally {
			//메모리 낭비를 방지하기 위해 close 
			dbc.close(rs);
			dbc.close(psmt);
		}
		
		return userList;


	}

}
