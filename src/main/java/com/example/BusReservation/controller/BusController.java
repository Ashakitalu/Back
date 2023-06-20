package com.example.BusReservation.controller;


import com.example.BusReservation.Repository.BusRepository;
import com.example.BusReservation.model.Bus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class BusController {

    //link repository and controller
    @Autowired
    private BusRepository busRepo;

    @GetMapping("/allBus")
    public ResponseEntity<?> getBus(){
        try{
            List<Bus> busList = busRepo.findAll();
            if (busList.isEmpty()){
                return new ResponseEntity<>("Hakuna kitu", HttpStatus.NOT_FOUND);
            }else {
                return new ResponseEntity<>(busList,HttpStatus.OK);
            }
        }catch (Exception exception){
            return new ResponseEntity<>("Successfully",HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/add")
    public ResponseEntity<?> addBus(@RequestBody Bus bus) {
        try {
            Bus bus1= busRepo.save(bus);
            return new ResponseEntity<>("entered",HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>("Not entered",HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBus(@PathVariable int id) {
        try {
            busRepo.deleteById(id);
            return new ResponseEntity<>("deleted", HttpStatus.OK);

        } catch (Exception exception) {
            return new ResponseEntity<>("not deleted", HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBus(@PathVariable int id, @RequestBody Bus bus) {
        try {
            if (busRepo.findById(id).isPresent()) {
                Bus bus1 = busRepo.save(bus);
                bus1.setId(id);
                return new ResponseEntity<>("updated", HttpStatus.OK);
            }else {
                return new ResponseEntity<>("Not updated",HttpStatus.CONFLICT);
            }
        } catch (Exception exception){
            return new ResponseEntity<>("Error",HttpStatus.BAD_REQUEST);
        }
    }



}
