package ru.challenge.rateservice.service;

import ru.challenge.rateservice.domain.CurrencyData;
import ru.challenge.rateservice.domain.enums.Currency;
import ru.challenge.rateservice.repository.CurrencyDataInMemoryRepository;

import java.util.List;

public class CurrencyDataService {
    private final CurrencyDataInMemoryRepository currencyDataInMemoryRepository;

    public CurrencyDataService(CurrencyDataInMemoryRepository currencyDataInMemoryRepository) {
        this.currencyDataInMemoryRepository = currencyDataInMemoryRepository;
    }

    public List<CurrencyData> findByCurrency(Currency currency) {
        return currencyDataInMemoryRepository.findByCurrency(currency);
    }
}
