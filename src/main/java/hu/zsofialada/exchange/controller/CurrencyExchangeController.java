package hu.zsofialada.exchange.controller;

import hu.zsofialada.exchange.dto.CurrencyExchangeRateDto;
import hu.zsofialada.exchange.dto.CurrencyExchangeRequestDto;
import hu.zsofialada.exchange.dto.CurrencyExchangeResponseDto;
import hu.zsofialada.exchange.enums.Currency;
import hu.zsofialada.exchange.service.CurrencyExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CurrencyExchangeController {

    @Autowired
    private CurrencyExchangeService currencyExchangeService;

    @GetMapping(value = "/exchange/rates")
    public List<CurrencyExchangeRateDto> retrieveExchangeRates(@RequestParam final Currency currency) {
        return currencyExchangeService.retrieveExchangeRates(currency);
    }

    @PostMapping(value = "/exchange")
    public CurrencyExchangeResponseDto exchangeCurrency(@RequestBody @Valid final CurrencyExchangeRequestDto currencyExchangeRequest) {
        return currencyExchangeService.exchangeCurrency(currencyExchangeRequest);
    }
}
