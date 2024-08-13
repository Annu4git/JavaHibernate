package org.anurag.dto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Vehicle {
    @Id
    @GeneratedValue
    private int vehicleId;

    private String vehicleName;

    @ManyToMany
    @JoinTable(name = "vehicles_belongs_to_users",
            joinColumns = @JoinColumn(name = "vehicle_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<UserDetails> users = new ArrayList<>();

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public List<UserDetails> getUsers() {
        return users;
    }

    public void setUsers(List<UserDetails> users) {
        this.users = users;
    }
}
