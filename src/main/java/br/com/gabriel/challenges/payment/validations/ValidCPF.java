package br.com.gabriel.challenges.payment.validations;

import br.com.caelum.stella.validation.CPFValidator;
import br.com.gabriel.challenges.payment.exception.PaymentException;

public class ValidCPF {

    public static void valid (String cpf){
        CPFValidator cpfValidator = new CPFValidator();
        try{ cpfValidator.assertValid(cpf);
        }catch(PaymentException e){
            throw new RuntimeException("CPF inválido!");
        }
    }
}