package me.memory_game.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import me.memory_game.utils.Assets;
import me.memory_game.utils.SQLUtils;
import me.memory_game.utils.StageUtils;

import java.io.IOException;

public class GameApplication extends Application {
	@Override
	public void start(Stage options) throws IOException {
		StageUtils.getInstance().setOptions(options);

		options.setResizable(false);
		options.setTitle("Memory Game");
		options.setScene(new Scene(FXMLLoader.load(GameApplication.class.getResource(Assets.OPTIONS))));
		options.show();
	}

	public static void main(String[] args) {
		startup();
	}

	public static void startup() {
		SQLUtils.getInstance().createTable();
		launch();
	}

}