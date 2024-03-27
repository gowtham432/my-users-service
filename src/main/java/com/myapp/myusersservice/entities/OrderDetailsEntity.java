package com.myapp.myusersservice.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "order_details")
public class OrderDetailsEntity {

    @Id
    @Column(name = "order_id", columnDefinition = "BINARY(16)")
    private UUID orderId;

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_address")
    private AddressEntity deliveryAddress;

    @Column(name = "shipment_status")
    private String shipmentStatus;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_details")
    private PaymentDetailsEntity paymentDetails;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @OneToMany(mappedBy = "orderDetails", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OrderItemsEntity> orderItems;

}
