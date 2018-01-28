package com.yermilov.configuration;

import com.yermilov.command.CommandFactory;

import java.util.HashMap;
import java.util.Map;

public class SecurityConfiguration {
    private Map<String,String> grant = new HashMap<>();
    private final static SecurityConfiguration configuration = new SecurityConfiguration();
    public final static String ALL = "ALL";
    public final static String AUTH = "AUTH";
    public final static String ADMIN = "ADMIN";
    public final static String NO_ACCESS = "NO_ACCESS";
    private SecurityConfiguration(){
        grant.put(CommandFactory.LOGIN,ALL);
        grant.put(CommandFactory.REGISTRATION,ALL);
        grant.put(CommandFactory.LOGOUT,AUTH);
        grant.put(CommandFactory.USERS,ADMIN);
        grant.put(CommandFactory.ADD_CAR,ADMIN);
        grant.put(CommandFactory.REGISTER_DRIVER,ADMIN);
        grant.put(CommandFactory.RIDE,AUTH);
        grant.put(CommandFactory.CALCULATE_COST,AUTH);
        grant.put(CommandFactory.DELETE,ADMIN);
        grant.put(CommandFactory.ADMIN_LOGIN,ALL);
        grant.put(CommandFactory.RIDES_STATISTICS,AUTH);
        grant.put(CommandFactory.ADD_TAXITYPE,ADMIN);
        grant.put(CommandFactory.TAXITYPES, ADMIN);
        grant.put(CommandFactory.UPDATE_TAXITYPE,ADMIN);
        grant.put(CommandFactory.CLIENTTYPES,ADMIN);
        grant.put(CommandFactory.ADD_CLIENTTYPE,ADMIN);
        grant.put(CommandFactory.UPDATE_CLIENTTYPE,ADMIN);
        grant.put(CommandFactory.RIDE_ORDER,AUTH);
        grant.put(CommandFactory.ADMIN_LOGOUT,ALL);
        grant.put("/",ALL);
        grant.put("registration.jsp",ALL);
        grant.put("login.jsp",ALL);
        grant.put("index.jsp",ALL);
        grant.put("admin",ALL);
        grant.put("users.jsp",ADMIN);
        grant.put("header.jsp",NO_ACCESS);
        grant.put("main.jsp",NO_ACCESS);
        grant.put("addTaxiType.jsp",ADMIN);
        grant.put("rideInformation.jsp",AUTH);
        grant.put("ride.jsp",AUTH);
        grant.put("ridesStatistics.jsp",AUTH);
        grant.put("taxitypes.jsp",ADMIN);
        grant.put("addTaxiType.jsp",ADMIN);
        grant.put("updateTaxiType.jsp",ADMIN);
        grant.put("clienttypes.jsp",ADMIN);
        grant.put("addClientType.jsp",ADMIN);
        grant.put("updateClientType.jsp",ADMIN);
    }

    public static SecurityConfiguration getInstance() {
        return configuration;
    }

    public String security(String command){
        return grant.getOrDefault(command,ALL);
    }
}
