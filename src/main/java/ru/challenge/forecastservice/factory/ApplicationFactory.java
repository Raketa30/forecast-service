package ru.challenge.forecastservice.factory;

import ru.challenge.forecastservice.ConsoleApplication;
import ru.challenge.forecastservice.domain.enums.AlgorithmType;
import ru.challenge.forecastservice.repository.CurrencyDataInMemoryRepository;
import ru.challenge.forecastservice.service.CommandExecutorService;
import ru.challenge.forecastservice.service.CommandParserService;
import ru.challenge.forecastservice.service.CurrencyDataService;
import ru.challenge.forecastservice.service.CurrencyForecastService;
import ru.challenge.forecastservice.service.utils.CsvDataLoader;
import ru.challenge.forecastservice.validator.impl.ConsoleInputValidator;

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
                new CurrencyForecastService(
                        ForecastAlgorithmFactory.create(AlgorithmType.LAST_SEVEN_DAYS_AVERAGE)));
    }
}
