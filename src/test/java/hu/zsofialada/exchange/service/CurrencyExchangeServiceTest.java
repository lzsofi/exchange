package hu.zsofialada.exchange.service;

import hu.zsofialada.exchange.dto.CurrencyExchangeRateDto;
import hu.zsofialada.exchange.dto.CurrencyExchangeRequestDto;
import hu.zsofialada.exchange.dto.CurrencyExchangeResponseDto;
import hu.zsofialada.exchange.entity.CurrencyExchangeRateEntity;
import hu.zsofialada.exchange.enums.Currency;
import hu.zsofialada.exchange.enums.Rate;
import hu.zsofialada.exchange.repository.CurrencyExchangeRateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CurrencyExchangeServiceTest {

    private static final double BUYING_RATE = 0.87;
    private static final double SELLING_RATE = 0.86;


    private CurrencyExchangeService currencyExchangeService;
    private CurrencyExchangeRateRepository currencyExchangeRateRepository;

    @BeforeEach
    public void setUp() {
        currencyExchangeRateRepository = mock(CurrencyExchangeRateRepository.class);
        currencyExchangeService = new CurrencyExchangeService(currencyExchangeRateRepository);
    }

    @Test
    @DisplayName("should find currency exchange rates for USD")
    public void retrieve_exchange_rates_by_currency() {
        //Given
        when(currencyExchangeRateRepository.findByCurrencyFrom(Currency.USD))
                .thenReturn(List.of(new CurrencyExchangeRateEntity(1L, Currency.USD, Currency.EUR, BUYING_RATE, SELLING_RATE)));

        // When
        List<CurrencyExchangeRateDto> currencyExchangeRateDtoList = currencyExchangeService.retrieveExchangeRates(Currency.USD);

        // Then
        assertEquals(1, currencyExchangeRateDtoList.size());
        assertEquals(BUYING_RATE, currencyExchangeRateDtoList.get(0).getBuyingRate());
        assertEquals(SELLING_RATE, currencyExchangeRateDtoList.get(0).getSellingRate());
    }

    @Test
    @DisplayName("should exchange USD to EUR")
    public void exchange_currency() {
        final double AMOUNT_FROM = 50.0;

        //Given
        when(currencyExchangeRateRepository.findByCurrencyFromAndCurrencyTo(Currency.USD, Currency.EUR))
                .thenReturn(new CurrencyExchangeRateEntity(1L, Currency.USD, Currency.EUR, BUYING_RATE, SELLING_RATE));

        // When
        CurrencyExchangeResponseDto currencyExchangeResponseDto = currencyExchangeService.exchangeCurrency(
                new CurrencyExchangeRequestDto(
                        Currency.USD,
                        Currency.EUR,
                        AMOUNT_FROM,
                        Rate.BUYING
                )
        );

        // Then
        assertNotNull(currencyExchangeResponseDto);
        assertEquals(AMOUNT_FROM * BUYING_RATE, currencyExchangeResponseDto.getAmountTo());
    }
}
