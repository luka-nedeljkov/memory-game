package me.memory_game.ui.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextInputDialog;
import me.memory_game.game.Player;
import me.memory_game.utils.Options;
import me.memory_game.utils.SQLUtils;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class OptionsController implements Initializable {

	@FXML
	private ChoiceBox<String> player1select;
	@FXML
	private ChoiceBox<String> player2select;

	Options options = Options.getInstance();
	SQLUtils sqlUtils = SQLUtils.getInstance();

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		refreshChoiceBox();
	}

	private void refreshChoiceBox() {
		ArrayList<String> list = sqlUtils.selectAllNames();
		player1select.setItems(FXCollections.observableList(list));
		player2select.setItems(FXCollections.observableList(list));
		player1select.getSelectionModel().select(0);
		player2select.getSelectionModel().select(1);
	}

	@FXML
	private void newPlayer() {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("New player");
		dialog.setHeaderText(null);
		dialog.setContentText("Player name: ");
		Optional<String> result = dialog.showAndWait();
		if(result.isPresent()) {
			if(result.get().equals("")) return;
			sqlUtils.insert(result.get());
		}
		refreshChoiceBox();
	}

	@FXML
	private void start() {
		String player1 = player1select.getSelectionModel().getSelectedItem();
		String player2 = player2select.getSelectionModel().getSelectedItem();
		if(player1 == null || player2 == null) {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("Warning");
			alert.setHeaderText("No players");
			alert.setContentText("No players selected");
			alert.showAndWait();
			return;
		}
		if(player1.equals(player2)) {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("Warning");
			alert.setHeaderText("Invalid players");
			alert.setContentText("Please select 2 different players");
			alert.showAndWait();
			return;
		}
		options.setPlayer1(new Player(player1, sqlUtils.select(player1)));
		options.setPlayer2(new Player(player2, sqlUtils.select(player2)));
		options.switchStage("game");
	}

}
