package com.choudharykhushboo.RideShare.controller;

import com.choudharykhushboo.RideShare.dto.NewRideRequest;
import com.choudharykhushboo.RideShare.model.Ride;
import com.choudharykhushboo.RideShare.service.RideService;
import com.choudharykhushboo.RideShare.util.SecurityHelper;
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
