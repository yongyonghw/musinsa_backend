package com.musinsa.search.service;

import com.musinsa.search.dto.BrandDTO;
import com.musinsa.search.dto.CategoryDTO;
import com.musinsa.search.repository.PriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PriceService {

    private final PriceRepository priceRepository;

    public CategoryDTO.CategoryLowPrice.Response getCategoryLowPrice() {
        List<CategoryDTO.Projection> projectionList = priceRepository.getCategoryLowPrice();
        List<CategoryDTO.CategoryLowPrice> categoryLowPriceList = projectionList.stream().map
                (dto -> new CategoryDTO.CategoryLowPrice(dto.getCategoryName(), dto.getBrandName(), dto.getPrice())).toList();
        int sum = categoryLowPriceList.stream().mapToInt(o -> o.getPrice()).sum();
        return new CategoryDTO.CategoryLowPrice.Response(categoryLowPriceList, sum);
    }

    public BrandDTO.BrandLowPrice.Response getBrandLowPrice() {
        List<BrandDTO.Projection> projectionList = priceRepository.getBrandLowPriceRaw();
        BrandDTO.Projection resultOne = projectionList.stream().findFirst().get();
        String brandName = resultOne.getBrandName();
        int totalPrice = resultOne.getTotalPrice();
        List<BrandDTO.BrandLowPrice> brandLowPriceList = projectionList.stream().map
                (dto -> new BrandDTO.BrandLowPrice(dto.getCategoryName(), dto.getPrice())).toList();
        return new BrandDTO.BrandLowPrice.Response(brandLowPriceList, totalPrice, brandName);
    }

    public CategoryDTO.ItemBrandPrice.Response getItemBrandPrice(String categoryName) {
        CategoryDTO.ItemBrandPrice.Response response;
        try {
            CategoryDTO.Projection projection = priceRepository.getItemBrandPrice(categoryName);
            response = new CategoryDTO.ItemBrandPrice.Response(projection.getCategoryName()
                    ,Collections.singletonList(new CategoryDTO.ItemBrandPrice.BrandInfo(projection.getMinPriceBrandName(), projection.getMinPrice()))
                    ,Collections.singletonList(new CategoryDTO.ItemBrandPrice.BrandInfo(projection.getMaxPriceBrandName(), projection.getMaxPrice()))
            );
        } catch (Exception e) {
            throw new RuntimeException("지원하지 않는 카테고리 입니다.");
        }
        return response;
    }
}
