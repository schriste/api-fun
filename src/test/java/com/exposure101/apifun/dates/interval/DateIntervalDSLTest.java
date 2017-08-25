package com.exposure101.apifun.dates.interval;

import static com.exposure101.apifun.dates.DateDSL.theDateFor;
import static com.exposure101.apifun.dates.DateDSL.theEighteenth;
import static com.exposure101.apifun.dates.DateDSL.theNineteenth;
import static com.exposure101.apifun.dates.DateDSL.theTenth;
import static com.exposure101.apifun.dates.DateDSL.theTwentieth;
import static com.exposure101.apifun.dates.Month.FEBRUARY;
import static com.exposure101.apifun.dates.Month.JANUARY;
import static com.exposure101.apifun.dates.interval.DateIntervalDSL.after;
import static com.exposure101.apifun.dates.interval.DateIntervalDSL.before;
import static com.exposure101.apifun.dates.interval.DateIntervalDSL.days;
import static com.exposure101.apifun.dates.interval.DateIntervalDSL.daysBetween;
import static com.exposure101.apifun.dates.interval.DateIntervalDSL.theDateAt;
import static com.exposure101.apifun.dates.interval.DateIntervalDSL.theNumberOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.joda.time.LocalDate;
import org.junit.Test;

public class DateIntervalDSLTest {

	@Test
	public void testDaysBetween2017() {
		final LocalDate january10 = theDateFor(theTenth().of(JANUARY).in(2017));
		final LocalDate january20 = theDateFor(theTwentieth().of(JANUARY).in(2017));
		assertThat(theNumberOf(daysBetween(january10).and(january20)), is(equalTo(10)));
		
		final LocalDate february18 = theDateFor(theEighteenth().of(FEBRUARY).in(2017));
		final LocalDate february19 = theDateFor(theNineteenth().of(FEBRUARY).in(2017));
		assertThat(theNumberOf(daysBetween(february19).and(february18)), is(equalTo(-1)));
		assertThat(theNumberOf(daysBetween(february18).and(february18)), is(equalTo(0)));
		assertThat(theNumberOf(daysBetween(february18).and(february19)), is(equalTo(1)));
	}
	
	@Test
	public void testDaysAfter2017() {
		final LocalDate january10 = theDateFor(theTenth().of(JANUARY, 2017));
		final LocalDate january10Plus14Days = theDateAt(14, days(after(january10)));
		assertThat(january10Plus14Days.getDayOfMonth(), is(equalTo(24)));
	}
	
	@Test
	public void testDaysBefore2017() {
		final LocalDate january20 = theDateFor(theTwentieth().of(JANUARY, 2017));
		final LocalDate january20Minus10Days = theDateAt(10, days(before(january20)));
		assertThat(january20Minus10Days.getDayOfMonth(), is(equalTo(10)));
	}
}
