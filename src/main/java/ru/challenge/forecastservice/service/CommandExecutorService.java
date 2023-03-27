package ru.challenge.forecastservice.service;

import ru.challenge.forecastservice.domain.Command;
import ru.challenge.forecastservice.domain.ForecastData;
import ru.challenge.forecastservice.domain.enums.AlgorithmType;
import ru.challenge.forecastservice.factory.ForecastAlgorithmFactory;

import java.util.List;

public class CommandExecutorService {
    private final CurrencyDataService dataService;
    private final CurrencyForecastService calculatorService;

    public CommandExecutorService(CurrencyDataService dataService,
                                  CurrencyForecastService calculatorService) {
        this.dataService = dataService;
        this.calculatorService = calculatorService;
    }

    public List<ForecastData> execute(Command command) {
        setupAlgorithm(command.algorithmType());
        var currencyData = dataService.findByCurrency(command.currency());
        return calculatorService.forecast(currencyData, command.period(), command.currency());
    }

    private void setupAlgorithm(AlgorithmType type) {
        if (type != null) {
            calculatorService.setAlgorithm(ForecastAlgorithmFactory.create(type));
        }
    }
}
