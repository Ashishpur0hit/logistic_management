package com.logistics.user_service.impl;

import com.logistics.user_service.entity.Address;
import com.logistics.user_service.entity.User;
import com.logistics.user_service.exception.EmailAlreadyExistException;
import com.logistics.user_service.exception.ResourceNotFoundException;
import com.logistics.user_service.repository.AddressRepository;
import com.logistics.user_service.repository.UserRepository;
import com.logistics.user_service.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AddressRepository addressRepository;

    @Override
    public Address addAddress(UUID userId, Address address) {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User Does not exist !!"));
        address.setUser(user);
        return addressRepository.save(address);

    }

    @Override
    public Page<Address> getAllAddress(UUID userId,int page) {
        Pageable pageable = PageRequest.of(page,10);
        return addressRepository.findByUser_UserId(userId,pageable);
    }

    @Override
    public Address getAddressById(UUID addressId) {
        return addressRepository.findById(addressId).orElseThrow(()-> new ResourceNotFoundException("Address not found !"));
    }

    @Override
    public Address updateAddress(UUID addressId, Address address) {
        Address existingAddress = addressRepository.findById(addressId).orElseThrow(()-> new ResourceNotFoundException("Address not found !"));
        existingAddress.setDetailedAddress(address.getDetailedAddress());
        existingAddress.setCity(address.getCity());
        existingAddress.setState(address.getState());
        existingAddress.setCountry(address.getCountry());
        existingAddress.setType(address.getType());
        return addressRepository.save(existingAddress);
    }

    @Override
    public String deleteAddress(UUID addressId) {
        addressRepository.deleteById(addressId);
        return "Address deleted successfully !!";
    }
}
