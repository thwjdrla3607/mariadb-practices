package emaillist.dao.test;

import java.util.List;

import emaillist.dao.EmaillistDao;
import emaillist.vo.EmaillistVo;

public class EmaillistDaoTest {

	public static void main(String[] args) {
		EmaillistVo vo = new EmaillistVo();
		vo.setFisrtName("둘");
		vo.setLastName("리");
		vo.setEmail("dooly@gmail.com");
		
		testInsert(vo);
		testFindAll();
		testDeleteByEmail("dooly@gmail.com");
		testFindAll(;)
		

	}

	private static void testDeleteByEmail(String string) {
		// TODO Auto-generated method stub
		
	}

	private static void testInsert(EmaillistVo vo) {
		// TODO Auto-generated method stub
		
	}

	private static void testFindAll() {
		List<EmaillistVo> list = new EmaillistDao().findAll();
		for(EmaillistVO vo : list) {
			System.out.println(vo);
		}
	}

}
