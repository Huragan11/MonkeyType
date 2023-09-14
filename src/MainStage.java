import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextFlow;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class MainStage {

    String dictionaryPath = "MonkeyType/dictionary";
    int height = 500;
    int width = 500;
    VBox layout;
    ChoiceBox languageChoice;
    String chosenLanguage;
    ChoiceBox timeChoice;
    int chosenTime;
    HBox restartTest;
    HBox pauseTest;
    HBox endTest;
    Scene mainScene;
    CustomTextArea customTextArea;
    Map<String, List<String>> languagesMap;

    String backgroundText;

    private void SetTextAccordingToLanguage(String choiceOfLanguage) {
        List<String> words = languagesMap.get(choiceOfLanguage);
        backgroundText = "";
        Random random = new Random();

        for (int i = 0; i < 30; i++) {
            backgroundText += words.get(random.nextInt(words.size())) + " ";
        }
        customTextArea.setNewText(backgroundText);
    }




    public MainStage() {
        customTextArea = new CustomTextArea();
        languagesMap = new HashMap<>();

        File folder = new File(dictionaryPath);
        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    String fileName = file.getName();
                    String fileKey = fileName.substring(0, fileName.lastIndexOf("."));
                    List<String> words = readFileWords(file);
                    languagesMap.put(fileKey, words);
                }
            }
        }

        layout = new VBox();
        layout.setStyle("-fx-background-color: #363636");

        languageChoice = new ChoiceBox(FXCollections.observableArrayList(languagesMap.keySet()));
        languageChoice.setDisable(true);
        languageChoice.setOnAction(e -> {
            chosenLanguage = (String) languageChoice.getValue();
            SetTextAccordingToLanguage(chosenLanguage);
            customTextArea.requestFocus();
        });

        timeChoice = new ChoiceBox(FXCollections.observableArrayList("15", "20", "45", "60", "90", "120", "300"));
        timeChoice.setOnAction(e -> {
            chosenTime = Integer.parseInt((String) timeChoice.getValue());
            languageChoice.setDisable(false);
        });




        HBox choosers = new HBox();
        choosers.getChildren().addAll(timeChoice, languageChoice);
        choosers.setPadding(new Insets(30, 15, 0, 15));
        choosers.setSpacing(50);
        choosers.setAlignment(Pos.CENTER);
        timeChoice.setStyle("-fx-background-color:#cbcbcb; -fx-text-fill: black;");
        languageChoice.setStyle("-fx-background-color:#cbcbcb; -fx-text-fill: black;");

        restartTest = new HBox();
        Button tab = new Button("tab");
        tab.setDisable(true);
        tab.setStyle("-fx-background-radius: 5;-fx-background-color: #cbcbcb ; -fx-text-fill: black;");

        Label plus = new Label("+");
        plus.setStyle("-fx-text-fill: #cbcbcb");
        Button enter = new Button("enter");
        enter.setDisable(true);
        enter.setStyle("-fx-background-radius: 5;-fx-background-color: #cbcbcb ; -fx-text-fill: black;");

        Label restart = new Label("- restart test");
        restart.setStyle("-fx-text-fill: #cbcbcb");
        restartTest.setSpacing(5);
        restartTest.getChildren().addAll(tab, plus, enter, restart);
        restartTest.setAlignment(Pos.CENTER);

        pauseTest = new HBox();
        Button ctrl = new Button("ctrl");
        ctrl.setDisable(true);
        ctrl.setStyle("-fx-background-radius: 5;-fx-background-color: #cbcbcb ; -fx-text-fill: black;");

        Button shift = new Button("shift");
        shift.setDisable(true);
        shift.setStyle("-fx-background-radius: 5;-fx-background-color: #cbcbcb ; -fx-text-fill: black;");

        Label pause = new Label("- pause");
        pause.setStyle("-fx-text-fill: #cbcbcb");

        Button p = new Button("p");
        p.setDisable(true);
        p.setStyle("-fx-background-radius: 5;-fx-background-color: #cbcbcb ; -fx-text-fill: black;");

        Label plus1 = new Label("+");
        Label plus2 = new Label("+");
        plus1.setStyle("-fx-text-fill: #cbcbcb");
        plus2.setStyle("-fx-text-fill: #cbcbcb");

        pauseTest.setSpacing(5);
        pauseTest.getChildren().addAll(ctrl, plus1, shift, plus2, p, pause);
        pauseTest.setAlignment(Pos.CENTER);

        endTest = new HBox();
        Button escape = new Button("esc");
        escape.setDisable(true);
        escape.setStyle("-fx-background-radius: 5;-fx-background-color: #cbcbcb ; -fx-text-fill: black;");

        Label end = new Label("- end test");
        end.setStyle("-fx-text-fill: #cbcbcb");

        endTest.setSpacing(5);
        endTest.getChildren().addAll(escape, end);
        endTest.setAlignment(Pos.CENTER);

        VBox keyLegend = new VBox();
        keyLegend.setSpacing(10);
        keyLegend.getChildren().addAll(restartTest, pauseTest, endTest);
        keyLegend.setAlignment(Pos.CENTER);

        layout.setAlignment(Pos.TOP_CENTER);
        layout.setSpacing(30);
        layout.getChildren().addAll(choosers, customTextArea.getTextFlow(),customTextArea, keyLegend);
        mainScene = new Scene(layout, width, height);

    }

    public Scene GetMainScene() {
        return mainScene;
    }

    private static List<String> readFileWords(File file) {
        List<String> words = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                words.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return words;
    }


}
