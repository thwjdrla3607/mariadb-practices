package bookmall.vo;

public class CartVo {
	private int no;
	private String member;
	private String book;
	private int count;
	
	public CartVo(String member, String book, int count) {
		this.member = member;
		this.book = book;
		this.count = count;
	}

	public CartVo(int no, String member, String book, int count) {
		this.no = no;
		this.member = member;
		this.book = book;
		this.count = count;
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getMember() {
		return member;
	}
	public void setMember(String member) {
		this.member = member;
	}
	public String getBook() {
		return book;
	}
	public void setBook(String book) {
		this.book = book;
	}
	
	@Override
	public String toString() {
		return "CartVo [no=" + no + ", member=" + member + ", book=" + book + ", count=" + count + "]";
	}
}
