package me.memory_game.game;

public class Player {

	private String name;
	private int wins;
	private int score;

	public Player(String name, int wins) {
		this.name = name;
		this.wins = wins;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWins() {
		return wins;
	}

	public void tie() {
		wins++;
	}

	public void win() {
		wins += 2;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Player{" +
				"name='" + name + '\'' +
				", wins=" + wins +
				", score=" + score +
				'}';
	}

}
