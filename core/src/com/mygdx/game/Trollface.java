package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class Trollface extends Sprite {
	private Texture trollface_l;
	private Texture trollface_r;
	private boolean go_right;
	
	Trollface(int x, int y) {
		super(new Texture(Gdx.files.internal("trollface_right.png")));
		setPosition(x,y);
		trollface_l = new Texture(Gdx.files.internal("trollface_left.png"));
		trollface_r = new Texture(Gdx.files.internal("trollface_right.png"));
	}
	
	void moveLeft() {
		translateX(-600 * Gdx.graphics.getDeltaTime());
		if (go_right) {
			go_right = false;
			setTexture(trollface_l);
		}
	}
	
	void moveRight() {
		translateX(600 * Gdx.graphics.getDeltaTime());
		if (!go_right) { 
			go_right = true;
			setTexture(trollface_r);
		}
	}
	
	void fire(World world) {
		Bullet projectile = new Bullet(getX() + 75, getY(), 32.0f, 32.0f);
		world.resetFireTimer();
		world.addProjectile(projectile);
	}
}
