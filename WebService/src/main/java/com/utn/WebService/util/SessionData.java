package com.utn.WebService.util;

import com.utn.WebService.wrapper.UserWrapper;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

@Service
public class SessionData {

    private HashMap<String, AuthenticationData> sessionData;

    @Value("${session.expiration}")
    private int expirationTime;


    public SessionData() {
        this.sessionData = new HashMap<String, AuthenticationData>();
    }

    public String addSession(UserWrapper user) {
        String sessionId = UUID.randomUUID().toString();
        AuthenticationData aData = new AuthenticationData();
        aData.setUser(user);
        aData.setLastAction(new DateTime());
        this.sessionData.put(sessionId, aData);

        return sessionId;
    }

    public void removeSession(String sessionId) {
        this.sessionData.remove(sessionId);
    }

    public AuthenticationData getSession(String sessionId) {
        return this.sessionData.get(sessionId);
    }

    @Scheduled(fixedRate = 5000)
    public void checkSessions() {
        System.out.println("Checking sessions");
        Set<String> sessionsId = this.sessionData.keySet();

        for (String sessionId : sessionsId) {
            AuthenticationData aData = this.sessionData.get(sessionId);

            if (aData.getLastAction().plusSeconds(expirationTime).isBefore(System.currentTimeMillis())) {
                System.out.println("Deleting sessionId = " + sessionId);
                removeSession(sessionId);
            }
        }
    }

}
