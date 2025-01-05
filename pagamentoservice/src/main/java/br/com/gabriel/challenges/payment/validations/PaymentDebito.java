package br.com.gabriel.challenges.payment.validations;

import br.com.gabriel.challenges.payment.entity.Payment;
import br.com.gabriel.challenges.payment.enums.TypePayment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PaymentDebito implements Strategy {

    @Override
    public void payment(Payment entity) {
        log.info("Pagando com o debito!");
    }

    @Override
    public TypePayment getType() {
        return TypePayment.DEBITO;
    }
}