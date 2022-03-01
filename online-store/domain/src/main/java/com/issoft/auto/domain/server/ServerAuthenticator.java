package com.issoft.auto.domain.server;

import com.issoft.auto.domain.db.PropertiesUtil;
import com.sun.net.httpserver.BasicAuthenticator;

public class ServerAuthenticator extends BasicAuthenticator {

    private static final String USER = "user";
    private static final String PASSWORD = "password";

    private static String user = PropertiesUtil.get(USER);
    private static String password = PropertiesUtil.get(PASSWORD);

    public ServerAuthenticator(String realm) {
        super(realm);
    }

    @Override
    public boolean checkCredentials(String userLogin, String userPassword) {
        return userLogin.equals(user) && userPassword.equals(password);
    }
}
