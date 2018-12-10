package com.riabchenko.algorithms;

import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

/**
 *
 */
public class CurrencyExchangeTest {
  @Test
  public void test() {
    CurrencyExchange exchange = new CurrencyExchange(new String[][]{
        {"UAH", "USD"},
        {"RUR", "USD"},
        {"TUG", "UAH"},
        {"TUG", "RUR"}},
        new double[]{
            1.0 / 29,
            1.0 / 70,
            1.0 / 100,
            1.0 / 50
        });
    assertThat(exchange.findRate("UAH", "RUR")).isWithin(0.00001).of(0);
    assertThat(exchange.findRate("UAH", "USD")).isWithin(0.00001).of(1.0 / 29);
    assertThat(exchange.findRate("RUR", "USD")).isWithin(0.00001).of(1.0 / 70);
  }

  @Test
  public void test2() {
    CurrencyExchange exchange = new CurrencyExchange(new String[][]{
        {"UAH", "USD"},
        {"RUR", "USD"},
        {"TUG", "UAH"},
        {"TUG", "RUR"}},
        new double[]{
            1.0 / 29,
            1.0 / 70,
            1.0 / 100,
            1.0 / 50
        });
    assertThat(exchange.findRate("TUG", "USD")).isWithin(0.00001).of(1.0 / (100 * 29));
  }

  @Test
  public void test3() {
    CurrencyExchange exchange = new CurrencyExchange(new String[][]{
        {"UAH", "USD"},
        {"RUR", "USD"},
        {"TUG", "UAH"},
        {"TUG", "RUR"}},
        new double[]{
            1.0 / 29,
            1.0 / 70,
            1.0 / 150,
            1.0 / 50
        });
    assertThat(exchange.findRate("TUG", "USD")).isWithin(0.00001).of(1.0 / (50 * 70));
  }
}
