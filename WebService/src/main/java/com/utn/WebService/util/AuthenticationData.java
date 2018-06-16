package com.utn.WebService.util;

import com.utn.WebService.wrapper.UserWrapper;
import org.joda.time.DateTime;

public class AuthenticationData {

    private UserWrapper userWrapper;
    private DateTime lastAction;

    public DateTime getLastAction() {
        return lastAction;
    }

    public void setLastAction(DateTime lastAction) {
        this.lastAction = lastAction;
    }

    public UserWrapper getUser() {
        return userWrapper;
    }

    public void setUser(UserWrapper userWrapper) {
        this.userWrapper = userWrapper;
    }
}
