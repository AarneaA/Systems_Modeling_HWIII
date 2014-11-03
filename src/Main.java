import java.io.IOException;


public class Main {

	public static void main(String[] args) throws IOException {
		GameController controller = new GameController();
		Restaurant restaurant = new Restaurant();
		controller.startGame();
	}

}
