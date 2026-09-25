package org.khush.typingtutor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.io.IOException;

public class TypingTutor extends Application {
    @Override
    public void start(Stage stage) {

        String[] texts = {
                "Try typing this text. Do it as quickly and accurately as you can.",
                "Next type another line of input data.",
                "The quick brown fox jumps over the lazy dog.",
                "Five big quacking zephyrs jolt my wax bed.",
                "Sympathizing would fix Quaker objectives.",
                "A large fawn jumped quickly over white zinc boxes."
        };

        String row1key = "1234567890";
        String row2key = "qwertyuiop";
        String row3key = "asdfghjkl";
        String row4key = "zxcvbnm";

        boolean[] shiftPressed = {false};

        int[] currentText = {0};

        TextField pressedKey = new TextField();
        pressedKey.setPrefWidth(200);
        pressedKey.setPromptText("Pressed key");
        pressedKey.setEditable(false);

        TextField expectedText = new TextField();
        expectedText.setPrefWidth(500);
        expectedText.setText(texts[0]);
        expectedText.setEditable(false);

        TextField response = new TextField();
        response.setPrefWidth(500);
        response.setPromptText("Type here");

        Button next = new Button("Next");
        Button reset = new Button("Reset");

        Label counter = new Label("1 of 6");
        Label correct = new Label("Correct: 0");
        Label incorrect = new Label("Incorrect: 0");

        next.setOnAction(event -> {
            if (currentText[0] < texts.length - 1) {
                currentText[0]++;
                expectedText.setText(texts[currentText[0]]);
                response.clear();

                counter.setText(
                        (currentText[0] + 1) + " of " + texts.length
                );

                response.requestFocus();
            }
        });

        reset.setOnAction(event -> {
            currentText[0] = 0;

            expectedText.setText(texts[0]);
            response.clear();

            counter.setText("1 of 6");

            shiftPressed[0] = false;

            response.requestFocus();
        });

        response.textProperty().addListener((observable, oldValue, newValue) -> {

            int correctCount = 0;
            int incorrectCount = 0;

            String expected = expectedText.getText();

            for (int i = 0; i < newValue.length(); i++) {
                if (i < expected.length()
                        && newValue.charAt(i) == expected.charAt(i)) {
                    correctCount++;
                } else {
                    incorrectCount++;
                }
            }

            correct.setText("Correct: " + correctCount);
            incorrect.setText("Incorrect: " + incorrectCount);
        });

        HBox controls = new HBox(10);
        controls.setAlignment(Pos.CENTER);
        controls.getChildren().addAll(
                next, reset, counter, correct, incorrect
        );

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

        Button comma = new Button(",");
        comma.setId("COMMA");
        comma.setPrefSize(50, 50);

        comma.setOnAction(event -> {
            response.appendText(",");
            response.requestFocus();
        });

        comma.setOnMousePressed(event -> {
            comma.setStyle("-fx-background-color: gray;");
            pressedKey.setText(",");
        });

        comma.setOnMouseReleased(event -> {
            comma.setStyle("");
        });

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

        Button period = new Button(".");
        period.setId("PERIOD");
        period.setPrefSize(50, 50);

        period.setOnAction(event -> {
            response.appendText(".");
            response.requestFocus();
        });

        period.setOnMousePressed(event -> {
            period.setStyle("-fx-background-color: gray;");
            pressedKey.setText(".");
        });

        period.setOnMouseReleased(event -> {
            period.setStyle("");
        });

        row5.getChildren().addAll(comma, space, period);

        VBox keyboard = new VBox(5);
        keyboard.setAlignment(Pos.CENTER);

        keyboard.getChildren().addAll(
                expectedText, pressedKey, response, controls,
                row1, row2, row3, row4, row5
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

            if (key.equals("BACKSPACE")) {
                if (!response.getText().isEmpty()) {
                    response.deleteText(
                            response.getText().length() - 1,
                            response.getText().length()
                    );
                }

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