package dao;
import vo.*;

import java.sql.*;
import java.util.*;

import util.DBUtil;

public class CountryDao {
	public ArrayList<Country> selectSearchCountry(int beginRow, int rowPerPage, String searchWord) throws Exception {
		DBUtil dbutil = new DBUtil();
		Connection conn = dbutil.getConnection();
		PreparedStatement stmt = conn.prepareStatement("select * from country WHERE country like ? ORDER BY country_id LIMIT ?,?");
		stmt.setString(1, "%" + searchWord + "%");
		stmt.setInt(2,beginRow);
		stmt.setInt(3, rowPerPage);
		ResultSet rs = stmt.executeQuery();
		ArrayList<Country> list = new ArrayList<>();
		while (rs.next()) {
			Country country = new Country();
			country.setCountryId(rs.getInt("country_id"));
			country.setCountry(rs.getString("country"));
			country.setLastUpdate(rs.getString("last_update"));
			list.add(country);
		}
		return list;
	}
	public ArrayList<Country> selectCountryListAll(String searchWord) throws Exception {
		System.out.println(searchWord+" <- searchWord");
		DBUtil dbUtil = new DBUtil();
		Connection conn = dbUtil.getConnection();
		String sql = "SELECT * FROM country where country like ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, "%"+searchWord+"%");
		ResultSet rs = stmt.executeQuery();
		ArrayList<Country> list = new ArrayList<Country>();
		while(rs.next()) {
			Country country = new Country();
			country.setCountryId(rs.getInt("country_id"));
			country.setCountry(rs.getString("country"));
			country.setLastUpdate(rs.getString("last_update"));
			list.add(country);
		}
		return list;
	}
	public ArrayList<Country> selectCountryListAll(int rowPerPage, int beginRow) throws Exception {
		DBUtil dbUtil = new DBUtil();
		Connection conn = dbUtil.getConnection();
		String sql = "SELECT * FROM country  LIMIT ?,?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, beginRow);
		stmt.setInt(2, rowPerPage);
		ResultSet rs = stmt.executeQuery();
		ArrayList<Country> list = new ArrayList<Country>();
		while(rs.next()) {
			Country country = new Country();
			country.setCountryId(rs.getInt("country_id"));
			country.setCountry(rs.getString("country"));
			country.setLastUpdate(rs.getString("last_update"));
			list.add(country);
		}
		return list;
	}
	public int selectSearchTotalCount(String searchWord, int totalCount) throws Exception{
		DBUtil dbUtil = new DBUtil();
		Connection conn = dbUtil.getConnection();
		String sql="SELECT count(*) as count FROM country where country like ? ";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, "%"+searchWord+"%");
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			totalCount = rs.getInt("count"); 
		}
		return totalCount;
	}
	public int selectTotalCount(int totalCount) throws Exception {
		DBUtil dbUtil = new DBUtil();
		Connection conn = dbUtil.getConnection();
		String sql="SELECT count(*) as count FROM country";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			totalCount = rs.getInt("count"); 
		}
		return totalCount;
	}
}
