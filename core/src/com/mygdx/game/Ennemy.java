package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

public class Ennemy extends Rectangle {
	private boolean go_right = true;
	private Texture nyan_texture_r, nyan_texture_l;

	Ennemy(float x, float y, float width, float height) {
		super(x, y, width, height);
		nyan_texture_r = new Texture(Gdx.files.internal("nyan_cat_right.png"));
		nyan_texture_l = new Texture(Gdx.files.internal("nyan_cat_left.png"));
	}
	
	Texture getTexture() {
		if (go_right) return nyan_texture_r;
		if (!go_right) return nyan_texture_l;
		return nyan_texture_r;
	}
	
	boolean getDirection() {
		return go_right;
	}
	
	void changeDirection() {
		go_right = !go_right;
	}
	
	void goLeft() {
		go_right = false;
	}
	
	void goRight() {
		go_right = true;
	}
}
