package main;

import java.util.Scanner;

import member.MemberController;
import member.MemberVo;
import notion.NotionController;
import notion.NotionVo;

public class Main {

	public static final Scanner SC = new Scanner(System.in);
	
	public static MemberVo loginMember = null;
	
	public static void main(String[] args) throws Exception {

		MemberController mc = new MemberController();
		NotionController nc = new NotionController();
		
		while(true) {
			System.out.println("Woongs Notion");
			
			System.out.println("0. 종료");
			System.out.println("1. 회원가입 및 로그인");
			System.out.println("2. 공지사항 작성 및 조회");
			
			System.out.println("원하는 프로그램 번호를 입력해주세요.");
			String num = Main.SC.nextLine();
			
			switch(num) {
			case "0" : System.out.println("프로그램을 종료합니다."); return;
			case "1" : mc.printMenu(); break;
			case "2" : nc.printMenu(); break;
			default : System.out.println("잘못된 입력입니다.");
			}
			
			
		}
	
	}

}
