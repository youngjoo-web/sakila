package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import vo.Country;

public class DBUtil {
	public Connection getConnection() throws Exception {
		String driver = "org.mariadb.jdbc.Driver";
		String dbAddr ="jdbc:mariadb://localhost:3306/sakila";
		String dbId ="root";
		String dbPw = "java1234";
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(dbAddr,dbId,dbPw);
		return conn;
	}
}
