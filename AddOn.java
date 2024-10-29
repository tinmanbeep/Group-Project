package timesharerentals;

import java.text.NumberFormat;

public class AddOn {
	
	private String type;
	private double cost;
	private boolean duration;
	
	public AddOn() {
		
	}
	
	public AddOn(String t, double c, boolean d) {
		type = t;
		cost = c;
		duration = d;
	}
	
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		if (duration == true)
			return type + " for a total cost of " + nf.format(cost);
		else
			return  type + " for a DAILY cost of " + nf.format(cost);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public boolean isDuration() {
		return duration;
	}

	public void setDuration(boolean duration) {
		this.duration = duration;
	}
}
