package com.slowcampus.config;

import javax.servlet.*;
import java.io.IOException;

public class Utf8Filter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            chain.doFilter(request, response);
        } catch (Exception ex) {
            request.setAttribute("errorMessage", ex);
        }

    }

    @Override
    public void destroy() {

    }
}
