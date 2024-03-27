package com.myapp.myusersservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentsModel {

    private String paymentId;

    private String paymentMethod;

    private String paymentStatus;

    private String createdDate;

}
