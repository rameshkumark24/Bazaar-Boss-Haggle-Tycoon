package io.github.rameshkumark24.bazaarboss.core.app;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

/**
 * The empty project: proves the stack runs on a device. It draws one label on a plain
 * background and does nothing else. Replaced by the real game in M0.
 */
public class BazaarBossGame extends ApplicationAdapter {

    private SpriteBatch batch;
    private BitmapFont font;

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.getData().setScale(3f);
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.137f, 0.188f, 0.369f, 1f);
        batch.begin();
        font.draw(batch, "Bazaar Boss - empty project", 40f, Gdx.graphics.getHeight() / 2f);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
