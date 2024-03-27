package com.myapp.myusersservice.controllers;

import com.myapp.myusersservice.entities.AddressEntity;
import com.myapp.myusersservice.entities.OrderDetailsEntity;
import com.myapp.myusersservice.entities.UserProfileEntity;
import com.myapp.myusersservice.models.AddressModel;
import com.myapp.myusersservice.models.OrderDetailsModel;
import com.myapp.myusersservice.models.UserProfileModel;
import com.myapp.myusersservice.services.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Slf4j
@Controller
@RequestMapping("/myUsers")
public class MyUsersController {

    private final BaseService baseService;

    @Autowired
    public MyUsersController(BaseService baseService) {
        this.baseService = baseService;
    }

        @GetMapping("/getUserProfile/{uuid}")
    public ResponseEntity<UserProfileModel> getUserProfileById(@PathVariable("uuid") String uuid) {
        UserProfileModel response = baseService.getUserProfile(uuid);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getAllDetails/{uuid}")
    public ResponseEntity<UserProfileModel> getAllUserDetailsById(@PathVariable("uuid") String uuid) {
        UserProfileModel response = baseService.getAllUserDetails(uuid);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/createProfile")
    public ResponseEntity<UserProfileModel> createProfile(@RequestBody UserProfileEntity entity) {
        UserProfileModel response = baseService.createUserProfile(entity);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/createAddress")
    public ResponseEntity<AddressModel> createAddress(@RequestBody AddressEntity entity) {
        AddressModel response = baseService.createAddress(entity);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/createOrder")
    public ResponseEntity<OrderDetailsModel> createOrder(@RequestBody OrderDetailsEntity entity) {
        OrderDetailsModel response = baseService.createOrder(entity);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/updateAddress")
    public ResponseEntity<AddressModel> updateAddress(@RequestBody AddressEntity entity) {
        AddressModel response = baseService.createAddress(entity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PutMapping("/updateOrderStatus/{uuid}")
    public ResponseEntity<String> updateOrderStatus(@PathVariable String uuid, @RequestParam String status) {
        String response = baseService.updateOrderStatus(uuid, status);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
