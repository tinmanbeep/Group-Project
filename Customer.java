package timesharerentals;

public class Customer {
	
	
	private int custId;
	private String name;
   	private boolean premier;
	
	
	
	public Customer() {
		
	}
	
	public Customer(String name, boolean premier,int custId) {
		this.name = name;
		this.premier = premier;
		this.custId = custId;
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
	
	public static int getCustId() {
		return custId;
	}
	
	public static void setCustId(int custId) {
		this.custId = custId;
	}

}
