
/**
 * Class implements Booking.
 *
 * @author Samantha Chhoeu
 */
public class Booking {
	
	private String booking;
	private int id, startDate,endDate,startTime,endTime,startMonth,endMonth,sortTime,startNum,endNum;
	
	/**
	 * Instantiates a new booking.
	 *
	 * @param start the start time and date.
	 * @param end the end time and date.
	 * @param id - the booking id.
	 */
	public Booking(int[] start, int[] end, int id){
		this.booking = start+" "+end;
		this.startDate = start[1];
		this.endDate = end[1];
		this.startTime = start[0];
		this.endTime = end[0];
		this.startMonth = start[2];
		this.endMonth = end[2];
		this.id = id;
		Calendar cal = new Calendar();
		this.sortTime = cal.convert(start);
		this.startNum = cal.convert(start);
		this.endNum = cal.convert(end);
	}
	
	/**
	 * Gets the booking details.
	 *
	 * @return the booking details.
	 */
	public String getBooking(){
		return booking;
	}
	
	/**
	 * Gets the start date.
	 *
	 * @return the start date.
	 */
	public int getStartDate(){
		return startDate;
	}
	
	/**
	 * Gets the start month.
	 *
	 * @return the start month.
	 */
	public int getStartMonth(){
		return startMonth;
	}
	
	/**
	 * Gets the start time.
	 *
	 * @return the start time.
	 */
	public int getStartTime(){
		return startTime;
	}
	
	/**
	 * Gets the end date.
	 *
	 * @return the end date.
	 */
	public int getEndDate(){
		return endDate;
	}
	
	/**
	 * Gets the end month.
	 *
	 * @return the end month.
	 */
	public int getEndMonth(){
		return endMonth;
	}
	
	/**
	 * Gets the end time.
	 *
	 * @return the end time.
	 */
	public int getEndTime(){
		return endTime;
	}
	
	/**
	 * Gets the booking ID.
	 *
	 * @return the booking ID.
	 */
	public int getBookingID(){
		return id;
	}
	
	/**
	 * Gets the sort number which contains the info in the format MMDDTT.
	 *
	 * @return the sort number.
	 */
	public int getSortTime(){
		return sortTime;
	}
	
	/**
	 * Gets the start num which contains the info in the format MMDDT.
	 *
	 * @return the start num.
	 */
	public int getStartNum(){
		return startNum;
	}
	
	/**
	 * Gets the end num which contains the info in the format MMDDT.
	 *
	 * @return the end num.
	 */
	public int getEndNum(){
		return endNum;
	}
}
