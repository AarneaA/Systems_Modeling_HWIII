import java.io.*;

/**
 * @(#) RankingList.java
 */

public class RankingList
{
	private Restaurant restaurant;
	
	private Player currentPlayer;
	
	public static void writeScore(Restaurant restaurant, Player currentPlayer){
		
		int budget = restaurant.getBudget();
		String name = currentPlayer.getName();
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader("highscores.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line = null;
        StringBuilder builder = new StringBuilder();
        try {
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        PrintWriter writer = null;
        try {
            try {
                writer = new PrintWriter("highscores.txt", "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        writer.println(builder);
        writer.println("Name : " + name + " Budget: " + budget);
        System.out.println(name);
        System.out.println(budget);
        writer.close();
    }

    public static void readScores(){
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("highscores.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line = null;
        System.out.println("=====HIGH SCOREs=====");
        try {
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("======================");
    }
}
