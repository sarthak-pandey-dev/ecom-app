package com.wishmedia.ecom_app.dto;

import com.wishmedia.ecom_app.model.UserRole;
import lombok.Data;

@Data
public class UserResponse {

    private String  id;
    private String first_name;
    private String last_name;
    private String email;
    private String phone;
    private UserRole role;
    private AddressDTO address;
}
