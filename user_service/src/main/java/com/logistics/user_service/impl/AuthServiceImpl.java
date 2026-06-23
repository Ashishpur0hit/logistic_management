package com.logistics.user_service.impl;

import com.logistics.user_service.dto.LoginRequest;
import com.logistics.user_service.dto.UserDTO;
import com.logistics.user_service.entity.Address;
import com.logistics.user_service.entity.User;
import com.logistics.user_service.exception.EmailAlreadyExistException;
import com.logistics.user_service.jwt.JwtService;
import com.logistics.user_service.repository.UserRepository;
import com.logistics.user_service.service.AuthService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public String login(LoginRequest request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );


        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        assert userDetails != null;
        return jwtService.generateToken(userDetails);
    }


    @Override
    public UserDTO addUser(UserDTO userDTO) {

        Optional<User> existingUser = userRepository.findByEmail(userDTO.getEmail());
        if (existingUser.isPresent())  throw new EmailAlreadyExistException("Email already exist !!");
        User user = modelMapper.map(userDTO, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        for(Address address : userDTO.getAddresses()) {
            address.setUser(user);
        }

        user.setAddresses(userDTO.getAddresses());
        User savedUser = userRepository.save(user);
        return  modelMapper.map(savedUser, UserDTO.class);
    }

}
