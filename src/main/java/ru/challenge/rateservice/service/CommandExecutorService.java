package ru.challenge.rateservice.service;

import ru.challenge.rateservice.domain.Command;
import ru.challenge.rateservice.domain.CurrencyRate;
import ru.challenge.rateservice.domain.enums.AlgorithmType;
import ru.challenge.rateservice.factory.CalculationAlgorithmFactory;

import java.util.List;

public class CommandExecutorService {
    private final CurrencyDataService dataService;
    private final CurrencyRateCalculatorService calculatorService;

    public CommandExecutorService(CurrencyDataService dataService,
                                  CurrencyRateCalculatorService calculatorService) {
        this.dataService = dataService;
        this.calculatorService = calculatorService;
    }

    public List<CurrencyRate> execute(Command command) {
        setupAlgorithm(command.algorithmType());
        var currencyData = dataService.findByCurrency(command.currency());
        return calculatorService.calculate(currencyData, command.period(), command.currency());
    }

    private void setupAlgorithm(AlgorithmType type) {
        if (type != null) {
            calculatorService.setAlgorithm(CalculationAlgorithmFactory.create(type));
        }
    }
}
