package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class BoardDao {
	public BoardVo selectBoardByNo(Connection conn, String no) throws Exception {
		
		//게시글 상세조회 sql
		//sql
		String sql = "SELECT B.NO , TITLE , CONTENT , HIT , NICK AS WRITER_NO , TO_CHAR(B.ENROLL_DATE, 'YYYY\"년 \"MM\"월 \"DD\"일 \"HH24\"시\"MI\"분\"SS\"초\"') AS ENROLL_DATE FROM BOARD B JOIN MEMBER M ON B.WRITER_NO = M.NO WHERE B.NO = ? AND DEL_YN = 'N'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, no);
		ResultSet rs = pstmt.executeQuery();
		
		//rs 
		BoardVo vo = null;
		if(rs.next()) {
//			String no = rs.getString("NO");
			String title = rs.getString("TITLE");
			String content = rs.getString("CONTENT");
			String writerNo = rs.getString("WRITER_NO");
			String hit = rs.getString("HIT");
			String enrollDate = rs.getString("ENROLL_DATE"); 
			
			vo = new BoardVo();
			vo.setNo(no);
			vo.setTitle(title);
			vo.setContent(content);
			vo.setWriter_no(writerNo);
			vo.setHit(hit);
			vo.setEnroll_date(enrollDate);
		}
		return vo;
		
	}//method
	
	//조회수 증가 SQL
	public int increaseHit(Connection conn, String no) throws Exception {
		
		//SQL
		String sql = "UPDATE BOARD SET HIT = HIT + 1 WHERE NO = ? AND DEL_YN = 'N'";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, no);
		int result = pstmt.executeUpdate();
		
		return result;
	}
	
	
	
}// class
















