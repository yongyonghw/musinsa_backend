package com.musinsa.search.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ProductException extends RuntimeException{
    private String message;
}
