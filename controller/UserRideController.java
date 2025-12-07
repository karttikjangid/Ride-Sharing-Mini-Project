package com.choudharykhushboo.RideShare.controller;

import com.choudharykhushboo.RideShare.model.Ride;
import com.choudharykhushboo.RideShare.service.RideService;
import com.choudharykhushboo.RideShare.util.SecurityHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
        String username = SecurityHelper.getCurrentUsername();
        List<Ride> rides = rideService.getRidesForUser(username);
        return ResponseEntity.ok(rides);
    }

}
