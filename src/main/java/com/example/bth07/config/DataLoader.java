package com.example.bth07.config;

import com.example.bth07.entity.Category;
import com.example.bth07.entity.Product;
import com.example.bth07.entity.User;
import com.example.bth07.repository.CategoryRepository;
import com.example.bth07.repository.ProductRepository;
import com.example.bth07.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepo;
    private final CategoryRepository categoryRepo;
    private final ProductRepository productRepo;

    public DataLoader(UserRepository userRepo, CategoryRepository categoryRepo, ProductRepository productRepo) {
        this.userRepo = userRepo;
        this.categoryRepo = categoryRepo;
        this.productRepo = productRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepo.count() == 0) {
            User admin = new User(null, "Admin User", "admin@example.com", "123456789", "password", User.Role.ADMIN, null);
            User regularUser = new User(null, "Regular User", "user@example.com", "987654321", "password", User.Role.USER, null);
            userRepo.saveAll(List.of(admin, regularUser));
            System.out.println("Created initial users.");
        }

        if (categoryRepo.count() == 0) {
            Category electronics = new Category(null, "Electronics", "Devices and gadgets", null, null);
            Category books = new Category(null, "Books", "Printed and digital books", null, null);
            Category clothing = new Category(null, "Clothing", "Apparel and accessories", null, null);
            categoryRepo.saveAll(List.of(electronics, books, clothing));
            System.out.println("Created initial categories.");
        }
        
        if (productRepo.count() == 0) {
            List<Category> categories = categoryRepo.findAll();
            List<User> users = userRepo.findAll();

            User adminUser = users.stream().filter(u -> u.getRole() == User.Role.ADMIN).findFirst().orElse(null);

            if (adminUser == null) {
                System.out.println("Could not find admin user to assign products.");
                return;
            }

            Category electronics = categories.stream().filter(c -> c.getName().equals("Electronics")).findFirst().orElse(null);
            Category books = categories.stream().filter(c -> c.getName().equals("Books")).findFirst().orElse(null);

            if (electronics != null) {
                productRepo.save(new Product(null, "Laptop Dell XPS", 100, "A high-end laptop for professionals", 25000000.0, adminUser, electronics));
                productRepo.save(new Product(null, "Samsung Galaxy S23", 200, "The latest smartphone from Samsung", 15000000.0, adminUser, electronics));
            }
            if (books != null) {
                productRepo.save(new Product(null, "The Hobbit", 50, "A fantasy novel by J.R.R. Tolkien", 350000.0, adminUser, books));
            }
            System.out.println("Created initial products.");
        }
    }
}
