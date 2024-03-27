package com.myapp.myusersservice.repositories;

import com.myapp.myusersservice.entities.OrderDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetailsEntity, UUID> {
    @Modifying
    @Query("UPDATE OrderDetailsEntity o SET o.shipmentStatus = :shipmentStatus WHERE o.orderId = :orderId")
    void updateOrderStatus(@Param("orderId") UUID orderId, @Param("shipmentStatus") String shipmentStatus);
}
