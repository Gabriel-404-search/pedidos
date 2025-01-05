package br.com.gabriel.challenges.payment.dto;

import br.com.gabriel.challenges.payment.enums.TypePayment;
import br.com.gabriel.challenges.payment.enums.TypeStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Data
@Component
public class PaymentDTOV2{

    private TypePayment typePayment;
    private TypeStatus typeStatus;
    private LocalDate paymentDate;
    private Double amount;

}