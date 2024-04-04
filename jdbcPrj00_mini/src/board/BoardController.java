package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import main.Main;
import util.JDBCT;

public class BoardController {

	//메뉴
	public void printMenu() throws Exception {
		System.out.println("1. 게시글 작성");
		System.out.println("2. 게시글 목록 조회(최신순)");
		System.out.println("3. 게시글 목록 조회(오래된 순)");
		System.out.println("4. 게시글 목록 조회(조회수 높은 순)");
		System.out.println("5. 게시글 상세 조회");
		System.out.println("6. 게시글 검색 (제목)");
		System.out.println("7. 게시글 검색 (내용)");
		System.out.println("8. 게시글 수정(제목)");
		System.out.println("9. 게시글 수정(내용)");
		System.out.println("10. 게시글 삭제");
		System.out.println("0. 돌아가기");
		
		System.out.println("원하는 번호 입력 : ");
		String num = Main.SC.nextLine();
		
		switch (num) {
		case "1" : writer(); break;
		case "2" : selectBoardOrderByNoDesc(); break;
		case "3" : selectBoardOrderByAsc(); break;
		case "4" : selectBoardHit(); break;
		case "5" : selectBoardByNo(); break;
		case "6" : searchByTitle(); break;
		case "7" : searchByContent(); break;
		case "8" : editTitle(); break;
		case "9" : editContent(); break;
		case "10" : delete(); break;
		case "0" : System.out.println("돌아가기.");; return;
		default : System.out.println("잘못된 입력...");
		}
		
	}
	
    // 게시글 작성
	private void writer() throws Exception {
		//validate
		if(Main.loginManager == null) {
			System.out.println("로그인 후 진행해주세요");
			return;
		}
		
		//conn
		Connection conn = JDBCT.getConn();
		
		System.out.print("제목 : ");
		String title = Main.SC.nextLine();
		System.out.print("내용 : ");
		String content = Main.SC.nextLine();
		
		
		//sql
		String sql = "INSERT INTO BOARD(NO, TITLE, CONTENT, WRITER_NO) VALUES(SEQ_BOARD_NO.NEXTVAL , ?, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, title);
		pstmt.setString(2, content);
		pstmt.setString(3, Main.loginManager.getNo());
		int result = pstmt.executeUpdate();
		
		
		//result
		if(result != 1) {
			System.out.println("게시글 작성 실패...");
			return;
		}
		conn.commit();
		System.out.println("게시글 작성 성공.");
		
		
	}
 
    // 게시글 목록 조회(최신순)
	private void selectBoardOrderByNoDesc() throws Exception {
		
		// conn
		Connection conn = JDBCT.getConn();
		
		//sql
		String sql = "SELECT B.NO, B.TITLE,B.HIT, M.NICK FROM BOARD B JOIN MEMBER M ON M.NO = B.writer_NO ORDER BY NO DESC";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		//rs
		ArrayList<BoardVo> voList = new ArrayList<BoardVo>();
		while( rs.next() ) {
			String no = rs.getString("NO");
			String title = rs.getString("TITLE");
//			String content = rs.getString("CONTENT");
//			String writerNo = rs.getString("WRITER_NO");
			String writerNo = rs.getString("NICK");
			String hit = rs.getString("HIT");
//			String enrollDate = rs.getString("ENROLL_DATE");
//			String modifyDate = rs.getString("MODIFY_DATE");
//			String delYn = rs.getString("DEL_YN");
			
			BoardVo vo = new BoardVo(no, title, null, writerNo, hit, null, null, null);
			voList.add(vo); // ArratList에 계속 추가해주는 거임
		
		}
		System.out.println("-------------------------------");
		System.out.print("번호");
		System.out.print(" | ");
		System.out.print("제목");
		System.out.print(" | ");
		System.out.print("조회수");
		System.out.print(" | ");
		System.out.print("작성자");
		System.out.println();
		for(BoardVo x : voList) {
			System.out.print(x.getNo());
			System.out.print(" | ");
			System.out.print(x.getTitle());
			System.out.print(" | ");
			System.out.print(x.getHit());
			System.out.print(" | ");
			System.out.print(x.getWriter_no());
			System.out.println();
		}
		System.out.println("-------------------------------");
		
	}//method
	
	
    // 게시글 목록 조회(오래된순)
	private void selectBoardOrderByAsc() throws Exception {
		
		// conn
		Connection conn = JDBCT.getConn();
		
		//sql
		String sql = "SELECT B.NO, B.TITLE,B.HIT, M.NICK FROM BOARD B JOIN MEMBER M ON M.NO = B.WRITER_NO ORDER BY NO ASC";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		//rs
		ArrayList<BoardVo> voList = new ArrayList<BoardVo>();
		while( rs.next() ) {
			String no = rs.getString("NO");
			String title = rs.getString("TITLE");
//			String content = rs.getString("CONTENT");
//			String writerNo = rs.getString("WRITER_NO");
			String writerNo = rs.getString("NICK");
			String hit = rs.getString("HIT");
//			String enrollDate = rs.getString("ENROLL_DATE");
//			String modifyDate = rs.getString("MODIFY_DATE");
//			String delYn = rs.getString("DEL_YN");
			
			BoardVo vo = new BoardVo(no, title, null, writerNo, hit, null, null, null);
			voList.add(vo); // ArratList에 계속 추가해주는 거임
		
		}
		System.out.println("-------------------------------");
		System.out.print("번호");
		System.out.print(" | ");
		System.out.print("제목");
		System.out.print(" | ");
		System.out.print("조회수");
		System.out.print(" | ");
		System.out.print("작성자");
		System.out.println();
		for(BoardVo x : voList) {
			System.out.print(x.getNo());
			System.out.print(" | ");
			System.out.print(x.getTitle());
			System.out.print(" | ");
			System.out.print(x.getHit());
			System.out.print(" | ");
			System.out.print(x.getWriter_no());
			System.out.println();
		}
		System.out.println("-------------------------------");
		
	}//method
	
	
    // 게시글 목록 조회(조회수)
	private void selectBoardHit() throws Exception {
		
		// conn
		Connection conn = JDBCT.getConn();
		
		//sql
		String sql = "SELECT B.NO, B.TITLE,B.HIT, M.NICK FROM BOARD B JOIN MEMBER M ON M.NO = B.WRITER_NO ORDER BY HIT DESC";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		//rs
		ArrayList<BoardVo> voList = new ArrayList<BoardVo>();
		while( rs.next() ) {
			String no = rs.getString("NO");
			String title = rs.getString("TITLE");
//			String content = rs.getString("CONTENT");
//			String writerNo = rs.getString("WRITER_NO");
			String writerNo = rs.getString("NICK");
			String hit = rs.getString("HIT");
//			String enrollDate = rs.getString("ENROLL_DATE");
//			String modifyDate = rs.getString("MODIFY_DATE");
//			String delYn = rs.getString("DEL_YN");
			
			BoardVo vo = new BoardVo(no, title, null, writerNo, hit, null, null, null);
			voList.add(vo); // ArratList에 계속 추가해주는 거임
		
		}
		System.out.println("-------------------------------");
		System.out.print("번호");
		System.out.print(" | ");
		System.out.print("제목");
		System.out.print(" | ");
		System.out.print("조회수");
		System.out.print(" | ");
		System.out.print("작성자");
		System.out.println();
		for(BoardVo x : voList) {
			System.out.print(x.getNo());
			System.out.print(" | ");
			System.out.print(x.getTitle());
			System.out.print(" | ");
			System.out.print(x.getHit());
			System.out.print(" | ");
			System.out.print(x.getWriter_no());
			System.out.println();
		}
		System.out.println("-------------------------------");
		
	}//method
	
	
	
    
	// 게시글 상세 조회 
	private void selectBoardByNo() throws Exception {
		//conn
		Connection conn = JDBCT.getConn();
		
		System.out.print("상세조회할 게시글 번호 : ");
		String no = Main.SC.nextLine();
		
		BoardDao dao = new BoardDao();
		
		int result = dao.increaseHit(conn, no);
		if(result != 1) {
			throw new Exception("게시글 상세 조회 중 에러 발생 (조회수 증가 실패)");
		}
		conn.commit();
		
		BoardVo vo = dao.selectBoardByNo(conn, no);
		
		System.out.println("게시글 상세 조회 성공 !");
		System.out.println("-------------");
		System.out.println("제목 : " + vo.getTitle());
		System.out.println("작성자 : " + vo.getWriter_no());
		System.out.println("조회수 : " + vo.getHit());
		System.out.println("작성일시 : " + vo.getEnroll_date());
		System.out.println("내용 : " + vo.getContent());
		System.out.println("-------------");
		
		
	}//method

	// 게시글 검색 (제목으로)
	private void searchByTitle() throws Exception {
		
		//conn
		Connection conn = JDBCT.getConn();
		
		System.out.print("검색할 제목 : ");
		String value = Main.SC.nextLine();
		
		//sql
		String sql = "SELECT * FROM BOARD WHERE TITLE LIKE '%' || ? || '%'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, value);
		ResultSet rs = pstmt.executeQuery();
		
		//rs
		List<BoardVo> voList = new ArrayList<BoardVo>();
		while( rs.next() ) {
			String no = rs.getString("NO");
			String title = rs.getString("TITLE");
			String content = rs.getString("CONTENT");
			String hit = rs.getString("HIT");
			
			BoardVo vo = new BoardVo(no, title, content, null , hit, null, null, null);
			voList.add(vo);
		}
		
		System.out.print("번호");
		System.out.print(" | ");
		System.out.print("제목");
		System.out.print(" | ");
		System.out.print("내용");
		System.out.print(" | ");
		System.out.print("조회수");
		System.out.println();
		
		for (BoardVo vo : voList) {
			System.out.print(vo.getNo());
			System.out.print(" | ");
			System.out.print(vo.getTitle());
			System.out.print(" | ");
			System.out.print(vo.getContent());
			System.out.print(" | ");
			System.out.print(vo.getHit());
			System.out.println();
		}
		
	}
	
	// 게시글 검색 (내용으로)
	private void searchByContent() throws Exception {
		
		//conn
		Connection conn = JDBCT.getConn();
		
		System.out.print("검색할 내용 : ");
		String value = Main.SC.nextLine();
		
		//sql
		String sql = "SELECT * FROM BOARD WHERE TITLE LIKE '%' || ? || '%'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, value);
		ResultSet rs = pstmt.executeQuery();
		
		//rs
		List<BoardVo> voList = new ArrayList<BoardVo>();
		while( rs.next() ) {
			String no = rs.getString("NO");
			String title = rs.getString("TITLE");
			String content = rs.getString("CONTENT");
			String hit = rs.getString("HIT");
			
			BoardVo vo = new BoardVo(no, title, content, null , hit, null, null, null);
			voList.add(vo);
		}
		
		System.out.print("번호");
		System.out.print(" | ");
		System.out.print("제목");
		System.out.print(" | ");
		System.out.print("내용");
		System.out.print(" | ");
		System.out.print("조회수");
		System.out.println();
		
		for (BoardVo vo : voList) {
			System.out.print(vo.getNo());
			System.out.print(" | ");
			System.out.print(vo.getTitle());
			System.out.print(" | ");
			System.out.print(vo.getContent());
			System.out.print(" | ");
			System.out.print(vo.getHit());
			System.out.println();
		}
		
	}
    
	// 게시글 제목 수정 (작성자 또는 관리자 만 가능) (일단 작성자 관리자 제한 조건은 달지 못함) 
	 private void editTitle() throws Exception {
	 	
//		 if( Main.loginManager.getId().equals("admin")) {
//	         System.out.println("관리자 로그인");
//		 }
		//conn
		Connection conn = JDBCT.getConn();
		
		System.out.print("수정할 게시글 번호 : ");
		String no = Main.SC.nextLine();
		System.out.print("수정할 제목 : ");
		String title = Main.SC.nextLine();
		
		//sql
		String sql = "UPDATE BOARD SET TITLE = ?, MODIFY_DATE = SYSDATE WHERE NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, title);
		pstmt.setString(2, no);
		int result = pstmt.executeUpdate();
		
		//결과
		if(result != 1) {
			System.out.println("게시글 수정 실패 ...");
			return;
		}
		conn.commit();
		System.out.println("게시글 수정 성공!");
		
	} //method
	
	
	 // 게시글 내용 수정
	private void editContent() throws Exception {
		
		//conn
		Connection conn = JDBCT.getConn();
		
		System.out.print("게시글 번호 : ");
		String no = Main.SC.nextLine();
		System.out.print("수정할 내용 : ");
		String content = Main.SC.nextLine();
		
		//sql
		String sql = "UPDATE BOARD SET CONTENT = ?, MODIFY_DATE = SYSDATE WHERE NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, content);
		pstmt.setString(2, no);
		int result = pstmt.executeUpdate();
		
		//result
		if(result != 1) {
			System.out.println("게시글 수정 실패 ...");
			return;
		}
		conn.commit();
		System.out.println("게시글 수정 성공 !");
	}
	 
	 
    
	// 게시글 삭제 (작성자 또는 관리자 만 가능)
	private void delete() throws Exception {
		//conn
		Connection conn = JDBCT.getConn();
		
		System.out.print("삭제할 게시글 번호 :");
		String no = Main.SC.nextLine();
		
		//sql
		String sql = "UPDATE BOARD SET DEL_YN = 'Y' , MODIFY_DATE = SYSDATE WHERE NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, no);
		int result = pstmt.executeUpdate();
		
		//result
		if(result != 1) {
			System.out.println("게시글 삭제 실패 ...");
			return;
		}
		conn.commit();
		System.out.println("게시글 삭제 성공 !");
	}
	
	



}
















