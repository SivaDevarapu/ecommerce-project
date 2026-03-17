package com.example.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.main.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product,Integer>
{
	@Query("SELECT p from Product p WHERE "+
			"LOWER(p.name) LIKE LOWER(CONCAT('%', :keyWord, '%')) OR "+
			"LOWER(p.brand) LIKE LOWER(CONCAT('%', :keyWord, '%')) OR "+
			"LOWER(p.category) LIKE LOWER(CONCAT('%', :keyWord, '%')) OR "+
			"LOWER(p.description) LIKE LOWER(CONCAT('%', :keyWord, '%'))")
	List<Product> searchProducts(String keyWord);
	
}
