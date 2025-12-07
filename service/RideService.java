package com.choudharykhushboo.RideShare.service;

import com.choudharykhushboo.RideShare.dto.NewRideRequest;
import com.choudharykhushboo.RideShare.model.Ride;
import com.choudharykhushboo.RideShare.model.User;
import com.choudharykhushboo.RideShare.repository.RideRepository;
import com.choudharykhushboo.RideShare.repository.UserRepository;
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
        Ride ride = new Ride();
        ride.setUserId(user.getId());
        ride.setDriverId(null);
        ride.setPickupLocation(request.getPickupLocation());
        ride.setDropLocation(request.getDropLocation());
        ride.setStatus("REQUESTED");
        ride.setCreatedAt(new Date());
        return rideRepository.save(ride);
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
