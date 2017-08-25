package com.exposure101.apifun.dates.interval;

import org.joda.time.LocalDate;

class Interval {

	private final boolean before;
	private final LocalDate localDate;
	
	public Interval(boolean before, LocalDate localDate) {
		this.before = before;
		this.localDate = localDate;
	}
	
	public boolean isBefore() {
		return before;
	}
	
	public LocalDate getLocalDate() {
		return localDate;
	}
}
