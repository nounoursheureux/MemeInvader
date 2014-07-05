package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameOver implements Screen {
	
	private MyGdxGame game;
	private BitmapFont font;
	private String str, str_high;
	private SpriteBatch batch;
	
	GameOver(MyGdxGame game, int score, int highScore) {
		this.game = game;
		batch = new SpriteBatch();
		font = new BitmapFont();
		str = new String();
		str = "Your score : " + Integer.toString(score);
		str_high = new String("Best score : " + Integer.toString(highScore));
	}
	@Override
    public void render(float delta) {        
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		font.draw(batch, "GAME OVER", 280, 270);
		font.draw(batch, str, 280, 240);
		font.draw(batch, str_high, 280, 210);
		batch.end();
		if (Gdx.input.isTouched()) game.setScreen(new GameScreen(game));
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
