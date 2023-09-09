package bookmall.vo;

public class OrdersBookVo {
	private String bookName;
	
	public OrdersBookVo(String bookName) {
		this.bookName = bookName;
	}
	
	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	@Override
	public String toString() {
		return "OrdersBookVo [bookname : " + getBookName() + "]";
	}
}
