package com.exposure101.apifun.dates;

import static com.exposure101.apifun.dates.DateDSL.theDateFor;
import static com.exposure101.apifun.dates.DateDSL.theEighteenth;
import static com.exposure101.apifun.dates.DateDSL.theEighth;
import static com.exposure101.apifun.dates.DateDSL.theEleventh;
import static com.exposure101.apifun.dates.DateDSL.theFifteenth;
import static com.exposure101.apifun.dates.DateDSL.theFifth;
import static com.exposure101.apifun.dates.DateDSL.theFirst;
import static com.exposure101.apifun.dates.DateDSL.theFourteenth;
import static com.exposure101.apifun.dates.DateDSL.theFourth;
import static com.exposure101.apifun.dates.DateDSL.theLast;
import static com.exposure101.apifun.dates.DateDSL.theNineteenth;
import static com.exposure101.apifun.dates.DateDSL.theNinth;
import static com.exposure101.apifun.dates.DateDSL.theSecond;
import static com.exposure101.apifun.dates.DateDSL.theSeventeenth;
import static com.exposure101.apifun.dates.DateDSL.theSeventh;
import static com.exposure101.apifun.dates.DateDSL.theSixteenth;
import static com.exposure101.apifun.dates.DateDSL.theSixth;
import static com.exposure101.apifun.dates.DateDSL.theTenth;
import static com.exposure101.apifun.dates.DateDSL.theThird;
import static com.exposure101.apifun.dates.DateDSL.theThirteenth;
import static com.exposure101.apifun.dates.DateDSL.theTwelfth;
import static com.exposure101.apifun.dates.DayOfWeek.FRIDAY;
import static com.exposure101.apifun.dates.DayOfWeek.MONDAY;
import static com.exposure101.apifun.dates.DayOfWeek.SATURDAY;
import static com.exposure101.apifun.dates.DayOfWeek.SUNDAY;
import static com.exposure101.apifun.dates.DayOfWeek.THURSDAY;
import static com.exposure101.apifun.dates.DayOfWeek.WEDNESDAY;
import static com.exposure101.apifun.dates.Month.APRIL;
import static com.exposure101.apifun.dates.Month.AUGUST;
import static com.exposure101.apifun.dates.Month.JANUARY;
import static com.exposure101.apifun.dates.Month.JULY;
import static com.exposure101.apifun.dates.Month.JUNE;
import static com.exposure101.apifun.dates.Month.MAY;
import static com.exposure101.apifun.dates.Month.NOVEMBER;
import static com.exposure101.apifun.dates.Month.SEPTEMBER;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class DateDSLTest {

	@Before
	public void setUp() {
		// we don't actually need to instantiate our cut - it's all static methods
	}
	
	@Test
	public void test2016Dates() {
		LocalDate january1 = theDateFor(theFirst(FRIDAY).of(JANUARY).in(2016));
		testDay(january1, 1, DateTimeConstants.JANUARY, 2016);
		
		LocalDate january31 = theDateFor(theLast(SUNDAY).of(JANUARY).in(2016));
		testDay(january31, 31, DateTimeConstants.JANUARY, 2016);
		
		LocalDate august18 = theDateFor(theThird(THURSDAY).of(AUGUST).in(2016));
		testDay(august18, 18, DateTimeConstants.AUGUST, 2016);
		
		LocalDate september4 = theDateFor(theFirst(SUNDAY).of(SEPTEMBER).in(2016));
		testDay(september4, 4, DateTimeConstants.SEPTEMBER, 2016);
		
		LocalDate november23 = theDateFor(theFourth(WEDNESDAY).of(NOVEMBER).in(2016));
		testDay(november23, 23, DateTimeConstants.NOVEMBER, 2016);
		
		LocalDate november24 = theDateFor(theLast(THURSDAY).of(NOVEMBER).in(2016));
		testDay(november24, 24, DateTimeConstants.NOVEMBER, 2016);
	}
	
	@Test
	public void	test2017Dates() {
		LocalDate may1 = theDateFor(theFirst(MONDAY).of(MAY).in(2017));
		testDay(may1, 1, DateTimeConstants.MAY, 2017);
		
		LocalDate june16 = theDateFor(theThird(FRIDAY).of(JUNE).in(2017));
		testDay(june16, 16, DateTimeConstants.JUNE, 2017);
		
		LocalDate june23 = theDateFor(theFourth(FRIDAY).of(JUNE).in(2017));
		testDay(june23, 23, DateTimeConstants.JUNE, 2017);
		
		LocalDate june30 = theDateFor(theLast(FRIDAY).of(JUNE).in(2017));
		testDay(june30, 30, DateTimeConstants.JUNE, 2017);
		
		LocalDate july8 = theDateFor(theSecond(SATURDAY).of(JULY).in(2017));
		testDay(july8, 8, DateTimeConstants.JULY, 2017);
		
		LocalDate august31 = theDateFor(theLast(THURSDAY).of(AUGUST).in(2017));
		testDay(august31, 31, DateTimeConstants.AUGUST, 2017);
	}
	
	@Test
	public void test2018Dates() {
		LocalDate april1 = theDateFor(theFirst(SUNDAY).of(APRIL).in(2018));
		testDay(april1, 1, DateTimeConstants.APRIL, 2018);
		
		LocalDate may7 = theDateFor(theFirst(MONDAY).of(MAY).in(2018));
		testDay(may7, 7, DateTimeConstants.MAY, 2018);
		
		LocalDate june27 = theDateFor(theFourth(WEDNESDAY).of(JUNE).in(2018));
		testDay(june27, 27, DateTimeConstants.JUNE, 2018);
	}
	
	@Ignore
	@Test
	public void testThisMonth() {
		// TODO: schriste - how to test this????
	}

	@Test
	public void testFirst() {
		testDay(theFirst(), 1);
	}

	@Test
	public void testSecond() {
		testDay(theSecond(), 2);
	}

	@Test
	public void testThird() {
		testDay(theThird(), 3);
	}

	@Test
	public void testFourth() {
		testDay(theFourth(), 4);
	}
	
	@Test
	public void testFifth() { 
		testDay(theFifth(), 5);
	}
	
	@Test
	public void testSixth() {
		testDay(theSixth(), 6);
	}
	
	@Test
	public void testSeventh() { 
		testDay(theSeventh(), 7);
	}
	
	@Test
	public void testEighth() {
		testDay(theEighth(), 8);
	}
	
	@Test
	public void testNinth() { 
		testDay(theNinth(), 9);
	}
	
	@Test
	public void testTenth() { 
		testDay(theTenth(), 10);
	}
	
	@Test
	public void testEleventh() {
		testDay(theEleventh(), 11);
	}
	
	@Test
	public void testTwelfth() {
		testDay(theTwelfth(), 12);
	}
	
	@Test
	public void testThirteenth() {
		testDay(theThirteenth(), 13);
	}
	
	@Test
	public void testFourteenth() {
		testDay(theFourteenth(), 14);
	}
	
	@Test
	public void testFifteenth() {
		testDay(theFifteenth(), 15);
	}
	
	@Test
	public void testSixteenth() {
		testDay(theSixteenth(), 16);
	}
	
	@Test
	public void testSeventeenth() {
		testDay(theSeventeenth(), 17);
	}
	
	@Test
	public void testEighteenth() {
		testDay(theEighteenth(), 18);
	}
	
	@Test
	public void testNineteenth() {
		testDay(theNineteenth(), 19);
	}
	
	@Test
	public void testTwentieth() {
		testDay(DateDSL.theTwentieth(), 20);
	}
	
	@Test
	public void testTwentyFirst() {
		testDay(DateDSL.theTwentyFirst(), 21);
	}
	
	@Test
	public void testTwentySecond() {
		testDay(DateDSL.theTwentySecond(), 22);
	}
	
	@Test
	public void testTwentyThird() {
		testDay(DateDSL.theTwentyThird(), 23);
	}
	
	@Test
	public void testTwentyFourth() {
		testDay(DateDSL.theTwentyFourth(), 24);
	}
	
	@Test
	public void testTwentyFifth() {
		testDay(DateDSL.theTwentyFifth(), 25);
	}
	
	@Test
	public void testTwentySixth() {
		testDay(DateDSL.theTwentySixth(), 26);
	}
	
	@Test
	public void testTwentySeventh() {
		testDay(DateDSL.theTwentySeventh(), 27);
	}
	
	@Test
	public void testTwentyEighth() {
		testDay(DateDSL.theTwentyEigth(), 28);
	}
	
	@Test
	public void testTwentyNinth() {
		testDay(DateDSL.theTwentyNinth(), 29);
	}
	
	@Test
	public void testThirtieth() {
		testDay(DateDSL.theThirtieth(), 30);
	}
	
	@Test
	public void testThirtyFirst() {
		testDay(DateDSL.theThirtyFirst(), 31);
	}

	private void testDay(DateDSL cut, int expected) {
		assertThat(cut.getDay(), is(equalTo(expected)));
		assertThat(cut.getDayOfWeek(), is(nullValue()));
		assertThat(cut.getNthWeek(), is(nullValue()));
	}

	private void testDay(LocalDate date, int expectedDay, int expectedMonth, int expectedYear) {
		assertThat(date.getDayOfMonth(), is(equalTo(expectedDay)));
		assertThat(date.getMonthOfYear(), is(equalTo(expectedMonth)));
		assertThat(date.getYear(), is(equalTo(expectedYear)));
	}
}
