package io.github.rameshkumark24.bazaarboss.lwjgl3;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import io.github.rameshkumark24.bazaarboss.BazaarBossGame;

/** Starts the game in a portrait desktop window, for development only. */
public final class Lwjgl3Launcher {

    private Lwjgl3Launcher() {
    }

    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Bazaar Boss (dev)");
        config.setWindowedMode(405, 720);
        new Lwjgl3Application(new BazaarBossGame(), config);
    }
}
