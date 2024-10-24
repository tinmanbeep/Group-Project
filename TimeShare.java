package csc222Quiz_m5StartFiles;

import java.text.NumberFormat;

public class TimeShare {
	
	private String location;
	private int numBedrooms;
	private double costPerNight;
	
	//need constructors
	
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		return "A "+ numBedrooms + " bedroom glorius room located in " + location + 
				" and rents for " + nf.format(costPerNight) + " per night";
	}

	//need getters and setters
}
