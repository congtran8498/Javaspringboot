package com.example.fastfood.service;

import com.example.fastfood.entity.Product;
import com.example.fastfood.entity.ProductSize;
import com.example.fastfood.entity.Size;
import com.example.fastfood.entity.SizeTopping;
import com.example.fastfood.exception.BadRequestException;
import com.example.fastfood.exception.NotFoundException;
import com.example.fastfood.repository.*;
import com.example.fastfood.request.dto.CreatePizzaRequestDto;
import com.example.fastfood.request.dto.ProductSizeDto;
import com.example.fastfood.request.dto.ToppingSizeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SizeRepository sizeRepository;
    @Autowired
    private ToppingRepository toppingRepository;
    @Autowired
    private ProductSizeRepository productSizeRepository;
    @Autowired
    private SizeToppingRepository sizeToppingRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ImageRepository imageRepository;

    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }

    public Page<Product> findAll(Integer page, Integer limit){
        Pageable pageable = PageRequest.of(page-1,limit);
        return productRepository.findAll(pageable);
    }

    public Product createProduct(CreatePizzaRequestDto request){
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setCategoryList(categoryRepository.findByIdIn(request.getCategoryIds()));
        product.setImage(imageRepository.findById(request.getImageId()).orElseThrow(()-> new BadRequestException("Không thấy ảnh")));
        productRepository.save(product);

        List<ProductSize> productSizeList = new ArrayList<>();
        for (ProductSizeDto n : request.getProductSizeDtoList()){
            ProductSize productSize = new ProductSize();
            Size size = sizeRepository.findById(n.getSizeId()).orElseThrow(()-> new BadRequestException("Không có size này"));

            productSize.setProduct(product);
            productSize.setSize(size);
            productSize.setPrice(n.getPrice());
            productSizeRepository.save(productSize);
            productSizeList.add(productSize);
        }
        product.setProductSizeList(productSizeList);
        productRepository.save(product);
        return product;
    }
    public Product findById(Long id){
        return productRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Không tồn tại sản phẩm này"));
    }

    public Product updateProduct(Long id,CreatePizzaRequestDto request ){
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("không thấy sản phẩm này"));
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setCategoryList(categoryRepository.findByIdIn(request.getCategoryIds()));
        product.setImage(imageRepository.findById(request.getImageId()).orElseThrow(()-> new BadRequestException("Không thấy ảnh")));

        List<ProductSize> productSizeList = productSizeRepository.findByProduct(product);
        productSizeRepository.deleteAll(productSizeList);

        List<ProductSize> productSizeListNew = new ArrayList<>();
        for (ProductSizeDto n : request.getProductSizeDtoList()){
            ProductSize productSize = new ProductSize();
            Size size = sizeRepository.findById(n.getSizeId()).orElseThrow(()-> new BadRequestException("Không có size này"));

            productSize.setProduct(product);
            productSize.setSize(size);
            productSize.setPrice(n.getPrice());
            productSizeRepository.save(productSize);
            productSizeListNew.add(productSize);
        }
        product.setProductSizeList(productSizeListNew);
        productRepository.save(product);
        return product;
    }

}
