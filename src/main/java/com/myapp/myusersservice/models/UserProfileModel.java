package com.myapp.myusersservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class UserProfileModel {

    private String userId;

    private String firstName;

    private String lastName;

    private String userName;

    private String email;

    private String mobile;

    private String password;

    private List<AddressModel> addresses;

    private List<OrderDetailsModel> orderDetails;

}
