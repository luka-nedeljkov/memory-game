package me.memory_game.game;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import java.util.Arrays;

public class Move {

	Player player;
	Button first;
	Button second;

	public Move(Player player) {
		this.player = player;
	}

	public boolean success() {
		if(((ImageView) first.getGraphic()).getImage().equals(((ImageView) second.getGraphic()).getImage())) {
			first.setDisable(true);
			second.setDisable(true);
			player.setScore(player.getScore() + 1);
			return true;
		}
		return false;
	}

}
