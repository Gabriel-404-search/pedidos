package br.com.gabriel.challenges.payment.validations;

import br.com.gabriel.challenges.payment.enums.TypePayment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class StrategyFactory  {
    private final Map<TypePayment, Strategy> strategyMap;

    public StrategyFactory(List<Strategy> strategyList) {
        strategyMap = strategyList.stream()
                .collect(Collectors.toMap(Strategy::getType, service -> service));
    }
    public Strategy getStrategy(TypePayment type) {
        return strategyMap.get(type);
    }
}