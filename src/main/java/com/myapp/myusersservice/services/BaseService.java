package com.myapp.myusersservice.services;

import com.myapp.myusersservice.entities.*;
import com.myapp.myusersservice.exceptions.NotFoundException;
import com.myapp.myusersservice.models.*;
import com.myapp.myusersservice.repositories.AddressRepository;
import com.myapp.myusersservice.repositories.OrderDetailsRepository;
import com.myapp.myusersservice.repositories.UserProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BaseService {

    private final UserProfileRepository userProfileRepository;

    private final AddressRepository addressRepository;

    private final OrderDetailsRepository orderDetailsRepository;

    @Autowired
    public BaseService(UserProfileRepository userProfileRepository, AddressRepository addressRepository, OrderDetailsRepository orderDetailsRepository) {
        this.userProfileRepository = userProfileRepository;
        this.addressRepository = addressRepository;
        this.orderDetailsRepository = orderDetailsRepository;
    }

    @Transactional
    public UserProfileModel getUserProfile(String uuid) {
        UserProfileEntity data = userProfileRepository.findById(UUID.fromString(uuid))
                .orElseThrow(() -> new NotFoundException("User Not Fond"));
        log.info("GET UserProfile : " + data.getUserId().toString());
        return setUserProfileDetails(data);
    }

    @Transactional
    public UserProfileModel getAllUserDetails(String uuid) {
        UserProfileEntity data = userProfileRepository.findById(UUID.fromString(uuid))
                .orElseThrow(() -> new NotFoundException("User Not Found"));
        log.info("GET All Details : " + data.getUserId().toString());

        UserProfileModel userProfileModel = setUserProfileDetails(data);

        userProfileModel.setAddresses(
                data.getAddresses()
                        .stream()
                        .map(this::setAddressDetails)
                        .collect(Collectors.toList()));

        userProfileModel.setOrderDetails(
                data.getOrders()
                        .stream()
                        .map(this::setOrderDetails)
                        .collect(Collectors.toList())
        );
        return userProfileModel;
    }

    public UserProfileModel createUserProfile(UserProfileEntity entity) {
        UserProfileEntity data;
        try {
            data = userProfileRepository.save(entity);
            log.info("User created : " + data.getUserId());
        } catch (Exception e) {
            throw new RuntimeException("Error while creating profile", e.getCause());
        }
        return setUserProfileDetails(data);
    }

    public AddressModel createAddress(AddressEntity entity) {
        AddressEntity data;
        if (userProfileRepository.existsById(entity.getUserId())) {
            try {
                data = addressRepository.save(entity);
                log.info("Address Added : " + data.getAddressId());
            } catch (Exception e) {
                throw new RuntimeException("Error while creating address", e.getCause());
            }
        } else {
            throw new NotFoundException("User Not Found");
        }
        return setAddressDetails(data);
    }

    public OrderDetailsModel createOrder(OrderDetailsEntity entity) {

        UUID uuid = UUID.randomUUID();
        entity.setOrderId(uuid);
        entity.getOrderItems().forEach((item) -> item.setOrderDetails(uuid));

        OrderDetailsEntity data;

        if (userProfileRepository.existsById(entity.getUserId())) {
            try {
                data = orderDetailsRepository.save(entity);
                log.info("Order created : " + data.getOrderId());
            } catch (Exception e) {
                throw new RuntimeException("Error while creating order", e.getCause());
            }
        } else {
            throw new NotFoundException("User Not Found");
        }
        return setOrderDetails(data);
    }

    @Transactional
    public String updateOrderStatus(String uuid, String status) {
        String response;
        try {
            if (orderDetailsRepository.existsById(UUID.fromString(uuid))) {
                orderDetailsRepository.updateOrderStatus(UUID.fromString(uuid), status);
                log.info("Order status updated");
                response = "Updated";
            } else {
                throw new NotFoundException("Order Not Found");
            }
        } catch (Exception e) {
            throw new RuntimeException("Error while updating order status - " + e.getMessage(), e.getCause());
        }
        return response;
    }

    public String deleteAddress(String uuid) {
        String response;
        try {
            addressRepository.deleteById(UUID.fromString(uuid));
            log.info("Address deleted");
            response = "Deleted";
        } catch (Exception e) {
            throw new RuntimeException("Error while deleting address", e.getCause());
        }
        return response;
    }


    public UserProfileModel setUserProfileDetails(UserProfileEntity data) {
        return new UserProfileModel(
                data.getUserId().toString(),
                data.getFirstName(),
                data.getLastName(),
                data.getUserName(),
                data.getEmail(),
                data.getMobile(),
                data.getPassword(),
                null,
                null
        );
    }

    public AddressModel setAddressDetails(AddressEntity data) {
        return new AddressModel(
                data.getAddressId().toString(),
                data.getUserId().toString(),
                data.getAddressType(),
                data.getAddressLine1(),
                data.getAddressLine2(),
                data.getLandmark(),
                data.getPostalCodeDetails().getPostalCode(),
                data.getPostalCodeDetails().getCity(),
                data.getPostalCodeDetails().getState(),
                data.getPostalCodeDetails().getCountry(),
                data.getMobile(),
                data.getIsDefault()
        );
    }

    public OrderDetailsModel setOrderDetails(OrderDetailsEntity data) {
        return new OrderDetailsModel(
                data.getOrderId().toString(),
                data.getUserId().toString(),
                data.getTotalAmount(),
                data.getOrderItems()
                        .stream()
                        .map(this::setOrderItems)
                        .collect(Collectors.toList()),
                setAddressDetails(data.getDeliveryAddress()),
                data.getShipmentStatus(),
                setPaymentDetails(data.getPaymentDetails()),
                data.getCreatedDate().toString()
        );
    }

    public OrderItemsModel setOrderItems(OrderItemsEntity data) {
        return new OrderItemsModel(
                data.getOrderDetails().toString(),
                data.getProductId().toString(),
                data.getQuantity()
        );
    }

    public PaymentsModel setPaymentDetails(PaymentDetailsEntity data) {
        return new PaymentsModel(
                data.getPaymentId().toString(),
                data.getPaymentMethod(),
                data.getPaymentStatus(),
                data.getCreatedDate().toString()
        );
    }

}
