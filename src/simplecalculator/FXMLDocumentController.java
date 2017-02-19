/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simplecalculator;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

/**
 *
 * @author edgea
 */
public class FXMLDocumentController implements Initializable {
    @FXML private Label mainDisplay;
    @FXML private Label secondDisplay;
    @FXML private Label operationText;
    
    boolean positive = true;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void digit(ActionEvent event) {
        if (mainDisplay.getText().equals("0")) {
            mainDisplay.setText(((Button)event.getSource()).getText());
        }
        else {
            mainDisplay.setText(mainDisplay.getText() + ((Button)event.getSource()).getText());
        }
    }
    
    @FXML
    private void flipSign() {
        String response = String.valueOf(-Double.parseDouble(mainDisplay.getText()));
        if (response.endsWith(".0"))
            response = response.substring(0, response.length() - 2);
        mainDisplay.setText(response);
    }
    
    @FXML
    private void clearLine() {
        mainDisplay.setText("0");
    }
    
    @FXML
    private void clearAllDisplays() {
        mainDisplay.setText("0");
        secondDisplay.setText("0");
        operationText.setText("+");
    }
    
    @FXML
    private void evaluate() {
        double answer = 0d;
        if (operationText.getText().equals("/")) {
            answer = Double.parseDouble(secondDisplay.getText()) / Double.parseDouble(mainDisplay.getText());
        }
        else if (operationText.getText().equals("*")) {
            answer = Double.parseDouble(secondDisplay.getText()) * Double.parseDouble(mainDisplay.getText());
        }
        else if (operationText.getText().equals("-")) {
            answer = Double.parseDouble(secondDisplay.getText()) - Double.parseDouble(mainDisplay.getText());
        }
        else {
            answer = Double.parseDouble(secondDisplay.getText()) + Double.parseDouble(mainDisplay.getText());
        }
        
        String response = String.valueOf(answer);
        if (response.endsWith(".0"))
            response = response.substring(0, response.length() - 2);
        mainDisplay.setText(response);
        secondDisplay.setText("");
        operationText.setText("");
    }
    
    @FXML
    private void operation(ActionEvent event) {
        if (operationText.getText().equals("")) {
            secondDisplay.setText(mainDisplay.getText());
            operationText.setText(((Button)event.getSource()).getText());
            mainDisplay.setText("0");
        }
        
        if (!mainDisplay.getText().equals("0")) {
            this.evaluate();
            System.out.println("Evaluating through operation");
            secondDisplay.setText(mainDisplay.getText());
            mainDisplay.setText("0");
        }
        operationText.setText(((Button)event.getSource()).getText());
    }
    
    @FXML
    private void decimal() {
        char[] dispCharArray = mainDisplay.getText().toCharArray();
        boolean check = false;
        for (int i = 0; i < dispCharArray.length; i++) {
            if (dispCharArray[i] == '.') {
                check = true;
                break;
            }
        }
        if (!check)
            mainDisplay.setText(mainDisplay.getText().concat("."));
    }
    
}
