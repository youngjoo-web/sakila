package dao;

import java.sql.*;
import java.util.ArrayList;
import util.DBUtil;
import vo.*;

public class StoreDao {
	public ArrayList<Integer> selectStoreIdList() throws Exception {
		DBUtil dbutil = new DBUtil();
		Connection conn = dbutil.getConnection();
		String sql = "select store_id from store";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		ArrayList<Integer> list = new ArrayList<>();
		while (rs.next()) {
			list.add(rs.getInt("store_id"));
		}

		return list;
	}

	public ArrayList<Store> selectStoreListAll(int rowPerPage, int beginRow) throws Exception {
		System.out.println("selectStoreList");
		DBUtil dbUtil = new DBUtil();
		Connection conn = dbUtil.getConnection();
		String sql = "SELECT * FROM store LIMIT ?,?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, beginRow);
		stmt.setInt(2, rowPerPage);
		ResultSet rs = stmt.executeQuery();
		ArrayList<Store> list = new ArrayList<Store>();
		while (rs.next()) {
			Store s = new Store();
			s.setStoreId(rs.getInt("store_id"));
			s.setManagerStaffId(rs.getInt("manager_staff_id"));
			s.setAddressId(rs.getInt("address_id"));
			s.setLastUpdate(rs.getString("last_update"));
			list.add(s);
		}
		return list;
	}

	public int selectTotalCount(int totalCount) throws Exception {
		DBUtil dbUtil = new DBUtil();
		Connection conn = dbUtil.getConnection();
		String sql = "SELECT count(*) as count FROM store";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			totalCount = rs.getInt("count");
		}
		return totalCount;
	}

	public ArrayList<StoreAndAddressAndStaff> selectStoreList(int beginRow, int rowPerPage, int searchNo)
			throws Exception {
		DBUtil dbutil = new DBUtil();
		Connection conn = dbutil.getConnection();
		String sql = "SELECT st.*,sf.*, ad.* FROM store st inner join address ad inner join staff sf ON st.address_id= ad.address_id AND sf.staff_id=st.manager_staff_id where st.store_id like ? limit ?,?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, "%" + searchNo + "%");
		stmt.setInt(2, beginRow);
		stmt.setInt(3, rowPerPage);
		ResultSet rs = stmt.executeQuery();
		ArrayList<StoreAndAddressAndStaff> list = new ArrayList<StoreAndAddressAndStaff>();
		while (rs.next()) {
			StoreAndAddressAndStaff saadast = new StoreAndAddressAndStaff();
			Store store = new Store();
			store.setStoreId(rs.getInt("store_id"));
			store.setManagerStaffId(rs.getInt("manager_staff_id"));
			store.setAddressId(rs.getInt("address_id"));
			store.setLastUpdate(rs.getString("last_update"));
			saadast.setStore(store);
			Staff staff = new Staff();
			staff.setStaffId(rs.getInt("staff_id"));
			staff.setFirstName(rs.getString("first_name"));
			staff.setLastName(rs.getString("last_name"));
			staff.setAddressId(rs.getInt("address_id"));

			staff.setEmail(rs.getString("email"));
			staff.setStoreId(rs.getInt("store_id"));
			staff.setActive(rs.getInt("active"));
			staff.setUserName(rs.getString("username"));
			staff.setPassword(rs.getString("password"));
			staff.setLastUpdate(rs.getString("last_update"));
			saadast.setStaff(staff);
			Address address = new Address();
			address.setAddressId(rs.getInt("address_id"));
			address.setAddress(rs.getString("address"));
			address.setAddress2(rs.getString("address2"));
			address.setDistrict(rs.getString("district"));
			address.setCityId(rs.getInt("city_id"));
			address.setPostalCode(rs.getString("postal_code"));
			address.setPhone(rs.getString("phone"));
			address.setLastUpdate(rs.getString("last_update"));
			saadast.setAddress(address);
			list.add(saadast);
		}
		return list;
	}

	public ArrayList<StoreAndAddressAndStaff> selectStoreList(int rowPerPage, int beginRow) throws Exception {
		DBUtil dbutil = new DBUtil();
		Connection conn = dbutil.getConnection();
		String sql = "SELECT st.store_id, sf.first_name, sf.last_name, ad.address ";
		sql += "FROM store st ";
		sql += "INNER JOIN address ad ";
		sql += "INNER JOIN staff sf ";
		sql += "ON st.address_id = ad.address_id ";
		sql += "AND sf.staff_id = st.manager_staff_id ";
		sql += "LIMIT ?,?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, beginRow);
		stmt.setInt(2, rowPerPage);
		ResultSet rs = stmt.executeQuery();
		ArrayList<StoreAndAddressAndStaff> list = new ArrayList<StoreAndAddressAndStaff>();
		while (rs.next()) {
			StoreAndAddressAndStaff saadast = new StoreAndAddressAndStaff();
			Store store = new Store();
			store.setStoreId(rs.getInt("store_id"));
			saadast.setStore(store);
			Staff staff = new Staff();
			staff.setFirstName(rs.getString("first_name"));
			staff.setLastName(rs.getString("last_name"));
			saadast.setStaff(staff);
			Address address = new Address();
			address.setAddress(rs.getString("address"));
			saadast.setAddress(address);
			list.add(saadast);
		}
		return list;
	}
	public void insertStore(Store store) throws Exception{
		DBUtil dbutil = new DBUtil();
		Connection conn = dbutil.getConnection();
		String sql = "INSERT INTO ";
			sql+="store(store_id, manager_staff_id, address_id, last_update)";
			sql+="VALUE(?,?,?,now())";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, store.getStoreId());
		stmt.setInt(2, store.getManagerStaffId());
		stmt.setInt(3, store.getAddressId());
		stmt.executeQuery();
	}
}