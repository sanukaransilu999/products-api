
package uk.ac.westminster.products_api;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final List<Product> products = new ArrayList<>();
    private Long nextId = 1L;

    public ProductService() {

        products.add(new DigitalProduct(
                nextId++,
                "Clean Code (E-book)",
                29.99,
                "/downloads/clean-code.pdf",
                5.0
        ));

        products.add(new PhysicalProduct(
                nextId++,
                "27-inch Monitor",
                249.99,
                5.4,
                "61 x 36 x 7 cm"
        ));
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public Product addProduct(Product product) {
        product.setId(nextId++);
        products.add(product);
        return product;
    }

    public Optional<Product> getProductById(Long id) {
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst();
    }

    public void deleteProduct(Long id) {
        products.removeIf(product -> product.getId().equals(id));
    }

    public List<Product> findByName(String name) {
        return products.stream()
                .filter(product -> product.getName()
                        .toLowerCase()
                        .contains(name.toLowerCase()))
                .toList();
    }

    // Activity 5: Return summaries using polymorphism
    public List<String> getAllSummaries() {

        List<String> summaries = new ArrayList<>();

        for (Product p : products) {
            summaries.add(p.getSummary());
        }

        return summaries;
    }
}
