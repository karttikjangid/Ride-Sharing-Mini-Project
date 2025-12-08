package com.rideshare.app.repository;

import com.rideshare.app.model.Ride;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RideRepository extends MongoRepository<Ride,String> {
    List<Ride> findByUserId(String id);

    List<Ride> findByStatus(String requested);
}
