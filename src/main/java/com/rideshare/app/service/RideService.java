package com.rideshare.app.service;

import com.rideshare.app.dto.NewRideRequest;
import com.rideshare.app.model.Ride;
import com.rideshare.app.model.User;
import com.rideshare.app.repository.RideRepository;
import com.rideshare.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RideService {
    @Autowired
    private RideRepository rideRepository;
    @Autowired
    private UserRepository userRepository;
    public Ride createRide (String username, NewRideRequest request){
        User user = userRepository.findByUsername(username).orElseThrow(()-> new RuntimeException("User not found"));
        Ride newRide = new Ride();
        newRide.setUserId(user.getId());
        newRide.setDriverId(null);
        newRide.setPickupLocation(request.getPickupLocation());
        newRide.setDropLocation(request.getDropLocation());
        newRide.setStatus("REQUESTED");
        newRide.setCreatedAt(new Date());
        return rideRepository.save(newRide);
    }
    public List<Ride> getRidesForUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));

        return rideRepository.findByUserId(user.getId());
    }
    public List<Ride> getPendingRides() {
        return rideRepository.findByStatus("REQUESTED");
    }
    public Ride acceptRide(String rideId, String driverUsername) {
        Ride ride = rideRepository.findById(rideId)
                .orElseThrow(() -> new RuntimeException("Ride not found"));

        if (!"REQUESTED".equals(ride.getStatus())) {
            throw new RuntimeException("Ride is not in REQUESTED state");
        }
        User driver = userRepository.findByUsername(driverUsername)
                .orElseThrow(() -> new RuntimeException("Driver not found"));
        ride.setDriverId(driver.getId());
        ride.setStatus("ACCEPTED");
        return rideRepository.save(ride);
    }
    public Ride completeRide(String rideId, String driverUsername) {
        Ride ride = rideRepository.findById(rideId)
                .orElseThrow(() -> new RuntimeException("Ride not found: " + rideId));
        if (!"ACCEPTED".equals(ride.getStatus())) {
            throw new RuntimeException("Ride is not in ACCEPTED state");
        }
        User driver = userRepository.findByUsername(driverUsername)
                .orElseThrow(() -> new RuntimeException("Driver not found: " + driverUsername));
        if (ride.getDriverId() == null || !ride.getDriverId().equals(driver.getId())) {
            throw new RuntimeException("This ride is not assigned to the current driver");
        }
        ride.setStatus("COMPLETED");
        return rideRepository.save(ride);
    }
}
