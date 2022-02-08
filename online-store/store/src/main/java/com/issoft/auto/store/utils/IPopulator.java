package com.issoft.auto.store.utils;

import com.issoft.auto.domain.Category;

import java.util.List;

public interface IPopulator {

    List<Category> getCategoriesForShop() throws IllegalAccessException, InstantiationException;

}
