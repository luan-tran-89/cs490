package com.edu.miu.controller;

import com.edu.miu.domain.PaymentMethod;
import com.edu.miu.enums.CardType;
import com.edu.miu.service.PaymentMethodService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/payment-methods")
@Tag(name = "User Service", description = "Business Payment Methods Service")
@OpenAPIDefinition(servers = { @Server(url = "/payment-methods")},
        info = @Info(title = "Car Rental System - Payment Methods Service", version = "v1",
                description = "This is a documentation for the Payment Methods Service",
                license = @License(name = "Apache 2.0", url = "http://car-fleet-license.com"),
                contact = @Contact(url = "http://car-fleet.com", name = "Car Fleet", email = "car-fleet@gmail"))
)
public class PaymentMethodController {

    private static final Logger logger = LoggerFactory.getLogger(PaymentMethodController.class);

    @Autowired
    private PaymentMethodService paymentMethodService;

    @PostMapping
    public ResponseEntity<PaymentMethod> createPaymentMethod(@RequestBody PaymentMethod paymentMethod) {
        PaymentMethod savedPaymentMethod = paymentMethodService.createPaymentMethod(paymentMethod);
        logger.info("PaymentMethod with ID: {} created successfully.", savedPaymentMethod.getMethodId());
        return ResponseEntity.ok(savedPaymentMethod);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentMethod> updatePaymentMethod(@PathVariable Integer id, @RequestBody PaymentMethod paymentMethod) {
        if (!id.equals(paymentMethod.getMethodId())) {
            logger.warn("Mismatch between ID in path and ID in request body.");
            return ResponseEntity.badRequest().build();
        }
        PaymentMethod updatedPaymentMethod = paymentMethodService.updatePaymentMethod(paymentMethod);
        logger.info("PaymentMethod with ID: {} updated successfully.", updatedPaymentMethod.getMethodId());
        return ResponseEntity.ok(updatedPaymentMethod);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<PaymentMethod>> findByUserId(@PathVariable Integer userId) {
        List<PaymentMethod> paymentMethods = paymentMethodService.findByUserId(userId);
        return ResponseEntity.ok(paymentMethods);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentMethod> findById(@PathVariable Integer id) {
        Optional<PaymentMethod> paymentMethod = paymentMethodService.findById(id);
        return paymentMethod.map(ResponseEntity::ok).orElseGet(() -> {
            logger.warn("PaymentMethod with ID: {} not found.", id);
            return ResponseEntity.notFound().build();
        });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
        paymentMethodService.deleteById(id);
        logger.info("PaymentMethod with ID: {} deleted successfully.", id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/type/{cardType}")
    public ResponseEntity<List<PaymentMethod>> findByCardType(@PathVariable CardType cardType) {
        List<PaymentMethod> paymentMethods = paymentMethodService.findByCardType(cardType);
        return ResponseEntity.ok(paymentMethods);
    }

    @GetMapping("/card/{cardNumber}")
    public ResponseEntity<PaymentMethod> findByCardNumber(@PathVariable String cardNumber) {
        Optional<PaymentMethod> paymentMethod = paymentMethodService.findByCardNumber(cardNumber);
        return paymentMethod.map(ResponseEntity::ok).orElseGet(() -> {
            logger.warn("PaymentMethod with card number: {} not found.", cardNumber);
            return ResponseEntity.notFound().build();
        });
    }
}
