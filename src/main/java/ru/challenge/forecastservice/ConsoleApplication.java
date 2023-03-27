package ru.challenge.forecastservice;

import ru.challenge.forecastservice.domain.enums.Currency;
import ru.challenge.forecastservice.domain.enums.Period;
import ru.challenge.forecastservice.service.CommandExecutorService;
import ru.challenge.forecastservice.service.CommandParserService;
import ru.challenge.forecastservice.service.utils.ConsolePrintUtils;
import ru.challenge.forecastservice.validator.impl.ConsoleInputValidator;

import java.util.Scanner;

import static ru.challenge.forecastservice.service.utils.ConsolePrintUtils.printPurple;

public class ConsoleApplication {

    private static final String WELCOME_MESSAGE = "Добрый день! Рады приветствовать в нашем сервисе прогноза валют!";
    private static final String HELP_MESSAGE = "Поддерживаемые команды: rate [%s] [%s]\nДля помощи используйте команду: help\nДля выхода введите: exit\n";
    private static final String ENTER_COMMAND_MESSAGE = "Введите команду: ";
    private static final String RETRY_REQUEST = "Неверная команда, повторите ввод";
    private static final String COMMAND_EXIT = "EXIT";
    private static final String COMMAND_HELP = "HELP";

    private final CommandExecutorService executorService;
    private final CommandParserService parserService;
    private final ConsoleInputValidator validator;

    public ConsoleApplication(CommandExecutorService executorService,
                              CommandParserService parserService,
                              ConsoleInputValidator validator) {
        this.executorService = executorService;
        this.parserService = parserService;
        this.validator = validator;
    }

    public void start() {
        try (var scanner = new Scanner(System.in)) {
            ConsolePrintUtils.printBlue(WELCOME_MESSAGE);
            printPurple(String.format(HELP_MESSAGE, Currency.toInlineValues(), Period.toInlineValues()));
            while (true) {
                ConsolePrintUtils.printBlue(ENTER_COMMAND_MESSAGE);
                var consoleInput = scanner.nextLine().toUpperCase().trim();
                if (consoleInput.startsWith(COMMAND_EXIT)) {
                    return;
                }
                if (consoleInput.startsWith(COMMAND_HELP)) {
                    printPurple(String.format(HELP_MESSAGE, Currency.toInlineValues(), Period.toInlineValues()));
                    continue;
                }
                process(consoleInput);
            }
        }
    }

    private void process(String consoleInput) {
        if (!validator.isValid(consoleInput)) {
            ConsolePrintUtils.printBlue(RETRY_REQUEST);
            return;
        }
        try {
            var command = parserService.parse(consoleInput);
            ConsolePrintUtils.print(executorService.execute(command));
        } catch (RuntimeException e) {
            ConsolePrintUtils.printBlue(RETRY_REQUEST);
        }
    }
}
