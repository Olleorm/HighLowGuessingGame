package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import java.util.Random;

public class Controller {
    @FXML private Label numberGuessLabel;
    @FXML private TextField txtAddItem;
    @FXML private Label systemOut;
    static Random rand = new Random();
    static final int RANDOM_NUMBER_FROM = 1;
    static final int RANDOM_NUMBER_TO = 10;
    int theNumber = randomInt(RANDOM_NUMBER_FROM, RANDOM_NUMBER_TO);
    int guess;

    int numberGuess = 0;

    public static int randomInt(int from, int to) {
        return rand.nextInt(to - from + 1) + from;
    }

    public int countGuess(int theNumber) {
        numberGuess++;
        if (guess < theNumber) {
            systemOut.setText("Too low");
            numberGuessLabel.setText("Guess counter:" + numberGuess);
        } else if (guess > theNumber) {
            systemOut.setText("Too high");
            numberGuessLabel.setText("Guess counter:" + numberGuess);
        } else {
            systemOut.setText("Congratz you were right!");
            numberGuessLabel.setText("Guess counter:" + numberGuess);
            return numberGuess;

        }
        return numberGuess;
    }

    public void guessing(){
        String textIn = (txtAddItem.getText());
        if(!textIn.matches("^\\d+$")) {
            systemOut.setText("Pleas enter a number");
        }else {
            guess = Integer.parseInt(textIn);
            if (guess < RANDOM_NUMBER_FROM || guess > RANDOM_NUMBER_TO){
                systemOut.setText("Pleas enter number: " + RANDOM_NUMBER_FROM + "-" + RANDOM_NUMBER_TO);
            }else {
                numberGuess = countGuess(theNumber);
            }
        }
    }

    @FXML private void getNewNumber(ActionEvent action){
        numberGuess = 0;
        numberGuessLabel.setText("Guess counter:" + numberGuess);
        theNumber = randomInt(RANDOM_NUMBER_FROM, RANDOM_NUMBER_TO);
    }

    @FXML
    public void handleEnterPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            guessing();
        }
    }

    @FXML private void guessButton(ActionEvent action){
        guessing();
    }
}
