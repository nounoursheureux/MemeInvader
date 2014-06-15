package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class Trollface extends Rectangle {
	private Texture trollface_l;
	private Texture trollface_r;
	private boolean go_right;
	
	Trollface(float x, float y, float width, float height) {
		super(x, y, width, height);
		trollface_l = new Texture(Gdx.files.internal("trollface_left.png"));
		trollface_r = new Texture(Gdx.files.internal("trollface_right.png"));
	}
	
	Texture getTexture() {
		if (go_right) return trollface_r;
		if (!go_right) return trollface_l;
		return trollface_r;
	}
	
	void moveLeft() {
		x -= 600 * Gdx.graphics.getDeltaTime();
		if (go_right) go_right = false;
	}
	
	void moveRight() {
		x += 600 * Gdx.graphics.getDeltaTime();
		if (!go_right) go_right = true;
	}
	
	void fire(World world) {
		Bullet projectile = new Bullet((float)x + 75, (float)y, 32.0f, 32.0f);
		world.resetFireTimer();
		world.addProjectile(projectile);
	}
}
