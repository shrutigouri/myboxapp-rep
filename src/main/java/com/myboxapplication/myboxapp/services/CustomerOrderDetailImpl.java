package com.myboxapplication.myboxapp.services;

import com.myboxapplication.myboxapp.exceptions.CustOrderDetailsNotFoundException;
import com.myboxapplication.myboxapp.models.CartMenuData;
import com.myboxapplication.myboxapp.models.OrderCartDetailResonse;
import com.myboxapplication.myboxapp.models.OrderCartRequestData;
import com.myboxapplication.myboxapp.models.mysql.Cart;
import com.myboxapplication.myboxapp.models.mysql.CustomerOrder;
import com.myboxapplication.myboxapp.models.mysql.CustomerOrderDetail;
import com.myboxapplication.myboxapp.models.mysql.Menu;
import com.myboxapplication.myboxapp.models.mysql.Restaurant;
import com.myboxapplication.myboxapp.models.mysql.User;
import com.myboxapplication.myboxapp.repository.CustomerOrderDetailRepository;
import com.myboxapplication.myboxapp.repository.CustomerPaymentDetailRepository;
import com.myboxapplication.myboxapp.repository.MenuRepository;
import com.myboxapplication.myboxapp.repository.RestaurantRepository;
import com.myboxapplication.myboxapp.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerOrderDetailImpl implements CustomerOrderDetailService {

    @Autowired
    CustomerOrderDetailRepository customerOrderDetailRepository;

    @Autowired
    CustomerOrderService customerOrderService;
    
    @Autowired
    CartService cartService;
    
    @Autowired
    MenuService menuService;
    
    @Autowired
    CustomerPaymentDetailService customerPaymentDetailService;
     
    @Autowired
    UserService userService;
    
    
    @Autowired
    RestaurentService restaurantService;


    @Override
    public Page<CustomerOrderDetail> getAllCustomerOrderDetail(Pageable pageable) {
        return customerOrderDetailRepository.findAll(pageable);
    }

    @Override
    public OrderCartDetailResonse addCustomerOrderDetail(OrderCartRequestData orderCartRequestData) {
    	CustomerOrderDetail customerOrderDetail = new CustomerOrderDetail();
        CustomerOrder customerOrder = customerOrderService.addCustomerOrder(orderCartRequestData);
		User user = userService.getUserByUserId(orderCartRequestData.getUserId());
		Restaurant restaurant = restaurantService.getByRestaurantId(orderCartRequestData.getRestaurantId());
	
		Menu menu = null;
		Cart cart = null;
		List<Cart> cartItems = new ArrayList<Cart>();
		for(CartMenuData cartMenuData : orderCartRequestData.getMenuList()) {		
			menu = menuService.getMenuByFoodItemId(cartMenuData.getFoodItemId());
			if(menu != null ) {
				cart = new Cart();
				cart.setCustomerOrderId(customerOrder);
				cart.setMenu(menu);
				cart.setOrderCartDate(orderCartRequestData.getOrderDate());
				cart.setPrice(menu.getFoodItemPrice());
				cart.setQuantity(cartMenuData.getQuantity());
				cartService.addCart(cart);
				cartItems.add(cart);
			}
		}
		       
       // List<Cart> cartItems = cartService.getCartByCustomerOrderId(customerOrderDetail.getCustomerOrder().getCustomerOrderId());
        Integer quantity = 0;
        Double amount = 0.0;
        for(Cart cartData:cartItems) {
        	quantity = quantity + cartData.getQuantity();
        	amount = amount + cartData.getAmount();
        }

        customerOrderDetail.setCustomerOrder(customerOrder);
		customerOrderDetail.setUser(user);
		customerOrderDetail.setRestaurant(restaurant);
		customerOrderDetail.setDeliveryTime(orderCartRequestData.getOrderDeliveryTime());
		customerOrderDetail.setOrderDetailDate(orderCartRequestData.getOrderDate());		
        customerOrderDetail.setTotalAmount(amount);
        customerOrderDetail.setTotalQuantity(quantity);
        customerOrderDetail.setDeliveryTime(orderCartRequestData.getOrderDeliveryTime());
        CustomerOrderDetail customerOrderDetail2 = customerOrderDetailRepository.save(customerOrderDetail);
        OrderCartDetailResonse orderCartDetailResonse =null;
        if (customerOrderDetail2 != null) {
        	orderCartDetailResonse = new OrderCartDetailResonse();
        	orderCartDetailResonse.setCart(cartItems);
        	orderCartDetailResonse.setCustomerOrderId(customerOrder.getCustomerOrderId());
        	orderCartDetailResonse.setRestaurantName(restaurant.getRestaurantName());
        	orderCartDetailResonse.setLocation(restaurant.getRestaurantLocation());
        	orderCartDetailResonse.setUserName(user.getUsername());
        	orderCartDetailResonse.setTotalAmount(amount);
        	orderCartDetailResonse.setTotalQuatity(quantity);
        }
        return orderCartDetailResonse;
    }


    @Override
    public CustomerOrderDetail updateCustomerOrderDetail(long orderCartDetailId,  CustomerOrderDetail customerOrderDetail) {

        CustomerOrderDetail customerOrderDetail1 = customerOrderDetailRepository.findByOrderCartDetailId(orderCartDetailId);

            if (customerOrderDetail1==null){

            throw new CustOrderDetailsNotFoundException("CustomerOrderDetail not Found= " +orderCartDetailId);
           }

            List<Cart> cartItems = cartService.getCartByCustomerOrderId(customerOrderDetail.getCustomerOrder().getCustomerOrderId());
            Integer quantity = 0;
            Double amount = 0.0;
            for(Cart cart:cartItems) {
            	quantity = quantity + cart.getQuantity();
            	amount = amount + cart.getAmount();
            }
            customerOrderDetail1.setTotalAmount(amount);
            //customerOrderDetail1.setCart(cartItems);
            customerOrderDetail1.setTotalQuantity(quantity);
            return customerOrderDetailRepository.save(customerOrderDetail1);

    }
    
    @Override
    public CustomerOrderDetail deleteCustomerOrderDetail(long orderCartDetailId, CustomerOrderDetail customerOrderDetail) {

        CustomerOrderDetail customerOrderDetail1 = customerOrderDetailRepository.findByOrderCartDetailId(orderCartDetailId);

            if (customerOrderDetail1==null){

            throw new CustOrderDetailsNotFoundException("CustomerOrderDetail not Found= " +orderCartDetailId);
           }
            List<Cart> cartItems = cartService.getCartByCustomerOrderId(customerOrderDetail.getCustomerOrder().getCustomerOrderId());
            Integer quantity = 0;
            Double amount = 0.0;
            for(Cart cart:cartItems) {
            	quantity = quantity + cart.getQuantity();
            	amount = amount + cart.getAmount();
            }
            customerOrderDetail1.setTotalAmount(amount);
            //customerOrderDetail1.setCart(cartItems);
            customerOrderDetail1.setTotalQuantity(quantity);;
        return customerOrderDetailRepository.save(customerOrderDetail1);

    }

    @Override
    public CustomerOrderDetail getCustomerOrderDetailByOrderCartDetailId(long orderCartDetailId) {
        return customerOrderDetailRepository.findByOrderCartDetailId(orderCartDetailId);
    }

  
}
