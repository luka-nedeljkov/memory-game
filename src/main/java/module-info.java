module me.memory_game {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;


	opens me.memory_game to javafx.fxml;
	exports me.memory_game;
	exports me.memory_game.ui.controllers;
	opens me.memory_game.ui.controllers to javafx.fxml;
	exports me.memory_game.ui;
	opens me.memory_game.ui to javafx.fxml;
}