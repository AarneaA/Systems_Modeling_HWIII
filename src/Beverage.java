/**
 * @(#) Beverage.java
 */

public class Beverage extends MenuItem
{
	private double volume;
	
	public Beverage(String name, int price, Quality qualityLevel, double volume) {
		super(name,price,qualityLevel);
		this.volume = volume;
	}
	
	public int computeProductionPrice()
	{
		if(getQualityLevel().equals(Quality.LOW)){
			return 1;
		}
		else{
			return 3;
		}
	}

	public double getVolume() {
		return volume;
	}

	public void setVolume(double volume) {
		this.volume = volume;
	}
}
