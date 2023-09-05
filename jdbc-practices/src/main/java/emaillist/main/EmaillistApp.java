package emaillist.main;

import java.util.List;
import java.util.Scanner;

import emaillist.vo.EmaillistVo;

public class EmaillistApp {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.println("(l)ist  (i)nsert  (d)elete  (q)uit");
        String command = scanner.nextLine();

        if ("l".equals(command)){
            doList();
        } else if ("i".equals(command)) {
            doInsert();
        } else if ("d".equals(command)) {
            doDelete();
        } else if("q".equals(command)){
            break;
        }
    }

	private static void doDelete() {
		new EmaillistDao().
		
	}

	private static void doInsert() {
		System.out.println("doInsert");
		
	}

	private static void doList() {
		List<EmaillistVo> list = new EmaillistDao().findAll();
		for(EmaillistVo vo : list) {
			System.out.println("이름: " + vo.getFirstName() + vo.getLastName());
		}
		
	}
}