package coms.model.cartorder;

import java.util.HashSet;
import java.util.Set;

import coms.model.product.ProductQuantity;

public class CartOrder {
    private String username;
    private String firstname;
    private String lastname;
    private String address;
    private String district;
    private int pinCode;
    private String state;
    private String contact;
    private Double paidAmount;
    private String paymentMode;
    private Set<CartItem> cartItems = new HashSet<>();

    public CartOrder() {
    }



	public CartOrder(String username, String firstname, String lastname, String address, String district, int pinCode,
			String state, String contact, Double paidAmount, String paymentMode, Set<CartItem> cartItems) {
		super();
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = address;
		this.district = district;
		this.pinCode = pinCode;
		this.state = state;
		this.contact = contact;
		this.paidAmount = paidAmount;
		this.paymentMode = paymentMode;
		this.cartItems = cartItems;
	}



	public String getFirstname() {
		return firstname;
	}



	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}



	public String getLastname() {
		return lastname;
	}



	public void setLastname(String lastname) {
		this.lastname = lastname;
	}



	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public int getPinCode() {
		return pinCode;
	}

	public void setPinCode(int pinCode) {
		this.pinCode = pinCode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public Double getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(Double paidAmount) {
		this.paidAmount = paidAmount;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public Set<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(Set<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

   
}
