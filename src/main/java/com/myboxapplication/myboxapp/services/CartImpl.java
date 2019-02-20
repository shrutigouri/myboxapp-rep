package com.myboxapplication.myboxapp.services;

import com.myboxapplication.myboxapp.exceptions.CustOrderDetailsNotFoundException;
import com.myboxapplication.myboxapp.models.mysql.Cart;
import com.myboxapplication.myboxapp.models.mysql.Menu;
import com.myboxapplication.myboxapp.models.mysql.Restaurant;
import com.myboxapplication.myboxapp.repository.CartRepository;
import com.myboxapplication.myboxapp.repository.CustomerPaymentDetailRepository;
import com.myboxapplication.myboxapp.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.UUID;
import java.util.Date;
import java.util.List;

@Service
public class CartImpl implements CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    MenuRepository menuRepository;


    @Override
    public List<Cart> getAllCart() {
        return cartRepository.findAll();
    }

    @Override
    public Cart addCart(Cart cart) {
        Cart cart1 = new Cart();

        Menu menu = menuRepository.findMenuByFoodItemId(cart.getMenu().getFoodItemId());


        // Cart cart2 = (Cart) cartRepository.findCartByFoodItemIdAndOrderCartId(cart.getMenu().getFoodItemId(),cart.getOrderCartId());

          if (cart != null && cart.getCustomerOrderId() != null) {
              //cart1.se(Math.round(((cart.getPrice() * cart.getQuantity()) * 100) / 100));
              cart1.setPrice(menu.getFoodItemPrice());
              cart1.setQuantity(cart.getQuantity());
             // String orderUniqueId = UUID.randomUUID().toString();
              cart1.setCustomerOrderId(cart.getCustomerOrderId());
              cart1.setOrderCartDate(new Date().toString());
              cart1.setMenu(menu);
              return cartRepository.save(cart1);
          }
          return cart1;
    }


    @Override
    public Cart updateCart(long cartId,  Cart cart) {

        Cart cart1 = cartRepository.findByCartId(cartId);

            if (cart1==null){

            throw new CustOrderDetailsNotFoundException("Cart not Found= " +cartId);
           }

        cart1.setPrice(cart.getPrice());
        cart1.setQuantity(cart.getQuantity());
       // cart1.setAmount(Math.round(((cart.getPrice()*cart.getQuantity())*100)/100));
       // cart1.setCustomerOrderId(cart.getCustomerOrderId());
        return cartRepository.save(cart1);

    }
    
    @Override
    public Cart deleteCart(long cartId,  Cart cart) {

        Cart cart1 = cartRepository.findByCartId(cartId);

            if (cart1==null){

            throw new CustOrderDetailsNotFoundException("Cart not Found= " +cartId);
           }

        cart1.setPrice(cart.getPrice());
        cart1.setQuantity(cart.getQuantity());
       // cart1.setAmount(Math.round(((cart.getPrice()*cart.getQuantity())*100)/100));
        //cart1.setOrderCartId(cart.getOrderCartId());
        return cartRepository.save(cart1);

    }


    @Override
    public Cart getCartByCartId(long cartId) {
        return cartRepository.findByCartId(cartId);
    }

    @Override
    public List<Cart> getCartByCustomerOrderId(String customerOrderId) {
        return cartRepository.findByCustomerOrderId(customerOrderId);
    }
}
