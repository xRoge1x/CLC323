// ShoppingCartController.java
package com.OutdoorLife.controller;

import com.OutdoorLife.model.ProductModel;
import com.OutdoorLife.service.ProductService;
import com.OutdoorLife.service.ShoppingCartService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cart")
public class ShoppingCartController {
    private final ShoppingCartService cartService;
    private final ProductService productService;

    public ShoppingCartController(ShoppingCartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }

    @GetMapping
    public ModelAndView viewCart() {
        ModelAndView modelAndView = new ModelAndView("cart");
        modelAndView.addObject("cart", cartService.getShoppingCart());
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView showAddToCartForm() {
        ModelAndView modelAndView = new ModelAndView("addToCart");
        modelAndView.addObject("products", productService.getAllProducts());
        return modelAndView;
    }

    @PostMapping("/add")
    public String addToCart(@RequestParam Long productId, @RequestParam int quantity) {
        ProductModel product = productService.getProductById(productId);
        cartService.addToCart(product, quantity);
        return "redirect:/cart";
    }

    @PostMapping("/remove/{index}")
    public String removeFromCart(@PathVariable int index) {
        cartService.removeFromCart(index);
        return "redirect:/cart";
    }

    @PostMapping("/checkout")
    public String checkout() {
        // Redirect to the summary page
        return "redirect:/cart/summary";
    }

    @GetMapping("/summary")
    public ModelAndView showSummary() {
        ModelAndView modelAndView = new ModelAndView("summary");
        modelAndView.addObject("cart", cartService.getShoppingCart());
        modelAndView.addObject("totalPrice", cartService.getTotalPrice());
        return modelAndView;
    }

    @PostMapping("/confirm")
    public String confirmCheckout() {
        // Clear the shopping cart upon checkout
        cartService.clearCart();
        return "redirect:/cart";
    }
}
