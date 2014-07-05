package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Bonus extends Sprite {
	
	private String type;
	
	Bonus(int x,int y, String type) {
		super(new Texture(Gdx.files.internal("heart.png")));
		if (type == "bomb") setTexture(new Texture(Gdx.files.internal("bomb.png")));
		else if (type == "health") setTexture(new Texture(Gdx.files.internal("heart.png")));
		setPosition(x,y);
		this.type = new String();
		this.type = type;
	}
	
	void move() {
		translateY(-250 * Gdx.graphics.getDeltaTime());
	}
	
	String getType() {
		return type;
	}
}
