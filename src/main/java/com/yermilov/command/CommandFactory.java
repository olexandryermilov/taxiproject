package com.yermilov.command;

import com.yermilov.admin.command.AdminLoginCommand;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private final static CommandFactory factory = new CommandFactory();
    public final static String LOGIN = "login";
    public final static String REGISTRATION = "registration";
    public final static String LOGOUT = "logout";
    public final static String USERS = "users";
    public final static String ADMIN_LOGIN="adminLogin";
    private Map<String,Command> commandMap = new HashMap<>();
    private CommandFactory(){
        commandMap.put(LOGIN,new LoginCommand());
        commandMap.put(REGISTRATION,new RegistrationCommand());
        commandMap.put(LOGOUT, new LogoutCommand());
        commandMap.put(USERS,new UsersCommand());
        commandMap.put(ADMIN_LOGIN,new AdminLoginCommand());
    }
    public static CommandFactory getInstance() {
        return factory;
    }

    public Command getCommand(String command){
        return commandMap.get(command);
    }
}
