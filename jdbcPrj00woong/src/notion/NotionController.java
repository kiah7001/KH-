package notion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import main.Main;
import util.JDBCTemplet;

public class NotionController {

	public void printMenu() throws Exception {
		System.out.println("=== Notion Table ===");
		
		System.out.println("1. 공지 작성");
		System.out.println("2. 공지 수정 (제목)");
		System.out.println("3. 공지 수정 (내용)");
		System.out.println("4. 공지 상세 조회");
		System.out.println("5. 공지 삭제");
		System.out.println();
		
		System.out.print("번호를 입력하세요 : ");
		String input = Main.SC.nextLine();
		
		switch(input) {
		case "1" : write(); break;
		case "2" : editTitle(); break;
		case "3" : editContent(); break;
		case "4" : selectBoardOne(); break;
		case "5" : delete(); break;
		}
		
		
	}//method
	
	// 공지 작성
	private void write() throws Exception {
		//conn 준비
		Connection conn = JDBCTemplet.getConn();
		
		//sql 준비
		String sql = "INSERT INTO NOTION(NO, TITLE, CONTENT) VALUES(SEQ_NOTION_NO.NEXTVAL , ? , ?)";
		
		System.out.print("제목 : ");
		String title = Main.SC.nextLine();
		System.out.print("내용: ");
		String content = Main.SC.nextLine();
		
		//sql 실행
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, title);
		pstmt.setString(2, content);
		int result = pstmt.executeUpdate();
		
		//결과 처리
		if(result != 1){
			System.out.println("공지 작성 실패 ...");
			return;
		}
		System.out.println("공지 작성 성공 !");
		System.out.println();
		
	}//method
	
	//공지 수정 (제목)
	private void editTitle() throws Exception {
		//conn 준비
		Connection conn = JDBCTemplet.getConn();
		
		System.out.print("수정할 번호를 입력하세요 : ");
		String no = Main.SC.nextLine();
		System.out.println();
		System.out.print("수정할 제목을 입력하세요 : ");
		String title = Main.SC.nextLine();
		
		//sql
		String sql = "UPDATE NOTION SET TITLE = ? WHERE NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, title);
		pstmt.setString(2, no);
		int result = pstmt.executeUpdate();
		
		//rs
		if(result != 1) {
			System.out.println("공지 제목 작성 실패...");
			return;
		}
		System.out.println("공지 제목 수정 완료!");
		System.out.println();
		
		// 수정 전 제목 조회
		String sqlSelectBefore = "SELECT TITLE FROM NOTION WHERE NO = ?";
		PreparedStatement pstmtSelectBefore = conn.prepareStatement(sqlSelectBefore);
		pstmtSelectBefore.setString(1, no);
		ResultSet rsBefore = pstmtSelectBefore.executeQuery();
		String beforeTitle = "";
		if (rsBefore.next()) {
		    beforeTitle = rsBefore.getString("TITLE");
		}

		// 수정된 공지사항 출력
		String sqlSelect = "SELECT * FROM NOTION WHERE NO = ?";
		PreparedStatement pstmtSelect = conn.prepareStatement(sqlSelect);
		pstmtSelect.setString(1, no);
		ResultSet rs = pstmtSelect.executeQuery();

		if (rs.next()) {
		    String modifiedTitle = rs.getString("TITLE");
		    System.out.println("바뀌기 전 제목: " + beforeTitle);
		    System.out.println("바뀐 제목: " + modifiedTitle);
		    System.out.println();
		} else {
		    System.out.println("수정된 공지 제목을 불러올 수 없습니다.");
		}
		
	}//method
	
	//공지 수정 (내용)
	private void editContent() throws Exception {
	
		//conn
		Connection conn = JDBCTemplet.getConn();
		
		System.out.print("공지 번호를 입력하세요 : ");
		String no = Main.SC.nextLine();
		System.out.println();
		System.out.print("수정할 내용을 입력하세요 : ");
		String content = Main.SC.nextLine();
		
		//sql
		String sql = "UPDATE NOTION SET CONTENT = ? WHERE NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, content);
		pstmt.setString(2, no);
		int result = pstmt.executeUpdate();
		
		
		//result
		if(result != 1) {
			System.out.println("공지 수정 실패 ...");
			return;
		}
		System.out.println("공지 수정 성공 !");
		System.out.println();
	}
		
		
	
	
	//공지 상세 조회
	private void selectBoardOne() throws Exception {
		
		//conn
		Connection conn = JDBCTemplet.getConn();
		
		System.out.print("상세조회할 공지 번호 : ");
		String num = Main.SC.nextLine();
		
		//sql
		String sql = "SELECT * FROM NOTION WHERE NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, num);
		ResultSet rs = pstmt.executeQuery();
		
		//rs
		NotionVo vo = null;
		if(rs.next()) {
			String No = rs.getString("NO");
			String Title = rs.getString("TITLE");
			String Content = rs.getString("CONTENT");
			String Enroll_date = rs.getString("ENROLL_DATE");
			
			vo = new NotionVo(No, Title, Content, Enroll_date, Enroll_date);
		
		}
		if(vo == null) {
			System.out.println("공지 상세 조회 실패..");
			System.out.println();
			return;
		}
		
		System.out.println("공지 상세 조회 성공");
		System.out.println("-------------");
		System.out.println("번호 : " + vo.getNo());
		System.out.println("제목 : " + vo.getTitle());
		System.out.println("작성일시 : " + vo.getEnroll_date());
		System.out.println("내용 : " + vo.getContent());
		System.out.println("-------------");
		

	}
	
	//공지 삭제
	private void delete() throws Exception {
		//conn
		Connection conn = JDBCTemplet.getConn();
		
		System.out.print("삭제할 공지 번호 :");
		String no = Main.SC.nextLine();
		
		//sql
		String sql = "DELETE NOTION WHERE NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, no);
		int result = pstmt.executeUpdate();
		
		//result
		if(result != 1) {
			System.out.println("게시글 삭제 실패 ...");
			return;
		}
		System.out.println("게시글 삭제 성공 !");
		System.out.println();
	}
	

		
	}
	
	
	










































