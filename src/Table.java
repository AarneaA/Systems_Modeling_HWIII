/**
 * @(#) Table.java
 */

public class Table
{
	private int number;
	
	private Waiter assignedWaiter;
	
	public void assignToWaiter( Waiter assignedWaiter )
	{
		this.assignedWaiter = assignedWaiter;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Waiter getWaiter() {
		return assignedWaiter;
	}
	
	public void setWaiter( Waiter assignedWaiter )
	{
		this.assignedWaiter = assignedWaiter;
	}
	
}
