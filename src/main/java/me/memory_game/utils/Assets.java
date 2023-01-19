package me.memory_game.utils;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class Assets {

	private static ArrayList<Image> birds;
	private static ArrayList<Image> shapes;
	private static ArrayList<Image> smileys;

	public static Image GEAR = new Image(Assets.class.getResourceAsStream("/me/memory_game/images/gear.png"));

	public static String OPTIONS = "/me/memory_game/ui/fxml/Options.fxml";
	public static String GAME = "/me/memory_game/ui/fxml/Game.fxml";
	public static String GAME_OPTINS = "/me/memory_game/ui/fxml/GameOptions.fxml";

	public static Image getImage(int n, String imageSet) {
		switch(imageSet) {
			case "Birds" -> {
				return birds.get(n);
			}
			case "Shapes" -> {
				return shapes.get(n);
			}
			case "Smileys" -> {
				return smileys.get(n);
			}
		}
		return null;
	}

	static {
		birds = new ArrayList<>();
		shapes = new ArrayList<>();
		smileys = new ArrayList<>();
		for(int i = 0; i < 8; i++) {
			birds.add(new Image(Assets.class.getResourceAsStream("/me/memory_game/images/birds/" + (i + 1) + ".jpg")));
		}
		for(int i = 0; i < 8; i++) {
			shapes.add(new Image(Assets.class.getResourceAsStream("/me/memory_game/images/shapes/" + (i + 1) + ".jpg")));
		}
		for(int i = 0; i < 8; i++) {
			smileys.add(new Image(Assets.class.getResourceAsStream("/me/memory_game/images/smileys/" + (i + 1) + ".jpg")));
		}
	}

}
