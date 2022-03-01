package com.issoft.auto.domain.server;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public class CategoriesHandler extends ServerDataHandler {

    StoreServer server;

    public CategoriesHandler(HttpExchange httpExchange) throws IOException {
        super.handle(httpExchange, server.getCategories());
    }

    public CategoriesHandler(StoreServer server) {
        this.server = server;
    }

}
