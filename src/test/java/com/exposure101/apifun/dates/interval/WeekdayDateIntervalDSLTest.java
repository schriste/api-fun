package com.exposure101.apifun.dates.interval;

import static com.exposure101.apifun.dates.DateDSL.theDateFor;
import static com.exposure101.apifun.dates.DateDSL.theEleventh;
import static com.exposure101.apifun.dates.DateDSL.theTenth;
import static com.exposure101.apifun.dates.DateDSL.theThirtyFirst;
import static com.exposure101.apifun.dates.DateDSL.theTwentieth;
import static com.exposure101.apifun.dates.DateDSL.theTwentySixth;
import static com.exposure101.apifun.dates.Month.FEBRUARY;
import static com.exposure101.apifun.dates.Month.JANUARY;
import static com.exposure101.apifun.dates.interval.DateIntervalDSL.after;
import static com.exposure101.apifun.dates.interval.DateIntervalDSL.before;
import static com.exposure101.apifun.dates.interval.DateIntervalDSL.theDateAt;
import static com.exposure101.apifun.dates.interval.DateIntervalDSL.theNumberOf;
import static com.exposure101.apifun.dates.interval.DateIntervalDSL.weekdays;
import static com.exposure101.apifun.dates.interval.DateIntervalDSL.weekdaysBetween;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.junit.Test;

public class WeekdayDateIntervalDSLTest {

	@Test
	public void testWeekdaysBetween2017() {
		final LocalDate january10 = theDateFor(theTenth().of(JANUARY).in(2017));
		final LocalDate january20 = theDateFor(theTwentieth().of(JANUARY).in(2017));
		assertThat(theNumberOf(weekdaysBetween(january10).and(january20)), is(equalTo(8)));
		
		// start on a saturday, end on a sunday
		final LocalDate february11 = theDateFor(theEleventh().of(FEBRUARY).in(2017));
		final LocalDate february26 = theDateFor(theTwentySixth().of(FEBRUARY).in(2017));
		assertThat(theNumberOf(weekdaysBetween(february11).and(february26)), is(equalTo(10)));
	}
	
	@Test
	public void testDaysAfter2017() {
		final LocalDate january10 = theDateFor(theTenth().of(JANUARY, 2017));
		final LocalDate january10Plus5Weekdays = theDateAt(5, weekdays(after(january10)));
		assertThat(january10Plus5Weekdays.getDayOfMonth(), is(equalTo(17)));
		
		final LocalDate january20 = theDateFor(theTwentieth().of(JANUARY, 2017));
		final LocalDate january20Plus15Weekdays = theDateAt(15, weekdays(after(january20)));
		assertThat(january20Plus15Weekdays.getMonthOfYear(), is(equalTo(DateTimeConstants.FEBRUARY)));
		assertThat(january20Plus15Weekdays.getDayOfMonth(), is(equalTo(10)));
	}
	
	@Test
	public void testDaysBefore2017() {
		final LocalDate january20 = theDateFor(theTwentieth().of(JANUARY, 2017));
		final LocalDate january20Minus5Weekdays = theDateAt(5, weekdays(before(january20)));
		assertThat(january20Minus5Weekdays.getDayOfMonth(), is(equalTo(13)));
		
		final LocalDate january31 = theDateFor(theThirtyFirst().of(JANUARY, 2017));
		final LocalDate january31Minus10Weekdays = theDateAt(10, weekdays(before(january31)));
		assertThat(january31Minus10Weekdays.getDayOfMonth(), is(equalTo(17)));
	}
}
