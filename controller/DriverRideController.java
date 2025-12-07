package com.choudharykhushboo.RideShare.controller;

import com.choudharykhushboo.RideShare.model.Ride;
import com.choudharykhushboo.RideShare.service.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
        String username = getCurrentUsername();
        Ride ride = rideService.acceptRide(rideId, username);
        return ResponseEntity.ok(ride);
    }
    private String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof UserDetails userDetails)) {
            throw new RuntimeException("User not authenticated");
        }
        return userDetails.getUsername();
    }
}
