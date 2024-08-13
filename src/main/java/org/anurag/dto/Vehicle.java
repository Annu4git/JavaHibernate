package org.anurag.dto;

import javax.persistence.*;

@Entity
public class Vehicle {
    @Id
    @GeneratedValue
    private int vehicleId;

    private String vehicleName;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserDetails user;

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

    public UserDetails getUser() {
        return user;
    }

    public void setUser(UserDetails user) {
        this.user = user;
    }
}
