package com.yermilov.authentification;

import javax.servlet.http.HttpSession;
import java.util.Objects;

public class Authentication
{
    private final static Authentication AUTHENTICATION = new Authentication();
    private Authentication(){

    }

    public static boolean isUserLoggedIn(HttpSession session) {
        return Objects.nonNull(session.getAttribute("currentUser"));
    }
    public static boolean isAdminLoggedIn(HttpSession session) {
        System.out.println(session.getAttribute("admin"));
        return Objects.nonNull(session.getAttribute("admin"));
    }
}
