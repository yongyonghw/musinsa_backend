package com.musinsa.search.controller;

import com.musinsa.search.dto.BrandDTO;
import com.musinsa.search.dto.CategoryDTO;
import com.musinsa.search.exception.SearchException;
import com.musinsa.search.service.PriceService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/price")
@RequiredArgsConstructor
public class PriceController {
    private final PriceService priceService;

    @GetMapping("/category/low-price")
    public CategoryDTO.CategoryLowPrice.Response getCategoryLowestPrice() {
        return priceService.getCategoryLowPrice();
    }

    @GetMapping("/brand/low-price")
    public BrandDTO.BrandLowPrice.Response getBrandLowestPrice() {
        return priceService.getBrandLowPrice();
    }

    @GetMapping("/search")
    public CategoryDTO.ItemBrandPrice.Response getItemBrandPrice(@RequestParam String categoryName) {
        if (categoryName.isBlank()) throw new SearchException("카테고리명은 필수 정보 입니다.");
        return priceService.getItemBrandPrice(categoryName);
    }

}
