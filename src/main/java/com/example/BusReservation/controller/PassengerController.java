package com.example.BusReservation.controller;

import com.example.BusReservation.Repository.PassengerRepository;
import com.example.BusReservation.model.Bus;
import com.example.BusReservation.model.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class PassengerController {

    @Autowired
    private PassengerRepository passengerRepository;

    @GetMapping
    public ResponseEntity<?> getPassenger(){
        try {
            List<Passenger> passengerList = passengerRepository.findAll();
            if (passengerList.isEmpty()){
                return new ResponseEntity<>("No data found", HttpStatus.NOT_FOUND);
            }else {
                return new ResponseEntity<>(passengerList,HttpStatus.OK);
            }
        }catch (Exception exception){
            return new ResponseEntity<>("Network project",HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/add")
    public ResponseEntity<?> addPassenger(@RequestBody Passenger passenger) {
        try {
            Passenger passenger1= passengerRepository.save(passenger);
            return new ResponseEntity<>("entered",HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>("Not entered",HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePassenger(@PathVariable int id) {
        try {
            passengerRepository.deleteById(id);
            return new ResponseEntity<>("deleted", HttpStatus.OK);

        } catch (Exception exception) {
            return new ResponseEntity<>("not deleted", HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePassenger(@PathVariable int id, @RequestBody Passenger passenger) {
        try {
            if (passengerRepository.findById(id).isPresent()) {
                Passenger passenger1 = passengerRepository.save(passenger);
                passenger1.setId(id);
                return new ResponseEntity<>("updated", HttpStatus.OK);
            }else {
                return new ResponseEntity<>("Not updated",HttpStatus.CONFLICT);
            }
        } catch (Exception exception){
            return new ResponseEntity<>("Error",HttpStatus.BAD_REQUEST);
        }
    }


}
