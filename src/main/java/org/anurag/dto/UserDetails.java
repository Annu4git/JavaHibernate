package org.anurag.dto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "users")
@NamedQuery(name = "getUserById", query = "from users where userId = :id")
@NamedNativeQuery(name ="getUserByName",
                        query = "select * from users where username = :name",
                        resultClass = UserDetails.class)
public class UserDetails {

    @Id
    private int userId;

    @Basic
    private String username;

    private String city;

    public UserDetails() {
    }

    public UserDetails(int userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
