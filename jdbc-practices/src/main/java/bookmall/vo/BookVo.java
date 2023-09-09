package bookmall.vo;

public class BookVo {
	private int no;
	private String title;
	private int price;
	private String categoryName;
	
	public BookVo(String title, int price) {
		this.title = title;
		this.price = price;
	}
	
	public BookVo(String title, int price, String categoryName) {
		this.title = title;
		this.price = price;
		this.categoryName = categoryName;
	}

	public BookVo(int no, String title, int price, String categoryName) {
		this.no = no;
		this.title = title;
		this.price = price;
		this.categoryName = categoryName;
	}

	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	@Override
	public String toString() {
		return "BookVo [no=" + no + ", title=" + title + ", price=" + price + ", categoryName=" + categoryName + "]";
	}
}
