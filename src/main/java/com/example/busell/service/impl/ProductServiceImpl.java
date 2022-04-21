package com.example.busell.service.impl;

import com.example.busell.dao.ProductDAO;
import com.example.busell.models.Image;
import com.example.busell.models.Product;
import com.example.busell.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    public void saveProduct(Product product, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException {
        Image image1;
        Image image2;
        Image image3;
        if (file1.getSize() != 0) {
            image1=toImageEntity(file1);
            image1.setPreviewImage(true);
            product.addImageToProduct(image1);
        }
        if (file2.getSize() != 0) {
            image2=toImageEntity(file2);
            image2.setPreviewImage(true);
            product.addImageToProduct(image2);
        }
        if (file3.getSize() != 0) {
            image3=toImageEntity(file3);
            image3.setPreviewImage(true);
            product.addImageToProduct(image3);
        }
        log.info("saving new Product. Title:{}; Author:{}", product.getTitle(), product.getAuthor());
        Product productFromDb = productDAO.save(product);
        productFromDb.setPreviewImageId(productFromDb.getImages().get(0).getId());
        productDAO.save(product);
    }

    private Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }

    public void deleteProduct(Long id) {
        productDAO.deleteById(id);
    }

    public Product getProductById(Long id) {
        return productDAO.findById(id).orElse(null);
    }
}
