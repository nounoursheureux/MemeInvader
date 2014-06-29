package com.mygdx.game;

import java.util.Iterator;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;
import java.lang.Enum;

public class GameScreen implements Screen {
	private MyGdxGame game;
	SpriteBatch batch;
	Trollface trollface;
	long lastDropTime;
	int i;
	boolean go_right = true;
	long lastFireTime;
	World world;
	BitmapFont font;
	String str_score;
	Texture heart, arrow_l, arrow_r;
	
	GameScreen(MyGdxGame game) {
		this.game = game;
		batch = new SpriteBatch();
		trollface = new Trollface(320, 20);
		world = new World();
		world.addEnnemy(20.0f, 400.0f);
		world.addEnnemy(200.0f, 400.0f);
		world.addEnnemy(400.0f, 400.0f);
		world.addEnnemy(70.0f, 300.0f);
		world.addEnnemy(240.0f, 300.0f);
		world.addEnnemy(430.0f, 300.0f);
		font = new BitmapFont();
		str_score = new String();
		heart = new Texture(Gdx.files.internal("heart.png"));
		arrow_l = new Texture(Gdx.files.internal("arrow_l.png"));
		arrow_r = new Texture(Gdx.files.internal("arrow_r.png"));
	}
	
	@Override
    public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		font.draw(batch, str_score, 20, 380);
		for (i = 0; i < world.getHealth(); i++) batch.draw(heart, 20 + 50 * i, 400);
		trollface.draw(batch);
		for (Meteorite meteorite: world.getMeteorites()) meteorite.draw(batch);
		for (Ennemy ennemy: world.getEnnemies()) ennemy.draw(batch);
		for (Bullet projectile: world.getProjectiles()) batch.draw(projectile.getTexture(), projectile.x, projectile.y);
		if (Gdx.app.getType() == Application.ApplicationType.Android) batch.draw(arrow_l, 20, 20);
		if (Gdx.app.getType() == Application.ApplicationType.Android) batch.draw(arrow_r, 168, 20);
		if (world.getBonus() != null) {
			world.getBonus().draw(batch);
			System.out.println("DRAAAAAAW");
		}
		batch.end();
		if (Gdx.input.isTouched() && Gdx.input.getX() > 296 && Gdx.input.getX() < 640 && world.fireReady()) trollface.fire(world);
		if (Gdx.input.isTouched(1) && Gdx.input.getX(1) > 296 && Gdx.input.getX(1) < 640 && world.fireReady()) trollface.fire(world);
		if(Gdx.input.isKeyPressed(Keys.LEFT)) trollface.moveLeft();
		if(Gdx.input.isKeyPressed(Keys.RIGHT)) trollface.moveRight();
		if (Gdx.input.isKeyPressed(Keys.SPACE) && world.fireReady()) trollface.fire(world);
		if (Gdx.input.isKeyPressed(Keys.ENTER)) world.createBomb(100, 300, "bomb");
		if (Gdx.input.isTouched() && Gdx.input.getX() <= 148 && Gdx.input.getX() >= 20 && Gdx.input.getY() <= 460 && Gdx.input.getY() >= 332) trollface.moveLeft();
		if (Gdx.input.isTouched() && Gdx.input.getX() <= 296 && Gdx.input.getX() >= 168 && Gdx.input.getY() <= 460 && Gdx.input.getY() >= 332) trollface.moveRight();
		if (Gdx.input.isTouched(1) && Gdx.input.getX(1) <= 148 && Gdx.input.getX(1) >= 20 && Gdx.input.getY(1) <= 460 && Gdx.input.getY(1) >= 332) trollface.moveLeft();
		if (Gdx.input.isTouched(1) && Gdx.input.getX(1) <= 296 && Gdx.input.getX(1) >= 168 && Gdx.input.getY(1) <= 460 && Gdx.input.getY(1) >= 332) trollface.moveRight();
		if(trollface.getX() < 0) trollface.setX(0);
		if (trollface.getX() > 552) trollface.setX(552);
		Iterator<Bullet> iter_projectiles = world.getProjectiles().iterator();
		while (iter_projectiles.hasNext()) {
			Bullet projectile = iter_projectiles.next();
			projectile.move();
			if (projectile.y >= 480) iter_projectiles.remove();
		}
		if(TimeUtils.nanoTime() - world.getLastDropTime() > 500000000) world.spawnMeteorite();
		Iterator<Meteorite> iter_meteorites = world.getMeteorites().iterator();
		while(iter_meteorites.hasNext()) {
			Meteorite meteorite = iter_meteorites.next();
			meteorite.translateY(-500 * Gdx.graphics.getDeltaTime());
			if (meteorite.getY() + 32 < 0) iter_meteorites.remove();
			if (meteorite.getBoundingRectangle().overlaps(trollface.getBoundingRectangle())) {
				System.out.println("Plif plaf plof");
				iter_meteorites.remove();
				world.decHealth();
			}
		}
		Iterator<Ennemy> iter_ennemy = world.getEnnemies().iterator();
		while(iter_ennemy.hasNext()) {
			Ennemy ennemy = iter_ennemy.next();
			if (ennemy.getDirection()) ennemy.goRight();
			if (!ennemy.getDirection()) ennemy.goLeft();
			if (ennemy.getX() <= 0) ennemy.changeDirectionRight(); 
			if (ennemy.getX() >= 540) ennemy.changeDirectionLeft();
			for (i = 0; i < world.getProjectiles().size; i++) {
				if (ennemy.getBoundingRectangle().overlaps(world.getProjectiles().get(i))) {
					iter_ennemy.remove();
					world.addTimer();
					world.removeProjectile(i);
					world.incScore();
					System.out.println("Touche !");
				}
			}
		}
		if (world.getBonus() != null) {
			System.out.println(world.getBonus().getY());
			world.getBonus().move();
			if (world.getBonus().getBoundingRectangle().overlaps(trollface.getBoundingRectangle())) world.destroyBonus();
		}
		if (world.getHealth() <= 0) {
			game.setScreen(new GameOver(game));
		}
		str_score = "Score: " + Integer.toString(world.getScore());
		if (world.checkSpawn()) world.addEnnemy((float)MathUtils.random(0, 540), (float)MathUtils.random(300,400));
	}
 
    @Override
    public void resize(int width, int height) {
    }
 
    @Override
    public void show() {
    }
 
    @Override
    public void hide() {
    }
 
    @Override
    public void pause() {
    }
 
    @Override
    public void resume() {
    }
 
    @Override
    public void dispose() {
    }

}
