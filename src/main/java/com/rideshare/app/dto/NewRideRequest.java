package com.rideshare.app.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class NewRideRequest {
    @NotBlank(message = "PickUp location is needed!!")
    private String pickupLocation;

    @NotBlank(message = "Drop location is also required!!")
    private String dropLocation;
}
