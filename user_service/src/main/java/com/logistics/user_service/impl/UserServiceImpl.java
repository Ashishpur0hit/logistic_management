package com.logistics.user_service.impl;

import com.logistics.user_service.dto.UserDTO;
import com.logistics.user_service.dto.UserUpdateRequest;
import com.logistics.user_service.entity.User;
import com.logistics.user_service.exception.EmailAlreadyExistException;
import com.logistics.user_service.exception.ResourceNotFoundException;
import com.logistics.user_service.repository.UserRepository;
import com.logistics.user_service.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;


    @Override
    public UserDTO getUser(UUID userId) {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User not found !"));
        return modelMapper.map(user,UserDTO.class);
    }

    @Override
    public UserDTO updateUser(UUID userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User not found !"));
        user.setFirstname(request.getFirstname());
        user.setEmail(request.getEmail());
        user.setLastname(request.getLastname());
        User updatedUser  = userRepository.save(user);
        return modelMapper.map(updatedUser,UserDTO.class);
    }
}
