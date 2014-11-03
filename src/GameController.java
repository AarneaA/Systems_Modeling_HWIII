/**
 * @(#) GameController.java
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GameController
{
	private static Player currentPlayer;
	
	public Restaurant restaurant;
	public static ArrayList<Employee> workerList = new ArrayList<Employee>();
	public static ArrayList<Client> clientList = new ArrayList<Client>();
	public static ArrayList<MenuItem> menuItems = new ArrayList<MenuItem>();
	
	public void startGame() throws IOException{
		
		GameController controller = new GameController();
		
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input your name: ");
        String name = br.readLine();
        currentPlayer = new Player();
        currentPlayer.setName(name);

        restaurant = new Restaurant();
        restaurant.setName("Burger Or Not");
        restaurant.setAddress("Burger street 259");
        restaurant.setCity("Burger");
        restaurant.setReputationPoints(15);
		String[] employeeNames = {"Lily", "Andy", "Jim","Billy","Laura"};
		String[] employeeSurnames = {"Woods","Baker","Smith","James","Reeves"};
		
		for (int i = 0; i < 5; i++){
			if(i == 3){
				Barman barman = new Barman(employeeNames[i],employeeSurnames[i], 300, Experience.LOW);
				workerList.add(barman);
			}
			else if(i == 4){
				Chef chef = new Chef(employeeNames[i],employeeSurnames[i], 300, Experience.LOW);
				workerList.add(chef);
			}
			else{
				Waiter waiter = new Waiter(employeeNames[i],employeeSurnames[i], 200, Experience.LOW);
				workerList.add(waiter);
			}
		}

		String[] clientsNames = {"Barry", "Bruce", "Oliver", "Hal", "Clark", "Tony", "John", "Joe", "Bruce", "Dick", "Clint" , "Natalia", "Dinah", "Selina", "James", "Arthur", "Wade", "Slade"};
		String[] clientsSurnames = {"Allen", "Wayne", "Queen", "Jordan", "Kent", "Stark", "Constantine", "Gordon", "Banner", "Grayson" ,"Barton", "Romanova", "Lance", "Kyle", "Rhodes" , "Curry", "Wilson", "Wilson"};
		int[] clientTelephones = {122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139};
		String[] clientTaxCodes = {"IR330","IR331","IR332","IR333","IR334","IR335","IR336","IR337","IR338","IR339","IR340","IR341","IR342","IR343","IR344","IR345","IR346","IR347"};
		
		for (int i = 0; i < 18; i++){
			Map<Dish, Integer> orderedDishes = new HashMap<Dish, Integer>();
			Map<Beverage, Integer> orderedBeverages = new HashMap<Beverage, Integer>();
			Client client = new Client(clientsNames[i],clientsSurnames[i], clientTelephones[i], clientTaxCodes[i],0,0,orderedDishes,orderedBeverages);
			clientList.add(client);
			
		}
		
		String[] dishNames = {"MushroomPizza","Chicken","Potatoes","Soup","Fries"};
		int[] dishCalorieCounts = {3000, 1500, 1000, 2000, 3500};

		for (int i = 0; i < 5; i++){
			Dish dish = new Dish(dishNames[i],6, Quality.LOW, dishCalorieCounts[i]);
			menuItems.add(dish);
		}
		
		String[] beverageNames = {"Water","Juice","Beer","Coffee","Tea"};
		double[] volume = {0.7, 0.5, 0.57, 0.33, 0.33};

		for (int i = 0; i < 5; i++){
			Beverage beverage = new Beverage(beverageNames[i],6, Quality.LOW, volume[i]);
			menuItems.add(beverage);
		}
		
		restaurant.runGame(controller, restaurant);

	}
	
	public ArrayList<Employee> getWorkerList() {
		return workerList;
	}

	public void selectWaitersToTable(ArrayList<Table> tableList) throws IOException
	{
		for(int i = 0; i < 9; i++){
			System.out.println("Waiters and their tables: ");
			for(Employee e: workerList){
				if(e.getClass().equals(Waiter.class)){
					System.out.print(e.getName()  + "(" + e.getExperience()+ ")" +" : ");
					for(Table t: tableList){
						if(t.getWaiterName().equals(e.getName())){
							System.out.print(t.getNumber() + ", ");
						}
					}
				}
				System.out.println();
			}
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        System.out.println("Input the table number which you want assign to a waiter!");
	        String tableNumber= br.readLine();
	        br = new BufferedReader(new InputStreamReader(System.in));
	        System.out.println("Input the waiter name you want to assign to the selected table!");
	        String waiterName = br.readLine();
	        
	        int tableCount = 0;
			for(Table t: tableList){
				if(t.getWaiterName().equals(waiterName)){
					tableCount += 1;
				}
			}
	        if(tableCount >= 3){
	        	System.out.println("Waiter already has 3 tables, select another waiter!");
	        	i -=1;
	        }
	        else{
		        for(Table t: tableList){
					if(t.getNumber() == Integer.parseInt(tableNumber)){
						t.assignToWaiter(waiterName);
					}
				}
	        }
		}

	}
	
	public ArrayList<Client> getClientList() {
		return clientList;
	}

	public static void setClientList(ArrayList<Client> clientList) {
		GameController.clientList = clientList;
	}

	public void setDishesQuality() throws IOException
	{
		System.out.println("Dishes and their qualities: ");
		for(MenuItem m: menuItems){
			if(m.getClass().equals(Dish.class)){
				System.out.println(m.getName() + " : " + m.getQualityLevel());
			}
		}
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter a dish name that quality you want do change!");
        String dish= br.readLine();
		for(MenuItem m: menuItems){
			if(m.getClass().equals(Dish.class)){
				if(m.getName().equals(dish)){
					if(m.getQualityLevel().equals(Quality.LOW)){
						m.setQualityLevel(Quality.HIGH);
					}
					else{
						m.setQualityLevel(Quality.LOW);
					}
				}
			}
		}
		System.out.println("New dish qualities: ");
		for(MenuItem m: menuItems){
			if(m.getClass().equals(Dish.class)){
				System.out.println(m.getName() + " : " + m.getQualityLevel());
			}
		}
	}
	
	public void setBeverageQuality() throws IOException
	{
		System.out.println("Beverages and their qualities: ");
		for(MenuItem m: menuItems){
			if(m.getClass().equals(Beverage.class)){
				System.out.println(m.getName() + " : " + m.getQualityLevel());
			}
		}
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter a beverage name that quality you want do change!");
        String beverage= br.readLine();
		for(MenuItem m: menuItems){
			if(m.getClass().equals(Beverage.class)){
				if(m.getName().equals(beverage)){
					if(m.getQualityLevel().equals(Quality.LOW)){
						m.setQualityLevel(Quality.HIGH);
					}
					else{
						m.setQualityLevel(Quality.LOW);
					}
				}
			}
		}
		System.out.println("New beverage qualities: ");
		for(MenuItem m: menuItems){
			if(m.getClass().equals(Beverage.class)){
				System.out.println(m.getName() + " : " + m.getQualityLevel());
			}
		}
	}
	
	public void setMenuPrices() throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Input low quality dish price: ");
        String lowQualityDishPrice = br.readLine();
        br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Input high quality dish price: ");
        String highQualityDishPrice = br.readLine();
        
		br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Input low quality beverage price: ");
        String lowQualityBeveragePrice = br.readLine();
        br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Input high quality beverage price: ");
        String highQualityBeveragePrice = br.readLine();
		
		for(MenuItem m: menuItems){
			if(m.getClass().equals(Dish.class)){
				if(m.getQualityLevel().equals(Quality.LOW)){
					m.setPrice(Integer.parseInt(lowQualityDishPrice));
				}
				else{
					m.setPrice(Integer.parseInt(highQualityDishPrice));
				}
			}
			else{
				if(m.getQualityLevel().equals(Quality.LOW)){
					m.setPrice(Integer.parseInt(lowQualityBeveragePrice));
				}
				else{
					m.setPrice(Integer.parseInt(highQualityBeveragePrice));
				}
			}
		}
		System.out.println("New prices:");
		for(MenuItem m: menuItems){
			System.out.println(m.getName() + " : "+ m.getPrice());
		}
	}
	
	
}
