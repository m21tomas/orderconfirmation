package coms.controller;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import coms.configuration.ImageUtil;
import coms.model.*;
import coms.model.product.*;
import coms.model.cartorder.CartItem;
import coms.model.cartorder.*;
import coms.model.product.Product;
import coms.model.product.ProductQuantity;
import coms.service.ProductService;
import coms.service.UserOrderService;
@RestController
@CrossOrigin(origins = "*")
public class UserOrderController {
	
	@Autowired
	private UserOrderService userOrderService;
	
	@Autowired
	private ProductService productService;
	
	@PreAuthorize("hasAuthority('USER')")
	@PostMapping("/user/create/order")
	public ResponseEntity<?> createOrder(@Valid @RequestBody CartOrder cartOrder){
		
		UserOrder userOrder = new UserOrder();
		userOrder.setUsername(cartOrder.getUsername());
		userOrder.setAddress(cartOrder.getAddress());
		userOrder.setDistrict(cartOrder.getDistrict());
		userOrder.setState(cartOrder.getState());
		userOrder.setContact(cartOrder.getContact());
		userOrder.setPinCode(cartOrder.getPinCode());
		
		DateFormat df = DateFormat.getDateInstance();
		Calendar cl = Calendar.getInstance();
		String orderDate = df.format(cl.getTime());
		userOrder.setDate(orderDate);
		userOrder.setStatus("PLACED");
		userOrder.setPaidAmount(cartOrder.getPaidAmount());
		userOrder.setPaymentMode(cartOrder.getPaymentMode());
		Set<CartItem> cartItems  = cartOrder.getCartItems();
		Set<ProductQuantity> pq = new HashSet<>();
		for(CartItem item : cartItems) {
			Product product = this.productService.findProduct(item.getid());
			int quantity = item.getQuantity();
			ProductQuantity productQuantity = new ProductQuantity();
			productQuantity.setProduct(product);
			productQuantity.setQuantity(quantity);
			this.userOrderService.saveProductQuantity(productQuantity);
			pq.add(productQuantity);
		}
		
		userOrder.setProducts(pq);
		UserOrder orderCreated = this.userOrderService.saveOrder(userOrder);
		return ResponseEntity.ok(orderCreated);
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/get/all/orders")
	public ResponseEntity<?> getAllOrders(){
		List<UserOrder> orders = this.userOrderService.getAll();
		return ResponseEntity.ok(orders);
	}
	
	@PreAuthorize("hasAuthority('USER')")
	@GetMapping("/get/orders/{username}")
	public ResponseEntity<?> userOrders(@PathVariable("username") String username){
		List<UserOrder> orders = this.userOrderService.getUserOrders(username);
		if(orders.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}else {
			return ResponseEntity.ok(orders);
		}
	}
	
	@PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
	@GetMapping("/get/order-invoice/{oid}")
	public ResponseEntity<?> getUserOrderById(@PathVariable("oid") Long oid){
		UserOrder order = this.userOrderService.getOrderById(oid);
		Set<ProductQuantity> products = order.getProducts();
		products.forEach(p -> {
			ProductImageMain img1 = new ProductImageMain();
			img1.setImageData(ImageUtil.decompressImage(p.getProduct().getMainImage().getImageData()));
			img1.setName(p.getProduct().getMainImage().getName());
			img1.setImgId(p.getProduct().getMainImage().getImgId());
			img1.setType(p.getProduct().getMainImage().getType());
			p.getProduct().setMainImage(img1);
		});
		order.setProducts(products);
		return ResponseEntity.ok(order);
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping("/delete/order/{oid}")
	public ResponseEntity<?> deleteOrderById(@PathVariable("oid") Long oid){
		this.userOrderService.deleteOrder(oid);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

}
