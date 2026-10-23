package rw.isurafashion.service;
import rw.isurafashion.model.*; import rw.isurafashion.repository.*; import org.springframework.stereotype.Service; import java.util.*; import java.util.stream.*;
@Service public class ShopService {
 final ProductRepository products; final UserRepository users; final CartItemRepository cart; final WishlistItemRepository wish; final OrderRepository orders;
 public ShopService(ProductRepository p,UserRepository u,CartItemRepository c,WishlistItemRepository w,OrderRepository o){products=p;users=u;cart=c;wish=w;orders=o;}
 public List<Product> products(String q,String category){Stream<Product> s=products.findByPublishedTrueOrderByIdDesc().stream();if(q!=null&&!q.isBlank())s=s.filter(p->p.getName().toLowerCase().contains(q.toLowerCase())||p.getSku().toLowerCase().contains(q.toLowerCase()));if(category!=null&&!category.isBlank())s=s.filter(p->p.getCategory().equalsIgnoreCase(category));return s.toList();}
 public Product product(Long id){return products.findById(id).filter(Product::isPublished).orElseThrow(()->new NoSuchElementException("Product not found"));}
 public User user(Long id){return users.findById(id).orElseThrow();}
 public List<CartItem> cart(User u){return cart.findByUser(u);}
 public CartItem addCart(User u,Long pid,int qty){if(qty<1)throw new IllegalArgumentException("Quantity must be at least 1");Product p=product(pid);if(p.getStockQty()<qty)throw new IllegalArgumentException("Only "+p.getStockQty()+" item(s) remain");CartItem i=cart.findByUserAndProduct(u,p).orElseGet(()->{CartItem x=new CartItem();x.setUser(u);x.setProduct(p);x.setQuantity(0);return x;});if(i.getQuantity()+qty>p.getStockQty())throw new IllegalArgumentException("Quantity exceeds available stock");i.setQuantity(i.getQuantity()+qty);return cart.save(i);}
 public void updateCart(User u,Long itemId,int qty){CartItem i=cart.findById(itemId).orElseThrow();if(!i.getUser().getId().equals(u.getId()))throw new SecurityException();if(qty<=0){cart.delete(i);return;}if(qty>i.getProduct().getStockQty())throw new IllegalArgumentException("Quantity exceeds available stock");i.setQuantity(qty);cart.save(i);}
 public void removeCart(User u,Long itemId){CartItem i=cart.findById(itemId).orElseThrow();if(!i.getUser().getId().equals(u.getId()))throw new SecurityException();cart.delete(i);}
 public List<WishlistItem> wishlist(User u){return wish.findByUser(u);}
 public WishlistItem toggleWish(User u,Long pid){Product p=product(pid);if(p.getStockQty()<1)throw new IllegalArgumentException("Out-of-stock products cannot be added to wishlist");var existing=wish.findByUserAndProduct(u,p);if(existing.isPresent()){wish.delete(existing.get());return null;}var w=new WishlistItem();w.setUser(u);w.setProduct(p);return wish.save(w);}
 public List<Order> orders(User u){return orders.findByCustomerOrderByCreatedAtDesc(u);}
}
