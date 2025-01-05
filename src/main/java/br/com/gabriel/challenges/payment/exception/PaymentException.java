package br.com.gabriel.challenges.payment.exception;

public class PaymentException extends RuntimeException{
    public PaymentException(String message) {
        super(message);
    }
}
