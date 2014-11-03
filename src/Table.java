/**
 * @(#) Table.java
 */

public class Table
{
	private int number;
	
	private String waiterName;
	
	public void assignToWaiter( String waiterName )
	{
		this.waiterName = waiterName;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getWaiterName() {
		return waiterName;
	}

	public void setWaiterName(String waiterName) {
		this.waiterName = waiterName;
	}
	
	
}
