package com.myapp.myusersservice.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "address")
public class AddressEntity {

    @Id
    @Column(name = "address_id")
    @GeneratedValue(strategy= GenerationType.UUID)
    private UUID addressId;

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "address_type")
    private String addressType;

    @Column(name = "address_line1")
    private String addressLine1;

    @Column(name = "address_line2")
    private String addressLine2;

    @Column(name = "landmark")
    private String landmark;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postal_code")
    private PostalCodeEntity postalCodeDetails;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "is_default")
    private Boolean isDefault;

}
