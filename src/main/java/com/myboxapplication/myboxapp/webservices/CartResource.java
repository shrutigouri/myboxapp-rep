package com.myboxapplication.myboxapp.webservices;

import com.myboxapplication.myboxapp.models.mysql.Cart;
import com.myboxapplication.myboxapp.services.CartService;
import com.myboxapplication.myboxapp.services.ResponseGenerator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartResource {

    @Autowired
    ResponseGenerator responseGenerator;

    @Autowired
    CartService cartService;

    @GetMapping("/getAll/public")
    public ResponseEntity getCartItems() {
    	List<Cart> cartList = cartService.getAllCart();
        if(!cartList.isEmpty() && cartList != null) {
        	return ResponseEntity
                .ok(responseGenerator
                        .success(cartList,"cart.list"));
        } else {
        	 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add/public")
    public ResponseEntity addItemToCart(@RequestBody Cart cart) {
    	Cart cartAdd = cartService.addCart(cart);
    	if(cart != null) {
             return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseGenerator
                        .success(cartAdd,"cart.added"));
    	}else {
    		 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	}
    }

    @GetMapping("/get/order/{id}/public")
    public ResponseEntity getCartsById(@PathVariable("id") String customerOrderId){
    	List<Cart> cartUpd = cartService.getCartByCustomerOrderId(customerOrderId);
    	if(!cartUpd.isEmpty() && cartUpd != null) {
    		return ResponseEntity
                .ok(responseGenerator
                        .success(cartUpd ,"cart.found"));
    	} else {
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	}
    }

    @PutMapping("update/{id}/public")
    public ResponseEntity updateCartItem(@RequestBody Cart cart,@PathVariable("id") long cartId){
    	Cart cartUpd = cartService.updateCart(cartId,cart);
    	if(cartUpd != null)
    		return ResponseEntity
                .ok(responseGenerator
                        .success(cartUpd,"cart.updated"));
    	else
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("delete/{id}/public")
    public ResponseEntity deleteCartItem(@RequestBody Cart cart,@PathVariable("id") long cartId){
    	Cart cartDel = cartService.deleteCart(cartId,cart);
    	if(cartDel != null)
        return ResponseEntity
                .ok(responseGenerator
                        .success(cartService.deleteCart(cartId,cart),"cart.updated"));
    	else
    		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/get/{cartId}/public")
    public ResponseEntity getCartsByCartId(@PathVariable("cartId") long cartId){
       //System.out.println(cartService.getCartByCartId(cartId));
    	Cart cartList = cartService.getCartByCartId(cartId);
    	if(cartList != null)
    		return ResponseEntity
                .ok(responseGenerator
                .success(cartList,"cart.found"));
        else
        	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
