package main;

import java.util.List;
import java.util.Scanner;

// 2021-06-07_박현지
// 1차 project  - 회원 정보 관리
// 콘솔 환경으로 진행
// DB : File DB (ex. 메모장)
// 기능 : 신규 등록, 목록 조회, 회원정보 수정, 회원 삭제

public class Member_main {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		System.out.println("메뉴를 선택하세요.(단, 정수로만 입력할 것)");
		System.out.println("===================================");
		System.out.println("1. 신규 등록");
		System.out.println("2. 목록 조회");
		System.out.println("3. 회원정보 수정");
		System.out.println("4. 회원 삭제");
		System.out.println("===================================");

		int num = scan.nextInt();

		Member_dao dao = new Member_dao();
		Member_bean bean = new Member_bean();

		// 스캐너로 입력 받아 해당 DAO 메소드로 이동
		switch (num) {
		case 1:
			// 신규 등록
			System.out.println("[신규 등록]");

			System.out.print("아이디 : ");
			bean.setId(scan.next());

			System.out.print("패스워드 : ");
			bean.setPassword(scan.next());

			System.out.print("성명 : ");
			bean.setName(scan.next());

			System.out.print("생년월일 8자 : ");
			bean.setBirth(scan.next());

			System.out.print("휴대폰 번호 : ");
			bean.setPhone(scan.next());

			System.out.print("나이 : ");
			bean.setAge(scan.nextInt());

			int cnt = 0;
			cnt = dao.insertData(bean);

			if (cnt > 0) {
				System.out.println("정상적으로 등록되었습니다.");
			}

			break;
		case 2:
			// 목록 조회
			List<Member_bean> lists = dao.selectAll();

			String result = "";
			result += "[목록 조회]\n";
			result += "아이디\t비밀번호\t성명\t생년월일\t\t휴대폰번호\t\t나이\n";
			result += "===============================================================\n";

			for (Member_bean bean1 : lists) {
				result += bean1.getId() + "\t";
				result += bean1.getPassword() + "\t";
				result += bean1.getName() + "\t";
				result += bean1.getBirth() + "\t";
				result += bean1.getPhone() + "\t";
				result += bean1.getAge() + "\n";

			}
			System.out.println(result);
			break;
		case 3:
			// 회원 정보 수정
			System.out.println("[회원정보 수정]");

			System.out.print("수정할 아이디 : ");
			bean.setId(scan.next());

			System.out.println("------회원 정보 변경------");
			System.out.print("패스워드 : ");
			bean.setPassword(scan.next());

			System.out.print("성명 : ");
			bean.setName(scan.next());

			System.out.print("생년월일 8자 : ");
			bean.setBirth(scan.next());

			System.out.print("휴대폰 번호 : ");
			bean.setPhone(scan.next());

			System.out.print("나이 : ");
			bean.setAge(scan.nextInt());
			
			cnt = 0;
			cnt = dao.updateData(bean);
			
			if (cnt > 0) {
				System.out.println("정상적으로 수정되었습니다.");
			}
			break;
		case 4:
			// 회원 정보 삭제
			System.out.println("[회원 삭제]");
			System.out.println("삭제할 아이디를 입력하세요.");
			System.out.print("아이디 : ");
			bean.setId(scan.next());
			
			cnt = 0;
			cnt = dao.deleteData(bean.getId());
			if (cnt > 0) {
				System.out.println("정상적으로 삭제되었습니다.");
			}
			break;

		default:
			break;
		}

	}

}
