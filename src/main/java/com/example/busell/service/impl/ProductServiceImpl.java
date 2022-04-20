package com.example.busell.service.impl;

import com.example.busell.dao.ProductDAO;
import com.example.busell.models.Product;
import com.example.busell.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductDAO productDAO;

    public List<Product> listProducts(String title) {
        if (title != null) return productDAO.findByTitle(title);
        return productDAO.findAll();
    }

    public void saveProduct(Product product) {
        log.info("saving new {}", product);
        productDAO.save(product);
    }

    public void deleteProduct(Long id) {
       productDAO.deleteById(id);
    }

    public Product getProductById(Long id) {
        return productDAO.findById(id).orElse(null);
    }
}
