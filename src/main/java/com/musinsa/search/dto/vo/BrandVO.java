package com.musinsa.search.dto.vo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BrandVO {
    int id;
    @NotNull
    String name;
}
