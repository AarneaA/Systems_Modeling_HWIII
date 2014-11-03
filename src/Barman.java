/**
 * @(#) Barman.java
 */

public class Barman extends Employee
{
	public Barman(String name, String surname, int salary, Experience experience) {
		super(name, surname,salary,experience);
	}

	public void computeSalary( )
	{
		if(getExperience().equals(Experience.LOW)){
			this.salary =  300;
		}
		else if(getExperience().equals(Experience.MEDIUM)){
			this.salary =  400;
		}
		else{
			this.salary =  500;
		}
	}
	
	
}
