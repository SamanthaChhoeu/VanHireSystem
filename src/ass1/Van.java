import java.util.ArrayList;

/**
 * Class implements Van.
 *
 * @author Samantha Chhoeu
 */
public class Van {
	private String name, transmission;
	private ArrayList<Booking> bookings; // store the bookings here - maybe store in string for easy access printing
	
	/**
	 * Instantiates a new van.
	 *
	 * @param name - the name of the van.
	 * @param transmission - the transmission type of the van.
	 */
	public Van(String name, String transmission){
		this.name = name;
		this.transmission = transmission;
		this.bookings = new ArrayList<Booking>();
	}
	
	/**
	 * Create and add the booking to the list of bookings in the Van.
	 *
	 * @param start - the start
	 * @param end the end
	 * @param id the id
	 */
	public void addBooking(int[] start, int[] end, int id){
		Booking newBooking = new Booking(start,end, id);
		bookings.add(newBooking);
		
	}
	
	/**
	 * Removes the booking from the list of bookings in the Van.
	 *
	 * @param booking the booking
	 */
	public void removeBooking(Booking booking){
		bookings.remove(booking);
	}

	/**
	 * Check availability.
	 *
	 * @param start - the start time and date
	 * @param end - the end time and date
	 * @return true, if the van is available
	 */
	public boolean checkAvailability(int start, int end) {
		//cal[0]= hour
		//cal[1]= date
		//cal[2]= month
		// need to account for 1 hour
		//System.out.print(bookings);
		Boolean canBook = true;
		
		for (Booking booking:bookings){
			// date ranges do not overlap if end_1<start_2 OR end_2<start_1
			if (end<booking.getStartNum()||booking.getEndNum()<start){
				//canBook = true;
				continue;
			} else {
				canBook = false;
			}
			// date ranges do overlap if end_1>=start_2 AND end_2>=start_1
			if (end>=booking.getStartNum() && booking.getEndNum()>=start){
				canBook = false;
			}
				
		}	
		return canBook;
	}
	
	
	// change booking range 
	
	
	/**
	 * Gets the name of the van.
	 *
	 * @return the name of the van.
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Gets the list of bookings in the van.
	 *
	 * @return the list of bookings in the van.
	 */
	public ArrayList<Booking> getBookings(){
		return bookings;
	}
	
	/**
	 * Gets the transmission type.
	 *
	 * @return the transmission type.
	 */
	public String getTransmission(){
		return transmission;
	}
}
