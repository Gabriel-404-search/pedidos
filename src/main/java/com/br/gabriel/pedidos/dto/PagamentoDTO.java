package com.br.gabriel.pedidos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record PagamentoDTO (@NotNull TypePayment typePayment,
                            @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate paymentDate,
                            @NotNull Double amount,
                            @NotBlank String document,
                            @NotNull Long orderId){}
