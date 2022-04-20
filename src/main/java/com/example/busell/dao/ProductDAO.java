package com.example.busell.dao;

import com.example.busell.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductDAO extends JpaRepository<Product, Long> {

    List<Product> findByTitle(String title);
}
