package bookmall.main;

import java.util.LinkedList;
import java.util.List;

import bookmall.vo.CategoryVo;
import bookmall.vo.MemberVo;
import bookmall.vo.BookVo;
import bookmall.vo.CartVo;
import bookmall.vo.OrdersVo;
import bookmall.vo.OrdersBookVo;

import bookmall.dao.CategoryDao;
import bookmall.dao.MemberDao;
import bookmall.dao.BookDao;
import bookmall.dao.CartDao;
import bookmall.dao.OrdersDao;
import bookmall.dao.OrdersBookDao;

public class BookMall {

	static void init() {
		createMember();
		createCategory();
		createBook();
		createCart();
		createOrders();
	}	
	
	static void createMember() {
		List<MemberVo> memberList = new LinkedList<>();
		memberList.add(new MemberVo("김소정", "010-1234-5678", "aa@aa.a", "0000"));
		memberList.add(new MemberVo("최승혁", "010-8765-4321", "bb@bb.b", "0000"));

		for (MemberVo member : memberList)
			new MemberDao().insert(member);
	}
	static void createCategory() {
		List<CategoryVo> categoryList = new LinkedList<>();
		categoryList.add(new CategoryVo("인문"));
		categoryList.add(new CategoryVo("사회"));		
		categoryList.add(new CategoryVo("경제"));

		for (CategoryVo category : categoryList)
			new CategoryDao().insert(category);
	}
	static void createBook() {
		List<BookVo> bookList = new LinkedList<>();
		bookList.add(new BookVo("도둑맞은 집중력", 16920, "인문"));
		bookList.add(new BookVo("디케의 눈물", 16080, "사회"));
		bookList.add(new BookVo("일론 머스크", 34200, "경제"));

		for (BookVo book : bookList)
			new BookDao().insert(book);
	}
	static void createCart() {
		List<CartVo> cartList = new LinkedList<>();
		cartList.add(new CartVo("김소정", "도둑맞은 집중력", 1));
		cartList.add(new CartVo("김소정", "일론 머스크", 1));
		cartList.add(new CartVo("최승혁", "일론 머스크", 1));

		for (CartVo cart : cartList)
			new CartDao().insert(cart);
	}
	static void createOrders() {
		List<OrdersVo> ordersList = new LinkedList<>();
		ordersList.add(new OrdersVo("김소정", "창원"));

		for (OrdersVo orders : ordersList)
			new OrdersDao().insert(orders);		
	}

	static void readMember() {
		List<MemberVo> memberList = new MemberDao().findAll();
		for(MemberVo member : memberList) 
			System.out.println(member.toString());
		System.out.println();
	}
	static void readCategory() {
		List<CategoryVo> categoryList = new CategoryDao().findAll();
		for(CategoryVo category : categoryList) 
			System.out.println(category.toString());
		System.out.println();
	}
	static void readBook() {
		List<BookVo> bookList = new BookDao().findAll();
		for(BookVo book : bookList) 
			System.out.println(book.toString());
		System.out.println();
	}
	static void readCart() {
		List<CartVo> cartList = new CartDao().findAll();
		for(CartVo cart : cartList) 
			System.out.println(cart.toString());
		System.out.println();
	}
	static void readOrders() {
		List<OrdersVo> ordersList = new OrdersDao().findAll();
		for(OrdersVo orders : ordersList) 
			System.out.println(orders.toString());
		System.out.println();
	}
	static void readOrdersBook() {
		List<OrdersBookVo> ordersBookList = new OrdersBookDao().findAll();
		for(OrdersBookVo ordersBook : ordersBookList) 
			System.out.println(ordersBook.toString());
		System.out.println();
	}
	
	public static void main(String[] args) {
		//init();
		
		System.out.println("## 회원리스트");
		readMember();

		System.out.println("## 카테고리");
		readCategory();

		System.out.println("## 상품");
		readBook();
		
		System.out.println("## 카트");
		readCart();
		
		System.out.println("## 주문");
		readOrders();
		
		System.out.println("## 주문도서");
		readOrdersBook();
	}
}