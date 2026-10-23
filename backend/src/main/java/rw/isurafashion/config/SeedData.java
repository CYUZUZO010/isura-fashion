package rw.isurafashion.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import rw.isurafashion.model.Product;
import rw.isurafashion.model.User;
import rw.isurafashion.repository.ProductRepository;
import rw.isurafashion.repository.UserRepository;

@Configuration
public class SeedData {

    @Bean
    CommandLineRunner seed(
            UserRepository users,
            ProductRepository products
    ) {
        return args -> {

            if (users.count() == 0) {
                var enc = new BCryptPasswordEncoder();

                var admin = new User();
                admin.setEmail("admin@isurafashion.rw");
                admin.setPasswordHash(enc.encode("Admin1234"));
                admin.setPhone("0780000000");
                admin.setRole("ADMIN");
                users.save(admin);

                var u = new User();
                u.setEmail("demo@isurafashion.rw");
                u.setPasswordHash(enc.encode("Customer1234"));
                u.setPhone("0781111111");
                users.save(u);
            }

            if (products.count() == 0) {

                products.save(p(
                        "ISU-DRESS-01",
                        "Amani Linen Dress",
                        "Dresses",
                        "Lightweight linen dress with a clean relaxed cut.",
                        42000,
                        "M",
                        "Ivory",
                        12,
                        "https://images.unsplash.com/photo-1595777457583-95e059d581b8?auto=format&fit=crop&w=900&q=80"
                ));

                products.save(p(
                        "ISU-SHIRT-01",
                        "Kivu Relaxed Shirt",
                        "Tops",
                        "Soft cotton shirt designed for everyday wear.",
                        28000,
                        "L",
                        "Sand",
                        20,
                        "https://images.unsplash.com/photo-1602810318383-e386cc2a3ccf?auto=format&fit=crop&w=900&q=80"
                ));

                products.save(p(
                        "ISU-SKIRT-01",
                        "Mwezi Midi Skirt",
                        "Bottoms",
                        "Structured midi skirt with a simple modern silhouette.",
                        36000,
                        "M",
                        "Black",
                        8,
                        "https://images.unsplash.com/photo-1551488831-00ddcb6c6bd3?auto=format&fit=crop&w=900&q=80"
                ));

                products.save(p(
                        "ISU-JACKET-01",
                        "Umucyo Jacket",
                        "Outerwear",
                        "Minimal tailored jacket for evenings and events.",
                        68000,
                        "M",
                        "Charcoal",
                        5,
                        "https://images.unsplash.com/photo-1544022613-e87ca75a784a?auto=format&fit=crop&w=900&q=80"
                ));

                products.save(p(
                        "ISU-TEE-01",
                        "Isura Essential Tee",
                        "Tops",
                        "Premium everyday tee with a clean neckline.",
                        18000,
                        "M",
                        "White",
                        0,
                        "https://images.unsplash.com/photo-1521572163474-6864f9cf17ab?auto=format&fit=crop&w=900&q=80"
                ));
            }
        };
    }

    private Product p(
            String sku,
            String name,
            String cat,
            String desc,
            long price,
            String size,
            String colour,
            int stock,
            String image
    ) {
        var p = new Product();

        p.setSku(sku);
        p.setName(name);
        p.setCategory(cat);
        p.setDescription(desc);
        p.setPriceRwf(price);
        p.setSize(size);
        p.setColour(colour);
        p.setStockQty(stock);
        p.setImageUrl(image);

        return p;
    }
}