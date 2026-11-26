package io.github.rameshkumark24.bazaarboss.android;

import android.os.Bundle;
import android.view.WindowManager;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import io.github.rameshkumark24.bazaarboss.core.app.BazaarBossGame;

/** The Android entry point: hands the screen to the libGDX game. */
public class AndroidLauncher extends AndroidApplication {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        // Keep the system bars visible, so Android never shows its "Viewing full screen"
        // notice over the first screen (PRD F1; TRD TD-25). libGDX defaults this to true.
        config.useImmersiveMode = false;
        // The game never reads motion sensors; libGDX turns these on by default (battery).
        config.useAccelerometer = false;
        config.useCompass = false;
        initialize(new BazaarBossGame(), config);
        DevToolsHook.install();
        // libGDX's initialize() always sets FLAG_FULLSCREEN, which hides the status bar.
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}
