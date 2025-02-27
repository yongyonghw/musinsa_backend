package com.musinsa.search.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "BRAND")
@Data
public class Brand {
    @Id
    private int id;
    @Column(name = "name")
    private String name;
}
