package com.yermilov.authentification;

import javax.servlet.http.HttpSession;
import java.util.Objects;

public class Authentication
{
    private final static Authentication AUTHENTICATION = new Authentication();
    private Authentication(){

    }

    public static Authentication getInstance(){
        return AUTHENTICATION;
    }
    public boolean isUserLoggedIn(HttpSession session) {
        return Objects.nonNull(session.getAttribute("currentUser"));
    }
    public boolean isAdminLoggedIn(HttpSession session) {
        return Objects.nonNull(session.getAttribute("admin"));
    }
}
