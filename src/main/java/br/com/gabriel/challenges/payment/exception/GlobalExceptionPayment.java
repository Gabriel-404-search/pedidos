package br.com.gabriel.challenges.payment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionPayment {

    @ExceptionHandler(PaymentException.class)
    public ResponseEntity<ResponseError> paymentException(PaymentException e){
        ResponseError response = new ResponseError(
                e.getMessage(),
                e.getCause(),
                HttpStatus.BAD_REQUEST
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

}
