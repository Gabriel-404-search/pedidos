package br.com.gabriel.challenges.payment.mapper;

import br.com.gabriel.challenges.payment.dto.PaymentDTO;
import br.com.gabriel.challenges.payment.dto.PaymentDTOV2;
import br.com.gabriel.challenges.payment.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
@SpringBootApplication(scanBasePackages = "br.com.gabriel.challenges.payment")
// as vezes o spring pode nao estar reconhecendo um component, mesmo ele sendo anotado da forma correta
//ai se dever colocar essa anotaçaõ e passar o path para o spring se localizar.
public interface PaymentMapper {

    Payment to(PaymentDTO paymentDTO);

    PaymentDTOV2 to(Payment paymentDTO);
}