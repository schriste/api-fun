package com.exposure101.apifun.dates.interval;

import org.joda.time.Days;
import org.joda.time.LocalDate;

public class DateIntervalDSL {

	protected Boolean after;
	protected Boolean before;
	protected LocalDate startDate;
	protected LocalDate endDate;
	
	protected DateIntervalDSL(LocalDate startDate) {
		this.startDate = startDate;
	}
	
	protected DateIntervalDSL(Interval interval) {
		this.startDate = interval.getLocalDate();
		this.before = interval.isBefore();
		this.after = !interval.isBefore();
	}
	
	public DateIntervalDSL and(LocalDate endDate) {
		this.endDate = endDate;
		return this;
	}

	public static BusinessDayDateIntervalDSL businessDaysBetween(LocalDate startDate) {
		return new BusinessDayDateIntervalDSL(startDate);
	}

	public static DateIntervalDSL daysBetween(LocalDate startDate) {
		return new DateIntervalDSL(startDate);
	}

	protected int daysBetween(LocalDate startDate, LocalDate endDate) {
		return Days.daysBetween(startDate, endDate).getDays();
	}
	
	protected LocalDate calculateDate(int numberOfDays) {
		if (this.after != null && this.after) {
			// simply add the number of days
			return this.startDate.plusDays(numberOfDays);
		} else if (this.before != null && this.before) {
			// simply subtract the number of days
			return this.startDate.minusDays(numberOfDays);
		} else {
			throw new IllegalStateException("What?");
		}
	}
 	
	public static LocalDate theDateAt(int numberOfDays, DateIntervalDSL dateIntervalDsl) {
		return dateIntervalDsl.calculateDate(numberOfDays);
	}
//	
//	public static LocalDate theDateAt(int numberOfDays, WeekdayDateIntervalDSL dateIntervalDsl) {
//		return dateIntervalDsl.calculateDate(numberOfDays);
//	}
//	
//	public static LocalDate theDateAt(int numberOfDays, BusinessDayDateIntervalDSL dateIntervalDsl) {
//		return dateIntervalDsl.calculateDate(numberOfDays);
//	}
	
	public static BusinessDayDateIntervalDSL businessDays(Interval interval) {
		return new BusinessDayDateIntervalDSL(interval);
	}
	
	public static DateIntervalDSL days(Interval interval) {
		return new DateIntervalDSL(interval);
	}
	
	public static Interval after(LocalDate localDate) {
		return new Interval(false, localDate);
	}
	
	public static Interval before(LocalDate localDate) {
		return new Interval(true, localDate);
	}
	
	public static int theNumberOf(DateIntervalDSL dateIntervalDsl) {
		final LocalDate startDate = dateIntervalDsl.startDate;
		final LocalDate endDate = dateIntervalDsl.endDate;
		if (startDate == null || endDate == null) {
			throw new IllegalStateException("Cannot compute date interval without both a start and end date.");
		}
		return dateIntervalDsl.daysBetween(startDate, endDate);
	}
	
	public static WeekdayDateIntervalDSL weekdays(Interval interval) {
		return new WeekdayDateIntervalDSL(interval);
	}

	public static DateIntervalDSL weekdaysBetween(LocalDate startDate) {
		return new WeekdayDateIntervalDSL(startDate);
	}
}
