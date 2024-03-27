package com.myapp.myusersservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class OrderDetailsModel {

    private String orderId;

    private String userId;

    private BigDecimal totalAmount;

    private List<OrderItemsModel> orderItems;

    private AddressModel deliveryAddress;

    private String shipmentStatus;

    private PaymentsModel paymentDetails;

    private String createdDate;
}
