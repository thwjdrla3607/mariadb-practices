package bookshop.vo;

public class BookVo {
	private int no;
	private String title;
	private char rent;
	private int dept_no;
	
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
	public char getRent() {
		return rent;
	}
	public void setRent(char rent) {
		this.rent = rent;
	}
	public int getDept_no() {
		return dept_no;
	}
	public void setDept_no(int dept_no) {
		this.dept_no = dept_no;
	}
	
	@Override
	public String toString() {
		return "BookVo [no=" + no + ", title=" + title + ", rent=" + rent + ", dept_no=" + dept_no + "]";
	}
	
	
}
