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
@RequestMapping("/api/v1/user/rides")
public class UserRideController {

    @Autowired
    private RideService rideService;

    // see all my rides
    @GetMapping
    public ResponseEntity<List<Ride>> getMyRides() {
        String username = getCurrentUsername();
        List<Ride> rides = rideService.getRidesForUser(username);
        return ResponseEntity.ok(rides);
    }
    private String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !(authentication.getPrincipal() instanceof UserDetails userDetails)) {
            throw new RuntimeException("User not authenticated");
        }
        return userDetails.getUsername();
    }

}
