package com.logistics.user_service.impl;

import com.logistics.user_service.dto.UserDTO;
import com.logistics.user_service.entity.Driver;
import com.logistics.user_service.entity.User;
import com.logistics.user_service.enums.AccountStatus;
import com.logistics.user_service.exception.ResourceNotFoundException;
import com.logistics.user_service.repository.DriverRepository;
import com.logistics.user_service.repository.UserRepository;
import com.logistics.user_service.service.AdminService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    DriverRepository driverRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public Page<Driver> getAllDrivers(Integer page) {
        Pageable pageable = PageRequest.of(page,10);
        return driverRepository.findAll(pageable);
    }

    @Override
    public Page<User> getAllUsers(Integer page) {
        Pageable pageable = PageRequest.of(page,10);
        return userRepository.findAll(pageable);
    }

    @Override
    public UserDTO markUserDisable(UUID userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User nor found !"));
        user.setStatus(AccountStatus.DISABLE);
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser,UserDTO.class);
    }

    @Override
    public UserDTO activetUser(UUID userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User nor found !"));
        user.setStatus(AccountStatus.ACTIVE);
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser,UserDTO.class);
    }

    @Override
    public UserDTO getUser(UUID userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User nor found !"));
        return modelMapper.map(user,UserDTO.class);
    }
}
