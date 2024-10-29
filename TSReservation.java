package timesharerentals;

import java.text.NumberFormat;
import java.util.ArrayList;

import timesharerentals.AddOn;
import timesharerentals.Customer;
import timesharerentals.TimeShare;

public class TSReservation {

	private Customer cust;
	private TimeShare timeShare;
	private int numDays;
	private char discount; // N = none P = premier D = days B = both
	// get 20% discount for rentals of a week or more
	// get 10% more (30% total) if they are a premier member
	private double rentAmt;
	private ArrayList<AddOn> addOn = new ArrayList<>();
	
	// Default constructor
	public TSReservation() {
		
	}

	// Full constructor
	public TSReservation(Customer cust, TimeShare timeShare, int numDays, ArrayList<AddOn> addOn) {
		this.numDays = numDays;
		this.cust = cust;
		this.timeShare = timeShare;
		for (AddOn add : addOn)
		{
			this.addOn.add(add);
		}
		setRentalCost(cust, timeShare);
	}

	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		String string;
		string = cust.toString() + " rented ";
		string = string + timeShare.toString() + " for " + numDays + " days.";
		string = string + "\nThe cost was " + nf.format(rentAmt) + "\n";
		switch (discount) {
			case 'B':
				string = string + "This person was a premier member and rented over six days and received a 30% discount\n";
				break;
			case 'G':
				string = string + "This person was a premier member and received a 10% discount\n";
				break;
			case 'D':
				string = string + "This person rented over six days and received a 20% discount\n";
				break;
			case 'N':
				string = string + "This person did not qualify for a discount\n";
		}
		
		string = string + "The following add ones were reserved:\n";
		
		for (AddOn add : addOn)
		{
			string = string + add + "\n";
		}
		
		return string;
	}

	// Method to calculate rental cost and set discount
	public void setRentalCost(Customer cust, TimeShare timeShare) {
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		double rentdiscount = timeShare.getCostPerNight() * numDays;
		char discounttype = ' ';

		if (cust.isPremier()) {
			if (numDays > 6) {
				
				discounttype = 'B';
				rentdiscount = 0.7;
			} else {
				discounttype = 'G';
				rentdiscount = 0.9; // 10% total discount
			}
		}
		else {
			if (numDays <= 6) {
				discounttype = 'D';
				rentdiscount = 0.8; // 20% total discount
			} else {
				discounttype = 'N';
				rentdiscount = 1;   // 0% total discount
			}
		
		}
		this.setRentAmt(timeShare.getCostPerNight() * numDays * rentdiscount);
		this.setDiscount(discounttype);
	}
	
	// Method to adjust rental amount based on add-ons
	public void costAddOns(ArrayList<AddOn> addOn) {
		double addonCost = 0.0;
		for (AddOn addon : addOn) {
			if (addon.isDuration()) {
				addonCost = addonCost + addon.getCost();
			} else {
				addonCost = addonCost + addon.getCost() * numDays;
			}
		}
        System.out.println("The cost of the add ons is: $" + addonCost);
        System.out.println("The total cost is: $" + (this.getRentAmt() + addonCost));
	}

	// Getters and setters for each field
	public double getRentAmt() {
		return rentAmt;
	}

	public void setRentAmt(double rentAmt) {
		this.rentAmt = rentAmt;
	}

	public int getNumDays() {
		return numDays;
	}

	public void setNumDays(int numDays) {
		this.numDays = numDays;
	}

	public char getDiscount() {
		return discount;
	}

	public void setDiscount(char discount) {
		this.discount = discount;
	}
}
