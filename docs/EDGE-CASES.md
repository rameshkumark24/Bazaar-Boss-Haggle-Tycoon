# Bazaar Boss: Haggle Tycoon — Edge Cases

Everything that can go wrong with this app, ranked, with where each fix belongs and how to reproduce it. Written 2026-11-25 from [PRD.md](PRD.md), [TRD.md](TRD.md), [APP-FLOW.md](APP-FLOW.md) and [RESEARCH.md](RESEARCH.md).

- **Verified** means it was run on 2026-11-25 (§7). **From memory** means it wasn't; the case says what would confirm it.
- **What this file changed:** one checklist line in `site/README.md` (OP-3). The gaps and conflicts it found (§5) were applied on 2026-11-25, as recommended, to the PRD, APP-FLOW, DESIGN, the TRD (TD-27 to TD-32) and the root `build.gradle` (BR-3).

---

## 0. How to read this

- **IDs** are by category: SV-1, LC-2 and so on. §2 maps the nine categories asked for, and adds the ones this app needs.
- **Score = L × D**, each 1–5, as in RESEARCH §1.
  - **L:** 5 every player or every build · 4 most players, often · 3 some players · 2 rarely · 1 hardly ever.
  - **D:** 5 progress or the game lost, or a store or legal block · 4 a wrong result the player can't undo, or weeks lost · 3 a feature broken, with a way round · 2 confusing or ugly · 1 cosmetic.
- **When:**
  - **v1 · N** — build it in slice N (the Business verdict's 17 slices, IDEA.md).
  - **Owner** — a step the owner takes, not code.
  - **M4**, **Purchases**, **Yearly** — waits for that release.
- **Fix in.** There's no server (TRD §2), so "client, server or both" becomes:
  - on the phone: `logic`, `core`, `android`
  - in the build: `build` (a Gradle guard or test), `content` (the validator), `sim` (the simulator)
  - at Google: `Play Console`, `AdMob`
  - `owner`: a process step
- **Reproduce** is one of: an adb command (the verified ones are in §7), a unit test in `logic`, a dev-build switch (§6), or by hand. Commands assume Git Bash with:

```bash
ADB="$LOCALAPPDATA/Android/Sdk/platform-tools/adb.exe"
PKG=io.github.rameshkumark24.bazaarboss
export MSYS_NO_PATHCONV=1   # verified: without it, Git Bash rewrites /data into C:/Program Files/Git/data
```

---

## 1. ⭐ The ranking

**95 cases:**

| Group | Count | Rule |
|---|---|---|
| **Must be handled before v1** | **49** | Every v1 case scoring 8 or more |
| Cheap; do in its slice, or watch in the closed test | 20 | v1 cases scoring 6 or less |
| Owner steps | 12 | MS-1 and OP-3 before the waitlist goes out; ST-1, MS-5 and AB-2 before the closed test |
| Wait for M4, purchases or a yearly bump | 12 | — |
| Already designed; nothing to build | 2 | DC-3, TC-3 |

Most v1 cases are one test, one clamp or one guard. The heavy ones are SV-1, DV-1 and LC-1 to LC-3: they decide how the save, numbers and screens are built, so they come first.

| # | Score | ID | What goes wrong | Where | When |
|---|---|---|---|---|---|
| 1 | 20 | SV-1 | Release builds rename the fields the save and content pack are read by | Every update | v1 · 3, 16 |
| 2 | 20 | DV-1 | Marathi, Bengali, Urdu phones draw prices as missing glyphs; a Turkish phone breaks the save | Every ₹ amount; `game.json` | v1 · 1, 3 |
| 3 | 16 | LC-3 | A restart loses a result the player is owed: verdict, auction estimates, tier-up | Deal result, Auction result, Tier-up | v1 · 3, then each screen |
| 4 | 16 | LC-2 | Changing font size, display size, dark mode or language rebuilds the whole game | Any screen | v1 · 3, 12 |
| 5 | 16 | IN-1 | A double tap applies an action twice, or acts on a finished haggle | Haggle, Auction, Sort, Kanakku, Low cash | v1 · 4, 6 |
| 6 | 16 | HE-1 | A quick button offers a price worse than the customer's own | Haggle, selling | v1 · 4 |
| 7 | 16 | DV-2 | At 316 dp wide with XL text, the Offer button leaves the screen | Haggle | v1 · 6, 12 |
| 8 | 16 | MS-2 | Testers don't send Share my stats, so G2 can't be judged | Settings → Share my stats | Owner; v1 · 16 |
| 9 | 16 | ST-1 | Fewer than 12 testers stay opted in for 14 days | Play Console closed track | Owner |
| 10 | 16 | MN-1 | Tester builds are release builds, so they carry the real ad IDs | Ad offers | M4 |
| 11 | 15 | SV-2 | A Java API that Android 7 lacks crashes the first save on API 24–25 | `SaveWriter` | v1 · 1 |
| 12 | 15 | SV-3 | A save with a fixable inconsistency is thrown away | Launch routing | v1 · 3 |
| 13 | 15 | LC-1 | Two copies of the game run in one process and overwrite each other's save | `AndroidLauncher` | v1 · 3, 12 |
| 14 | 15 | MS-1 | Duplicate or bot sign-ups decide the waitlist test | Google Form | Owner, now |
| 15 | 12 | SV-4 | A failed or partial write is taken for a good one | `SaveWriter` | v1 · 3 |
| 16 | 12 | SV-5 | With storage full, the player keeps playing unsaved without knowing | Every screen | v1 · 3, 12 |
| 17 | 12 | HE-2 | Buying below scrap value and scrapping prints money | Haggle, Godown, Sort | v1 · 5, 9, 14 |
| 18 | 12 | HE-3 | A habit the five bots never try beats every customer | `sim` | v1 · 5 |
| 19 | 12 | HE-5 | A frame stall skips the auction countdown to the hammer | Auction | v1 · 8 |
| 20 | 12 | ST-2 | Data safety, content rating or audience don't match the build | Play Console | v1 · 16 |
| 21 | 12 | HO-6 | The ads SDK starts work before the age and consent gate | Process start | M4 |
| 22 | 12 | HE-4 | The player outbids themselves | Auction | v1 · 8 |
| 23 | 12 | IN-2 | The owner's CSV arrives in the wrong encoding or with invisible characters | `content/strings.csv` | v1 · 2 |
| 24 | 12 | DV-3 | Buttons sit under the status bar or the gesture bar | Every screen | v1 · 1, 12 |
| 25 | 12 | CL-1 | A line misgenders the customer in Hinglish, Tanglish or English | Haggle, Deal result | v1 · 2, 13 |
| 26 | 12 | CL-2 | A line fits as written but overflows once `{item}` and `₹{price}` are filled | Haggle | v1 · 2, 6 |
| 27 | 12 | DC-1 | Number edges: ₹0, ₹1, losses, 99,99,999, a −10% that can't move | Haggle, Stall, Kanakku | v1 · 1, 4 |
| 28 | 10 | SV-6 | A crash while loading becomes a crash at every launch | Launch | v1 · 3, 15 |
| 29 | 10 | SV-7 | A save from a newer build is treated as damage and replaced | Launch routing | v1 · 3 |
| 30 | 10 | OP-1 | "Rolling back" by re-uploading old code locks players out | Play Console | Owner |
| 31 | 10 | BR-1 | AndroidX already exports a receiver; the planned guard would fail | `checkMergedManifest` | v1 · 15 |
| 32 | 10 | ST-3 | Play's yearly target-API rule blocks updates | Play Console | Yearly |
| 33 | 9 | SV-8 | Backups rotate on every load, so all three files end up identical | `files/save/` | v1 · 3 |
| 34 | 9 | LC-6 | Rivals keep bidding behind the notification shade or in split screen | Auction | v1 · 8 |
| 35 | 9 | HE-6 | A tap in the hammer's frame is lost; a resumed auction ends at once | Auction | v1 · 8 |
| 36 | 9 | HE-7 | A waiting customer's item is sold, scrapped or taken off display | Stall, Haggle | v1 · 10 |
| 37 | 9 | HE-8 | The godown limit conflicts with "Back on Sort keeps everything" | Sort, Godown | v1 · 9 |
| 38 | 9 | DV-4 | The empty project already needs 2.7–4.0 s to start on the emulator | Launch | v1 · 1, 14 |
| 39 | 9 | CL-3 | Dates on screen come out in the phone's language and script | Restored cards, Share my stats | v1 · 3, 11 |
| 40 | 9 | BR-2 | A phone-to-phone transfer copies files the backup rules exclude | Backup rules | v1 · 15 |
| 41 | 9 | HO-1 | Some email apps drop the report's subject and body | Report a problem | v1 · 15 |
| 42 | 9 | MS-3 | Start over or a reinstall resets a tester's stats mid-test | Share my stats | Owner; v1 · 15 |
| 43 | 9 | MS-4 | Time with the game open and untouched counts as play time | Stats → Check 3 | v1 · 11 |
| 44 | 9 | AB-1 | A deal card is forwarded as proof of real earnings | Deal result → Share | v1 · 11 |
| 45 | 9 | AB-2 | Testers or friends post reviews | Play listing | Owner |
| 46 | 9 | HO-5 | An ad load hangs, or a reward lands after its auction ended | Ad offers | M4 |
| 47 | 9 | MN-2 | The player pays, then the app dies before the purchase arrives | Shop | Purchases |
| 48 | 8 | SV-9 | Settings say a voice was chosen, but there's no save | Launch routing | v1 · 3, 12 |
| 49 | 8 | LC-4 | Start over races a queued save, and the old game comes back | Settings → Start over | v1 · 15 |
| 50 | 8 | HE-9 | Loan repayment rounds to ₹0 on small sales, so the loan never clears | Low cash | v1 · 10 |
| 51 | 8 | HE-10 | A "Last price?" or call-back price falls past the walk-away price | Haggle | v1 · 4 |
| 52 | 8 | EF-1 | The first haggle can't end in a deal, so the stall never appears | First haggle | v1 · 12, 14 |
| 53 | 8 | BR-4 | `ids-ever-shipped.txt` isn't updated at a release | `tools:validateContent` | v1 · 16 |
| 54 | 8 | ST-4 | A native library isn't aligned for 16 KB pages | App bundle | v1 · 16 |
| 55 | 8 | OP-2 | The launch email goes out from the support address | Game Gmail | Owner |
| 56 | 8 | OP-3 | The waitlist form shows every respondent the others' emails | Google Form | Owner, now (README fixed) |
| 57 | 8 | GT-1 | The ad gate is read when the offer is drawn, not when it's tapped | Ad offers | M4 |
| 58 | 8 | AB-3 | Bots reuse the game's public ad-unit IDs | AdMob | M4 |
| 59 | 8 | IN-3 | The slider can't land on the price the player wants | Haggle | v1 · 6 |
| 60 | 8 | DV-5 | The game redraws 60 times a second while the player reads | Every screen | v1 · 12 |
| 61 | 8 | CL-4 | The banned-word check blocks "betel box" or Tamil "sattai" | `content/banned/` | v1 · 2 |
| 62 | 8 | BR-3 | Android Studio's JDK 21 fails a build that passes on JDK 17 | Gradle | v1 · 1 |
| 63 | 6 | LC-5 | After the GL context is lost, customer faces come back blank | Haggle, Stall | v1 · 6 |
| 64 | 6 | DV-6 | Split screen or unfolding squeezes the layout | Every screen | v1 · 12 |
| 65 | 6 | DV-8 | With animations off, the reply shows before the save commits | Haggle | v1 · 6 |
| 66 | 6 | TC-1 | A wrong date at first launch puts 1970 into the stats | Share my stats | v1 · 11 |
| 67 | 6 | HO-3 | A slow fsync makes the haggle look frozen | Haggle | v1 · 3 |
| 68 | 6 | MS-5 | The simulator's prediction is adjusted after testers' data arrives | Check 3 | Owner |
| 69 | 6 | AB-4 | Clones or "earn money" APKs use the game's name | Third-party sites | Owner |
| 70 | 6 | MN-3 | Clock changes bend the 12-ads-a-day cap | Ad offers | M4 |
| 71 | 6 | HE-11 | The peek can be studied for as long as the player likes under Pause | Auction | v1 · 8 |
| 72 | 6 | HE-12 | Inspect costs the last point of patience and the customer leaves | Inspect | v1 · 7 |
| 73 | 6 | HE-13 | Leaving the auction of Today's special loses it without warning | Auction | v1 · 11 |
| 74 | 6 | IN-4 | A back swipe from the edge grabs the slider drag | Haggle | v1 · 6 |
| 75 | 6 | IN-5 | Fast taps through the Reveal land on the Sort list | Reveal → Sort | v1 · 9 |
| 76 | 6 | DV-7 | Changing text size mid-haggle leaves stale layouts | Settings → Text size | v1 · 12 |
| 77 | 6 | TC-2 | A brand-new player is greeted with "Welcome back" | Stall | v1 · 11 |
| 78 | 6 | DC-2 | The goal bar passes 100%, or a loss moves it back | Stall | v1 · 10 |
| 79 | 6 | BR-5 | Installing a debug build over the Play build wipes the owner's game | The owner's phone | v1 · 1 |
| 80 | 6 | HO-2 | A save confirmation lands after the player has moved on | Haggle | v1 · 6 |
| 81 | 6 | MN-4 | A double tap on Buy opens the purchase twice | Shop | Purchases |
| 82 | 6 | MN-5 | A UPI payment stays pending for days | Shop | Purchases |
| 83 | 5 | OP-4 | The upload key or the Google account's 2-step access is lost | Play Console | Owner |
| 84 | 4 | LC-7 | The game resumes after days without a restart and skips the day checks | Stall | v1 · 11 |
| 85 | 4 | DC-3 | Two phones on one Google account keep two separate games | Restore | Designed |
| 86 | 4 | HO-4 | A second share overwrites the card before the first app reads it | Deal result → Share | v1 · 11 |
| 87 | 4 | ST-5 | Play's pre-launch crawler can't see inside the game view | Play Console | v1 · 16 |
| 88 | 4 | OP-5 | Renaming the GitHub account breaks the privacy URL in every build | GitHub Pages | Owner |
| 89 | 4 | AB-5 | A GitHub break-in swaps the waitlist form for a phishing one | Waitlist page | Owner |
| 90 | 4 | GT-2 | The Play account on the phone changes | Shop | Purchases |
| 91 | 4 | MN-6 | A refunded theme is still equipped | Shop | Purchases |
| 92 | 3 | DV-9 | With TalkBack on, a double tap presses whatever is at the screen's centre | Every screen | v1 · 12 |
| 93 | 2 | EF-2 | An empty godown with a filter on shows the filter message | Godown | v1 · 9 |
| 94 | 2 | TC-3 | Flying west moves the calendar day back by one | Stall | Designed |
| 95 | 2 | AB-6 | Someone asks to remove an email address that isn't theirs | Requests log | Owner |

### 1.1 Before v1, in build order

| Slice | Cases to build in it |
|---|---|
| **1** project, fonts, ₹ | DV-1 (our own formatter, `Locale.ROOT`), SV-2 (the Android 7 API guard), BR-3 (done 2026-11-25), DV-3, DV-4 (measure), BR-5 |
| **2** content, validator | IN-2, CL-1, CL-2, CL-4 |
| **3** saves | SV-1, SV-3 to SV-9, LC-1 and LC-2 (one writer per process), LC-3 (the "owed result" field), DV-1 (save round-trip test), HO-3, CL-3 |
| **4–5** engine, bots | HE-1, HE-10, IN-1, DC-1, HE-3, HE-2 (sim check) |
| **6** haggle screen | IN-3, IN-4, LC-5, DV-8, HO-2, CL-2 (on screen) |
| **7–9** items, auction, reveal | HE-12, HE-4, HE-5, HE-6, HE-11, LC-6, HE-8, IN-5, EF-2 |
| **10–11** day loop, kanakku | HE-7, HE-9, DC-2, LC-7, TC-1, TC-2, MS-4, AB-1, HE-13, HO-4 |
| **12** voice picker, settings, back, resume | LC-1 (launch mode), LC-2 (config changes), DV-2, DV-5, DV-6, DV-7, DV-9, EF-1, SV-9 |
| **13–14** dialogue, tuning | CL-1 (writing), HE-2, EF-1 (tuning check), DV-4 (cheap-phone check) |
| **15** reports, Start over, guards | LC-4, BR-1, BR-2, HO-1, SV-6 (crash note), MS-3 |
| **16** release | SV-1 (R8 rules and the two-build test), BR-4, ST-2, ST-4, ST-5, MS-2 |

---

## 2. The categories

### 2.1 The nine asked for, as they exist here

| # | Asked for | What it is in this app | Cases |
|---|---|---|---|
| 1 | Empty and first run | First launch is the voice picker, then the first haggle (F1). The empty states are designed (F9, F10, PRD §6). **Search:** there's none; the godown filter is the nearest. **Deleted while open elsewhere:** nothing changes in the background in v1 (DR25), so only the player's own actions remove things | EF-1, EF-2, SV-9, HE-7, HE-12 |
| 2 | Network | **v1 can't use the network:** no internet permission (CR13), held by the allowlist guard. What's left is the hand-offs to other apps and the save writer's background commits. Offline, captive portals and hangs arrive with ads (M4) | HO-1 to HO-6, IN-1 (the same write twice) |
| 3 | Lifecycle | Process death, recreation, two instances. **Deep links:** none in v1, only the launcher (TRD §7). **Push:** never; local notifications after G3 are designed in APP-FLOW §1.6 | LC-1 to LC-7, SV-5 |
| 4 | Auth | No accounts or tokens (TRD §7). The gates stand in: first launch, the newer-save block, the M4 ad gate, the billing gate. Start over is the account deletion | SV-7, OP-1, LC-4, GT-1, GT-2 |
| 5 | Data and concurrency | One player, one phone, one writer thread. **A row deleted while edited:** a waiting customer's item. **Pagination:** none (TRD §4.9) | HE-7, LC-1, SV-4, DC-1 to DC-3 |
| 6 | Input | No text field anywhere (F13). The inputs are taps, the slider, back, and the owner's content files. Empty, whitespace, 10,000 characters, emoji, right-to-left marks, apostrophes and pasted formatting all reach the game only through those files | IN-1 to IN-5, CL-2 |
| 7 | Device | Storage, text size, screen size, rotation, permissions, battery, slow phones; plus the phone's language, which India makes a big one | DV-1 to DV-9, SV-5, LC-2 |
| 8 | Money | No real money in v1 (CR1). "Paying" means in-game cash applied exactly once. Real money arrives with ads and purchases | IN-1, LC-3, SV-1 to SV-9, MN-1 to MN-6 |
| 9 | Abuse | RESEARCH §1 rules moderation out: there's no player content. What's left is below | AB-1 to AB-6, MS-1 |

**"Permission permanently denied"** has no v1 case: vibration is granted at install, and nothing asks at runtime (F20). It arrives with notifications after G3: if Android stops showing the prompt, say once where to turn them on, and never ask again.

### 2.2 Added for this app

| Category | Why it's here |
|---|---|
| **SV · Save integrity** | One file holds the whole game (TRD §4). Losing it is the worst thing that can happen to a player, and a top complaint in the genre (R7) |
| **HE · Haggle and auction rules** | The game is its rules. One that pays out wrongly, or a habit that always wins, breaks it (DR6–DR19, R5, R6) |
| **TC · Time and calendar** | Today's special, the welcome-back lot and the stats follow the phone's clock (DR26, DR28) |
| **CL · Content and language** | Three voices, gendered verbs, a baked Latin-only font, and a banned-word list (F13, CR2) |
| **BR · Build and release builds** | Some bugs exist only in release builds, only on JDK 21, or only on Android 7 |
| **MS · Measurement** | Every go/stop decision rests on a form count or on text that testers paste (PRD §12) |
| **ST · Store and testers** | Production access needs 12 testers for 14 days (RESEARCH §3 #1) |
| **OP · Owner operations** | One person, 5 hours a week, and keys and URLs that can never change (TRD §6, §11) |
| **DC · Data boundaries** · **GT · Gates** | The real cases behind categories 5 and 4 |

---

## 3. The cases

Each case: the trigger · what happens if it isn't handled · what should happen · where the fix belongs · how to reproduce it. Cases scoring 5 or less are in the table in §4.

### 3.1 SV · Save integrity

#### SV-1 · Release builds rename what the save and content are read by — 20 · v1 · 3, 16
- **Trigger:** the save codec or the content loader uses libGDX's reflection-based `Json` class. R8, on for release builds (TRD §1.3), renames fields and strips the ones only reflection sets. Renaming a Java field in a refactor does the same without R8.
- **Unhandled:** at the first update, every save reads as damaged: "Couldn't read your save or its backups, so a new game has started." In release builds only, content fields such as `fakeChanceBp` can come back as 0, so the shipped game has no fakes. Debug builds look fine throughout.
- **Should:** string literals fix what's on disk. Renaming a field in code changes nothing there.
- **Fix in:** `logic` — read with `JsonReader`/`JsonValue`, write with `JsonWriter`, keys as literals; never `Json.fromJson`/`toJson` for the save or the pack. `build` — keep rules for anything still reflected. Tests — every fixture (`saves/vN.json`) compares values, not just "it parsed".
- **Reproduce:** rename one field of `GameState` and run the fixture test: it must fail. At slice 16, play a day on release build N, install release build N+1 that differs by one unrelated line, and launch: the day must be there.

#### SV-2 · A Java API that Android 7 doesn't have — 15 · v1 · 1
- **Trigger:** code in `logic` or `core` calls `java.nio.file.Files`, `Paths`, `File.toPath()`, `java.time`, `java.util.Base64` or `String.join`. **Verified** in the SDK's `api-versions.xml`: all arrive in API 26. `--release 8` allows them, because they're Java 8, and Android Lint doesn't check plain-Java modules.
- **Unhandled:** `NoClassDefFoundError` or `NoSuchMethodError` on Android 7.0–7.1 (API 24–25) at the first call. If that's the first save, the game never gets past the voice picker.
- **Should:** those calls can't enter the shipped modules. (`Math.addExact`, `multiplyExact` and `floorDiv`, which TRD §4.2 relies on, are API 24: **verified**, fine.)
- **Fix in:** `build` — a `check` test that scans the sources (or class files) of `logic` and `core` for those names. Desktop-only modules (`lwjgl3`, `sim`, `tools`) may use them.
- **Reproduce:** add `String.join(",", "a")` to `logic`: `./gradlew check` must fail. Then the API 24 emulator before the closed test (TD-23; the download needs the owner's yes).

#### SV-3 · A fixable save is thrown away — 15 · v1 · 3
- **Trigger:** the load-time invariant check (TRD §4.10) meets something legal it didn't expect: cash ₹0, reputation 0, an empty godown, a loan fully repaid but not yet null. Or something wrong but repairable: a `reservedByVisit` pointing at a visit that ended, a `displaySlot` past the slot count after an update.
- **Unhandled:** "Couldn't read your save; restored your backup from <time>", losing up to a day. If the backups share the fault, a new game.
- **Should:** repair what can be repaired (clear the reservation, move the item to the godown) and load. Reject only what can't be repaired: a bad CRC, unparseable JSON, an unknown format.
- **Fix in:** `logic` — a repair list beside the invariants, each repair with a test.
- **Reproduce:** a fixture for each state above. Each must load and play on.

#### SV-4 · A failed or partial write is taken for a good one — 12 · v1 · 3
- **Trigger:** `File.renameTo` returns `false` and nobody checks. Or a write is cut short: **verified** with storage full, a 4 MB write stopped at 1.9 MB and left the partial file behind. Or, in the Windows desktop build, `renameTo` refuses to replace an existing file.
- **Unhandled:** the writer posts "committed" and the reply shows, but the old file is still on disk. The next launch has lost the action, and nobody was told. In the desktop window, nothing after the first save is ever saved.
- **Should:** "committed" only after the write, fsync, close and a rename that returned true. A leftover `game.json.tmp` is overwritten, never loaded.
- **Fix in:** `core` (`SaveWriter`). The desktop build replaces the file through `PlatformServices`, where `Files.move` with `REPLACE_EXISTING` is allowed.
- **Reproduce:** a unit test with a file seam whose rename returns false. The storage fill in §7. `./gradlew :lwjgl3:run` on Windows, then make two moves.

#### SV-5 · Storage full, and the player keeps playing unsaved — 12 · v1 · 3, 12
- **Trigger:** the phone fills up mid-session. **Verified** on the emulator: its data partition had only 330 MB free, and `fallocate` filled it in under a second.
- **Unhandled:** F15 AC4's card shows once, and play goes on. Every deal after it lives only in memory. If Android then kills the game, all of them are gone, though the player saw each one happen.
- **Should:** after the card, a small line, "Not saved: phone storage is full", stays on every screen until a commit succeeds. Every commit retries. Backups are never deleted to make room.
- **Fix in:** `core`.
- **Reproduce:** the storage fill in §7, mid-haggle. Make two deals, `am kill`, free the space, relaunch. The line must have been showing, and the game must be at the last good save.

#### SV-6 · A crash while loading becomes a crash at every launch — 10 · v1 · 3, 15
- **Trigger:** a save that the loader or a migration crashes on: an update's bug, a rare state.
- **Unhandled:** the game dies at every launch. Settings and Report a problem can't be reached. Android vitals counts the crash but can't say which save.
- **Should:** write a "loading" marker before the load, and clear it after the first frame. If the next launch finds it, load `bak1` instead, show "Couldn't read your save; restored your backup from <time>", and keep the crash note for a report.
- **Fix in:** `core`, with the crash note from slice 15.
- **Reproduce:** the dev switch "crash during load" (§6). Launch twice: the second launch must reach the backup.

#### SV-7 · A newer save is treated as damage — 10 · v1 · 3
- **Trigger:** an older build opens a save written by a newer one. The format number didn't change, but the content did: an item ID this build doesn't know. It happens to testers moved between tracks, to sideloaded builds, and after OP-1's "rollback".
- **Unhandled:** the unknown ID fails the invariants. The loader falls to the backups, then to `broken/` and a new game. When the player updates again, the good save sits in `broken/`, unread.
- **Should:** a save whose `writtenBy` is newer than this build, or that names content this build doesn't have, gets the newer-save block ("This save is from a newer version. Update the game to keep playing."), never the damage path.
- **Fix in:** `logic`. The loader checks `formatVersion`, `contentVersion` and `writtenBy` before anything else.
- **Reproduce:** install a debug build with one extra item and play until you own it. Then `"$ADB" install -r -d` the build without it: the block card, not a new game.

#### SV-8 · Backups that are all the same file — 9 · v1 · 3
- **Trigger:** TRD §4.10 rotates the backups at every day end **and after every successful load**. The player opens the game, closes it, and opens it again.
- **Unhandled:** after two launches, `game.json`, `bak1` and `bak2` are identical. If a logic bug (not file damage) spoiled the state, there's no older copy to go back to.
- **Should:** rotate at day end. At load, only when the loaded file differs from `bak1`.
- **Fix in:** `core` (`SaveWriter`).
- **Reproduce:** a unit test: load twice without playing. `bak2` must still hold the older day.

#### SV-9 · Settings say a voice was chosen, but there's no save — 8 · v1 · 3, 12
- **Trigger:** the first commit failed (storage full at first launch); or a restore brought `bb-settings` without the save; or the save went to `broken/`.
- **Unhandled:** if routing reads `voiceChosen`, the voice picker is skipped, and the game opens a stall with no state, or crashes.
- **Should:** launch routing follows the save file only (APP-FLOW §1.3). No save means the voice picker, with the chosen voice preselected.
- **Fix in:** `core`.
- **Reproduce:** `"$ADB" shell "run-as $PKG rm -r files/save"`, then relaunch. (**Verified** that `run-as` reaches the folder on debug builds.)

### 3.2 LC · Lifecycle

#### LC-1 · Two copies of the game write one save — 15 · v1 · 3, 12
- **Trigger:** a second `AndroidLauncher` starts while the first lives. **Verified:** `"$ADB" shell am start -n $PKG/.android.AndroidLauncher -f 0x18000000` gave two instances in two tasks inside one process. In the wild, Android is long known to do this when a game is first opened from the installer or the Play Store, then from its icon (from memory).
- **Unhandled:** two `BazaarBossGame` objects, each with its own state, both writing `game.json`. The one left in the background writes its old state last: deals vanish, or happen twice across the copies.
- **Should:** one activity, one game, one writer.
- **Fix in:** `android` — `launchMode="singleTask"`, plus finishing any launcher instance that isn't its task's root. `core` — one `SaveWriter` per process; a new game object waits for its queue before loading.
- **Reproduce:** the command above. Afterwards there must be one instance: `"$ADB" shell dumpsys activity activities | grep -o "ActivityRecord{[0-9a-f]* u0 $PKG/.android.AndroidLauncher t[0-9]*}" | sort -u`.

#### LC-2 · A settings change rebuilds the whole game — 16 · v1 · 3, 12
- **Trigger:** **verified:** changing the font size, display size, dark mode or the app's language destroys and recreates `AndroidLauncher`. Logcat shows `ActivityThread.handleRelaunchActivity`, and libGDX logs its ~15 start-up lines again. None of these changes is in the manifest's `configChanges`.
- **Unhandled:** a new game object loads the save while the old one may still be writing it (LC-1's race). The Pause overlay, Settings and the slider position are gone. With both bugs present, the last action is lost.
- **Should:** the old game's `dispose()` drains the writer's queue (a bounded wait of about 2 s) before the new one loads. Resume lands exactly where the save says.
- **Fix in:** `core` (drain on dispose). `android`: add `density|fontScale|uiMode|locale|layoutDirection|smallestScreenSize` to `configChanges`, so most of these become a `resize()`. Process death takes the same path, so recreation must stay safe anyway.
- **Reproduce:** mid-haggle, `"$ADB" shell settings put system font_scale 1.3` (reset with `1.0`). Also `cmd uimode night yes`, `wm density 546`, `cmd locale set-app-locales $PKG --locales mr-IN`.

#### LC-3 · A restart loses a result the player is owed — 16 · v1 · 3, then 6, 8, 10, 11
- **Trigger:** the process dies after a commit but before its result is dismissed: the Deal result and its verdict, the auction result with the rivals' estimates (DR19), the walk-away Lesson, the tier-up. Phones from Xiaomi, Oppo, Vivo and Realme, which most of India buys, kill background apps quickly (from memory; dontkillmyapp.com tracks it). A phone call or a WhatsApp reply is enough.
- **Unhandled:** the relaunch lands on the stall with the cash changed and no explanation. The verdict, the game's teaching moment, is lost, and Share with it. A tier-up nobody saw counts as celebrated.
- **Should:** the save holds an "owed result" (deal seq, auction result, lesson, tier-up) until the player dismisses it, and a relaunch shows it first. First-time hints count as seen only when dismissed.
- **Fix in:** `logic` (one field in `DayState`), `core` (each result screen).
- **Reproduce:** on each result: Home, `"$ADB" shell am kill $PKG`, relaunch (**verified**: the process goes, the task stays in Recents).

#### LC-4 · Start over races a queued save — 8 · v1 · 15
- **Trigger:** Start over while the writer still holds a commit: the last action was a moment ago, or the phone is slow.
- **Unhandled:** the files are deleted, then the queued write recreates `game.json`. The "new" game opens on the old one, or the next launch skips the voice picker.
- **Should:** Start over locks input, drains the queue, deletes, and raises a generation number. The writer drops any write from an older generation. It deletes `save/`, `crash/`, `broken/`, `cache/share/` and `bb-settings`, and leaves AndroidX's own `files/profileInstalled` marker alone (**verified**: it's already there in the empty project).
- **Fix in:** `core`.
- **Reproduce:** the dev switch "slow commit 2 s" (§6). Make a move, Start over at once, relaunch: the voice picker.

#### LC-5 · Blank faces after the GL context is lost — 6 · v1 · 6
- **Trigger:** some phones drop the GL context while the game is paused, although the game object survives. Faces drawn into textures or frame buffers when a customer arrives (TRD §4.10) aren't reloaded by libGDX.
- **Unhandled:** back from a call or the share sheet, the customer's face is blank or black.
- **Should:** cached faces are redrawn from their inputs (kit and mood) when the context is new.
- **Fix in:** `core`.
- **Reproduce:** depends on the phone (from memory). On the borrowed 2–3 GB phone (Q13): mid-haggle, Home, open three heavy apps, come back. Developer options → "Don't keep activities" must be switched by hand: `settings put global always_finish_activities 1` alone did **not** take effect on Android 16 (verified).

#### LC-6 · Rivals bid while the player can't — 9 · v1 · 8
- **Trigger:** the notification shade pulled down, a system dialog, or the other half of split screen. The game loses focus but isn't paused, because `onPause` doesn't run.
- **Unhandled:** the countdown and the rivals carry on, though DR16 says they never bid while the game is paused. The player loses a lot they were bidding on.
- **Should:** losing window focus pauses the peek and the auction, the same as going to the background (F5 AC8).
- **Fix in:** `android` passes `onWindowFocusChanged(false)` to `core`, which pauses.
- **Reproduce:** by hand: during bidding, pull down the shade for 5 seconds. The countdown must not move.

### 3.3 HE · Haggle and auction rules

#### HE-1 · A quick button offers a price worse than the customer's own — 16 · v1 · 4
- **Trigger:** selling. A customer offers ₹600 for a lamp on display, and the player taps −25%. F2 doesn't say what the quick buttons apply to when selling. Applied to the price on the table, the offer is ₹450, below the customer's own ₹600.
- **Unhandled:** the customer accepts at once (it's on their side of the line, DR8). The player loses ₹150 with one tap, and the verdict line reads as mockery.
- **Should:** no offer can be worse for the player than the other side's current price; that's what Accept is for. Recommended (§5, F-1): buying shows −10% and −25%, below the seller's price; selling shows +10% and +25%, above the customer's price.
- **Fix in:** `logic` (`Offer` rejects such a price), `core` (the button labels).
- **Reproduce:** a unit test in both directions: every quick button's offer is better for the player than the price on the table, by at least one bazaar step (DC-1).

#### HE-2 · Buying below scrap value, then scrapping, prints money — 12 · v1 · 5, 9, 14
- **Trigger:** a walk-in seller's walk-away price, built from the item, its condition and the personality (DR9), lands below the item's `scrapValue`. Scrap always sells at once (DR4), and cheap sellers always come.
- **Unhandled:** buy and scrap, forever: a money loop that skips the haggle. If scrap profit counts toward the goal bar, the shop arrives in minutes, and Check 3 measures the loop, not the game.
- **Should:** a seller's walk-away price is never below the scrap value plus a margin, and only haggled sales fill the goal bar (§5, F-2).
- **Fix in:** `logic` (the price model), `content` (the validator compares `scrapValue` with the lowest walk-away price possible), `sim` (a "buy and scrap" bot must lose money).
- **Reproduce:** the simulator with that bot, and a property test over many seeds: walk-away ≥ scrap value.

#### HE-3 · A habit the five bots never try wins everywhere — 12 · v1 · 5
- **Trigger:** DR11's bots are the lowballer, creeper, splitter, impatient and honest. None tries "walk away first and wait for the call-back" (DR14), or "ask Last price? first" (DR13).
- **Unhandled:** if the call-back reliably beats haggling, walking away becomes the best opening against everyone, and haggling becomes a two-tap chore. That's R6, the risk the whole design exists to avoid.
- **Should:** both habits lose to at least one personality. For example, the chance of a call-back falls with the patience already used, and the rushed buyer never calls back [A — the haggle lab sets it].
- **Fix in:** `sim` (two more bots), `logic` (call-back odds per personality).
- **Reproduce:** `./gradlew sim:run`. The report lists all seven bots, and each one loses somewhere.

#### HE-4 · The player outbids themselves — 12 · v1 · 8
- **Trigger:** the player taps +₹50 while already leading, or taps twice quickly.
- **Unhandled:** the price rises against nobody. Bids are final (DR17), so the extra is paid.
- **Should:** while the player leads, the bid buttons read "You lead" and do nothing (§5, F-3).
- **Fix in:** `logic` (`PlaceBid` returns `Rejected` with the reason "leading"), `core`.
- **Reproduce:** a unit test: `PlaceBid` twice in a row.

#### HE-5 · A frame stall skips the countdown — 12 · v1 · 8
- **Trigger:** garbage collection or a texture upload stalls a frame for a second or two on a 2–3 GB phone. The next `AuctionTick` carries `elapsedMs` = 2,000.
- **Unhandled:** the countdown jumps past zero, and the hammer falls before the player could react. The game's only real-time moment (APP-FLOW §2.2) turns unfair, and only on cheap phones.
- **Should:** each tick advances the countdown by at most 100 ms [A]. A stall slows the auction down; it never skips it.
- **Fix in:** `core` clamps the time it passes; `logic` rejects a larger step in tests.
- **Reproduce:** the dev switch "stall one frame for 2 s" (§6) during bidding, on the LowEnd_A9 virtual device.

#### HE-6 · A tap in the hammer's frame, and a resumed auction that ends at once — 9 · v1 · 8
- **Trigger:** (a) the player's bid and the countdown's last tick land in the same frame. (b) The game died with 0.3 s left, and F5 AC8 resumes the auction paused, with that 0.3 s.
- **Unhandled:** (a) depending on code order, the bid is dropped, or counted after the hammer. (b) The hammer falls as the auction resumes.
- **Should:** (a) taps are handled before the tick in each frame, so the bid counts. (b) A resumed auction restarts its countdown in full.
- **Fix in:** `core` (frame order), `logic` (the resume rule).
- **Reproduce:** (a) a unit test: `PlaceBid`, then `AuctionTick` to zero, in one step. (b) `am kill` in the last second (§7), then relaunch.

#### HE-7 · A waiting customer's item disappears — 9 · v1 · 10
- **Trigger:** two waiting customers want the same displayed item, and the player sells it to one. Or, before serving the other, the player scraps it from the godown or takes it off display.
- **Unhandled:** `ServeVisit` opens a haggle over an item that's gone: a crash, or a deal that sells nothing.
- **Should:** `ServeVisit` checks again. The customer asks for another displayed item in the same category, or leaves quietly with a line and no reputation change.
- **Fix in:** `logic`.
- **Reproduce:** a unit test: two visits want item X; serve and close the first; serve the second.

#### HE-8 · The godown limit against "Back on Sort keeps everything" — 9 · v1 · 9
- **Trigger:** the godown holds 58 of its 60 items (Q9's limit, TRD tuning table), and the player wins a lot of 6.
- **Unhandled:** two rules collide. Back on Sort keeps everything (F5 AC10), which breaks the limit. Enforcing the limit means scrapping, which back must never do.
- **Should:** a soft limit (§5, F-4). A won lot may push the godown past 60. At or over the limit, the bid button and walk-in purchases say "Godown full" until the player sells or scraps.
- **Fix in:** `logic`, `core`.
- **Reproduce:** a unit test from a state holding 58 items.

#### HE-9 · The loan never clears — 8 · v1 · 10
- **Trigger:** the repayment share (DR3) of a small sale rounds to ₹0: 10% of ₹4 is 40 paise.
- **Unhandled:** a player who sells cheap items never repays. Only one loan may be open, so when they're broke again there's no loan left: a dead end that F11 AC4's simulator check misses if it never reaches that state.
- **Should:** every sale repays at least ₹1, or the rest of the loan if that's less (§5, F-6).
- **Fix in:** `logic`. `sim`: the dead-end check includes "broke with a loan open".
- **Reproduce:** a unit test: a loan open, then ten ₹4 sales.

#### HE-10 · A promised price lands past the line — 8 · v1 · 4
- **Trigger:** the "Last price?" firm price (DR13) or a call-back price (DR14), worked out from mood and then rounded, lands on the player's side of the walk-away price.
- **Unhandled:** the player accepts a deal past the line, breaking DR8, and the verdict ("She'd have taken ₹220") contradicts the price just agreed.
- **Should:** both prices stay on the customer's side of the walk-away price, after rounding.
- **Fix in:** `logic`.
- **Reproduce:** a property test across many seeds and every personality.

#### HE-11 · The peek can be studied under Pause — 6 · v1 · 8
- **Trigger:** back during the 10-second peek opens Pause (APP-FLOW §1.7). The peek's timer stops, but the lot stays visible behind the overlay.
- **Unhandled:** the 10-second peek becomes unlimited, and at M4 the "+10 s peek" ad reward is worth nothing.
- **Should:** Pause and Settings cover the lot. The peek's time and its visibility stop and start together.
- **Fix in:** `core`.
- **Reproduce:** by hand: press back during the peek.

#### HE-12 · Inspect costs the last point of patience — 6 · v1 · 7
- **Trigger:** Q4's recommendation charges one point of patience per Inspect tap mid-haggle. The tap that reaches 0 happens on the Inspect screen.
- **Unhandled:** the customer walks away behind Inspect, and back returns to a haggle that no longer exists.
- **Should:** Inspect closes, and the walk-away shows with its Lesson line (F2 AC8). APP-FLOW §1.8's "That item's gone, then back" is this case.
- **Fix in:** `core`.
- **Reproduce:** a unit test with patience 1; then by hand.

#### HE-13 · Leaving the special's auction loses it without warning — 6 · v1 · 11
- **Trigger:** Today's special is a lot (APP-FLOW §1.2). The player opens it, then leaves the auction (DR18).
- **Unhandled:** the special is used up for the day (F6 AC4). The player got nothing, and was never told.
- **Should:** the confirmation says so: "Leave? Today's special lot goes to the rivals." (§5, F-7).
- **Fix in:** `core` (the copy).
- **Reproduce:** by hand.

### 3.4 EF · Empty and first run

#### EF-1 · The first haggle can't end in a deal — 8 · v1 · 12, 14
- **Trigger:** a tuning change puts the starting cash below the first seller's walk-away price. Or the player walks away twice, refusing the call-back, and the next sellers leave too.
- **Unhandled:** F1 opens the stall only "after that first deal". The new player meets seller after seller and never reaches the game.
- **Should:** a test keeps the first seller within the starting cash. If two sellers leave without a deal, the stall opens anyway, with one hint (§5, F-5).
- **Fix in:** `logic` (the test and the rule), `core`.
- **Reproduce:** a unit test on `NewGame`; then by hand, walking away from three sellers.

The other first-run cases are SV-9 (settings without a save) and EF-2 (§4).

### 3.5 IN · Input

#### IN-1 · A double tap applies an action twice — 16 · v1 · 4, 6
- **Trigger:** a double tap, or two fingers, on Offer, Accept, a bid, Scrap's confirmation, Upgrade or Take the loan. Cheap phones lag, so people tap again.
- **Unhandled:** the second tap runs against the new state: a second Accept on a finished haggle (a crash on a null `haggle`), a second Upgrade, a second bid.
- **Should:** input locks from the tap until "committed" comes back. Every command names the round it answers, and a stale one returns `Rejected` and changes nothing.
- **Fix in:** `logic` (the round token), `core` (the lock).
- **Reproduce:** a unit test that sends each command twice; on the phone, the dev switch "slow commit 500 ms" (§6).

#### IN-2 · The owner's CSV arrives broken — 12 · v1 · 2
- **Trigger:**
  - `content/strings.csv`, edited in Excel on this PC, is saved in the Windows code page (₹ becomes `?`), or with a byte-order mark in front of `id`.
  - Tanglish lines pasted from WhatsApp or Docs carry invisible characters: zero-width spaces, non-breaking spaces.
  - Straight and curly apostrophes mix.
  - Commas inside a line split it into columns.
- **Unhandled:** the validator's font check (TRD §4.4) fails with an unhelpful message. Or a line passes and shows a gap where the invisible character sits. A shifted column puts Hinglish text into the Tanglish voice.
- **Should:** the validator requires UTF-8, removes a byte-order mark, names any invisible character by code point, line ID and voice, parses quoted fields properly, and normalises apostrophes to the one DESIGN §7 uses.
- **Fix in:** `content` (`tools:validateContent`).
- **Reproduce:** a fixture CSV with each problem. Each must fail with a message that says where.

#### IN-3 · The slider can't land on the wanted price — 8 · v1 · 6
- **Trigger:** a range of ₹10–₹5,000 on a slider about 300 dp wide is more than ₹16 per dp.
- **Unhandled:** the player wants ₹350 and gets ₹341 or ₹358, and the bazaar rounding steps (TRD §4.2) make it jump.
- **Should:** the slider snaps to bazaar steps, − and + buttons nudge it one step, and the price reads large while dragging.
- **Fix in:** `core`.
- **Reproduce:** by hand, at `wm density 546` (§7).

#### IN-4 · A back swipe grabs the slider — 6 · v1 · 6
- **Trigger:** with gesture navigation, a drag that starts near the screen's left or right edge is Android's back gesture.
- **Unhandled:** the player drags the slider from the edge and gets Pause instead of a price.
- **Should:** the slider's track keeps clear of both edges. On Android 10 and later, the slider can also be excluded from the back gesture (`setSystemGestureExclusionRects`; from memory).
- **Measured 2026-11-25 (Phase 4), Android 16 emulator, default sensitivity:** a drag starting within 28 dp of either edge is a back, and one starting at 30 dp reaches the game. The zone is `config_backGestureInset`, 30 dp, and it widens if the player raises the back sensitivity.
- **Decided 2026-11-25 by the owner:** the slider's whole 48 dp target stays at least 32 dp from both edges (DESIGN §2.7, which said 24 dp), and on Android 10+ its area is excluded from the back gesture through `PlatformServices`.
- **Fix in:** `core`, `android`.
- **Reproduce:** switch to gesture navigation (§7, verified) and drag the thumb from the edge.

#### IN-5 · Fast taps through the Reveal land on Sort — 6 · v1 · 9
- **Trigger:** taps speed the Reveal up, and the last one arrives just as Sort opens.
- **Unhandled:** the tap marks a Sort row as Scrap: the accident the two-screen split was meant to prevent (APP-FLOW §4). Sort's confirmation still lists the item, so nothing is lost silently.
- **Should:** Sort ignores taps for its first 400 ms [A].
- **Fix in:** `core`.
- **Reproduce:** by hand: tap fast through a reveal.

### 3.6 DV · Device

#### DV-1 · The phone's language changes the digits, and the letters — 20 · v1 · 1, 3
- **Trigger:** text on screen formatted with `String.format`, `NumberFormat` or `DecimalFormat` without a locale, or lowercased with `toLowerCase()`. **Verified** on JDK 17:
  - with the phone in Marathi, Bengali, Assamese or Urdu, all common in India, `String.format("%,d", 125000)` gives `१२५,०००`, `১২৫,০০০` or `۱۲۵٬۰۰۰`
  - in Turkish, `"TRADING".toLowerCase()` gives `tradıng`

  Android's formatting uses the same locale data (from memory; the unit test below settles it).
- **Unhandled:** the baked Latin-only font (TRD §1.2) has no glyphs for those digits, so prices draw as blanks or boxes. In a haggling game, the price itself vanishes. The Turkish case writes enum names such as `tradıng` into `game.json`; the next load can't read them, so the save and then its backups count as damaged.
- **Should:** every number on screen goes through our own `IndianNumberFormat` (TRD §2.3), which writes ASCII digits. `String.format`, `toLowerCase` and `toUpperCase` always take `Locale.ROOT`.
- **Fix in:** `logic` (the formatter, the save codec), `core`. `build`: a test that runs the formatter and a save round trip with the default locale set to `mr-IN`, `bn-IN`, `ur-IN`, `ar-EG` and `tr-TR`.
- **Reproduce:** the unit test. On the emulator, `"$ADB" shell cmd locale set-app-locales $PKG --locales mr-IN` (verified; Android 13+; reset with `--locales ""`).

#### DV-2 · 316 dp wide with XL text — 16 · v1 · 6, 12
- **Trigger:** "Display size: Largest" makes an ordinary phone narrower in dp than a small one. **Verified:** `wm density 546` on the 1080-px emulator gives about 316 dp. Add XL text (1.4×, DESIGN §2) and five rounds of offers.
- **Unhandled:** the offers history grows every round (F2 AC2) and pushes the slider and Offer off the bottom of the haggle screen by round 5 or 6.
- **Should:** the haggle screen fits at 300 dp with XL text in every round. The history shows the last two rounds plus "Earlier offers". Below 300 dp, the whole layout scales down evenly.
- **Fix in:** `core`. DESIGN §10's screenshots include this size.
- **Reproduce:** `"$ADB" shell wm density 546` (verified; reset with `wm density reset`), Settings → Text size → XL, then six rounds.

#### DV-3 · Buttons under the system bars — 12 · v1 · 1, 12
- **Trigger:** the game draws behind the status and navigation bars (TD-25). Edge-to-edge is forced on Android 15 and later but not on 7–14, the bars differ between 3-button and gesture navigation, and some phones have a camera cutout.
- **Unhandled:** mute sits under the status bar, or Walk away under the gesture bar, on some phones and not others.
- **Should:** the layout stays inside the insets Android reports for the system bars and the cutout. `android` reads them and passes them to `core`.
- **Fix in:** `android` (reads `WindowInsets`), `core` (lays out inside them).
- **Reproduce:** screenshots in both navigation modes (§7, verified switch), and on the LowEnd_A9 virtual device (Android 9).

#### DV-4 · Little room left on start-up time — 9 · v1 · 1, 14
- **Trigger:** **verified:** the empty project's first cold start took 3,986 ms on the emulator, and 2,673 ms after a force-stop. TRD §0.4's target is 4 s at p95 on a cheap phone, and the content, fonts and save aren't loaded yet. Separately, older phone GPUs can't load font atlases larger than 2,048 px (from memory).
- **Unhandled:** the first offer misses F1 AC3's 30 seconds, and text goes blank on some older phones.
- **Should:** measure start-up at every slice, load only the chosen voice and text-size step, and keep atlases at 2,048 px or less.
- **Fix in:** `core`, `tools` (the font baker).
- **Reproduce:** `"$ADB" shell am force-stop $PKG`, then `"$ADB" shell am start -W -n $PKG/.android.AndroidLauncher` and read TotalTime (verified). On LowEnd_A9 at slice 14.

#### DV-5 · Redrawing 60 times a second while the player reads — 8 · v1 · 12
- **Trigger:** libGDX draws continuously by default, but most of this game is reading and deciding.
- **Unhandled:** a warm phone and a draining battery, on exactly the cheap phones F20 targets: a likely one-star review. Battery saver and heat may also cap the frame rate.
- **Should:** draw only while something moves or after input (`setContinuousRendering(false)` plus `requestRendering()`). Animations run on time, not on frame counts.
- **Fix in:** `core`.
- **Reproduce:** `"$ADB" shell cmd battery unplug`, play for ten minutes, then `"$ADB" shell dumpsys batterystats $PKG` (from memory). Battery saver as in §7 (verified).

#### DV-6 · Split screen and foldables — 6 · v1 · 12
- **Trigger:** split screen gives the game half the height, and unfolding a foldable changes the width mid-haggle. The portrait lock doesn't hold in split screen.
- **Unhandled:** overlapping or cut-off controls.
- **Should:** the manifest asks for a minimum size (`<layout android:minWidth="320dp" android:minHeight="480dp"/>`, from memory). Below it, the game pauses behind a "Make the window bigger to play" card. Above it, the layout follows the width, up to DESIGN's 480 dp column.
- **Fix in:** `android`, `core`.
- **Reproduce:** by hand: Recents → the game's icon → Split screen.

#### DV-7 · Text size changed mid-haggle — 6 · v1 · 12
- **Trigger:** Settings → Text size, while a haggle waits paused behind Settings.
- **Unhandled:** screens built with the old font keep the old measurements, and lines overlap until each screen is rebuilt.
- **Should:** changing the step rebuilds every open screen with the new font, and unloads the old one.
- **Fix in:** `core`.
- **Reproduce:** by hand, at each step.

#### DV-8 · With animations off, the reply shows before the save — 6 · v1 · 6
- **Trigger:** "Remove animations" is on, or `animator_duration_scale` is 0 (verified settable). If the game follows the system setting (DESIGN §4), the 300 ms reaction that hides the commit (TRD §3.2) becomes instant.
- **Unhandled:** if the reply shows when the animation ends, it can now show before "committed". The exactly-once rule breaks whenever the phone is slow.
- **Should:** the reply shows at the later of two moments: the animation's end, and "committed".
- **Fix in:** `core`.
- **Reproduce:** `"$ADB" shell settings put global animator_duration_scale 0` (verified; reset with `1`), with the dev switch "slow commit 500 ms".

DV-9 (TalkBack) is in §4.

### 3.7 TC · Time and calendar

#### TC-1 · A wrong date at first launch — 6 · v1 · 11
- **Trigger:** a new or freshly reset phone starts at a wrong date, such as 1970 or its factory date, until it gets network time.
- **Unhandled:** Share my stats says "First played: 1970-01-01". Check 1 counts that tester as still playing, and the welcome-back gap looks like decades.
- **Should:** a date earlier than the build's own date counts as unknown. The stats say "unknown" until the first real date fills it in.
- **Fix in:** `logic`.
- **Reproduce:** before the first launch, `"$ADB" shell settings put global auto_time 0` and `"$ADB" shell cmd alarm set-time 86400000`. (`set-time` was verified with another date; it needs no root.) Restore the date afterwards.

#### TC-2 · "Welcome back" for a brand-new player — 6 · v1 · 11
- **Trigger:** `lastWelcomeBackEpochDay` or the last-played day starts at −1 or 0.
- **Unhandled:** day 1 says "Welcome back. Here's a cheap starter lot." to someone who has just arrived, on top of Periyappa's gift.
- **Should:** `NewGame` sets both to today.
- **Fix in:** `logic`.
- **Reproduce:** a unit test: `NewGame`, then `StartDay`.

DR26's backward jumps are designed (F6) and can be tested with `cmd alarm set-time` (§7). TC-3 is in §4.

### 3.8 CL · Content and language

#### CL-1 · A line misgenders the customer — 12 · v1 · 2, 13
- **Trigger:** women and men are both customers (F3). Hinglish verbs agree with the subject's gender ("de deta" / "de deti"), as do Tanglish verb endings and English pronouns ("He'd have gone to ₹380" / "She'd have taken ₹220", F2 AC5). A line written once can't fit both.
- **Unhandled:** a woman customer speaks, or is spoken of, in the masculine: a mistake every Hinglish reader will notice (Q2).
- **Should:** every line about the customer has a variant for each gender, chosen by the customer type. The validator fails a line that's missing one.
- **Fix in:** `content` (a gender column or paired IDs in the string table; the validator), `logic` (passes the gender).
- **Reproduce:** a validator fixture with a one-variant line.

#### CL-2 · A line fits as written, but not once it's filled in — 12 · v1 · 2, 6
- **Trigger:** `max_len` is checked on the template (TRD §4.4), but `{item}` and `₹{price}` are filled in at run time: the longest item name, or "₹99,99,999".
- **Unhandled:** the filled line runs past its box on the haggle screen, and worse at XL text on a narrow screen (DV-2).
- **Should:** the validator checks each line with the longest possible value of each placeholder.
- **Fix in:** `content`.
- **Reproduce:** a validator fixture, and a screenshot at XL text with the longest item.

#### CL-3 · Dates come out in the phone's language — 9 · v1 · 3, 11
- **Trigger:** "Restored your backup from <time>", "Your progress from <last played date> is here" and the Share my stats dates are formatted with the phone's locale.
- **Unhandled:** Tamil or Hindi month names, or non-Latin digits (DV-1): blanks on screen, and stats in mixed formats the owner can't sort.
- **Should:** on screen, one fixed English format, such as "24 Nov 2026". In Share my stats, ISO dates ("2026-11-24"). Both use `Locale.ROOT`.
- **Fix in:** `logic`.
- **Reproduce:** DV-1's unit test, extended to dates.

#### CL-4 · The banned-word check blocks innocent words — 8 · v1 · 2
- **Trigger:** CR2's list holds gambling words. Matching inside words flags "better" and "betel box" (bet), and the Tamil "sattai", a shirt (satta).
- **Unhandled:** the build fails on a good line, and the owner rewrites the line or weakens the list.
- **Should:** whole-word, case-insensitive matching, plus a short reviewed allowlist in `content/banned/`.
- **Fix in:** `content`.
- **Reproduce:** validator fixtures: "betel box" passes; "jackpot" fails.

### 3.9 DC · Data boundaries

#### DC-1 · Number edges — 12 · v1 · 1, 4
- **Trigger:**
  - ₹0 and ₹1
  - a loss, written with the minus sign (−₹350)
  - 99,99,999, which rounds to "₹100.0L" instead of "₹1Cr"
  - `Long.MIN_VALUE`, whose `Math.abs` is still negative
  - −10% of a ₹5 price, which rounds back to ₹5
- **Unhandled:** wrong or odd amounts on the stall and the kanakku, and a quick button that does nothing.
- **Should:** `IndianNumberFormat` and `Money.applyBp` have table-driven tests. The short form moves up a unit when rounding reaches it. A quick button that can't move the price by at least one bazaar step is disabled.
- **Fix in:** `logic`.
- **Reproduce:** the unit tests.

#### DC-2 · The goal bar and reputation out of range — 6 · v1 · 10
- **Trigger:** profit beyond the goal, a sale at a loss, and reputation changes at 0 or 100.
- **Unhandled:** a bar at 104%, a bar that moves back (F7 AC2 says it never does), reputation outside 0–100.
- **Should:** the bar shows 100% at most, a loss adds nothing, and reputation is clamped.
- **Fix in:** `logic`.
- **Reproduce:** unit tests.

DC-3 (two phones on one account) is in §4.

### 3.10 BR · Build and release builds

#### BR-1 · AndroidX already exports a receiver — 10 · v1 · 15
- **Trigger:** **verified** in the built APK: `androidx.profileinstaller.ProfileInstallReceiver` is merged in and exported, protected by `android.permission.DUMP`. TRD §4.7 says no component is exported except the launcher activity, and §4.11's guard fails the build on any other.
- **Unhandled:** the guard fails the first time it runs, and the quick fix, loosening it, would let a real leak through later.
- **Should:** the guard allows a named list: the launcher, plus this receiver for as long as it keeps the `DUMP` permission. (Or remove the receiver with `tools:node="remove"`; it only speeds up start-up.) TRD §4.7 is corrected.
- **Fix in:** `build`, and the TRD.
- **Reproduce:** `"$LOCALAPPDATA/Android/Sdk/build-tools/35.0.0/aapt2.exe" dump xmltree --file AndroidManifest.xml android/build/outputs/apk/debug/android-debug.apk`, then look for `exported=true` (verified).

#### BR-2 · A phone-to-phone transfer copies everything the rules don't name — 9 · v1 · 15
- **Trigger:** on Android 12 and later, a transfer to a new phone follows the `<device-transfer>` section of `dataExtractionRules`, separately from `<cloud-backup>`. **Verified** in Android's documentation: without that section, all of the app's data is transferred, and `allowBackup` is ignored for these transfers.
- **Unhandled:** crash notes and `broken/` saves move to the new phone. At M4, the age and consent record would too, breaking DR35.
- **Should:** both sections list the same files (the save, its backups and `bb-settings`), and the backup-rules test checks both.
- **Fix in:** `build` (the rules and their test).
- **Reproduce:** the backup-rules unit test. By hand: Android Studio's Backup and Restore tool, "Device to device" (from memory).

#### BR-3 · Android Studio's JDK fails the build — 8 · v1 · 1
- **Trigger:** **verified:** with this project's javac flags, a class that calls an overridable method from its constructor compiles on JDK 17 and fails on JDK 21, with "[this-escape] possible 'this' escape" turned into an error by `-Werror`. Android Studio builds with its own JDK 21.0.9 unless told otherwise, and scene2d UI code does this all the time. JDK 17 rejects `-Xlint:-this-escape` as an invalid flag (verified), so one flag can't serve both.
- **Unhandled:** the build passes in the terminal and fails in Android Studio, or the other way round after a JDK update.
- **Should:** the build adds `-this-escape` to the lint flags only when the compiling JDK is 21 or newer. Or Android Studio's Gradle JDK is set to the same JDK 17.
- **Fix in:** `build` (the root `build.gradle`). **Done 2026-11-25:** with the flag added on JDK 21, Gradle compiled a class that trips the lint cleanly, and `./gradlew check` still passes on JDK 17.
- **Reproduce:** the two `javac` runs in §7.

#### BR-4 · `ids-ever-shipped.txt` isn't updated at a release — 8 · v1 · 16
- **Trigger:** the guard (TRD §4.11) only knows the IDs someone wrote into the file, and a release goes out without updating it.
- **Unhandled:** a later content change removes an ID that shipped. The guard can't see it, and saves that use the ID fail to load (SV-3, SV-7).
- **Should:** `bundleRelease` appends the current IDs to the file itself, and fails if that leaves changes not committed to git.
- **Fix in:** `build`.
- **Reproduce:** remove an item that has shipped and run `bundleRelease`: it must fail.

#### BR-5 · A debug build can't sit beside the Play build — 6 · v1 · 1
- **Trigger:** the owner plays the Play build, then installs a debug build. The signatures differ, so the install fails, and uninstalling wipes the game.
- **Unhandled:** the owner loses their own progress, and their feel for the real game, every time they test.
- **Should:** debug builds get `applicationIdSuffix = '.dev'` and their own name. The FileProvider's authority comes from `${applicationId}`, never written out, so both builds install side by side.
- **Fix in:** `build`, `android` (the manifest).
- **Reproduce:** install the release build and the debug build on one phone.

### 3.11 HO · Hand-offs and the network

#### HO-1 · Some email apps drop the report's subject and body — 9 · v1 · 15
- **Trigger:** TRD §5.3 sends Report a problem as `ACTION_SENDTO` to a `mailto:` address, with the subject and body as extras. Some email apps ignore the extras with this action (from memory), and Gmail with no account signed in opens its set-up screen instead.
- **Unhandled:** the player sends an empty email, without the versions or the crash note that make it useful, or gives up.
- **Should:** send `ACTION_SEND` with the extras and a `mailto:` selector (from memory, the usual pattern for this), and put a short subject and the versions into the `mailto:` link as well. Test it with Gmail and one other email app before slice 15 closes.
- **Fix in:** `android` (`PlatformServices`).
- **Reproduce:** by hand: Gmail on the emulator, and Outlook or Samsung Email on a phone.

#### HO-2 · A save confirmation lands after the player has moved on — 6 · v1 · 6
- **Trigger:** "committed" comes back from the save writer (TRD §3.2) after the player pressed back into Pause, or opened Settings, during the 300 ms reaction.
- **Unhandled:** the reply plays out behind the overlay or on the wrong screen, or it's dropped and the haggle shows the round before.
- **Should:** each commit carries the ID of the command it answers. The haggle keeps the reply and plays it when it's on screen again.
- **Fix in:** `core`.
- **Reproduce:** the dev switch "slow commit 500 ms", then back straight after Offer.

#### HO-3 · A slow save makes the haggle look frozen — 6 · v1 · 3
- **Trigger:** on cheap storage, fsync can stall for seconds (from memory), and the reply waits for "committed" (TRD §3.2).
- **Unhandled:** the customer doesn't react. The player taps again (IN-1), or force-closes the game.
- **Should:** after 1 s, a small "Saving…" shows. The reply still waits for the commit, however long it takes.
- **Fix in:** `core`.
- **Reproduce:** the dev switch "slow commit 3 s".

#### HO-5 · An ad load hangs, or a reward arrives after its moment — 9 · M4
- **Trigger:** a captive portal or a poor network makes the ad load hang (TRD §3.4). Or the reward arrives after the auction it was for has ended.
- **Unhandled:** a spinner that never ends. Or an extra peek granted to a finished auction, or lost.
- **Should:** after 10 s without an ad, "No ad right now", and nothing is spent. The pending-ad nonce names its offer and context; a reward whose context is gone is dropped. The game saved before the ad, so nothing is lost (DR32).
- **Fix in:** `core`.
- **Reproduce:** at M4: during the load, switch Wi-Fi to a network with no internet, or cut the network mid-ad (from memory).

#### HO-6 · The ads SDK starts work before the gate — 12 · M4
- **Trigger:** DR30 starts the SDK only after the age and consent gate. But merged libraries can run code at process start through a ContentProvider; AndroidX already does, with `androidx.startup.InitializationProvider` (verified in today's manifest). TD-16 already asks for a check that nothing goes out before `initialize()`.
- **Unhandled:** data reaches Google before consent, for every player, minors included, contradicting the policy and the Data safety form.
- **Should:** at M4, list the providers the SDK merges in, switch off any automatic start it offers, and prove that nothing leaves before consent.
- **Fix in:** `android`. `build`: the merged-manifest guard lists providers too.
- **Reproduce:** at M4: fresh install, launch, don't tap an ad. `"$ADB" shell dumpsys netstats detail` must show no bytes for the app's UID (from memory).

HO-4 (the share file) is in §4.

### 3.12 GT · Gates

#### GT-1 · The ad gate is read when the offer is drawn — 8 · M4
- **Trigger:** an ad offer is drawn while consent stands. The player withdraws consent in Settings → Ads & privacy, or the yearly "minor" re-check changes, then returns to the offer still on screen.
- **Unhandled:** the tap starts an ad without consent, though DR34 says a withdrawal takes effect at once.
- **Should:** the gate is read at the tap, as TRD §0.6 says, not when the offer was drawn.
- **Fix in:** `core`.
- **Reproduce:** at M4: open an offer, withdraw consent in Settings, go back and tap the offer.

The newer-save block is SV-7 and OP-1. GT-2 (a change of Play account) is in §4.

### 3.13 MN · Money

v1 has no real money (CR1). Its "payments" are in-game cash applied exactly once: IN-1, LC-3 and §3.1.

#### MN-1 · Tester builds carry the real ad IDs — 16 · M4
- **Trigger:** Play's internal and closed tracks get release builds, and TRD §2.2 gives release builds the real ad-unit IDs. But CR18 makes "a tester build contains a real ad" a release blocker, and R11 says testers tapping real ads can suspend the AdMob account for 30 days.
- **Unhandled:** the M4 closed test either breaks CR18, or can't happen.
- **Should:** decided at M4 (§5, T-15). Recommended: register each tester's phone as an AdMob test device, so the real IDs serve them test ads, and tell testers in writing never to tap ads (CR24).
- **Fix in:** `AdMob`, `owner`. `build`: `checkAdIds`.
- **Reproduce:** at M4, install the closed-track build on a tester's phone. Its ads must carry Google's "Test Ad" label.

#### MN-2 · The player pays, then the app dies before the purchase arrives — 9 · Purchases
- **Trigger:** Play charges the player, and the game is killed before `onPurchasesUpdated` runs.
- **Unhandled:** paid for, and nothing granted. Left unacknowledged for 3 days, Google refunds it automatically.
- **Should:** every launch queries purchases, grants each once, and acknowledges it (TRD §3.3, DR38).
- **Fix in:** `core`, `android`.
- **Reproduce:** with a license-tester account, kill the game while Play's sheet shows the payment succeeded, then relaunch.

#### MN-3 · Clock changes bend the 12-ads-a-day cap — 6 · M4
- **Trigger:** the player moves the date forward to reset the count, then back.
- **Unhandled:** more than 12 rewards a day, and more invalid-traffic risk (H5-10).
- **Should:** the count resets only on a day later than any day seen, like DR26 (DR33; `maxEpochDaySeen`).
- **Fix in:** `logic`.
- **Reproduce:** `cmd alarm set-time` (§7), forward and back.

#### MN-4 · A double tap on Buy — 6 · Purchases
- **Trigger:** two taps on Buy.
- **Unhandled:** two billing flows. Play fails the second with an error the game shows as a failed purchase.
- **Should:** Buy locks until the flow returns.
- **Fix in:** `core`.
- **Reproduce:** double-tap Buy with a license-tester account.

#### MN-5 · A UPI payment stays pending for days — 6 · Purchases
- **Trigger:** UPI purchases can stay PENDING for a long time (RESEARCH L-7).
- **Unhandled:** the item is granted too early, or forgotten once paid.
- **Should:** "Payment pending" with nothing granted, then a grant at the first launch after payment clears (DR38).
- **Fix in:** `core`.
- **Reproduce:** Play's slow test card, which approves after a few minutes (from memory).

MN-6 (a refund while the theme is equipped) is in §4.

### 3.14 MS · Measurement

#### MS-1 · Duplicates and bots decide the waitlist test — 15 · Owner, now
- **Trigger:** the form collects typed emails, with no sign-in, and accepts any number of responses. That's on purpose (site/README.md), so nobody has to sign in. One person can sign up five times; a script can sign up a thousand.
- **Unhandled:** the day-7 count decides months of the owner's time (Q1, PRD §12.1). Anywhere between 370 and 1,600, a handful of duplicates or one bored script can tip it.
- **Should:** decide on unique emails, lower-cased and trimmed, and record both counts. Look at sign-ups by hour: a burst at 3 a.m. isn't people. Sign-ups above about 10% of a post's views are a warning sign [A] (§5, F-9).
- **Fix in:** `owner` (the response sheet).
- **Reproduce:** send the form with `A@x.com`, then with `a@x.com ` (trailing space): two rows. In the response sheet, with the emails in column B: `=COUNTUNIQUE(ARRAYFORMULA(LOWER(TRIM(B2:B))))`.

#### MS-2 · Testers don't send their stats — 16 · Owner; v1 · 16
- **Trigger:** Checks 1–3 come from Share my stats, sent by testers on day 8 (PRD §12.2, Q3). Testers forget, or send it on day 3.
- **Unhandled:** with 6 of 14 sending, G2 can't be judged, or is judged on a biased few.
- **Should:** a rule, written down before the test (§5, F-11). Recommended: judge G2 on the testers who sent stats if at least 10 did. Below 10, extend the test a week and ask once more. Share my stats stays two taps from Settings.
- **Fix in:** `owner`; `core` (the two taps).
- **Reproduce:** nothing to run; it's a rule to settle in advance.

#### MS-3 · Start over or a reinstall resets a tester's stats — 9 · Owner; v1 · 15
- **Trigger:** a tester starts over, or reinstalls, mid-test.
- **Unhandled:** "First played" moves to the restart, so Check 1 reads a loyal tester as new.
- **Should:** the day-0 message asks testers not to. The stats text shows the date this game began (`createdAtUtcMs`) next to the first-played date, so a restart is visible.
- **Fix in:** `owner`, `logic`.
- **Reproduce:** play, Start over, play, then send stats: the two dates differ.

#### MS-4 · Time with the game open but untouched counts as play — 9 · v1 · 11
- **Trigger:** `playTimeMs` counts time in the foreground (TRD §4.2). A tester leaves the game open on the stall while cooking, and some phones never turn the screen off.
- **Unhandled:** "Play time to shop" grows, and Check 3 compares that number with the simulator's.
- **Should:** play time counts only within 60 s of the last tap (§5, F-10).
- **Fix in:** `logic` (with the injected clock), `core`.
- **Reproduce:** a unit test with a fake clock: ten idle minutes add one minute.

#### MS-5 · The prediction is adjusted after the data arrives — 6 · Owner
- **Trigger:** Check 3 compares testers with "the simulator's prediction, which is written down before the test starts" (PRD §12.2).
- **Unhandled:** the simulator is re-run with tuned numbers afterwards, and the check always passes.
- **Should:** the simulator's report is committed to git, dated, before the closed test opens.
- **Fix in:** `owner`.
- **Reproduce:** `git log` on the report: its date comes before the test's.

### 3.15 ST · Store and testers

#### ST-1 · Fewer than 12 testers for 14 days — 16 · Owner
- **Trigger:** Play needs at least 12 testers opted in for 14 days in a row (CR17), and the owner has 14: two spare. A tester leaves, opts in with a different Google account from the one on their phone, or has a phone older than Android 7 (F20).
- **Unhandled:** production access is refused, or the 14 days start again. Weeks are lost, and G2's dates slip.
- **Should:** before inviting, check each tester's Android version and the Google account on their phone. Invite 16–18 if possible. Check the opted-in count in Play Console every day of the 14.
- **Fix in:** `owner`, `Play Console`.
- **Reproduce:** Play Console → Testing → Closed testing → Testers.

#### ST-2 · The Play forms don't match the build — 12 · v1 · 16
- **Trigger:** Data safety, the advertising-ID answer, the content-rating questionnaire or the target audience are filled in from memory, not from the build (CR14, CR18, Q16).
- **Unhandled:** a rejection, or later a policy strike (RESEARCH §3, #2–#4).
- **Should:** TRD §10's release checklist at every release: the merged manifest's permissions against Data safety and the PRD's data table.
- **Fix in:** `owner`, `Play Console`.
- **Reproduce:** `aapt2 dump permissions` (CLAUDE.md) on the release APK, compared with the Data safety form.

#### ST-3 · Play's yearly target-API rule — 10 · Yearly
- **Trigger:** Play raises the target API it requires each year. API 37 is expected around August 2027, not yet announced (TD-08).
- **Unhandled:** updates are blocked until the bump, which may also mean moving to AGP 9.
- **Should:** put the date in the owner's calendar when Google announces it, and bump in the first update after.
- **Fix in:** `build`, `owner`.
- **Reproduce:** nothing to run.

#### ST-4 · A library that isn't ready for 16 KB pages — 8 · v1 · 16
- **Trigger:** Play requires support for 16 KB memory pages (CR9). libGDX 1.14.2's native libraries are aligned (RESEARCH S27); a library added later may not be.
- **Unhandled:** Play Console warns about or blocks the upload, and the library fails to load on 16 KB phones.
- **Should:** check Play Console's App bundle explorer at every upload.
- **Fix in:** `build`.
- **Reproduce:** Play Console's App bundle explorer; or a 16 KB emulator image (a download, so it needs the owner's yes).

ST-5 (the pre-launch report) is in §4.

### 3.16 OP · Owner operations

#### OP-1 · "Rolling back" by re-uploading old code — 10 · Owner
- **Trigger:** a release goes bad. Play can't roll back (TRD §0.5), so the owner rebuilds the previous version's code under a higher versionCode.
- **Unhandled:** players who opened the bad version have saves from it. The "rollback" finds a newer save and blocks them with "This save is from a newer version. Update the game to keep playing.", although they already have the newest version there is.
- **Should:** never ship a lower `formatVersion` or `contentVersion` than a build already released. A rollback is a forward fix that still reads and writes the newest format.
- **Fix in:** `owner` (the release checklist). `build`: fail if either number is lower than in the last release tag.
- **Reproduce:** SV-7's downgrade test.

#### OP-2 · The launch email goes out from the support address — 8 · Owner
- **Trigger:** one email to up to 1,600 addresses (TRD §12, 1st), sent from the game's Gmail: the address that every build's Report a problem uses (TRD §6.1).
- **Unhandled:** Gmail's sending limits or spam flags restrict that account, and reports from every installed build go nowhere.
- **Should:** send from a different account or a Google Group, at most 450 a day. The support address only answers mail.
- **Fix in:** `owner`.
- **Reproduce:** nothing to run.

#### OP-3 · The form shows each respondent the others' emails — 8 · Owner, now · **fixed in the README**
- **Trigger:** the Google Form's "View results summary" is on. TRD §4.7 (flag 3) says the owner's checklist in `site/README.md` covers this, but the checklist didn't mention it.
- **Unhandled:** every sign-up can read every other sign-up's email address: a privacy breach on day one.
- **Should:** the checklist says to keep it off, and to remove the "Submit another response" link, which also cuts accidental duplicates (MS-1).
- **Fix in:** `owner`. **Done in this step:** both lines are now in `site/README.md`.
- **Reproduce:** send a test response. The confirmation page must show neither a summary link nor "Submit another response".

OP-4 (a lost upload key) and OP-5 (renaming the GitHub account) are in §4.

### 3.17 AB · Abuse

The waitlist's bots are MS-1.

#### AB-1 · A deal card forwarded as proof of earnings — 9 · v1 · 11
- **Trigger:** "bought ₹200 → sold ₹1,800" spreads through WhatsApp groups, where "earning apps" are a known scam genre.
- **Unhandled:** to players, press or a regulator, the game reads as a money game (CR1, RESEARCH L-1).
- **Should:** the card keeps "Game money. No real money." (F12). It never says "earn", "win" or "cash", and never looks like a payment receipt or a banknote.
- **Fix in:** `core` (the card), checked in a DESIGN review.
- **Reproduce:** DESIGN §10's checklist, on the card at the size it's shared.

#### AB-2 · Testers or friends post reviews — 9 · Owner
- **Trigger:** well-meaning testers or friends rate the game on Play (CR8, RESEARCH L-9).
- **Unhandled:** a breach of the fake-review rules: Play can remove the reviews or act against the listing.
- **Should:** the day-0 message to testers asks them not to review the game, and the owner never asks anyone for a rating.
- **Fix in:** `owner`.
- **Reproduce:** nothing to run.

#### AB-3 · Bots reuse the game's ad-unit IDs — 8 · M4
- **Trigger:** ad-unit IDs ship inside the APK, so anyone can copy them into a bot or a modified app (H5-10).
- **Unhandled:** invalid traffic on the owner's AdMob account, and a suspension.
- **Should:** H5-10's measures: the cap, rewards only for completed ads, app-ads.txt. The owner checks AdMob's policy center weekly (TRD §10).
- **Fix in:** `AdMob`, `owner`.
- **Reproduce:** nothing to run.

#### AB-4 · Clones and "earn money" APKs using the name — 6 · Owner
- **Trigger:** after launch, third-party APK sites repackage the game, or use its name and screenshots for a scam app (R33).
- **Unhandled:** players blame the real game for malware or ads, and reports arrive about builds the owner never made.
- **Should:** the waitlist page and the privacy policy say "The only official version is on Google Play". Reports from versions that aren't on Play get a stock reply. Impersonation is reported through Play.
- **Fix in:** `owner`.
- **Reproduce:** nothing to run.

---

## 4. The low ones (score 5 or less)

| ID | Score | Trigger → if unhandled | Should | Fix in | Reproduce | When |
|---|---|---|---|---|---|---|
| OP-4 | 5 | The upload key, or 2-step access to the Google account, is lost → no updates until Google resets the key, which takes days | CR11: the key in a password manager plus an offline copy; 2-step backup codes stored offline | owner | — | Owner |
| LC-7 | 4 | The game sits in the background for days without being killed → the checks that run at launch (Today's special, welcome back, DR26) are skipped on return | Run them on resume too | `core` | `cmd alarm set-time` 3 days ahead while it's in the background (§7) | v1 · 11 |
| DC-3 | 4 | Two phones on one Google account → two separate games; a restore picks one phone's backup | Designed: no sync (TRD §11, decision 2). The restore card names the date | — | — | Designed |
| HO-4 | 4 | A second share overwrites `deal-card.png` before the first app reads it, or the read grant doesn't reach the target through the chooser → the wrong card, or a blank one | A new file name per share, deleted at launch once over an hour old; the grant on both intents, with `ClipData` (from memory) | `android` | Share twice quickly, to two apps | v1 · 11 |
| ST-5 | 4 | Play's pre-launch crawler sees one blank view and flags accessibility → noise in the report | Read it; the known limit is in PRD §5 | Play Console | — | v1 · 16 |
| OP-5 | 4 | The owner renames their GitHub account → the privacy URL in every build, and on Play, breaks (TRD §6.1) | Never rename it; it's as permanent as the app ID | owner | — | Owner |
| AB-5 | 4 | A break-in on GitHub swaps the waitlist form link for a phishing form | 2-step verification on GitHub (CR11) | owner | — | Owner |
| GT-2 | 4 | The Play account on the phone changes → owned cosmetics differ from the cached list | Re-query at every launch; owned is whatever Play says (TRD §4.6) | `core` | Switch accounts in the Play Store | Purchases |
| MN-6 | 4 | A refunded theme is still equipped → a crash on the missing item, or the theme stays | Re-lock it at launch and fall back to the default theme (DR38) | `core` | A refunded test purchase | Purchases |
| DV-9 | 3 | TalkBack is on. The game is one view, and a double tap presses its centre (from memory) → whatever sits there, such as Accept | No irreversible button at the exact centre; the one announcement (DESIGN §6) | `core` | Settings → Accessibility → TalkBack | v1 · 12 |
| EF-2 | 2 | The last item is scrapped while a godown filter is on → "Nothing matches this filter" on an empty godown | "Empty" wins over "filtered" | `core` | Scrap the last item with a filter on | v1 · 9 |
| TC-3 | 2 | Flying west moves the calendar day back by one → Today's special reads "back tomorrow" | Designed (DR26); nothing to build | — | `cmd alarm set-timezone America/Los_Angeles` (§7) | Designed |
| AB-6 | 2 | Someone asks to remove an email address that isn't theirs | Reply to that address to confirm before deleting | owner | — | Owner |

Also watched, not scored: sound carrying on through the speaker when headphones are unplugged; the first sound after loading playing silently (Android loads sounds in the background); a long-press opening Report this line by accident. Each is noted for G1 or G2 if a tester reports it.

---

## 5. Findings

Gaps and conflicts this file found, each with its recommendation. **All applied on 2026-11-25, as recommended:** the product rules in the PRD (see its change log), APP-FLOW and DESIGN; the build decisions in the TRD, as TD-27 to TD-32.

### 5.1 For the PRD (product rules)

| # | Gap or conflict | From | Recommended |
|---|---|---|---|
| F-1 | What −10% and −25% apply to when selling | HE-1 | Buying: −10% and −25%, below the seller's price. Selling: +10% and +25%, above the customer's price. No offer can be worse for the player than the other side's current price; that's what Accept is for (F2) |
| F-2 | Whether scrap profit fills the goal bar; scrap value against walk-away prices | HE-2 | Only haggled sales fill the bar. A seller's walk-away price is never below the item's scrap value plus a margin (F7, DR4) |
| F-3 | Bidding while already leading | HE-4 | The bid buttons read "You lead" and do nothing until a rival bids (F5, DR17) |
| F-4 | The godown limit against "Back on Sort keeps everything" | HE-8 | A soft limit of 60. At or over it, bidding and buying from sellers say "Godown full". A won lot may push it over, and back on Sort still keeps everything (F5 AC10, F9, Q9) |
| F-5 | A first haggle that never ends in a deal | EF-1 | After two walk-in sellers leave without a deal, the stall opens anyway, with one hint. The first seller always fits within the starting cash (F1) |
| F-6 | Loan repayments that round to ₹0 | HE-9 | Each sale repays at least ₹1, or the rest of the loan if that's less (DR3, F11) |
| F-7 | Leaving the auction of Today's special | HE-13 | The confirmation says "Leave? Today's special lot goes to the rivals." (DR18, F6 AC4) |
| F-8 | Results lost to a restart | LC-3 | The deal result, the auction result, the walk-away Lesson and the tier-up survive a restart and show once. A first-time hint counts as seen only when dismissed (F15, PRD §7.10) |
| F-9 | How the waitlist is counted | MS-1 | Decide on unique emails, lower-cased and trimmed. Record the raw and unique counts. Sign-ups above about 10% of a post's views are flagged as likely bots (§12.1, Q1) |
| F-10 | What counts as play time | MS-4 | Only time within 60 s of the last tap (F18, Check 3) |
| F-11 | G2 with missing stats | MS-2 | Judge G2 on the testers who sent stats, if at least 10 did. Otherwise extend the test by a week and ask once more (§12.2, §12.3) |

### 5.2 For the TRD (how it's built)

| # | Finding | From | Recommended |
|---|---|---|---|
| T-1 | The save and the content pack would be read by reflection, which R8 breaks | SV-1 | Explicit keys with `JsonReader` and `JsonWriter`; fixture tests compare values (§4.5, §6.3) |
| T-2 | `--release 8` doesn't stop API 26 calls | SV-2 | A `check` test that rejects `java.nio.file`, `java.time`, `java.util.Base64`, `String.join` and `File.toPath` in `logic` and `core` (§1.2, §4.11) |
| T-3 | Two activity instances, and recreation | LC-1, LC-2, LC-4 | `singleTask` plus the task-root check; one `SaveWriter` per process, drained on dispose; a generation number for Start over; more `configChanges` (§3, §4.10) |
| T-4 | Formatting that follows the phone's language | DV-1, CL-3 | `Locale.ROOT` everywhere; a test under `mr-IN`, `bn-IN`, `ur-IN`, `ar-EG` and `tr-TR` (§4.2) |
| T-5 | A crash during load repeats at every launch | SV-6 | A loading marker; the next launch goes to `bak1` (§4.10) |
| T-6 | The newer-save rule looks only at `formatVersion` | SV-7, OP-1 | Also `writtenBy` and unknown content IDs. The build refuses a lower format or content version than the last release (§4.10, §6) |
| T-7 | Repairable saves are rejected | SV-3 | A repair list beside the invariants (§4.10) |
| T-8 | The backups rotate after every load | SV-8 | Rotate at day end, and at load only when the content differs (§3.5, §4.10) |
| T-9 | A command can be sent twice | IN-1 | Each command names its round, and stale ones are rejected (§5.2) |
| T-10 | `AuctionTick` accepts any step | HE-5 | At most 100 ms per tick (§5.2) |
| T-11 | §4.7 says only the launcher is exported, but the build already exports an AndroidX receiver | BR-1 | Correct §4.7; the guard allows a named list (§4.11) |
| T-12 | The backup rules ignore `<device-transfer>` | BR-2 | Write both sections and test both (§4.7 flag 2, §4.11) |
| T-13 | JDK 21 fails the build | BR-3 | Add `-this-escape` only on JDK 21 and newer, or set Android Studio's Gradle JDK to 17 (§1.2, TD-22) |
| T-14 | Debug and release builds can't share a phone | BR-5 | `applicationIdSuffix '.dev'`; the FileProvider authority from `${applicationId}` (§5.3) |
| T-15 | At M4, tester builds would carry real ad IDs | MN-1 | Register testers' phones as AdMob test devices; decide at M4 (§2.2, CR18, CR24) |

### 5.3 Applied in this step
- `site/README.md`: the form checklist now says to keep "View results summary" off, and to remove the "Submit another response" link (OP-3).

---

## 6. Dev-build switches the tests need

Debug builds only, in the dev menu beside the dev screens (PRD §6). Release builds don't contain them.

| Switch | What it does | Cases |
|---|---|---|
| Slow commit (500 ms, 2 s, 3 s) | The save writer waits before each write | IN-1, LC-4, HO-2, HO-3, DV-8 |
| Fail the next commit | The next write fails as if storage were full | SV-4, SV-5 |
| Crash during load | The next launch throws inside the loader | SV-6 |
| Stall one frame for 2 s | One frame blocks for 2 seconds | HE-5 |
| Day offset | Adds N days to the injected `Clock` | TC-1, TC-2, LC-7, MN-3, on phones where `cmd alarm` can't be used |

---

## 7. Verified on 2026-11-25

Run on the owner's PC in Git Bash: the `Medium_Phone_API_36.1` emulator (Android 16; a Google Play image, so no root) and the empty project's debug build. Every setting was put back afterwards.

| What | Command | Result |
|---|---|---|
| Process death | `"$ADB" shell input keyevent KEYCODE_HOME`, then `"$ADB" shell am kill $PKG` | The process is gone, the task stays in Recents, and a relaunch is a cold start |
| A second instance | `"$ADB" shell am start -n $PKG/.android.AndroidLauncher -f 0x18000000` | Two `AndroidLauncher` records in two tasks (t201, t202), in one process |
| Recreate the activity | `"$ADB" shell settings put system font_scale 1.3` (reset: `1.0`). Also `cmd uimode night yes` (reset: `no`), `wm density 546` (reset: `wm density reset`), `cmd locale set-app-locales $PKG --locales mr-IN` (reset: `--locales ""`) | Each logs `ActivityThread.handleRelaunchActivity` and libGDX's ~15 start-up lines again. The control, a volume key, logs none |
| Font size 200% | `"$ADB" shell settings put system font_scale 2.0` | Accepted |
| A narrow screen | `"$ADB" shell wm density 546` | 1080 px ≈ 316 dp |
| Navigation mode | `"$ADB" shell cmd overlay enable-exclusive --category com.android.internal.systemui.navbar.gestural` (or `.threebutton`) | Switches both ways. *Phase 4:* it also recreates a running game's activity |
| A camera cutout (Phase 4) | `"$ADB" shell cmd overlay enable-exclusive --category com.android.internal.display.cutout.emulation.hole` (also `.corner`, `.double`, `.tall`, `.waterfall`). Reset: `cmd overlay disable com.android.internal.display.cutout.emulation.hole` | The top inset grows from 63 to 136 px, and the running activity is recreated |
| The back gesture's edge zone (Phase 4) | `"$ADB" shell input swipe <x> 1500 <x+450> 1500 300`, in gesture navigation, with the game catching back | From either edge, a drag starting within 28 dp is a back; from 30 dp it reaches the game. The zone is `config_backGestureInset`, 30 dp at default sensitivity |
| Back, by key and by button (Phase 4) | `"$ADB" shell input keyevent 4` injects a key; `"$ADB" shell input tap 229 2337` presses the 3-button bar's back | On Android 13+ they differ: the key also reaches the view as a key event; the bar's button only fires the callback |
| Battery saver | `"$ADB" shell cmd battery unplug`, then `"$ADB" shell settings put global low_power 1`. Reset: `low_power 0`, `cmd battery reset` | `mSettingBatterySaverEnabled=true` |
| Animations off | `"$ADB" shell settings put global animator_duration_scale 0` (reset: `1`) | Set |
| The clock | `"$ADB" shell settings put global auto_time 0`, then `"$ADB" shell cmd alarm set-time <epoch ms>` | The date moved back 3 days, with no root |
| The time zone | `"$ADB" shell cmd alarm set-timezone America/Los_Angeles` (restore: `Asia/Calcutta`) | Changed and restored |
| Storage full | `AV=$("$ADB" shell df -k /data \| tr -d '\r' \| awk 'NR==2{print $4}')`, then `"$ADB" shell fallocate -l $(( (AV-2048)*1024 )) /data/local/tmp/fill.bin`. Clean up: `"$ADB" shell rm /data/local/tmp/fill.bin` | 330 MB free became 1.9 MB. The app's 4 MB write stopped at 1.9 MB and left a partial file |
| A damaged save | `"$ADB" shell "run-as $PKG sh -c 'mkdir -p files/save && printf \"BBSAVE 00000000 7\n{broken\" > files/save/game.json'"` | Written into the app's private storage. Debug builds only; quoted for the device's shell |
| Start-up time | `"$ADB" shell am force-stop $PKG`, then `"$ADB" shell am start -W -n $PKG/.android.AndroidLauncher` | TotalTime 3,986 ms on the first launch, 2,673 ms after a force-stop |
| Exported components | `aapt2 dump xmltree --file AndroidManifest.xml android/build/outputs/apk/debug/android-debug.apk` | The launcher, plus `ProfileInstallReceiver` (exported, protected by `DUMP`). `InitializationProvider` isn't exported |
| API levels | The SDK's `platforms/android-36/data/api-versions.xml` | `java.nio.file`, `java.time`, `Base64`, `String.join`, `File.toPath`: API 26. `Math.addExact`, `multiplyExact`, `floorDiv`: API 24 |
| JDK 21 against 17 | `javac -Xlint:all,-path,-processing,-options -Werror --release 8` on a constructor that calls an overridable method | JDK 17.0.11 passes. JDK 21.0.9, Android Studio's, fails on `this-escape`. JDK 17 rejects `-Xlint:-this-escape` as invalid |
| Locale digits | `String.format("%,d", 125000L)` under each default locale, on JDK 17.0.11 | `mr-IN`, `bn-IN`, `as-IN`, `ur-IN`, `ne-NP` and `ar-EG` give non-Latin digits; `hi-IN`, `ta-IN` and `en-IN` give Latin ones. Under `tr-TR`, `"TRADING".toLowerCase()` gives `tradıng`. `Locale.ROOT` and `Long.toString` are safe |

**Didn't work:** `settings put global always_finish_activities 1` doesn't take effect while Android 16 runs. Switch Developer options → "Don't keep activities" by hand.

**A Git Bash trap:** an argument that starts with `/` is rewritten into a Windows path: `/data` became `C:/Program Files/Git/data`. Set `MSYS_NO_PATHCONV=1` for any adb command that names a device path.

---

## 8. Sources

- Android Developers, [Back up user data with Auto Backup](https://developer.android.com/identity/data/autobackup): without `<device-transfer>`, all of the app's data moves in a device-to-device transfer (BR-2).
- Android Developers, [Behavior changes: apps targeting Android 12](https://developer.android.com/about/versions/12/behavior-changes-12): `allowBackup` and device-to-device transfers (BR-2).
- The Android SDK's `api-versions.xml` for platform 36, on the owner's PC (SV-2).
