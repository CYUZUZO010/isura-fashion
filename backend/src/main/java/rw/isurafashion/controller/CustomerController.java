package rw.isurafashion.controller;
import rw.isurafashion.model.*; import rw.isurafashion.service.*; import org.springframework.web.bind.annotation.*; import jakarta.servlet.http.HttpServletRequest; import java.util.*;
@RestController @RequestMapping("/api") public class CustomerController {final ShopService shop;final OrderService order;final rw.isurafashion.repository.UserRepository users; public CustomerController(ShopService s,OrderService o,rw.isurafashion.repository.UserRepository u){shop=s;order=o;users=u;} User user(HttpServletRequest r){return shop.user((Long)r.getAttribute("userId"));}
 @GetMapping("/me") public User me(HttpServletRequest r){return user(r);}
 @GetMapping("/cart") public List<CartItem> cart(HttpServletRequest r){return shop.cart(user(r));}
 @PostMapping("/cart") public CartItem add(@RequestBody Map<String,Object>b,HttpServletRequest r){return shop.addCart(user(r),Long.valueOf(b.get("productId").toString()),Integer.parseInt(b.get("quantity").toString()));}
 @PatchMapping("/cart/{id}") public void update(@PathVariable Long id,@RequestBody Map<String,Object>b,HttpServletRequest r){shop.updateCart(user(r),id,Integer.parseInt(b.get("quantity").toString()));}
 @DeleteMapping("/cart/{id}") public void remove(@PathVariable Long id,HttpServletRequest r){shop.removeCart(user(r),id);}
 @GetMapping("/wishlist") public List<WishlistItem> wish(HttpServletRequest r){return shop.wishlist(user(r));}
 @PostMapping("/wishlist/{pid}") public WishlistItem wishToggle(@PathVariable Long pid,HttpServletRequest r){return shop.toggleWish(user(r),pid);}
 @GetMapping("/orders") public List<Order> orders(HttpServletRequest r){return shop.orders(user(r));}
 @PostMapping("/checkout") public Order checkout(@RequestBody Map<String,String>b,HttpServletRequest r){return order.checkout(user(r),b.get("paymentMethod"),b.get("paymentResult"),b.get("discountCode"),b.get("idempotencyKey"));}
}
