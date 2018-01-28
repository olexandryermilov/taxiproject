package com.yermilov.filter;

import com.yermilov.command.CommandFactory;
import com.yermilov.domain.Admin;
import com.yermilov.domain.User;
import com.yermilov.filters.SecurityFilter;
import org.junit.Test;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class SecurityFilterTest {

    @Test
    public void filter_doesNothing_WhenLevelIsAll() throws IOException, ServletException {
        HttpServletRequest httpServletRequest =mock(HttpServletRequest.class);
        HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);
        when(httpServletRequest.getParameter(eq("command"))).thenReturn(CommandFactory.LOGIN);

        SecurityFilter securityFilter = new SecurityFilter();
        securityFilter.doFilter(httpServletRequest,httpServletResponse,filterChain);

        verify(httpServletResponse,never()).setStatus(anyInt());
    }

    @Test
    public void filter_SetsForbidden_WhenLevelIsNoAccess() throws IOException, ServletException {
        HttpServletRequest httpServletRequest =mock(HttpServletRequest.class);
        HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);
        when(httpServletRequest.getParameter(eq("command"))).thenReturn(null);
        when(httpServletRequest.getRequestURI()).thenReturn("main.jsp");

        SecurityFilter securityFilter = new SecurityFilter();
        securityFilter.doFilter(httpServletRequest,httpServletResponse,filterChain);

        verify(httpServletResponse).setStatus(HttpServletResponse.SC_FORBIDDEN);
    }

    @Test
    public void filter_SetsForbidden_WhenLevelIsAuthAndNoUserInSession() throws IOException, ServletException {
        HttpServletRequest httpServletRequest =mock(HttpServletRequest.class);
        HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);
        HttpSession httpSession = mock(HttpSession.class);
        when(httpSession.getAttribute(eq("currentUser"))).thenReturn(null);
        when(httpServletRequest.getSession()).thenReturn(httpSession);
        when(httpServletRequest.getParameter(eq("command"))).thenReturn(null);
        when(httpServletRequest.getRequestURI()).thenReturn("ride.jsp");

        SecurityFilter securityFilter = new SecurityFilter();
        securityFilter.doFilter(httpServletRequest,httpServletResponse,filterChain);

        verify(httpServletResponse).setStatus(HttpServletResponse.SC_FORBIDDEN);
    }

    @Test
    public void filter_DoesNothing_WhenLevelIsAuthAndUserInSession() throws IOException, ServletException {
        HttpServletRequest httpServletRequest =mock(HttpServletRequest.class);
        HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);
        HttpSession httpSession = mock(HttpSession.class);
        when(httpSession.getAttribute(eq("currentUser"))).thenReturn(new User("user@gmail.com",
                "userpassword","Name","Surname"
                ));
        when(httpServletRequest.getSession()).thenReturn(httpSession);
        when(httpServletRequest.getParameter(eq("command"))).thenReturn(null);
        when(httpServletRequest.getRequestURI()).thenReturn("ride.jsp");

        SecurityFilter securityFilter = new SecurityFilter();
        securityFilter.doFilter(httpServletRequest,httpServletResponse,filterChain);

        verify(httpServletResponse,never()).setStatus(anyInt());
    }

    @Test
    public void filter_DoesNothing_WhenLevelIsAdminAndAdminInSession() throws IOException, ServletException {
        HttpServletRequest httpServletRequest =mock(HttpServletRequest.class);
        HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);
        HttpSession httpSession = mock(HttpSession.class);
        when(httpSession.getAttribute(eq("admin"))).thenReturn(new Admin("admin@gmail.com","adminpass",
                "Name","Surname")
        );
        when(httpServletRequest.getSession()).thenReturn(httpSession);
        when(httpServletRequest.getParameter(eq("command"))).thenReturn(null);
        when(httpServletRequest.getRequestURI()).thenReturn("users.jsp");

        SecurityFilter securityFilter = new SecurityFilter();
        securityFilter.doFilter(httpServletRequest,httpServletResponse,filterChain);

        verify(httpServletResponse,never()).setStatus(anyInt());
    }

    @Test
    public void filter_SetsForbidden_WhenLevelIsAdminAndNoAdminInSession() throws IOException, ServletException {
        HttpServletRequest httpServletRequest =mock(HttpServletRequest.class);
        HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);
        HttpSession httpSession = mock(HttpSession.class);
        when(httpSession.getAttribute(eq("admin"))).thenReturn(null);
        when(httpServletRequest.getSession()).thenReturn(httpSession);
        when(httpServletRequest.getParameter(eq("command"))).thenReturn(null);
        when(httpServletRequest.getRequestURI()).thenReturn("users.jsp");

        SecurityFilter securityFilter = new SecurityFilter();
        securityFilter.doFilter(httpServletRequest,httpServletResponse,filterChain);

        verify(httpServletResponse).setStatus(HttpServletResponse.SC_FORBIDDEN);
    }
}
