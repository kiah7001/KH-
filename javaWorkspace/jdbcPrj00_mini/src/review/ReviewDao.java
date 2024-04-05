package review;

import java.sql.Connection;
import java.sql.PreparedStatement;

import main.Main;

public class ReviewDao {

	// 후기작성
	public int write(Connection conn, ReviewVo inputVo) throws Exception {

		// SQL
		String sql = "INSERT INTO REVIEW(NO , PRODUCT_NO, WRITER_NO , TITLE, CONTENT) VALUES (SEQ_REVIEW_NO.NEXTVAL, ?, ? ,?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, inputVo.getProductNo());
		pstmt.setString(2, Main.loginManager.getNo());
		pstmt.setString(3, inputVo.getTitle());
		pstmt.setString(4, inputVo.getContent());
		int result = pstmt.executeUpdate();
		return result;
	}

	// 제목변경
	public int modifyTitle(Connection conn, ReviewVo inputVo) throws Exception {

		// SQL
		String sql = "UPDATE REVIEW SET TITLE = ? WHERE NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, inputVo.getTitle());
		pstmt.setString(2, inputVo.getNo());
		int result = pstmt.executeUpdate();
		return result;
	}

	// 내용변경
	public int modifyContent(Connection conn, ReviewVo inputVo) throws Exception {

		// SQL
		String sql = "UPDATE REVIEW SET CONTENT = ? WHERE NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, inputVo.getContent());
		pstmt.setString(2, inputVo.getNo());
		int result = pstmt.executeUpdate();
		return result;

	}

	// 후기삭제
	public int delet(Connection conn, ReviewVo inputVo) throws Exception {

		// SQL
		String sql = "UPDATE REVIEW SET DEL_YN = 'Y' WHERE NO = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, inputVo.getNo());

		int result = pstmt.executeUpdate();
		return result;

	}

}// class
