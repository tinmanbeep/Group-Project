package timesharerentals;

import java.util.*;
import java.text.*;

import timesharerentals.AddOn;
import timesharerentals.Customer;
import timesharerentals.TimeShare;
import timesharerentals.TSReservation;

public class TimeShareDriver {

    public static void main(String[] args) {
        ArrayList<TimeShare> timeShare = new ArrayList<>();
        ArrayList<Customer> customer = new ArrayList<>();
        ArrayList<AddOn> addOn = new ArrayList<>();
        ArrayList<TSReservation> reservations = new ArrayList<>(); // Store reservations
        
        loadNewData(timeShare, customer, addOn);
        
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Would you like to (1) rent a condo (2) check a condo back in (3) quit");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    rentCondo(timeShare, customer, addOn, reservations);
                    break;
                case 2:
                    checkInCondo(timeShare, reservations);
                    break;
                case 3:
                	printRentals(reservations);
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }

    // Method to load the initial data
    public static void loadNewData(ArrayList<TimeShare> timeshare, ArrayList<Customer> customer, ArrayList<AddOn> addOn) {
    	
    	// sets timeshare: location, bed roooms, cost per day
    	timeshare.add(new TimeShare("Phoenix-1", 2, 180.00));
    	timeshare.add(new TimeShare("San Diego-1", 1, 235.00));
    	timeshare.add(new TimeShare("San Francisco", 1, 450.00));
    	timeshare.add(new TimeShare("Virginia Beach-1", 3, 145.00));
    	
    	// sets customer: customer, premier status
    	customer.add(new Customer("Mike Trout", false));
    	customer.add(new Customer("Shohei Ohtani", true));
    	customer.add(new Customer("Corey Seager", true));
    	customer.add(new Customer("Lainey Wilson", true));
    	customer.add(new Customer("Taylor Swift", false));
    	
    	// sets addons: type, cost, daily/length
        addOn.add(new AddOn("Internet", 5.95, false));
        addOn.add(new AddOn("Best view", 90.00, false));
        addOn.add(new AddOn("Extra cleaning", 45.00, true));
        addOn.add(new AddOn("Extra Bed", 25.00, true));
        addOn.add(new AddOn("Meal plan", 120.00, false));

        // Sets the customer id
        for (Customer cus : customer) {
        	cus.setCustId(Customer.getNextNum());
        	Customer.setNextNum(Customer.getNextNum() + 1);
        }      
    }
    
    // Show time shares
    public static void showTimeShares(ArrayList<TimeShare> timeshare) {
        System.out.println("\nThe following time share locations are available to rent:");
        for (int i = 0; i < timeshare.size(); i++) {
            System.out.println((i + 1) + ": " + timeshare.get(i));
        }
    }
    
    // Show customers
    public static void showCust(ArrayList<Customer> customer) {
        System.out.println("\nCustomers:");
        
        for (int i = 0; i < customer.size(); i++) {
            System.out.println((i + 1) + ": " + customer.get(i));
        }
    }
    
    // Show add-ons
    public static void showAddOns(ArrayList<AddOn> addOn) {
        System.out.println("\nAdd ons:");
        for (int i = 0; i < addOn.size(); i++) {
            System.out.println((i + 1) + ": " + addOn.get(i));
        }
    }

    // Choose add-ons
    public static ArrayList<AddOn> chooseAddOns(ArrayList<AddOn> addOn) {
        ArrayList<AddOn> selectedAddOns = new ArrayList<>();
        boolean moreAddOns = true;
        
        Scanner scanner = new Scanner(System.in);
        
        while (moreAddOns) {
        	//print out the addons
        	showAddOns(addOn);
            System.out.print("\nWould you like one of these add ons? (true/false) ");
            moreAddOns = scanner.nextBoolean();
            if (moreAddOns) {
            	
            	
                System.out.print("Which number? ");
                int addOnIndex = scanner.nextInt() - 1; // Convert to 0-based index
                
                int mult = expOrNot(addOn,addOnIndex);
                
                double cost = addOn.get(addOnIndex).getCost();
                
                addOn.get(addOnIndex).setCost(mult * cost);
                
                selectedAddOns.add(addOn.get(addOnIndex)); // add the addon to the list
                System.out.println();
                System.out.print("More add ons? (true/false) ");
                moreAddOns = scanner.nextBoolean();
            }
        }
        return selectedAddOns;
    }
    
    // Print rentals
    public static void printRentals(ArrayList<TSReservation> reservation) {
        System.out.println("Rental Summary");
        for (int i = 0; i < reservation.size(); i++) {
            System.out.println((i + 1) + " " + reservation.get(i).toString());
        }
    }
    
    // Method to rent a condo
    public static void rentCondo(ArrayList<TimeShare> timeshare, ArrayList<Customer> customer, ArrayList<AddOn> addOn,
    		ArrayList<TSReservation> reservations) {

    	Scanner scanner = new Scanner(System.in);

    	showCust(customer);
        
        System.out.print("Please select a customer: ");
        int customerIndex = scanner.nextInt() - 1; // Convert to 0-based index
        Customer selectedCustomer = customer.get(customerIndex);
        
        if (selectedCustomer.isPremier()) {
            System.out.println(selectedCustomer.getName() + " is a premium member, so treat them well.");
        }

        showTimeShares(timeshare);
        System.out.print("Which time share would you like to rent: ");
        int timeShareIndex = scanner.nextInt() - 1; // Convert to 0-based index
        TimeShare selectedTimeShare = timeshare.get(timeShareIndex);

        System.out.print("How many days do you wish to have this beautiful vacation spot? ");
        int numDays = scanner.nextInt();


        ArrayList<AddOn> selectedAddOns = chooseAddOns(addOn);

        // Create a reservation and add it to the list
        TSReservation newReservation = new TSReservation(selectedCustomer, selectedTimeShare, numDays, selectedAddOns);
        reservations.add(newReservation);

        // Print reservation summary
        printRentalSummary(newReservation, selectedCustomer, selectedTimeShare, selectedAddOns);
        timeshare.remove(timeShareIndex);
    }

    // Method to check in condo
    public static void checkInCondo(ArrayList<TimeShare> timeshare, ArrayList<TSReservation> reservations) {
        Scanner scanner = new Scanner(System.in);

        if (reservations.isEmpty()) {
            System.out.println("There are no rentals yet.\n");
            return;
        }
        
        printRentals(reservations);
        System.out.print("Which rental should be returned? ");
        int returnIndex = scanner.nextInt() - 1; // Convert to 0-based index
        TimeShare newTimeShare = reservations.get(returnIndex).getTimeShare(); 
        timeshare.add(0, newTimeShare); // Add the rental back as an option
        reservations.remove(returnIndex); // Remove the reservation
        
    }

    // Print rental summary
    public static void printRentalSummary(TSReservation reservation, Customer selectedCustomer, TimeShare selectedTimeShare, ArrayList<AddOn> selectedAddOns) {

    	System.out.print(reservation.toString());
        System.out.println("The cost before add ons is: $" + reservation.getRentAmt());
        reservation.costAddOns(); // calculate and display cost of rental 

    }


    public static int expOrNot(ArrayList<AddOn> addOn, int userInput){
    	NumberFormat nf = NumberFormat.getCurrencyInstance();
    	Scanner scanner = new Scanner(System.in);
    	
    	int mult = 1;
    	
        System.out.print("Would you like the expensive option or the not expensive option? (True/False) \nExpensive option = " + 
        nf.format(addOn.get(userInput).getCost() * 2) + " Inexpensive option = " + nf.format(addOn.get(userInput).getCost()));
        
        if (!addOn.get(userInput).isDuration()) {
        	System.out.print(" DAILY");
        }

        boolean choice = scanner.nextBoolean();
        
        
        if (choice == true){
        	mult = 2;
            

        }else
        	mult = 1;
        
        	
        return mult;
    } 

}
