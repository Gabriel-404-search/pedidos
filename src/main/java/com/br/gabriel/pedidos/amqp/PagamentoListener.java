package com.br.gabriel.pedidos.amqp;

import com.br.gabriel.pedidos.dto.PagamentoDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Component
public class PagamentoListener {
    private Logger logger;

    @RabbitListener(queues = "pagamentos.detalhes-pedido")
    public void recebeMensagem(PagamentoDTO pagamento){
        String mensagem = """
                Dados do pagamento: %s
                Valor R$: %s
                Documento: %s
                Data: %s
                Tipo do pagamento: %s
                """.formatted(pagamento.orderId(),
                pagamento.amount(),
                pagamento.document(),
                pagamento.paymentDate(),
                pagamento.typePayment());

        logger.warn("Recebi a mensagem {}", mensagem);
    }
}
