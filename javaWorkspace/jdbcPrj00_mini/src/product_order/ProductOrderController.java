package product_order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import main.Main;
import util.JDBCT;

public class ProductOrderController {
	
	public void printMenu() throws Exception{
		while(true) {
			System.out.println("주문 테이블");
			System.out.println("1.물건 조회");
			System.out.println("2.물건 주문");
			System.out.println("3.주문 취소");
			System.out.println("4.주문한 물건 확인");
			System.out.println("0.이이전으로 돌아가기 ");
			
			int num = Integer.parseInt(Main.SC.nextLine());
			
			switch(num) {
			case 1 : selectOrderProduct(); break;
			case 2 : orderProduct(); break;
			case 3 : orderCancel(); break;
			case 4 : orderCheck(); break;
			case 0 : System.out.println("돌아가기"); return;
			default : System.out.println("잘못 입력하셨습니다.");
			}
			
		}
	}
	
	private void selectOrderProduct() throws Exception{
		Connection conn = JDBCT.getConn();
		
		String sql = "SELECT NO,DRINK_NAME,PRICE,PRODUCT_STOCK FROM PRODUCT  WHERE DEL_YN = 'N' ORDER BY NO ASC";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) {
			String no = rs.getString("NO");
			String drinkName = rs.getString("drink_name");
			String price = rs.getString("PRICE");
			String product_stock = rs.getString("PRODUCT_STOCK");
			
			System.out.println(no + " : " +drinkName + 
					"		"+"가격: " + price 
					+ "		" + "남은재고: "+ product_stock);
			System.out.println();
		}
			
	}
	
	private void orderProduct() throws Exception{
		if(Main.loginManager != null) {
			Connection conn = JDBCT.getConn();
			System.out.print("물건 번호 : ");
			int productNo = Integer.parseInt(Main.SC.nextLine());
			System.out.print("주문할 물건 개수 : ");
			int productCnt = Integer.parseInt(Main.SC.nextLine());
			
			
			String sql = "INSERT INTO PRODUCT_ORDER(NO,MEM_NO,PRODUCT_NO,ADDRESS,PRODUCT_CNT,PRICE) VALUES (SEQ_PRODUCT_ORDER_NO.NEXTVAL,?,?,?,?, ?*(select price from product where no =?) )";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, Main.loginManager.getNo());
			pstmt.setInt(2, productNo);
			pstmt.setString(3, Main.loginManager.getAddress());
			pstmt.setInt(4, productCnt);
			pstmt.setInt(5, productCnt);
			pstmt.setInt(6, productNo);
			
			
			
			int result = pstmt.executeUpdate();
			System.out.println(result);
			
			if(result != 1) {
				System.out.println("주문 실패...");
			}
			conn.commit();
			System.out.println("주문 완료!");	
		}else {
			System.out.println("로그인을 해주세요");
		}
	}
	
	private void orderCancel() throws Exception{
		Connection conn = JDBCT.getConn();
		
		System.out.print("주문 번호 : ");
		int productNo = Integer.parseInt(Main.SC.nextLine());
		
		String sql = "UPDATE PRODUCT_ORDER SET CANCEL_YN = 'Y' WHERE NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, productNo);
		int result = pstmt.executeUpdate();
		
		if(result != 1) {
			System.out.println("주문 취소 실패...");
		}
		conn.commit();
		System.out.println("주문 취소 완료!");
	}
	
	private void orderCheck() throws Exception{
		Connection conn = JDBCT.getConn();
		
		System.out.print("회원 번호 : ");
		int num = Integer.parseInt(Main.SC.nextLine());
		String sql ="SELECT * FROM PRODUCT_ORDER WHERE NO = ? AND CANCEL_YN = 'N'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, num);
		ResultSet rs = pstmt.executeQuery();
		String status = null;
		
		ProductOrderVo vo = null;
		if(rs.next()) {
			String no = rs.getString("NO");
			String mem_no = rs.getString("MEM_NO");
			String product_no = rs.getString("PRODUCT_NO");
			String status_no = rs.getString("STATUS_NO");
			String product_cnt = rs.getString("PRODUCT_CNT");
			String address = rs.getString("ADDRESS");
			String cancel_yn = rs.getString("CANCEL_YN");
			String price = rs.getString("PRICE");
			String estimate_time = rs.getString("ESTIMATE_TIME");
			
			
			vo = new ProductOrderVo(no, mem_no, product_no, status_no, product_cnt,
					address, cancel_yn, price, estimate_time);
			
			System.out.println(vo);
		}
		if(vo == null) {
			System.out.println("배송 정보가 없습니다.");
			return;
		}
		if(vo.getStatus_no().equals("1")) {
			status = "배송중";
		}else if(vo.getStatus_no().equals("2")) {
			status = "배송완료";
		}
        System.out.print("주문 번호 : " + vo.getNo());
        System.out.print("  ");
        System.out.print("주문한 물건 번호 : " + vo.getProduct_no());
        System.out.print("  ");
        System.out.print("배송 상태 :  " + status);
	}
}
