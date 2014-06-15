package com.mygdx.game;

import java.util.Vector;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import java.util.Iterator;

public class World {
	 
	private Array<Bullet> projectiles;
	private Array<Meteorite> meteorites;
	private Array<Ennemy> ennemies;
	private Array<Long> timers;
	private long lastFireTime;
	private long lastDropTime;
	private int score = 0;
	private int health = 3;
	private Long time;
	
	World() {
		projectiles = new Array<Bullet>();
		meteorites = new Array<Meteorite>();
		ennemies = new Array<Ennemy>();
		timers = new Array<Long>();
		lastFireTime = TimeUtils.nanoTime();
		lastDropTime = TimeUtils.nanoTime();
	}
	
	void resetFireTimer() {
		lastFireTime = TimeUtils.nanoTime();
	}
	
	void addProjectile(Bullet projectile) {
		projectiles.add(projectile);
	}
	
	void addEnnemy(float x, float y) {
		Ennemy ennemy = new Ennemy(x, y, 100.0f, 84.0f);
		ennemies.add(ennemy);
	}
	
	void spawnMeteorite() {
		Meteorite meteorite = new Meteorite((float)MathUtils.random(0, 640), 480.0f, 64.0f, 64.0f);
		meteorite.x = MathUtils.random(0, 576);
		meteorite.y = 480;
		meteorite.width = 64;
		meteorite.height = 64;
		meteorites.add(meteorite);
		lastDropTime = TimeUtils.nanoTime();
	}
	
	long getLastDropTime() {
		return lastDropTime;
	}
	
	Array<Meteorite> getMeteorites() {
		return meteorites;
	}
	
	Array<Bullet> getProjectiles() {
		return projectiles;
	}
	
	Array<Ennemy> getEnnemies() {
		return ennemies;
	}
	
	boolean fireReady() {
		if (TimeUtils.nanoTime() - lastFireTime > 500000000) return true;
		else return false;
	}
	
	void removeProjectile(int i) {
		projectiles.removeIndex(i);
	}
	
	int getScore() {
		return score;
	}
	
	int getHealth() {
		return health;
	}
	
	void incScore() {
		score++;
	}
	
	void decHealth() {
		health--;
	}
	
	void addTimer() {
		time = new Long(TimeUtils.nanoTime());
		timers.add(time);
	}
	
	boolean checkSpawn() {
		Iterator<Long> iter = timers.iterator();
		while (iter.hasNext()) {
			time = iter.next();
			if (TimeUtils.nanoTime() - time > 2000000000) {
				iter.remove();
				return true;
			}
		}
		return false;
	}
}
