package com.lmf.math.util;

public class LMFMath {
	
	public static int divisionWithoutDecimal(int divident, int divider){
		String quotient = String.valueOf(divident/divider);
		if(quotient.indexOf(",")>=0){
			quotient = quotient.substring(0, quotient.indexOf(","));
		}else if(quotient.indexOf(".")>=0){
			quotient = quotient.substring(0, quotient.indexOf("."));
		}
		return Integer.parseInt(quotient);
	}
	
}
