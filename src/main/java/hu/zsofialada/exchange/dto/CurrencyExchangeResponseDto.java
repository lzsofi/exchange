package hu.zsofialada.exchange.dto;

import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Value
@EqualsAndHashCode(callSuper = true)
public class CurrencyExchangeResponseDto extends CurrencyExchangeRequestDto {
    Double amountTo;
}

