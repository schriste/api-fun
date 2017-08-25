package com.exposure101.apifun.dates;

import static com.exposure101.apifun.dates.NthWeek.FIRST;
import static com.exposure101.apifun.dates.NthWeek.FOURTH;
import static com.exposure101.apifun.dates.NthWeek.LAST;
import static com.exposure101.apifun.dates.NthWeek.SECOND;
import static com.exposure101.apifun.dates.NthWeek.THIRD;

import java.util.Calendar;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

public final class DateDSL {

	private Integer day;
	private DayOfWeek dayOfWeek;
	private Month month;
	private NthWeek nthWeek;
	private Integer year;

	private DateDSL(int day) {
		this.day = Integer.valueOf(day);
	}

	private DateDSL(DayOfWeek dayOfWeek, NthWeek nthWeek) {
		this.dayOfWeek = dayOfWeek;
		this.nthWeek = nthWeek;
	}
	
	Integer getDay() {
		return day;
	}

	DayOfWeek getDayOfWeek() {
		return dayOfWeek;
	}

	Month getMonth() {
		return month;
	}

	NthWeek getNthWeek() {
		return nthWeek;
	}

	Integer getYear() {
		return year;
	}

	public DateDSL in(int year) {
		this.year = Integer.valueOf(year);
		return this;
	}
	
	public static LocalDate now() {
		return LocalDateTime.now().toLocalDate();
	}

	public DateDSL of(Month month) {
		this.month = month;
		return this;
	}
	
	public DateDSL of(Month month, int year) {
		this.month = month;
		this.year = Integer.valueOf(year);
		return this;
	}
	
	private static int theCurrentMonth() { 
		return LocalDateTime.now().getMonthOfYear();
	}

	public static LocalDate theDateFor(DateDSL dateDsl) {
		final int month = dateDsl.month != null ? dateDsl.month.getJodaConstant() : theCurrentMonth();
		final int year = dateDsl.year != null ? dateDsl.year.intValue() : thisYear();
	
		// check that the date setup is valid
		final DayOfWeek dayOfWeek = dateDsl.dayOfWeek;
		final NthWeek nthWeek = dateDsl.nthWeek;
		if (dayOfWeek == null && nthWeek == null && dateDsl.day != null) {
			return new LocalDate(year, month, dateDsl.day.intValue());
		} else if ((dayOfWeek == null && nthWeek != null) || (dayOfWeek != null && nthWeek == null)
				|| (dayOfWeek == null && nthWeek == null && dateDsl.day == null)) {
			throw new IllegalStateException("Something is broken internally: "
					+ "created a DateDSL without specifying either both dayOfWeek and nthWeek or the day. "
					+ "This should not be possible.");
		}
	
		/*
		 * begin to build the date
		 */
		// set it to the first of the month
		LocalDate localDate = new LocalDate(year, month, 1);
	
		// determine which week we're working with - last is handled differently
		if (nthWeek == LAST) {
			// to get the last week in a month, we want to go to the first week of the next month and come back 1 week
			localDate = localDate.plusMonths(1).withDayOfWeek(dayOfWeek.getJodaConstant());
			/*
			 * there's a chance that the next month actually starts on a day later in the week than the day we're trying
			 * to set it to.
			 * for example, if the month starts on a thursday and we set it to a wednesday, that wednesday will be the 
			 * last wednesday of the month
			 * therefore we only need to subtract a week from the current date if we're in the next month 
			 */
			if (localDate.getMonthOfYear() != month) {
				localDate = localDate.minusWeeks(1);
			}
		} else {
			// set the local date to the day of the week
			localDate = localDate.withDayOfWeek(dayOfWeek.getJodaConstant());
			/*
			 * there's a chance that the current month starts on a day later in the week than the one day we're looking 
			 * for 
			 * for example, if the first of the month is on a thursday and we set it to wednesday, the first wednesday 
			 * will be the next week
			 * before doing anything else make sure we're in the right month once we set the week day
			 */
			if (localDate.getMonthOfYear() != month) {
				// increment by one week to get us at the start of the month
				localDate = localDate.plusWeeks(1);
			}
			// calculate and add the remaining weeks
			final int n;
			if (nthWeek == FIRST) {
				n = 1;
			} else if (nthWeek == SECOND) {
				n = 2;
			} else if (nthWeek == THIRD) {
				n = 3;
			} else if (nthWeek == FOURTH) {
				n = 4;
			} else {
				throw new UnsupportedOperationException("NthWeek " + nthWeek.name() + " is not supported");
			}
			localDate = localDate.plusWeeks(n - 1);
		}
		
		return localDate;
	}

	private static DateDSL theDay(int day) {
		return new DateDSL(day);
	}

	public static DateDSL theEighteenth() {
		return theDay(18);
	}
	
	public static DateDSL theEighth() {
		return theDay(8);
	}
	
	public static DateDSL theEleventh() {
		return theDay(11);
	}
	
	public static DateDSL theFifteenth() {
		return theDay(15);
	}

	public static DateDSL theFifth() {
		return theDay(5);
	}

	public static DateDSL theFirst() {
		return theDay(1);
	}

	public static DateDSL theFirst(DayOfWeek dayOfWeek) {
		return theNth(dayOfWeek, FIRST);
	}

	public static DateDSL theFourteenth() {
		return theDay(14);
	}

	public static DateDSL theFourth() {
		return theDay(4);
	}

	public static DateDSL theFourth(DayOfWeek dayOfWeek) {
		return theNth(dayOfWeek, FOURTH);
	}

	public static DateDSL theLast(DayOfWeek dayOfWeek) {
		return theNth(dayOfWeek, LAST);
	}

	public static DateDSL theNineteenth() {
		return theDay(19);
	}

	public static DateDSL theNinth() {
		return theDay(9);
	}

	private static DateDSL theNth(DayOfWeek dayOfWeek, NthWeek nthWeek) {
		return new DateDSL(dayOfWeek, nthWeek);
	}

	public static DateDSL theSecond() {
		return theDay(2);
	}

	public static DateDSL theSecond(DayOfWeek dayOfWeek) {
		return theNth(dayOfWeek, SECOND);
	}

	public static DateDSL theSeventeenth() {
		return theDay(17);
	}
	
	public static DateDSL theSeventh() {
		return theDay(7);
	}

	public static DateDSL theSixteenth() {
		return theDay(16);
	}
	
	public static DateDSL theSixth() {
		return theDay(6);
	}

	public static DateDSL theTenth() {
		return theDay(10);
	}

	public static DateDSL theThird() {
		return theDay(3);
	}

	public static DateDSL theThird(DayOfWeek dayOfWeek) {
		return theNth(dayOfWeek, THIRD);
	}

	public static DateDSL theThirteenth() {
		return theDay(13);
	}

	public static DateDSL theThirtieth() {
		return theDay(30);
	}

	public static DateDSL theThirtyFirst() {
		return theDay(31);
	}

	public static DateDSL theTwelfth() {
		return theDay(12);
	}
	
	public static DateDSL theTwentieth() {
		return theDay(20);
	}

	public static DateDSL theTwentyEigth() {
		return theDay(28);
	}
	
	public static DateDSL theTwentyFifth() {
		return theDay(25);
	}
	
	public static DateDSL theTwentyFirst() {
		return theDay(21);
	}

	public static DateDSL theTwentyFourth() {
		return theDay(24);
	}

	public static DateDSL theTwentyNinth() {
		return theDay(29);
	}

	public static DateDSL theTwentySecond() {
		return theDay(22);
	}
	
	public static DateDSL theTwentySeventh() {
		return theDay(27);
	}

	public static DateDSL theTwentySixth() {
		return theDay(26);
	}

	public static DateDSL theTwentyThird() {
		return theDay(23);
	}

	public static Month thisMonth() {
		return Month.getMonthFor(theCurrentMonth());
	}

	public static int thisYear() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}

	public static LocalDate whenIs(DateDSL dateDsl) {
		return theDateFor(dateDsl);
	}
}
