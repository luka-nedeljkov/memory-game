package me.memory_game.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StageUtils {

	private static StageUtils instance;

	private Stage options;
	private Stage game;

	public StageUtils() {}

	public static StageUtils getInstance() {
		if(instance == null) {
			instance = new StageUtils();
		}
		return instance;
	}

	public void setOptions(Stage options) {
		this.options = options;
	}

	public void switchStage(String stage) {
		if(stage.equals("game")) {
			game = new Stage();
			game.setResizable(false);
			game.setTitle("Options");
			try {
				game.setScene(new Scene(FXMLLoader.load(getClass().getResource(Assets.GAME))));
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			game.show();
			options.hide();
			return;
		}
		if(stage.equals("options")) {
			game.hide();
			game = null;
			options.show();
			return;
		}
		System.out.println("Invalid stage name");
	}

	public Stage getStage(String stage) {
		if(stage.equals("options")) {
			return options;
		}
		if(stage.equals("game")) {
			return game;
		}
		return null;
	}

}
