package review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import main.Main;
import util.JDBCT;

public class ReviewController {

	// 메뉴
	public void printMenu() throws Exception {
		System.out.println("1. 후기 작성");
		System.out.println("2. 후기 제목 수정");
		System.out.println("3. 후기 내용 수정");
		System.out.println("4. 후기 삭제");
		System.out.println("5. 상품 후기 목록 조회");
		System.out.println("0. 돌아가기");

		String num = Main.SC.nextLine();

		switch (num) {
		case "1": write(); break;
		case "2": modifyTitle(); break;
		case "3": modifyContent(); break;
		case "4" : delete(); break;
		case "5" : selectReviewList(); break;
		case "0": System.out.println("돌아가기"); return;
		default: System.out.println("잘못 입력하셨습니다.");
		}
	}

	// 후기작성
	private void write() throws Exception {
		if (Main.loginManager == null) {
			System.out.println("로그인 후 이용가능합니다.");
			return;
		}
		// conn
		Connection conn = JDBCT.getConn();

		System.out.print("후기 남기실 제품 번호 : ");
		String productNo = Main.SC.nextLine();
		System.out.print("후기 제목 : ");
		String title = Main.SC.nextLine();
		System.out.print("후기 내용 : ");
		String content = Main.SC.nextLine();

		ReviewVo inputVo = new ReviewVo();
		inputVo.setProductNo(productNo);
		inputVo.setTitle(title);
		inputVo.setContent(content);

		ReviewDao dao = new ReviewDao();
		int result = dao.write(conn, inputVo);

		// rs
		if (result != 1) {
			System.out.println("후기 작성 실패");
			return;
		}
		conn.commit();
		System.out.println("후기 작성 완료 !");
	}

	// 후기 수정 (제목)
	private void modifyTitle() throws Exception {
		if (Main.loginManager == null) {
			System.out.println("로그인 후 이용가능합니다.");
			return;
		}
		// conn
		Connection conn = JDBCT.getConn();

		System.out.print("변경할 제목 : ");
		String title = Main.SC.nextLine();
		System.out.print("변경할 후기의 번호 : ");
		String no = Main.SC.nextLine();

		ReviewVo inputVo = new ReviewVo();
		inputVo.setTitle(title);
		inputVo.setNo(no);

		ReviewDao dao = new ReviewDao();
		int result = dao.modifyTitle(conn, inputVo);

		// rs
		if (result != 1) {
			System.out.println("제목 수정 실패");
			return;
		}
		conn.commit();
		System.out.println("제목 수정 완료 !");
	}

	// 후기 수정 (내용)
	private void modifyContent() throws Exception {
		if (Main.loginManager == null) {
			System.out.println("로그인 후 이용가능합니다.");
			return;
		}

		// conn
		Connection conn = JDBCT.getConn();

		System.out.print("변경할 내용 : ");
		String content = Main.SC.nextLine();
		System.out.print("변경할 후기의 번호 : ");
		String no = Main.SC.nextLine();

		ReviewVo inputVo = new ReviewVo();
		inputVo.setContent(content);
		inputVo.setNo(no);

		ReviewDao dao = new ReviewDao();
		int result = dao.modifyContent(conn, inputVo);

		// rs
		if (result != 1) {
			System.out.println("내용 수정 실패");
			return;
		}
		conn.commit();
		System.out.println("내용 수정 완료 !");
	}

	// 후기 삭제
	private void delete() throws Exception {
		if (Main.loginManager == null) {
			System.out.println("로그인 후 이용가능합니다.");
			return;
		}
		
		// conn
		Connection conn = JDBCT.getConn();
		
		System.out.print("삭제할 후기 번호 : ");
		String no = Main.SC.nextLine();
		
		ReviewVo inputVo = new ReviewVo();
		inputVo.setNo(no);
		
		ReviewDao dao = new ReviewDao();
		int result = dao.delet(conn, inputVo);
		
		//rs
		if ( result != 1 ) {
			System.out.println("후기 삭제 실패");
			return;
		}
		conn.commit();
		System.out.println("후기 삭제 완료 !");
	}

	// 상품 후기 조회
	private void selectReviewList() throws Exception {
		if (Main.loginManager == null) {
			System.out.println("로그인 후 이용가능합니다.");
			return;
		}
		
		// conn
		Connection conn = JDBCT.getConn();
		
		System.out.println("조회할 상품 번호 : ");
		String inputNo = Main.SC.nextLine();
		
		// SQL
		String sql = "SELECT R.NO ,R.PRODUCT_NO , M.NICK  , R.TITLE , R.CONTENT FROM REVIEW R JOIN MEMBER M ON R.WRITER_NO = M.NO WHERE R.PRODUCT_NO = ? AND DEL_YN = 'N'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, inputNo);
		
		ResultSet rs = pstmt.executeQuery();
		
		ArrayList<ReviewVo> voList = new ArrayList<ReviewVo>();
		while(rs.next()) {
			String no = rs.getString("NO");
			String productNo = rs.getString("PRODUCT_NO");
			String writerNo = rs.getString("NICK");
			String title = rs.getString("TITLE");
			String content = rs.getString("CONTENT");
//			String enrollDate = rs.getString("DEL_YN");
			
			ReviewVo vo = new ReviewVo(no, productNo, writerNo, title, content, null, null);
			voList.add(vo);
		}
		System.out.println("---------------------------------------");
		System.out.print("번호");
		System.out.print(" | ");
		System.out.print("제품 번호");
		System.out.print(" | ");
		System.out.print("작성자");
		System.out.print(" | ");
		System.out.print("제목");
		System.out.print(" | ");
		System.out.print("내용");
		System.out.println();
		
		for ( ReviewVo x : voList ) {
			System.out.print(x.getNo());
			System.out.print(" | ");
			System.out.print(x.getProductNo());
			System.out.print(" | ");
			System.out.print(x.getWriterNo());
			System.out.print(" | ");
			System.out.print(x.getTitle());
			System.out.print(" | ");
			System.out.print(x.getContent());
			System.out.println();
		}
		System.out.println("---------------------------------------");
	}

} //class