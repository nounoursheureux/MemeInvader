package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

public class Ennemy extends Sprite {
	private boolean go_right = true;
	private Texture nyan_texture_r, nyan_texture_l;

	Ennemy(float x, float y) {
		super(new Texture("nyan_cat_right.png"));
		setPosition(x,y);
		nyan_texture_r = new Texture(Gdx.files.internal("nyan_cat_right.png"));
		nyan_texture_l = new Texture(Gdx.files.internal("nyan_cat_left.png"));
	}
	
	boolean getDirection() {
		return go_right;
	}
	
	void changeDirectionLeft() {
		go_right = false;
		setTexture(nyan_texture_l);
	}
	
	void changeDirectionRight() {
		go_right = true;
		setTexture(nyan_texture_r);
	}
	
	void goLeft() {
		translateX(-500 * Gdx.graphics.getDeltaTime());
	}
	
	void goRight() {
		translateX(500 * Gdx.graphics.getDeltaTime());
	}
}
