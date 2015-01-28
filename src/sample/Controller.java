package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Random;



public class Controller {
    @FXML private Label numberGuesslable;
    @FXML private TextField txtAddItem;
    @FXML private Label systemOut;
    private String SystemOut;
    private String textIn;
    static Random rand = new Random();
    int theNumber = randomInt(1, 10);
    int numberIn;
    static final int RANDOM_NUMBER_FROM = 1;
    static final int RANDOM_NUMBER_TO = 10;
    int numberGuess = 0;

    public static int randomInt(int from, int to) {
        return rand.nextInt(to - from + 1) + from;
    }

    public int countGuess(int theNumber) {
        numberGuess++;
        int guess = numberIn;
        if (guess < theNumber) {
            systemOut.setText(SystemOut = "Du gissade för lågt");
            numberGuesslable.setText("Guess counter:" + numberGuess);
        } else if (guess > theNumber) {
            systemOut.setText(SystemOut = "Du gissade för högt");
            numberGuesslable.setText("Guess counter:" + numberGuess);
        } else {
            systemOut.setText(SystemOut = "Grattis du gissade RÄTT!");
            numberGuesslable.setText("Guess counter:" + numberGuess);
            return numberGuess;

        }
        return numberGuess;
    }

    @FXML private void getNewNumber(ActionEvent action){
        numberGuess = 0;
        numberGuesslable.setText("Guess counter:" + numberGuess);
        theNumber = randomInt(RANDOM_NUMBER_FROM, RANDOM_NUMBER_TO);

    }

    @FXML private void guess(ActionEvent action){
        textIn = (txtAddItem.getText());
        if(!textIn.matches ("^\\d+$")) {
            systemOut.setText(SystemOut = "Pleas enter a number");
        }else {
            numberIn = Integer.parseInt(textIn);
            if (numberIn < RANDOM_NUMBER_FROM || numberIn > RANDOM_NUMBER_TO){
                systemOut.setText(SystemOut = "Pleas enter a number: " + RANDOM_NUMBER_FROM + "-" + RANDOM_NUMBER_TO);
            }else {
                numberGuess = countGuess(theNumber);


            }
        }
    }
}
