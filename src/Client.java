import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @(#) Client.java
 */

public class Client
{
	private String name;
	
	private String surname;
	
	private int telephone;
	
	private String taxCode;
	
	private int tableNumber;
	
	private int totalMoneySpent = 0;
	
	Map<Dish, Integer> orderedDishes = new HashMap<Dish, Integer>();
	Map<Beverage, Integer> orderedBeverages = new HashMap<Beverage, Integer>();
	

	public Client(String name, String surname, int telephone,
			String taxCode, int tableNumber, int totalMoneySpent,
			Map<Dish, Integer> orderedDishes,
			Map<Beverage, Integer> orderedBeverages) {
		super();
		this.name = name;
		this.surname = surname;
		this.telephone = telephone;
		this.taxCode = taxCode;
		this.tableNumber = tableNumber;
		this.totalMoneySpent = totalMoneySpent;
		this.orderedDishes = orderedDishes;
		this.orderedBeverages = orderedBeverages;
	}

	public int isSatisfied( Dish dish, Beverage beverage, Waiter waiter, Chef chef, Barman barman  )
	{
		if(!orderedDishes.containsKey(dish)){
			orderedDishes.put(dish, 1);
		}
		else{
			orderedDishes.put(dish, orderedDishes.get(dish)+1);
		}
		
		if(!orderedBeverages.containsKey(beverage)){
			orderedBeverages.put(beverage, 1);
		}
		else{
			orderedBeverages.put(beverage, orderedBeverages.get(beverage)+1);
		}
		
		totalMoneySpent += dish.getPrice();
		totalMoneySpent += beverage.getPrice();
		
		int reputationCount = 0;

		int dishProductionPrice = dish.computeProductionPrice();
		int beverageProductionPrice = beverage.computeProductionPrice();
		int dishPriceDifference = (int) Math.floor((dish.getPrice() - dishProductionPrice)/3);
		int beveragePriceDifference = (int) Math.floor((beverage.getPrice() - beverageProductionPrice)/3);
		int dropSatisfactionLevel = dishPriceDifference + beveragePriceDifference;

		int[] randomValues =  {0,0,0,0,0,0,0,0,0,0};
        int randomInt = 0;
		Random rn = new Random();
		randomInt = rn.nextInt(10);

		int percentage = 0;
		if (waiter.getExperience().equals(Experience.HIGH)){
			percentage = 9 - dropSatisfactionLevel;
		}
		else if (waiter.getExperience().equals(Experience.MEDIUM)){
			percentage = 8 - dropSatisfactionLevel;
		}
		else{
			percentage = 6 - dropSatisfactionLevel;
		}
		for(int i = 0; i < percentage; i++){
			randomValues[i] = 1;
		}
		if(randomValues[randomInt] == 1){
			reputationCount += 1;
		}
		else{
			reputationCount -= 1;
		}
		
		int[] randomValues2 =  {0,0,0,0,0,0,0,0,0,0};
        randomInt = 0;
		rn = new Random();
		randomInt = rn.nextInt(10);
		percentage = 0;
		if(dish.getQualityLevel().equals(Quality.HIGH)){
			percentage = 2;
		}
		if (chef.getExperience().equals(Experience.HIGH)){
			percentage = 8 - dropSatisfactionLevel;
		}
		else if (chef.getExperience().equals(Experience.MEDIUM)){
			percentage = 6 - dropSatisfactionLevel;
		}
		else{
			percentage = 4 - dropSatisfactionLevel;
		}
		for(int i = 0; i < percentage; i++){
			randomValues2[i] = 1;
		}
		if(randomValues2[randomInt] == 1){
			reputationCount += 1;
		}
		else{
			reputationCount -= 1;
		}
		
		int[] randomValues3 =  {0,0,0,0,0,0,0,0,0,0};
        randomInt = 0;
		rn = new Random();
		randomInt = rn.nextInt(10);
		percentage = 0;
		if(dish.getQualityLevel().equals(Quality.HIGH)){
			percentage = 2;
		}
		if (barman.getExperience().equals(Experience.HIGH)){
			percentage = 8 - dropSatisfactionLevel;
		}
		else if (barman.getExperience().equals(Experience.MEDIUM)){
			percentage = 6 - dropSatisfactionLevel;
		}
		else{
			percentage = 4 - dropSatisfactionLevel;
		}
		for(int i = 0; i < percentage; i++){
			randomValues3[i] = 1;
		}
		if(randomValues3[randomInt] == 1){
			reputationCount += 1;
		}
		else{
			reputationCount -= 1;
		}
		
		return reputationCount;
		
	}
	
	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTableNumber() {
		return tableNumber;
	}

	public void setTableNumber(int tableNumber) {
		this.tableNumber = tableNumber;
	}

	public int getTotalMoneySpent() {
		return totalMoneySpent;
	}

	public void setTotalMoneySpent(int totalMoneySpent) {
		this.totalMoneySpent = totalMoneySpent;
	}

	public Map<Dish, Integer> getOrderedDishes() {
		return orderedDishes;
	}

	public void setOrderedDishes(Map<Dish, Integer> orderedDishes) {
		this.orderedDishes = orderedDishes;
	}

	public Map<Beverage, Integer> getOrderedBeverages() {
		return orderedBeverages;
	}

	public void setOrderedBeverages(Map<Beverage, Integer> orderedBeverages) {
		this.orderedBeverages = orderedBeverages;
	}
}
