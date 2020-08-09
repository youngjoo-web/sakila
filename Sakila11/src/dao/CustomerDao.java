package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import util.DBUtil;
import vo.*;

public class CustomerDao {
	public ArrayList<Customer> selectCustomerListAll(int rowPerPage, int beginRow) throws Exception {
		System.out.println("--selectCustomerListAll--");
		DBUtil dbUtil = new DBUtil();
		Connection conn = dbUtil.getConnection();
		String sql = "SELECT * FROM customer LIMIT ?,?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, beginRow);
		stmt.setInt(2, rowPerPage);
		ResultSet rs = stmt.executeQuery();
		ArrayList<Customer> list = new ArrayList<Customer>();
		while(rs.next()) {
			Customer customer = new Customer();
			customer.setCustomerId(rs.getInt("customer_id"));
			customer.setStoreId(rs.getInt("store_id"));
			customer.setFirstName(rs.getString("first_name"));
			customer.setLastName(rs.getString("last_name"));
			customer.setEmail(rs.getString("email"));
			customer.setAddressId(rs.getInt("address_id"));
			customer.setActive(rs.getInt("active"));
			customer.setCreateDate(rs.getString("create_date"));
			customer.setLastUpdate(rs.getString("last_update"));
			list.add(customer);
		}
		return list;
	}
	public int selectTotalCount(int totalCount) throws Exception {
		DBUtil dbUtil = new DBUtil();
		Connection conn = dbUtil.getConnection();
		String sql="SELECT count(*) as count FROM customer";
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
		String sql = "insert into staff(first_name, last_name, address_id, picture, email, store_id, active, username, password, last_update) value(?,?,?,?,?,?,?,?,?,now())";
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
	public ArrayList<CustomerAndStoreAndAddress> selectCustegoryList(int beginRow, int rowPerPage, String searchWord) throws Exception{
		DBUtil dbutil = new DBUtil();
	    Connection conn = dbutil.getConnection();
	    String sql="SELECT cu.*,st.*,cu.* FROM customer cu INNER JOIN store st INNER JOIN address ad on cu.store_id=st.store_id AND cu.address_id=ad.address_id where (cu.first_name like ?)or (cu.last_name like ?) limit ?,?";
	    PreparedStatement stmt = conn.prepareStatement(sql);
	    stmt.setString(1, "%"+searchWord+"%");
	    stmt.setString(2, "%"+searchWord+"%");
	    stmt.setInt(3, beginRow);
	    stmt.setInt(4, rowPerPage);
	    ResultSet rs = stmt.executeQuery();
	    ArrayList<CustomerAndStoreAndAddress> list= new ArrayList<CustomerAndStoreAndAddress>();
	    while(rs.next()) {
	    	CustomerAndStoreAndAddress castaad = new CustomerAndStoreAndAddress();
	    	Customer customer = new Customer();
	    	customer.setCustomerId(rs.getInt("customer_id"));
			customer.setStoreId(rs.getInt("store_id"));
			customer.setFirstName(rs.getString("first_name"));
			customer.setLastName(rs.getString("last_name"));
			customer.setEmail(rs.getString("email"));
			customer.setAddressId(rs.getInt("address_id"));
			customer.setActive(rs.getInt("active"));
			customer.setCreateDate(rs.getString("create_date"));
			customer.setLastUpdate(rs.getString("last_update"));
	    	castaad.setCustomer(customer);
	    	Store store = new Store();
	    	store.setStoreId(rs.getInt("store_id"));
			store.setManagerStaffId(rs.getInt("manager_staff_id"));
			store.setAddressId(rs.getInt("address_id"));
			store.setLastUpdate(rs.getString("last_update"));
	    	castaad.setStore(store);
	    	Address address = new Address();
	    	address.setAddressId(rs.getInt("address_id"));
	    	address.setAddress(rs.getString("address"));
	    	address.setAddress2(rs.getString("address2"));
	        address.setDistrict(rs.getString("district"));
	        address.setCityId(rs.getInt("city_id"));
	        address.setPostalCode(rs.getString("postal_code"));
	        address.setPhone(rs.getString("phone"));
	        address.setLastUpdate(rs.getString("last_update"));
	    	castaad.setAddress(address);
	    	list.add(castaad);
	    }
	    return list;
	}
	public ArrayList<CustomerAndStoreAndAddress> selectCustegoryList(int rowPerPage, int beginRow) throws Exception{
		DBUtil dbutil = new DBUtil();
	    Connection conn = dbutil.getConnection();
	    String sql="SELECT cu.customer_id, st.store_id, cu.first_name, cu.last_name, cu.email, ad.address, cu.active, cu.create_date ";
	    sql+="FROM customer cu ";
	    sql+="INNER JOIN store st ";
	    sql+="INNER JOIN address ad ";
	    sql+="ON cu.store_id = st.store_id ";
	    sql+="AND cu.address_id = ad.address_id ";
	    sql+="ORDER BY customer_id ";
	    sql+="LIMIT ?,?";
	    PreparedStatement stmt = conn.prepareStatement(sql);
	    stmt.setInt(1, beginRow);
		stmt.setInt(2, rowPerPage);
	    ResultSet rs = stmt.executeQuery();
	    ArrayList<CustomerAndStoreAndAddress> list= new ArrayList<CustomerAndStoreAndAddress>();
	    while(rs.next()) {
	    	CustomerAndStoreAndAddress castaad = new CustomerAndStoreAndAddress();
	    	Customer customer = new Customer();
	    	customer.setCustomerId(rs.getInt("customer_id"));
	    	customer.setFirstName(rs.getString("first_name"));
	    	customer.setLastName(rs.getString("last_name"));
	    	customer.setEmail(rs.getString("email"));
	    	customer.setActive(rs.getInt("active"));
	    	customer.setCreateDate(rs.getString("create_date"));
	    	castaad.setCustomer(customer);
	    
	    	Store store = new Store();
	    	store.setStoreId(rs.getInt("store_id"));
	    	castaad.setStore(store);
	    	Address address = new Address();
	    	address.setAddress(rs.getString("address"));
	    	castaad.setAddress(address);
	    	list.add(castaad);
	    }
	    return list;
	}
	public void insertCustomer(Customer customer) throws Exception{
		DBUtil dbutil = new DBUtil();
		Connection conn = dbutil.getConnection();
		String sql = "insert into customer(first_name, last_name, email, address_id, active, create_date, last_update, store_id) VALUE(?,?,?,?,?,now(),NOW(),?);";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, customer.getFirstName());
		stmt.setString(2, customer.getLastName());		
		stmt.setString(3, customer.getEmail());
		stmt.setInt(4, customer.getAddressId());
		stmt.setInt(5, customer.getActive());
		stmt.setInt(6, customer.getStoreId());
		
		
		stmt.executeUpdate();
	}
}
