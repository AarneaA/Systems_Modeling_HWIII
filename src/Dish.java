/**
 * @(#) Dish.java
 */

public class Dish extends MenuItem
{
	private int calorieCount;
	
	public Dish(String name, int price, Quality qualityLevel, int calorieCount) {
		super(name,price,qualityLevel);
		this.calorieCount = calorieCount;
	}
	
	public int computeProductionPrice()
	{
		if(getQualityLevel().equals(Quality.LOW)){
			return 3;
		}
		else{
			return 10;
		}
	}

	public int getCalorieCount() {
		return calorieCount;
	}

	public void setCalorieCount(int calorieCount) {
		this.calorieCount = calorieCount;
	}
	
	
}
