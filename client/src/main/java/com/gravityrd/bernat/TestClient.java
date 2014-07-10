package com.gravityrd.bernat;

import com.googlecode.jsonrpc4j.DefaultExceptionResolver;
import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import com.googlecode.jsonrpc4j.ProxyUtil;

import java.net.MalformedURLException;
import java.net.URL;

public class TestClient {

    public static void main(String[] args) throws Exception {
        JsonRpcHttpClient client = new JsonRpcHttpClient(new URL("http://localhost:8081/server/a"));
        ITest test = ProxyUtil.createClientProxy(
                TestClient.class.getClassLoader(),
                ITest.class,
                client);
        client.setExceptionResolver(new DefaultExceptionResolver());

        String user = test.name();
    }
}
