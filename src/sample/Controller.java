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
    String textIn;
    static Random rand = new Random();
    boolean isInt;
    int randomNumberFrom = 1;
    int randomNumberTo = 10;
    int numberGuess = 0;
    int numberIn;
    int theRandomNumber = randomInt(randomNumberFrom, randomNumberTo);

    public static int randomInt(int from, int to) {
        return rand.nextInt(to - from + 1) + from;
    }

    public void countGuess(int numberIn) {
        numberGuess++;
        numberGuessLabel.setText("Guess counter:" + numberGuess);
        if (numberIn < theRandomNumber) {
            systemOut.setText("Too low");
        } else if (numberIn > theRandomNumber) {
            systemOut.setText("Too high");
        } else {
            systemOut.setText("Congratz you were right!");
        }
    }

    public int textInt(TextField textField) {
        textIn = (textField.getText());
        if(!textField.getText().matches("^\\d+$")) {
            systemOut.setText("Pleas enter a number");
            isInt = false;
            return 0;
        }else{
        isInt = true;
        return Integer.parseInt(textField.getText());
        }
    }

    public void guessing(int numberIn){
        if (isInt == true) {
            if (numberIn < randomNumberFrom || numberIn > randomNumberTo) {
                systemOut.setText("Pleas enter number: " + randomNumberFrom + "-" + randomNumberTo);
            } else {
                countGuess(numberIn);
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
                theRandomNumber = randomInt(fromInt, tooInt);
                guessLabel.setText("Guess a number:" + fromInt + "-" + tooInt);
            } else{
                systemOut.setText("The second number must be bigger then the first");
            }
        }
    }

    @FXML public void handleEnterPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            guessing(textInt(txtAddItem));
        }
    }

    @FXML private void guessButton(ActionEvent action){
        guessing(textInt(txtAddItem));
    }
}
