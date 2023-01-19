package me.memory_game.ui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import me.memory_game.game.Game;
import me.memory_game.utils.Assets;
import me.memory_game.utils.Options;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class GameController implements Initializable {

	@FXML
	private Label player1name;
	@FXML
	private Label player1wins;
	@FXML
	private Label player1score;
	@FXML
	private Label player2name;
	@FXML
	private Label player2wins;
	@FXML
	private Label player2score;
	@FXML
	private GridPane gridPane;
	@FXML
	private Label lblOnMove;
	@FXML
	private ImageView imgGear;

	Options options = Options.getInstance();
	Game game;

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		ArrayList<Button> buttons = new ArrayList<>();
		ArrayList<Label> labels = new ArrayList<>();
		for(Node temp : gridPane.getChildren()) {
			buttons.add((Button) temp);
		}
		Collections.addAll(labels, player1name, player1wins, player1score, player2name, player2wins, player2score, lblOnMove);
		((AnchorPane) gridPane.getParent()).setBackground(options.getBackground());
		game = new Game(buttons, labels);
		imgGear.setImage(Assets.GEAR);
		player1name.setTooltip(new Tooltip(options.getPlayer1().getName()));
		player2name.setTooltip(new Tooltip(options.getPlayer2().getName()));
	}

	@FXML
	private void action(ActionEvent e) {
		game.click((Button) e.getSource());
	}

	@FXML
	private void newGame() {
		game.newGame();
	}

	@FXML
	private void options() throws IOException {
		Stage stage = new Stage();
		stage.setScene(new Scene(FXMLLoader.load(getClass().getResource(Assets.GAME_OPTINS))));
		stage.show();
	}

	@FXML
	private void changePlayers() {
		options.switchStage("options");
	}

}