package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCTemplet {

	public static Connection getConn() throws Exception {
		String driver = "oracle.jdbc.driver.OracleDriver";
		Class.forName(driver);
		
		String url = "jdbc:oracle:thin:@61.78.95.160:1522:xe";
		String id = "C##KH";
		String pwd = "1234";
		
		Connection conn = DriverManager.getConnection(url, id, pwd);
		
		return conn;
		
	}//getConn
	
}
