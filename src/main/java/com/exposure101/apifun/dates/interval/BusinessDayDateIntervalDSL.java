package com.exposure101.apifun.dates.interval;

import static com.exposure101.apifun.dates.holidays.USHolidayFactory.US_HOLIDAYS;

import org.joda.time.LocalDate;

import com.exposure101.apifun.dates.holidays.HolidayFactory;


public class BusinessDayDateIntervalDSL extends WeekdayDateIntervalDSL {
	
	private HolidayFactory holidays;

	protected BusinessDayDateIntervalDSL(LocalDate startDate) {
		super(startDate);
		holidays = US_HOLIDAYS; // defaults to US Holidays
	}
	
	protected BusinessDayDateIntervalDSL(Interval interval) {
		super(interval);
		holidays = US_HOLIDAYS; // TODO: clean this up
	}
	
	@Override
	public BusinessDayDateIntervalDSL and(LocalDate endDate) {
		return (BusinessDayDateIntervalDSL) super.and(endDate);
	}
	
	@Override
	protected LocalDate calculateDate(int numberOfDays) {
		LocalDate date = new LocalDate(this.startDate);
		final boolean after = this.after != null && this.after;
		final boolean before = this.before != null && this.before;
		if (!after && !before) {
			throw new IllegalStateException("Cannot calculate date " + numberOfDays + " from startDate without specifying after or before.");
		}
		
		final HolidayFactory holidayFactory = getHolidaysFactory();
		int i = before ? -1 : 0;
		while (i < numberOfDays) {
			if (super.isWeekday(date) && !holidayFactory.isBusinessHoliday(date)) {
				i += 1;
			}
			if (after) {
				date = date.plusDays(1);
			} else if (before && i != numberOfDays) {
				date = date.minusDays(1);
			}
		}
		return date;
	}
	
	@Override
	protected int daysBetween(LocalDate startDate, LocalDate endDate) {
		final HolidayFactory holidayFactory = getHolidaysFactory();
		int days = 0;
		LocalDate iterator = new LocalDate(startDate);
		
		while (iterator.compareTo(endDate) < 0) {
			// first check that we're on a weekday - if we're not iterate to the next value
			if (super.isWeekday(iterator)) {
				// next check that we're not on a holiday - if we are just iterate to the next day
				if (!holidayFactory.isBusinessHoliday(iterator)) {
					days += 1;
				}
			}
			iterator = iterator.plusDays(1);
		}
		return days;
	}
	
	protected HolidayFactory getHolidaysFactory() {
		return holidays;
	}

	public BusinessDayDateIntervalDSL usingHolidays(HolidayFactory holidays) {
		this.holidays = holidays;
		return this;
	}
}
