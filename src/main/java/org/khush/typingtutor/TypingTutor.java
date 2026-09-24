package org.khush.typingtutor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;

import java.io.IOException;

public class TypingTutor extends Application {
    @Override
    public void start(Stage stage) {

        String row1key = "1234567890";
        String row2key = "QWERTYUIOP";
        String row3key = "ASDFGHJKL";
        String row4key = "ZXCVBNM";

        HBox row1 = new HBox(5);
        row1.setAlignment(Pos.CENTER);

        for (int i = 0; i < row1key.length(); i++) {
            Button button = new Button(String.valueOf(row1key.charAt(i)));
            button.setId(String.valueOf(row1key.charAt(i)));
            button.setPrefSize(50, 50);
            row1.getChildren().add(button);
        }

        HBox row2 = new HBox(5);
        row2.setAlignment(Pos.CENTER);

        for (int i = 0; i < row2key.length(); i++) {
            Button button = new Button(String.valueOf(row2key.charAt(i)));
            button.setId(String.valueOf(row2key.charAt(i)));
            button.setPrefSize(50, 50);
            row2.getChildren().add(button);

        }

        HBox row3 = new HBox(5);
        row3.setAlignment(Pos.CENTER);

        for (int i = 0; i < row3key.length(); i++) {
            Button button = new Button(String.valueOf(row3key.charAt(i)));
            button.setId(String.valueOf(row3key.charAt(i)));
            button.setPrefSize(80, 50);
            row3.getChildren().add(button);
        }

        HBox row4 = new HBox(5);
        row4.setAlignment(Pos.CENTER);

        Button shift = new Button("Shift");
        shift.setId("SHIFT");
        shift.setPrefSize(80, 50);
        row4.getChildren().add(shift);

        for (int i = 0; i < row4key.length(); i++) {
            Button button = new Button(String.valueOf(row4key.charAt(i)));
            button.setId(String.valueOf(row4key.charAt(i)));
            button.setPrefSize(50,50);
            row4.getChildren().add(button);

        }

        Button backspace = new Button("Backspace");
        backspace.setId("BACK_SPACE");
        backspace.setPrefSize(100,50);
        row4.getChildren().add(backspace);

        HBox row5 = new HBox(5);
        row5.setAlignment(Pos.CENTER);

        Button space = new Button("Space");
        space.setId("SPACE");
        space.setPrefSize(300, 50);
        row5.getChildren().add(space);

        VBox keyboard = new VBox(5);
        keyboard.setAlignment(Pos.CENTER);

        keyboard.getChildren().addAll(
                row1,row2,row3,row4,row5
        );
        Scene scene = new Scene(keyboard, 700, 350);

        scene.setOnKeyPressed(event -> {
            System.out.println(event.getCode());
        });

        stage.setTitle("Typing Tutor");
        stage.setScene(scene);
        stage.show();

    }
}
