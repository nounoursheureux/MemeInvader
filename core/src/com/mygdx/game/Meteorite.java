package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Meteorite extends Rectangle {
	private Texture sun;
	
	Meteorite(float x, float y, float width, float height) {
		super(x, y, width, height);
		sun = new Texture(Gdx.files.internal("sun.png"));
	}
	
	Texture getTexture() {
		return sun;
	}
}
