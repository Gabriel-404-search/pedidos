package br.com.gabriel.challenges.payment.dto;

import br.com.gabriel.challenges.payment.enums.TypePayment;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record PaymentDTO(@NotNull TypePayment typePayment,
                         @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate paymentDate,
                         @NotNull Double amount,
                         @NotBlank String document,
                         @NotNull Long orderId){}