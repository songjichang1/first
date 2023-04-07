package book.service;

/*
 * DTO는 DB의 정보를 담고, view 에서 입력값을 저장하기 위해 순수객체만 들어있는 클래스
 * 순수객체를 private를 한 이유는 외부로부터 데이터를 은닉하기 위해 접근제한자를 private로 하엿고
 * getter, setter를 public으로 설정하여 접근가능하게 함.
 */

public class BookDTO {

	private int bookID;
	private String bookAuthor;
	private String bookTitle;
	private int bookPrice;


	public int getBookID() {
		return bookID;
	}

	public void setBookID(int bookID) {
		this.bookID = bookID;
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

	@Override
	public String toString() {
		return "[도서번호=" + bookID + ", 도서명=" + bookTitle + ", 도서저자=" + bookAuthor + ", 도서가격="
				+ bookPrice + "]";
	}

}
