package me.memory_game.game;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import me.memory_game.utils.Generator;
import me.memory_game.utils.Options;
import me.memory_game.utils.SQLUtils;

import java.util.ArrayList;

public class Game {

	Options options = Options.getInstance();
	SQLUtils sqlUtils = SQLUtils.getInstance();

	ArrayList<Button> buttons;
	ArrayList<Label> labels;

	Move move;
	Player onMove;

	boolean blockClickEvent;

	public Game(ArrayList<Button> buttons, ArrayList<Label> labels) {
		this.buttons = buttons;
		this.labels = labels;
		init();
	}

	public void init() {
		newGame();
	}

	public void click(Button button) {
		if(blockClickEvent) {
			return;
		}
		if(move == null) {
			move = new Move(onMove);
		}
		if(move.first != null) {
			if(move.first.equals(button)) {
				return;
			}
		}
		options.getCardTransition().open(button);
		if(move.first == null) {
			move.first = button;
			return;
		}
		move.second = button;

		if(move.success()) {
			winCheck();
			move = null;
			return;
		}
		closeCards();
	}

	public void closeCards() {
		blockClickEvent = true;
		options.delay(options.getTransitionDelay(), () -> {
			changePlayer();
			options.getCardTransition().close(move.first);
			options.getCardTransition().close(move.second);
			move = null;
			blockClickEvent = false;
		});
	}

	public void winCheck() {
		Player p1 = options.getPlayer1();
		Player p2 = options.getPlayer2();
		if(p1.getScore() + p2.getScore() == 8) {
			if(p1.getScore() > p2.getScore()) {
				p1.win();
				sqlUtils.update(p1.getName(), p1.getWins());
			} else if(p2.getScore() > p1.getScore()) {
				p2.win();
				sqlUtils.update(p2.getName(), p2.getWins());
			} else {
				p1.tie();
				p2.tie();
				sqlUtils.update(p1.getName(), p1.getWins());
				sqlUtils.update(p2.getName(), p2.getWins());
			}
			newGame();
		}
		refreshLabels();
	}

	public void newGame() {
		changePlayer(options.getPlayer1());
		options.getPlayer1().setScore(0);
		options.getPlayer2().setScore(0);
		for(Button temp : buttons) {
			temp.getGraphic().setVisible(false);
			temp.setDisable(false);
		}
		Generator.generate(buttons);
		refreshLabels();
	}

	public void changePlayer() {
		if(onMove.equals(options.getPlayer1())) {
			changePlayer(options.getPlayer2());
			return;
		}
		changePlayer(options.getPlayer1());
	}

	public void changePlayer(Player player) {
		Label label = labels.get(6);
		onMove = player;
		if(onMove.equals(options.getPlayer1())) {
			label.setText("Player 1");
			label.setTextFill(Color.RED);
			return;
		}
		label.setText("Player 2");
		label.setTextFill(Color.BLUE);
	}

	public void refreshLabels() {
		labels.get(0).setText(options.getPlayer1().getName());
		labels.get(1).setText(Integer.toString(options.getPlayer1().getWins()));
		labels.get(2).setText(Integer.toString(options.getPlayer1().getScore()));
		labels.get(3).setText(options.getPlayer2().getName());
		labels.get(4).setText(Integer.toString(options.getPlayer2().getWins()));
		labels.get(5).setText(Integer.toString(options.getPlayer2().getScore()));
	}

}
