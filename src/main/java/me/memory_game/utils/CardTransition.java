package me.memory_game.utils;

import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.scene.control.Button;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class CardTransition {

	private String transitionType;
	private int millis;

	public CardTransition(String transitionType, int millis) {
		this.transitionType = transitionType;
		this.millis = millis;
	}

	public void open(Button button) {
		switch(transitionType) {
			case "Rotate" -> {
				RotateTransition transition = new RotateTransition(Duration.millis(millis), button.getGraphic());
				button.getGraphic().setOpacity(1);
				transition.setAxis(Rotate.Y_AXIS);
				transition.setFromAngle(90);
				transition.setToAngle(0);
				transition.play();
			}
			case "Fade" -> {
				FadeTransition transition = new FadeTransition(Duration.millis(millis), button.getGraphic());
				button.getGraphic().setRotate(0);
				transition.setFromValue(0);
				transition.setToValue(1);
				transition.play();
			}
			case "None" -> {
				button.getGraphic().setOpacity(1);
				button.getGraphic().setRotate(0);
			}
		}
		button.getGraphic().setVisible(true);
	}

	public void close(Button button) {
		switch(transitionType) {
			case "Rotate" -> {
				RotateTransition transition = new RotateTransition(Duration.millis(millis), button.getGraphic());
				transition.setAxis(Rotate.Y_AXIS);
				transition.setFromAngle(0);
				transition.setToAngle(90);
				transition.play();
				Options.getInstance().delay(millis, () -> {
					button.getGraphic().setVisible(false);
				});
				transition.setFromAngle(90);
				transition.setToAngle(0);
				transition.play();
			}
			case "Fade" -> {
				FadeTransition transition = new FadeTransition(Duration.millis(millis), button.getGraphic());
				transition.setFromValue(1);
				transition.setToValue(0);
				transition.play();
				Options.getInstance().delay(millis, () -> {
					button.getGraphic().setVisible(false);
				});
				transition.setFromValue(0);
				transition.setToValue(1);
			}
			case "None" -> button.getGraphic().setVisible(false);
		}
	}

}
