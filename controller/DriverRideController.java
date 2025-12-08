package com.rideshare.app.controller;

import com.rideshare.app.model.Ride;
import com.rideshare.app.service.RideService;
import com.rideshare.app.util.SecurityHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/driver/rides")
public class DriverRideController {

    @Autowired
    private RideService rideService;

    // DRIVER: see all pending ride requests
    @GetMapping("/requests")
    public ResponseEntity<List<Ride>> getPendingRides() {
        List<Ride> rides = rideService.getPendingRides();
        return ResponseEntity.ok(rides);
    }

    // DRIVER: accept a specific ride
    @PutMapping("/{rideId}/accept")
    public ResponseEntity<Ride> acceptRide(@PathVariable String rideId) {
        String username = SecurityHelper.getCurrentUsername();
        Ride ride = rideService.acceptRide(rideId, username);
        return ResponseEntity.ok(ride);
    }
}
