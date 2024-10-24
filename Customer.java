package timesharerentals;

public class Customer {
	
	// the Customer class has three fields
	private int custId;
	private String name;
    private boolean premier;
	
	// the following will be used to automatically generate customer ids
	//so they will be unique
	private static int nextNum = 100;
	
	
	public Customer() {
		
	}
	
	public Customer(String name, boolean premier) {
		this.name = name;
		this.premier = premier;
	}
	
	// toString method
	public String toString()
	{
		String ans = "is a";
		if(!premier)
			ans = " is not a";
		return name + " (custID: " + custId + ") " + ans + " premier member of the club";
	}


	public int getCustId() {
		return custId;
	}


	public void setCustId(int custId) {
		this.custId = custId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public boolean isPremier() {
		return premier;
	}


	public void setPremier(boolean premier) {
		this.premier = premier;
	}


	public static int getNextNum() {
		return nextNum;
	}


	public static void setNextNum(int nextNum) {
		Customer.nextNum = nextNum;
	}
	
	


}
