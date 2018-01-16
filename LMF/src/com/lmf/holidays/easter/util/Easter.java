package com.lmf.holidays.easter.util;

import java.util.Calendar;
import java.util.GregorianCalendar;

import com.lmf.holidays.Calendars;
import com.lmf.holidays.exceptions.InvalidCalendarsException;
import com.lmf.math.util.LMFMath;

public class Easter {

	public static Calendar calcola(int year, Calendars calendar) throws InvalidCalendarsException {
		int g = year % 19;
		int i = -1, j = -1;
		
		if(calendar.equals(Calendars.GREGORIAN)){
			int c = year / 100;
			int h = (c-LMFMath.divisionWithoutDecimal(c,4)-LMFMath.divisionWithoutDecimal((8*c+13),25)+19*g+15) % 30;
			i = h-(LMFMath.divisionWithoutDecimal(h,28))*(1-(LMFMath.divisionWithoutDecimal(29,h+1))*(LMFMath.divisionWithoutDecimal(21-g,11)));
			j = (year+LMFMath.divisionWithoutDecimal(year,4)+i+2-c+LMFMath.divisionWithoutDecimal(c, 4)) % 7;
		}else if(calendar.equals(Calendars.GIULIAN)){
			i = (19*g+15) % 30;
			j = (year+LMFMath.divisionWithoutDecimal(year,4)+i) % 7;
		}
		
		if(i < 0 || j < 0){
			throw new InvalidCalendarsException();
		}
		
		int l = i-j;
		int month = 3+LMFMath.divisionWithoutDecimal(l+40, 44);
		int day =  l+28-31*(LMFMath.divisionWithoutDecimal(month, 4));
		
		GregorianCalendar gc = new GregorianCalendar();
		gc.set(year, month-1, day, 0, 0, 0);
		gc.set(GregorianCalendar.MILLISECOND, 0);
		return gc;
	}

}
