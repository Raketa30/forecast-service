package ru.challenge.rateservice;

import ru.challenge.rateservice.domain.enums.Currency;
import ru.challenge.rateservice.domain.enums.Period;
import ru.challenge.rateservice.service.CommandExecutorService;
import ru.challenge.rateservice.service.CommandParserService;
import ru.challenge.rateservice.validator.impl.ConsoleInputValidator;

import java.util.Scanner;

import static ru.challenge.rateservice.service.utils.ConsolePrintUtils.print;
import static ru.challenge.rateservice.service.utils.ConsolePrintUtils.printHelp;

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
            print(WELCOME_MESSAGE);
            printHelp(String.format(HELP_MESSAGE, Currency.toInlineValues(), Period.toInlineValues()));
            while (true) {
                print(ENTER_COMMAND_MESSAGE);
                var consoleInput = scanner.nextLine().toUpperCase().trim();
                if (consoleInput.startsWith(COMMAND_EXIT)) {
                    return;
                }
                if (consoleInput.startsWith(COMMAND_HELP)) {
                    printHelp(HELP_MESSAGE);
                    continue;
                }
                process(consoleInput);
            }
        }
    }

    private void process(String consoleInput) {
        if (!validator.isValid(consoleInput)) {
            print(RETRY_REQUEST);
            return;
        }
        try {
            var command = parserService.parse(consoleInput);
            print(executorService.execute(command));
        } catch (RuntimeException e) {
            print(RETRY_REQUEST);
        }
    }
}
