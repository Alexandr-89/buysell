package com.example.busell.service.impl;

import com.example.busell.models.Product;
import com.example.busell.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {


//    @Override
//    public List<Product> products() {
//        return null;
//    }

    private List<Product> products = new ArrayList<>();
    private long ID = 0;

    {
        products.add(new Product(++ID, "Playstation 5", "Simple description", 67000, "Minsk", "Kulakevich"));
        products.add(new Product(++ID, "Iphone 8", "Simple description", 24000, "Minsk", "Kulakevich"));
    }

    public List<Product> listProducts(){
        return products;
    }

    public void saveProduct(Product product){
        product.setId(++ID);
        products.add(product);
    }

    public void  deleteProduct(Long id){
        products.removeIf(product -> product.getId().equals(id));
    }

    public Product getProductById(Long id) {
        for (Product product : products){
            if (product.getId().equals(id)) return product;
        }
        return null;
    }
}
