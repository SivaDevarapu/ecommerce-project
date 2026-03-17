package com.example.main.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.main.model.Product;
import com.example.main.repository.ProductRepo;

@Service
public class ProductService {
    @Autowired
    private ProductRepo repo;

    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    public void insertProducts(Product pro) {
        repo.save(pro);
    }

    public Product getProductById(int id) {
        return repo.findById(id).orElse(null);
    }

    public Product addProduct(Product product, MultipartFile imageFile) throws IOException {
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());  
        return repo.save(product);
    }

	public Product updateProduct(int id, Product product, MultipartFile imageFile) throws IOException {
		
		product.setImageName(imageFile.getOriginalFilename());
		product.setImageType(imageFile.getContentType());
		product.setImageData(imageFile.getBytes());
		return repo.save(product);
	}

	public void deleteProduct(int id) {
		
		 repo.deleteById(id);;
	}

	public List<Product> searchKeyWord(String keyWord) {
		
		return repo.searchProducts(keyWord);
	}

	
}