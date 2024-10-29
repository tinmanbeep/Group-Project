package timesharerentals;

import java.util.*;
import java.text.*;

import timesharerentals.AddOn;
import timesharerentals.Customer;
import timesharerentals.TimeShare;
import timesharerentals.TSReservation;

public class TimeShareDriver {
    private static ArrayList<TSReservation> reservations = new ArrayList<>(); // Store reservations

    public static void main(String[] args) {
        ArrayList<TimeShare> ts = new ArrayList<>();
        ArrayList<Customer> cus = new ArrayList<>();
        ArrayList<AddOn> addOn = new ArrayList<>();
        
        loadNewData(ts, cus, addOn);
        
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Would you like to (1) rent a condo (2) check a condo back in (3) quit");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    rentCondo(scanner, ts, cus, addOn);
                    break;
                case 2:
                    checkInCondo();
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }

    // Method to rent a condo
    public static void rentCondo(Scanner scanner, ArrayList<TimeShare> ts, ArrayList<Customer> cus, ArrayList<AddOn> addOn) {
        showCust(cus);
        System.out.print("Please select a customer: ");
        int customerIndex = scanner.nextInt() - 1; // Convert to 0-based index
        Customer selectedCustomer = cus.get(customerIndex);
        
        if (selectedCustomer.isPremier()) {
            System.out.println("This customer is a premium member, so treat them well.");
        }

        showTimeShares(ts);
        System.out.print("Which time share would you like to rent: ");
        int timeShareIndex = scanner.nextInt() - 1; // Convert to 0-based index
        TimeShare selectedTimeShare = ts.get(timeShareIndex);

        System.out.print("How many days do you wish to have this beautiful vacation spot? ");
        int numDays = scanner.nextInt();

        ArrayList<AddOn> selectedAddOns = chooseAddOns(scanner, addOn);

        // Create a reservation and add it to the list
        TSReservation reservation = new TSReservation(selectedCustomer, selectedTimeShare, numDays, selectedAddOns);
        reservations.add(reservation);

        // Print reservation summary
        printRentalSummary(reservation, selectedAddOns);
    }

    // Method to check in condo
    public static void checkInCondo() {
        if (reservations.isEmpty()) {
            System.out.println("There are no rentals yet.");
            return;
        }

        printRentals(reservations);
        Scanner scanner = new Scanner(System.in);
        System.out.print("Which rental should be returned? ");
        int returnIndex = scanner.nextInt() - 1; // Convert to 0-based index
        reservations.remove(returnIndex); // Remove the reservation
    }

    // Method to load the initial data
    public static void loadNewData(ArrayList<TimeShare> a, ArrayList<Customer> c, ArrayList<AddOn> addOn) {
        a.add(new TimeShare("Phoenix-1", 2, 180.00));
        a.add(new TimeShare("San Diego-1", 1, 235.00));
        a.add(new TimeShare("San Francisco", 1, 450.00));
        a.add(new TimeShare("Virginia Beach-1", 3, 145.00));

        c.add(new Customer("Mike Trout", false,100));
        c.add(new Customer("Shohei Ohtani", true,101));
        c.add(new Customer("Corey Seager", true,102));
        c.add(new Customer("Lainey Wilson", true,103));
        c.add(new Customer("Taylor Swift", false,104));

        addOn.add(new AddOn("Internet", 5.95, false));
        addOn.add(new AddOn("Best view", 90.00, false));
        addOn.add(new AddOn("Extra cleaning", 45.00, true));
        addOn.add(new AddOn("Extra Bed", 25.00, true));
        addOn.add(new AddOn("Meal plan", 120.00, false));
    }

    // Show customers
    public static void showCust(ArrayList<Customer> cus) {
        System.out.println("Customers:");
        for (int i = 0; i < cus.size(); i++) {
            System.out.println((i + 1) + " " + cus.get(i));
        }
    }

    // Show time shares
    public static void showTimeShares(ArrayList<TimeShare> ts) {
        System.out.println("The following time share locations are available to rent:");
        for (int i = 0; i < ts.size(); i++) {
            System.out.println((i + 1) + " " + ts.get(i));
        }
    }

    // Show add-ons
    public static void showAddOns(ArrayList<AddOn> a) {
        System.out.println("Add ons:");
        for (int i = 0; i < a.size(); i++) {
            System.out.println((i + 1) + " " + a.get(i));
        }
    }

    // Choose add-ons
    public static ArrayList<AddOn> chooseAddOns(Scanner scanner, ArrayList<AddOn> a) {
        ArrayList<AddOn> selectedAddOns = new ArrayList<>();
        showAddOns(a);
        boolean moreAddOns = true;

        while (moreAddOns) {
            System.out.print("Would you like one of these add ons? (true/false) ");
            boolean wantAddOn = scanner.nextBoolean();
            if (wantAddOn) {
                System.out.print("Which number? ");
                int addOnIndex = scanner.nextInt() - 1; // Convert to 0-based index
                selectedAddOns.add(a.get(addOnIndex));
            }

            System.out.print("More add ons? (true/false) ");
            moreAddOns = scanner.nextBoolean();
        }

        return selectedAddOns;
    }

    // Print rental summary
    public static void printRentalSummary(TSReservation reservation, ArrayList<AddOn> selectedAddOns) {
        System.out.println(reservation.toString());
        System.out.println("The following add ones were reserved:");
        for (AddOn addOn : selectedAddOns) {
            System.out.println(addOn.toString());
        }
        System.out.println("The cost before add ons is: $" + reservation.getRentAmt());
        double addOnCost = 0;
        for (AddOn addOn : selectedAddOns) {
            if (addOn.isDuration()) {
                addOnCost += addOn.getCost();
            } else {
                addOnCost += addOn.getCost() * reservation.getNumDays();
            }
        }
        System.out.println("The cost of the add ons is: $" + addOnCost);
        System.out.println("The total cost is: $" + (reservation.getRentAmt() + addOnCost));
    }

    // Print rentals
    public static void printRentals(ArrayList<TSReservation> res) {
        System.out.println("Rental Summary");
        for (int i = 0; i < res.size(); i++) {
            System.out.println((i + 1) + " " + res.get(i).toString());
        }
    }
}

