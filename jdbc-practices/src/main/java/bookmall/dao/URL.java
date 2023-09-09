package bookmall.dao;

public class URL {
	private boolean where; // 집이면 False, 학원이면 True
	
	public URL() {
		where = false;
	}
	
	public void setWhere(boolean where) {
		this.where = where;
	};
	
	public String getURL() {
		if (this.where) // 학원에서
			return "192.168.0.185";
		return "192.168.123.108"; // 집
	}
}