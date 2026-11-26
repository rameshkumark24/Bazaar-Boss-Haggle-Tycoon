package io.github.rameshkumark24.bazaarboss.android;

import android.util.Log;
import io.github.rameshkumark24.bazaarboss.devtools.DevMenu;

/** Debug builds: plugs the dev tools in (LLD §1). The release build's twin does nothing. */
final class DevToolsHook {

    private DevToolsHook() {
    }

    static void install() {
        Log.i("BazaarBoss", DevMenu.describe());
    }
}
