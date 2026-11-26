package io.github.rameshkumark24.bazaarboss.android;

/** Release builds: no dev tools, by construction (LLD §1, PRD §6). The debug twin plugs them in. */
final class DevToolsHook {

    private DevToolsHook() {
    }

    static void install() {
        // Release builds have no dev tools: nothing to plug in.
    }
}
