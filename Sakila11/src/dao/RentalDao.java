package dao;
import java.sql.Connection;	
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import util.DBUtil;
import vo.*;


public class RentalDao {
	public  ArrayList<RentalAndInventoryAndCustomerAndStaff>selectSearchRentalAndInvaentaoryAndCustomerAndStaffListAll(int beginRow, int rowPerPage, String searchWord)throws Exception{
		DBUtil dbUtil = new DBUtil();
		Connection conn = dbUtil.getConnection();
		String sql ="SELECT re.*,inv.*,cu.*,st.* FROM rental re INNER JOIN inventory inv INNER JOIN customer cu INNER JOIN Staff st ON re.inventory_id  =inv.inventory_id AND re.customer_id=cu.customer_idAND re.staff_id= st.staff_id where (cu.first_name like ?)or (cu.last_name like ?) limit ?,? ";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, "%"+searchWord+"%");
		stmt.setString(2, "%"+searchWord+"%");
		stmt.setInt(3, beginRow);
		stmt.setInt(4, rowPerPage);
		ResultSet rs = stmt.executeQuery();
		ArrayList<RentalAndInventoryAndCustomerAndStaff> list = new ArrayList<>();
		while(rs.next()) {
			RentalAndInventoryAndCustomerAndStaff raiacas = new RentalAndInventoryAndCustomerAndStaff();
			Rental rental = new Rental();
			rental.setRentalId(rs.getInt("rental_id"));
			rental.setRentalDate(rs.getString("rental_date"));
			rental.setInventoryId(rs.getInt("inventory_id"));
			rental.setCustomerId(rs.getInt("customer_id"));
			rental.setReturnDate(rs.getString("return_date"));
			rental.setStaffId(rs.getInt("staff_id"));
			rental.setLastUpdate(rs.getString("last_update"));
			raiacas.setRental(rental);
			
			Inventory inventory = new Inventory();
			inventory.setInventoryId(rs.getInt("inventory_id"));
			inventory.setFilmId(rs.getInt("film_id"));
			inventory.setStoreId(rs.getInt("store_id"));
			inventory.setLastUpdate(rs.getString("last_update"));
			raiacas.setInventory(inventory);
			
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
			raiacas.setCustomer(customer);
			
			Staff staff= new Staff();
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
	        raiacas.setStaff(staff);
	        list.add(raiacas);
			}
			return list;
		}
	public ArrayList<Rental> selectRentalListAll(int beginRow, int rowPerPage) throws Exception{
		DBUtil dbUtil = new DBUtil();
		Connection conn = dbUtil.getConnection();
		String sql = "SELECT * FROM rental LIMIT ?,?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, beginRow);
		stmt.setInt(2, rowPerPage);
		ResultSet rs = stmt.executeQuery();
		ArrayList<Rental> list = new ArrayList<Rental>();
		while(rs.next()) {
			Rental rental = new Rental();
			rental.setRentalId(rs.getInt("rental_id"));
			rental.setRentalDate(rs.getString("rental_date"));
			rental.setInventoryId(rs.getInt("inventory_id"));
			rental.setCustomerId(rs.getInt("customer_id"));
			rental.setReturnDate(rs.getString("return_date"));
			rental.setStaffId(rs.getInt("staff_id"));
			rental.setLastUpdate(rs.getString("last_update"));
			list.add(rental);
		}
		return list;
	}
	public int selectTotalCount(int totalCount) throws Exception {
		DBUtil dbUtil = new DBUtil();
		Connection conn = dbUtil.getConnection();
		String sql="SELECT count(*) as count FROM rental";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			totalCount = rs.getInt("count"); 
		}
		return totalCount;
	}
	public  ArrayList<RentalAndInventoryAndCustomerAndStaff>selectRentalAndInvaentaoryAndCustomerAndStaffListAll(int beginRow, int rowPerPage)throws Exception{
		DBUtil dbUtil = new DBUtil();
		Connection conn = dbUtil.getConnection();
		String sql ="SELECT re.*, inv.*, cu.*, st.* "
				+ "FROM rental re "
				+ "INNER JOIN inventory inv "
				+ "INNER JOIN customer cu "
				+ "INNER JOIN Staff st "
				+ "ON re.inventory_id = inv.inventory_id "
				+ "AND re.customer_id=cu.customer_id "
				+ "AND re.staff_id= st.staff_id "
				+ "ORDER BY rental_id "
				+ "LIMIT ?,?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, beginRow);
		stmt.setInt(2, rowPerPage);
		ResultSet rs = stmt.executeQuery();
		ArrayList<RentalAndInventoryAndCustomerAndStaff> list = new ArrayList<RentalAndInventoryAndCustomerAndStaff>();
		while(rs.next()) {
			RentalAndInventoryAndCustomerAndStaff raiacas = new RentalAndInventoryAndCustomerAndStaff();
			Rental rental = new Rental();
			rental.setRentalId(rs.getInt("rental_id"));
			rental.setRentalDate(rs.getString("rental_date"));
			rental.setInventoryId(rs.getInt("inventory_id"));
			rental.setCustomerId(rs.getInt("customer_id"));
			rental.setReturnDate(rs.getString("return_date"));
			rental.setStaffId(rs.getInt("staff_id"));
			rental.setLastUpdate(rs.getString("last_update"));
			raiacas.setRental(rental);
			
			Inventory inventory = new Inventory();
			inventory.setInventoryId(rs.getInt("inventory_id"));
			inventory.setFilmId(rs.getInt("film_id"));
			inventory.setStoreId(rs.getInt("store_id"));
			inventory.setLastUpdate(rs.getString("last_update"));
			raiacas.setInventory(inventory);
			
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
			raiacas.setCustomer(customer);
			
			Staff staff= new Staff();
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
	        raiacas.setStaff(staff);
	        list.add(raiacas);
			}
			return list;
		}
}	