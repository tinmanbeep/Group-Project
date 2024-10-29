package timesharerentals;

import java.text.NumberFormat;
import java.util.ArrayList;

public class TSReservation {
	private Customer cust;
	private TimeShare timeShare;
	private int numDays;
	private char discount; // N = none, P = premier, D = days, B = both
	private double rentAmt;
	private ArrayList<AddOn> addOn = new ArrayList<>();

	// Default constructor
	public TSReservation() {
	}

	// Full constructor
	public TSReservation(Customer cust, TimeShare timeShare, int numDays, ArrayList<AddOn> addOn) {
		this.cust = cust;
		this.timeShare = timeShare;
		this.numDays = numDays;
		this.addOn = addOn;
		setRentalCost(cust, timeShare);
		costAddOns();
	}

	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		String string;
		string = "Customer rented " + numDays + " days.";
		string = string + "\n   The cost was " + nf.format(rentAmt) + "\n";
		switch (discount) {
			case 'B':
				string = string + "This person was a premier member and rented over six days and received a 30% discount";
				break;
			case 'P':
				string = string + "This person was a premier member and received a 10% discount";
				break;
			case 'D':
				string = string + "This person rented over six days and received a 20% discount";
				break;
			case 'N':
				string = string + "This person did not qualify for a discount";
		}
		return string;
	}

	// Method to calculate rental cost and set discount
	public void setRentalCost(Customer cust, TimeShare timeShare) {
		rentAmt = timeShare.getCostPerNight() * numDays;
		if (numDays > 6) {
			discount = 'D';
			rentAmt *= 0.8;
		}
		if (cust.isPremier()) {
			if (discount == 'P') {
				discount = 'B';
				rentAmt *= 0.7; // 30% total discount
			} else {
				discount = 'P';
				rentAmt *= 0.9; // 10% discount for premier
			}
		}
	}

	// Method to adjust rental amount based on add-ons
	public void costAddOns() {
		for (AddOn addon : addOn) {
			if (addon.isDuration()) {
				rentAmt += addon.getCost();
			} else {
				rentAmt += addon.getCost() * numDays;
			}
		}
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
