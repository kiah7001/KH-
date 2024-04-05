package product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import main.Main;
import util.JDBCT;

public class ProductController {
	public void printMenu() throws Exception {
		while(true) {
			System.out.println("===== 제품 페이지=====");
			System.out.println("1. 제품 등록");
			System.out.println("2. 품목 조회(품번순)");
			System.out.println("3. 제품 상세 조회");
			System.out.println("4. 제품 삭제 ");
			System.out.println("0.이전으로 돌아가기");
			int input = Integer.parseInt(Main.SC.nextLine());
			
			switch(input) {
			case 1 : productAdd(); break;
			case 2 : selectProduct(); break;
			case 3 :selectProductOne(); break;
			case 4 : ProductDelete(); break;
			case 0 : System.out.println("돌아가기"); return;
			default : System.out.println("잘못된 선택입니다.");
			}
			
			
		}
	}
	
	public void productAdd() throws Exception{
		Connection conn = JDBCT.getConn();		
		
		System.out.print("음료 이름 : ");
		String drink_name = Main.SC.nextLine();
		System.out.print("음료 가격 : ");
		String price = Main.SC.nextLine();
		System.out.print("재고량 : ");
		String product_stock = Main.SC.nextLine();
		
		String sql = "INSERT INTO PRODUCT(NO,DRINK_NAME,PRICE,PRODUCT_STOCK) VALUES(SEQ_PRODUCT_ORDER_NO.NEXTVAL,?,?,?)";
		
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, drink_name);
		pstmt.setString(2, price );
		pstmt.setString(3, product_stock);
		
		int result = pstmt.executeUpdate();
		
		if(result != 1) {
			System.out.println("제품 등록 실패...");
		}
		conn.commit();
		System.out.println("제품 등록 완료!");
	}
	
	private void selectProduct() throws Exception{
		
		Connection conn = JDBCT.getConn();		
		
		String sql = "SELECT NO,DRINK_NAME FROM PRODUCT  WHERE DEL_YN = 'N' ORDER BY NO ASC";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		List<ProductVo> voList = new ArrayList<ProductVo>();
		while(rs.next()) {
			String no = rs.getString("NO");
			String drinkName = rs.getString("drink_name");
			
			
			ProductVo vo= new ProductVo(no, drinkName, null, null, null, null);
			
            voList.add(vo);
			
		}
        for(ProductVo vo : voList) {
		System.out.print("번호 : "+ vo.getNo());
		System.out.print("  |  ");
		System.out.print("이름 : " + vo.getDrinkName());
		System.out.println();
	}
}
		
		private void selectProductOne() throws Exception{
			Connection conn = JDBCT.getConn();
			
			System.out.print("조회할 물품 번호 : ");
			String num = Main.SC.nextLine();
			
			String sql = "SELECT * FROM PRODUCT WHERE NO = ? AND DEL_YN = 'N'";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			ResultSet rs = pstmt.executeQuery();
			
			ProductVo vo = null;			
			if(rs.next()) {
				String no = rs.getString("NO");
				String drink_name = rs.getString("DRINK_NAME");
				String price = rs.getString("PRICE");
				String product_stock = rs.getString("product_stock");
				String regis_date = rs.getString("REGIS_DATE");
				String del_yn = rs.getString("DEL_YN");
				
				vo = new ProductVo(no, drink_name, price, product_stock, regis_date, del_yn);
			}
			if(vo == null) {
				System.out.println("물품 상세조회 실패...");
				return;
			}
			System.out.println("상세 조회한 물품 정보");
			System.out.println("--------------------------");
			System.out.print("번호 : " + vo.getNo());
			System.out.print(" | ");
			System.out.print("이름 : " + vo.getDrinkName());
			System.out.print(" | ");
			System.out.print("가격 : " + vo.getPrice());
			System.out.print(" | ");
			System.out.print("재고 : " + vo.getProductStock());
			System.out.print(" | ");
			System.out.print("입고일 : " + vo.getRegisDate());
			System.out.print(" | ");
			System.out.print("제고 취소여부 : " + vo.getDelYn());
			System.out.println("--------------------------");			
		}
		private void ProductDelete() throws Exception{
			Connection conn = JDBCT.getConn();
			
			System.out.print("삭제할 물품 번호 : ");
			String num = Main.SC.nextLine();
			
			String sql = "UPDATE PRODUCT SET DEL_YN = 'Y' WHERE NO = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			int result = pstmt.executeUpdate();
			
			if(result != 1) {
				System.out.println("물품 삭제 실패..");
			}
			conn.commit();
			System.out.println("물품 삭제 완료");		
		}		
}