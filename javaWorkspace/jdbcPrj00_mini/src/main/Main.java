package main;

import java.util.Scanner;

import board.BoardController;
import faq.FaqBoardController;
import member.MemberController;
import member.MemberVo;
import notion.NotionController;
import product.ProductController;
import product_order.ProductOrderController;
import review.ReviewController;

public class Main {

	public static final Scanner SC = new Scanner(System.in);
	public static MemberVo loginManager = null;

	public static void main(String[] args) throws Exception {

		MemberController mc = new MemberController();
		ReviewController rc = new ReviewController();
        BoardController bc = new BoardController();
        FaqBoardController fc = new FaqBoardController();
        NotionController nc = new NotionController();
        ProductController pc = new ProductController();
        ProductOrderController poc = new ProductOrderController();
		while (true) {

			if (loginManager != null) {
				System.out.println("---------------------------------------");
				System.out.println(loginManager.getNick() + "님 환영합니다 😊");
			}

            System.out.println("1. 로그인 및 회원가입(MEMBER) ");
            System.out.println("2. 공지사항(NOTION)");
            System.out.println("3. 제품(PRODUCT)");
            System.out.println("4. 제품 주문(PRODUCTORDER)");
            System.out.println("5. 리뷰(REVIEW)");
            System.out.println("6. 자유게시판(BOARD)");
            System.out.println("7. QNA");
            System.out.println("8. FAQ");
            System.out.println("0. 프로그램 종료");
            System.out.print("메뉴 번호 입력 : ");
            String num = SC.nextLine();
			try {
				switch (num) {
				case "1": mc.printMenu(); break;
                case "2": nc.printMenu(); break;
                case "3" : pc.printMenu(); break;
                case "4" : poc.printMenu(); break;
                case "5" : rc.printMenu(); break;
                case "6" : bc.printMenu(); break;
                //case "7" : ; break;
                case "8" : fc.PrintMenu(); break;
				case "0": System.out.println("종료합니다."); return;
				default: System.out.println("잘못 입력하셨습니다.");
				}
			} catch (Exception e) {
				System.out.println("예외가 발생했습니다.");
				e.printStackTrace();
			}
		}

	}// main

}// class
