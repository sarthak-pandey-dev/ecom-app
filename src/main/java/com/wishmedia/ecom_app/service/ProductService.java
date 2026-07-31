package com.wishmedia.ecom_app.service;

import com.wishmedia.ecom_app.Repository.ProductRepository;
import com.wishmedia.ecom_app.dto.ProductRequest;
import com.wishmedia.ecom_app.dto.ProductResponse;
import com.wishmedia.ecom_app.model.Product;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
private ProductRepository productRepository;

    public List<ProductResponse> getAllProducts (){
        return productRepository.findByActiveTrue().stream()
                .map(this::mapToProductResponse)
                .collect(Collectors.toList());
    }

    public @Nullable ProductResponse createProduct(ProductRequest productRequest) {
  Product product = new Product();
  updateProductFromRequest(product,productRequest);
  Product savedProduct=productRepository.save(product);
  return mapToProductResponse(savedProduct);

    }

    private @Nullable ProductResponse mapToProductResponse(Product savedProduct) {
        ProductResponse response=new ProductResponse();
        response.setId(savedProduct.getId());
        response.setName(savedProduct.getName());
        response.setActive(savedProduct.getActive());
        response.setCategory(savedProduct.getCategory());
        response.setDiscription(savedProduct.getDiscription());
        response.setPrice(savedProduct.getPrice());
        response.setImageUrl(savedProduct.getImageUrl());
        response.setStockQuantity(savedProduct.getStockQuantity());

        return response;
    }

    private void updateProductFromRequest(Product product, ProductRequest productRequest) {
        product.setName(productRequest.getName());
        product.setCategory(productRequest.getCategory());
        product.setDiscription(productRequest.getDiscription());
        product.setPrice(productRequest.getPrice());
        product.setImageUrl(productRequest.getImageUrl());
        product.setStockQuantity(productRequest.getStockQuantity());

    }

    public Optional <ProductResponse> updateProduct(Long id, ProductRequest productRequest) {

      return  productRepository.findById(id)
                .map(existingProduct->{
                    updateProductFromRequest(existingProduct,productRequest);
                    Product savedProduct=productRepository.save(existingProduct);
                    return mapToProductResponse(savedProduct);
                });
    }

    public boolean deleteProduct(Long id) {
        return productRepository.findById(id)
                .map(product->{
                    product.setActive(false);
                    productRepository.save(product);
                    return true;
                }).orElse(false);
//        Product product =productRepository.findById(id)
//                .orElseThrow(()-> new RuntimeException( "Product not Found"));
//        product.setActive(false);
//        productRepository.save(product);
    }

    public List <ProductResponse> searchProducts(String keyword) {

        return productRepository.searchProducts(keyword).stream()
                .map(this::mapToProductResponse)
                .collect(Collectors.toList());
    }

}
