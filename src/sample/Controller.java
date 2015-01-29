package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import java.util.Random;

public class Controller {
    @FXML private Label guessLabel;
    @FXML private Label numberGuessLabel;
    @FXML private TextField txtAddItem;
    @FXML private TextField fromAddItem;
    @FXML private TextField tooAddItem;
    @FXML private Label systemOut;
    static Random rand = new Random();
    int randomNumberFrom = 1;
    int randomNumberTo = 10;
    String textIn;
    int theNumber = randomInt(randomNumberFrom, randomNumberTo);
    int numberIn;
    boolean isInt;
    int numberGuess = 0;

    public static int randomInt(int from, int to) {
        return rand.nextInt(to - from + 1) + from;
    }

    public int countGuess(int theNumber) {
        numberGuess++;
        if (numberIn < theNumber) {
            systemOut.setText("Too low");
            numberGuessLabel.setText("Guess counter:" + numberGuess);
        } else if (numberIn > theNumber) {
            systemOut.setText("Too high");
            numberGuessLabel.setText("Guess counter:" + numberGuess);
        } else {
            systemOut.setText("Congratz you were right!");
            numberGuessLabel.setText("Guess counter:" + numberGuess);
            return numberGuess;
        }
        return numberGuess;
    }

    public int textInt(TextField x) {
        textIn = (x.getText());
        if(!textIn.matches("^\\d+$")) {
            systemOut.setText("Pleas enter a number");
            isInt = false;
            return 0;
        }else{
        isInt = true;
        return Integer.parseInt(textIn);
        }
    }

    public void guessing(){
        numberIn = textInt(txtAddItem);
        if (isInt == true) {
            if (numberIn < randomNumberFrom || numberIn > randomNumberTo) {
                systemOut.setText("Pleas enter number: " + randomNumberFrom + "-" + randomNumberTo);
            } else {
                numberGuess = countGuess(theNumber);
            }
        }
    }

    @FXML private void getNewNumber(ActionEvent action){
        int fromInt = textInt(fromAddItem);
        if (isInt == true) {
            int tooInt = textInt(tooAddItem);
            if (isInt == true && fromInt < tooInt) {
                numberGuess = 0;
                numberGuessLabel.setText("Guess counter:" + numberGuess);
                theNumber = randomInt(fromInt, tooInt);
                guessLabel.setText("Guess a number:" + fromInt + "-" + tooInt);
            } else{
                systemOut.setText("The second number must be bigger then the first");
            }
        }


    }

    @FXML public void handleEnterPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            guessing();
        }
    }

    @FXML private void guessButton(ActionEvent action){
        guessing();
    }
}
