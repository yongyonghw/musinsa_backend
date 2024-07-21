package com.musinsa.search.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class BrandDTO {
    public interface Projection {
        String getCategoryName();

        int getTotalPrice();

        int getPrice();

        String getBrandName();

    }

    @AllArgsConstructor
    @Data
    public static class BrandLowPrice {
        private String categoryName;

        private int price;

        @Data
        @AllArgsConstructor
        public static class Response {
            private List<BrandDTO.BrandLowPrice> brandLowPriceList;
            private int totalPrice;
            private String brandName;
        }
    }
}
