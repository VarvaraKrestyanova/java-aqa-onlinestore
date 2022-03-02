package com.issoft.auto.domain.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.issoft.auto.domain.Category;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class ServerDataHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
    }

    protected void handle(HttpExchange httpExchange, Object responseObject) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        String response = objectMapper.writeValueAsString(responseObject);

        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream outputStream = httpExchange.getResponseBody();
        outputStream.write(response.getBytes());
        outputStream.close();
    }

}
