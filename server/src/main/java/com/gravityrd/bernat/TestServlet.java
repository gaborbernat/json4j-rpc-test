package com.gravityrd.bernat;

import com.googlecode.jsonrpc4j.DefaultErrorResolver;
import com.googlecode.jsonrpc4j.DefaultExceptionResolver;
import com.googlecode.jsonrpc4j.JsonRpcServer;
import com.googlecode.jsonrpc4j.ProxyUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/a"})
public class TestServlet extends HttpServlet {

    private JsonRpcServer jsonRpcServer;
    TestImpl a = new TestImpl();

    @Override
    public void init() throws ServletException {
        super.init();
        Object compositeService = ProxyUtil.createCompositeServiceProxy(
                this.getClass().getClassLoader(),
                new Object[]{a},
                new Class<?>[]{ITest.class},
                true);
        jsonRpcServer = new JsonRpcServer(compositeService);
        jsonRpcServer.setErrorResolver(new DefaultErrorResolver());
        jsonRpcServer.setRethrowExceptions(false);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().println("Mukszik");
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        jsonRpcServer.handle(req, resp);
    }

}
