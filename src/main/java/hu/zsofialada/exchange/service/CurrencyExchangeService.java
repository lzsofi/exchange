package hu.zsofialada.exchange.service;

import hu.zsofialada.exchange.dto.CurrencyExchangeRateDto;
import hu.zsofialada.exchange.dto.CurrencyExchangeRequestDto;
import hu.zsofialada.exchange.dto.CurrencyExchangeResponseDto;
import hu.zsofialada.exchange.entity.CurrencyExchangeRateEntity;
import hu.zsofialada.exchange.enums.Currency;
import hu.zsofialada.exchange.enums.Rate;
import hu.zsofialada.exchange.repository.CurrencyExchangeRateRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CurrencyExchangeService {

    @Autowired
    private CurrencyExchangeRateRepository currencyExchangeRateRepository;

    public List<CurrencyExchangeRateDto> retrieveExchangeRates(final Currency currency) {
        return currencyExchangeRateRepository.findByCurrencyFrom(currency)
                .stream()
                .map(entity -> new CurrencyExchangeRateDto(entity.getCurrencyTo(), entity.getBuyingRate(), entity.getSellingRate()))
                .collect(Collectors.toList());
    }

    public CurrencyExchangeResponseDto exchangeCurrency(final CurrencyExchangeRequestDto currencyExchangeRequest) {
        final CurrencyExchangeRateEntity currencyExchangeRate = currencyExchangeRateRepository.findByCurrencyFromAndCurrencyTo(
                currencyExchangeRequest.getCurrencyFrom(),
                currencyExchangeRequest.getCurrencyTo()
        );
        final Double exchangeRate = currencyExchangeRequest.getRate() == Rate.BUYING
                ? currencyExchangeRate.getBuyingRate()
                : currencyExchangeRate.getSellingRate();
        return CurrencyExchangeResponseDto.builder()
                .currencyFrom(currencyExchangeRequest.getCurrencyFrom())
                .currencyTo(currencyExchangeRequest.getCurrencyTo())
                .amountFrom(currencyExchangeRequest.getAmountFrom())
                .rate(currencyExchangeRequest.getRate())
                .amountTo(exchangeRate * currencyExchangeRequest.getAmountFrom()).build();
    }
}
