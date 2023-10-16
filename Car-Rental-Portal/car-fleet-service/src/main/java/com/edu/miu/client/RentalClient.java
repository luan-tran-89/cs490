package com.edu.miu.client;

import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author gasieugru
 */
@FeignClient(name = "RENTAL-SERVICE")
public interface RentalClient {

    @GetMapping("/rentals")
    Object getAllRentals();

    @GetMapping("/rentals/{userId}")
    Object getRentalsByUser(@PathVariable("userId") Integer userId);

    @GetMapping
    Object getReservations(@PathVariable("userId") Integer userId);

    @GetMapping("/rentals/user/{userId}/active-rentals")
    boolean isUserCurrentlyRenting(@PathVariable("userId") Integer userId);

}
