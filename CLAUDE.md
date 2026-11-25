# Bazaar Boss: Haggle Tycoon

An offline, single-player Android haggling game, built with libGDX in Java.

## Read first

- **[docs/PRD.md](docs/PRD.md)** — the product, and the source of truth. Change the product there first.
- **[docs/TRD.md](docs/TRD.md)** — how it's built: the stack, the data model, the decision log.
- **[docs/APP-FLOW.md](docs/APP-FLOW.md)** — how the screens connect: navigation, back, launch routing, and one row per screen.
- **[docs/DESIGN.md](docs/DESIGN.md)** — how it looks, moves and speaks: the scales, the colour roles, the components, the copy rules, and the §10 visual checklist.
- **[docs/EDGE-CASES.md](docs/EDGE-CASES.md)** — what can go wrong, ranked, with the slice each case belongs to (§1.1) and verified ways to reproduce it (§7). Before building a slice, handle its cases.
- **[docs/LLD.md](docs/LLD.md)** — the low-level design: the feature packages and the dependency rule (§1–§2), where every PRD §7 rule lives (§2.4), the interfaces, errors, hot paths, and what tests each slice owes (§7.5). New code follows it; a change to it is a decision.
- **[docs/BUILD-PLAN.md](docs/BUILD-PLAN.md)** — the 42 phases. **"Build the next phase"** means:
  - read its CURRENT STATE
  - take the lowest-numbered phase that isn't DONE and whose DEPENDS ON are all DONE
  - follow its contract: plan mode first where it says so; PROGRESS updated during the phase; every DONE WHEN check shown with its evidence
  - update CURRENT STATE at the end, then stop and report
- **[docs/MONETISATION.md](docs/MONETISATION.md)** — how the game makes money: cosmetic purchases right after G3, ads only past ~3,000 installs a month. Read it before the purchase or ad slices.
- `docs/IDEA.md`, `docs/RESEARCH.md` and `docs/COMPETITORS.md` are the background they were built from.

## Rules that are easy to break

- **Work milestone by milestone.** After each one, stop: say what to test on the phone, list the known issues, and wait for "go".
- **Ask before installing or downloading anything.** Accounts, logins and signing keys are the owner's to create.
- **Free tools and free tiers only.** Anything paid needs the owner's explicit yes, with its price and the free option beside it.
- **No permission off the allowlist, and no SDK that sends data** (PRD §8.5, CR13). Change the data table in the PRD first.
- **Build scripts are Groovy and use `prop = value` assignments.** The space syntax is removed in Gradle 10.
- **Shipped Java compiles against the Java 8 API** (`--release 8`), with `-Xlint:all -Werror`. A warning fails the build.
- **Never modify the original engine or Bus Mudhalali repos.** Copy code in.
- **Every screen is proven by screenshot.** Screenshot it, including its empty and error states and XL text, and answer DESIGN.md §10's checklist. Any "no" is a bug.
- **Colours come only from `docs/design/palette.py`** (`python -I docs/design/palette.py` exits non-zero if any contrast pair fails). Spacing, type sizes and radii come only from DESIGN.md's scales.

## Commands

Every command below was run successfully on the owner's Windows 11 PC on 2026-11-24, in Git Bash. In PowerShell or cmd, use `.\gradlew.bat` instead of `./gradlew`.

**Setup, once per machine:**
- JDK 17 or newer as `java` on PATH (here: Oracle JDK 17.0.11).
- The Android SDK path in `local.properties` (not in git), e.g. `sdk.dir=C\:\\Users\\Asus\\AppData\\Local\\Android\\Sdk`.

```bash
SDK="$LOCALAPPDATA/Android/Sdk"          # the Android SDK on this PC
ADB="$SDK/platform-tools/adb.exe"
```

| What | Command | Proof it works |
|---|---|---|
| **Every check:** compile with `-Xlint:all -Werror`, Android Lint, unit tests | `./gradlew check` | `BUILD SUCCESSFUL` |
| Typecheck only | `./gradlew :logic:compileJava :logic:compileTestJava :core:compileJava :lwjgl3:compileJava :android:compileDebugJavaWithJavac` | `BUILD SUCCESSFUL` |
| Lint only | `./gradlew :android:lintDebug` | `BUILD SUCCESSFUL`; report at `android/build/reports/lint-results-debug.html`. One warning is expected: `MissingApplicationIcon` (the icon arrives with the art) |
| Tests only | `./gradlew :logic:test` | `SmokeTest > theTestRunnerRuns() PASSED` |
| The desktop window (development only) | `./gradlew :lwjgl3:run` | A 405 × 720 window opens; `BUILD SUCCESSFUL` once it closes. Verified 2026-11-25 (Phase 4): 8 s, even with `--offline` |
| List the virtual devices | `"$SDK/emulator/emulator.exe" -list-avds` | `LowEnd_A9`, `Medium_Phone_API_36.1` |
| Start the emulator — run it in the background | `"$SDK/emulator/emulator.exe" -avd Medium_Phone_API_36.1 -no-snapshot-save -no-boot-anim` | A window opens; `"$ADB" devices` shows `emulator-5554 device` |
| Wait until it has booted | The script below — run it in the background | `BOOTED after ~57s` |
| Install the debug build | `./gradlew :android:installDebug` | `Installed on 1 device.` |
| Launch the app | `"$ADB" shell am start -W -n io.github.rameshkumark24.bazaarboss/.android.AndroidLauncher` | `Status: ok` |
| **Screenshot — how every phase proves a screen** | `mkdir -p shots && "$ADB" exec-out screencap -p > shots/<name>.png` | A 1080 × 2400 PNG in `shots/` (not in git). Look at it before claiming a screen works. The status bar and navigation bar show — the game draws behind them, so keep the UI inside the safe insets (TRD TD-25) |
| Simulate a first-ever open: remove the app, then install it again | `"$ADB" uninstall io.github.rameshkumark24.bazaarboss` | `Success` |
| List the permissions in the built app | `"$SDK/build-tools/35.0.0/aapt2.exe" dump permissions android/build/outputs/apk/debug/android-debug.apk` | Only AndroidX's app-private `…DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION` (TRD TD-24) — no internet |
| Stop the emulator | `"$ADB" emu kill` | `OK: killing emulator, bye bye` |

The boot wait, exactly as it ran:

```bash
for i in $(seq 1 120); do s=$("$ADB" shell getprop sys.boot_completed 2>/dev/null | tr -d '\r'); if [ "$s" = "1" ]; then echo "BOOTED after ~$((i*3))s"; "$ADB" devices; exit 0; fi; sleep 3; done; echo "TIMEOUT: not booted after 360s"; "$ADB" devices; exit 1
```

**Not verified yet** — run one successfully before adding it to the table:
- installing on a real phone
- the API 24 emulator (no system image on this PC yet; TRD TD-23)
- `./gradlew :android:bundleRelease` (needs the upload key — TRD §1.3, Ship)
- Maestro (deferred until there are screens to tap through)

**Known harmless warning:** "SDK processing. This version only understands SDK XML versions up to 3…". Android Studio's newer SDK manager wrote files that AGP 8.13 only partly reads. The build is unaffected.

**Git Bash rewrites device paths:** an adb argument starting with `/` becomes a Windows path (`/data` → `C:/Program Files/Git/data`). Prefix such commands with `MSYS_NO_PATHCONV=1`. Verified 2026-11-25, with more reproduction commands (process death, storage full, clock, font and display size, navigation mode) in [docs/EDGE-CASES.md](docs/EDGE-CASES.md) §7.
