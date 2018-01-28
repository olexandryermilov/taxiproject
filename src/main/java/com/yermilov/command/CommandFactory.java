package com.yermilov.command;

import com.yermilov.admin.command.*;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private final static CommandFactory factory = new CommandFactory();
    public final static String LOGIN = "login";
    public final static String REGISTRATION = "registration";
    public final static String LOGOUT = "logout";
    public final static String USERS = "users";
    public final static String ADMIN_LOGIN="adminLogin";
    public final static String ADMIN_LOGOUT="adminLogout";
    public final static String DELETE = "delete";
    public final static String RIDE = "ride";
    public final static String CALCULATE_COST = "calculateCost";
    public final static String REGISTER_DRIVER = "registerDriver";
    public final static String ADD_CAR="addTaxiType";
    public final static String RIDES_STATISTICS = "ridesStatistics";
    public final static String ADD_TAXITYPE = "addTaxiType";
    public final static String TAXITYPES = "taxitypes";
    public final static String UPDATE_TAXITYPE = "updateTaxiType";
    public final static String CLIENTTYPES = "clienttypes";
    public final static String ADD_CLIENTTYPE = "addClientType";
    public final static String UPDATE_CLIENTTYPE = "updateClientType";
    public final static String RIDE_ORDER = "rideOrder";
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
        commandMap.put(ADD_TAXITYPE,new AddTaxiTypeCommand());
        commandMap.put(TAXITYPES, new TaxiTypesCommand());
        commandMap.put(UPDATE_TAXITYPE, new UpdateTaxiTypeCommand());
        commandMap.put(CLIENTTYPES,new ClientTypesCommand());
        commandMap.put(ADD_CLIENTTYPE, new AddClientTypeCommand());
        commandMap.put(UPDATE_CLIENTTYPE, new UpdateClientTypeCommand());
        commandMap.put(RIDE_ORDER, new RideOrderCommand());
        commandMap.put(ADMIN_LOGOUT,new com.yermilov.admin.command.LogoutCommand());
    }
    public static CommandFactory getInstance() {
        return factory;
    }

    public Command getCommand(String command){
        return commandMap.get(command);
    }
}
