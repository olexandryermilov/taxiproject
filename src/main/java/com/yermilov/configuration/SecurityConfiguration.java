package com.yermilov.configuration;

import com.yermilov.command.FactoryCommand;

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
        grant.put(FactoryCommand.LOGIN,"ALL");
        grant.put(FactoryCommand.REGISTRATION,"ALL");
        grant.put(FactoryCommand.LOGOUT,"AUTH");
        grant.put(FactoryCommand.USERS,"AUTH");
        grant.put("/*","ALL");
    }

    public static SecurityConfiguration getInstance() {
        return configuration;
    }

    public String security(String command){
        return grant.get(command);
    }
}
