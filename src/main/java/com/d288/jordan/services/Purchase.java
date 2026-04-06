package com.d288.jordan.services;

import com.d288.jordan.entities.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotEmpty;
import com.d288.jordan.entities.Customer;
import java.util.Set;

public class Purchase {

    @NotNull
    @Valid
    private Customer customer;

    @NotNull
    @Valid
    private Cart cart;

    @NotEmpty
    @Valid
    private Set<CartItem> cartItems;

    public Purchase() {}

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Set<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(Set<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
}
