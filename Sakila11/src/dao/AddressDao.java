package dao;

import java.sql.*;	
import java.util.*;
import util.DBUtil;
import vo.*;
public class AddressDao {
	public ArrayList<AddressAndCityAndCountry> selectSearchAdd(int beginRow, int rowPerPage, String searchWord) throws Exception {

		String sql = "select ad.*, ci.*, co.* from address ad inner join city ci inner join country co on ad.city_id = ci.city_id and ci.country_id = co.country_id where ad.address like ?  ORDER BY ad.address_id limit ?,?";
		DBUtil dbutil = new DBUtil();
		Connection conn = dbutil.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, "%" + searchWord + "%");
		ResultSet rs = stmt.executeQuery();
		ArrayList<AddressAndCityAndCountry> list = new ArrayList<AddressAndCityAndCountry>();
		while (rs.next()) {
			AddressAndCityAndCountry aacac = new AddressAndCityAndCountry();
			Address ad = new Address();
			ad.setAddressId(rs.getInt("address_id"));
			ad.setAddress(rs.getString("address"));
			ad.setAddress2(rs.getString("address2"));
			ad.setDistrict(rs.getString("district"));
			ad.setCityId(rs.getInt("city_id"));
			ad.setPostalCode(rs.getString("postal_code"));
			ad.setPhone(rs.getString("phone"));
			ad.setLastUpdate(rs.getString("last_update"));
			aacac.setAddress(ad);
			City ci = new City();
			ci.setCityId(rs.getInt("city_id"));
			ci.setCity(rs.getString("city"));
			ci.setCountryId(rs.getInt("country_id"));
			ci.setLastUpdate(rs.getString("last_update"));
			aacac.setCity(ci);
			Country co = new Country();
			co.setCountryId(rs.getInt("country_id"));
			co.setCountry(rs.getString("country"));
			co.setLastUpdate(rs.getString("last_update"));
			aacac.setCountry(co);
			list.add(aacac);
		}
		return list;
	}
	public ArrayList<AddressAndCityAndCountry> selectAddressAndCityAndCountry(int rowPerPage, int beginRow) throws Exception{
		System.out.println("------AddressDao_selectAddressAndCityAndCountry()------");
		DBUtil dbUtil = new DBUtil();
		Connection conn = dbUtil.getConnection();
		String sql="";
		sql += "SELECT ad.address_id, co.country, ci.city, ad.district, ad.address, ad.address2, ad.postal_code, ad.phone ";
		sql += "FROM address AS ad ";
		sql += "INNER JOIN city AS ci ";
		sql += "ON ad.city_id = ci.city_id ";
		sql += "INNER JOIN country AS co ";
		sql += "ON ci.country_id = co.country_id ";
		sql += "ORDER BY address_id asc ";
		sql += "LIMIT ?,?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, beginRow);
		stmt.setInt(2, rowPerPage);
		ResultSet rs = stmt.executeQuery();
		ArrayList<AddressAndCityAndCountry> list = new ArrayList<AddressAndCityAndCountry>();
		while(rs.next()) {
			AddressAndCityAndCountry addressAndCityAndCountry = new AddressAndCityAndCountry();
			Address address = new Address();
			address.setAddressId(rs.getInt("address_id"));
			address.setDistrict(rs.getString("district"));
			address.setAddress(rs.getString("address"));
			address.setAddress2(rs.getString("address2"));
			address.setPostalCode(rs.getString("postal_code"));
			address.setPhone(rs.getString("phone"));
			addressAndCityAndCountry.setAddress(address);
			
			City city = new City();
			city.setCity(rs.getString("city"));
			addressAndCityAndCountry.setCity(city);
			
			Country country = new Country();
			country.setCountry(rs.getString("country"));
			addressAndCityAndCountry.setCountry(country);
			
			list.add(addressAndCityAndCountry);
		}
		return list;
	}
	public void insertAddress(Address address) throws Exception {
		System.out.println("------AddressDao_insertAddress------");
		DBUtil dbUtil = new DBUtil();
		Connection conn = dbUtil.getConnection();
		System.out.println(conn+" <-- conn");
		String sql="INSERT INTO address";
		sql+="(address, address2, district, city_id, postal_code, phone, last_update) ";
		sql+="VALUE(?,?,?,?,?,?,now())";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1,address.getAddress());
		stmt.setString(2, address.getAddress2());
		stmt.setString(3, address.getDistrict());
		stmt.setInt(4, address.getCityId());
		stmt.setString(5, address.getPostalCode());
		stmt.setString(6, address.getPhone());
		System.out.println(stmt+" <- stmt");
		stmt.executeQuery();
		
	}
	public int insertAddressAndSelectKey(Address address) throws Exception { 
		DBUtil dbUtil = new DBUtil();
		Connection conn = dbUtil.getConnection();
		String sql="INSERT INTO address";
		sql+="(address, address2, district, city_id, postal_code, phone, last_update) ";
		sql+="VALUE(?,?,?,?,?,?,now())";
		PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); // Statement.RETURN_GENERATED_KEYS : select臾� �떎�뻾�빐�씪
		// stmt = insert + select
		// 1. insert
		stmt.setString(1,address.getAddress());
		stmt.setString(2, address.getAddress2());
		stmt.setString(3, address.getDistrict());
		stmt.setInt(4, address.getCityId());
		stmt.setString(5, address.getPostalCode());
		stmt.setString(6, address.getPhone());
		stmt.executeUpdate();
		// 2. select
		ResultSet rs = stmt.getGeneratedKeys();
		int addressId = 0;
		if(rs.next()) {
			addressId = rs.getInt(1); // select�뿉 �엳�뒗 泥ル쾲吏몃�� �샇異쒗빐�씪
		}
		return addressId;
	}
	public int selectTotalCount(int totalCount) throws Exception {
		DBUtil dbUtil = new DBUtil();
		Connection conn = dbUtil.getConnection();
		String sql="SELECT count(*) as count FROM address";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			totalCount = rs.getInt("count"); 
		}
		return totalCount;
	}
	public ArrayList<Address> selectaddressIdList() throws Exception{
		String sql = "select address_id from address";
		DBUtil dbutil = new DBUtil();
		Connection conn = dbutil.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		ArrayList<Address> addressIdListlist = new ArrayList<Address>();
		while(rs.next()) {
			Address address = new Address();
			address.setAddressId(rs.getInt("address_id"));
			addressIdListlist.add(address);
		}
		return addressIdListlist;
	}
}
