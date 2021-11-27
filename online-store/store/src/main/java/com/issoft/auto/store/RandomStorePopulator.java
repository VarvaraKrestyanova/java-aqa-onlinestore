package com.issoft.auto.store;

import com.github.javafaker.Faker;
import com.issoft.auto.domain.Category;
import com.issoft.auto.domain.Product;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RandomStorePopulator {

    Reflections reflections = new Reflections("com.issoft.auto.domain.categories");
    Set<Class<? extends Category>> namedSubCategories = reflections.getSubTypesOf(Category.class);

    Faker faker = new Faker();

    public List<Product> products = new ArrayList();
    public List<Category> categories = new ArrayList();

    public Product generateFakeProduct(String fakeName){
        Product newProduct = new Product(fakeName, faker.number().numberBetween(1,100), faker.number().randomDouble(2,1,6));
        return newProduct;
    }

    public List<Category> getCategoriesForShop() throws IllegalAccessException, InstantiationException {

        for (Class<? extends Category> namedSubCategory: namedSubCategories){
            Category category = namedSubCategory.newInstance();
            int numberOfProducts = faker.number().numberBetween(1,5);
            List<Product> products = new ArrayList();
            for (int i = 0; i < numberOfProducts; i++){
                switch (category.getName()){
                    case "Bike":
                        products.add(generateFakeProduct(faker.company().name()));
                        break;
                    case "Phone":
                        products.add(generateFakeProduct(faker.phoneNumber().phoneNumber()));
                        break;
                    case "Milk":
                        products.add(generateFakeProduct(faker.country().name()));
                        break;
                }
                category.setProducts(products);
            }
            categories.add(category);

        }
        return categories;
    }


}
