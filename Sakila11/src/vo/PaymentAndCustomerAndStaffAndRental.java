package vo;

public class PaymentAndCustomerAndStaffAndRental {
	private Payment payment;
	private Customer customer;
	private Staff staff;
	private Rental rental;
	
	public Payment getPayment() {
		return payment;
	}
	public Customer getCustomer() {
		return customer;
	}
	public Staff getStaff() {
		return staff;
	}
	public Rental getRental() {
		return rental;
	}
	public void setPayment(Payment payment) {
		this.payment=payment;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	public void setRental(Rental rental) {
		this.rental = rental;
	}
}
