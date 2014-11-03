/**
 * @(#) Employee.java
 */

public class Employee
{
	private String name;
	
	private String surname;
	
	public int salary;
	
	private Experience experience;
	
	public Employee(String name, String surname, int salary, Experience experience) {
		super();
		this.name = name;
		this.surname = surname;
		this.salary = salary;
		this.experience = experience;
	}

	public void increaseExperience( )
	{
		if(experience.equals(Experience.LOW)){
			setExperience(Experience.MEDIUM);
		}
		else if(experience.equals(Experience.MEDIUM)){
			setExperience(Experience.HIGH);
		}
	}

	public Experience getExperience() {
		return experience;
	}

	public void setExperience(Experience experience) {
		this.experience = experience;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void train(int amount, Restaurant restaurant )
	{
		increaseExperience();
		restaurant.payTraining(amount);
		
	}
	
}
