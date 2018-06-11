package com.utn.WebService.util;

import com.utn.tssi.tp5.Models.model.User;
import org.joda.time.DateTime;

public class AuthenticationData {

    private User user;
    private DateTime lastAction;

    public DateTime getLastAction() {
        return lastAction;
    }

    public void setLastAction(DateTime lastAction) {
        this.lastAction = lastAction;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
