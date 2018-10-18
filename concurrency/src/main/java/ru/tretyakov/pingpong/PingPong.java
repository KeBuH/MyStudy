package ru.tretyakov.pingpong;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * Ping Pong app starter.
 *
 * @author Rooter
 * @since 23.09.18
 **/

public class PingPong extends Application {
    /**
     * Window title.
     */
    private static final String JOB4J = "Пинг-понг www.job4j.ru";

    /**
     * Init and start app.
     * @param stage view
     */
    @Override
    public void start(final Stage stage) {
        int limitX = 300;
        int limitY = 300;
        int recScale = 10;
        Group group = new Group();
        Rectangle rect = new Rectangle(50, 100, recScale, recScale);
        group.getChildren().add(rect);
        Thread child = new Thread(new RectangleMove(rect, limitX - recScale));
        child.start();
        stage.setScene(new Scene(group, limitX, limitY));
        stage.setTitle(JOB4J);
        stage.setResizable(false);
        stage.show();
        stage.setOnCloseRequest(event -> child.interrupt());
    }
}
