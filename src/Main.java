import java.util.ArrayList;
import java.util.Scanner;
import static java.lang.Character.*;

public class Main {
    public static void main(String[] args) {

        Game game = new Game("movies.txt");
        StringBuilder incorrectLetterOrTitle = new StringBuilder();
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> titleList = game.makeTitleListFromFile();
        int wrongLettersOrTitlesCounter = 0;

        if (!titleList.isEmpty()) {
            System.out.println("Try to guess the movie title");
            String randomTitle = game.randomTitleGenerator(titleList);
            String hiddenTitle = game.hiddenTitleToGuessGenerator(randomTitle);

            while (wrongLettersOrTitlesCounter < 10) {
                System.out.println("You are guessing: " + hiddenTitle);
                System.out.println("Guess a letter or title:");
                String letterOrTitle = scanner.nextLine().toLowerCase();

                while (!isLetter(letterOrTitle.charAt(0))) {
                    System.out.println("It was not a letter, please try to guess a letter");
                    letterOrTitle = scanner.nextLine().toLowerCase();
                }
                if (letterOrTitle.length() == 1) {
                    if (randomTitle.contains(letterOrTitle)) {
                        hiddenTitle = game.hiddenTitleUpdate(randomTitle, letterOrTitle, hiddenTitle);
                    } else {
                        wrongLettersOrTitlesCounter++;
                        incorrectLetterOrTitle.append(letterOrTitle).append(" ");
                    }
                } else if (randomTitle.equals(letterOrTitle)) {
                    System.out.println("You win!! The title was: " + randomTitle);
                    break;
                } else {
                    wrongLettersOrTitlesCounter++;
                    incorrectLetterOrTitle.append(letterOrTitle).append(" ");
                }
                System.out.println("You have guessed (" + wrongLettersOrTitlesCounter + ") wrong letters or titles: " + incorrectLetterOrTitle);

                if (randomTitle.equals(hiddenTitle)) {
                    System.out.println("You win!! The title was: " + randomTitle);
                    break;
                }
            }
            System.out.println("Game over");
        }
    }
}