package com.musinsa.search.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "ITEM")
@Data
public class Item {
    @Id
    private int id;
    private int price;
    @Column(name = "brand_id")
    private int brandId;
    @Column(name = "category_id")
    private int categoryId;
}
