package csc222Quiz_m5StartFiles;

public class Customer {
	
	// the Customer class has three fields
	private int custId;
	private String name;
    private boolean premier;
	
	// the following will be used to automatically generate customer ids
	//so they will be unique
	private static int nextNum = 100;
	
	
	// create your constructors
	
	// toString method
	public String toString()
	{
		String ans = "is a";
		if(!premier)
			ans = " is not a";
		return name + " (custID: " + custId + ") " + ans + " premier member of the club";
	}
	
	//add getters and setters


}