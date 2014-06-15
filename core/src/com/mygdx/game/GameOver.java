package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameOver implements Screen {
	
	private MyGdxGame game;
	private BitmapFont font;
	private String str;
	private SpriteBatch batch;
	
	GameOver(MyGdxGame game) {
		this.game = game;
		batch = new SpriteBatch();
		font = new BitmapFont();
		str = new String("GAME OVER");
	}
	@Override
    public void render(float delta) {        
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		font.draw(batch, str, 300, 240);
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
