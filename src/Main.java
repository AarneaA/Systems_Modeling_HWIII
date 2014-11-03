import java.io.IOException;


public class Main {

	private static GameController controller;
	
	public static void main(String[] args) throws IOException {
		controller = new GameController();
		controller.startGame();
	}
}
