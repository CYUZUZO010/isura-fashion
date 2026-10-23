package rw.isurafashion.controller;
import rw.isurafashion.service.ShopService; import rw.isurafashion.model.Product; import org.springframework.web.bind.annotation.*; import java.util.*;
@RestController @RequestMapping("/api/products") public class ProductController {final ShopService shop; public ProductController(ShopService s){shop=s;} @GetMapping public List<Product> list(@RequestParam(required=false)String q,@RequestParam(required=false)String category){return shop.products(q,category);} @GetMapping("/{id}") public Product one(@PathVariable Long id){return shop.product(id);}}
