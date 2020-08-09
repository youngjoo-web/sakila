package dao;

import java.sql.*;
import util.*;
import vo.*;
import java.sql.*;
import java.util.*;

public class CityDao {
	public int selectSearchTotalCity(String searchWord, int totalCount) throws Exception{
		DBUtil dbUtil = new DBUtil();
		Connection conn = dbUtil.getConnection();
		String sql="SELECT count(*) as count FROM city where city like ? ";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, "%"+searchWord+"%");
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			totalCount = rs.getInt("count"); 
		}
		return totalCount;
	}
	public ArrayList<City> selectCityList() throws Exception {
		DBUtil dbutil = new DBUtil();
		Connection conn = dbutil.getConnection();
		String sql = "SELECT city_id, city FROM city";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		ArrayList<City> list = new ArrayList<>();
		while (rs.next()) {
			City city = new City();
			city.setCityId(rs.getInt("city_id"));
			city.setCity(rs.getString("city"));
			list.add(city);
		}
		return list;
	}

	public ArrayList<CityAndCountry> selectSearchCity(int beginRow, int rowPerPage, String searchWord)
			throws Exception {

		String sql = "SELECT ci.*, co.* FROM city ci INNER JOIN country co ON ci.country_id = co.country_id WHERE city LIKE ? ORDER BY ci.city_id limit ?,?";
		DBUtil dbutil = new DBUtil();
		Connection conn = dbutil.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, "%" + searchWord + "%");
		stmt.setInt(2, beginRow);
		stmt.setInt(3, rowPerPage);
		ResultSet rs = stmt.executeQuery();

		ArrayList<CityAndCountry> list = new ArrayList<>();
		while (rs.next()) {
			CityAndCountry cac = new CityAndCountry();
			City ci = new City();
			ci.setCityId(rs.getInt("city_id"));
			ci.setCity(rs.getString("city"));
			ci.setCountryId(rs.getInt("country_id"));
			ci.setLastUpdate(rs.getString("last_update"));
			cac.setCity(ci);
			Country co = new Country();
			co.setCountryId(rs.getInt("country_id"));
			co.setCountry(rs.getString("country"));
			co.setLastUpdate(rs.getString("last_update"));
			cac.setCountry(co);
			list.add(cac);
		}

		return list;
	}

	public void insertCity(City city) throws Exception {
		System.out.println("------CityDao_insertCity(City city)------");
		DBUtil dbUtil = new DBUtil();
		Connection conn = dbUtil.getConnection();
		System.out.println(conn + " <-- conn");
		String sql = "INSERT INTO city(city, country_Id, last_update) VALUE(?,?,now())";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, city.getCity());
		stmt.setInt(2, city.getCountryId());
		System.out.println(stmt + " <- stmt");
		stmt.executeQuery();
	}

	public ArrayList<City> selectCityListAll() throws Exception {
		System.out.println("------CityDao_selectCityListAll()------");
		DBUtil dbUtil = new DBUtil();
		Connection conn = dbUtil.getConnection();
		System.out.println(conn + " <-- conn");
		String sql = "SELECT * FROM city";
		PreparedStatement stmt = conn.prepareStatement(sql);
		System.out.println(stmt + " <- stmt");
		ResultSet rs = stmt.executeQuery();
		ArrayList<City> list = new ArrayList<City>();
		while (rs.next()) {
			City city = new City();
			city.setCityId(rs.getInt("city_id"));
			city.setCity(rs.getString("city"));
			city.setCountryId(rs.getInt("country_id"));
			city.setLastUpdate(rs.getString("last_update"));
			list.add(city);
		}
		System.out.println(list.size() + " <-- list.size");
		return list;
	}

	public ArrayList<CityAndCountry> selectCityAndCountry(int rowPerPage, int beginRow) throws Exception {
		System.out.println("------CityDao_selectCityAndCountry()------");
		DBUtil dbUtil = new DBUtil();
		Connection conn = dbUtil.getConnection();
		System.out.println(conn + " <-- conn");
		String sql = "";
		sql += "SELECT ci.country_id, co.country, ci.city_id, ci.city ";
		sql += "FROM city AS ci ";
		sql += "INNER JOIN country AS co ";
		sql += "ON ci.country_id = co.country_id ";
		sql += "ORDER BY ci.country_id ";
		sql += "LIMIT ?,?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, beginRow);
		stmt.setInt(2, rowPerPage);
		System.out.println(stmt + " <-- stmt");
		ResultSet rs = stmt.executeQuery();
		ArrayList<CityAndCountry> list = new ArrayList<CityAndCountry>();
		while (rs.next()) {
			CityAndCountry cityAndCountry = new CityAndCountry();
			City city = new City();
			city.setCityId(rs.getInt("city_id"));
			city.setCity(rs.getString("city"));
			city.setCountryId(rs.getInt("country_id"));
			cityAndCountry.setCity(city);

			Country country = new Country();
			country.setCountryId(rs.getInt("country_id"));
			country.setCountry(rs.getString("country"));
			cityAndCountry.setCountry(country);

			list.add(cityAndCountry);
		}
		System.out.println(list.size() + " <-- list.size");
		return list;
	}

	public ArrayList<City> selectCityListAll2() throws Exception {
		System.out.println("------CityDao_selectCityListAll2()------");
		DBUtil dbUtil = new DBUtil();
		Connection conn = dbUtil.getConnection();
		System.out.println(conn + " <-- conn");
		String sql = "SELECT city_id, city FROM city";
		PreparedStatement stmt = conn.prepareStatement(sql);
		System.out.println(stmt + " <- stmt");
		ResultSet rs = stmt.executeQuery();
		ArrayList<City> list = new ArrayList<City>();
		while (rs.next()) {
			City city = new City();
			city.setCityId(rs.getInt("city_id"));
			city.setCity(rs.getString("city"));
			list.add(city);
		}
		System.out.println(list.size() + " <-- list.size");
		return list;
	}

	public int selectTotalCount(int totalCount) throws Exception {
		DBUtil dbUtil = new DBUtil();
		Connection conn = dbUtil.getConnection();
		String sql = "SELECT count(*) as count FROM city";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			totalCount = rs.getInt("count");
		}
		return totalCount;
	}
}
