import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;

public class Main extends Application {
    MainStage mainStage = new MainStage();
    @Override
    public void start(Stage stage) throws Exception {
    stage.setScene(mainStage.GetMainScene());

    mainStage.GetMainScene().setOnKeyPressed(event -> {
        if (event.getCode() == KeyCode.ESCAPE){
            System.exit(0);
        }
        if ((event.getCode() == KeyCode.P && event.isControlDown() && event.isShiftDown())){
            System.out.println("pctrlshift");
        }
    });

    stage.show();
    }



}

