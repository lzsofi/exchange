package hu.zsofialada.exchange.dto;


import hu.zsofialada.exchange.enums.Currency;
import hu.zsofialada.exchange.enums.Rate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Min;

@SuperBuilder
@Data
@AllArgsConstructor
public class CurrencyExchangeRequestDto {
    private final Currency currencyFrom;
    private final Currency currencyTo;
    @Min(0)
    private final Double amountFrom;
    private final Rate rate;
}
