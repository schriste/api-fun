package com.exposure101.apifun.dates;

import org.joda.time.DateTimeConstants;

public enum DayOfWeek {

	MONDAY(DateTimeConstants.MONDAY),
	TUESDAY(DateTimeConstants.TUESDAY),
	WEDNESDAY(DateTimeConstants.WEDNESDAY),
	THURSDAY(DateTimeConstants.THURSDAY),
	FRIDAY(DateTimeConstants.FRIDAY),
	SATURDAY(DateTimeConstants.SATURDAY),
	SUNDAY(DateTimeConstants.SUNDAY);
	
	private final int jodaConstant;
	
	private DayOfWeek(int jodaConstant) {
		this.jodaConstant = jodaConstant;
	}
	
	public int getJodaConstant() {
		return jodaConstant;
	}
}
