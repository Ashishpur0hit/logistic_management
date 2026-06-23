package com.logistics.user_service.controller;

import com.logistics.user_service.entity.Address;
import com.logistics.user_service.response.CustomApiResponse;
import com.logistics.user_service.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("v1/api/address")
public class AddressController {

    @Autowired
    AddressService addressService;

    @PostMapping("/add/{userId}")
    ResponseEntity<CustomApiResponse<Address>> addAddress(@PathVariable UUID userId , @RequestBody Address address)
    {
        Address newAddress = addressService.addAddress(userId,address);
        CustomApiResponse<Address> response = CustomApiResponse.<Address>builder()
                .success(true)
                .body(newAddress)
                .message("Address added successfully !")
                .build();
        return  new ResponseEntity<>(response, HttpStatus.OK);
    }


    //get all Addresses
    @GetMapping("/get-all/{userId}")
    public ResponseEntity<CustomApiResponse<Page<Address>>> getAllAddress(@PathVariable UUID userId , @RequestParam Integer page)
    {
        Page<Address> addresses = addressService.getAllAddress(userId,page);
        CustomApiResponse<Page<Address>> response = CustomApiResponse.<Page<Address>>builder()
                .success(true)
                .body(addresses)
                .message("Address fetched successfully !")
                .build();
        return  new ResponseEntity<>(response, HttpStatus.OK);

    }


    // getAddress By Id

    @GetMapping("/get/{addressId}")
    ResponseEntity<CustomApiResponse<Address>> getAddress(@PathVariable UUID addressId , @RequestBody Address address)
    {
        Address fetchedAddress = addressService.getAddressById(addressId);
        CustomApiResponse<Address> response = CustomApiResponse.<Address>builder()
                .success(true)
                .body(fetchedAddress)
                .message("Address fetched successfully !")
                .build();
        return  new ResponseEntity<>(response, HttpStatus.OK);
    }





    // delete address
    @DeleteMapping("/delete/{addressId}")
    ResponseEntity<CustomApiResponse<Address>> deleteAddress(@PathVariable UUID addressId)
    {
        String msg = addressService.deleteAddress(addressId);
        CustomApiResponse<Address> response = CustomApiResponse.<Address>builder()
                .success(true)
                .body(null)
                .message(msg)
                .build();
        return  new ResponseEntity<>(response, HttpStatus.OK);
    }



    @PutMapping("/update/{addressId}")
    ResponseEntity<CustomApiResponse<Address>> updateAddress(@PathVariable UUID addressId , @RequestBody Address address)
    {
        Address newAddress = addressService.updateAddress(addressId,address);
        CustomApiResponse<Address> response = CustomApiResponse.<Address>builder()
                .success(true)
                .body(newAddress)
                .message("Address update successfully !")
                .build();
        return  new ResponseEntity<>(response, HttpStatus.OK);
    }


}
