package org.yearup.controllers;

// Imports for HTTP responses and status codes
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

// Import for role-based authorization
import org.springframework.security.access.prepost.PreAuthorize;

// Imports for REST controller annotations
import org.springframework.web.bind.annotation.*;

// Import for throwing custom HTTP exceptions
import org.springframework.web.server.ResponseStatusException;

// Model classes
import org.yearup.models.Category;
import org.yearup.models.Product;

// Service classes containing business logic
import org.yearup.service.CategoryService;
import org.yearup.service.ProductService;

// Import List collection
import java.util.List;

// Marks this class as a REST controller
@RestController

// Base URL for all endpoints in this controller
@RequestMapping("/categories")

// Allows requests from any frontend origin
@CrossOrigin(origins = "*")
public class CategoriesController {

    // Service used to manage categories
    private CategoryService categoryService;

    // Service used to manage products
    private ProductService productService;

    // Constructor injection for services
    public CategoriesController(CategoryService categoryService, ProductService productService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    // GET /categories
    // Returns a list of all categories
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {

        // Retrieve all categories from the service layer
        List<Category> categories = categoryService.getAllCategories();

        // Return categories with HTTP 200 OK
        return ResponseEntity.ok(categories);
    }

    // GET /categories/{id}
    // Returns a category by its ID
    @GetMapping("/{id}")
    public ResponseEntity<Category> getById(@PathVariable int id) {

        // Look up category by ID
        Category category = categoryService.getById(id);

        // If category doesn't exist, return 404 Not Found
        if (category == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Category not found"
            );
        }

        // Return category with HTTP 200 OK
        return ResponseEntity.ok(category);
    }

    // GET /categories/{categoryId}/products
    // Returns all products belonging to a specific category
    @GetMapping("{categoryId}/products")
    public List<Product> getProductsById(@PathVariable int categoryId) {

        // Retrieve products by category ID
        return productService.listByCategoryId(categoryId);
    }

    // POST /categories
    // Creates a new category
    // Only users with ADMIN role can access this endpoint
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {

        // Save the new category
        Category created = categoryService.create(category);

        // Return created category with HTTP 201 Created
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // PUT /categories/{id}
    // Updates an existing category
    // Only users with ADMIN role can access this endpoint
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Category updateCategory(
            @PathVariable int id,
            @RequestBody Category category) {

        // Update category and return updated object
        return categoryService.update(id, category);
    }

    // DELETE /categories/{id}
    // Deletes a category by ID
    // Only users with ADMIN role can access this endpoint
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteCategory(@PathVariable int id) {

        // Delete category
        categoryService.delete(id);

        // Return HTTP 204 No Content after successful deletion
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}