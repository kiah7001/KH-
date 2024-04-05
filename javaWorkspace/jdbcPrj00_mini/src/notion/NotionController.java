package notion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import main.Main;
import util.JDBCT;

public class NotionController {

	public void printMenu() throws Exception {
		System.out.println("=== 공지사항(Notion) ===");
		
		System.out.println("1. 공지 작성");
		System.out.println("2. 공지사항 전체 조회");
		System.out.println("3. 공지 수정 (제목)");
		System.out.println("4. 공지 수정 (내용)");
		System.out.println("5. 공지 상세 조회");
		System.out.println("6. 공지 삭제");
		System.out.println("0. 돌아가기");
		System.out.println();
		
		System.out.print("번호 입력 : ");
		String input = Main.SC.nextLine();
		
		switch(input) {
		case "1" : write(); break;
		case "2" : selectNotionOrderByNoDesc(); break;
		case "3" : editTitle(); break;
		case "4" : editContent(); break;
		case "5" : selectBoardOne(); break;
		case "6" : delete(); break;
		case "0" : System.out.println("돌아가기."); return;
		default : System.out.println("잘못된 입력...");
		}
		
	}//method
	
	// 공지 작성
	private void write() throws Exception {
		
		if(Main.loginManager == null) {
			System.out.println("로그인 후 진행");
			return;
		}
		
		if(Main.loginManager.getId().equals("admin")) {
			System.out.println("관리자 로그인");
			
			//conn 준비
			Connection conn = JDBCT.getConn();
			
			//sql 준비
			String sql = "INSERT INTO NOTION(NO, TITLE, CONTENT, WRITER_NO) VALUES(SEQ_NOTION_NO.NEXTVAL , ? , ?, ?)";
			
			System.out.print("제목 : ");
			String title = Main.SC.nextLine();
			System.out.print("내용: ");
			String content = Main.SC.nextLine();
			
			//sql 실행
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString (3, Main.loginManager.getNo());
			int result = pstmt.executeUpdate();
			
			//결과 처리
			if(result != 1){
				System.out.println("공지 작성 실패 ...");
				return;
			}
			conn.commit();
			System.out.println("공지 작성 성공 !");
			System.out.println();
		}else {
			System.out.println("권한이 없습니다.");
			return;
		}
		
		
		
	}//method
	
	// 전체 공지 조회
	private void selectNotionOrderByNoDesc() throws Exception {
		
		// conn
		Connection conn = JDBCT.getConn();
		
		//sql
		String sql = "SELECT NO, TITLE, ENROLL_DATE FROM NOTION ORDER BY NO DESC";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		//rs
		ArrayList<NotionVo> VoList = new ArrayList<NotionVo>();
		while( rs.next() ) {
			String no = rs.getString("NO");
			String title = rs.getString("TITLE");
//			String content = rs.getString("CONTENT");
//			String writerNo = rs.getString("NICK");
			String enrollDate = rs.getString("ENROLL_DATE");
//			String modifyDate = rs.getString("MODIFY_DATE");
//			String delYn = rs.getString("DEL_YN");
			
			NotionVo Vo = new NotionVo(no, title , null, null, enrollDate, null, null);
			VoList.add(Vo); 
		
		}
		System.out.println("-------------------------------");
		System.out.print("번호");
		System.out.print(" | ");
		System.out.print("제목");
		System.out.print(" | ");
		System.out.print("작성 시간");
		System.out.println();
		for(NotionVo x : VoList) {
			System.out.print(x.getNo());
			System.out.print(" | ");
			System.out.print(x.getTitle());
			System.out.print(" | ");
			System.out.print(x.getEnroll_date());
			System.out.println();
		}
		System.out.println("-------------------------------");
		
	}//method
	
	
	
	
	//공지 수정 (제목)
	private void editTitle() throws Exception {
		
		if(Main.loginManager == null) {
			System.out.println("로그인 후 진행해주세요");
			return;
		}
		
		if(Main.loginManager.getId().equals("admin")) {
			System.out.println("관리자 로그인");
			
			//conn 준비
			Connection conn = JDBCT.getConn();
			
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
				System.out.println("공지 제목 작성...");
				return;
			}
			conn.commit();
			System.out.println("공지 제목 수정 완료!");
			System.out.println();
			

		}else {
			System.out.println("권한이 없습니다.");
			return;
		}
			
		
		
	}//method
	
	//공지 수정 (내용)
	private void editContent() throws Exception {
	
		if(Main.loginManager.getId().equals("admin")) {
			System.out.println("관리자 로그인");
		
		//conn
		Connection conn = JDBCT.getConn();
		
		System.out.print("공지 번호 : ");
		String no = Main.SC.nextLine();
		System.out.println();
		System.out.print("수정할 내용 : ");
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
		conn.commit();
		System.out.println("공지 내용 수정 완료!");
		System.out.println();
	
			
		}else {
			System.out.println("권한이 없습니다.");
			return;
		}
		
		}//method
		
		
	
	
	//공지 상세 조회
	private void selectBoardOne() throws Exception {
		
		//conn
		Connection conn = JDBCT.getConn();
		
		System.out.print("상세조회할 공지 번호 : ");
		String num = Main.SC.nextLine();
		
		//sql
		String sql = "SELECT * FROM NOTION WHERE NO = ? AND DEL_YN = 'N'";
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
			String Del_yn = rs.getString("DEL_YN");
			
			vo = new NotionVo(No, Title, Content, null, Enroll_date, null, Del_yn);
		
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
		System.out.println("내용 : " + vo.getContent());
		System.out.println("작성일시 : " + vo.getEnroll_date());
		System.out.println("-------------");
		System.out.println();
		

	}//method
	
	//공지 삭제
	private void delete() throws Exception {
		
		if(Main.loginManager.getId().equals("admin")) {
			System.out.println("관리자 로그인");
		
		//conn
		Connection conn = JDBCT.getConn();
		
		System.out.print("삭제할 공지 번호 :");
		String no = Main.SC.nextLine();
		
		//sql
		String sql = "UPDATE NOTION SET DEL_YN = 'Y' , MODIFY_DATE = SYSDATE WHERE NO = ?";
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
		System.out.println();
	}else {
		System.out.println("권한이 없습니다.");
		return;
	}
	
	}//method
		
	}
	
	
	



