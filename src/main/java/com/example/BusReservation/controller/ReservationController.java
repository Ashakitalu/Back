package com.example.BusReservation.controller;

import com.example.BusReservation.Repository.ReservationRepository;
import com.example.BusReservation.model.Bus;
import com.example.BusReservation.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class ReservationController {

    @Autowired
    private ReservationRepository reservationRepo;

    @GetMapping("/allReservation")

    public ResponseEntity<?> getReservation(){
        try {
            List<Reservation> reservationList = reservationRepo.findAll();
            if (reservationList.isEmpty()){

                return new ResponseEntity<>("No data found", HttpStatus.NOT_FOUND);
            }else {
                return new ResponseEntity<>(reservationList,HttpStatus.OK);
            }
        }catch (Exception exception){
            return new ResponseEntity<>("Network project",HttpStatus.BAD_REQUEST);
        }

    }
    @PostMapping("/add")
    public ResponseEntity<?> addReservation(@RequestBody Reservation reservation) {
        try {
            Reservation reservation1= reservationRepo.save(reservation);
            return new ResponseEntity<>("entered",HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>("Not entered",HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReservation(@PathVariable int id) {
        try {
            reservationRepo.deleteById(id);
            return new ResponseEntity<>("deleted", HttpStatus.OK);

        } catch (Exception exception) {
            return new ResponseEntity<>("not deleted", HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateReservation(@PathVariable int id, @RequestBody Reservation reservation) {
        try {
            if (reservationRepo.findById(id).isPresent()) {
                Reservation reservation1 = reservationRepo.save(reservation);
                reservation1.setId(id);
                return new ResponseEntity<>("updated", HttpStatus.OK);
            }else {
                return new ResponseEntity<>("Not updated",HttpStatus.CONFLICT);
            }
        } catch (Exception exception){
            return new ResponseEntity<>("Network error",HttpStatus.BAD_REQUEST);
        }
    }

}
