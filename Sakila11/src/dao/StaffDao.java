package dao;
import java.sql.*;		
import java.util.*;
import util.DBUtil;
import vo.*;
public class StaffDao {
	public ArrayList<StaffAndAddressAndStore> selectSearchStaffAddressStoreListAll(int beginRow, int rowPerPage, String searchWord) throws Exception {
		DBUtil dbUtil = new DBUtil();
		Connection conn = dbUtil.getConnection();
		PreparedStatement stmt = conn.prepareStatement(
				"SELECT sta.*,ad.*,sto.* FROM staff sta INNER JOIN address ad INNER JOIN store sto ON sta.address_id = ad.address_id AND sta.store_id = sto.store_id WHERE (sta.first_name like ?)OR(sta.last_name like ?)ORDER BY sta.staff_id ASC LIMIT ?,? ");
		stmt.setString(1, "%"+searchWord+"%");
		stmt.setString(2, "%"+searchWord+"%");
		stmt.setInt(3, beginRow);
		stmt.setInt(4, rowPerPage);
		ResultSet rs = stmt.executeQuery();
		ArrayList<StaffAndAddressAndStore> list = new ArrayList<StaffAndAddressAndStore>();
		while (rs.next()) {
			StaffAndAddressAndStore saaas = new StaffAndAddressAndStore();
			Address ad = new Address();
			ad.setAddressId(rs.getInt("address_id"));
			ad.setAddress(rs.getString("address"));
			ad.setAddress2(rs.getString("address2"));
			ad.setDistrict(rs.getString("district"));
			ad.setCityId(rs.getInt("city_id"));
			ad.setPostalCode(rs.getString("postal_code"));
			ad.setPhone(rs.getString("phone"));
			ad.setLastUpdate(rs.getString("last_update"));
			Staff staff = new Staff();
			staff.setStaffId(rs.getInt("staff_id"));
			staff.setFirstName(rs.getString("first_name"));
			staff.setLastName(rs.getString("last_name"));
			staff.setAddressId(rs.getInt("address_id"));
			// staff.setPicture(rs.getFile("picture"));
			staff.setEmail(rs.getString("email"));
			staff.setStoreId(rs.getInt("store_id"));
			staff.setActive(rs.getInt("active"));
			staff.setUserName(rs.getString("username"));
			staff.setPassword(rs.getString("password"));
			staff.setLastUpdate(rs.getString("last_update"));
			Store s = new Store();
			s.setStoreId(rs.getInt("store_id"));
			s.setManagerStaffId(rs.getInt("manager_staff_id"));
			s.setAddressId(rs.getInt("address_id"));
			s.setLastUpdate(rs.getString("last_update"));
			saaas.setAddress(ad);
			saaas.setStaff(staff);
			saaas.setStore(s);
			list.add(saaas);
		}
		return list;
	}

	public ArrayList<Staff> selectStaffListAll(int rowPerPage, int beginRow) throws Exception{
		System.out.println("-------selectStaffListAll-------");
		DBUtil dbUtil = new DBUtil();
		Connection conn = dbUtil.getConnection();
		String sql = "SELECT * FROM staff LIMIT ?,?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, beginRow);
		stmt.setInt(2, rowPerPage);
		ResultSet rs = stmt.executeQuery();
		ArrayList<Staff> list = new ArrayList<Staff>();
		while(rs.next()) {
			Staff staff = new Staff();
			staff.setStaffId(rs.getInt("staff_id"));
	        staff.setFirstName(rs.getString("first_name"));
	        staff.setLastName(rs.getString("last_name"));
	        staff.setAddressId(rs.getInt("address_id"));
	        //staff.setPicture(rs.getFile("picture"));
	        staff.setEmail(rs.getString("email"));
	        staff.setStoreId(rs.getInt("store_id"));
	        staff.setActive(rs.getInt("active"));
	        staff.setUserName(rs.getString("username"));
	        staff.setPassword(rs.getString("password"));
	        staff.setLastUpdate(rs.getString("last_update"));
	        list.add(staff);
		}
		return list;
	}
	public int selectTotalCount(int totalCount) throws Exception {
		DBUtil dbUtil = new DBUtil();
		Connection conn = dbUtil.getConnection();
		String sql="SELECT count(*) as count FROM staff";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			totalCount = rs.getInt("count"); 
		}
		return totalCount;
	}
	public void insertStaff(Staff staff) throws Exception{
	DBUtil dbutil = new DBUtil();
	Connection conn = dbutil.getConnection();
	String sql = "INSERT INTO staff(first_name, last_name, address_id, picture, email, store_id, active, username, password, last_update) VALUE(?,?,?,?,?,?,?,?,?,now())";
	PreparedStatement stmt = conn.prepareStatement(sql);
	stmt.setString(1, staff.getFirstName());
	stmt.setString(2, staff.getLastName());
	stmt.setInt(3, staff.getAddressId());
	stmt.setString(4, null);
	stmt.setString(5, staff.getEmail());
	stmt.setInt(6, staff.getStoreId());
	stmt.setInt(7, 1);
	stmt.setString(8, staff.getUserName());
	stmt.setString(9, staff.getPassword());
	stmt.executeUpdate();
	}
	public ArrayList<StaffAndAddressAndStore> selectStaffAddressStoreListAll(int rowPerPage, int beginRow) throws Exception {
		DBUtil dbUtil = new DBUtil();
		Connection conn = dbUtil.getConnection();
		String sql="SELECT sta.*, ad.*, sto.* ";
		sql+="FROM staff sta ";
		sql+="INNER JOIN address ad ";
		sql+="INNER JOIN store sto ";
		sql+="ON sta.address_id = ad.address_id ";
		sql+="AND sta.store_id = sto.store_id ";
		sql+="ORDER BY staff_id ";
		sql+="LIMIT ?,?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, beginRow);
		stmt.setInt(2, rowPerPage);
		ResultSet rs = stmt.executeQuery();
		ArrayList<StaffAndAddressAndStore> list = new ArrayList<StaffAndAddressAndStore>();
		while (rs.next()) {
			StaffAndAddressAndStore saaas = new StaffAndAddressAndStore();
			Address ad = new Address();
			ad.setAddressId(rs.getInt("address_id"));
			ad.setAddress(rs.getString("address"));
			ad.setAddress2(rs.getString("address2"));
			ad.setDistrict(rs.getString("district"));
			ad.setCityId(rs.getInt("city_id"));
			ad.setPostalCode(rs.getString("postal_code"));
			ad.setPhone(rs.getString("phone"));
			ad.setLastUpdate(rs.getString("last_update"));
			
			Staff staff = new Staff();
			staff.setStaffId(rs.getInt("staff_id"));
			staff.setFirstName(rs.getString("first_name"));
			staff.setLastName(rs.getString("last_name"));
			staff.setAddressId(rs.getInt("address_id"));
			// staff.setPicture(rs.getFile("picture"));
			staff.setEmail(rs.getString("email"));
			staff.setStoreId(rs.getInt("store_id"));
			staff.setActive(rs.getInt("active"));
			staff.setUserName(rs.getString("username"));
			staff.setPassword(rs.getString("password"));
			staff.setLastUpdate(rs.getString("last_update"));
			
			Store s = new Store();
			s.setStoreId(rs.getInt("store_id"));
			s.setManagerStaffId(rs.getInt("manager_staff_id"));
			s.setAddressId(rs.getInt("address_id"));
			s.setLastUpdate(rs.getString("last_update"));
			
			saaas.setAddress(ad);
			saaas.setStaff(staff);
			saaas.setStore(s);
			list.add(saaas);
		}
		return list;
	}
	public ArrayList<Staff> selectStaffIdList() throws Exception{
		String sql= "select staff_id from staff";
		DBUtil dbutil = new DBUtil();
		Connection conn = dbutil.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		ArrayList<Staff> staffIdlist = new ArrayList<Staff>();
		while(rs.next()) {
		Staff staff = new Staff();
		staff.setStaffId(rs.getInt("staff_id"));
		staffIdlist.add(staff);
		}
		return staffIdlist;
	}
}