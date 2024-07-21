package com.musinsa.search.controller;

import com.musinsa.search.dto.ProductDTO;
import com.musinsa.search.dto.vo.BrandVO;
import com.musinsa.search.dto.vo.ItemVO;
import com.musinsa.search.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;


    @PostMapping("/item")
    public ProductDTO.Item.Response addItem(@RequestBody @Valid ItemVO item) {
        return productService.addItem(item);
    }
    @PutMapping("/item")
    public ProductDTO.Item.Response modifyProduct(@RequestBody @Valid ItemVO item) {
        return productService.putItem(item);
    }
    @DeleteMapping("/item/{id}")
    public String deleteProduct(@PathVariable int id) {
        return productService.deleteItem(id);
    }

    @PostMapping("/brand")
    public ProductDTO.Brand.Response addBrand(@RequestBody @Valid BrandVO brand) {
        return productService.addBrand(brand);
    }

    @PutMapping("/brand")
    public ProductDTO.Brand.Response modifyBrand(@RequestBody @Valid BrandVO brand) {
        return productService.putBrand(brand);
    }
    @DeleteMapping("/brand/{id}")
    public String deleteBrand(@PathVariable int id) {
        return productService.deleteBrand(id);
    }
}
