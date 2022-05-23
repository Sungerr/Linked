package com.jonesclass.sung;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.jonesclass.sung.screens.GameOverScreen;
import com.jonesclass.sung.screens.GameScreen;
import com.jonesclass.sung.screens.HighScoreScreen;
import com.jonesclass.sung.screens.MenuScreen;

public class Main extends Game {
	private Screen[] screens = new Screen[4];

	@Override
	public void create () {
		screens[0] = new MenuScreen(this);
		screens[1] = new HighScoreScreen(this);
		screens[2] = new GameScreen(this);
		screens[3] = new GameOverScreen(this);

		this.setScreen(screens[0]);
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
	}

	public Screen getScreen(String screenName) {
		Screen screen;
		switch (screenName) {
			case "Menu":
				screen = screens[0];
				break;
			case "HighScore":
				screen = screens[1];
				break;
			case "Game":
				screen = screens[2];
				break;
			case "GameOver":
				screen = screens[3];
				break;
			default:
				screen = screens[0];
				break;
		}
		return screen;
	}
}
