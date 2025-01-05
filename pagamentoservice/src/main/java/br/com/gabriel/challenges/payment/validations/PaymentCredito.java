package br.com.gabriel.challenges.payment.validations;

import br.com.gabriel.challenges.payment.entity.Payment;
import br.com.gabriel.challenges.payment.enums.TypePayment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PaymentCredito implements Strategy {

    @Override
    public void payment(Payment entity) {
        log.info("Pagando com o credito!");
    }

    @Override
    public TypePayment getType() {
        return TypePayment.CREDITO;
    }
}
