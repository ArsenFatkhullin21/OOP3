package ru.arsen.oop3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicBoolean;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) {
        MyList<Circle> highlightedCircle = new MyList<>();  // Список выделенных кругов
        MyList<Circle> circles = new MyList<>();  // Список всех кругов
        boolean[] isControlPressed = {false};  // Для отслеживания нажатой клавиши Ctrl

        // Создаем панель и канвас
        Canvas canvas = new Canvas(500, 400);
        Pane borderedPane = new Pane(canvas);

        borderedPane.setStyle("-fx-border-color: black; -fx-border-width: 3; -fx-background-color: #ffffff;");
        borderedPane.setMaxWidth(canvas.getWidth() + 5);
        borderedPane.setMaxHeight(canvas.getHeight() + 5);

        // Создаем сцену
        Scene scene = new Scene(borderedPane, 500, 400);

        // Обработчик изменения размера окна
        stage.widthProperty().addListener((observable, oldValue, newValue) -> {
            borderedPane.setPrefWidth(newValue.doubleValue());
            canvas.setWidth(newValue.doubleValue());
        });

        stage.heightProperty().addListener((observable, oldValue, newValue) -> {
            borderedPane.setPrefHeight(newValue.doubleValue());
            canvas.setHeight(newValue.doubleValue());
        });

        // Обработчик нажатия клавиш
        scene.setOnKeyPressed(event -> {
            KeyCode code = event.getCode();
            if (code == KeyCode.BACK_SPACE) {
                // Удалить только выделенные круги
                for (int i = 0; i < highlightedCircle.size(); i++) {
                    borderedPane.getChildren().remove(highlightedCircle.get(i));
                }
                highlightedCircle.removeAll(); // Очистить список выделенных кругов
            } else if (code == KeyCode.CONTROL) {
                isControlPressed[0] = true;
            }
        });

        // Обработчик отпускания клавиш
        scene.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.CONTROL) {
                isControlPressed[0] = false;
            }
        });

        // Обработчик нажатия мыши для рисования кругов
        /*borderedPane.setOnMousePressed(event -> {
            double x = event.getX();
            double y = event.getY();

            // Создаем круг
            Circle circle = new Circle(x, y, 60);
            circles.add(circle);  // Добавляем круг в список всех кругов
            circle.setFill(Color.CADETBLUE);

            // Обработчик для выделения всех пересекающихся кругов с зажатым CTRL
            circle.setOnMousePressed(ev -> {
                if (isControlPressed[0]) {
                    // Выделяем все круги, которые пересекаются с этим
                    for (int i = 0; i < circles.size(); i++) {
                        Circle otherCircle = circles.get(i);
                        if (areCirclesIntersecting(circle, otherCircle) && !highlightedCircle.contains(otherCircle)) {
                            otherCircle.setFill(Color.GREY); // Меняем цвет круга
                            highlightedCircle.add(otherCircle); // Добавляем в список выделенных
                        }
                    }
                    ev.consume(); // Предотвращаем дальнейшее распространение события
                }
            });

            borderedPane.getChildren().add(circle);
        });
*/

        borderedPane.setOnMousePressed(event -> {
            double x = event.getX();
            double y = event.getY();

            // Создаем новый круг
            Circle circle = new Circle(x, y, 60);
            circles.add(circle);
            circle.setFill(Color.CADETBLUE);

            // Обработчик для выделения круга с зажатым CTRL
            circle.setOnMousePressed(ev -> {
                if (isControlPressed[0]) {
                    circle.setFill(Color.GREY); // Меняем цвет на серый
                    highlightedCircle.add(circle); // Добавляем в список выделенных
                    ev.consume(); // Предотвращаем дальнейшее распространение события
                }
            });

            // Добавляем круг на экран
            borderedPane.getChildren().add(circle);
        });


        // Устанавливаем сцену и отображаем окно
        stage.setScene(scene);
        stage.setTitle("Hello World");
        stage.show();
    }

    // Метод для проверки пересечения двух кругов
    private boolean areCirclesIntersecting(Circle c1, Circle c2) {
        double distance = Math.sqrt(Math.pow(c1.getCenterX() - c2.getCenterX(), 2) +
                Math.pow(c1.getCenterY() - c2.getCenterY(), 2));
        return distance <= (c1.getRadius() + c2.getRadius());
    }

    public static void main(String[] args) {
        launch();
    }
}