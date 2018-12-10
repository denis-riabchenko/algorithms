package com.riabchenko.algorithms;

import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

/**
 *
 */
public class DayOfWeek {
  @Test
  public void test() {
    assertThat(getDayOfWeek(1, 1, 1582)).isEqualTo("FRI");
    assertThat(getDayOfWeek(1, 1, 2018)).isEqualTo("MON");
    assertThat(getDayOfWeek(30, 4, 2018)).isEqualTo("MON");
    assertThat(getDayOfWeek(16, 4, 1984)).isEqualTo("MON");

    int min = 12;
    int max = 0;
    for (int y = 1582; y <= 3000; y++) {
      int fr13 = 0;
      for (int m = 1; m <= 12; m++) {
        if (getDayOfWeek(13, m, y).equals("FRI")) {
          fr13++;
        }
      }
      min = Math.min(min, fr13);
      max = Math.max(max, fr13);
    }
    System.out.println(min);
    System.out.println(max);
  }

  private static final String[] DAYS_OF_WEEK = new String[] {"FRI", "SAT", "SUN", "MON", "TUE", "WED", "THU"};
  private static final int[] DAYS_IN_MONTH = new int[] {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

  private String getDayOfWeek(int day, int month, int year) {
    DAYS_IN_MONTH[1] = isLeapYear(year) ? 29 : 28;

    int daysToDate = 0;

    int y = 1582;
    while (y < year) {
      if (isLeapYear(y)) {
        daysToDate += 366;
      } else {
        daysToDate += 365;
      }
      y++;
    }

    int m = 1;
    while (m < month) {
      daysToDate += DAYS_IN_MONTH[m - 1];
      m++;
    }

    daysToDate += day - 1;
    daysToDate %= 7;

    return DAYS_OF_WEEK[daysToDate];
  }

  private boolean isLeapYear(int year) {
    return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
  }
}
