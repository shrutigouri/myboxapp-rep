package com.myboxapplication.myboxapp.services;

import com.myboxapplication.myboxapp.models.mysql.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CartService {

    List<Cart> getAllCart();

    Cart addCart(Cart cart);

    public Cart updateCart(long cartId,  Cart cart);

    Cart deleteCart(long cartId,  Cart cart);

    List<Cart> getCartByCustomerOrderId(String customerOrderId);

    Cart getCartByCartId(long cartId);

}
