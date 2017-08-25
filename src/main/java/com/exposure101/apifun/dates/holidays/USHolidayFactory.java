package com.exposure101.apifun.dates.holidays;

import static com.exposure101.apifun.dates.DateDSL.theDateFor;
import static com.exposure101.apifun.dates.DateDSL.theEleventh;
import static com.exposure101.apifun.dates.DateDSL.theFirst;
import static com.exposure101.apifun.dates.DateDSL.theFourth;
import static com.exposure101.apifun.dates.DateDSL.theLast;
import static com.exposure101.apifun.dates.DateDSL.theSecond;
import static com.exposure101.apifun.dates.DateDSL.theThird;
import static com.exposure101.apifun.dates.DateDSL.theTwentyFifth;
import static com.exposure101.apifun.dates.DayOfWeek.MONDAY;
import static com.exposure101.apifun.dates.DayOfWeek.THURSDAY;
import static com.exposure101.apifun.dates.Month.DECEMBER;
import static com.exposure101.apifun.dates.Month.FEBRUARY;
import static com.exposure101.apifun.dates.Month.JANUARY;
import static com.exposure101.apifun.dates.Month.JULY;
import static com.exposure101.apifun.dates.Month.MAY;
import static com.exposure101.apifun.dates.Month.NOVEMBER;
import static com.exposure101.apifun.dates.Month.OCTOBER;
import static com.exposure101.apifun.dates.Month.SEPTEMBER;

import java.util.HashSet;
import java.util.Set;

import org.joda.time.LocalDate;

public class USHolidayFactory extends AbstractHolidayFactory {

	public static final HolidayFactory US_HOLIDAYS = new USHolidayFactory();
	
	private USHolidayFactory() {
	}
	
	@Override
	public Set<LocalDate> initializeHolidays(int year) {
		final Set<LocalDate> holidays = new HashSet<LocalDate>(10, 1);
		
		// new years - jan 1
		holidays.add(theDateFor(theFirst().of(JANUARY).in(year)));
		
		// martin luther king day - third monday in january
		holidays.add(theDateFor(theThird(MONDAY).of(JANUARY).in(year)));
		
		// washington's bday - third monday in february
		holidays.add(theDateFor(theThird(MONDAY).of(FEBRUARY).in(year)));
		
		// memorial day - last monday in may
		holidays.add(theDateFor(theLast(MONDAY).of(MAY).in(year)));
		
		// independence day - july 4
		holidays.add(theDateFor(theFourth().of(JULY).in(year)));
		
		// labor day - first monday in september
		holidays.add(theDateFor(theFirst(MONDAY).of(SEPTEMBER).in(year)));
		
		// columbus day - second monday in october
		holidays.add(theDateFor(theSecond(MONDAY).of(OCTOBER).in(year)));
		
		// veterans day - november 11
		holidays.add(theDateFor(theEleventh().of(NOVEMBER).in(year)));
		
		// thanksgiving - fourth thursday in november
		holidays.add(theDateFor(theFourth(THURSDAY).of(NOVEMBER).in(year)));
		
		// christmas - december 25
		holidays.add(theDateFor(theTwentyFifth().of(DECEMBER).in(year)));
		return holidays;
	}
}
