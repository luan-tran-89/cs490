package rentalservice.domain;

import lombok.Data;
import jakarta.persistence.*;

import java.util.Date;

@Data
@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer paymentId;

    @Column(nullable = false)
    private Double amount;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date date;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentMethodType paymentMethodType;

    private String cardDetails; // You might want to handle this more securely in a real application

    // Enums for payment status and method type
    public enum PaymentStatus {
        COMPLETED,
        PENDING,
        FAILED
    }

    public enum PaymentMethodType {
        VISA,
        MASTERCARD
    }

    // Additional methods, attributes, or annotations can be added as required.
}
