
/**
 * Class implements Calendar.
 *
 * @author Samantha Chhoeu
 */
public class Calendar {
	/**
	 * Convert to calendar.
	 *
	 * @param hour - the hour
	 * @param date - the date
	 * @param month - the month
	 * @return the int[] which contains the time and date information where 
	 * cal[0]= hour
	 * cal[1]= date
	 * cal[2]= month
	 */
	
	public int[] convertToCalendar(int hour, int date, String month){
		int[] cal  = new int[3]; 
		cal[0] = hour;
		cal[1] = date;
		
		if (month.equals("Jan")){
			cal[2] = 1;
		} else if (month.equals("Feb")){
			cal[2] = 2;
		} else if (month.equals("Mar")){
			cal[2] = 3;
		} else if (month.equals("Apr")){
			cal[2] = 4;
		} else if (month.equals("May")){
			cal[2] = 5;
		} else if (month.equals("Jun")){
			cal[2] = 6;
		} else if (month.equals("Jul")){
			cal[2] = 7;
		} else if (month.equals("Aug")){
			cal[2] = 8;
		} else if (month.equals("Sep")){
			cal[2] = 9;
		} else if (month.equals("Oct")){
			cal[2] = 10;
		} else if (month.equals("Nov")){
			cal[2] = 11;
		} else if (month.equals("Dec")){
			cal[2] = 12;
		}
		return cal;
		
	}
	
	/**
	 * Month to cal conversion.
	 *
	 * @param the month as a digit. 
	 * @return the string which is a 3 character representation of the month. 
	 */
	// String in this format 23:00 Mar 27 23:00 Mar 29 
	public String monthToCal(int month){
		String cal="";
		if (month == 1){
			cal = "Jan";
		} else if (month == 2){
			cal = "Feb";
		} else if (month == 3){
			cal = "Mar";
		} else if (month == 4){
			cal = "Apr";
		} else if (month == 5){
			cal = "May";
		} else if (month == 6){
			cal = "Jun";
		} else if (month == 7){
			cal = "Jul";
		} else if (month == 8){
			cal = "Aug";
		} else if (month == 9){
			cal = "Sep";
		} else if (month == 10){
			cal = "Oct";
		} else if (month == 11){
			cal = "Nov";
		} else if (month == 12){
			cal = "Dec";
		}
		return cal;
		
	}
	
	/**
	 * Time print.
	 *
	 * @param time the time as a digit.
	 * @return the string as a two character number e.g. 9 -> 09. 
	 */
	// convert time
	public String twoDigit(int time){
		String print = null;
		if (time<10){
			print = Integer.toString(0);
			print += Integer.toString(time);
		}else{
			print = Integer.toString(time);
		}
		return print;
		
	}
	
	/**
	 * Convert Time and Date to Int for sorting.
	 *
	 * @param the array representing date and time.
	 * @return the int which is in the format MMDDTT.
	 */
	public int convert(int[] dt){
		StringBuilder sb = new StringBuilder();
		sb.append(twoDigit(dt[2]));
		sb.append(twoDigit(dt[1]));
		sb.append(twoDigit(dt[0]));
		String string = sb.toString();
		int number = Integer.parseInt(string);	
		return number;
	}
	
}
