package com.utn.WebService.wrapper;

import com.utn.tssi.tp5.Models.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserWrapper {

    private String name;
    private String password;

    public UserWrapper(User user) {
        if(user != null) {
            this.name = user.getName();
            this.password = user.getPassword();
        }
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof UserWrapper)) return false;

        UserWrapper userWrapper = (UserWrapper) o;
        return name.equals(userWrapper.getName()) && password.equals(userWrapper.getPassword());
    }

    @Override
    public int hashCode() {
        int hash = 19;

        hash = 31 * hash + ((name == null) ? 0 : name.hashCode());
        hash = 31 * hash + ((password == null) ? 0 : password.hashCode());

        return hash;
    }
}
