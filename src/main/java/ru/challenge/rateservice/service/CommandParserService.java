package ru.challenge.rateservice.service;

import ru.challenge.rateservice.domain.Command;
import ru.challenge.rateservice.domain.enums.Currency;
import ru.challenge.rateservice.domain.enums.Period;

public class CommandParserService {

    public Command parse(String input) {
        var splitInputBySpace = input.split("\\s+");
        var currency = Currency.valueOf(splitInputBySpace[1]);
        var period = Period.valueOf(splitInputBySpace[2]);

        return new Command(currency, period, null);
    }
}
