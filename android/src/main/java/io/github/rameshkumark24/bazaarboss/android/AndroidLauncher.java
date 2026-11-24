package io.github.rameshkumark24.bazaarboss.android;

import android.os.Bundle;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import io.github.rameshkumark24.bazaarboss.BazaarBossGame;

/** The Android entry point: hands the screen to the libGDX game. */
public class AndroidLauncher extends AndroidApplication {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        initialize(new BazaarBossGame(), config);
    }
}
