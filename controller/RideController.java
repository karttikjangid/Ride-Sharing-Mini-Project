package com.rideshare.app.controller;

import com.rideshare.app.dto.NewRideRequest;
import com.rideshare.app.model.Ride;
import com.rideshare.app.service.RideService;
import com.rideshare.app.util.SecurityHelper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/rides")
public class RideController {

    @Autowired
    private RideService rideService;
    @PostMapping
    public ResponseEntity<Ride> createRide(@Valid @RequestBody NewRideRequest request) {
        String username = SecurityHelper.getCurrentUsername();
        Ride ride = rideService.createRide(username, request);
        return ResponseEntity.ok(ride);
    }
    @PutMapping("/{rideId}/complete")
    public ResponseEntity<Ride> completeRide(@PathVariable String rideId) {
        String username = SecurityHelper.getCurrentUsername();
        Ride ride = rideService.completeRide(rideId, username);
        return ResponseEntity.ok(ride);
    }
}
