package com.example.BusReservation.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;



@Entity
@Data
public class Reservation {

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public int getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(int reservationTime) {
        this.reservationTime = reservationTime;
    }

    public int getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(int reservationDate) {
        this.reservationDate = reservationDate;
    }

    @Id
    private int reservationId;
    private String seatNumber;
    private int reservationTime;
    private int reservationDate;

    @ManyToOne
    @JoinColumn(name = "PassengerId")
    private Passenger passenger;

    @ManyToOne
    @JoinColumn(name = "RouteId")
    private Route route;

    public void setId(int id) {
    }
}



