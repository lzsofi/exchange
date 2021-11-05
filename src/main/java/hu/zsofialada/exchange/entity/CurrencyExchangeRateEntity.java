package hu.zsofialada.exchange.entity;

import hu.zsofialada.exchange.enums.Currency;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "CURRENCY")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyExchangeRateEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Currency currencyFrom;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Currency currencyTo;

    @Column(nullable = false)
    private Double buyingRate;

    @Column(nullable = false)
    private Double sellingRate;
}
