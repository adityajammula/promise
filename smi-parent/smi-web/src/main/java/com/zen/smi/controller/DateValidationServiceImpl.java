package com.zen.smi.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateValidationServiceImpl implements DateValidationService {

	public boolean validateDate(String dateToValidate) {
		
			
			String dateFromat="MM-DD-YYYY";
		

			if(dateToValidate == null){
				return false;
			}

			SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
			sdf.setLenient(false);

			try {

				//if not valid, it will throw ParseException
				Date date = sdf.parse(dateToValidate);
				System.out.println(date);

			} catch (ParseException e) {

				e.printStackTrace();
				return false;
			}

		return false;
	}

}
