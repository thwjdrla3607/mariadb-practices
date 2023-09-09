package bookmall.vo;

import java.sql.Timestamp;
import java.util.Random;

public class OrdersVo {
	private int no;
	private int totalPrice;
	private String address;
	private String ordersCode;
	private String memberName;
	
	public OrdersVo(String memberName, String address) {
		this.memberName = memberName;
		this.address = address;

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String timestampCode = String.valueOf(timestamp.getTime());
		Random random = new Random();
		int randNum = random.nextInt(100);
		this.ordersCode = timestampCode + "" + randNum;
	}

	public OrdersVo(int no, int totalPrice, String address, String ordersCode, String memberName) {
		this.no = no;
		this.totalPrice = totalPrice;
		this.memberName = memberName;
		this.address = address;
		this.memberName = memberName;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOrdersCode() {
		return ordersCode;
	}

	public void setOrdersCode(String ordersCode) {
		this.ordersCode = ordersCode;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	@Override
	public String toString() {
		return "OrdersVo [no=" + no + ", totalPrice=" + totalPrice + ", address=" + address + ", ordersCode="
				+ ordersCode + ", memberName=" + memberName + "]";
	}
}
