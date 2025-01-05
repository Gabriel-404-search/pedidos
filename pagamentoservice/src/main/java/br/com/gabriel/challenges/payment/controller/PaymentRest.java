package br.com.gabriel.challenges.payment.controller;

import br.com.gabriel.challenges.payment.dto.PaymentDTO;
import br.com.gabriel.challenges.payment.dto.PaymentDTOV2;
import br.com.gabriel.challenges.payment.mapper.PaymentMapper;
import br.com.gabriel.challenges.payment.refl.ObjectToJson;
import br.com.gabriel.challenges.payment.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/pagamento-service")
public class PaymentRest {

    private final PaymentService service;

    private final RabbitTemplate rabbitTemplate;

    private final PaymentMapper mapper;

    private final ObjectToJson objectToJson;

    @PostMapping("/confimar-pagamento")
public ResponseEntity<Long> confirmOrder(@RequestBody @Valid PaymentDTO paymentDTO){
        service.confirmOrder(paymentDTO);
        String json = objectToJson.transform(paymentDTO);

        rabbitTemplate.convertAndSend("pagamento.ex","", json);
        return ResponseEntity.ok(paymentDTO.orderId());
    }
    @GetMapping("/consultar-pagamento/{orderId}")
public ResponseEntity<PaymentDTOV2> getOrder(@PathVariable Long orderId) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return ResponseEntity.ok(service.getOrder(orderId));
    }
}