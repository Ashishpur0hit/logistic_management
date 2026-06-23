package com.logistics.user_service.service;

import com.logistics.user_service.entity.Address;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface AddressService {
    Address addAddress(UUID userId , Address address);

    Page<Address> getAllAddress(UUID userId,int page);

    Address getAddressById(UUID addressId);

    Address updateAddress(UUID addressId , Address address);

    String deleteAddress(UUID addressId);
}
