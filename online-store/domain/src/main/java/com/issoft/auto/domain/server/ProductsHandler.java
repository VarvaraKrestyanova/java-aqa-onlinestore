package com.issoft.auto.domain.server;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public class ProductsHandler extends ServerDataHandler {

    StoreServer server;
    String category;

    public ProductsHandler(String categoryName, StoreServer server) {
        category = categoryName;
        this.server = server;
    }

    public void handle(HttpExchange httpExchange) throws IOException {
        super.handle(httpExchange, server.getProductsOfCategory(category));
    }

}
