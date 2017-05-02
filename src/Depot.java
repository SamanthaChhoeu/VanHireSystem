import java.util.ArrayList;

/**
 * Class implements Depot.
 *
 * @author Samantha Chhoeu
 */
public class Depot {
	private String name;
	private ArrayList<Van> vans;
	
	/**
	 * Constructor for a new depot.
	 *
	 * @param String name - the name of the depot
	 */
	public Depot(String name){
		this.name = name;
		this.vans = new ArrayList<Van>();

	}
	
	/**
	 * Creates and adds a new van to the list of vans.
	 *
	 * @param String name - the name of the van.
	 * @param String transmission - the transmission type of the van.
	 */
	// add van
	public void addVan(String name, String transmission){
		Van newVan = new Van(name, transmission);
		vans.add(newVan);
		//System.out.print(getName()+"\nauto"+automatic+"\nmanual"+manual+"\n");
	}
	
	/**
	 * Gets the list of vans.
	 *
	 * @return the list of vans.
	 */
	// get list of vans
	public ArrayList<Van> getVans(){
		return vans;
	}
	
	/**
	 * Gets the name of the van.
	 *
	 * @return the name of the van.
	 */
	public String getName() {
		return name;
		
	}
	
	
}
