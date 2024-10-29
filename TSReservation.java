package timesharerentals;

import java.text.NumberFormat;

public class TimeShare {
	
	private String location;
	private int numBedrooms;
	private double costPerNight;
	
	public TimeShare() {
		
	}
	
	public TimeShare(String location, int numBedrooms, double costPerNight) {
		this.location = location;
		this.numBedrooms = 	numBedrooms;
		this.costPerNight = costPerNight;
	}
	
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		return "A "+ numBedrooms + " bedroom glorius room located in " + location + 
				" and rents for " + nf.format(costPerNight) + " per night";
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getNumBedrooms() {
		return numBedrooms;
	}

	public void setNumBedrooms(int numBedrooms) {
		this.numBedrooms = numBedrooms;
	}

	public double getCostPerNight() {
		return costPerNight;
	}

	public void setCostPerNight(double costPerNight) {
		this.costPerNight = costPerNight;
	}
	
}
