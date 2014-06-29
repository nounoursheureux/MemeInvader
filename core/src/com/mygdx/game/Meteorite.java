package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class Meteorite extends Sprite {
	private Texture sun;
	
	Meteorite(float x, float y) {
		super(new Texture(Gdx.files.internal("sun.png")));
		setPosition(x,y);
		sun = new Texture(Gdx.files.internal("sun.png"));
	}
}
