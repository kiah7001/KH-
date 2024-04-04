package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import main.Main;
import util.JDBCT;

public class MemberController {

	// 메뉴
	public void printMenu() throws Exception {
		
		System.out.println("1. 회원가입");
		System.out.println("2. 로그인");
		System.out.println("3. 로그아웃");
		System.out.println("4. 비밀번호 변경");
		System.out.println("5. 닉네임 변경");
		System.out.println("6. 주소 변경");
		System.out.println("7. 회원 탈퇴");
		System.out.println("8. 회원 정보 목록 조회 (관리자)");
		System.out.println("9. 회원 정보 상세 조회 (관리자)");
		System.out.println("0. 이전으로 돌아가기");
		System.out.print("메뉴 번호 입력 : ");
		String num = Main.SC.nextLine();
	
		switch(num) {
		case "1" : join(); break;
		case "2" : login(); break;
		case "3" : logout(); break;
		case "4" : pwdChange(); break;
		case "5" : nickChange(); break;
		case "6" : addressChange(); break;
		case "7" : secession(); break;
		case "8" : select(); break;
		case "9" : selectMemberOne(); break;
		case "0" : System.out.println("돌아가기"); return;
		default : System.out.println("잘못된 입력입니다");
		}
		
	}
	
	// 회원가입
	private void join() throws Exception {
		
		Connection conn = JDBCT.getConn();
		
		System.out.print("아이디 : ");
		String id = Main.SC.nextLine();
		System.out.print("비밀번호 : ");
		String pwd = Main.SC.nextLine();
		System.out.print("닉네임 : ");
		String nick = Main.SC.nextLine();
		System.out.print("주소 : ");
		String address = Main.SC.nextLine();
		
		MemberVo inputVo = new MemberVo();
		inputVo.setId(id);
		inputVo.setPwd(pwd);
		inputVo.setNick(nick);
		inputVo.setAddress(address);
		
		MemberDao dao = new MemberDao();
		int result = dao.join(conn, inputVo);
		
		if ( result != 1 ) {
			System.out.println("회원가입 실패");
			return;
		}
		conn.commit();
		System.out.println("회원가입 성공 👍");
	}
	
	// 로그인
	private void login() throws Exception {
		Connection conn = JDBCT.getConn();
		
		System.out.print("아이디 : ");
		String inputId = Main.SC.nextLine();
		System.out.print("비밀번호 :");
		String inputPwd = Main.SC.nextLine();
		
		MemberVo inputVo = new MemberVo();
		inputVo.setId(inputId);
		inputVo.setPwd(inputPwd);
		
		MemberDao dao = new MemberDao();
		ResultSet rs = dao.login(conn, inputVo);
		
		// rs
		MemberVo vo = null;
		if ( rs.next() ) {
			String no = rs.getString("NO");
			String id = rs.getString("ID");
			String pwd = rs.getString("PWD");
			String nick = rs.getString("NICK");
			String address = rs.getString("ADDRESS");
			String quitYn = rs.getString("QUIT_YN");
			String enrollDate = rs.getString("ENROLL_DATE");
			
			vo = new MemberVo(no, id, pwd, nick, address, quitYn, enrollDate, null);
		}
		if (vo == null ) {
			System.out.println("로그인 실패");
			return;
		}
		Main.loginManager = vo;
	}
	
	// 로그아웃
	private void logout () {
		Main.loginManager = null;
		System.out.println("로그아웃 완료");
	}
	
	// 비밀번호 변경
	private void pwdChange() throws Exception {
		
		if(Main.loginManager == null) {
			System.out.println("로그인 후 이용가능합니다.");
			return;
		}
		
		//conn
		Connection conn = JDBCT.getConn();
		
		System.out.print("변경할 비밀번호 : ");
		String pwd = Main.SC.nextLine();
		System.out.print("변경할 정보의 아이디 : ");
		String id = Main.SC.nextLine();
		
		MemberVo inputVo = new MemberVo();
		inputVo.setPwd(pwd);
		inputVo.setId(id);
		
		MemberDao dao = new MemberDao();
		int result = dao.pwdChange(conn, inputVo);
		
		//rs
		if ( result != 1 ) {
			System.out.println("비밀번호 변경 실패");
			return;
		}
		conn.commit();
		System.out.println("비밀번호 변경 완료 👍");
		
	}
	// 닉네임 변경
	private void nickChange() throws Exception {
		if(Main.loginManager == null) {
			System.out.println("로그인 후 이용가능합니다.");
			return;
		}
		
		//conn
		Connection conn = JDBCT.getConn();
		
		System.out.print("변경할 닉네임 : ");
		String nick = Main.SC.nextLine();
		System.out.print("변경할 정보의 아이디 : ");
		String id = Main.SC.nextLine();
		
		MemberVo inputVo = new MemberVo();
		inputVo.setNick(nick);
		inputVo.setId(id);
		
		MemberDao dao = new MemberDao();
		int result = dao.nickChange(conn, inputVo);
		
		//rs
		if( result != 1 ) {
			System.out.println("닉네임 변경 실패");
			return;
		}
		conn.commit();
		System.out.println("닉네임 변경 완료 👍");		
	}
	// 주소 변경
	private void addressChange() throws Exception {
		if(Main.loginManager == null) {
			System.out.println("로그인 후 이용가능합니다.");
			return;
		}
		//conn
		Connection conn = JDBCT.getConn();
		
		System.out.print("변경할 주소 : ");
		String address = Main.SC.nextLine();
		System.out.print("변경할 정보의 아이디 : ");
		String id = Main.SC.nextLine();
		
		MemberVo inputVo = new MemberVo();
		inputVo.setAddress(address);
		inputVo.setId(id);
		
		MemberDao dao = new MemberDao();
		int result = dao.addressChange(conn, inputVo);
		
		//rs
		if( result != 1 ) {
			System.out.println("주소 변경 실패");
			return;
		}
		conn.commit();
		System.out.println("주소 변경 완료 👍");
		
	}
	
	// 회원 탈퇴
	private void secession() throws Exception {
		if(Main.loginManager == null) {
			System.out.println("로그인 후 이용가능합니다.");
			return;
		}
		//conn
		Connection conn = JDBCT.getConn();
		
		System.out.print("회원 탈퇴 할 유저의 아이디 : ");
		String id = Main.SC.nextLine();
		System.out.print("회원 탈퇴 할 유저의 비밀번호 : ");
		String pwd = Main.SC.nextLine();
		
		MemberVo inputVo = new MemberVo();
		inputVo.setId(id);
		inputVo.setPwd(pwd);
		
		MemberDao dao = new MemberDao();
		int result = dao.addressChange(conn, inputVo);
		
		//rs
		if( result != 1 ) {
			System.out.println("회원 탈퇴 실패");
			return;
		}
		conn.commit();
		System.out.println("회원 탈퇴 완료 👍");
	}
	// 회원 정보 목록 조회 (관리자)
	private void select() throws Exception {
		
		if( Main.loginManager.getId().equals("admin")) {
			System.out.println("관리자 로그인");
			
			// conn
			Connection conn = JDBCT.getConn();
			
			String sql = "SELECT * FROM MEMBER WHERE QUIT_YN = 'N'";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			//rs
			ArrayList<MemberVo> voList = new ArrayList<MemberVo>();
			while( rs.next() ) {
				String no = rs.getString("NO");
				String id = rs.getString("ID");
				String pwd = rs.getString("PWD");
				String nick = rs.getString("NICK");
				String address = rs.getString("ADDRESS");
				String quitYn = rs.getString("QUIT_YN");
//				String enrollDate = rs.getString("ENROLL_DATE");
//				String adminYn = rs.getString("ADMIN_YN");
				
				MemberVo vo = new MemberVo(no, id, pwd, nick, address, quitYn, null, null);
				voList.add(vo);
			}
			
			System.out.println("--------------------------------");
			System.out.print("번호");
			System.out.print(" | ");
			System.out.print("아이디");
			System.out.print(" | ");
			System.out.print("비밀번호");
			System.out.print(" | ");
			System.out.print("닉네임");
			System.out.print(" | ");
			System.out.print("주소");
			System.out.print(" | ");
			System.out.print("탈퇴여부");
			System.out.println();
			
			for(MemberVo vo : voList) {
				System.out.print(vo.getNo());
				System.out.print(" | ");
				System.out.print(vo.getId());
				System.out.print(" | ");
				System.out.print(vo.getPwd());
				System.out.print(" | ");
				System.out.print(vo.getNick());
				System.out.print(" | ");
				System.out.print(vo.getAddress());
				System.out.print(" | ");
				System.out.print(vo.getQuitYn());
				System.out.println();
			}
			System.out.println("--------------------------------");
			
		}
		else {
			System.out.println("관리자만 조회 가능합니다.");
		}
	
	}
	
	// 회원 정보 상세 조회 (관리자)
	private void selectMemberOne() throws Exception {
		if( Main.loginManager.getId().equals("admin")) {
			System.out.println("관리자 로그인");
			//conn
			Connection conn = JDBCT.getConn();
			
			System.out.print("조회 할 회원 번호 : ");
			String inputNo = Main.SC.nextLine();
			
			String sql = "SELECT * FROM MEMBER WHERE NO = ? AND QUIT_YN = 'N'";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, inputNo);
			
			ResultSet rs = pstmt.executeQuery();
			
			//rs
			MemberVo vo = null;
			if( rs.next() ) {
				String no = rs.getString("NO");
				String id = rs.getString("ID");
				String pwd = rs.getString("PWD");
				String nick = rs.getString("NICK");
				String address = rs.getString("ADDRESS");
				String quitYn = rs.getString("QUIT_YN");
				String enrollDate = rs.getString("ENROLL_DATE");
				
				vo = new MemberVo(no, id, pwd, nick, address, quitYn, enrollDate, null );
			}
			if( vo == null ) {
				System.out.println("회원정보 상세조회 실패...");
				return;
			}
			System.out.println("회원정보 상세조회 성공 👍");
			System.out.println("-------------------");
			System.out.println("번호 : "+vo.getNo());
			System.out.println("아이디 : "+vo.getId());
			System.out.println("비밀번호 : "+vo.getPwd());
			System.out.println("닉네임 : "+vo.getNick());
			System.out.println("주소 : "+vo.getAddress());
			System.out.println("탈퇴 여부 : "+vo.getQuitYn());
			System.out.println("가입 일시 : "+vo.getEnrollDate());
			System.out.println("-------------------");
		}else {
			System.out.println("관리자만 조회 가능합니다.");
		}
		
	}
	
	
}//class

