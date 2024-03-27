package com.myapp.myusersservice.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "order_items")
public class OrderItemsEntity {

    @Id
    @Column(name = "items_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID itemsId;

    @Column(name = "order_id")
    private UUID orderDetails;

    @Column(name = "product_id")
    private UUID productId;

    @Column(name = "quantity")
    private Integer quantity;

}
