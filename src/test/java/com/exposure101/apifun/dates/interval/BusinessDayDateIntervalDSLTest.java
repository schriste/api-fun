package com.exposure101.apifun.dates.interval;

import static com.exposure101.apifun.dates.DateDSL.theDateFor;
import static com.exposure101.apifun.dates.DateDSL.theEighth;
import static com.exposure101.apifun.dates.DateDSL.theEleventh;
import static com.exposure101.apifun.dates.DateDSL.theFirst;
import static com.exposure101.apifun.dates.DateDSL.theTenth;
import static com.exposure101.apifun.dates.DateDSL.theThirtyFirst;
import static com.exposure101.apifun.dates.DateDSL.theTwentieth;
import static com.exposure101.apifun.dates.DateDSL.theTwentySecond;
import static com.exposure101.apifun.dates.DateDSL.theTwentySixth;
import static com.exposure101.apifun.dates.Month.FEBRUARY;
import static com.exposure101.apifun.dates.Month.JANUARY;
import static com.exposure101.apifun.dates.interval.DateIntervalDSL.after;
import static com.exposure101.apifun.dates.interval.DateIntervalDSL.before;
import static com.exposure101.apifun.dates.interval.DateIntervalDSL.businessDays;
import static com.exposure101.apifun.dates.interval.DateIntervalDSL.businessDaysBetween;
import static com.exposure101.apifun.dates.interval.DateIntervalDSL.theDateAt;
import static com.exposure101.apifun.dates.interval.DateIntervalDSL.theNumberOf;
import static com.exposure101.apifun.dates.interval.DateIntervalDSL.weekdays;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.junit.Test;

public class BusinessDayDateIntervalDSLTest {

	@Test
	public void testBusinessDaysBetweenDates2017() {
		// start on saturday end 2 weeks later with holiday on second monday - should be 9 days
		final LocalDate february11 = theDateFor(theEleventh().of(FEBRUARY, 2017));
		final LocalDate february26 = theDateFor(theTwentySixth().of(FEBRUARY, 2017));
		assertThat(theNumberOf(businessDaysBetween(february11).and(february26)), is(equalTo(9)));
		
		// start on a wednesday and end on a wednesday with no holidays - should be 5 days
		final LocalDate february1 = theDateFor(theFirst().of(FEBRUARY, 2017));
		final LocalDate february8 = theDateFor(theEighth().of(FEBRUARY, 2017));
		assertThat(theNumberOf(businessDaysBetween(february1).and(february8)), is(equalTo(5)));
		
		// cross 2 holidays in one stretch - jan 1 to jan 22 should be 13 business days
		final LocalDate january1 = theDateFor(theFirst().of(JANUARY, 2017));
		final LocalDate january22 = theDateFor(theTwentySecond().of(JANUARY, 2017));
		assertThat(theNumberOf(businessDaysBetween(january1).and(january22)), is(equalTo(13)));
		
		// TODO: schriste
		/*
		 * change the dsl to the following
		 * 
		 * theNumberOf(businessDays(..between
		 * theNumberOf(businessDays(..after, before, etc.
		 * 
		 * 
		 * theDateAt(14, days(before(...
		 * theDateAt(14, weekdays(before(...
		 * 
		 * theDateAt(14, days(before(
		 * theDateAt(14, weekdays(before(DATE
		 * theDateAt(14, businessDays(before(DATE))))
		 */
	}
	
	@Test
	public void testDaysAfter2017() {
		final LocalDate january10 = theDateFor(theTenth().of(JANUARY, 2017));
		final LocalDate january10Plus5BusinessDays = theDateAt(5, businessDays(after(january10)));
		assertThat(january10Plus5BusinessDays.getDayOfMonth(), is(equalTo(18)));
//		
		final LocalDate january20 = theDateFor(theTwentieth().of(JANUARY, 2017));
		final LocalDate january20Plus15BusinessDays = theDateAt(15, businessDays(after(january20)));
		assertThat(january20Plus15BusinessDays.getMonthOfYear(), is(equalTo(DateTimeConstants.FEBRUARY)));
		assertThat(january20Plus15BusinessDays.getDayOfMonth(), is(equalTo(10)));
	}
	
	@Test
	public void testDaysBefore2017() {
		final LocalDate january20 = theDateFor(theTwentieth().of(JANUARY, 2017));
		final LocalDate january20Minus5BusinessDays = theDateAt(5, businessDays(before(january20)));
		assertThat(january20Minus5BusinessDays.getDayOfMonth(), is(equalTo(12)));
		
		final LocalDate january31 = theDateFor(theThirtyFirst().of(JANUARY, 2017));
		final LocalDate january31Minus10Weekdays = theDateAt(10, weekdays(before(january31)));
		assertThat(january31Minus10Weekdays.getDayOfMonth(), is(equalTo(17)));
	}
}
