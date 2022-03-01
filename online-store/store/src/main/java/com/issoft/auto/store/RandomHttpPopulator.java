package com.issoft.auto.store;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.issoft.auto.domain.Category;
import com.issoft.auto.domain.Product;
import com.issoft.auto.domain.server.StoreServer;
import com.issoft.auto.store.utils.IHttpPopulator;
import com.issoft.auto.store.utils.IPopulator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class RandomHttpPopulator implements IHttpPopulator {

    StoreServer server;
    HttpClient client;
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<Category> getCategoriesForShop() throws IllegalAccessException, InstantiationException, IOException {

        HttpGet request = new HttpGet(server.CATEGORIES_URL);
        HttpResponse response = client.execute(request);

        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity);

        List<Category> list = objectMapper.readValue(result, new TypeReference<List<Category>>() {});

        return list;
    }

    public List<Product> getProductsForCategory(String categoryName) throws IOException {

            HttpGet request = new HttpGet(String.format(server.PRODUCTS_URL + "/%s", categoryName));
            HttpResponse response = client.execute(request);

            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity);

            List<Product> list = objectMapper.readValue(result, new TypeReference<List<Product>>() {});

            return list;
    }

    @Override
    public void addToCart(String productName) throws Exception {

        HttpPost httppost = new HttpPost(String.format(server.CART_URL));
        try {
            httppost.setEntity(new StringEntity(objectMapper.writeValueAsString(server.addProductToCart(productName))));
        } catch (UnsupportedEncodingException | JsonProcessingException e) {
            throw new Exception();
        }
    }

    @Override
    public List<Product> getProductsInCart() throws Exception {

        HttpGet request = new HttpGet(String.format(server.CART_URL));
        HttpResponse response = client.execute(request);

        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity);

        List<Product> list = objectMapper.readValue(result, new TypeReference<List<Product>>() {
        });

        return list;
    }


}
