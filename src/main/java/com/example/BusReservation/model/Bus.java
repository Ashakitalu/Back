package com.example.BusReservation.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Bus {

    @Id
    private int BusId;
    private String BusNumber;
    private String BusDriver;

    public int getBusId() {
        return BusId;
    }

    public void setBusId(int busId) {
        BusId = busId;
    }

    public String getBusNumber() {
        return BusNumber;
    }

    public void setBusNumber(String busNumber) {
        BusNumber = busNumber;
    }

    public String getBusDriver() {
        return BusDriver;
    }

    public void setBusDriver(String busDriver) {
        BusDriver = busDriver;
    }

    public String getBusCapacity() {
        return BusCapacity;
    }

    public void setBusCapacity(String busCapacity) {
        BusCapacity = busCapacity;
    }

    private String BusCapacity;

    public void setId(int id) {
    }
}

