package com.zen.smi.generic.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

 

public class DateUtil {

	/**
	 * Date format "yyyy-MM-dd"
	 */
	public static final String YYYY_MM_DD = "yyyy-MM-dd HH:mm:ss";
	/**
	 * Converts date to string with given input format.
	 *
	 * @param inputDate Date
	 * @param inputFormat String
	 * @return String
	 */
	/**
	 * Instance of DateUtil.
	 */
	private static DateUtil instance;


	/**
	 * Private constructor
	 */
	private DateUtil() {

	}

	/**
	 * Singleton instance of GeneralUtil.
	 *
	 * @return instance EmployeeHelper
	 */
	public static DateUtil getInstance() {
		if (instance == null) {
//			LOGGER.debug("Inside getInstance...");
			instance = new DateUtil();
		}
		return instance;
	}
	
	public String convertDateToString(final Date inputDate, final String inputFormat) {

		if (inputDate == null) {
			return "";
		}
		String dateFormatString = YYYY_MM_DD;
		if (inputFormat != null) {
			dateFormatString = inputFormat;
		}
    	DateFormat dateFormat = new SimpleDateFormat(dateFormatString, Locale.ENGLISH);
        String retDate = null;
        retDate = dateFormat.format(inputDate);
        return retDate;
    }
	/**
	 * Converts string to date with given input format.
	 *
	 * @param inputDateStr String
	 * @param inputFormat String
	 * @return Date
	 */
	public Date convertStringToDate(final String inputDateStr, final String inputFormat) {

    	DateFormat dateFormat = new SimpleDateFormat(inputFormat, Locale.ENGLISH);
        Date startDate = null;
        try {
            startDate = dateFormat.parse(inputDateStr);
        } catch (ParseException exception) {
            //LOGGER.error(exception);
        }
        return startDate;
    }
	
	
 public boolean compareDates(String startDate,String endDate,String createdDate)
 {
	 	boolean isDateInBetween = false;
	 	Date fromDate = convertStringToDate(startDate, YYYY_MM_DD);
	 	Date toDate = convertStringToDate(endDate, YYYY_MM_DD);
	 	Date crDate = convertStringToDate(createdDate, YYYY_MM_DD);
	 
	 	System.out.println("fromDate==:"+fromDate+":==fromDate==:"+toDate+"createddate=:"+crDate);
	 	if((crDate.after(fromDate) || crDate.equals(fromDate) ) && (crDate.before(toDate) ||  crDate.equals(toDate)))
	 	{
	 		isDateInBetween = true;
	 		
	 	}
	 	
 
	 return isDateInBetween;
	 
 }
 
 public static void main(String a[]) 
	{
	 //fromDate==:Fri Aug 21 03:48:39 IST 2015:==endtDate==:Fri Aug 21 04:18:39 IST 2015createddate=:Fri Aug 21 12:15:31 IST 2015

	 String startDate = "Fri Aug 21 03:48:39 IST 2015";
	 String todate = "Fri Aug 21 04:18:39 IST 2015";
	 String createdDate = "Fri Aug 21 12:15:31 IST 2015";
	 DateUtil util = new DateUtil();
	 util.compareDates(startDate, todate, createdDate);
	 
	 
	}
	
}
