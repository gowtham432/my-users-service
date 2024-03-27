package com.myapp.myusersservice.models;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressModel {

    private String addressId;

    private String userId;

    private String addressType;

    private String addressLine1;

    private String addressLine2;

    private String landmark;

    private String postalCode;

    private String city;

    private String state;

    private String country;

    private String mobile;

    private Boolean isDefault;

}
