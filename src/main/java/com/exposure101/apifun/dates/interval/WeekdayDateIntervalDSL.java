package com.exposure101.apifun.dates.interval;

import static org.joda.time.DateTimeConstants.SATURDAY;
import static org.joda.time.DateTimeConstants.SUNDAY;

import org.joda.time.LocalDate;

public class WeekdayDateIntervalDSL extends DateIntervalDSL {

	protected WeekdayDateIntervalDSL(LocalDate startDate) {
		super(startDate);
	}
	
	protected WeekdayDateIntervalDSL(Interval interval) {
		super(interval);
	}
	
	@Override
	protected LocalDate calculateDate(int numberOfDays) {
		LocalDate date = new LocalDate(this.startDate);
		final boolean after = this.after != null && this.after;
		final boolean before = this.before != null && this.before;
		if (!after && !before) {
			throw new IllegalStateException("Cannot calculate date " + numberOfDays + " from startDate without specifying after or before.");
		}
		int i = before ? -1 : 0;
		while (i < numberOfDays) {
			if (isWeekday(date)) {
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
		// iterate from beginning, only counting weekdays
		LocalDate iterator = new LocalDate(startDate);
		int days = 0;
		while (iterator.compareTo(endDate) < 0) {
			// if we're on a weekday, increment the days by 1, otherwise just continue iterating
			if (isWeekday(iterator)) {
				days += 1;
			}
			iterator = iterator.plusDays(1);
		}
		return days;
	}
	
	protected final boolean isWeekday(LocalDate date) {
		final int dayOfWeek = date.getDayOfWeek();
		return dayOfWeek != SATURDAY && dayOfWeek != SUNDAY;
	}
}
