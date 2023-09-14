import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomTextArea extends TextArea {

        List<String> wordsList = new ArrayList<>();
        int whatWordIsBeingChecked = 0;
        List<Character> userInput = new ArrayList<>();
        List<Character> correctInput = new ArrayList<>();
        List<Character> textToType = new ArrayList<>();

        TextFlow textFlow = new TextFlow();
        boolean tabPressed = false;

        public CustomTextArea() {

            textFlow.setMaxWidth(400);
            textFlow.setPrefHeight(250);
            textFlow.setStyle("-fx-font-family: Arial;-fx-font-size: 20px ;-fx-control-inner-background: #363636; -fx-text-fill: grey; -fx-border-width: 5px; -fx-border-color: #af8f25");

            BorderStroke borderStroke = new BorderStroke(Color.BLACK,
                    BorderStrokeStyle.SOLID, null, new BorderWidths(1));
            Border border = new Border(borderStroke);
            textFlow.setBorder(border);

            setVisible(true);
            setManaged(true);

            addEventFilter(KeyEvent.KEY_TYPED, event -> {
                String typedText = event.getCharacter();
                char input = typedText.charAt(0);
                if (input == ' '){
                    whatWordIsBeingChecked +=1;

                    userInput.add(' ');

                }
                if (Character.isLetterOrDigit(input)){
                    userInput.add(input);
                    correctInput.add(textToType.remove(0));

                    updateTextFlow();
                }


            });

            addEventFilter(KeyEvent.KEY_PRESSED, event -> {
                if (event.getCode() == KeyCode.BACK_SPACE) {
                    if (getCaretPosition() > 0 & (getText().charAt(getCaretPosition()-1) != ' ')) {
                        deleteText(getCaretPosition() - 1, getCaretPosition());
                        userInput.remove(userInput.size()-1);
                        textToType.add(0, correctInput.remove(correctInput.size() - 1));
                        updateTextFlow();
                    }
                    event.consume();
                }
                if (event.getCode() == KeyCode.ENTER) {
                    event.consume();
                }
                if (event.getCode() == KeyCode.TAB) {
                    event.consume();
                    tabPressed = true;
                }
            });

            addEventFilter(KeyEvent.KEY_RELEASED, keyEvent -> {
                if (keyEvent.getCode() == KeyCode.ENTER && tabPressed) {
                    System.out.println("Tab+Enter pressed");
                    tabPressed = false;
                } else {
                    tabPressed = false;
                }
            });

        }

        public void setNewText(String text){
            String[] words = text.split("\\s+");
            wordsList.clear();
            textToType.clear();
            wordsList.addAll(Arrays.asList(words));
            for (String s : wordsList){
                for(char c : s.toCharArray()){
                    textToType.add(c);
                }
            }
            updateTextFlow();
            positionCaret(0);
        }

        private void updateTextFlow() {
            textFlow.getChildren().clear();

            String text = "";

            for (String str : wordsList) text += str + ' ';

            for (int i = 0; i < text.length(); i++) {
                char c = text.charAt(i);
                Text letter = new Text(String.valueOf(c));
                if (i < userInput.size()) {
                    if (userInput.get(i) != correctInput.get(i)) {
                        letter.setFill(Color.INDIANRED);
                    } else if (userInput.get(i) == correctInput.get(i)) {
                        letter.setFill(Color.LIGHTGREEN);
                    } else if (userInput.get(i) != correctInput.get(i) & userInput.size() > correctInput.size()) {
                        letter.setFill(Color.LIGHTYELLOW);
                    }
                } else {
                    letter.setFill(Color.SLATEGRAY);
                }
                textFlow.getChildren().add(letter);
            }
        }
        public TextFlow getTextFlow() {
            return textFlow;
        }
    }

    /*List<String> wordsList = new ArrayList<>();
    int whatWordIsBeingChecked = 0;

    List<List<Character>> listOfGivenChars = new ArrayList<>();
    List<List<Character>> listOfPastInputChars = new ArrayList<>();

    List<Character> userInput = new ArrayList<>();
    List<Character> correctInput = new ArrayList<>();

    TextFlow textFlow = new TextFlow();
    boolean tabPressed = false;


    public CustomTextArea() {


        textFlow.setMaxWidth(400);
        textFlow.setPrefHeight(250);
        textFlow.setStyle("-fx-font-family: Arial;-fx-font-size: 20px ;-fx-control-inner-background: #363636; -fx-text-fill: grey; -fx-border-width: 5px; -fx-border-color: #af8f25");


        BorderStroke borderStroke = new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, null, new BorderWidths(1));
        Border border = new Border(borderStroke);
        textFlow.setBorder(border);

        setVisible(true);
        setManaged(true);


        addEventFilter(KeyEvent.KEY_TYPED, event -> {
            String typedText = event.getCharacter();
            char input = typedText.charAt(0);
            if (event.getCode() == KeyCode.BACK_SPACE){
                event.consume();
            }
            if (input == ' ') {
                whatWordIsBeingChecked += 1;
                clear();
                listOfPastInputChars.add(userInput);
                userInput.clear();
                correctInput = listOfGivenChars.get(whatWordIsBeingChecked);
            } else {
                System.out.println(userInput);
                userInput.add(input);
                System.out.println(userInput);
            }
            updateTextFlow();
        });

        addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.BACK_SPACE) {
                userInput.remove(userInput.size() - 1);
                updateTextFlow();
                event.consume();

                *//*if (getCaretPosition() > 0) {
                    deleteText(getCaretPosition() - 1, getCaretPosition());
                    int userInputSize = userInput.size();
                    if (userInputSize > 0) {
                        userInput.remove(userInputSize - 1);
                    }
                    updateTextFlow();
                }
                event.consume();*//*
            }
            if (event.getCode() == KeyCode.ENTER) {
                event.consume();
            }
            if (event.getCode() == KeyCode.TAB) {
                event.consume();
                tabPressed = true;
            }
        });

        addEventFilter(KeyEvent.KEY_RELEASED, keyEvent -> {
            if (keyEvent.getCode() == KeyCode.BACK_SPACE){
                keyEvent.consume();
            }
            if (keyEvent.getCode() == KeyCode.ENTER && tabPressed) {
                System.out.println("Tab+Enter pressed");
                tabPressed = false;
            } else {
                tabPressed = false;
            }
        });

    }

    public void setNewText(String text) {
        String[] words = text.split("\\s+");

        whatWordIsBeingChecked = 0;
        wordsList.clear();
        listOfGivenChars.clear();
        correctInput.clear();
        wordsList.addAll(Arrays.asList(words));

        for (String s : wordsList) {
            List<Character> word = new ArrayList<>();
            for (char c : s.toCharArray()) {
                word.add(c);
            }
            listOfGivenChars.add(word);
        }
        correctInput = listOfGivenChars.get(whatWordIsBeingChecked);
        updateTextFlow();
        positionCaret(0);
    }

    private void updateTextFlow() {
        textFlow.getChildren().clear();

        for (int i = 0; i < listOfPastInputChars.size(); i++) {
            List<Character> user = listOfPastInputChars.get(i);
            List<Character> correct = listOfGivenChars.get(i);

            if (user.size() > correct.size()) {
                for (int j = 0; j < user.size(); j++) {
                    Text letter = new Text(String.valueOf(correct.get(j)));
                    try {
                        if (user.get(j) == correct.get(j)) {
                            letter.setFill(Color.LIGHTGREEN);
                        } else if (user.get(j) != correct.get(j)) {
                            letter.setFill(Color.INDIANRED);
                        }
                    } catch (IndexOutOfBoundsException e) {
                        letter.setText(String.valueOf(user.get(j)));
                        letter.setFill(Color.LIGHTYELLOW);
                    }
                    textFlow.getChildren().add(letter);
                }
            } else {
                for (int j = 0; j < correct.size(); j++) {
                    Text letter = new Text(String.valueOf(correct.get(j)));
                    try {
                        if (user.get(j) == correct.get(j)) {
                            letter.setFill(Color.LIGHTGREEN);
                        } else if (user.get(j) != correct.get(j)) {
                            letter.setFill(Color.INDIANRED);
                        }
                    } catch (IndexOutOfBoundsException e) {
                        letter.setFill(Color.SLATEGRAY);
                    }
                    textFlow.getChildren().add(letter);
                }
            }
            textFlow.getChildren().add(new Text(String.valueOf(' ')));
        }

        if (userInput.size() > correctInput.size()) {
            for (int j = 0; j < userInput.size(); j++) {
                Text letter = new Text(String.valueOf(userInput.get(j)));
                try {
                    if (userInput.get(j) == correctInput.get(j)) {
                        letter.setText(String.valueOf(correctInput.get(j)));
                        letter.setFill(Color.LIGHTGREEN);
                    } else if (userInput.get(j) != correctInput.get(j)) {
                        letter.setText(String.valueOf(correctInput.get(j)));
                        letter.setFill(Color.INDIANRED);
                    }
                } catch (IndexOutOfBoundsException e) {
                    letter.setFill(Color.LIGHTYELLOW);
                }
                textFlow.getChildren().add(letter);
            }
        } else {
            for (int j = 0; j < correctInput.size(); j++) {
                Text letter = new Text(String.valueOf(correctInput.get(j)));
                try {
                    if (userInput.get(j) == correctInput.get(j)) {
                        letter.setFill(Color.LIGHTGREEN);
                    } else if (userInput.get(j) != correctInput.get(j)) {
                        letter.setFill(Color.INDIANRED);
                    }
                } catch (IndexOutOfBoundsException e) {
                    letter.setFill(Color.SLATEGRAY);
                }
                textFlow.getChildren().add(letter);
            }
        }

        for (int i = listOfPastInputChars.size() + 2; i < listOfGivenChars.size(); i++) {
            for (int j = 0; j < listOfGivenChars.get(i).size(); j++) {
                Text letter = new Text(String.valueOf(listOfGivenChars.get(i).get(j)));
                letter.setFill(Color.SLATEGRAY);
                textFlow.getChildren().add(letter);
            }
            textFlow.getChildren().add(new Text(String.valueOf(' ')));
        }
    }




    public TextFlow getTextFlow() {
        return textFlow;
    }*/


/*for (int i = 0; i < doneText.size(); i++) {
            for (int j = 0; j < doneText.get(i).size(); j++) {
                if (j > correctText.get(i).size() - 1) {
                    doneText.get(i).get(j).setFill(Color.LIGHTYELLOW);
                } else if (doneText.get(i).get(j) != correctText.get(i).get(j)) {
                    System.out.println(doneText.get(i).get(j).getText());
                    System.out.println(correctText.get(i).get(j).getText());
                    doneText.get(i).get(j).setFill(Color.INDIANRED);
                } else if (doneText.get(i).get(j) == correctText.get(i).get(j)) {
                    doneText.get(i).get(j).setFill(Color.LIGHTGREEN);
                }
                textFlow.getChildren().add(doneText.get(i).get(j));
            }
            textFlow.getChildren().add(new Text(String.valueOf(' ')));
        }

        for (int i = 0; i < correctText.get(whatWordIsBeingChecked).size(); i++) {
            Text toBeComparedWith;
            try {
                toBeComparedWith = correctText.get(whatWordIsBeingChecked).get(i);
            } catch (IndexOutOfBoundsException e) {
                toBeComparedWith = inputText.get(i);
            }

            try {
                if (inputText.get(i) == toBeComparedWith & i < correctText.get(whatWordIsBeingChecked).size()) {
                    inputText.get(i).setFill(Color.LIGHTGREEN);
                } else if (inputText.get(i) != toBeComparedWith & i < correctText.get(whatWordIsBeingChecked).size()) {
                    inputText.get(i).setFill(Color.INDIANRED);
                } else {
                    inputText.get(i).setFill(Color.LIGHTYELLOW);
                }
                textFlow.getChildren().add(inputText.get(i));
            } catch (IndexOutOfBoundsException e) {
                correctText.get(whatWordIsBeingChecked).get(i).setFill(Color.SLATEGRAY);
                textFlow.getChildren().add(correctText.get(whatWordIsBeingChecked).get(i));
            }
        }

        for (int i = whatWordIsBeingChecked + 1; i < correctText.size(); i++) {
            for (int j = 0; j < correctText.get(i).size(); j++) {
                correctText.get(i).get(j).setFill(Color.SLATEGRAY);
                textFlow.getChildren().add(correctText.get(i).get(j));
            }
            textFlow.getChildren().add(new Text(String.valueOf(' ')));
        }
*/
