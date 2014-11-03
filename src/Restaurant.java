import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

/**
 * @(#) Restaurant.java
 */

public class Restaurant
{
	private String name;
	
	private String address;
	
	private String city;
	
	private int budget = 10000;
	
	private int reputationPoints = 15;
	
	private int ingrediantsCost = 0;
	
	private Table table;
	
	private Dish orderedDish;
	
	private Beverage orderedBeverage;
	
	private Waiter currentWaiter;
	
	private Barman currentBarman;
	
	private Chef currentChef;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getBudget() {
		return budget;
	}

	public void setBudget(int budget) {
		this.budget = budget;
	}

	public int getReputationPoints() {
		return reputationPoints;
	}

	public void setReputationPoints(int reputationPoints) {
		this.reputationPoints = reputationPoints;
	}

	public int getIngrediantsCost() {
		return ingrediantsCost;
	}

	public void setIngrediantsCost(int ingrediantsCost) {
		this.ingrediantsCost = ingrediantsCost;
	}

	public int getDaysOpen() {
		return daysOpen;
	}

	public void setDaysOpen(int daysOpen) {
		this.daysOpen = daysOpen;
	}

	private int daysOpen = 1;
	
	public void paySuppliers()
	{
		budget -= ingrediantsCost;
	}
	
	public void computeReputation( int points )
	{
		reputationPoints += points;
		if(points < 0){
			reputationPoints = 0;
		}
	}
	
	public void payUtilities()
	{
		budget -= 4000;
	}
	
	public void paySalaries(ArrayList<Employee> employeeList )
	{
		int salaries = 0;
		for(Employee e: employeeList){
			salaries += e.getSalary();
		}
		budget -= salaries;
	}
	
	public void populateTables(ArrayList<Client> clientList)
	{
		ArrayList<Client> SeatedClients = new ArrayList<Client>();
		if(reputationPoints >= 30){
			 for(Client c: clientList){
				 SeatedClients.add(c);
			 }	
		}
		else if(reputationPoints >= 15){
			for(int i = 0; i < 10 ; i++){
				SeatedClients.add(clientList.get(i));
			}
		}
		else{
			for(int i = 0; i < 4 ; i++){
				SeatedClients.add(clientList.get(i));
			}
		}
		int tableNumber = 2;
		for(Client c: SeatedClients){
			c.setTableNumber((int)Math.ceil(tableNumber/2));
			tableNumber += 1;
		}
	}
	
	public void computeClientStatistics(ArrayList<Client> clientList)
	{
		for(Client c: clientList){
			int calorieCount = 0;
			int numberOfDishes = 0;
			System.out.println("The client name: "+ c.getName() + " "+ c.getSurname());
			System.out.println("The consumed dishes:");
			for(Dish key: c.orderedDishes.keySet()){
				System.out.println(key.getName() + ": " + c.orderedDishes.get(key));
				numberOfDishes += c.orderedDishes.get(key);
				calorieCount += c.orderedDishes.get(key) * key.getCalorieCount();
			}
			if(numberOfDishes != 0){
				System.out.println("Average calorie count: " + calorieCount/numberOfDishes);
			}
			double volume = 0;
			int numberOfBeverage = 0;
			System.out.println("The consumed beverages: ");
			for(Beverage key: c.orderedBeverages.keySet()){
				System.out.println(key.getName() + ": " + c.orderedBeverages.get(key));
				numberOfBeverage += c.orderedBeverages.get(key);
				volume += c.orderedBeverages.get(key) * key.getVolume();
			}
			if(numberOfBeverage !=0){
				System.out.println("Average volume count: " + volume/numberOfBeverage);
			}
			System.out.println("Total money spent in the restaurant: " + c.getTotalMoneySpent());
		}
	}
	
	public void payTraining( int amount )
	{
		setBudget(getBudget()-amount);
	}
	
	public void processOrder( Dish dish, Beverage beverage, Client client, Waiter waiter,Chef chef, Barman barman )
	{
		ingrediantsCost += dish.computeProductionPrice();
		ingrediantsCost += beverage.computeProductionPrice();
		int repPoints = client.isSatisfied(dish,beverage,waiter, chef, barman);
		budget += dish.getPrice();
		budget += beverage.getPrice();
		computeReputation(repPoints);
	}
	
	public int runGame(GameController controller, Restaurant restaurant ) throws IOException
	{
		
		if (getBudget() <= 0){
			endGame(controller);
            return 0;
		}
		if(getDaysOpen() == 31){
			payUtilities();
            RankingList.writeScore(restaurant, controller.currentPlayer);
			endGame(controller);
            return 0;

        }
		if(getDaysOpen() % 7 == 0){
			paySuppliers();
			paySalaries(controller.getWorkerList());
		}
		
		if (getBudget() <= 0){
			endGame(controller);
            return 0;
        }
		
		else{
			System.out.println("Day : " + daysOpen + ", budget: " + budget+ ", reputation Points: "+ reputationPoints);
			String dishAnswer = "y";
			while(dishAnswer.equals("y")){
		        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		        System.out.println("Do you want change dish quality(y/n)?");
		        dishAnswer = br.readLine();
		        if(dishAnswer.equals("y")){
		        	controller.setDishesQuality();
		        }
		        else{
		        	dishAnswer = "n";
		        }
			}
			
			String beverageAnswer = "y";
			while(beverageAnswer.equals("y")){
		        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		        System.out.println("Do you want change beverage quality(y/n)?");
		        beverageAnswer = br.readLine();
		        if(beverageAnswer.equals("y")){
		        	controller.setBeverageQuality();
		        }
		        else{
		        	dishAnswer = "n";
		        }
			}
			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        System.out.println("Do you want change dishes and beverage prices(y/n)?");
	        String priceAnswer = br.readLine();
	        if(priceAnswer.equals("y")){
	        	controller.setMenuPrices();
	        }
	        if(budget < 800){
	        	System.out.println("You can not train any employees. Not sufficient money in budget!");
	        }
	        else{
	        	String trainAnswer = "y";
		        while(trainAnswer.equals("y")){
			        br = new BufferedReader(new InputStreamReader(System.in));
			        System.out.println("Do you want to train an employee(y/n)?");
			        trainAnswer = br.readLine();
			        if(trainAnswer.equals("y")){
				        if(budget < 800){
				        	System.out.println("You can not train any employees. Not sufficient money in budget!");
				        	break;
				        }
			        	if(budget < 1200){
				        	System.out.println("Employees and their experience levels(only waiters, not sufficent money to train others): ");
				        	for(Employee e : controller.getWorkerList()){
				        		if(e.getClass().equals(Waiter.class)){
				        			System.out.println(e.getName() + " : " + e.getExperience());
				        		}
				        	}
			        	}
			        	else{
			        		System.out.println("Employees and their experience levels: ");
				        	for(Employee e : controller.getWorkerList()){
				        		System.out.println(e.getName() + " : " + e.getExperience());
				        	}
			        	}

			        	br = new BufferedReader(new InputStreamReader(System.in));
				        System.out.println("\nEnter the emplyee�s name, you want to train(HIGH is max): ");
				        String employeeName = br.readLine();
			        	for(Employee e : controller.getWorkerList()){
					        if(e.getName().equals(employeeName)){
					        	if(e.getClass().equals(Waiter.class)){
					        		((Waiter) e).computeSalary();
					        		e.train(800, restaurant);
					        	}
					        	else{
						        	if(e.getClass().equals(Barman.class)){
						        		((Barman) e).computeSalary();
						        		e.train(1200, restaurant);
						        	}
						        	else{
						        		((Chef) e).computeSalary();
						        		e.train(1200, restaurant);
						        	}
					        	}
					        	break;
					        }
			        	}
			        	
			        	System.out.println("Employees and their new experience levels:");
			        	for(Employee e : controller.getWorkerList()){
			        		System.out.println(e.getName() + " : " + e.getExperience());
			        	}
			        }
			        else{
			        	trainAnswer = "n";
			        }
		        }
	        }
		}
		ArrayList<Table> tableList = new ArrayList<Table>();
		for(int i = 0; i < 9; i++){
			table = new Table();
			table.setNumber(i);
			Waiter waiter = new Waiter("", "", 0, null);
			table.setWaiter(waiter);
			tableList.add(table);
		}
		populateTables(controller.getClientList());
        controller.selectWaitersToTable(tableList);
        int rand = 0;
		for(Client c : GameController.clientList){

			if(c.getTableNumber() != 0){
				Random rn = new Random();
				rand = rn.nextInt(5);
				orderedDish = (Dish) GameController.menuItems.get(rand);
				rn = new Random();
				rand = rn.nextInt(5)+5;
				orderedBeverage = (Beverage) GameController.menuItems.get(rand);
				String waiterName = "";
				for(Table t: tableList){
					if(t.getNumber() == c.getTableNumber()){
						waiterName = t.getWaiter().getName();
						break;
					}
				}
				currentWaiter = null;
				currentChef = null;
				currentBarman = null;
	        	for(Employee e : controller.getWorkerList()){
			        if(e.getName().equals(waiterName)){
			        	currentWaiter = (Waiter) e;
			        }
			        else if(e.getClass().equals(Barman.class)){
			        	currentBarman = (Barman) e;
			        }
			        else if(e.getClass().equals(Chef.class)){
			        	currentChef = (Chef) e;
			        }       
	        	}
				processOrder(orderedDish, orderedBeverage,c, currentWaiter, currentChef, currentBarman);
			}
		}	
		daysOpen += 1;
		System.out.println(ingrediantsCost);
		runGame(controller, restaurant);
        return 1;
	}

	public void endGame(GameController controller )
	{
		System.out.println("Game over!");
		System.out.println("Budget: " + budget);
		System.out.println("Statistics: ");
		computeClientStatistics(controller.getClientList());

        RankingList.readScores();
	}
	
	
}
