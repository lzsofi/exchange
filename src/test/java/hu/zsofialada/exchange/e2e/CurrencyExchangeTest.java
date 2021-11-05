package hu.zsofialada.exchange.e2e;

import hu.zsofialada.exchange.DbInitializer;
import hu.zsofialada.exchange.dto.CurrencyExchangeRateDto;
import hu.zsofialada.exchange.enums.Currency;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = DbInitializer.class)
@Sql(scripts = "/sql/init_currencies.sql")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CurrencyExchangeTest {
    @LocalServerPort
    private int webAppPort;

    @Test
    @DisplayName("/exchange/rates?currency=USD - should return exchange rates for given currency")
    public void test_exchange_rates() {
        CurrencyExchangeRateDto[] response = given()
                .port(webAppPort)
                .queryParam("currency", "USD")
                .when()
                .get("/exchange/rates")
                .then()
                .contentType(ContentType.JSON)
                .extract()
                .as(CurrencyExchangeRateDto[].class);

        assertEquals(2, response.length);

        CurrencyExchangeRateDto EUR = response[0];
        assertEquals(Currency.EUR, EUR.getCurrency());
        assertEquals(0.86, EUR.getSellingRate());
        assertEquals(0.87, EUR.getBuyingRate());

        CurrencyExchangeRateDto HUF = response[1];
        assertEquals(Currency.HUF, HUF.getCurrency());
        assertEquals(311, HUF.getSellingRate());
        assertEquals(312, HUF.getBuyingRate());
    }

    @Test
    @DisplayName("/exchange/rates?currency=USDT - should return 400 for not supported currency")
    public void test_exchange_rates_wrong_currency() {
        given()
                .port(webAppPort)
                .queryParam("currency", "USDT")
                .when()
                .get("/exchange/rates")
                .then()
                .statusCode(400);
    }

    @Test
    @DisplayName("/exchange - should return the exchanged amount")
    public void test_currency_exchange() {
        JsonPath response = given()
                .port(webAppPort)
                .contentType(ContentType.JSON)
                .body(this.getClass().getResourceAsStream("/json/exchange_request.json"))
                .when()
                .post("/exchange")
                .then()
                .contentType(ContentType.JSON)
                .extract()
                .response()
                .jsonPath();

        assertEquals("USD", response.get("currencyFrom"));
        assertEquals("HUF", response.get("currencyTo"));
        assertEquals(50.0f, (float) response.get("amountFrom"));
        assertEquals(15600.0f, (float) response.get("amountTo"));
        assertEquals("BUYING", response.get("rate"));
    }

    @Test
    @DisplayName("/exchange - should return 400 for negative amount")
    public void test_currency_exchange_wrong_amount() {
        given()
                .port(webAppPort)
                .contentType(ContentType.JSON)
                .body(this.getClass().getResourceAsStream("/json/negative_exchange_request.json"))
                .when()
                .post("/exchange")
                .then()
                .statusCode(400);
    }
}
