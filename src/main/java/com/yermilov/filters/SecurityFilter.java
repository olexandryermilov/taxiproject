package com.yermilov.filters;

import com.yermilov.authentification.Authentication;
import com.yermilov.configuration.SecurityConfiguration;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;
import java.util.Set;


public class SecurityFilter implements Filter {
    private FilterConfig filterConfig;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        SecurityConfiguration securityConfiguration = SecurityConfiguration.getInstance();
        HttpServletRequest httpServletRequest = ((HttpServletRequest) request);
        HttpServletResponse httpServletResponse = ((HttpServletResponse)response);
        System.out.println(httpServletRequest.getRequestURL());
        String command =httpServletRequest.getParameter("command");
        if(command==null){
            String url = httpServletRequest.getRequestURL().toString();
            if(url.endsWith("login.jsp")){
                command="login.jsp";
            }else {
                if(url.endsWith("registration.jsp")){
                    command="registration.jsp";
                }
                else{
                    if (url.endsWith("taxiproject/") || url.endsWith("index.jsp") || url.endsWith("/admin")
                            || url.endsWith("styles/w3.css")||url.endsWith("/admin/")) {
                        command = "/";
                    }
                    else {
                        if (url.contains("/admin/")) {
                            command = "/admin/";
                        } else {
                            command = "auth";
                        }
                    }
                }
            }
        }
        System.out.println("Command is "+command);
        String role=securityConfiguration.security(command);
        System.out.println("Role is "+ role);
        if ("ALL".equals(role)) {
            chain.doFilter(request, response);
            return;
        }
        if ("AUTH".equals(role)) {
            if (Authentication.isUserLoggedIn(httpServletRequest.getSession())) {
                chain.doFilter(request, response);
                return;
            } else {
                httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            }
        }
        if("ADMIN".equals(role)){
            if (Authentication.isAdminLoggedIn(httpServletRequest.getSession())) {
                chain.doFilter(request, response);
                return;
            } else {
                httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
            }
        }
        httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
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
