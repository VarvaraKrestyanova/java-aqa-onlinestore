package com.issoft.auto.domain.server;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public class Cart extends ServerDataHandler {

    StoreServer server;

    public Cart(StoreServer server) {
        this.server = server;
    }

    public void handle(HttpExchange httpExchange) throws IOException {
        super.handle(httpExchange, server.getProductsOnCart());
    }

}
