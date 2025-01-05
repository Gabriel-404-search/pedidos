package br.com.gabriel.challenges.payment.validations;

public class ValidationException extends RuntimeException{
    public ValidationException(String message){
        super(message);
    }
}
