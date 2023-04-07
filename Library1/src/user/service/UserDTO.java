package user.service;

/*
 * DTO는 DB의 정보를 담고, view 에서 입력값을 저장하기 위해 순수객체만 들어있는 클래스
 * 순수객체를 private를 한 이유는 외부로부터 데이터를 은닉하기 위해 접근제한자를 private로 하엿고
 * getter, setter를 public으로 설정하여 접근가능하게 함.
 */

public class UserDTO {

	private int userID; 
	private String userName; 
	private String userSex; 
	private int userAge; 
	private String userPhone; 


	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getuserSex() {
		return userSex;
	}

	public void setuserSex(String userSex) {
		this.userSex = userSex;
	}

	public int getuserAge() {
		return userAge;
	}

	public void setuserAge(int userAge) {
		this.userAge = userAge;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	@Override
	public String toString() {
		return "[회원고유번호=" + userID + ", 회원이름=" + userName + ", 회원성별=" + userSex + ", 회원나이=" + userAge
				+ ", 회원전화번호=" + userPhone + "]";
	}

}
