package com.yermilov.filters;

import com.yermilov.authentification.Authentification;
import com.yermilov.configuration.SecurityConfiguration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;


public class SecurityFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        SecurityConfiguration securityConfiguration = SecurityConfiguration.getInstance();

        HttpServletRequest httpServletRequest = ((HttpServletRequest) request);
        HttpServletResponse httpServletResponse = ((HttpServletResponse)response);
        String command =getStringCommand(((HttpServletRequest) request).getRequestURI(),securityConfiguration.getEndpoints());
        String role=securityConfiguration.security(command);
        if ("ALL".equals(role)) {
            request.setAttribute("command",command);
            chain.doFilter(request, response);
            return;
        }
        if ("AUTH".equals(role)) {
            if (Authentification.isUserLoggedIn(((HttpServletRequest) request).getSession())) {
                request.setAttribute("command",command);
                chain.doFilter(request, response);
                return;
            } else {
                httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            }
        }
        httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return;
    }

    @Override
    public void destroy() {

    }
    private String getStringCommand(String URI, Set<String> endpoints){
        for(String endpoint:endpoints){
            if(URI.contains(endpoint)){
                return endpoint;
            }
        }
        return null;
    }
}
