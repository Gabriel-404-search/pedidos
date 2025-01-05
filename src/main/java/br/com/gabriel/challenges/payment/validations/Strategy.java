package br.com.gabriel.challenges.payment.validations;

import br.com.gabriel.challenges.payment.entity.Payment;
import br.com.gabriel.challenges.payment.enums.TypePayment;
import org.springframework.stereotype.Component;

@Component
public interface Strategy {
    void payment(Payment entity);
    TypePayment getType();
}