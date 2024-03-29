package coms.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coms.model.cartorder.CartItem;
import coms.model.cartorder.UserOrder;
import coms.model.product.Product;
import coms.model.product.ProductQuantity;
import coms.model.product.comboproduct;
import coms.repository.Order;
import coms.repository.OrderRepo;
import coms.repository.ProductQuantityRepo;

@Service
public class UserOrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ProductQuantityRepo productQuantityRepo;
    
    public UserOrder saveOrder(UserOrder userOrder) {
		UserOrder orderSaved = this.orderRepo.save(userOrder);
		return orderSaved;
	}
	public void saveProductQuantity(ProductQuantity productQuantity) {
		this.productQuantityRepo.save(productQuantity);
	}
	
	public List<UserOrder> getAll(){
		return this.orderRepo.findAll();
	}
	
	public List<UserOrder> getUserOrders(String username){
		List<UserOrder> orders = this.orderRepo.findByUsername(username);
		return orders;
	}
	
	public UserOrder getOrderById(Long oid) {
		UserOrder order = this.orderRepo.findById(oid).get();
		return order;
	}
	
	public void deleteOrder(Long oid) {
		this.orderRepo.deleteById(oid);
	}

}
