package com.rideshare.app.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "rides")
public class Ride {
    @Id
    private String id;

    private String userId;
    private String driverId;

    private String pickupLocation;
    private String dropLocation;

    private String status;
    private Date createdAt;
}
