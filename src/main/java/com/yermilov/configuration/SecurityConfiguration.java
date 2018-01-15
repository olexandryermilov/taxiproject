package com.yermilov.configuration;

import com.yermilov.command.Command;
import com.yermilov.command.CommandFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SecurityConfiguration {
    private Map<String,String> grant = new HashMap<>();
    private final static SecurityConfiguration configuration = new SecurityConfiguration();
    public Set<String> getEndpoints(){
        return grant.keySet();
    }
    private SecurityConfiguration(){
        grant.put(CommandFactory.LOGIN,"ALL");
        grant.put(CommandFactory.REGISTRATION,"ALL");
        grant.put(CommandFactory.LOGOUT,"AUTH");
        grant.put(CommandFactory.USERS,"AUTH");
        grant.put(CommandFactory.ADD_CAR,"ADMIN");
        grant.put(CommandFactory.REGISTER_DRIVER,"ADMIN");
        grant.put(CommandFactory.RIDE,"AUTH");
        grant.put(CommandFactory.CALCULATE_COST,"AUTH");
        grant.put(CommandFactory.DELETE,"ADMIN");
        grant.put(CommandFactory.ADMIN_LOGIN,"ALL");
        grant.put(CommandFactory.RIDES_STATISTICS,"AUTH");
        grant.put("/","ALL");
        grant.put("registration.jsp","ALL");
        grant.put("login.jsp","ALL");
        grant.put("index.jsp","ALL");
        grant.put("admin","ADMIN");
    }

    public static SecurityConfiguration getInstance() {
        return configuration;
    }

    public String security(String command){
        return grant.getOrDefault(command,"AUTH");
    }
}
