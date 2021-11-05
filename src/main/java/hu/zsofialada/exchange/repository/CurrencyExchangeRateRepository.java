package hu.zsofialada.exchange.repository;

import hu.zsofialada.exchange.enums.Currency;
import hu.zsofialada.exchange.entity.CurrencyExchangeRateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CurrencyExchangeRateRepository extends JpaRepository<CurrencyExchangeRateEntity, Long> {

    List<CurrencyExchangeRateEntity> findByCurrencyFrom(final Currency currency);
    CurrencyExchangeRateEntity findByCurrencyFromAndCurrencyTo(final Currency currencyFrom,final Currency currencyTo);
}
