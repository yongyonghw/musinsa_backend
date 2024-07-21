package com.musinsa.search.service;

import com.musinsa.search.dto.ProductDTO;
import com.musinsa.search.dto.vo.BrandVO;
import com.musinsa.search.dto.vo.ItemVO;
import com.musinsa.search.entity.Brand;
import com.musinsa.search.entity.Item;
import com.musinsa.search.exception.ProductException;
import com.musinsa.search.repository.BrandRepository;
import com.musinsa.search.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ItemRepository itemRepository;
    private final BrandRepository brandRepository;

    public ProductDTO.Item.Response addItem(ItemVO itemVO) {
        String status = "Product added";
        if(itemRepository.existsById(itemVO.getId())) {
            throw new ProductException("Item already exists");
        }
        Item item = new Item();
        item.setId(itemVO.getId());
        item.setPrice(itemVO.getPrice());
        item.setBrandId(itemVO.getBrandId());
        item.setCategoryId(itemVO.getCategoryId());
        itemRepository.save(item);
        return new ProductDTO.Item.Response(itemVO, status);
    }

    public ProductDTO.Item.Response putItem(ItemVO itemVO) {
        return updateItem(itemVO);
    }

    public String deleteItem(int id) {
        if(itemRepository.existsById(id)) {
            itemRepository.deleteById(id);
            return String.format("Product deleted %d", id);
        } else {
            throw new ProductException("Item not found");
        }
    }

    public ProductDTO.Brand.Response addBrand(BrandVO brandVO) {
        String status = "Brand added";
        if(brandRepository.existsById(brandVO.getId())) {
            throw new ProductException("Brand already exists");
        }
        Brand brand = new Brand();
        brand.setId(brandVO.getId());
        brand.setName(brandVO.getName());
        brandRepository.save(brand);
        return new ProductDTO.Brand.Response(brandVO, status);
    }

    public ProductDTO.Brand.Response putBrand(BrandVO brandVO) {
        return updateBrand(brandVO);
    }

    public String deleteBrand(int id) {
        if(brandRepository.existsById(id)) {
            brandRepository.deleteById(id);
            return String.format("Brand deleted %d", id);
        } else {
            throw new ProductException("Brand not found");
        }
    }

    private ProductDTO.Item.Response updateItem(ItemVO itemVO) {
        String status = "Item added";
        Item updateItem;
        if (itemRepository.existsById(itemVO.getId())) {
            status = "Item modified";
            updateItem = itemRepository.findById(itemVO.getId()).get();
        } else {
            updateItem = new Item();
            updateItem.setId(itemVO.getId());
        }
        updateItem.setPrice(itemVO.getPrice());
        updateItem.setBrandId(itemVO.getBrandId());
        updateItem.setCategoryId(itemVO.getCategoryId());
        itemRepository.save(updateItem);
        return new ProductDTO.Item.Response(itemVO, status);
    }

    private ProductDTO.Brand.Response updateBrand(BrandVO brandVO) {
        String status = "Brand added";
        Brand updateBrand;
        if(brandRepository.existsById(brandVO.getId())) {
            status = "Brand modified";
            updateBrand = brandRepository.findById(brandVO.getId()).get();
            updateBrand.setName(brandVO.getName());
        } else {
            updateBrand = new Brand();
            updateBrand.setId(brandVO.getId());
            updateBrand.setName(brandVO.getName());
        }
        brandRepository.save(updateBrand);
        return new ProductDTO.Brand.Response(brandVO, status);
    }
}
