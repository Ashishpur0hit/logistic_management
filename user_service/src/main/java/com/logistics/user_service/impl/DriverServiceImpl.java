package com.logistics.user_service.impl;

import com.logistics.common.RedisDriver;
import com.logistics.user_service.dto.AvailableDriverDTO;
import com.logistics.user_service.dto.DriverDTO;

import com.logistics.user_service.entity.Driver;
import com.logistics.user_service.entity.User;
import com.logistics.user_service.exception.ResourceNotFoundException;
import com.logistics.user_service.repository.DriverRepository;
import com.logistics.user_service.repository.UserRepository;
import com.logistics.user_service.service.DriverService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    RedisTemplate<String, Object> redisTemplate;

    @Override
    public DriverDTO addDriver(UUID userId, DriverDTO driverDTO) {
        User user = userRepository.findById(userId).orElseThrow(()-> new UsernameNotFoundException("User not Found !!"));
        Driver newdriver = modelMapper.map(driverDTO, Driver.class);
        newdriver.setUser(user);
        newdriver.setAvailability(false);
        Driver savedDriver = driverRepository.save(newdriver);

        return modelMapper.map(savedDriver, DriverDTO.class);
    }

    @Override
    public DriverDTO getDriver(UUID userId) {
        Driver driver = driverRepository.findByUser_UserId(userId).orElseThrow(() -> new ResourceNotFoundException("Driver not found !!"));
        return modelMapper.map(driver,DriverDTO.class);
    }

    @Override
    public DriverDTO updateDriver(UUID userId, Boolean availability) {
        Driver existingDriver = driverRepository.findByUser_UserId(userId).orElseThrow(()-> new ResourceNotFoundException("Driver not Found !!"));
        existingDriver.setAvailability(availability);
        Driver updatedDriver = driverRepository.save(existingDriver);
        return modelMapper.map(updatedDriver,DriverDTO.class);
    }

    @Transactional
    @Override
    public AvailableDriverDTO getAvailableDriver() {
        Driver driver = driverRepository.findFirstByAvailabilityTrue().orElseThrow(()->new ResourceNotFoundException("Drivers are not available !"));
        driver.setAvailability(false);
        Driver savedDriver =driverRepository.save(driver);

        // save driver location in redis
        saveRedis(savedDriver);


        return AvailableDriverDTO.builder()
                .driver_id(driver.getDriverId())
                .firstname(driver.getUser().getFirstname())
                .lastname(driver.getUser().getLastname())
                .build();
    }



    public void saveRedis(Driver savedDriver)
    {
        RedisDriver driver = RedisDriver.builder()
                .latitude(savedDriver.getCurrentLatitude())
                .longitude(savedDriver.getCurrentLongitude())
                .driverId(savedDriver.getDriverId())
                .driverName(savedDriver.getUser().getFirstname() + " " + savedDriver.getUser().getLastname())
                .build();
        // Save Driver Location to redis
        redisTemplate.opsForValue().set(
                "driver:location:"+driver.getDriverId(),
                driver
        );
    }


}
