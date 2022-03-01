package com.issoft.auto.store.utils;

import com.issoft.auto.domain.Product;

import java.util.List;

public interface IHttpPopulator extends IPopulator{

    void addToCart(String productName) throws Exception;
    List<Product> getProductsInCart() throws Exception;

}
