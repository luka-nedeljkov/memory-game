package me.memory_game.ui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import me.memory_game.utils.Options;
import me.memory_game.utils.StageUtils;

import java.net.URL;
import java.util.ResourceBundle;

public class GameOptionsController implements Initializable {

	private static final Options options = Options.getInstance();

	@FXML
	private ColorPicker picker;
	@FXML
	private Slider sliderSpeed;
	@FXML
	private ChoiceBox<String> transitionPicker;
	@FXML
	private Slider sliderDelay;
	@FXML
	private ChoiceBox<String> imageSetPicker;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		picker.setValue((Color) options.getBackground().getFills().get(0).getFill());
		sliderSpeed.setValue(options.getTransitionLength() / 50);
		transitionPicker.getItems().add("Rotate");
		transitionPicker.getItems().add("Fade");
		transitionPicker.getItems().add("None");
		transitionPicker.setValue(options.getTransitionType());
		sliderDelay.setValue((options.getTransitionDelay() - 500) / 150);
		imageSetPicker.getItems().add("Birds");
		imageSetPicker.getItems().add("Shapes");
		imageSetPicker.getItems().add("Smileys");
		imageSetPicker.setValue(options.getImageType());
	}

	@FXML
	private void apply(ActionEvent e) {
		options.setBackground(new Background(new BackgroundFill(picker.getValue(), CornerRadii.EMPTY, Insets.EMPTY)));
		options.setTransitionLength((int) sliderSpeed.getValue() * 50);
		options.setTransitionType(transitionPicker.getValue());
		options.setTransitionDelay((int) sliderDelay.getValue() * 150 + 500);
		options.setImageType(imageSetPicker.getValue());
		options.apply(((AnchorPane) StageUtils.getInstance().getStage("game").getScene().getRoot()));
		cancel(e);
	}

	@FXML
	private void reset(ActionEvent e) {
		picker.setValue((Color) Options.DEFAULT_COLOR.getFills().get(0).getFill());
		sliderSpeed.setValue(Options.DEFAULT_TRANSITON_LENGTH / 50);
		transitionPicker.setValue(Options.DEFAULT_TRANSITION_TYPE);
		apply(e);
	}

	@FXML
	private void cancel(ActionEvent e) {
		((Button) e.getSource()).getScene().getWindow().hide();
	}

}
