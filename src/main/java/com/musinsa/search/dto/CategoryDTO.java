package com.musinsa.search.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
public class CategoryDTO {
    public interface Projection {
        String getCategoryName();

        String getMinPriceBrandName();

        String getMaxPriceBrandName();

        int getMinPrice();
        int getMaxPrice();

        int getCategoryLowPrice();

        int getPrice();

        String getBrandName();

    }
    @Data
    @AllArgsConstructor
    public static class CategoryLowPrice {
        private String categoryName;
        private String brandName;
        private int price;

        @Data
        @AllArgsConstructor
        public static class Response {
            private List<CategoryLowPrice> categoryLowPriceList;
            private int totalPrice;

        }
    }
    @Data
    @AllArgsConstructor
    public static class ItemBrandPrice {
        @Data
        @AllArgsConstructor
        public static class BrandInfo {
            String brandName;
            int price;
        }

        @Data
        @AllArgsConstructor
        public static class Response {
            private String categoryName;
            private List<BrandInfo> minPriceBrand;
            private List<BrandInfo> maxPriceBrand;
        }
    }
}


