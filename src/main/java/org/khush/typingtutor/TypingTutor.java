package org.khush.typingtutor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.io.IOException;

public class TypingTutor extends Application {
    @Override
    public void start(Stage stage) {

        String row1key = "1234567890";
        String row2key = "qwertyuiop";
        String row3key = "asdfghjkl";
        String row4key = "zxcvbnm";

        boolean[] shiftPressed = {false};

        TextField pressedKey = new TextField();
        pressedKey.setPrefWidth(200);
        pressedKey.setPromptText("Pressed key");
        pressedKey.setEditable(false);

        TextField response = new TextField();
        response.setPrefWidth(500);
        response.setPromptText("Type here");

        HBox row1 = new HBox(5);
        row1.setAlignment(Pos.CENTER);

        for (int i = 0; i < row1key.length(); i++) {
            Button button = new Button(String.valueOf(row1key.charAt(i)));
            button.setId(String.valueOf(row1key.charAt(i)));
            button.setPrefSize(50, 50);

            button.setOnAction(event -> {
                response.appendText(button.getText());
                response.requestFocus();
            });

            button.setOnMousePressed(event -> {
                button.setStyle("-fx-background-color: gray;");
                pressedKey.setText(button.getText());
            });

            button.setOnMouseReleased(event -> {
                button.setStyle("");
            });

            row1.getChildren().add(button);
        }

        HBox row2 = new HBox(5);
        row2.setAlignment(Pos.CENTER);

        for (int i = 0; i < row2key.length(); i++) {
            Button button = new Button(String.valueOf(row2key.charAt(i)));
            button.setId(String.valueOf(row2key.charAt(i)));
            button.setPrefSize(50, 50);

            button.setOnAction(event -> {
                response.appendText(button.getText());
                response.requestFocus();
            });

            button.setOnMousePressed(event -> {
                button.setStyle("-fx-background-color: gray;");
                pressedKey.setText(button.getText());
            });

            button.setOnMouseReleased(event -> {
                button.setStyle("");
            });

            row2.getChildren().add(button);
        }

        HBox row3 = new HBox(5);
        row3.setAlignment(Pos.CENTER);

        for (int i = 0; i < row3key.length(); i++) {
            Button button = new Button(String.valueOf(row3key.charAt(i)));
            button.setId(String.valueOf(row3key.charAt(i)));
            button.setPrefSize(80, 50);

            button.setOnAction(event -> {
                response.appendText(button.getText());
                response.requestFocus();
            });

            button.setOnMousePressed(event -> {
                button.setStyle("-fx-background-color: gray;");
                pressedKey.setText(button.getText());
            });

            button.setOnMouseReleased(event -> {
                button.setStyle("");
            });

            row3.getChildren().add(button);
        }

        HBox row4 = new HBox(5);
        row4.setAlignment(Pos.CENTER);

        Button shift = new Button("Shift");
        shift.setId("SHIFT");
        shift.setPrefSize(80, 50);

        shift.setOnAction(event -> {
            shiftPressed[0] = !shiftPressed[0];

            for (int i = 0; i < row2.getChildren().size(); i++) {
                Button button = (Button) row2.getChildren().get(i);

                if (shiftPressed[0]) {
                    button.setText(button.getText().toUpperCase());
                } else {
                    button.setText(button.getText().toLowerCase());
                }
            }

            for (int i = 0; i < row3.getChildren().size(); i++) {
                Button button = (Button) row3.getChildren().get(i);

                if (shiftPressed[0]) {
                    button.setText(button.getText().toUpperCase());
                } else {
                    button.setText(button.getText().toLowerCase());
                }
            }

            for (int i = 0; i < row4key.length(); i++) {
                Button button = (Button) row4.getChildren().get(i + 1);

                if (shiftPressed[0]) {
                    button.setText(button.getText().toUpperCase());
                } else {
                    button.setText(button.getText().toLowerCase());
                }
            }

            response.requestFocus();
        });

        shift.setOnMousePressed(event -> {
            shift.setStyle("-fx-background-color: gray;");
            pressedKey.setText("SHIFT");
        });

        shift.setOnMouseReleased(event -> {
            shift.setStyle("");
        });

        row4.getChildren().add(shift);

        for (int i = 0; i < row4key.length(); i++) {
            Button button = new Button(String.valueOf(row4key.charAt(i)));
            button.setId(String.valueOf(row4key.charAt(i)));
            button.setPrefSize(50, 50);

            button.setOnAction(event -> {
                response.appendText(button.getText());
                response.requestFocus();
            });

            button.setOnMousePressed(event -> {
                button.setStyle("-fx-background-color: gray;");
                pressedKey.setText(button.getText());
            });

            button.setOnMouseReleased(event -> {
                button.setStyle("");
            });

            row4.getChildren().add(button);
        }

        Button backspace = new Button("Backspace");
        backspace.setId("BACK_SPACE");
        backspace.setPrefSize(100, 50);

        backspace.setOnAction(event -> {

            if (!response.getText().isEmpty()) {
                response.deleteText(
                        response.getText().length() - 1,
                        response.getText().length()
                );
            }

            response.requestFocus();
        });

        backspace.setOnMousePressed(event -> {
            backspace.setStyle("-fx-background-color: gray;");
            pressedKey.setText("BACK_SPACE");
        });

        backspace.setOnMouseReleased(event -> {
            backspace.setStyle("");
        });

        row4.getChildren().add(backspace);

        HBox row5 = new HBox(5);
        row5.setAlignment(Pos.CENTER);

        Button space = new Button("Space");
        space.setId("SPACE");
        space.setPrefSize(300, 50);

        space.setOnAction(event -> {
            response.appendText(" ");
            response.requestFocus();
        });

        space.setOnMousePressed(event -> {
            space.setStyle("-fx-background-color: gray;");
            pressedKey.setText("SPACE");
        });

        space.setOnMouseReleased(event -> {
            space.setStyle("");
        });

        row5.getChildren().add(space);

        VBox keyboard = new VBox(5);
        keyboard.setAlignment(Pos.CENTER);

        keyboard.getChildren().addAll(
                pressedKey, response, row1, row2, row3, row4, row5
        );

        Scene scene = new Scene(keyboard, 700, 400);

        response.addEventFilter(KeyEvent.KEY_PRESSED, event -> {

            String key = event.getCode().toString();

            pressedKey.setText(key);

            String buttonId = key;

            if (key.startsWith("DIGIT")) {
                buttonId = key.substring(5);
            }

            if (key.length() == 1 && Character.isLetter(key.charAt(0))) {
                buttonId = key.toLowerCase();
            }

            Button button = (Button) scene.lookup("#" + buttonId);

            if (button != null) {
                button.setStyle("-fx-background-color: gray;");
            }

            if (key.equals("SHIFT")) {
                return;
            }

            if (key.equals("BACK_SPACE")) {
                if (!response.getText().isEmpty()) {
                    response.deleteText(
                            response.getText().length() - 1,
                            response.getText().length()
                    );
                }

                event.consume();
                return;
            }

            if (key.equals("SPACE")) {
                response.appendText(" ");

                event.consume();
                return;
            }

            if (button == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Key not handled.");
                alert.showAndWait();

                event.consume();
            }
        });

        response.addEventFilter(KeyEvent.KEY_RELEASED, event -> {

            String key = event.getCode().toString();

            String buttonId = key;

            if (key.startsWith("DIGIT")) {
                buttonId = key.substring(5);
            }

            if (key.length() == 1 && Character.isLetter(key.charAt(0))) {
                buttonId = key.toLowerCase();
            }

            Button button = (Button) scene.lookup("#" + buttonId);

            if (button != null) {
                button.setStyle("");
            }
        });

        stage.setTitle("Typing Tutor");
        stage.setScene(scene);
        stage.show();

        response.requestFocus();
    }
}