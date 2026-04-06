package com.d288.jordan.services;

import com.d288.jordan.dao.CartRepository;
import com.d288.jordan.dao.CustomerRepository;
import com.d288.jordan.entities.Cart;
import com.d288.jordan.entities.CartItem;
import com.d288.jordan.entities.Customer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private final CartRepository cartRepository;
    private final CustomerRepository customerRepository;

    public CheckoutServiceImpl(CartRepository cartRepository,
                               CustomerRepository customerRepository) {
        this.cartRepository = cartRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {

        Customer incomingCustomer = purchase.getCustomer();
        Cart cart = purchase.getCart();
        // Angular sends id = 0 for new carts → normalize for JPA
        if (cart.getId() != null && cart.getId() == 0) {
            cart.setId(null);
        }
        Set<CartItem> cartItems = purchase.getCartItems();

        Long customerId = incomingCustomer.getId();
        Customer dbCustomer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found: " + customerId));

        // DO NOT null cart id - Angular is sending an existing cart
        cart.setCustomer(dbCustomer);

        String orderTrackingNumber = UUID.randomUUID().toString();
        cart.setOrderTrackingNumber(orderTrackingNumber);

        for (CartItem item : cartItems) {

            if (item.getId() != null && item.getId() == 0) {
                item.setId(null);
            }

            item.setCart(cart);
        }

        cart.setCartItems(cartItems);

        cartRepository.save(cart);

        return new PurchaseResponse(orderTrackingNumber);
    }
}
