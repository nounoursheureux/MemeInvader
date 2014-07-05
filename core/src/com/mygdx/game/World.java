package com.mygdx.game;

import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.NumberUtils;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

public class World {
	 
	private Array<Bullet> projectiles;
	private Array<Meteorite> meteorites;
	private Array<Ennemy> ennemies;
	private Array<Long> timers;
	private Trollface trollface;
	private long lastFireTime;
	private long lastDropTime;
	private int score = 0;
	private Long time;
	private Long bombTimer;
	private Bonus bonus;
	Preferences pref = Gdx.app.getPreferences("preferences");
	int highScore = pref.getInteger("highscore", 0);
	
	World() {
		projectiles = new Array<Bullet>();
		meteorites = new Array<Meteorite>();
		ennemies = new Array<Ennemy>();
		timers = new Array<Long>();
		trollface = new Trollface(320, 20);
		lastFireTime = TimeUtils.nanoTime();
		lastDropTime = TimeUtils.nanoTime();
		bombTimer = TimeUtils.nanosToMillis(TimeUtils.nanoTime());
	}
	
	void resetFireTimer() {
		lastFireTime = TimeUtils.nanoTime();
	}
	
	void addProjectile(Bullet projectile) {
		projectiles.add(projectile);
	}
	
	void addEnnemy(float x, float y) {
		Ennemy ennemy = new Ennemy(x, y);
		ennemies.add(ennemy);
	}
	
	void spawnMeteorite() {
		Meteorite meteorite = new Meteorite((float)MathUtils.random(0, 640), 480.0f);
		//meteorite.x = MathUtils.random(0, 576);
		//meteorite.y = 480;
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
	
	void incScore() {
		score++;
	}
	
	void addTimer() {
		time = new Long(TimeUtils.nanoTime());
		timers.add(time);
	}
	
	void createBomb(int x, int y) {
		if (TimeUtils.nanosToMillis(TimeUtils.nanoTime()) - bombTimer >= 10000) {
			int type = MathUtils.random(0,1);
			switch (type) {
				case 0:
					bonus = new Bonus(x,y,"health" );
					break;
				case 1:
					bonus = new Bonus(x,y,"bomb");
					break;
			}
			bombTimer = TimeUtils.nanosToMillis(TimeUtils.nanoTime());
			System.out.println(type);		
		}
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
	
	Bonus getBonus() {
		return bonus;
	}
	
	void destroyBonus() {
		if (bonus.getType() == "health") trollface.incHealth();
		if (bonus.getType() == "bomb") {
			System.out.println("BOOOOMBE");
			Iterator<Ennemy> iter = getEnnemies().iterator();
			while (iter.hasNext()) {
				Ennemy ennemy = iter.next();
			}
			int size = getEnnemies().size;
			for (int i = 0; i < size; i++) {
				iter.remove();
				addTimer();
				incScore();
			}
		}
		bonus = null;
	}
	
	Trollface getTrollface() {
		return trollface;
	}
	
	int getHighScore() {
		return highScore;
	}
	
	void setHighScore() {
		pref.putInteger("highscore", score);
		pref.flush();
		highScore = pref.getInteger("highscore");
	}
}
