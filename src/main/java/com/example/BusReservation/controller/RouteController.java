package com.example.BusReservation.controller;

import com.example.BusReservation.Repository.RouteRepository;
import com.example.BusReservation.model.Bus;
import com.example.BusReservation.model.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping
public class RouteController {
    @Autowired
    public RouteRepository routeRepo;

    @GetMapping("/allRoute")
    public ResponseEntity<?> getRoute(){
        try {
            List<Route> RouteList = routeRepo.findAll();
            if (RouteList.isEmpty()){

                return new ResponseEntity<>("No data found", HttpStatus.NOT_FOUND);
            }else {
                return new ResponseEntity<>(RouteList,HttpStatus.OK);
            }
        }catch (Exception exception){
            return new ResponseEntity<>("Network project",HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/add")
    public ResponseEntity<?> addRoute(@RequestBody Route route) {
        try {
            Route route1= routeRepo.save(route);
            return new ResponseEntity<>("entered",HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>("Not entered",HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRoute(@PathVariable int id) {
        try {
            routeRepo.deleteById(id);
            return new ResponseEntity<>("deleted", HttpStatus.OK);

        } catch (Exception exception) {
            return new ResponseEntity<>("not deleted", HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateRoute(@PathVariable int id, @RequestBody Route route) {
        try {
            if (routeRepo.findById(id).isPresent()) {
                Route route1 = routeRepo.save(route);
                route1.setId(id);
                return new ResponseEntity<>("updated", HttpStatus.OK);
            }else {
                return new ResponseEntity<>("Not updated",HttpStatus.CONFLICT);
            }
        } catch (Exception exception){
            return new ResponseEntity<>("Error",HttpStatus.BAD_REQUEST);
        }
    }
}
