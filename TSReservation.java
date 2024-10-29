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
		setNumDays(numDays);
		setCust(cust);
		setTimeShare(timeShare);
		setAddOn(addOn);
		setRentalCost();
	}

	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		String string;
		string = "\n" + cust.toString() + " rented \n";
		string = string + timeShare.toString() + " for " + numDays + " days.";
		string = string + "\nThe cost was " + nf.format(rentAmt) + "\n";
		switch (discount) {
			case 'B':
				string = string + cust.getName() + " was a premier member and rented over six days and received a 30% discount\n";
				break;
			case 'P':
				string = string + cust.getName() + " was a premier member and received a 10% discount\n";
				break;
			case 'D':
				string = string + cust.getName() + " rented over six days and received a 20% discount\n";
				break;
			case 'N':
				string = string + cust.getName() + " did not qualify for a discount\n";
		}
		if (!addOn.isEmpty()) {
			string = string + "The following addons were reserved:\n";
			for (AddOn add : addOn) {
				string = string + add + "\n";
			}
		}
		return string;
	}

	// Method to calculate rental cost and set discount
	public void setRentalCost() {
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		double rentdiscount = timeShare.getCostPerNight() * numDays;
		char discounttype = ' ';
		
		// N = none P = premier D = days B = both
		// get 20% discount for rentals of a week or more
		// get 10% more (30% total) if they are a premier member
		if (numDays > 6) {
			if (cust.isPremier()) {
				// 30% discount
				discounttype = 'B';
				rentdiscount = rentdiscount * 0.7;
			}
			else {
				// 10% discount
				discounttype = 'D';
				rentdiscount = rentdiscount * 0.8; // 20% total discount
			}
		}
		else {
			if (cust.isPremier()) {
				discounttype = 'P';
				rentdiscount = rentdiscount * 0.9; // 10% total discount
			}
			else {
				discounttype = 'N';
				rentdiscount = rentdiscount * 1;   // 0% total discount
			}
		}
		this.setRentAmt(rentdiscount);
		this.setDiscount(discounttype);
	}
	
	// Method to adjust rental amount based on add-ons
	public void costAddOns() {
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		double addonCost = 0.0;
		for (AddOn addon : addOn) {
			if (addon.isDuration()) {
				addonCost = addonCost + addon.getCost();
			} else {
				addonCost = addonCost + addon.getCost() * numDays;
			}
		}
		setRentAmt(this.getRentAmt() + addonCost);
        System.out.println("The cost of the add ons is: " + nf.format(addonCost));
        System.out.println("The total cost is: " + nf.format(this.getRentAmt()));
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

	public Customer getCust() {
		return cust;
	}

	public void setCust(Customer cust) {
		this.cust = cust;
	}

	public TimeShare getTimeShare() {
		return timeShare;
	}

	public void setTimeShare(TimeShare timeShare) {
		this.timeShare = timeShare;
	}

	public ArrayList<AddOn> getAddOn() {
		return addOn;
	}

	public void setAddOn(ArrayList<AddOn> addOn) {
		this.addOn = addOn;
	}

}
