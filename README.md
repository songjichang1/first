#JAVA-Project-Library 
Java+Oracle도서관프로그램
***
## :computer: 프로젝트 소개
- **도서관에서 회원(추가,수정,삭제,조회), 도서(추가,수정,삭제,조회), 대여(대여,반납,조회)**

## :mag_right: 프로젝트 상세 내용
- **프로그램 실행자는 도서매니저**
- 회원(추가,수정,삭제,조회), 도서(추가,수정,삭제,조회), 대여(대여,반납,조회)
- 대여은 중복된 도서를 제외 하고 n개 대여 가능
- 도서, 회원 삭제 시 대여중인 회원, 도서목록도 함께 삭제

## :bulb: **기술스택**
- ` java ` 
- ` Oracle `

## :scroll: **주요기능**


```java
public class Check {
	public String checkString(String value) throws Exception1 {

		if(value==null || "".equals(value)) {

			throw new Exception1();
		}
		return value;
	}

	public int checkInt(int value) throws Exception1 {

		if(value < 0 || value == 0) {

			throw new Exception1();
		}

		return value;
    
	}
```
- view에서 입력받은 값을 바로 controller나 setter하기 전에 check.checkInt, checkString 에서 확인

```java
public class Exception1 extends Exception{


	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {

		return "잘못된 값입니다  프로그램을 다시 실행주세요! ";
	}

}
```
- 입력 값이 Check클래스의 if문안에 해당하지 않는다면 값이 일치하지 않다는 메시지를 view에게 보여주기위해 클래스 생성. 

```java
public int selectBook1(int bookID) throws SQLException {
		int cnt = 0;
		DBConnection dbc= new DBConnection();
		ResultSet rs = null;
		PreparedStatement psmt =null;
	
		String sql = "SELECT COUNT (*) AS CNT "
				+    "FROM BOOK A "
				+    "WHERE 1=1 "
				+    "AND A.BOOKID = ?";
```
- 도서, 회원의 정보를 수정하기 전 view에서 받은 입력값이 DB에 저장되어 있는지(존재 및 일치 여부) 확인 후 다음 로직 수행 

```java
public int overLapID(int userID, int bookID) throws SQLException {
		int cnt = 0;
		DBConnection dbc= new DBConnection();
		ResultSet rs = null;
		PreparedStatement psmt =null;

		String sql = "SELECT COUNT (*) AS CNT "
				+    "FROM BOOKRENTAL A "
				+    "WHERE 1=1 "
				+    "AND A.USERID = ? "
				+    "AND A.BOOKID = ? ";

``` 
 - 대여를 하기 전 중복된 도서를 대여를 할 수 없기 때문에 DB에서 확인 후 대여(가능,불가능) 여부 확인 
