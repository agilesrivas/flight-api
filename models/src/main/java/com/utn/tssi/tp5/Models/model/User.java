package com.utn.tssi.tp5.Models.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@Entity(name = "Users")
@NoArgsConstructor
public class User implements ValidationInterface<User> {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "name", nullable = false)
    private String password;

    private String tocken;

    public User(long id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
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
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.getId() && name.equals(user.getName()) && password.equals(user.getPassword()) && tocken.equals(user.getTocken());
    }

    @Override
    public int hashCode() {
        int hash = 19;

        hash = 31 * hash + (int) id;
        hash = 31 * hash + ((name == null) ? 0 : name.hashCode());
        hash = 31 * hash + ((password == null) ? 0 : password.hashCode());
        hash = 31 * hash + ((tocken == null) ? 0 : tocken.hashCode());

        return hash;
    }

    public boolean validateNullEmpty() {
        boolean bool = true;

        if(id >= 0 && name != null && !(name.trim().equals("")) && password != null && !(password.trim().equals(""))) {
            bool = false;
        }

        return bool;
    }

    public boolean validateNullEmptyIdentifier() {
        boolean bool = true;

        if(name != null && !(name.trim().equals(""))) {
            bool = false;
        }

        return bool;
    }
}
