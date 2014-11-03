/**
 * @(#) Waiter.java
 */

public class Waiter extends Employee
{
	public Waiter(String name, String surname, int salary, Experience experience) {
		super(name, surname,salary,experience);
	}

	public void computeSalary( )
	{
		if(getExperience().equals(Experience.LOW)){
			this.salary = 200;
		}
		else if(getExperience().equals(Experience.MEDIUM)){
			this.salary = 300;
		}
		else{
			this.salary = 400;
		}
	}
	
	
}
