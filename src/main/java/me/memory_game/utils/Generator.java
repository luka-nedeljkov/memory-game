package me.memory_game.utils;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Random;

public class Generator {

	public static void generate(ArrayList<Button> buttons) {
		int[][] matrix = generateMatrix();
		for(int i = 0; i < buttons.size(); i++) {
			((ImageView) buttons.get(i).getGraphic()).setImage(Assets.getImage(matrix[i / 4][i % 4] - 1, Options.getInstance().getImageType()));
		}
	}

	private static int[][] generateMatrix() {
		Random random = new Random();
		int[][] matrix = new int[4][4];
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				int temp;
				do {
					temp = random.nextInt(8) + 1;
				} while(specialDuplicate(matrix, temp));
				matrix[i][j] = temp;
			}
		}
		return matrix;
	}

	private static boolean specialDuplicate(int[][] matrix, int x) {
		int counter = 0;
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				if(matrix[i][j] == x) {
					counter++;
				}
			}
		}
		return counter > 1;
	}

}
