package hu.zsofialada.exchange.repository;

import hu.zsofialada.exchange.DbInitializer;
import hu.zsofialada.exchange.entity.CurrencyExchangeRateEntity;
import hu.zsofialada.exchange.enums.Currency;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@ContextConfiguration(initializers = DbInitializer.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(scripts = "/sql/init_currencies.sql")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CurrencyExchangeRateRepositoryTest {

    @Autowired
    private CurrencyExchangeRateRepository currencyExchangeRateRepository;

    @Test
    @DisplayName("should find 2 currency exchange rates for USD")
    public void find_exchange_rates_by_currency() {
        // Given - When
        List<CurrencyExchangeRateEntity> exchangeRateEntities = currencyExchangeRateRepository.findByCurrencyFrom(Currency.USD);

        // Then
        assertEquals(2, exchangeRateEntities.size());
    }

    @Test
    @DisplayName("should find the exchange rate for USD - EUR pair")
    public void exchange_currency() {
        // Given - When
        CurrencyExchangeRateEntity exchangeRateEntity = currencyExchangeRateRepository.findByCurrencyFromAndCurrencyTo(Currency.USD, Currency.EUR);

        // Then
        assertNotNull(exchangeRateEntity);
        assertEquals(Currency.USD, exchangeRateEntity.getCurrencyFrom());
        assertEquals(Currency.EUR, exchangeRateEntity.getCurrencyTo());
    }
}
