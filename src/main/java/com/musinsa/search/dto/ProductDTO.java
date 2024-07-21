package com.musinsa.search.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.musinsa.search.dto.vo.BrandVO;
import com.musinsa.search.dto.vo.ItemVO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
public class ProductDTO {
    public static class Item {
        @Data
        @AllArgsConstructor
        public static class Response {
            private ItemVO itemVO;
            private String status;
        }
    }
    public static class Brand {
        @Data
        @AllArgsConstructor
        public static class Response {
            @JsonProperty("brand")
            private BrandVO brandVO;
            private String status;
        }
    }
}
