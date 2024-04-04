package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MemberDao {

	// 회원가입
	public int join(Connection conn, MemberVo vo) throws Exception {
		
		String sql = "INSERT INTO MEMBER(NO, ID, PWD, NICK, ADDRESS) VALUES(SEQ_MEMBER_NO.NEXTVAL, ? , ?, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getId());
		pstmt.setString(2, vo.getPwd());
		pstmt.setString(3, vo.getNick());
		pstmt.setString(4, vo.getAddress());
		
		int result = pstmt.executeUpdate();
		
		return result;
	}
	
	// 로그인
	public ResultSet login(Connection conn, MemberVo vo) throws Exception {
		
		String sql = "SELECT * FROM MEMBER WHERE ID = ? AND PWD = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getId());
		pstmt.setString(2, vo.getPwd());
		ResultSet rs = pstmt.executeQuery();
		return rs;
	}
	
	// 비밀번호 변경
	public int pwdChange(Connection conn, MemberVo vo) throws Exception {
		
		String sql = "UPDATE MEMBER SET PWD = ? WHERE ID = ? AND QUIT_YN = 'N'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getPwd());
		pstmt.setString(2, vo.getId());
		
		int result = pstmt.executeUpdate();
		return result;
		
	}
	
	// 닉네임 변경
	
	public int nickChange(Connection conn, MemberVo vo) throws Exception {
		
		String sql = "UPDATE MEMBER SET NICK = ? WHERE ID = ? AND QUIT_YN = 'N'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getNick());
		pstmt.setString(2, vo.getId());
		
		int result = pstmt.executeUpdate();
		return result;
	}
	
	//주소 변경
	
	public int addressChange(Connection conn , MemberVo vo) throws Exception{
		
		String sql = "UPDATE MEMBER SET ADDRESS = ? WHERE ID = ? AND QUIT_YN = 'N'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getAddress());
		pstmt.setString(2, vo.getId());
		
		int result = pstmt.executeUpdate();
		return result;
	}
	
	// 회원 탈퇴
	public int secession(Connection conn, MemberVo vo) throws Exception {
		
		String sql = "UPDATE MEMBER SET QUIT_YN = 'Y' WHERE NO = (SELECT NO FROM MEMBER WHERE ID = ?  AND PWD = ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, vo.getId());
		pstmt.setString(2, vo.getPwd());
		
		int result = pstmt.executeUpdate();
		return result;
	}
	
	
}// class
