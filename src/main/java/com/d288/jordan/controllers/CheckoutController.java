package com.d288.jordan.controllers;

import com.d288.jordan.services.CheckoutService;
import com.d288.jordan.services.Purchase;
import com.d288.jordan.services.PurchaseResponse;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {

    private final CheckoutService checkoutService;

    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping("/purchase")
    public PurchaseResponse placeOrder(@Valid @RequestBody Purchase purchase) {
        return checkoutService.placeOrder(purchase);
    }
}
