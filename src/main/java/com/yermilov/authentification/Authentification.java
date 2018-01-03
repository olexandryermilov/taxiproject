package com.yermilov.authentification;

import javax.servlet.http.HttpSession;
import java.util.Objects;

public class Authentification {
    private final static Authentification authentification = new Authentification();
    private Authentification(){

    }

    public static boolean isUserLoggedIn(HttpSession session) {
        return Objects.nonNull(session.getAttribute("login"));
    }
}
