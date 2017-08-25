package com.exposure101.apifun.dates.holidays;

import java.util.Set;

import org.joda.time.LocalDate;

public interface HolidayFactory {
	
	Set<LocalDate> getHolidays(int year);
	
	Set<LocalDate> getBusinessHolidays(int year);
	
	boolean isHoliday(LocalDate date);
	
	boolean isBusinessHoliday(LocalDate date);
}
