package main;

import java.util.Scanner;

import board.BoardController;
import member.MemberController;
import member.MemberVo;
import notion.NotionController;

public class Main {

	public static final Scanner SC = new Scanner(System.in);
	public static MemberVo loginManager = null;
	
	public static void main(String[] args) throws Exception {

		MemberController mc = new MemberController();
		BoardController bc = new BoardController();
		NotionController nc = new NotionController();
		
		while(true) {
			if(loginManager != null) {
				System.out.println(loginManager.getNick() + "님 환영합니다 😊");
				System.out.println();
			}
			
			System.out.println("1. MEMBER");
			System.out.println("2. BOARD");
			System.out.println("3. 공지사항");			
			System.out.println("0. 프로그램 종료");
			System.out.print("메뉴 번호 입력 : ");
			String num = SC.nextLine();
			System.out.println();
			
			try {
				switch(num) {
				case "1" : mc.printMenu(); break;
				case "2" : bc.printMenu(); break;
				case "3" : nc.printMenu(); break;
				case "0" : System.out.println("종료합니다."); return;
				default : System.out.println("잘못 입력하셨습니다.");
				}
			}catch(Exception e) {
				System.out.println("예외가 발생했습니다.");
				System.out.println();
				e.printStackTrace();
			}
		}
		
	}//main

}//class
