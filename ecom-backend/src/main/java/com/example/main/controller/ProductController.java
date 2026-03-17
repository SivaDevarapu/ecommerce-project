package com.example.main.controller;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.example.main.ECommerceApplication;
import com.example.main.model.Product;
import com.example.main.service.ProductService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {

   

    @Autowired
    private ProductService service;


    @GetMapping("/")
    public String greet() {
        return "Hello, building an E-commerce website";
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return service.getAllProducts();
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        Product product = service.getProductById(id);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/product")
    public ResponseEntity<String> addProduct(
            @RequestPart("product") Product product,
            @RequestPart("imageFile") MultipartFile imageFile) {
    	System.out.println(product);
    	System.out.println(imageFile);
    	
        try {
            Product saved = service.addProduct(product, imageFile);
            return new ResponseEntity<>("created", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }    
        
    }
    @GetMapping("/product/{proId}/image")
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable int proId)
    {
    	Product pro=service.getProductById(proId);
    	byte[] imageFile=pro.getImageData();
    	
//    	return ResponseEntity.ok().
//    			body(imageFile);
    	
    	return new ResponseEntity<>(imageFile, HttpStatus.OK);
    }
    
    @PutMapping("/product/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id,
    									@RequestPart("product") Product product,
    									@RequestPart("imageFile") MultipartFile imageFile)
    {
    	
    	Product product1=null;
		try {
			product1 = service.updateProduct(id,product,imageFile);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
    	if(product1!=null)
    	
		return new ResponseEntity<>("Product updated",HttpStatus.OK);
    	else
    		return new ResponseEntity<>("Product not updated",HttpStatus.BAD_REQUEST);
    	
    }
    
    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id)
    									
    {
    	Product product1=service.getProductById(id);
    	if(product1!=null)
    	{
    		service.deleteProduct(id);
    		return new ResponseEntity<>("Product deleted",HttpStatus.OK);
    	}
    	else
    		return new ResponseEntity<>("Product not found",HttpStatus.NOT_FOUND);
    	
    }
    
    @GetMapping("/products/search")
    public ResponseEntity<List<Product>> searchKeyword(@RequestParam String keyWord)
    {
    	System.out.println(keyWord);
    	List<Product> products=service.searchKeyWord(keyWord);
		return new ResponseEntity<>(products,HttpStatus.OK);
    	
    }
    
    
}