package com.myapp.myusersservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderItemsModel {

    private String orderId;

    private String productId;

    private int quantity;

}
