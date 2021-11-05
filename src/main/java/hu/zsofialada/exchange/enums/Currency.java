package hu.zsofialada.exchange.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Currency {
    USD("USD"),
    HUF("HUF"),
    GBP("GBP"),
    CHF("CHF"),
    EUR("EUR");

    @Getter
    private String value;
}
