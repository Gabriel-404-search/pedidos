package br.com.gabriel.challenges.payment.service;

import br.com.gabriel.challenges.payment.refl.Transformador;
import br.com.gabriel.challenges.payment.validations.Strategy;
import br.com.gabriel.challenges.payment.validations.StrategyFactory;
import br.com.gabriel.challenges.payment.validations.ValidCPF;
import br.com.gabriel.challenges.payment.dto.PaymentDTO;
import br.com.gabriel.challenges.payment.dto.PaymentDTOV2;
import br.com.gabriel.challenges.payment.entity.Payment;
import br.com.gabriel.challenges.payment.mapper.PaymentMapper;
import br.com.gabriel.challenges.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository repository;
    private final PaymentMapper mapper;
    private final StrategyFactory strategyFactory;
    private final Transformador transformador;

    public void confirmOrder(PaymentDTO paymentDTO) {
        ValidCPF.valid(paymentDTO.document());
        Strategy strategy = strategyFactory.getStrategy(paymentDTO.typePayment());
        Payment entity = mapper.to(paymentDTO);
        strategy.payment(entity);
        repository.save(entity);
    }
    public PaymentDTOV2 getOrder(Long orderId) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Payment payment = repository.getReferenceById(orderId);
        return transformador.transforme(payment);
    }
}