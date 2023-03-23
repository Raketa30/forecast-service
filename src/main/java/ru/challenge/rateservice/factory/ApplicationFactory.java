package ru.challenge.rateservice.factory;

import ru.challenge.rateservice.ConsoleApplication;
import ru.challenge.rateservice.domain.enums.AlgorithmType;
import ru.challenge.rateservice.repository.CurrencyDataInMemoryRepository;
import ru.challenge.rateservice.service.CommandExecutorService;
import ru.challenge.rateservice.service.CommandParserService;
import ru.challenge.rateservice.service.CurrencyDataService;
import ru.challenge.rateservice.service.CurrencyRateCalculatorService;
import ru.challenge.rateservice.service.utils.CsvDataLoader;
import ru.challenge.rateservice.validator.impl.ConsoleInputValidator;

public class ApplicationFactory {
    private ApplicationFactory() {

    }

    public static ConsoleApplication create() {
        return new ConsoleApplication(
                createCommandExecutor(),
                new CommandParserService(),
                new ConsoleInputValidator()
        );
    }

    private static CommandExecutorService createCommandExecutor() {
        return new CommandExecutorService(
                new CurrencyDataService(
                        new CurrencyDataInMemoryRepository(CsvDataLoader.loadCurrencyData())),
                new CurrencyRateCalculatorService(
                        CalculationAlgorithmFactory.create(AlgorithmType.LAST_SEVEN_DAYS_AVERAGE)));
    }
}
