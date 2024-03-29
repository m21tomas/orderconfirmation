package coms.model.cartorder;

import javax.persistence.*;
import coms.model.product.ProductQuantity;
import coms.repository.Order;

import java.util.HashSet;
import java.util.Set;

@Entity
public class UserOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;

    private String username;
    private String firstname;
    private String lastname;
    private String address;
    private String district;
    private int pinCode;
    private String state;
    private String contact;
    private String date;

    
    private String status;

    private String returnStatus; // Status for return functionality
    private String replacementStatus; // Status for replacement functionality
    private Double paidAmount;
    private String paymentMode;
    private boolean confirmationEmailSent; // New field for order confirmation email
    private boolean addressVerified; // New field for address verification

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<ProductQuantity> products = new HashSet<>();

    public UserOrder() {
    }

    public UserOrder(Long orderId, String username, String firstname, String lastname, String address, String district,
			int pinCode, String state, String contact, String date, String status, String returnStatus,
			String replacementStatus, Double paidAmount, String paymentMode, boolean confirmationEmailSent,
			boolean addressVerified, Set<ProductQuantity> products) {
		super();
		this.orderId = orderId;
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = address;
		this.district = district;
		this.pinCode = pinCode;
		this.state = state;
		this.contact = contact;
		this.date = date;
		this.status = status;
		this.returnStatus = returnStatus;
		this.replacementStatus = replacementStatus;
		this.paidAmount = paidAmount;
		this.paymentMode = paymentMode;
		this.confirmationEmailSent = confirmationEmailSent;
		this.addressVerified = addressVerified;
		this.products = products;
	}

	public UserOrder(String username, String address, String district, int pinCode, String state, String contact, String date, String status, String returnStatus, String replacementStatus, Double paidAmount, String paymentMode, boolean confirmationEmailSent, boolean addressVerified, Set<ProductQuantity> products) {
        this.username = username;
        this.address = address;
        this.district = district;
        this.pinCode = pinCode;
        this.state = state;
        this.contact = contact;
        this.date = date;
        this.status = status;
        this.returnStatus = returnStatus;
        this.replacementStatus = replacementStatus;
        this.paidAmount = paidAmount;
        this.paymentMode = paymentMode;
        this.confirmationEmailSent = confirmationEmailSent;
        this.addressVerified = addressVerified;
        this.products = products;
    }

    // Getters and setters...

    public Long getOrderId() {
        return orderId;
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

	public void setOrderId(Long orderId) {
        this.orderId = orderId;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(String returnStatus) {
        this.returnStatus = returnStatus;
    }

    public String getReplacementStatus() {
        return replacementStatus;
    }

    public void setReplacementStatus(String replacementStatus) {
        this.replacementStatus = replacementStatus;
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

    public boolean isConfirmationEmailSent() {
        return confirmationEmailSent;
    }

    public void setConfirmationEmailSent(boolean confirmationEmailSent) {
        this.confirmationEmailSent = confirmationEmailSent;
    }

    public boolean isAddressVerified() {
        return addressVerified;
    }

    public void setAddressVerified(boolean addressVerified) {
        this.addressVerified = addressVerified;
    }

    public Set<ProductQuantity> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductQuantity> products) {
        this.products = products;
    }
}
