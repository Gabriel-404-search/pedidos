package br.com.gabriel.challenges.payment.exception;

import org.springframework.http.HttpStatus;

public record ResponseError(String message, Throwable cause, HttpStatus status) {
}
