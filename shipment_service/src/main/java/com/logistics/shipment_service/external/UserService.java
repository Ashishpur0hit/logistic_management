package com.logistics.shipment_service.external;

import com.logistics.shipment_service.dto.AvailableDriverDTO;
import com.logistics.shipment_service.response.CustomApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(url = "http://localhost:8081/v1/api/internal",name = "user-service")
public interface UserService {

    @GetMapping("/get-available-driver")
    public CustomApiResponse<AvailableDriverDTO> getAvailableDriver();

}
