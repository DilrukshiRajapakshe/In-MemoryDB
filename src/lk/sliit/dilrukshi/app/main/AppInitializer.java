/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.sliit.dilrukshi.app.main;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * @author ranjith-suranga
 */
public class AppInitializer extends Application {

    public static void navigateToHome(Node node, Stage mainStage) throws IOException {

        Parent root = FXMLLoader.load(AppInitializer.class.getResource("/lk/sliit/dilrukshi/app/view/MainForm.fxml"));
        Scene mainScene = new Scene(root);
        mainStage.setScene(mainScene);

        TranslateTransition tt1 = new TranslateTransition(Duration.millis(300), root.lookup("AnchorPane"));
        tt1.setToX(0);
        tt1.setFromX(-mainScene.getWidth());
        tt1.play();

        mainStage.centerOnScreen();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        FileHandler fileHandler = new FileHandler("error.log", true);
        fileHandler.setFormatter(new SimpleFormatter());
        Logger.getLogger("").addHandler(fileHandler);

        Parent root = FXMLLoader.load(this.getClass().getResource("/lk/sliit/dilrukshi/app/view/MainForm.fxml"));

        Scene mainScene = new Scene(root);

        primaryStage.setTitle("JavaFX POS");
        primaryStage.setScene(mainScene);
        primaryStage.setResizable(false);

        primaryStage.show();

    }

}
