package user.service;

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

public class UserDAO {

	/* Method Name  :  addUser  
	 * ========================================= Method Detail =============================================
	 * 1. Service에서 전달 받은 UserDTO udt를 가지고 사용자의 정보를 등록를 하기 위해 DB에 접근한다.
	 * 2. DAO에서 return type이 int type이기 때문에 int type 설정.
	 * 3. 개발자가 try-catch문을 작성해 수동적으로 제어 함.  
	 * =====================================================================================================
	 */	
	public int addUser(UserDTO udt) throws SQLException{

		// INSERT의 쿼리반환 값이 int형 이기 때문에 int type 선언
		int result = 0;

		// DB 연결을 위한 생성자 호출
		DBConnection dbc= new DBConnection(); 

		//동적 쿼리를 실행하기 위해서 psmt 선언 및 null값 선언
		PreparedStatement psmt=null;

		String sql= "INSERT INTO "
				+ "USER1 VALUES (?,?,?,?,?)";

		try {
			psmt=dbc.con.prepareStatement(sql);

			psmt.setInt(1, udt.getUserID());
			psmt.setString(2,udt.getUserName());
			psmt.setString(3,udt.getuserSex());
			psmt.setInt(4, udt.getuserAge());
			psmt.setString(5, udt.getUserPhone());


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

	/* Method Name  :  updateUser  
	 * ========================================= Method Detail =============================================
	 * 1. Service에서 전달 받은 UserDTO udt를 가지고 사용자의 정보를 수정을 하기 위해 DB에 접근한다.
	 * 2. DAO에서 return type이 int type이기 때문에 int type 설정.
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
				+ "WHERE A.USERID = ?";

		try {
			psmt=dbc.con.prepareStatement(sql);

			psmt.setString(1,udt.getUserName());
			psmt.setString(2,udt.getuserSex());
			psmt.setInt(3,udt.getuserAge());
			psmt.setString(4,udt.getUserPhone());
			psmt.setInt(5, udt.getUserID());


			result = psmt.executeUpdate();

		}catch(SQLException e) {

			System.out.println(e.getMessage());
			throw new SQLException(e);

		}finally {

			dbc.close(psmt);
		}

		return result;
	}

	/* Method Name  :  deleteUser  
	 * ========================================= Method Detail =============================================
	 * 1. Service에서 전달 받은 int userID를 가지고 사용자 삭제를 하기 위해 DB에 접근한다.
	 * 2. DAO에서 return type이 int type이기 때문에 int type 설정.
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

		String sql= "DELETE"
				+ " FROM USER1 A"
				+ " WHERE 1=1 "
				+ "AND A.USERID = ?";
		try {

			psmt=dbc.con.prepareStatement(sql);

			psmt.setInt(1,userID);


			result =psmt.executeUpdate();


		}catch(SQLException e) {

			System.out.println(e.getMessage());
			throw new SQLException(e);

		}finally {
			//쿼리가 종료되고 메모리낭비를 방지하기위해 close 선언
			dbc.close(psmt);
		}

		return result;
	}


	/* Method Name  :  selectUser
	 * ========================================= Method Detail =============================================
	 * 1. Service에서 요청한 사용자의정보를 가져오기 위해 DB에 접근한다.
	 * 2. DAO에서 return type을 List<BookDTO> type으로 반환해야 하기 때문에 List<BookDTO> type 설정.
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
		//SELECT 쿼리문 결과 저장할때 사용하기 때문에 null값 선언 
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
			//데이터베이스에 있는 데이터만큼 반복
			while(rs.next()) {

				// 데이터베이스에 저장되어 있는 데이터를 받기 위해서 UserDTO user 선언 
				UserDTO user = new UserDTO ();

				int userID=rs.getInt("USERID");
				user.setUserID(userID);

				String userName =rs.getString("USERNAME");
				user.setUserName(userName);

				String userSex =rs.getString("USERSEX");
				user.setuserSex(userSex);

				int userAge =rs.getInt("USERAGE");
				user.setuserAge(userAge);

				String userPhone =rs.getString("USERPHONE");
				user.setUserPhone(userPhone);

				// user에 반환 받은 값을 userList에 추가
				userList.add(user);
			}
		}catch(SQLException e) {

			System.out.println(e.getMessage());
			throw new SQLException(e);

		}finally {

			//쿼리가 종료되고 메모리낭비를 방지하기위해 close 선언
			dbc.close(rs);
			dbc.close(psmt);
		}

		return userList;
	}

	/* Method Name  :  selectUser1 
	 * ========================================= Method Detail =============================================
	 * 1. Service에서 전달 받은 int userID를 가지고 정보를 수정하기 전 DB에 일치하는 데이터가 있는지 확인 하기위해 DB접근한다. 
	 * 2. DAO에서 return type이 int type이기 때문에 int type 설정.
	 * 3. 개발자가 try-catch문을 작성해 수동적으로 제어 함.  
	 * =====================================================================================================
	 */	
	public int selectUser1(int userID) throws SQLException {

		//SELECT 쿼리문 결과 저장할때 사용하려면 int type 선언이 필요없지만
		// 사용자의 입력값이 DB에 저장되어 있는 데이터와 일치여부를 확인하기 위해서 int형 선언
		int cnt = 0;

		//DB연결 (생성자 호춣)
		DBConnection dbc= new DBConnection();
		//SELECT 쿼리문 결과 저장할때 사용하기 때문에 null값 선언
		ResultSet rs = null;
		//동적 쿼리를 실행하기 위해서 psmt 선언 및 null값 선언
		PreparedStatement psmt =null;

		//DB에 저장되어있는 값과 사용자가 입력값과 비교만 하기 때문에 COUNT 선언
		String sql= "SELECT COUNT (*) AS CNT "
				+ "FROM USER1 A "
				+ "WHERE 1=1 "
				+ "AND A.USERID = ?";

		try {
			// SQL문에 (?)에 전달하기 위해 psmt 선언
			psmt =dbc.con.prepareStatement(sql);

			psmt.setInt(1,userID);

			//데이터베이스에 저장되어 있는 데이터를 확인하기 위해서 rs 선언
			rs=psmt.executeQuery();


			 
			//일치하는 값이 있다면 cnt에 값을 반환
			while(rs.next()) {

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

}
