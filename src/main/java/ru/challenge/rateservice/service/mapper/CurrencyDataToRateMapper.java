package ru.challenge.rateservice.service.mapper;

import ru.challenge.rateservice.domain.CurrencyData;
import ru.challenge.rateservice.domain.CurrencyRate;

public class CurrencyDataToRateMapper {
    private CurrencyDataToRateMapper() {
    }

    public static CurrencyRate mapToRate(CurrencyData currencyData) {
        return new CurrencyRate(currencyData.date(), currencyData.price());
    }
}
