package com.musinsa.search.repository;

import com.musinsa.search.dto.BrandDTO;
import com.musinsa.search.dto.CategoryDTO;
import com.musinsa.search.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PriceRepository extends JpaRepository<Item, Integer> {

    @Query(value = """
                WITH category_low_price AS (
                    SELECT category_id, brand_id, price
                    FROM (
                        SELECT category_id, brand_id, price,
                               ROW_NUMBER() OVER (PARTITION BY category_id ORDER BY price ASC, id DESC) AS row_num
                        FROM item
                    ) ranked
                    WHERE row_num = 1
                )
                SELECT c.name as category_name, b.name as brand_name, price FROM category_low_price LEFT JOIN brand AS b ON b.id = brand_id LEFT JOIN category  AS c ON c.id = category_id;
            """, nativeQuery = true)
            List<CategoryDTO.Projection> getCategoryLowPrice();

    @Query(value = """
                WITH brand_low_price AS (
                    SELECT MIN(price) price, brand_id, category_id FROM item GROUP BY brand_id, category_id
                )
                ,brand_low_price_named AS (
                    SELECT c.name as category_name, b.name as brand_name, price FROM brand_low_price LEFT JOIN brand AS b ON b.id = brand_id LEFT JOIN category  AS c ON c.id = category_id
                )
                ,low_price_brand AS (
                    SELECT SUM(price) AS total_price, brand_name FROM brand_low_price_named GROUP BY brand_name ORDER BY total_price asc LIMIT 1
                )
                SELECT b.category_name, b.brand_name, b.price, total_price FROM brand_low_price_named AS b 
                    INNER JOIN low_price_brand AS lb 
                    ON b.brand_name = lb.brand_name
            """, nativeQuery = true)
    List<BrandDTO.Projection> getBrandLowPriceRaw();

    @Query(value = """
                SELECT
                    ?1 AS category_name,
                    (SELECT name FROM brand WHERE id = min_brand.brand_id) AS min_price_brand_name,
                    min_brand.price AS min_price,
                    (SELECT name FROM brand WHERE id = max_brand.brand_id) AS max_price_brand_name,
                    max_brand.price AS max_price
                FROM
                    (SELECT TOP(1) price, brand_id
                     FROM item
                     JOIN category ON item.category_id = category.id
                     WHERE category.name = ?1
                     ORDER BY price ASC) AS min_brand,
                    (SELECT TOP(1) price, brand_id
                     FROM item
                     JOIN category ON item.category_id = category.id
                     WHERE category.name = ?1
                     ORDER BY price DESC) AS max_brand;
            """, nativeQuery = true)
    CategoryDTO.Projection getItemBrandPrice(@Param("cateName") String cateName);


}
