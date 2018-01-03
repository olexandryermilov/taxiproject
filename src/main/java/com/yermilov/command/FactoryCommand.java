package com.yermilov.command;

import javax.servlet.Registration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class FactoryCommand {
    private final static FactoryCommand factory = new FactoryCommand();
    public final static String LOGIN = "/login";
    public final static String REGISTRATION = "/registration";
    public final static String LOGOUT = "/logout";
    public final static String USERS = "/users";
    private Map<String,Command> commandMap = new HashMap<>();
    private FactoryCommand(){
        commandMap.put(LOGIN,new LoginCommand());
        commandMap.put(REGISTRATION,new RegistrationCommand());
        commandMap.put(LOGOUT, new LogoutCommand());
        commandMap.put(USERS,new UsersCommand());
    }
    public static FactoryCommand getInstance() {
        return factory;
    }

    public Command getCommand(String command){
        return commandMap.get(command);
    }
}
