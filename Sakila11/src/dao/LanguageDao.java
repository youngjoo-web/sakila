package dao;
import vo.*;
import java.sql.*;
import java.util.*;
import util.DBUtil;
public class LanguageDao {
	public ArrayList<Language> selectLanguageIdList() throws Exception {
		
		DBUtil dbutil = new DBUtil();
		Connection conn = dbutil.getConnection();
		String sql = "SELECT language_id, name FROM language";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		ArrayList<Language> list = new ArrayList<>();
		while (rs.next()) {
			Language lang = new Language();
			lang.setLanguageId(rs.getInt("language_id"));
			lang.setName(rs.getString("name"));
			list.add(lang);
		}
		return list;
	}
	public ArrayList<Language> selectSearchLanguageAll(int beginRow, int rowPerPage, String searchWord) throws Exception {
		DBUtil dbutil = new DBUtil();
		Connection conn = dbutil.getConnection();
		PreparedStatement stmt = conn.prepareStatement("select * from language where name like ? order by language_id asc limit ?,?");
		ResultSet rs = stmt.executeQuery();
		stmt.setString(1, "%"+searchWord+"%");
		stmt.setInt(2, beginRow);
		stmt.setInt(3, rowPerPage);
		ArrayList<Language> list = new ArrayList<>();
		while (rs.next()) {
			Language la = new Language();
			la.setLanguageId(rs.getInt("language_id"));
			la.setName(rs.getString("name"));
			la.setLastUpdate(rs.getString("last_update"));
			list.add(la);
		}
		return list;
	}
	public void insertlanguage(Language language) throws Exception{
		Class.forName("org.mariadb.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/sakila","root","java1234");
		System.out.println(conn+" <-- conn");
		PreparedStatement stmt = conn.prepareStatement("INSERT INTO language(language_id, name, last_update) value(?,?,now())");
		stmt.setInt(1, language.getLanguageId());
		stmt.setString(2, language.getName());
		stmt.executeQuery();
	}
	
	public ArrayList<Language> selectLanguageAll(int beginRow, int rowPerPage) throws Exception {
		System.out.println("---------selectLanguageAll----------");
		System.out.println(beginRow+" <-- beginRow");
		System.out.println(rowPerPage+" <-- rowPerPage");
		Class.forName("org.mariadb.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/sakila","root","java1234");
		System.out.println(conn+" <-- conn");
		PreparedStatement stmt = conn.prepareStatement("Select * from Language limit ?,?");
		stmt.setInt(1, beginRow);
		stmt.setInt(2, rowPerPage);
		//System.out.println(stmt+" <-- stmt");
		ResultSet rs = stmt.executeQuery();
		ArrayList<Language> list= new ArrayList<Language>();
		while(rs.next()) {
			Language language = new Language();
			language.setLanguageId(rs.getInt("language_id"));
			language.setName(rs.getString("name"));
			language.setLastUpdate(rs.getString("last_update"));
			list.add(language);
		}
		System.out.println(list.size()+" <--list size");
		return list;
	}
	public Language selectLanguageOne(int languageId) throws Exception{
		System.out.println(languageId+"<-- languageId");
		Class.forName("org.mariadb.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/sakila","root","java1234");
		System.out.println(conn+" <-- conn");
		PreparedStatement stmt = conn.prepareStatement("Select * from Language where Language_id=?");
		stmt.setInt(1, languageId);
		System.out.println(stmt+" <-- stmt");
		ResultSet rs = stmt.executeQuery();
		Language language = null;
		while(rs.next()) {
			language = new Language();
			language.setLanguageId(languageId);
			language.setName(rs.getString("name"));
			language.setLastUpdate(rs.getString("last_update"));
		}
		return language;
	}
	public int selectTotalCount() throws Exception{
		System.out.println("---------selectTotalCount---------");
		Class.forName("org.mariadb.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/sakila","root","java1234");
		//System.out.println(conn+" <-- conn");
		PreparedStatement stmt = conn.prepareStatement("Select count(*) as count from Language");
		ResultSet rs = stmt.executeQuery();
		int totalCount=0;
		if(rs.next()) {
			totalCount = rs.getInt("count");
		}
		System.out.println(totalCount+" <-- totalCount");
		return totalCount;
	}
	public void deleteLanaguage(int languageId) throws Exception {
		System.out.println("---------deleteLanguage--------");
		System.out.println(languageId+" <-- languageId");
		String driver="org.mariadb.jdbc.Driver";
		String dbAddr="jdbc:mariadb://localhost:3306/sakila";
		String dbId="root";
		String dbPw="java1234";
		String sql = "DELETE FROM language WHERE language_id=? ";
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(dbAddr,dbId,dbPw);
		//System.out.println(conn+" <-- conn");
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, languageId);
		stmt.executeQuery();
	}
	public void updateLanguage(Language language) throws Exception {
		System.out.println("---------LanguageDao : updateLanguage--------");
		System.out.println(language.getLanguageId()+" <-- languageId");
		String driver="org.mariadb.jdbc.Driver";
		String dbAddr="jdbc:mariadb://localhost:3306/sakila";
		String dbId="root";
		String dbPw="java1234";
		String sql = "UPDATE language SET name=? WHERE language_id=?";
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(dbAddr,dbId,dbPw);
		//System.out.println(conn+" <-- conn");
		PreparedStatement stmt = conn.prepareStatement(sql); 
		stmt.setString(1,language.getName());
		stmt.setInt(2, language.getLanguageId());
		stmt.executeQuery();
	}
}
