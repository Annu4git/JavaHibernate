package org.anurag.dto;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "users")
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;

    @Basic
    private String username;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date date;

    @ElementCollection
    @JoinTable(name = "user_add",
        joinColumns = @JoinColumn(name = "userId"))
    @GenericGenerator(name = "increment-gen", strategy = "increment")
    @CollectionId(column = @Column(name = "address_id"), generator = "increment-gen",
                    type = @Type(type = "long"))
    private List<Address> addresses;

    /*@Embedded
    @AttributeOverrides({
    @AttributeOverride(name = "city" , column = @Column(name = "office_city")),
    @AttributeOverride(name = "postcode" , column = @Column(name = "office_postcode"))
    })
    Address officeAddress;*/

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", date=" + date +
                '}';
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
}
