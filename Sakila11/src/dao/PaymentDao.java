package dao;
import java.sql.*;
import java.util.*;
import util.*;
import vo.*;

public class PaymentDao {
	public ArrayList<PaymentAndCustomerAndStaffAndRental> selectSearchPaymentJoinListAll(int beginRow, int rowPerPage, int searchNo) throws Exception {
		ArrayList<PaymentAndCustomerAndStaffAndRental> list = new ArrayList<>();
		DBUtil dbUtil = new DBUtil();
		Connection conn = dbUtil.getConnection();
		String sql = "select pa.*, cu.*, st.*, re.* from payment pa inner join customer cu inner join staff st inner join rental re on pa.customer_id = cu.customer_id and pa.staff_id = st.staff_id and pa.rental_id = re.rental_id where pa.payment_id like ? order by pa.payment_id asc limit ?, ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, "%"+searchNo+"%");
		stmt.setInt(2, beginRow);
		stmt.setInt(3, rowPerPage);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			PaymentAndCustomerAndStaffAndRental paymentJoin = new PaymentAndCustomerAndStaffAndRental();
			Payment payment = new Payment();
			payment.setPaymentId(rs.getInt("payment_id"));
			payment.setCustomerId(rs.getInt("customer_id"));
			payment.setStaffId(rs.getInt("staff_id"));
			payment.setRentalId(rs.getInt("rental_id"));
			payment.setAmount(rs.getString("amount"));
			payment.setPaymentDate(rs.getString("payment_date"));
			payment.setLastUpdate(rs.getString("last_update"));
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
			Rental rental = new Rental();
			rental.setRentalId(rs.getInt("rental_id"));
			rental.setRentalDate(rs.getString("rental_date"));
			rental.setInventoryId(rs.getInt("inventory_id"));
			rental.setCustomerId(rs.getInt("customer_id"));
			rental.setReturnDate(rs.getString("return_date"));
			rental.setStaffId(rs.getInt("staff_id"));
			rental.setLastUpdate(rs.getString("last_update"));
			paymentJoin.setPayment(payment);
			paymentJoin.setCustomer(customer);
			paymentJoin.setStaff(staff);
			paymentJoin.setRental(rental);
			list.add(paymentJoin);
		}
		return list;
	}

	public ArrayList<Payment> selectPaymentListAll(int rowPerPage, int beginRow) throws Exception {
		DBUtil dbUtil = new DBUtil();
		Connection conn = dbUtil.getConnection();
		String sql = "SELECT * FROM payment LIMIT ?,?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, beginRow);
		stmt.setInt(2, rowPerPage);
		ResultSet rs = stmt.executeQuery();
		ArrayList<Payment> list = new ArrayList<Payment>();
		while(rs.next()) {
			Payment payment = new Payment();
			payment.setPaymentId(rs.getInt("payment_id"));
			payment.setCustomerId(rs.getInt("customer_id"));
			payment.setStaffId(rs.getInt("staff_id"));
			payment.setRentalId(rs.getInt("rental_id"));
			payment.setAmount(rs.getString("amount"));
			payment.setPaymentDate(rs.getString("payment_date"));
			payment.setLastUpdate(rs.getString("last_update"));
			list.add(payment);
		}
	return list;
	}
	
	public int selectTotalCount(int totalCount) throws Exception {
		DBUtil dbUtil = new DBUtil();
		Connection conn = dbUtil.getConnection();
		String sql="SELECT count(*) as count FROM payment";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		if(rs.next()) {
			totalCount = rs.getInt("count"); 
		}
		return totalCount;
	}
	public ArrayList<PaymentAndCustomerAndStaffAndRental> selectPaymentJoinListAll(int rowPerPage, int beginRow) throws Exception {
		ArrayList<PaymentAndCustomerAndStaffAndRental> list = new ArrayList<>();
		DBUtil dbUtil = new DBUtil();
		Connection conn = dbUtil.getConnection();
		String sql = "SELECT pa.*, cu.*, st.*, re.* "
				+ "FROM payment pa "
				+ "INNER JOIN customer cu "
				+ "INNER JOIN staff st "
				+ "INNER JOIN rental re "
				+ "ON pa.customer_id = cu.customer_id "
				+ "AND pa.staff_id = st.staff_id "
				+ "AND pa.rental_id = re.rental_id "
				+ "ORDER BY payment_id "
				+ "LIMIT ?,?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, beginRow);
		stmt.setInt(2, rowPerPage);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			PaymentAndCustomerAndStaffAndRental paymentJoin = new PaymentAndCustomerAndStaffAndRental();
			Payment payment = new Payment();
			payment.setPaymentId(rs.getInt("payment_id"));
			payment.setCustomerId(rs.getInt("customer_id"));
			payment.setStaffId(rs.getInt("staff_id"));
			payment.setRentalId(rs.getInt("rental_id"));
			payment.setAmount(rs.getString("amount"));
			payment.setPaymentDate(rs.getString("payment_date"));
			payment.setLastUpdate(rs.getString("last_update"));
			
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
			
			Rental rental = new Rental();
			rental.setRentalId(rs.getInt("rental_id"));
			rental.setRentalDate(rs.getString("rental_date"));
			rental.setInventoryId(rs.getInt("inventory_id"));
			rental.setCustomerId(rs.getInt("customer_id"));
			rental.setReturnDate(rs.getString("return_date"));
			rental.setStaffId(rs.getInt("staff_id"));
			rental.setLastUpdate(rs.getString("last_update"));
			paymentJoin.setPayment(payment);
			paymentJoin.setCustomer(customer);
			paymentJoin.setStaff(staff);
			paymentJoin.setRental(rental);
			list.add(paymentJoin);
		}
		return list;
	}
}
