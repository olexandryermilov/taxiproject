package com.yermilov.command;

import com.yermilov.admin.command.AddCarCommand;
import com.yermilov.admin.command.DeleteCommand;
import com.yermilov.admin.command.RegisterDriverCommand;
import com.yermilov.admin.command.UsersCommand;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private final static CommandFactory factory = new CommandFactory();
    public final static String LOGIN = "login";
    public final static String REGISTRATION = "registration";
    public final static String LOGOUT = "logout";
    public final static String USERS = "users";
    public final static String ADMIN_LOGIN="adminLogin";
    public final static String DELETE = "delete";
    public final static String RIDE = "ride";
    public final static String CALCULATE_COST = "calculateCost";
    public final static String REGISTER_DRIVER = "registerDriver";
    public final static String ADD_CAR="addCar";
    public final static String RIDES_STATISTICS = "ridesStatistics";
    private Map<String,Command> commandMap = new HashMap<>();
    private CommandFactory(){
        commandMap.put(LOGIN,new com.yermilov.command.LoginCommand());
        commandMap.put(REGISTRATION,new RegistrationCommand());
        commandMap.put(LOGOUT, new LogoutCommand());
        commandMap.put(USERS,new UsersCommand());
        commandMap.put(ADMIN_LOGIN,new com.yermilov.admin.command.LoginCommand());
        commandMap.put(DELETE, new DeleteCommand());
        commandMap.put(CALCULATE_COST,new CostCalculationCommand());
        commandMap.put(RIDE,new RideCommand());
        commandMap.put(REGISTER_DRIVER,new RegisterDriverCommand());
        commandMap.put(ADD_CAR, new AddCarCommand());
        commandMap.put(RIDES_STATISTICS, new RideStatisticsCommand());
    }
    public static CommandFactory getInstance() {
        return factory;
    }

    public Command getCommand(String command){
        return commandMap.get(command);
    }
}
