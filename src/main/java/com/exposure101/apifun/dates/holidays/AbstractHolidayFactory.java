package com.exposure101.apifun.dates.holidays;

import static org.joda.time.DateTimeConstants.SATURDAY;
import static org.joda.time.DateTimeConstants.SUNDAY;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.joda.time.LocalDate;

public abstract class AbstractHolidayFactory implements HolidayFactory {

	private final Map<Integer, Set<LocalDate>> holidays;
	private final Map<Integer, Set<LocalDate>> businessHolidays;
	
	protected AbstractHolidayFactory() {
		holidays = new HashMap<Integer, Set<LocalDate>>();
		businessHolidays = new HashMap<Integer, Set<LocalDate>>();
	}
	
	@Override
	public Set<LocalDate> getHolidays(int year) {
		final Integer key = Integer.valueOf(year);
		if (!holidays.containsKey(key)) {
			holidays.put(key, initializeHolidays(year));
		}
		return holidays.get(key);
	}
	
	@Override
	public Set<LocalDate> getBusinessHolidays(int year) {
		final Integer key = Integer.valueOf(year);
		if (!businessHolidays.containsKey(key)) {
			businessHolidays.put(key, initializeBusinessHolidays(year));
		}
		return businessHolidays.get(key);
	}
	
	protected abstract Set<LocalDate> initializeHolidays(int year);
	
	protected Set<LocalDate> initializeBusinessHolidays(int year) {
		final Set<LocalDate> holidays = getHolidays(year);
		final Set<LocalDate> businessHolidays = new HashSet<LocalDate>(holidays.size(), 1);
		for (final LocalDate holiday : holidays) {
			LocalDate businessHoliday = new LocalDate(holiday);
			if (businessHoliday.getDayOfWeek() == SUNDAY) {
				businessHoliday = businessHoliday.plusDays(1);
			} else if (businessHoliday.getDayOfWeek() == SATURDAY) {
				businessHoliday = businessHoliday.minusDays(1);
			}
			businessHolidays.add(businessHoliday);
		}
		return businessHolidays;
	}

	@Override
	public boolean isHoliday(LocalDate date) {
		return isHoliday(date, getHolidays(date.getYear()));
	}
	
	@Override
	public boolean isBusinessHoliday(LocalDate date) {
		return isHoliday(date, getBusinessHolidays(date.getYear()));
	}
	
	protected boolean isHoliday(LocalDate date, Set<LocalDate> holidays) {
		for (final LocalDate holiday : holidays) {
			if (date.getDayOfYear() == holiday.getDayOfYear()) {
				return true;
			}
		}
		return false;
	}
}
