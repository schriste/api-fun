package com.exposure101.apifun.dates;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTimeConstants;

public enum Month {

	JANUARY(DateTimeConstants.JANUARY),
	FEBRUARY(DateTimeConstants.FEBRUARY),
	MARCH(DateTimeConstants.MARCH),
	APRIL(DateTimeConstants.APRIL),
	MAY(DateTimeConstants.MAY),
	JUNE(DateTimeConstants.JUNE),
	JULY(DateTimeConstants.JULY),
	AUGUST(DateTimeConstants.AUGUST),
	SEPTEMBER(DateTimeConstants.SEPTEMBER),
	OCTOBER(DateTimeConstants.OCTOBER),
	NOVEMBER(DateTimeConstants.NOVEMBER),
	DECEMBER(DateTimeConstants.DECEMBER);
	
	private static final Map<Integer, Month> LOOKUP;
	static {
		final Month[] values = values();
		final Map<Integer, Month> lookup = new HashMap<Integer, Month>(values.length, 1);
		for (final Month value : values) {
			lookup.put(value.jodaConstant, value);
		}
		LOOKUP = Collections.unmodifiableMap(lookup);
	}
	
	private final int jodaConstant;
	
	
	private Month(int jodaConstant) {
		this.jodaConstant = jodaConstant;
	}
	
	public int getJodaConstant() {
		return jodaConstant;
	}
	
	public static Month getMonthFor(int jodaConstant) {
		return LOOKUP.get(jodaConstant);
	}
}
