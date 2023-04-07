package bookRental.service;

/*
 * DTO는 DB의 정보를 담고, view 에서 입력값을 저장하기 위해 순수객체만 들어있는 클래스
 * 순수객체를 private를 한 이유는 외부로부터 데이터를 은닉하기 위해 접근제한자를 private로 하엿고
 * getter, setter를 public으로 설정하여 접근가능하게 함.
 */

public class BookRentalDTO {

	private int BookID;
	private int UserID;

	private String bookTitle;
	private String bookAuthor;
	private int bookPrice;

	private String userName; 
	private String userSex; 
	private int userAge; 
	private String userPhone; 




	public int getBookID() {
		return BookID;
	}



	public void setBookID(int bookID) {
		BookID = bookID;
	}



	public int getUserID() {
		return UserID;
	}



	public void setUserID(int userID) {
		UserID = userID;
	}



	public String getBookTitle() {
		return bookTitle;
	}



	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}



	public String getBookAuthor() {
		return bookAuthor;
	}



	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}



	public int getBookPrice() {
		return bookPrice;
	}



	public void setBookPrice(int bookPrice) {
		this.bookPrice = bookPrice;
	}



	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getUserSex() {
		return userSex;
	}



	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}



	public int getUserAge() {
		return userAge;
	}



	public void setUserAge(int userAge) {
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
		return "BookRentalDTO [BookID=" + BookID + ", UserID=" + UserID + ", bookTitle=" + bookTitle
				+ ", bookAuthor=" + bookAuthor + ", bookPrice=" + bookPrice + ", userName=" + userName
				+ ", userSex=" + userSex + ", userAge=" + userAge + ", userPhone=" + userPhone + "]";
	}


}
