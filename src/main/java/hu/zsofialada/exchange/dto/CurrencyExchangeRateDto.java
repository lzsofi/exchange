package hu.zsofialada.exchange.dto;

import hu.zsofialada.exchange.enums.Currency;
import lombok.Value;

@Value
public class CurrencyExchangeRateDto {
    Currency currency;
    Double buyingRate;
    Double sellingRate;
}
