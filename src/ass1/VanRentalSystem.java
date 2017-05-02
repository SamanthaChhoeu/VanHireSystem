import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

/**
 * Class implements VanRentalSystem.
 * Contains the main function.
 * @author Samantha Chhoeu
 */
public class VanRentalSystem {
	private ArrayList<Depot> depotList;
	private Calendar cal;
	
	/**
	 * Constructor for a new Van Rental System.
	 */
	public VanRentalSystem(){
		this.depotList = new ArrayList<Depot>();
		this.cal = new Calendar();
	}
	
	/**
	 * The main method.
	 *
	 * @param args the input file
	 */
	// only main is static
	public static void main(String[] args){
		VanRentalSystem system = new VanRentalSystem();
	    Scanner sc = null;
	    try
	    {
	    	
	        sc = new Scanner(new FileReader(args[0]));    // args[0] is the first command line argument
		    while(sc.hasNextLine() && sc.hasNext()){
		    	system.Request(sc);
		    }
	    }
	    catch (FileNotFoundException e) {}
	    finally
	    {
	        if (sc != null) sc.close();
	    }
	}

	    

	/**
	 * Request function handles Van Rental System Requests.
	 * @param sc - the scanner which contains the instructions.
	 */
	
	private void Request(Scanner sc){
		String command;
    	Boolean exists;
    	
    	command = sc.next();
    	
    	// Location <depot> <name> <type>
    	if(command.contains("#")){
    		command = sc.nextLine();
    	}
    	if(command.equals("Location")){
    		String depot, name, type;
    		depot = sc.next();
    		name = sc.next();
    		type = sc.next();
    		exists = false;
    					    		
    		// check if the name exists in the list
    		for (Depot d : depotList){
				if (d != null && d.getName().equals(depot)){
					exists = true;
					d.addVan(name, type);
				}
			}
    		
    		// add depot to the list and add van	
    		if(!exists){
    			Depot newDepot = new Depot(depot);
    			depotList.add(newDepot);
    			newDepot.addVan(name, type);
    		}
    		
    	}
    	// Request <id> <hour1> <month1> <date1> <hour2> <month2> <date2> <num1> <type1> [<num2> <type2>]
    	else if(command.equals("Request")){
    		// linked to preserve order of insertion
    		LinkedHashMap<String, LinkedList<String>> bookings = new LinkedHashMap<String,LinkedList<String>>();

    		String type1, type2, month1, month2;
    		int id, hour1, date1, hour2, date2, num1, num2; 
    		
    	
    		id = sc.nextInt();
    		hour1 = sc.nextInt();
    		month1 = sc.next();
    		date1 = sc.nextInt();
    		hour2 = sc.nextInt();
    		month2 = sc.next();
    		date2 = sc.nextInt();
    		num1 = sc.nextInt();
    		type1 = sc.next();
    		
    		String message = "Booking "+id+" ";
    		
    		int[] start = cal.convertToCalendar(hour1, date1, month1);
    		int[] end = cal.convertToCalendar(hour2, date2, month2);
    		
    		num2 = 0;
    		
    		int i = makeBookings(bookings, start, end, type1, num1,id,depotList);
    		int i2 = 0;
    			    		
    		// check if theres 2 ifhasnextint
    		// do something special for the hash map - get the list form and add to it
	    	if (sc.hasNextInt()){
    			num2 = sc.nextInt();
    			type2 = sc.next();
    			
    			i2 = makeBookings(bookings,start,end,type2,num2,id, depotList);
		    	
    		}
	    	if (i != num1|| i2!=num2){
		    	message = "Booking rejected";
		    	
		    	// remove bookings for rejected request
		    	removeBooking(id, depotList);
		    } else{
		    	message = printBooked(bookings,message);
		    }
    		//Booking 1 CBD Wicked, Zeppelin, Floyd; Penrith Queen	    		
    		System.out.print(message+"\n");
    	}
    	// Change <id> <hour1> <month1> <date1> <hour2> <month2> <date2> <num1> <type1> [<num2> <type2>]
    	else if(command.equals("Change")){
    		String type1, type2, month1, month2;
    		int id, hour1, date1, hour2, date2, num1, num2; 
    		LinkedHashMap<String, LinkedList<Booking>> oldBookings = new LinkedHashMap<String, LinkedList<Booking>>();
    		
    		id = sc.nextInt();
    		hour1 = sc.nextInt();
    		month1 = sc.next();
    		date1 = sc.nextInt();
    		hour2 = sc.nextInt();
    		month2 = sc.next();
    		date2 = sc.nextInt();
    		num1 = sc.nextInt();
    		type1 = sc.next();		
    		
    		// check availability
    		String message = "Change "+id+" ";
    		LinkedHashMap<String, LinkedList<String>> bookings = new LinkedHashMap<String,LinkedList<String>>();
    		int[] start = cal.convertToCalendar(hour1, date1, month1);
    		int[] end = cal.convertToCalendar(hour2, date2, month2);
    		
    		// get bookings
    		for (Depot depot:depotList){
				for (Van van:depot.getVans()){
					LinkedList<Booking> relevantBookings = new LinkedList<Booking>();
					for (Booking booking: van.getBookings()){
						//System.out.print(booking);
						if (booking.getBookingID()==id){
							// store old bookings
							relevantBookings.add(booking);	    							
						}
					}
					oldBookings.put(van.getName(),relevantBookings);
					
				}
				
			}
    		
    		// delete previous booking 
    		removeBooking(id,depotList);
    		
    		int i = makeBookings(bookings, start, end, type1, num1,id, depotList);
    		int i2 = 0;
    		num2 = 0;
    			    		
    		// check if theres 2 ifhasnextint
    		// do something special for the hash map - get the list form and add to it
	    	if (sc.hasNextInt()){
    			num2 = sc.nextInt();
    			type2 = sc.next();
    			i2 = makeBookings(bookings,start,end,type2,num2,id,depotList);    	
    		}
	    	if (i != num1|| i2!=num2){
		    	message = "Change rejected";
		    	// DELETE JUST MADE BOOKINGS
		    	removeBooking(id, depotList);
		    	// REBOOK THE BOOKINGS THAT WERE DELETED
		    	Set<String> keys = oldBookings.keySet();
		    	for (String key:keys){
		    		LinkedList<Booking> rebook = oldBookings.get(key);
		    		
		    		for (Depot depot:depotList){
		    			for (Van van:depot.getVans()){
		    				
		    				if (van.getName().equals(key)){
		    					
		    					for(Booking booking:rebook){
		    						van.getBookings().add(booking);
		    					}
		    					
		    				}
		    			}
		    		}
		    		
		    	}
		    	
		    } else {
		    	message = printBooked(bookings,message);
		    }
    		System.out.print(message+"\n");
    	}
    	// Cancel <id>
    	else if(command.equals("Cancel")){
    		int id;
    		id = sc.nextInt();
    		if (removeBooking(id, depotList)){
    			System.out.print("Cancel "+id+"\n");
    		} else {
    			System.out.print("Cancel rejected");
    		} 		
    	}
    	// Print <depot>
    	// for 9 it needs to be 09:00
    	// need to change the order
    	else if(command.equals("Print")){
    		String depotName;
    		depotName = sc.next();
    		
    		for (Depot depot : depotList){
    			if (depot.getName().equals(depotName)){
    				for (Van van:depot.getVans()){
    					Map<Integer,String> toPrint = new TreeMap<Integer,String>();
    					for (Booking booking:van.getBookings()){
    						toPrint.put(booking.getSortTime(), depot.getName()+" "+van.getName()+" "
        							+cal.twoDigit(booking.getStartTime())+":00 "
        							+cal.monthToCal(booking.getStartMonth())
        							+" "+booking.getStartDate()+" "
        							+cal.twoDigit(booking.getEndTime())+":00 "
        							+cal.monthToCal(booking.getEndMonth())
        							+" "+booking.getEndDate());
    		
    					}
    					Set<Integer> keys = toPrint.keySet();
    		    		for (int key:keys){
    	
    		    			System.out.println(toPrint.get(key));
    		    		}
    				}   		
    			}
    		}
    		
    		
    	}    				        	
    }
	
	/**
	 * Function that helps print the booked vans.
	 *
	 * @param HashMap<String, LinkedList<String>>bookings - the bookings that have been placed
	 * @param String message - the message contains all that needs to be printed so far.
	 * @return String message - the string that contains all the information that needs to be printed.
	 */
	
	private String printBooked(HashMap<String, LinkedList<String>> bookings, String message){
    	int depotCount = 0;
    	Set<String> keys = bookings.keySet();
    	Boolean realFirst = true;
    	for(String key: keys){	
    		if (!bookings.get(key).isEmpty()){
    			
    			if (depotCount != keys.size() && depotCount != 0 && !realFirst ){
					message += "; "+key;
					
				} else {
					message += key;
					realFirst = false;
				}		
    			int vanCount = 0;
    			for (String van:bookings.get(key)){
    				if (vanCount != bookings.get(key).size()-1){
    					message += " "+van+",";
    				} else {
    					message+=  " "+van;
    				}
    				
    				vanCount++;
    			}
    		
    		} 
    		depotCount++;
            
        }
		return message;
	}
	
	/**
	 * Removes the booking.
	 *
	 * @param int id - the Booking id.
	 * @param ArrayList<Depot> depotList - the list of existing depots. 
	 * @return boolean - check if anything has been deleted.
	 */
	
	private Boolean removeBooking(int id, ArrayList<Depot> depotList){
		Boolean found = false;
		for (Depot depot:depotList){
			for (Van van:depot.getVans()){
				Booking remove=null;
				for (Booking booking: van.getBookings()){
					if (booking.getBookingID()==id){
						// store booking to remove
						remove = booking;
						found = true;
						
					}		    					
				}
				if (remove != null){
					van.removeBooking(remove);
				}
				
			}
			
		}
		return found;
	}
	
	/**
	 * Make bookings.
	 *
	 * @param LinkedHashMap<String, LinkedList<String>> bookings - the existing bookings.
	 * @param int[] start - the start date and time.
	 * @param int[] end - the end date and time.
	 * @param String type - the transmission type.
	 * @param int num - the number of vans to book.
	 * @param int id - the booking id
	 * @param ArrayList<Depot> depotList - the existing depot list.
	 * @return int - the number of bookings that have been made. 
	 */
	public int makeBookings(LinkedHashMap<String, LinkedList<String>> bookings, 
			int[]start, int[]end, String type, int num, int id, ArrayList<Depot> depotList) {
		ArrayList<Van> vans = new ArrayList<>();
		int i = 0;
		
		for (Depot depot:depotList){
    		vans = depot.getVans();
			for (Van van:vans){
				if (i==num){
					
					break;
					
				}else {
					
					if (van.getTransmission().equals(type) && van.checkAvailability(cal.convert(start), cal.convert(end))){		
						//System.out.println(van.getName()+"success");
		    			van.addBooking(start, end, id);		
		    			if (bookings.keySet().contains(depot.getName())){
		    				LinkedList<String> bookedVans = bookings.get(depot.getName());
		    				bookedVans.add(van.getName());
		    				bookings.put(depot.getName(), bookedVans);
		    			} else {
		    				LinkedList<String> bookedVans = new LinkedList<String>();
		    				bookedVans.add(van.getName());
		    				bookings.put(depot.getName(), bookedVans);
		    			}	
		    			i++;
		    			
					}			
				}
			
			}
			
    	}
		return i;
	}
}
