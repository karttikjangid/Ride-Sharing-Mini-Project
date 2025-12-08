package com.rideshare.app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
//import org.hibernate.validator.constraints.UniqueElements;

@Data
public class RegisterRequest {
    @NotBlank
    @Size(min = 2 , max = 30)
    private String username;

    @NotBlank
    @Size(min=8 , max=30)
    private String password;

    @NotBlank
    private String role;

}
