package com.musinsa.search.dto.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ItemVO {
    @NotNull
    private int id;
    @NotNull
    private int price;
    @NotNull
    @JsonProperty("brand_id")
    private int brandId;
    @NotNull
    @JsonProperty("category_id")
    private int categoryId;
}
