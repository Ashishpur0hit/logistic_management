package com.logistics.api_gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator (RouteLocatorBuilder builder)
    {
        return builder.routes()

                .route("user-service", r -> r
                        .path("/v1/api/user/**" , "/v1/api/auth/**", "/v1/api/driver/**" , "/v1/api/admin/**" , "/v1/api/internal/**")
                        .uri("lb://USER-SERVICE"))

                .route("shipment-service", r -> r
                        .path("/v1/api/shipment/**")
                        .uri("lb://SHIPMENT-SERVICE"))

                .route("payment-service", r -> r
                        .path("/v1/api/payment/**")
                        .uri("lb://PAYMENT-SERVICE"))

                .build();
    }

}
