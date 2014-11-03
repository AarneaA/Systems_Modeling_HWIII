/**
 * @(#) MenuItem.java
 */

public class MenuItem
{
	private String name;
	
	private int price;
	
	private Quality qualityLevel;
	
	public MenuItem(String name, int price, Quality qualityLevel) {
		super();
		this.name = name;
		this.price = price;
		this.qualityLevel = qualityLevel;
	}
	
	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Quality getQualityLevel() {
		return qualityLevel;
	}

	public void setQualityLevel(Quality qualityLevel) {
		this.qualityLevel = qualityLevel;
	}
	
	
}
