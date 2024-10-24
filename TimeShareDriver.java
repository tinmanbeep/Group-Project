package csc222Quiz_m5StartFiles;
import java.util.*;

import csc222Quiz_m5ANSWERS.AddOn;
import csc222Quiz_m5ANSWERS.Customer;
import csc222Quiz_m5ANSWERS.TimeShare;

import java.text.*;

public class TimeShareDriver {

	
	public static void main(String[] args) {
		
		
	}
		// This method loads the pre-set data into the Customer and Auto ArrayLists
	// Loads the pre-set data into the Customer and Auto ArrayLists
		public static void loadNewData(ArrayList<TimeShare> a, ArrayList<Customer> c, ArrayList<AddOn> addOn) {
			a.add(new TimeShare("Phoenix-1", 2, 180.00));
			a.add(new TimeShare("San Diego-1", 1, 235.00));
			a.add(new TimeShare("San Francisco", 1, 450.00));
			a.add(new TimeShare("Virginia Beach-1", 3, 145.00));

			c.add(new Customer("Mike Trout", false));
			c.add(new Customer("Shohei Ohtani", true));
			c.add(new Customer("Corey Seager", true));
			c.add(new Customer("Lainey Wilson", true));
			c.add(new Customer("Taylor Swift", false));

			addOn.add(new AddOn("Internet", 5.95, false));
			addOn.add(new AddOn("Best view", 90.00, false));
			addOn.add(new AddOn("Extra cleaning", 45.00, true));
			addOn.add(new AddOn("Extra Bed", 25.00, true));
			addOn.add(new AddOn("Meal plan", 120.00, false));

		}

       // other methods go here
}
