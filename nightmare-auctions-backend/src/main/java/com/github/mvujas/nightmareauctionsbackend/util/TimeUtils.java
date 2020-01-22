package com.github.mvujas.nightmareauctionsbackend.util;

import java.util.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class TimeUtils {

	public static Date now() {
		return new Date();
	}
	
	public static Timestamp getCurrentTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}
	
	public static Time getCurrentTime() {
		return new Time(System.currentTimeMillis());
	}

}
