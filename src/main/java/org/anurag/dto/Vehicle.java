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

}
