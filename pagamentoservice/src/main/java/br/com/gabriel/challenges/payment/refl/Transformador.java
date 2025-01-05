package br.com.gabriel.challenges.payment.refl;

import br.com.gabriel.challenges.payment.exception.PaymentException;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

@Component
public class Transformador {
    public <I, O> O transforme(I input) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if (input instanceof HibernateProxy) {
            input = (I) ((HibernateProxy) input).getHibernateLazyInitializer().getImplementation();
        }
        //Aqui estamos tirando a proxy do caminho da classe, pra nao dar erro na hora de adicionar o sufixo.

        Class<?> source = input.getClass();
        // Aqui a gente pega a classe do ‘input’, ou seja, pegamos a Entity, BO, ou outros e armazenamos na variavel
        //pois aqui passamos umas instancia pelo input, e transformamos na classe
        String sourceClassName = source.getName();
        // colocamos o nome da variavel em uma string.

        // Substitui o pacote 'entity' por 'dto' e adiciona o sufixo 'DTOV2'
        String dtoClassName = sourceClassName.replace("entity", "dto") + "DTOV2";
        Class<?> target = null;
        try {
            target = Class.forName(dtoClassName);
            // colocamos o nome da classe que queremos como target numa ‘string’ e depois passamos essa ‘string’ para dentro do metodo para de fato
            // poder manipular o target class.
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        O targetClass = (O) target.getDeclaredConstructor().newInstance();
        // Aqui coletamos a instância da classe target, que seria a classe alvo, no nosso caso o DTO.

        Field[] sourceFields = source.getDeclaredFields();
        Field[] targetFields = target.getDeclaredFields();
        // Aqui armazenamos os campos de cada classe numa lista de campos para podemos manipulalos.

        I finalInput = input;
        Arrays.stream(sourceFields).forEach(sourceField ->
                Arrays.stream(targetFields).forEach(targetField -> {
                    try {
                        if (validate(sourceField, targetField)) {
                            // aqui fazermos a validação dos campos

                            sourceField.setAccessible(true);
                            targetField.setAccessible(true);
                            // aqui pegamos o campo da classe alvo e usamos o metodo ‘set’, e passamos como parametro o objeto, no caso a classe alvo, e logo apos passamos
                            // os campos da classe base. Esse ‘input’ eu perdi-me um pouco para que serve

                            Object sourceValue = sourceField.get(finalInput);

                            // Conversão de enums ou outros tipos incompatíveis
                            if (targetField.getType().isEnum() && sourceValue instanceof Number) {
                                Object enumValue = convertToEnum((Class<? extends Enum>) targetField.getType(), (Number) sourceValue);
                                targetField.set(targetClass, enumValue);
                            } else if (sourceValue != null && !targetField.getType().isAssignableFrom(sourceValue.getClass())) {
                                throw new PaymentException(
                                        "Erro ao associar o enum " + sourceValue.getClass() + " no campo de: " + targetField.getName());
                            } else {
                                targetField.set(targetClass, sourceValue);
                            }
                        }
                    } catch (PaymentException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }));
        return targetClass;
    }

    private boolean validate(Field sourceField, Field targetField) {
        return sourceField.getName().equals(targetField.getName());
        // Validação mais simplificada: nomes iguais são suficientes; tipos são tratados posteriormente.
    }

    private <E extends Enum<E>> E convertToEnum(Class<E> enumClass, Number value) {
        E[] enumConstants = enumClass.getEnumConstants();
        if (value.intValue() >= 0 && value.intValue() < enumConstants.length) {
            return enumConstants[value.intValue()];
        }
        throw new IllegalArgumentException("Invalid enum value: " + value);
    }
}