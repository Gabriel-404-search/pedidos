package br.com.gabriel.challenges.payment.refl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component
public class ObjectToJson {

    public String transform(Object object) {
        String result = "";

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        //configurando como vai ser a saida do nosso json, se a saida vai ser pretty com uma boa identação, ou se ela vai ser compacta.

        Map<String, Object> mapper = new HashMap<>();
        Class<?> classToBeTransformed = object.getClass();
        //Fizemos um hash map que tem como chave uma string e valor um objeto. e pegamos a classe do no parametro.

        Arrays.stream(classToBeTransformed.getDeclaredFields()).toList().forEach(
                //pegamos os campos da classe
                field -> {
                    field.setAccessible(true);
                    String key = field.getName();
                    Object value = null;
                    // definindo a chave e valor
                    try {
                        value = field.get(object);
                        // armazenando o objeto ao campo valor, que configuramos para receber um objeto.
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    mapper.put(key, value);
                    //aqui colocamos o nome de cada campo, e a classe.
                }
        );
        try {
            result = objectMapper.writeValueAsString(mapper);
            //aqui escrevemos o valor como ‘string’
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
