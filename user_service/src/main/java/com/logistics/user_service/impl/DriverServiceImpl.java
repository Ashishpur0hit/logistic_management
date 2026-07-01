package com.logistics.user_service.impl;

import com.logistics.common.RedisDriver;
import com.logistics.user_service.dto.AvailableDriverDTO;
import com.logistics.user_service.dto.DriverDTO;

import com.logistics.user_service.entity.Driver;
import com.logistics.user_service.entity.User;
import com.logistics.user_service.exception.ResourceNotFoundException;
import com.logistics.user_service.redis.RedisUtils;
import com.logistics.user_service.repository.DriverRepository;
import com.logistics.user_service.repository.UserRepository;
import com.logistics.user_service.service.DriverService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisConnectionUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DriverServiceImpl implements DriverService {

    @Autowired
    DriverRepository driverRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    RedisUtils redisUtils;


    @Override
    public DriverDTO addDriver(UUID userId, DriverDTO driverDTO) {
        User user = userRepository.findById(userId).orElseThrow(()-> new UsernameNotFoundException("User not Found !!"));
        Driver newdriver = modelMapper.map(driverDTO, Driver.class);
        newdriver.setUser(user);
        newdriver.setAvailability(true);
        Driver savedDriver = driverRepository.save(newdriver);

        return modelMapper.map(savedDriver, DriverDTO.class);
    }

    @Override
    public DriverDTO getDriver(UUID userId) {
        Driver driver = driverRepository.findByUser_UserId(userId).orElseThrow(() -> new ResourceNotFoundException("Driver not found !!"));
        return modelMapper.map(driver,DriverDTO.class);
    }

    @Override
    public void updateDriver(String driverId, Boolean availability) {
        Driver existingDriver = driverRepository.findById(driverId).orElseThrow(()-> new ResourceNotFoundException("Driver not Found !!"));
        existingDriver.setAvailability(availability);
        driverRepository.save(existingDriver);
        return ;
    }

    @Transactional
    @Override
    public AvailableDriverDTO getAvailableDriver() {
        Driver driver = driverRepository.findFirstByAvailabilityTrue().orElseThrow(()->new ResourceNotFoundException("Drivers are not available !"));
        driver.setAvailability(false);
        Driver savedDriver =driverRepository.save(driver);


        RedisDriver redisdriver  = RedisDriver.builder()
                .longitude(savedDriver.getCurrentLongitude())
                .latitude(savedDriver.getCurrentLatitude())
                .driverName(savedDriver.getUser().getFirstname()+ " " +savedDriver.getUser().getLastname() )
                .driverId(savedDriver.getDriverId())
                .build();

        // save driver location in redis
        String key = "driver:location:"+redisdriver.getDriverId();
        redisUtils.saveRedis(key,redisdriver);


        return AvailableDriverDTO.builder()
                .driver_id(String.valueOf(driver.getDriverId()))
                .firstname(driver.getUser().getFirstname())
                .lastname(driver.getUser().getLastname())
                .build();
    }

    @Override
    public RedisDriver getDriverSnapshot(UUID driverId) {
        Driver driver = driverRepository.findById(String.valueOf(driverId)).orElseThrow(()-> new ResourceNotFoundException("Driver does not exist !"));
        RedisDriver redisdriver  = RedisDriver.builder()
                .longitude(driver.getCurrentLongitude())
                .latitude(driver.getCurrentLatitude())
                .driverName(driver.getUser().getFirstname()+ " " +driver.getUser().getLastname() )
                .driverId(driver.getDriverId())
                .build();
        return redisUtils.saveRedis("driver:location:"+driver.getDriverId(),redisdriver);
    }




}
