import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class Game {

    private String fileName;

    Game(String fileName) {
        this.fileName = fileName;
    }

    ArrayList<String> makeTitleListFromFile() {
        ArrayList<String> titleList = new ArrayList<>();
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String title = scanner.nextLine();
                titleList.add(title.toLowerCase());
            }
        } catch (FileNotFoundException exception) {
            System.out.println("Cannot find the file to built the list of titles");
        }
        return titleList;
    }

    String randomTitleGenerator(ArrayList<String> titleList) {
        int randomTitleNumber = (int) (Math.random() * titleList.size());
        return titleList.get(randomTitleNumber);
    }

    String hiddenTitleToGuessGenerator(String randomTitle) {
        StringBuilder hiddenTitleToGuess = new StringBuilder();
        for (int i = 0; i < randomTitle.length(); i++) {
            if (randomTitle.charAt(i) == ' ') {
                hiddenTitleToGuess.append(" ");
            } else hiddenTitleToGuess.append("-");
        }
        return hiddenTitleToGuess.toString();
    }

    String hiddenTitleUpdate(String randomTitle, String letterOrTitle, String hiddenTitle){
        String hiddenTitle1 = hiddenTitle;
        for (int i = 0; i < randomTitle.length(); i++) {
            if (String.valueOf(randomTitle.charAt(i)).equals(letterOrTitle)) {
                if (i > 0 && i < (randomTitle.length() - 1)) {
                    hiddenTitle1 = hiddenTitle.substring(0, (i)) + randomTitle.charAt(i) + hiddenTitle.substring((i + 1), randomTitle.length());
                } else if (i == 0) {
                    hiddenTitle1 = randomTitle.charAt(i) + hiddenTitle.substring(1, randomTitle.length());
                } else if (i == (randomTitle.length() - 1)) {
                    hiddenTitle1 = hiddenTitle.substring(0, i) + randomTitle.charAt(i);
                }
            }
        }
        return hiddenTitle1;
    }
}
