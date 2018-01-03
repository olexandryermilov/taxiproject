package com.yermilov.configuration;

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
        grant.put("/*","ALL");
    }

    public static SecurityConfiguration getInstance() {
        return configuration;
    }

    public String security(String command){
        return grant.get(command);
    }
}
