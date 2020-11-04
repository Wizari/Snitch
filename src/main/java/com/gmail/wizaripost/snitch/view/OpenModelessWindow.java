package com.gmail.wizaripost.snitch.view;

import com.gmail.wizaripost.snitch.Main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class OpenModelessWindow {

    Stage stage = Main.stage;
    Stage window;


    public void handle(ActionEvent event, String title, String messageText, Font font, double width, double height, Paint paint, int timer) {

        Label secondLabel = new Label(messageText);
        StackPane secondaryLayout = new StackPane();
        secondaryLayout.getChildren().add(secondLabel);
        Scene secondScene = new Scene(secondaryLayout, width, height);

        window = new Stage();
        window.setTitle(title);
        window.setScene(secondScene);
        window.initModality(Modality.WINDOW_MODAL);
        window.initOwner(stage);
        secondLabel.setTextFill(paint);
        secondLabel.setFont(font);

//        System.out.println(stage.getX()); //480
//        System.out.println(stage.getY()); //88.0
//        System.out.println(stage.getWidth()); //700
//        System.out.println(stage.getHeight()); //837

        window.setX(stage.getX() + (stage.getWidth() / 2) - (width / 2));
        window.setY(stage.getY() + (stage.getHeight() / 2) - (height / 2));
        window.show();
        if (timer >= 1) {
            windowsTimerHid(timer);
        }
    }

    private void windowsTimerHid(int milliseconds) {
        Thread thread = new Thread(() -> {
            try {
                // Wait for 5 secs
                Thread.sleep(milliseconds);
                if (window.isShowing()) {
                    Platform.runLater(() -> window.close());
                }
            } catch (Exception exp) {
                exp.printStackTrace();
            }
        });
        thread.setDaemon(true);
        thread.start();
    }
}
