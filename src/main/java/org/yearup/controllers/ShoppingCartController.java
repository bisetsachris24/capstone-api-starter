package org.yearup.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;
import org.yearup.models.User;
import org.yearup.service.ShoppingCartService;
import org.yearup.service.UserService;

import java.security.Principal;

@RestController
@RequestMapping("cart")
@CrossOrigin
// Requires the user to be logged in before accessing any cart endpoints
@PreAuthorize("isAuthenticated()")
public class ShoppingCartController
{
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;

    public ShoppingCartController(ShoppingCartService shoppingCartService, UserService userService)
    {
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
    }

    /**
     * Retrieves the current user's shopping cart.
     */
    @GetMapping("")
    public ShoppingCart getCart(Principal principal)
    {
        User user = userService.getByUserName(principal.getName());
        return shoppingCartService.getByUserId(user.getId());
    }

    /**
     * Adds a product to the current user's shopping cart.
     * Returns the updated cart after the item is added.
     */
    @PostMapping("products/{productId}")
    public ResponseEntity<ShoppingCart> addProduct(@PathVariable int productId, Principal principal)
    {
        User user = userService.getByUserName(principal.getName());
        ShoppingCart cart = shoppingCartService.addProduct(user.getId(), productId);

        return ResponseEntity.status(HttpStatus.CREATED).body(cart);
    }

    /**
     * Updates the quantity of a product already in the cart.
     */
    @PutMapping("products/{productId}")
    public ShoppingCart updateProduct(@PathVariable int productId,
                                      @RequestBody ShoppingCartItem item,
                                      Principal principal)
    {
        User user = userService.getByUserName(principal.getName());
        return shoppingCartService.updateProductQuantity(
                user.getId(),
                productId,
                item.getQuantity()
        );
    }

    /**
     * Removes all items from the current user's shopping cart.
     */
    @DeleteMapping("")
    public ShoppingCart clearCart(Principal principal)
    {
        User user = userService.getByUserName(principal.getName());
        return shoppingCartService.clearCart(user.getId());
    }
}