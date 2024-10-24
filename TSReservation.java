package csc222Quiz_m5StartFiles;



import java.text.NumberFormat;
import java.util.ArrayList;


public class TSReservation {
	
	private Customer cust;
	private TimeShare timeShare;
	private int numDays;
	private char discount; // N = none P = premier D = days B = both
	// get 20% discount for rentals of a week or more
	// get 10% more (30% total) if they are a premier member
	private double rentAmt;
	private ArrayList<AddOn> addOn = new ArrayList<>();
	
	
	// need constructors
	//your full constructors needs to call the setRentalCost() method so it can
	// set the rentAmt and discount fields
	
   
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		String string;
		string = cust.toString() + " rented ";
		string = string + timeShare.toString() + " for " + numDays + " days.";
		string = string + "\n   The cost was " + nf.format(rentAmt) + "\n";
		switch (discount) {
		case 'B':
			string = string + "This person was a premier member and rented over six days and received a a 30% discount";
			break;
		case 'G':
			string = string + "This person was a premier member and received a a 10% discount";
			break;
		case 'D':
			string = string + "This person rented over six days and received a 20% discount";
			break;
		case 'N':
			string = string + "This person did not qualify for a discount";

		}
		string = string + "\n The following add ones were reserved:";
		for (int i = 0; i < addOn.size(); i++)
			string = string + "\n" + addOn.get(i).toString();

		return string;
	}
	
	// TO DO!!!the setRentalCost is where the cost is set
    // you also set the discount code here for the rental
	public void setRentalCost()  {
		
		
            
	}
	
	//TODO!!  the costAddOns() gets information on the add ons that the user wants
	//adjusts the rentAmt field to reflect these add ons
	public void costAddOns() {
		

	}

	// generate getterss and setters
}