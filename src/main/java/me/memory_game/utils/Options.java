package me.memory_game.utils;

import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import me.memory_game.game.Player;

import java.io.*;

public class Options {

	private static Options instance;

	private Player player1;
	private Player player2;

	private Background background;
	private int transitionLength;
	private String transitionType;
	private String imageType;
	private int transitionDelay;

	private CardTransition cardTransition;

	public static final Background DEFAULT_COLOR = new Background(new BackgroundFill(Color.rgb(242, 242, 242), CornerRadii.EMPTY, Insets.EMPTY));
	public static final int DEFAULT_TRANSITON_LENGTH = 250;
	public static final String DEFAULT_TRANSITION_TYPE = "Rotate";
	public static final int DEFAULT_TRANSITION_DELAY = 2000;
	public static final String DEFAULT_IMAGE_TYPE = "Birds";

	private Options() {
		init();
	}

	public static Options getInstance() {
		if(instance == null) {
			instance = new Options();
		}
		return instance;
	}

	public void init() {
		File file = new File(System.getenv("APPDATA") + "\\Memory Game\\options.dat");
		BufferedReader br;

		try {
			file.createNewFile();
			br = new BufferedReader(new FileReader(file));

			String background = br.readLine();
			String transitionLength = br.readLine();
			String transitionType = br.readLine();
			String transitionDelay = br.readLine();
			String imageType = br.readLine();

			if(background == null || transitionLength == null || transitionType == null || transitionDelay == null || imageType == null) {
				if(background == null) {
					this.background = DEFAULT_COLOR;
				}
				if(transitionLength == null) {
					this.transitionLength = DEFAULT_TRANSITON_LENGTH;
				}
				if(transitionType == null) {
					this.transitionType = DEFAULT_TRANSITION_TYPE;
				}
				if(transitionDelay == null) {
					this.transitionDelay = DEFAULT_TRANSITION_DELAY;
				}
				if(imageType == null) {
					this.imageType = DEFAULT_IMAGE_TYPE;
				}
				cardTransition = new CardTransition(this.transitionType, this.transitionLength);
				return;
			}
			this.background = new Background(new BackgroundFill(Color.web(background.substring(2)), CornerRadii.EMPTY, Insets.EMPTY));
			this.transitionLength = Integer.parseInt(transitionLength);
			this.transitionType = transitionType;
			this.transitionDelay = Integer.parseInt(transitionDelay);
			this.imageType = imageType;
			cardTransition = new CardTransition(this.transitionType, this.transitionLength);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void apply(AnchorPane ap) {
		ap.setBackground(background);
		cardTransition = new CardTransition(this.transitionType, this.transitionLength);
	}

	private void write() {
		try {
			FileWriter fw = new FileWriter(System.getenv("APPDATA") + "\\Memory Game\\options.dat", false);
			fw.append(background.getFills().get(0).getFill().toString()).append("\n")
					.append(Integer.toString(transitionLength)).append("\n")
					.append(transitionType).append("\n")
					.append(Integer.toString(transitionDelay)).append("\n")
					.append(imageType);
			fw.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void switchStage(String stage) {
		StageUtils.getInstance().switchStage(stage);
	}

	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}

	public Background getBackground() {
		return background;
	}

	public void setBackground(Background background) {
		this.background = background;
		write();
	}

	public int getTransitionLength() {
		return transitionLength;
	}

	public void setTransitionLength(int transitionLength) {
		this.transitionLength = transitionLength;
		write();
	}

	public String getTransitionType() {
		return transitionType;
	}

	public void setTransitionType(String transitionType) {
		this.transitionType = transitionType;
		write();
	}

	public int getTransitionDelay() {
		return transitionDelay;
	}

	public void setTransitionDelay(int transitionDelay) {
		this.transitionDelay = transitionDelay;
		write();
	}

	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
		write();
	}

	public CardTransition getCardTransition() {
		return cardTransition;
	}

	public void setCardTransition(CardTransition cardTransition) {
		this.cardTransition = cardTransition;
	}

	public void delay(long millis, Runnable continuation) {
		Task<Void> sleeper = new Task<>() {
			@Override
			protected Void call() {
				try {
					Thread.sleep(millis);
				} catch (InterruptedException ignored) {}
				return null;
			}
		};
		sleeper.setOnSucceeded(event -> continuation.run());
		new Thread(sleeper).start();
	}

}
