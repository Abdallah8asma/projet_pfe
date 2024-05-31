package com.gpro.consulting.tiers.commun.rest.security;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CORSFilter implements Filter {

    private String allowedOrigin;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        allowedOrigin = System.getenv("http://localhost:8080/ma-gpro-design-3.5.0.0-SNAPSHOT"); // URL de votre frontend
        if (allowedOrigin == null) {
            allowedOrigin = "http://localhost:8080"; // Valeur par défaut
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-Control-Allow-Origin", allowedOrigin);
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Cleanup if needed
    }
}
