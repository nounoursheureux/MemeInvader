package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Bonus extends Sprite {
	
	Bonus(int x,int y, String type) {
		super(new Texture(Gdx.files.internal("sun.png")));
		setPosition(x,y);
		/*if (type == "bomb") {
			System.out.println("BOOOMBE");
			setTexture(new Texture(Gdx.files.internal("heart.png")));
		}*/
	}
	
	void move() {
		translateY(-50 * Gdx.graphics.getDeltaTime());
	}
}
