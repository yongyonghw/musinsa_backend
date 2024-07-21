package com.musinsa.search;

import com.musinsa.search.controller.PriceController;
import com.musinsa.search.dto.BrandDTO;
import com.musinsa.search.dto.CategoryDTO;
import com.musinsa.search.service.PriceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PriceController.class)
public class PriceTest {
    @MockBean
    private PriceService priceService;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(MockitoAnnotations.openMocks(this));
    }


    @DisplayName("구현1 카테고리 별 최저가격 브랜드와 상품 가격, 총액 조회")
    @Test
    public void testGetCategoryLowestPrice() throws Exception {
        // Given
        List<CategoryDTO.CategoryLowPrice> list = new ArrayList<>();
        list.add(new CategoryDTO.CategoryLowPrice("상의", "C", 10000));
        CategoryDTO.CategoryLowPrice.Response response = new CategoryDTO.CategoryLowPrice.Response(
                list, 34100
        );
        // When
        when(priceService.getCategoryLowPrice()).thenReturn(response);

        // Then
        mockMvc.perform(get("/price/category/low-price")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.category_low_price_list[0].category_name").value("상의"))
                .andExpect(jsonPath("$.category_low_price_list[0].brand_name").value("C"))
                .andExpect(jsonPath("$.category_low_price_list[0].price").value(10000))
                .andExpect(jsonPath("$.total_price").value(34100))
                .andDo(MockMvcResultHandlers.print());
    }

    @DisplayName("구현2 단일 브랜드로 모든 카테고리 상품 구매할때 최저가격 판매정보 조회 API")
    @Test
    public void testGetBrandLowestPrice() throws Exception {
        // Given
        List<BrandDTO.BrandLowPrice> brandLowPriceList = new ArrayList<>();
        brandLowPriceList.add(new BrandDTO.BrandLowPrice("상의", 10100));
        BrandDTO.BrandLowPrice.Response response = new BrandDTO.BrandLowPrice.Response(
                brandLowPriceList, 34100, "D"
        );
        // When
        when(priceService.getBrandLowPrice()).thenReturn(response);

        // Then
        mockMvc.perform(get("/price/brand/low-price")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.brand_low_price_list[0].category_name").value("상의"))
                .andExpect(jsonPath("$.brand_low_price_list[0].price").value(10100))
                .andExpect(jsonPath("$.total_price").value(34100))
                .andDo(MockMvcResultHandlers.print());
    }

    @DisplayName("구현3 카테고리 이름으로 최저, 최고 가격 브랜드와 상품 가격을 조회하는 API")
    @Test
    public void testSearchPrice() throws Exception {
        // Given
        CategoryDTO.ItemBrandPrice.BrandInfo minBrand = new CategoryDTO.ItemBrandPrice.BrandInfo(
              "C", 10000
        );
        CategoryDTO.ItemBrandPrice.BrandInfo maxBrand = new CategoryDTO.ItemBrandPrice.BrandInfo(
                "I", 11400
        );
        CategoryDTO.ItemBrandPrice.Response response = new CategoryDTO.ItemBrandPrice.Response(
                "상의", List.of(minBrand), List.of(maxBrand)
        );
        // When
        when(priceService.getItemBrandPrice("상의")).thenReturn(response);

        // Then
        mockMvc.perform(get("/price/search")
                        .param("categoryName", "상의")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.category_name").value("상의"))
                .andExpect(jsonPath("$.min_price_brand[0].brand_name").value("C"))
                .andExpect(jsonPath("$.max_price_brand[0].brand_name").value("I"))
                .andDo(MockMvcResultHandlers.print());
    }
}
