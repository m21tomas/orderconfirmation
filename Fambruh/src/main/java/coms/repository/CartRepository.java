package coms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

import coms.model.cartorder.CartItem;
import coms.model.user.*;
public interface CartRepository extends JpaRepository<CartItem, Long> {
	public List<CartItem> findByUser(User user);
	public List<CartItem> findByUserUsername(String username);
}