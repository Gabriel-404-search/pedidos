package br.com.gabriel.challenges.payment.entity;

import br.com.gabriel.challenges.payment.enums.TypePayment;
import br.com.gabriel.challenges.payment.enums.TypeStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity(name = "Pagamentos")
@Table(name = "pagamento")
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private TypePayment typePayment;
    private String document;
    @Enumerated(EnumType.STRING)
    private TypeStatus typeStatus;
    private Double amount;
    private Long orderId;
    private LocalDate paymentDate;
}