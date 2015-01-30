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
    @FXML private TextField toAddItem;
    @FXML private Label systemOut;
    private static Random rand = new Random();
    private int randomNumberFrom = 1;
    private int randomNumberTo = 10;
    private int numberGuess = 0;
    private int theRandomNumber = randomInt(randomNumberFrom, randomNumberTo);

    public int getTheRandomNumber() {
        return theRandomNumber;
    }

    public void setRandomNumberBounds(int low, int high) {
        this.randomNumberFrom = low;
        this.randomNumberTo = high;
    }

    public int getRandomNumberFrom() {
        return randomNumberFrom;
    }

    public int getRandomNumberTo() {
        return randomNumberTo;
    }

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
        if(!textField.getText().matches("^\\d+$")) {
            systemOut.setText("Pleas enter a number");
            return -1;
        }else{
            return Integer.parseInt(textField.getText());
        }
    }

    public void guessing(int numberIn){
        if (numberIn >= 0) {
            if (numberIn < randomNumberFrom || numberIn > randomNumberTo) {
                systemOut.setText("Pleas enter number: " + randomNumberFrom + "-" + randomNumberTo);
            } else {
                countGuess(numberIn);
            }
        }
    }

    @FXML public void getNewNumber(ActionEvent action){
        int fromInt = textInt(fromAddItem);
        if (fromInt >= 0) {
            int toInt = textInt(toAddItem);
            if (toInt >= 0 && fromInt < toInt) {
                numberGuess = 0;
                numberGuessLabel.setText("Guess counter:" + numberGuess);
                theRandomNumber = randomInt(fromInt, toInt);
                randomNumberFrom = fromInt;
                randomNumberTo = toInt;
                guessLabel.setText("Guess a number:" + fromInt + "-" + toInt);
            } else{
                systemOut.setText("The second number must be bigger then the first");
            }
        }
    }

    @FXML private void handleEnterPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            guessing(textInt(txtAddItem));
        }
    }

    @FXML private void guessButton(ActionEvent action){
        guessing(textInt(txtAddItem));
    }
}
