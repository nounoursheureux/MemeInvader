package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Bullet extends Rectangle {
	private Texture pacman;
	
	Bullet(float x, float y, float width, float height) {
		super(x, y, width, height);
		pacman = new Texture(Gdx.files.internal("pacman.png"));
	}
	
	Texture getTexture() {
		return pacman;
	}
	
	void move() {
		y += 700 * Gdx.graphics.getDeltaTime();
	}
}
